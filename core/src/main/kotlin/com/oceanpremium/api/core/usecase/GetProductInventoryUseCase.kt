package com.oceanpremium.api.core.usecase

import com.oceanpremium.api.core.currentrms.ProductsApiImpl
import com.oceanpremium.api.core.currentrms.response.dto.mapper.CurrentRmsBaseDtoMapper
import com.oceanpremium.api.core.currentrms.response.dto.mapper.ProductDtoMapper
import com.oceanpremium.api.core.currentrms.response.dto.parameter.LocationStoreResolver
import com.oceanpremium.api.core.currentrms.response.dto.product.ProductDto
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import retrofit2.Response

class ResponseContainer(
    private val successResponse: Response<Any>?,
    private val errorResponse: Response<Any>?,
    val dtoMapper: CurrentRmsBaseDtoMapper) {

    fun getRawResponse(): Response<Any>? {

        if (successResponse != null) {
            return successResponse
        }

        return errorResponse
    }
}

/**
 * Checks the availability for the given search term(s). The availability is checked on multiple stores based on given
 * collection- & drop-off locations, which are mapped to multiple stores. Based on the current CurrentRMS API design.
 * querying on multiple stores is a call per store. Thus, if a location X is mapped to N stores. The invocation of the use case
 * will result in N calls to the CurrentRMS API, to determine the availability for N stores. The resulting availability count
 * then gets accumulated and presented back as a total availability count for all stores bound to given input location(s)
 * (collection / drop-off)
 */
interface GetProductInventoryUseCase {
    fun execute(queryParameters: MutableMap<String, String>, headers: HttpHeaders): ResponseContainer
}


/** {@inheritDoc} */
class GetProductInventoryUseCaseImpl(@Autowired private val locationStoreResolver: LocationStoreResolver,
                                     @Autowired private val productsApi: ProductsApiImpl) :
    GetProductInventoryUseCase {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    @Suppress("UNCHECKED_CAST")
    override fun execute(queryParameters: MutableMap<String, String>, headers: HttpHeaders): ResponseContainer {
        // List of mapped store ids for the given input location(s), for which we need to query each store,
        // to get the product inventory of
        val storeIds = locationStoreResolver.resolveStoreByLocation(queryParameters)
        var seedSuccessResponse: Response<Any>? = null
        var seedErrorResponse: Response<Any>? = null
        val responses: MutableList<Response<Any>> = mutableListOf()
        val dtos: MutableList<CurrentRmsBaseDtoMapper> = mutableListOf()
        var combinedDto: CurrentRmsBaseDtoMapper? = null

        storeIds?.forEach { storeId ->
            val response = productsApi.getProductsInventory(queryParameters, headers, storeId)

            logger.debug("Response code for query on storeId: $storeId - ${response?.code()}")

            if (response != null) {

                when {
                    !response.isSuccessful && seedErrorResponse == null -> seedErrorResponse = response
                }

                when {
                    response.isSuccessful && seedSuccessResponse == null -> seedSuccessResponse = response
                }

                responses.add(response)
            }
        }

        responses.forEach {
            dtos.add(ProductDtoMapper(it.code(), it))
        }

        dtos.forEach { dto ->
            when {
                combinedDto == null || combinedDto?.httpStatus !== HttpStatus.OK -> {
                    combinedDto = dto
                }
                else -> {
                    if (dto.httpStatus == HttpStatus.OK) {

                        val combinedDtoData: List<ProductDto> = combinedDto!!.data as List<ProductDto>
                        val currentDtoData: List<ProductDto>? = dto.data as List<ProductDto>?

                        combinedDtoData.forEach { productDtoItem ->
                            val itemQuantityAvailable: Double? = productDtoItem.rates.first().quantityAvailable?.toDouble()

                            // find the matching dto item in current dto data set, based on its id, to update the total count of available quantity
                            val currentDtoItem = currentDtoData?.find { it.id == productDtoItem.id }

                            if (currentDtoItem != null) {
                                logger.debug("Found candidate: ${currentDtoItem.name}")
                                val currentQuantityAvailable: Double? = currentDtoItem.rates.first().quantityAvailable?.toDouble()

                                if (itemQuantityAvailable != null && currentQuantityAvailable != null) {
                                    val totalQuantityAvailable = itemQuantityAvailable.plus(currentQuantityAvailable)

                                    logger.debug(
                                        "item id: ${productDtoItem.id} : " +
                                            "current quantity: $currentQuantityAvailable, " +
                                            "item id:${currentDtoItem.id} " +
                                            "addition quantity: $itemQuantityAvailable, " +
                                            "total new quantity: $totalQuantityAvailable"
                                    )

                                    productDtoItem.rates.first().quantityAvailable = totalQuantityAvailable.toString()
                                }
                            }
                        }
                    }
                }
            }
        }

        val filteredData: List<ProductDto>? = (combinedDto?.data as List<ProductDto>?)?.filter{ p -> p.rates.first().quantityAvailable?.toDouble()!! > 0 }
        combinedDto?.data = filteredData

        return ResponseContainer(
            seedSuccessResponse,
            seedErrorResponse,
            combinedDto!!
        )
    }
}

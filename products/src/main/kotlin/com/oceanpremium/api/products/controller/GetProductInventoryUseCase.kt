package com.oceanpremium.api.products.controller

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
import java.lang.NumberFormatException

class ResponseContainer(val successResponse: Response<Any>?, val errorResponse: Response<Any>?, val productInventoryResponseContainers: List<ProductInventoryResponseContainer>) {

    fun getResponse(): Response<Any>? {
        if (successResponse != null) {
            return successResponse
        }

        return errorResponse
    }
}

class ProductInventoryResponseContainer(val rawResponse: Response<Any>, val dtoMapper: CurrentRmsBaseDtoMapper)


/**
 * Checks the availability for the given search term(s). The availability is checked on multiple stores based on given
 * collection- & drop-off locations, which are mapped to multiple stores. Based on the current CurrentRMS API design.
 * querying on multiple stores is a call per store. Thus, if a location X is mapped to N stores. The invocation of the usecase
 * will result in N calls to the CurrentRMS API, to determine the availability for N stores. The resulting availability count
 * then gets accumulated and presented back as a total availability count for all stores bound to given input location(s) (collection / drop-off)
 */
interface GetProductInventoryUseCase {
    fun execute(queryParameters: MutableMap<String, String>, headers: HttpHeaders): ResponseContainer
}


/** {@inheritDoc} */
class GetProductInventoryUseCaseImpl(@Autowired private val locationStoreResolver: LocationStoreResolver,
                                     @Autowired private val productsApi: ProductsApiImpl) : GetProductInventoryUseCase {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    override fun execute(queryParameters: MutableMap<String, String>, headers: HttpHeaders): ResponseContainer {
        // List of mapped store ids for the given input location(s), for which we need to query each store, to get the product inventory of
        val storeIds = locationStoreResolver.resolveStoreByLocation(queryParameters)
        val productResponseContainers: MutableList<ProductInventoryResponseContainer> = mutableListOf()
        var seedSuccessResponse: Response<Any>? = null
        var seedErrorResponse: Response<Any>? = null

        var totalQuantityAvailable = 0

        storeIds?.forEach {
            val response = productsApi.getProductsInventory(queryParameters, headers, it)

            logger.debug("Response code for query on storeId: $it - ${response?.code()}")

            @Suppress("UNCHECKED_CAST")
            when {
                response == null || response.code() != HttpStatus.OK.value() -> {
                    if (seedErrorResponse == null) {
                        seedErrorResponse = response
                    }
                }

                response.code() == HttpStatus.OK.value() -> {
                    val dto = ProductDtoMapper(response.code(), response)
                    val productDtos = dto.data as List<ProductDto>?

                    if (seedSuccessResponse == null) {
                        seedSuccessResponse = response
                    }

                    productDtos?.forEach { dtoItem ->
                        try {
                            when {
                                dtoItem.rates.first().quantityAvailable != null -> {
                                    val dtoItemQuantityAvailable = dtoItem.rates.first().quantityAvailable
                                    val itemQuantity = (dtoItemQuantityAvailable!!.toDouble()).toInt()
                                    totalQuantityAvailable += itemQuantity

                                    logger.debug("item: ${dtoItem.id}  itemCount: $dtoItemQuantityAvailable totalCount: $totalQuantityAvailable")
                                }
                            }
                        } catch (e: NumberFormatException) {
                            e.printStackTrace()
                            logger.error("Failed to parse quantityAvailable for item: ${dtoItem.id} - quantity: ${dtoItem.rates.first().quantityAvailable} ${e.message}")
                        }
                    }

                    val productContainerItem = ProductInventoryResponseContainer(response, dto)
                    productResponseContainers.add(productContainerItem)

                }
            }
        }

        logger.debug("Total Quantity Available: $totalQuantityAvailable")

        return ResponseContainer(seedSuccessResponse, seedErrorResponse, productResponseContainers)
    }
}

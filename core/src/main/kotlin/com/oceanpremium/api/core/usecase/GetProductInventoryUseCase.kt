package com.oceanpremium.api.core.usecase

import com.oceanpremium.api.core.currentrms.ProductsApiImpl
import com.oceanpremium.api.core.currentrms.response.dto.mapper.CurrentRmsBaseDtoMapper
import com.oceanpremium.api.core.currentrms.response.dto.mapper.ProductDtoMapper
import com.oceanpremium.api.core.currentrms.response.dto.product.ProductDto
import com.oceanpremium.api.core.exception.throwable.BadRequestException
import com.oceanpremium.api.core.resolver.LocationStoreResolver
import com.oceanpremium.api.core.resolver.WrappedStores
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import retrofit2.Response

class ResponseContainer(
    val response: Response<Any>?,
    val dtoMapper: CurrentRmsBaseDtoMapper
)

/**
 * Checks the availability for the given search term(s). The availability is checked on multiple stores based on given
 * collection- & drop-off locations, which are mapped to multiple stores. Based on the current CurrentRMS API design.
 * querying on multiple stores is a call per store. Thus, if a location X is mapped to N stores. The invocation of the use case
 * will result in N calls to the CurrentRMS API, to determine the availability for N stores. The resulting availability count
 * then gets accumulated and presented back as a total availability count for all stores bound to given input location(s)
 * (collection / drop-off)
 */
interface GetProductInventoryUseCase {
    fun execute(queryParameters: Map<String, String>, headers: HttpHeaders): ResponseContainer
}

/** {@inheritDoc} */
class GetProductInventoryUseCaseImpl(
    @Autowired private val locationStoreResolver: LocationStoreResolver,
    @Autowired private val productsApi: ProductsApiImpl
) :
    GetProductInventoryUseCase {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    @Suppress("UNCHECKED_CAST")
    override fun execute(queryParameters: Map<String, String>, headers: HttpHeaders): ResponseContainer {
        // List of mapped store ids for the given input location(s), for which we need to query each store,
        // to get the product inventory of
        val stores: WrappedStores? = locationStoreResolver.resolveStoresByLocation(queryParameters)
        val allStoreIds: List<Int>? = stores?.allStores?.map {it.id}

        if (stores == null || stores.allStores.isNullOrEmpty()) {
            throw BadRequestException("Could not resolve location(s) therefore cannot determine stock")
        }

        val productInventoryResponse = productsApi.getProductsInventory(queryParameters, headers, allStoreIds)
        logger.debug("Response code for query on storeIds: $allStoreIds - ${productInventoryResponse?.code()}")

        val dto = ProductDtoMapper(productInventoryResponse!!.code(), productInventoryResponse)
        val combinedDto = ProductDtoMapper(productInventoryResponse.code(), productInventoryResponse)


        if (dto.httpStatus == HttpStatus.OK) {
            val combinedDtoData: List<ProductDto> = dto.data as List<ProductDto>
            combinedDtoData.forEach {productDto ->
                var totalQuantityAvailable = 0.0
                productDto.storeQuantities?.forEach { storeQuantityDto ->
                    val quantityAvailable = storeQuantityDto.quantityAvailable?.toDouble()
                    if (quantityAvailable != null) {
                        totalQuantityAvailable = totalQuantityAvailable.plus(quantityAvailable)
                    }
                }

                productDto.rates.first().quantityAvailable = totalQuantityAvailable.toString()
            }
            combinedDto.data = combinedDtoData
        }

        return ResponseContainer(
            productInventoryResponse,
            combinedDto
        )
    }
}

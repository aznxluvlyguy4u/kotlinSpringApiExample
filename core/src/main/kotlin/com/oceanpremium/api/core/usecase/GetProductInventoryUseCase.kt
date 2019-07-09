package com.oceanpremium.api.core.usecase

import com.oceanpremium.api.core.currentrms.ProductsApiImpl
import com.oceanpremium.api.core.currentrms.response.dto.mapper.CurrentRmsBaseDtoMapper
import com.oceanpremium.api.core.currentrms.response.dto.mapper.ErrorResponse
import com.oceanpremium.api.core.currentrms.response.dto.mapper.ProductDtoMapper
import com.oceanpremium.api.core.currentrms.response.dto.product.ProductDto
import com.oceanpremium.api.core.exception.handler.ApiError
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
        val nativeStoreIds: List<Int>? = stores?.nativeStores?.map {it.id}
        val alternativeStoreIds: List<Int>? = stores?.alternativeStores?.map {it.id}
        val grayStoreIds: List<Int>? = stores?.grayStores?.map {it.id}
        val newItemStoreIds: List<Int>? = stores?.newItemStores?.map {it.id}
        val allStoreIds: List<Int>? = stores?.allStores?.map {it.id}
        var combinedDto: CurrentRmsBaseDtoMapper?

        val productInventoryResponse = productsApi.getProductsInventory(queryParameters, headers, allStoreIds)
        logger.debug("Response code for query on storeIds: $allStoreIds - ${productInventoryResponse?.code()}")

        val dto = ProductDtoMapper(productInventoryResponse!!.code(), productInventoryResponse)

        /**
         * CurrentRms returns a 200 OK for empty result sets AND after accepting multi-store ids as input,
         * when no store ids are given, default response is given of the store id with lowest ID.
         * As a best practice for REST API, the response should be a 404 NOT FOUND when an empty result set is returned
         * OR when no store ids is supplied.
         */
        if (allStoreIds.isNullOrEmpty()) {
            dto.httpStatus = HttpStatus.NOT_FOUND
            val errorResponse = ErrorResponse()
            errorResponse.errors.add(dto.httpStatus.reasonPhrase)
            dto.error = ApiError(code = dto.httpStatus.value(), message = errorResponse)
        }

        combinedDto = dto

        if (dto.httpStatus == HttpStatus.OK) {
            val combinedDtoData: List<ProductDto> = dto.data as List<ProductDto>
            combinedDtoData.forEach {productDto ->
                var totalQuantityAvailable: Double = 0.0
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



//
//        val filteredData: List<ProductDto>? = (combinedDto?.data as List<ProductDto>?)?.filter{ p ->
//            p.rates.first().quantityAvailable?.toDouble() != null && p.rates.first().quantityAvailable?.toDouble()!! > 0
//        }
//
//        if (filteredData == null || filteredData.isEmpty()) {
//            combinedDto?.data = combinedDto?.data as List<ProductDto>?
//        } else {
//            combinedDto?.data = filteredData
//        }

//
//        combinedDto?.meta.totalRowCount = filteredData?.size
//        combinedDto?.meta.rowCount

        // default per page is 20, stel filtered data size is 24, en front end vraag per page 5, page 2, per page is 20.

        // filteredData size 24
        // request per page 5
        // request page 1

        // ?page=1 & per_page=5
        // totalrowcount = 24
        // page = 1
        // per page = 5
        // rowcount =

        return ResponseContainer(
            productInventoryResponse,
            combinedDto
        )
    }
}

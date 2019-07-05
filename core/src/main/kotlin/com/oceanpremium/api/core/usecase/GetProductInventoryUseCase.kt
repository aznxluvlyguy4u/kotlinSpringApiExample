package com.oceanpremium.api.core.usecase

import com.oceanpremium.api.core.currentrms.ProductsApiImpl
import com.oceanpremium.api.core.currentrms.response.dto.mapper.CurrentRmsBaseDtoMapper
import com.oceanpremium.api.core.currentrms.response.dto.mapper.ProductDtoMapper
import com.oceanpremium.api.core.resolver.LocationStoreResolver
import com.oceanpremium.api.core.resolver.WrappedStores
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import retrofit2.Response


class ResponseContainer(
    private val successResponse: Response<Any>?,
    private val errorResponse: Response<Any>?,
    val dtoMapper: CurrentRmsBaseDtoMapper
) {

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
        val wrappedStores: WrappedStores? = locationStoreResolver.resolveStoresByLocation(queryParameters)
        val nativeStoreIds: List<Int>? = wrappedStores?.nativeStores?.map {it.id}
        val alternativeStoreIds: List<Int>? = wrappedStores?.alternativeStores?.map {it.id}
        val grayStoreIds: List<Int>? = wrappedStores?.grayStores?.map {it.id}
        val newItemStoreIds: List<Int>? = wrappedStores?.newItemStores?.map {it.id}
        val allStoreIds: List<Int>? = wrappedStores?.allStores?.map {it.id}

        var seedSuccessResponse: Response<Any>? = null
        var seedErrorResponse: Response<Any>? = null
        val dtos: MutableList<CurrentRmsBaseDtoMapper> = mutableListOf()
        var combinedDto: CurrentRmsBaseDtoMapper? = null

        val allStoresResponse = productsApi.getProductsInventory(queryParameters, headers, allStoreIds)
        logger.debug("Response code for query on storeIds: $allStoreIds - ${allStoresResponse?.code()}")


        val dto = ProductDtoMapper(allStoresResponse!!.code(), allStoresResponse)

//        responses.forEach {
//            dtos.add(ProductDtoMapper(it.code(), it))
//        }

//        dtos.forEach { dto ->
//            when {
//                combinedDto == null || combinedDto?.httpStatus !== HttpStatus.OK -> {
//                    combinedDto = dto
//                }
//                else -> {
//                    if (dto.httpStatus == HttpStatus.OK) {
//
//                        val combinedDtoData: List<ProductDto> = combinedDto!!.data as List<ProductDto>
//                        val currentDtoData: List<ProductDto>? = dto.data as List<ProductDto>?
//
//                        combinedDtoData.forEach { productDtoItem ->
//                            val itemQuantityAvailable: Double? = productDtoItem.rates.first().quantityAvailable?.toDouble()
//
//                            // find the matching dto item in current dto data set, based on its id, to update the total count of available quantity
//                            val currentDtoItem = currentDtoData?.find { it.id == productDtoItem.id }
//
//                            if (currentDtoItem != null) {
//                                logger.debug("Found candidate: ${currentDtoItem.name}")
//                                val currentQuantityAvailable: Double? = currentDtoItem.rates.first().quantityAvailable?.toDouble()
//
//                                if (itemQuantityAvailable != null && currentQuantityAvailable != null) {
//                                    val totalQuantityAvailable = itemQuantityAvailable.plus(currentQuantityAvailable)
//
//                                    logger.debug(
//                                        "item id: ${productDtoItem.id} : " +
//                                            "current quantity: $currentQuantityAvailable, " +
//                                            "item id:${currentDtoItem.id} " +
//                                            "addition quantity: $itemQuantityAvailable, " +
//                                            "total new quantity: $totalQuantityAvailable"
//                                    )
//
//                                    productDtoItem.rates.first().quantityAvailable = totalQuantityAvailable.toString()
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
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
            seedSuccessResponse,
            seedErrorResponse,
            combinedDto!!
        )
    }
}

package com.oceanpremium.api.core.usecase

import com.oceanpremium.api.core.currentrms.ProductsApiImpl
import com.oceanpremium.api.core.currentrms.response.dto.mapper.CurrentRmsBaseDtoMapper
import com.oceanpremium.api.core.currentrms.response.dto.mapper.ProductDtoMapper
import com.oceanpremium.api.core.currentrms.response.dto.product.ProductDto
import com.oceanpremium.api.core.enum.WarehouseStoreType
import com.oceanpremium.api.core.exception.throwable.BadRequestException
import com.oceanpremium.api.core.model.Store
import com.oceanpremium.api.core.model.Stores
import com.oceanpremium.api.core.resolver.LocationStoreResolver
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import retrofit2.Response

class ResponseContainer(
    val response: Response<Any>?,
    val dtoMapper: CurrentRmsBaseDtoMapper,
    val stores: Stores
)

/**
 * Checks the availability for the given search term(s). The availability is checked on multiple stores based on given
 * collection- & drop-off locations, which are mapped to multiple stores. Based on the current CurrentRMS API design.
 * querying on multiple stores in a single call (which was request of us to Current RMS to support querying
 * the availability of products on multiple stores in one requests. If a location X is mapped to N stores. The invocation of the use case
 * will result in 1 call to the CurrentRMS API, to determine the availability for N stores. The resulting availability count
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
        val stores: Stores = locationStoreResolver.resolveStoresByLocation(queryParameters)
            ?: throw BadRequestException("Could not resolve location(s) therefore cannot determine stock")

        val allStoreIds = stores.all?.map { it.id }

        val productInventoryResponse = productsApi.getProductsInventory(queryParameters, headers, allStoreIds)
        logger.debug("Response code for query on storeIds: $allStoreIds - ${productInventoryResponse?.code()}")

        val productDtos = ProductDtoMapper(productInventoryResponse!!.code(), productInventoryResponse)
        val productItems = productDtos.data as List<ProductDto>

        productItems.forEach { productDtoItem ->
            val mappedStores = mapStoreQuantitiesToStoreDto(productDtoItem, stores.all)
            val totalQuantityAvailable = determineProductAvailability(mappedStores)
            productDtoItem.rates.first().quantityAvailable = "$totalQuantityAvailable"

            val native = mappedStores.filter { it.type == WarehouseStoreType.NATIVE }
            val alternative = mappedStores.filter { it.type == WarehouseStoreType.ALTERNATIVE }
            val gray = mappedStores.filter { it.type == WarehouseStoreType.GRAY }
            val newItems = mappedStores.filter { it.type == WarehouseStoreType.NEW_ITEMS }
            val storesWrapper = Stores(native, alternative, gray, newItems, mappedStores)

            productDtoItem.stores = storesWrapper
        }

        return ResponseContainer(
            productInventoryResponse,
            productDtos,
            stores
        )
    }

    private fun mapStoreQuantitiesToStoreDto(productDtoItem: ProductDto, stores: List<Store>?): List<Store> {
        var totalQuantityAvailable = 0.0
        val matchedStoreItems: MutableList<Store> = mutableListOf()

        logger.debug("********** ${productDtoItem.id} **********")

        productDtoItem.allStoreQuantities?.forEach { storeQuantityDto ->
            val matchingStore = stores?.firstOrNull { it.id == storeQuantityDto.storeId }

            if (matchingStore != null) {
                logger.debug("Found matching store: ${matchingStore.id} ${matchingStore.name} Q: ${storeQuantityDto.quantityAvailable}")

                val productItemStore = Store(
                    matchingStore.id,
                    matchingStore.name,
                    matchingStore.minimumDeliveryHours,
                    matchingStore.deliveryCost,
                    storeQuantityDto.quantityAvailable
                )

                productItemStore.type = matchingStore.type

                totalQuantityAvailable = totalQuantityAvailable.plus(productItemStore.quantityAvailable!!.toDouble())

                matchedStoreItems.add(productItemStore)
            }
        }

        productDtoItem.rates.first().quantityAvailable = "%.1f".format(totalQuantityAvailable)

        return matchedStoreItems
    }

    /**
     * Determines for all queried stores that total availability for given product.
     */
    private fun determineProductAvailability(stores: List<Store>?): Int {
        var totalQuantityAvailable = 0

        stores?.forEach { store ->
            val quantityPerStore = store.quantityAvailable?.toDouble()?.toInt()

            if (quantityPerStore != null && quantityPerStore > 0) {
                totalQuantityAvailable += quantityPerStore
            }
        }

        return totalQuantityAvailable
    }
}

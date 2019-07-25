package com.oceanpremium.api.core.usecase

import com.oceanpremium.api.core.currentrms.response.dto.product.ProductDto
import com.oceanpremium.api.core.enum.AvailabilityStateType
import com.oceanpremium.api.core.exception.throwable.BadRequestException
import com.oceanpremium.api.core.model.*
import com.oceanpremium.api.core.util.DateTimeUtil
import com.oceanpremium.api.core.util.DateTimeUtil.CURRENT_RMS_API_DATE_ISO8601_FORMAT
import org.joda.time.Days
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import java.lang.Exception

/**
 * Get the availability for batch POSTED product items and check against the available quantityAvailable
 * has sufficient stock levels compared to the wanted quantityAvailable. This is can be used by, for example,
 * for checking the availability of a basket where multiple products are added.
 */
interface CheckProductBatchAvailabilityUseCase {
    @Throws(Exception::class)
    fun execute(productItems: List<ProductAvailabilityItemDto>): ProductAvailabilityResponse
}

/**
 * @inherit
 */
class CheckProductBatchAvailabilityUseCaseUseCaseImpl(
    @Autowired private val getProductInventoryUseCase: GetProductInventoryUseCase
) : CheckProductBatchAvailabilityUseCase {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
        private const val PAYLOAD_EMPTY_ERROR_MESSAGE = "Payload may not contain empty array"
        private val DEFAULT_RENTAL_DAYS_DURATION = Days.ONE
    }

    /**
     * For each item check it's availability.
     */
    override fun execute(productItems: List<ProductAvailabilityItemDto>): ProductAvailabilityResponse {

        validateInput(productItems)

        productItems.forEach { productAvailabilityItem ->

            logger.debug(
                "check availability for product with id: ${productAvailabilityItem.id} on location collection: " +
                        "${productAvailabilityItem.location?.collection?.id} - dropOff: ${productAvailabilityItem.location?.delivery?.id} " +
                        "in period: ${productAvailabilityItem.period?.start} - ${productAvailabilityItem.period?.end}"
            )

            val result = getProductInventoryUseCase.execute(
                buildQueryParametersMap(productAvailabilityItem, false),
                HttpHeaders.EMPTY
            )

            @Suppress("UNCHECKED_CAST")
            val productDtos = result.dtoMapper.data as List<ProductDto>?
            val productDtoItem = productDtos?.firstOrNull { productResultItem ->
                productResultItem.id == productAvailabilityItem.id
            }

            updateAvailability(
                productAvailabilityItem,
                productDtoItem,
                false
            )

            //Check the availability of provided accessories for the given product, adjust the parent product
            //to reflect a state that matched the availability of both the parent product and accessory.
            productAvailabilityItem.accessories.forEach { accessoriesAvailabilityItem ->

                if (accessoriesAvailabilityItem.period == null) {
                    accessoriesAvailabilityItem.period = productAvailabilityItem.period
                }

                if (accessoriesAvailabilityItem.location == null) {
                    accessoriesAvailabilityItem.location = productAvailabilityItem.location
                }

                val map = buildQueryParametersMap(accessoriesAvailabilityItem, true, productAvailabilityItem)

                val accessoriesResult = getProductInventoryUseCase.execute(
                    map,
                    HttpHeaders.EMPTY
                )

                @Suppress("UNCHECKED_CAST")
                val accessoriesDtos = accessoriesResult.dtoMapper.data as List<ProductDto>?

                val accessoryDtoItem = accessoriesDtos?.firstOrNull { accessoriesResultItem ->
                    accessoriesResultItem.id == accessoriesAvailabilityItem.id
                }

                updateAvailability(
                    accessoriesAvailabilityItem,
                    accessoryDtoItem,
                    true
                )
            }

        }

        val totalCostOfAvailableProducts = computeTotalCostOfAllItems(productItems)

        return ProductAvailabilityResponse(
            "%.2f".format(totalCostOfAvailableProducts),
            productItems,
            listOf(),                   //currently unused
            listOf(),                   //currently unused
            "%.2f".format(0.0)   //currently unused
        )
    }

    @Throws(BadRequestException::class)
    private fun validateInput(productItems: List<ProductAvailabilityItemDto>) {

        if (productItems.isEmpty()) {
            throw BadRequestException(PAYLOAD_EMPTY_ERROR_MESSAGE)
        }

        productItems.forEach { productAvailabilityItem ->

            if (productAvailabilityItem.period?.start != null
                && productAvailabilityItem.period?.end != null
                && Days.daysBetween(
                    productAvailabilityItem.period?.start,
                    productAvailabilityItem.period?.end
                ) == Days.ZERO
            ) {

                throw BadRequestException(
                    "Supplied rental period (" +
                            "start: ${productAvailabilityItem.period?.start}, " +
                            "end: ${productAvailabilityItem.period?.end} " +
                            "must be at minimum: P1D, for product id: ${productAvailabilityItem.id}, for more information see section: " +
                            "Duration on the following page for understanding the provided Duration format: https://en.wikipedia.org/w/index.php?title=ISO_8601"
                )

            } else {
                val x = Days.daysBetween(productAvailabilityItem.period?.start, productAvailabilityItem.period?.end)
                logger.debug("${x.days}")
            }

            if (productAvailabilityItem.quantity == 0) {
                productAvailabilityItem.message =
                    "Quantity supplied for product: ${productAvailabilityItem.id} must be greater then 0."
                productAvailabilityItem.availabilityState = AvailabilityStateType.NOT_AVAILABLE
                productAvailabilityItem.quantityAvailable = productAvailabilityItem.quantity

                throw BadRequestException(productAvailabilityItem.message)
            }

            productAvailabilityItem.accessories.forEach {
                if (it.quantity == 0) {
                    it.message = "Quantity supplied for product: ${it.id} must be greater then 0."
                    it.availabilityState = AvailabilityStateType.NOT_AVAILABLE
                    it.quantityAvailable = productAvailabilityItem.quantity

                    throw BadRequestException(it.message)
                }
            }
        }
    }

    private fun updateAvailability(
        productAvailabilityItem: ProductAvailabilityItemDto,
        productDtoItem: ProductDto?,
        isAccessory: Boolean
    ) {
        // Determine quantity available per store type and total and update product item state accordingly
        val stockDetermination = determineStoresStock(productAvailabilityItem.quantity, productDtoItem?.stores)
        productAvailabilityItem.stock = stockDetermination
        productAvailabilityItem.availabilityState = stockDetermination.availabilityState

        productAvailabilityItem.images = productDtoItem?.images
        productAvailabilityItem.name = productDtoItem?.name
        productAvailabilityItem.rates = productDtoItem?.rates
        productAvailabilityItem.type = productDtoItem?.type

        // update / override quantityAvailable with truly filtered and determined available quantity
        productAvailabilityItem.rates?.first()?.quantityAvailable =
            "%.1f".format(stockDetermination.quantityTotallyAvailable.toDouble())

        productAvailabilityItem.quantityAvailable = stockDetermination.quantityTotallyAvailable

        // If it is an accessory, do not show the parent total, or accessories total, thus only show that on parent node
        if (isAccessory) {
            productAvailabilityItem.totalCostAccessories = null
        }
    }

    @Suppress("UNUSED")
    private fun determineStoresStock(quantityRequested: Int, stores: Stores?): StockDetermination {
        var quantityInNative = 0
        val nativeStoresWithStock = mutableListOf<Store>()

        var quantityInAlternative = 0
        val alternativeStoresWithStock = mutableListOf<Store>()

        var quantityInGray = 0
        val grayStoresWithStock = mutableListOf<Store>()

        var quantityInNewItems = 0
        val newItemStoresWithStock = mutableListOf<Store>()

        var totalQuantityAvailable = 0

        // Native
        stores?.native?.forEach { store ->
            val quantityPerStore = store.quantityAvailable?.toDouble()?.toInt()

            if (quantityPerStore != null && quantityPerStore > 0) {
                quantityInNative += quantityPerStore
                nativeStoresWithStock.add(store)
            }
        }

        // Alternative
        stores?.alternative?.forEach { store ->
            val quantityPerStore = store.quantityAvailable?.toDouble()?.toInt()

            if (quantityPerStore != null && quantityPerStore > 0) {
                quantityInAlternative += quantityPerStore
                alternativeStoresWithStock.add(store)
            }
        }

        // Gray
        stores?.gray?.forEach { store ->
            val quantityPerStore = store.quantityAvailable?.toDouble()?.toInt()

            if (quantityPerStore != null && quantityPerStore > 0) {
                quantityInGray += quantityPerStore
                grayStoresWithStock.add(store)
            }
        }

        // New Items
        stores?.newItems?.forEach { store ->
            val quantityPerStore = store.quantityAvailable?.toDouble()?.toInt()

            if (quantityPerStore != null && quantityPerStore > 0) {
                quantityInNewItems += quantityPerStore
                newItemStoresWithStock.add(store)
            }
        }

        totalQuantityAvailable =
            totalQuantityAvailable
                .plus(quantityInNative)
                .plus(quantityInAlternative)
                .plus(quantityInGray)
                .plus(quantityInNewItems)

        val storesWithStock = Stores()
        storesWithStock.native = nativeStoresWithStock
        storesWithStock.alternative = alternativeStoresWithStock
        storesWithStock.gray = grayStoresWithStock
        storesWithStock.newItems = newItemStoresWithStock

        logger.debug("Native Q available: $quantityInNative")
        logger.debug("Alternative Q available: $quantityInAlternative")
        logger.debug("Gray Q available: $quantityInGray")
        logger.debug("New Items Q available: $quantityInNewItems")

        // determine state of availability

        // Available

        // 1) Available: total stock level is sufficient for the requested quantity, all from native WH

        // 2) Available but delayed: total stock level is sufficient but the stock quantity supplied
        // are from stores with delays (Native + Store X)

        // 3) Partially available: some stock levels can provide a quantity but the totally is not sufficient for the total requested quantity,

        // Unavailable
        // 4) Unavailable: total stock level is not sufficient for the requested quantity (zero available in all stores)

        var remainingQuantity = when {
            totalQuantityAvailable >= quantityRequested -> 0
            else -> quantityRequested - totalQuantityAvailable
        }

        var availabilityState: AvailabilityStateType?

        // Determine if quantity requested, can be totally, partially or completely not supplied
        // First check quantity in:
        //
        // 1) Native
        //
        // then if quantity not sufficient determine if remaining quantity can be supplied by:

        // 2) Alternative
        // 3) Gray
        // 4) New Items warehouses
        when {
            quantityInNative >= quantityRequested -> {
                logger.debug(
                    "Can provide all quantity requested from Native WH: request: " +
                            "$quantityRequested | available: $quantityInNative"
                )
                availabilityState = AvailabilityStateType.AVAILABLE
            }
        }

        // Native
        when {
            quantityInAlternative >= quantityRequested.minus(quantityInNative) -> {
                // Alternative
                logger.debug(
                    "Native WH stock is not sufficient: $quantityInNative, " +
                            "need to provide additional quantities from Alternative WH: request: " +
                            "$quantityRequested | available: $quantityInAlternative"
                )

                availabilityState = AvailabilityStateType.AVAILABLE
            }
        }

        // Gray
        when {
            quantityInGray >= quantityRequested
                .minus(quantityInNative)
                .minus(quantityInAlternative) -> {
                logger.debug(
                    "Alternative WH stock is not sufficient: $quantityInAlternative need to provide additional quantities Gray WH: " +
                            "$quantityRequested | available: $quantityInGray"
                )

                availabilityState = AvailabilityStateType.AVAILABLE
            }
        }

        // New Items
        when {
            quantityInNewItems >= quantityRequested
                .minus(quantityInNative)
                .minus(quantityInAlternative)
                .minus(quantityInGray) -> {
                logger.debug(
                    "Gray WH stock is not sufficient: $quantityInGray need to provide additional quantities New Items WH: " +
                            "$quantityRequested | available: $quantityInNewItems"
                )

                availabilityState = AvailabilityStateType.AVAILABLE
            }

            else -> {
                remainingQuantity = quantityRequested
                    .minus(quantityInNative)
                    .minus(quantityInAlternative)
                    .minus(quantityInGray)
                    .minus(quantityInNewItems)

                availabilityState = if (quantityRequested == remainingQuantity) {
                    logger.debug("Cannot be ordered, all stock levels are: ${quantityRequested.minus(remainingQuantity)}")

                    AvailabilityStateType.NOT_AVAILABLE
                } else {
                    logger.debug("Remaining quantity that can not be ordered: $remainingQuantity")

                    AvailabilityStateType.PARTIALLY_AVAILABLE
                }
            }
        }

        return StockDetermination(
            totalQuantityAvailable,
            quantityRequested,
            quantityRequested.minus(remainingQuantity),
            remainingQuantity,
            storesWithStock,
            availabilityState
        )
    }

    private fun computeTotalCostOfAllItems(productItems: List<ProductAvailabilityItemDto>): Double {
        computeTotalCostsPerItem(productItems)

        var totalCost = 0.0
        productItems.forEach {

            if (it.totalCost != null) {
                totalCost += it.totalCost!!.toDouble()
            }
        }

        return totalCost
    }

    /**
     * Compute total price of available products.
     */
    private fun computeTotalCostsPerItem(productItems: List<ProductAvailabilityItemDto>) {

        // Calculate parent cost
        productItems.forEach { productItem ->

            // Default rentalDays
            var productItemRentalDays = DEFAULT_RENTAL_DAYS_DURATION

            if (productItem.period?.start != null && productItem.period?.end != null
                && Days.daysBetween(productItem.period?.start, productItem.period?.end) != Days.ZERO
            ) {
                productItemRentalDays = Days.daysBetween(productItem.period?.start, productItem.period?.end)
            }

            if (productItem.rates?.first() != null) {
                var totalAccessoriesCost = 0.0

                val parentItemCost =
                    productItemRentalDays.days * (productItem.quantity * productItem.rates!!.first().price?.toDouble()!!)
                productItem.totalCostProducts = "%.2f".format(parentItemCost)

                productItem.accessories.forEach { accessoryItem ->
                    when {
                        accessoryItem.rates?.first() != null -> {

                            val itemCost =
                                productItemRentalDays.days * (accessoryItem.quantity * accessoryItem.rates!!.first().price?.toDouble()!!)
                            accessoryItem.totalCostProducts = "%.2f".format(itemCost)
                            accessoryItem.totalCost = "%.2f".format(itemCost)
                            totalAccessoriesCost += itemCost
                        }
                    }
                }

                productItem.totalCostAccessories = "%.2f".format(totalAccessoriesCost)
                productItem.totalCost = "%.2f".format(parentItemCost + totalAccessoriesCost)
            }
        }
    }

    private fun buildQueryParametersMap(
        productAvailabilityItem: ProductAvailabilityItemDto,
        isAccessory: Boolean = false,
        parentProductItem: ProductAvailabilityItemDto? = null
    ): Map<String, String> {
        val queryParameters = mutableMapOf<String, String>()

        queryParameters["q[product_id_eq]"] = productAvailabilityItem.id.toString()

        if (isAccessory && parentProductItem != null) {
            queryParameters["q[product_accessory_only_eq]"] = "true"

            queryParameters["delivery_location_id"] = parentProductItem.location?.delivery?.id.toString()

            if (parentProductItem.location?.collection?.id != null) {
                queryParameters["collection_location_id"] = parentProductItem.location?.collection?.id.toString()
            }
        } else {
            if (productAvailabilityItem.location != null) {
                queryParameters["delivery_location_id"] = productAvailabilityItem.location?.delivery?.id.toString()

                if (productAvailabilityItem.location?.collection?.id != null) {
                    queryParameters["collection_location_id"] =
                        productAvailabilityItem.location?.collection?.id.toString()
                }
            }
        }

        if (productAvailabilityItem.period?.start != null) {
            val startDateAtNoon = productAvailabilityItem.period?.start!!.withTime(DateTimeUtil.NOON, 0, 0, 0)
            queryParameters["starts_at"] =
                DateTimeUtil.toISO8601UTC(startDateAtNoon, CURRENT_RMS_API_DATE_ISO8601_FORMAT)!!
        }

        if (productAvailabilityItem.period?.end != null) {
            val endDateAtNoon = productAvailabilityItem.period?.end!!.withTime(DateTimeUtil.NOON, 0, 0, 0)
            queryParameters["ends_at"] = DateTimeUtil.toISO8601UTC(endDateAtNoon, CURRENT_RMS_API_DATE_ISO8601_FORMAT)!!
        }

        return queryParameters
    }
}

package com.oceanpremium.api.core.usecase

import com.oceanpremium.api.core.currentrms.response.dto.product.ProductDto
import com.oceanpremium.api.core.enum.AvailabilityStateType
import com.oceanpremium.api.core.exception.throwable.BadRequestException
import com.oceanpremium.api.core.model.ProductAvailabilityItemDto
import com.oceanpremium.api.core.model.ProductAvailabilityResponse
import com.oceanpremium.api.core.model.Stores
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
                false,
                result.stores
            )

            //Check the availability of provided accessories for the given product, adjust the parent product
            //to reflect a state that matched the availability of both the parent product and accessory.
            productAvailabilityItem.accessories.forEach { accessoriesAvailabilityItem ->

                if (accessoriesAvailabilityItem.period == null) {
                    accessoriesAvailabilityItem.period = productAvailabilityItem.period
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
                    true,
                    result.stores
                )

                // Restate the availability of the parent product
                if (productAvailabilityItem.availabilityState == AvailabilityStateType.AVAILABLE) {
                    if (accessoriesAvailabilityItem.availabilityState == AvailabilityStateType.NOT_AVAILABLE) {
                        productAvailabilityItem.availabilityState =
                            AvailabilityStateType.AVAILABLE_BUT_ACCESSORY_NOT_AVAILABLE
                    }

                    if (accessoriesAvailabilityItem.availabilityState == AvailabilityStateType.AVAILABLE_BUT_DELAYED) {
                        productAvailabilityItem.availabilityState = AvailabilityStateType.AVAILABLE_BUT_DELAYED
                    }
                }
            }
        }

        return buildResponseModel(productItems)
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
                    queryParameters["collection_location_id"] = productAvailabilityItem.location?.collection?.id.toString()
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

    private fun updateAvailability(
        productAvailabilityItem: ProductAvailabilityItemDto,
        productDtoItem: ProductDto?,
        isAccessory: Boolean,
        stores: Stores
    ) {
        // Set stores reference to product dto
        mapStoreQuantitiesToStoreDto(productDtoItem, stores)
        productAvailabilityItem.stores = stores

        // Determine quantity available per store type and total and update product item state accordingly
        val totalQuantityAvailable = determineProductAvailability(stores, productAvailabilityItem.quantity)

        // Check that the total product quantity available, compared to the requested quantity for given product is sufficient
        if (totalQuantityAvailable >= productAvailabilityItem.quantity) {
            productAvailabilityItem.availabilityState = AvailabilityStateType.AVAILABLE
            productAvailabilityItem.quantityAvailable = totalQuantityAvailable
        } else {
            productAvailabilityItem.availabilityState = AvailabilityStateType.NOT_AVAILABLE
            productAvailabilityItem.quantityAvailable = totalQuantityAvailable
        }

        productAvailabilityItem.images = productDtoItem?.images
        productAvailabilityItem.name = productDtoItem?.name
        productAvailabilityItem.rates = productDtoItem?.rates

        // If it is an accessory, do not show the parent total, or accessories total, thus only show that on parent node
        if (isAccessory) {
            productAvailabilityItem.totalCostAccessories = null
        }
    }

    private fun mapStoreQuantitiesToStoreDto(productDtoItem: ProductDto?, stores: Stores ) {
        // Map store quantities to stores dto
        stores.all?.forEach { store ->
            val storeQuantityDto = productDtoItem?.allStoreQuantities?.firstOrNull { it.storeId == store.id }

            when {
                storeQuantityDto != null -> store.quantityAvailable = storeQuantityDto.quantityAvailable
            }
        }
    }

    /**
     * Determines for all queried stores that total availability for given product.
     */
    private fun determineProductAvailability(stores: Stores, quantityRequested: Int): Int {

        var quantityInNative = 0
        var quantityInAlternative = 0
        var quantityInGray = 0
        var quantityInNewItems = 0
        var totalQuantityAvailable = 0


        // First check quantity of Native
        stores.native?.forEach { store ->
            val quantityPerStore = store.quantityAvailable?.toDouble()?.toInt()

            if (quantityPerStore != null && quantityPerStore > 0) {
                quantityInNative += quantityPerStore
            }
        }

        // Alternative
        stores.alternative?.forEach { store ->
            val quantityPerStore = store.quantityAvailable?.toDouble()?.toInt()

            if (quantityPerStore != null && quantityPerStore > 0) {
                quantityInAlternative += quantityPerStore
            }
        }

        // Gray
        stores.gray?.forEach { store ->
            val quantityPerStore = store.quantityAvailable?.toDouble()?.toInt()

            if (quantityPerStore != null && quantityPerStore > 0) {
                quantityInGray += quantityPerStore
            }
        }

        // New Items
        stores.newItems?.forEach { store ->
            val quantityPerStore = store.quantityAvailable?.toDouble()?.toInt()

            if (quantityPerStore != null && quantityPerStore > 0) {
                quantityInNewItems += quantityPerStore
            }
        }

        totalQuantityAvailable =
            totalQuantityAvailable
            .plus(quantityInNative)
            .plus(quantityInAlternative)
            .plus(quantityInGray)
            .plus(quantityInNewItems)


        if (totalQuantityAvailable < quantityRequested) {
            logger.debug("Quantity requested: $quantityRequested, exceeds total quantity available, can only provide a quantity of: $totalQuantityAvailable")
        }

        logger.debug("Native Q available: $quantityInNative")
        logger.debug("Alternative Q available: $quantityInAlternative")
        logger.debug("Gray Q available: $quantityInGray")
        logger.debug("New Items Q available: $quantityInNewItems")

        if (quantityInNative >= quantityRequested) {                                     // Native
            logger.debug("Can provide all quantity requested from Native WH: request: $quantityRequested | available: $quantityInNative")
        } else if (quantityInAlternative >= quantityRequested.minus(quantityInNative)) { // Alternative
            logger.debug("Native WH stock is not sufficient: $quantityInNative, need to provide additional quantities from Alternative WH: request: $quantityRequested | available: $quantityInAlternative")
        } else if (quantityInGray >= quantityRequested                                  // Gray
                .minus(quantityInNative)
                .minus(quantityInAlternative)) {
            logger.debug("Alternative WH stock is not sufficient: $quantityInAlternative need to provide additional quantities Gray WH: $quantityRequested | available: $quantityInGray")
        } else if (quantityInNewItems >=  quantityRequested                               //New Items
                .minus(quantityInNative)
                .minus(quantityInAlternative)
                .minus(quantityInGray)) {
            logger.debug("Gray WH stock is not sufficient: $quantityInGray need to provide additional quantities New Items WH: $quantityRequested | available: $quantityInNewItems")
        } else {

            val remainingQuantity =  quantityRequested
                .minus(quantityInNative)
                .minus(quantityInAlternative)
                .minus(quantityInGray)
                .minus(quantityInNewItems)

            logger.debug("Remaining quantity that can not be ordered: $remainingQuantity ")
        }



        return totalQuantityAvailable
    }

    private fun computeTotalCostOfAllItems(productItems: List<ProductAvailabilityItemDto>): Double {
        logger.debug("computePrices")

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

                productItem.accessories.forEach { accessoryItem ->

                    when {
                        accessoryItem.rates?.first() != null -> {
                            if (accessoryItem.availabilityState == AvailabilityStateType.AVAILABLE) {
                                val itemCost =
                                    productItemRentalDays.days * (accessoryItem.quantity * accessoryItem.rates!!.first().price?.toDouble()!!)
                                accessoryItem.totalCostProducts = "%.2f".format(itemCost)
                                totalAccessoriesCost += itemCost
                            }
                        }
                    }
                }

                productItem.totalCostAccessories = "%.2f".format(totalAccessoriesCost)

                val parentItemCost =
                    productItemRentalDays.days * (productItem.quantity * productItem.rates!!.first().price?.toDouble()!!)
                productItem.totalCostProducts = "%.2f".format(parentItemCost)
                productItem.totalCost = "%.2f".format(parentItemCost + totalAccessoriesCost)

            }
        }
    }

    private fun buildResponseModel(productItems: List<ProductAvailabilityItemDto>): ProductAvailabilityResponse {

        val availableProductItems = productItems.filter {
            it.availabilityState == AvailabilityStateType.AVAILABLE
                    || it.availabilityState == AvailabilityStateType.AVAILABLE_BUT_ACCESSORY_NOT_AVAILABLE
        }

        val availableAccessoryItems = productItems.flatMap { it.accessories }.filter {
            it.availabilityState == AvailabilityStateType.AVAILABLE
        }

        val unavailableProductItems = productItems.filter {
            it.availabilityState == AvailabilityStateType.NOT_AVAILABLE
        }

        val unavailableAccessoryItems = productItems.flatMap { it.accessories }.filter {
            it.availabilityState == AvailabilityStateType.NOT_AVAILABLE
        }

        val allUnavailableProducts: MutableList<ProductAvailabilityItemDto> = mutableListOf()
        allUnavailableProducts.addAll(unavailableProductItems)
        allUnavailableProducts.addAll(unavailableAccessoryItems)

        val allAvailableProducts: MutableList<ProductAvailabilityItemDto> = mutableListOf()
        allAvailableProducts.addAll(availableProductItems)
        allAvailableProducts.addAll(availableAccessoryItems)

        val totalCostOfAvailableProducts = computeTotalCostOfAllItems(allAvailableProducts)
        val totalCostOfUnavailableProducts = computeTotalCostOfAllItems(allUnavailableProducts)

        return ProductAvailabilityResponse(
            "%.2f".format(totalCostOfAvailableProducts),
            productItems,
            allAvailableProducts,
            allUnavailableProducts,
            "%.2f".format(totalCostOfUnavailableProducts)
        )
    }
}

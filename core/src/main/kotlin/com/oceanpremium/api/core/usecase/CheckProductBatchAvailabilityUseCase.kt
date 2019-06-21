package com.oceanpremium.api.core.usecase

import com.oceanpremium.api.core.currentrms.response.dto.product.ProductDto
import com.oceanpremium.api.core.enum.AvailabilityStateType
import com.oceanpremium.api.core.exception.throwable.BadRequestException
import com.oceanpremium.api.core.model.ProductAvailabilityItemDto
import com.oceanpremium.api.core.model.ProductAvailabilityResponse
import com.oceanpremium.api.core.util.DateTimeUtil
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders

/**
 * Get the availability for batch POSTED product items and check against the available quantity has sufficient stock levels compared to
 * the wanted quantity. This is can be used by, for example, for checking the availability of a basket where multiple products are added.
 */
interface CheckProductBatchAvailabilityUseCase {
    fun execute(productItems: List<ProductAvailabilityItemDto>): ProductAvailabilityResponse
}

/**
 * @inherit
 */
class CheckProductBatchAvailabilityUseCaseUseCaseImpl(
    @Autowired private val getProductInventoryUseCase: GetProductInventoryUseCase) : CheckProductBatchAvailabilityUseCase {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
        private const val PAYLOAD_EMPTY_ERROR_MESSAGE = "Payload may not contain empty array"
    }

    /**
     * var totalCost: String? = null
     * var totalCostProducts: String? = null
     * var totalCostAccessories: String? = null
     */
    override fun execute(productItems: List<ProductAvailabilityItemDto>): ProductAvailabilityResponse {

        if (productItems.isEmpty()) {
            throw BadRequestException(PAYLOAD_EMPTY_ERROR_MESSAGE)
        }

        productItems.forEach { productAvailabilityItem ->

            if (productAvailabilityItem.quantity == 0) {
                productAvailabilityItem.message = "Quantity supplied for product: ${productAvailabilityItem.id} must be greater then 0."
                productAvailabilityItem.availabilityState = AvailabilityStateType.NOT_AVAILABLE
                productAvailabilityItem.quantityAvailable = productAvailabilityItem.quantity
            }

            logger.debug("check availability for product with id: ${productAvailabilityItem.id} on location collection: " +
                    "${productAvailabilityItem.location?.collection?.id} - dropOff: ${productAvailabilityItem.location?.delivery?.id} " +
                    "in period: ${productAvailabilityItem.period?.start} - ${productAvailabilityItem.period?.end}")

            val result = getProductInventoryUseCase.execute(buildQueryParametersMap(productAvailabilityItem, false), HttpHeaders.EMPTY)

            @Suppress("UNCHECKED_CAST")
            val productDtos = result.dtoMapper.data as List<ProductDto>?
            val productDtoItem = productDtos?.firstOrNull {
                    productResultItem -> productResultItem.id == productAvailabilityItem.id
            }

           updateAvailability(
               productAvailabilityItem,
               productDtoItem,
               productDtoItem?.rates?.first()?.quantityAvailable?.toDouble()?.toInt(),
               false
           )

            //Check the availability of provided accessories for the given product, adjust the parent product
            // to reflect a state that matched the availability of both the parent product and accessory.
            productAvailabilityItem.accessories.forEach { accessoriesAvailabilityItem->
                val accessoriesResult
                        = getProductInventoryUseCase.execute(
                    buildQueryParametersMap(accessoriesAvailabilityItem, true),
                    HttpHeaders.EMPTY
                )

                @Suppress("UNCHECKED_CAST")
                val accessoriesDtos = accessoriesResult.dtoMapper.data as List<ProductDto>?

                val accessoryDtoItem = accessoriesDtos?.firstOrNull {
                        accessoriesResultItem -> accessoriesResultItem.id == accessoriesAvailabilityItem.id
                }

                updateAvailability(
                    accessoriesAvailabilityItem,
                    accessoryDtoItem,
                    accessoryDtoItem?.rates?.first()?.quantityAvailable?.toDouble()?.toInt(),
                    true
                )

                // Restate the availability of the parent product
                if (productAvailabilityItem.availabilityState == AvailabilityStateType.AVAILABLE) {
                    if (accessoriesAvailabilityItem.availabilityState == AvailabilityStateType.NOT_AVAILABLE) {
                        productAvailabilityItem.availabilityState = AvailabilityStateType.AVAILABLE_BUT_ACCESSORY_NOT_AVAILABLE
                    }

                    if (accessoriesAvailabilityItem.availabilityState == AvailabilityStateType.AVAILABLE_BUT_DELAYED) {
                        productAvailabilityItem.availabilityState = AvailabilityStateType.AVAILABLE_BUT_DELAYED
                    }
                }
            }

        }

        val totalCost = computeTotalCostOfAllItems(productItems)

        return ProductAvailabilityResponse(
            "%.2f".format(totalCost),
            productItems
        )
    }

    private fun buildQueryParametersMap(productAvailabilityItem: ProductAvailabilityItemDto,
                                        isAccessory: Boolean = false) : Map<String, String> {
        val queryParameters = mutableMapOf<String, String>()

        if (isAccessory) {
            queryParameters["q[product_accessory_only_eq]"] = "true"
        }

        queryParameters["q[product_id_eq]"] = productAvailabilityItem.id.toString()

        if (productAvailabilityItem.period?.start != null) {
            queryParameters["starts_at"] = DateTimeUtil.toISO8601UTC(productAvailabilityItem.period?.start!!)
        }

        if (productAvailabilityItem.period?.end != null) {
            queryParameters["ends_at"] = DateTimeUtil.toISO8601UTC(productAvailabilityItem.period?.end!!)
        }

        if (productAvailabilityItem.location != null) {
            queryParameters["delivery_location_id"] = productAvailabilityItem.location?.delivery?.id.toString()

            if (productAvailabilityItem.location?.collection?.id != null) {
                queryParameters["collection_location_id"] = productAvailabilityItem.location?.collection?.id.toString()
            }
        }

        return queryParameters
    }

    private fun updateAvailability(
        productAvailabilityItem: ProductAvailabilityItemDto,
        productDtoItem: ProductDto?, quantityAvailable: Int?,
        isAccessory: Boolean
    ) {
        // Check that the stock level quantity for the requested quantity for given product is sufficient
        when {
            quantityAvailable != null -> if (quantityAvailable >= productAvailabilityItem.quantity) {
                productAvailabilityItem.availabilityState = AvailabilityStateType.AVAILABLE
                productAvailabilityItem.quantityAvailable = quantityAvailable
            } else {
                productAvailabilityItem.availabilityState = AvailabilityStateType.NOT_AVAILABLE
                productAvailabilityItem.quantityAvailable = quantityAvailable
            }
            else -> {
                productAvailabilityItem.availabilityState = AvailabilityStateType.AVAILABLE_BUT_DELAYED
                productAvailabilityItem.quantityAvailable = 0
            }
        }

        productAvailabilityItem.images = productDtoItem?.images
        productAvailabilityItem.name = productDtoItem?.name
        productAvailabilityItem.rates = productDtoItem?.rates

        // If it is an accessory, do not show the parent total, or accessories total, thus only show that on parent node
        if (isAccessory) {
            productAvailabilityItem.totalCostAccessories = null
        }
    }

    private fun computeTotalCostOfAllItems(productItems: List<ProductAvailabilityItemDto>) : Double {
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
            var totalProductItemCost = 0.0
            var totalAccessoriesCost = 0.0

            if (productItem.rates?.first() != null) {

                productItem.totalCostProducts = "%.2f".format(totalProductItemCost)
                productItem.accessories.forEach { accessoryItem ->
                    when {
                        accessoryItem.rates?.first() != null -> {
                            if (accessoryItem.availabilityState == AvailabilityStateType.AVAILABLE) {
                                val itemCost =  accessoryItem.quantity * accessoryItem.rates!!.first().price?.toDouble()!!
                                accessoryItem.totalCostProducts = "%.2f".format(itemCost)
                                totalAccessoriesCost += itemCost
                            }
                        }
                    }
                }

                productItem.totalCostAccessories = "%.2f".format(totalAccessoriesCost)

                val parentItemCost =  productItem.quantity * productItem.rates!!.first().price?.toDouble()!!
                productItem.totalCostProducts = "%.2f".format(parentItemCost)
                productItem.totalCost = "%.2f".format(parentItemCost + totalAccessoriesCost)

            }
        }
    }
}

package com.oceanpremium.api.core.usecase

import com.oceanpremium.api.core.currentrms.response.dto.product.ProductDto
import com.oceanpremium.api.core.enum.AvailabilityStateType
import com.oceanpremium.api.core.exception.throwable.BadRequestException
import com.oceanpremium.api.core.model.ProductAvailabilityItemDto
import com.oceanpremium.api.core.util.DateTimeUtil
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders

/**
 * Get the availability for batch POSTED product items and check against the available quantity has sufficient stock levels compared to
 * the wanted quantity. This is can be used by, for example, for checking the availability of a basket where multiple products are added.
 */
interface CheckProductBatchAvailabilityUseCase {
    fun execute(productItems: List<ProductAvailabilityItemDto>): List<ProductAvailabilityItemDto>
}

/**
 * @inherit
 */
class CheckProductBatchAvailabilityUseCaseUseCaseImpl(
    @Autowired private val getProductInventoryUseCase: GetProductInventoryUseCase) : CheckProductBatchAvailabilityUseCase {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    override fun execute(productItems: List<ProductAvailabilityItemDto>): List<ProductAvailabilityItemDto> {

        if (productItems.isEmpty()) {
            throw BadRequestException("Payload may not contain empty array")
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

            val result = getProductInventoryUseCase.execute(buildQueryParametersMap(productAvailabilityItem), HttpHeaders.EMPTY)

            @Suppress("UNCHECKED_CAST")
            val productDtos = result.dtoMapper.data as List<ProductDto>?
            val productDtoItem = productDtos?.firstOrNull {
                    productResultItem -> productResultItem.id == productAvailabilityItem.id
            }

           updateAvailability(productAvailabilityItem, productDtoItem, productDtoItem?.rates?.first()?.quantityAvailable?.toDouble()?.toInt())

            //Check the availability of provided accessories for the given product
            productAvailabilityItem.accessories?.forEach { accessoriesAvailabilityItem->
                val accessoriesResult = getProductInventoryUseCase.execute(buildQueryParametersMap(accessoriesAvailabilityItem, true), HttpHeaders.EMPTY)

                @Suppress("UNCHECKED_CAST")
                val accessoriesDtos = accessoriesResult.dtoMapper.data as List<ProductDto>?

                val accessoryDtoItem = accessoriesDtos?.firstOrNull {
                        accessoriesResultItem -> accessoriesResultItem.id == accessoriesAvailabilityItem.id
                }

                updateAvailability(accessoriesAvailabilityItem, accessoryDtoItem, accessoryDtoItem?.rates?.first()?.quantityAvailable?.toDouble()?.toInt())

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

        return productItems
    }

    private fun buildQueryParametersMap(productAvailabilityItem: ProductAvailabilityItemDto, isAccessory: Boolean = false) : Map<String, String> {
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

    private fun updateAvailability(productAvailabilityItem: ProductAvailabilityItemDto, productDtoItem: ProductDto?, quantityAvailable: Int?) {
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
        productAvailabilityItem.totalPrice = "%.2f".format(productAvailabilityItem.computeTotalPrice())
    }
}

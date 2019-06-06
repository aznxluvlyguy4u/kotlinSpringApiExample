package com.oceanpremium.api.core.usecase

import com.oceanpremium.api.core.currentrms.response.dto.product.ProductDto
import com.oceanpremium.api.core.enum.AvailabilityStateType
import com.oceanpremium.api.core.exception.throwable.BadRequestException
import com.oceanpremium.api.core.model.ProductAvailabilityItem
import com.oceanpremium.api.core.util.DateTimeUtil
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders

/**
 * Get the availability for batch POSTED product items and check against the available quantity has sufficient stock levels compared to
 * the wanted quantity. This is can be used by, for example, for checking the availability of a basket where multiple products are added.
 */
interface CheckProductBatchAvailability {
    fun execute(productItems: List<ProductAvailabilityItem>): List<ProductAvailabilityItem>
}

/**
 * @inherit
 */
class CheckProductBatchAvailabilityUseCaseImpl(
    @Autowired private val getProductInventoryUseCase: GetProductInventoryUseCase) : CheckProductBatchAvailability {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    override fun execute(productItems: List<ProductAvailabilityItem>): List<ProductAvailabilityItem> {

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
                    "${productAvailabilityItem.location?.collectionId} - dropOff: ${productAvailabilityItem.location?.deliveryId} " +
                    "in period: ${productAvailabilityItem.period?.start} - ${productAvailabilityItem.period?.end}")

            val queryParameters = mutableMapOf<String, String>()
            queryParameters["q[product_id_eq]"] = productAvailabilityItem.id.toString()

            if (productAvailabilityItem.period?.start != null) {
                queryParameters["starts_at"] = DateTimeUtil.toISO8601UTC(productAvailabilityItem.period?.start!!)
            }

            if (productAvailabilityItem.period?.end != null) {
                queryParameters["ends_at"] = DateTimeUtil.toISO8601UTC(productAvailabilityItem.period?.end!!)
            }

            val headers: HttpHeaders = HttpHeaders.EMPTY

            if (productAvailabilityItem.location != null) {
                queryParameters["delivery_location_id"] = productAvailabilityItem.location?.deliveryId!!.toString()

                if (productAvailabilityItem.location?.collectionId != null) {
                    queryParameters["collection_location_id"] = productAvailabilityItem.location?.collectionId!!.toString()
                }
            }

            val result = getProductInventoryUseCase.execute(queryParameters, headers)

            @Suppress("UNCHECKED_CAST")
            val productDtos = result.dtoMapper?.data as List<ProductDto>?
            val productDtoItem = productDtos?.firstOrNull {
                    productResultItem -> productResultItem.id == productAvailabilityItem.id
            }

            val quantityAvailable = productDtoItem?.rates?.first()?.quantityAvailable?.toDouble()?.toInt()

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
                    productAvailabilityItem.availabilityState = AvailabilityStateType.DELAYED
                    productAvailabilityItem.quantityAvailable = 0
                }
            }
        }

        return productItems
    }
}

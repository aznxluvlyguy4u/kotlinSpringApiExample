package com.oceanpremium.api.core.usecase

import com.oceanpremium.api.core.enum.AvailabilityStateType
import com.oceanpremium.api.core.model.OrderDto
import com.oceanpremium.api.core.model.ProductAvailabilityItemDto
import org.springframework.beans.factory.annotation.Autowired

/**
 * Checks the availability of provided products and accessories. Send out email with order.
 */
interface OrderPlacementUseCase {
    fun execute(order: OrderDto): OrderDto
}

class OrderPlacementUseCaseImpl (
    @Autowired private val checkProductBatchAvailabilityUseCase: CheckProductBatchAvailabilityUseCase,
    @Autowired private val sendEmailUseCase: SendEmailUseCase) : OrderPlacementUseCase {

    internal class Prices(val subTotalPrice: Double, val subTotalUnavailableProducts: Double)

    override fun execute(order: OrderDto): OrderDto {
        val productsAvailabilityResponse= checkProductBatchAvailabilityUseCase.execute(order.products)
        val unavailableProducts = productsAvailabilityResponse.products.filter {
            it.availabilityState == AvailabilityStateType.NOT_AVAILABLE
        }
        val availableProducts = productsAvailabilityResponse.products.minus(unavailableProducts)
        val pricesOfAvailableProducts = determineTotalPricesOfParentProductAndChildAccessories(availableProducts, unavailableProducts)
        val pricesOfUnavailableProducts = determineTotalPricesOfParentProductAndChildAccessories(unavailableProducts)

        order.products = availableProducts
        order.unavailableProducts = unavailableProducts
        order.totalPrice = "%.2f".format(pricesOfAvailableProducts.subTotalPrice)
        order.totalPriceUnavailableProducts = "%.2f".format(pricesOfUnavailableProducts.subTotalPrice)

        sendEmailUseCase.execute(order)

        return order
    }

    private fun determineTotalPricesOfParentProductAndChildAccessories(
        availableProducts: List<ProductAvailabilityItemDto>,
        unavailableProducts: List<ProductAvailabilityItemDto>? = null
    ): Prices {
        var subTotal = 0.0
        var subTotalUnavailableProducts = 0.0

        availableProducts.forEach { productItem ->
            val totalPriceUnitPriceProduct = productItem.totalPriceProducts!!.toDouble()
            subTotalUnavailableProducts += totalPriceUnitPriceProduct

            val unavailableAccessories = productItem.accessories.filter {
                it.availabilityState == AvailabilityStateType.NOT_AVAILABLE
            }

            if (unavailableProducts != null) {
                productItem.accessories = productItem.accessories.minus(unavailableProducts)
                productItem.unavailableAccessories = unavailableAccessories
            }

            productItem.accessories.forEach { accessoryItem ->
                val totalPricePerUnitAccessory = accessoryItem.totalPriceProducts!!.toDouble()
                subTotalUnavailableProducts += totalPricePerUnitAccessory
            }
        }

        return Prices(subTotal, subTotalUnavailableProducts)
    }
}

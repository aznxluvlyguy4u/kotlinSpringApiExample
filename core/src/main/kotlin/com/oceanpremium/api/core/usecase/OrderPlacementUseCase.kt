package com.oceanpremium.api.core.usecase

import com.oceanpremium.api.core.model.OrderDto
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


    override fun execute(order: OrderDto): OrderDto {
        order.products = checkProductBatchAvailabilityUseCase.execute(order.products).products
        var subTotal = 0.0

        order.products.forEach { productItem ->
            val totalPriceUnitPriceProduct = productItem.totalPriceProducts!!.toDouble()
            subTotal += totalPriceUnitPriceProduct

            productItem.accessories.forEach { accessoryItem ->
                val totalPricePerUnitAccessory = accessoryItem.totalPriceProducts!!.toDouble()
                subTotal += totalPricePerUnitAccessory
            }
        }

        order.totalPrice = "%.2f".format(subTotal)

        sendEmailUseCase.execute(order)

        return order
    }
}

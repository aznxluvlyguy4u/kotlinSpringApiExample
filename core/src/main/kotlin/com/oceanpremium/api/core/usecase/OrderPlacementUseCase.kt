package com.oceanpremium.api.core.usecase

import com.oceanpremium.api.core.model.OrderDto
import org.springframework.beans.factory.annotation.Autowired

interface OrderPlacementUseCase {
    fun execute(order: OrderDto): OrderDto
}

class OrderPlacementUseCaseImpl (
    @Autowired private val checkProductBatchAvailabilityUseCase: CheckProductBatchAvailabilityUseCase,
    @Autowired private val sendEmailUseCase: SendEmailUseCase) : OrderPlacementUseCase {

    override fun execute(order: OrderDto): OrderDto {
        order.products = checkProductBatchAvailabilityUseCase.execute(order.products)
        var subTotal = 0.0

        order.products.forEach { productItem ->
            val totalPriceUnitPriceProduct = productItem.totalPrice!!.toDouble()
            subTotal += totalPriceUnitPriceProduct

            productItem.accessories?.forEach { accessoryItem ->
                val totalPricePerUnitAccessory = accessoryItem.totalPrice!!.toDouble()
                subTotal += totalPricePerUnitAccessory
            }
        }

        order.totalPrice = "%.2f".format(subTotal)

        sendEmailUseCase.execute(order)

        return order
    }
}

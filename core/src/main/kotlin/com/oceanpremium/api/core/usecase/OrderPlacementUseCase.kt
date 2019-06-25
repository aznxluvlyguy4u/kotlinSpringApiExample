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
        val productsAvailabilityResponse=
            checkProductBatchAvailabilityUseCase.execute(order.products)

        order.totalCost = productsAvailabilityResponse.totalPrice
        order.products = productsAvailabilityResponse.products
        order.availableProducts = productsAvailabilityResponse.availableProducts
        order.unavailableProducts = productsAvailabilityResponse.unavailableProducts
        order.totalCostUnavailableProducts = productsAvailabilityResponse.totalPriceUnavailableProducts

        sendEmailUseCase.execute(order, false)

        return order
    }
}

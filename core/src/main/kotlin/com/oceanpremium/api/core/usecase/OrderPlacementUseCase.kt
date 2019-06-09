package com.oceanpremium.api.core.usecase

import com.oceanpremium.api.core.model.Order

interface OrderPlacementUseCase {
    fun execute(order: Order): Order
}

class OrderPlacementUseCaseImpl : OrderPlacementUseCase {
    override fun execute(order: Order): Order {
        return order
    }
}

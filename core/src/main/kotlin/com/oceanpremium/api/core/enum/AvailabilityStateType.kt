package com.oceanpremium.api.core.enum

enum class AvailabilityStateType(val state: String) {
    // Product is available for the requested quantity and location
    AVAILABLE("AVAILABLE"),
    // Product is not available for the requested quantity, meaning there is no stock level for it
    NOT_AVAILABLE("NOT AVAILABLE"),
    // Product is partially available for the requested quantity,
    // meaning there is a stock level but it is not suffient for the requested quantity
    PARTIALLY_AVAILABLE("PARTIALLY AVAILABLE"),
}

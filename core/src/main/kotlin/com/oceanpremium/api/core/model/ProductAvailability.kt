package com.oceanpremium.api.core.model

class ProductAvailabilityItem(
    val id: Int,
    val quantity: Int,
    val period: RentalPeriod? = null,
    val location: RentalLocation? = null,
    val configurations: Map<*,*>? = null,
    val accessories: List<ProductAvailabilityItem>? = null
)

class ProductAvailability {
    var products: MutableList<ProductAvailabilityItem> = mutableListOf()
}

package com.oceanpremium.api.core.model

class ProductAvailabilityItem(
    val id: Int,
    val quantity: Int,
    var period: RentalPeriod? = null,
    var location: RentalLocation? = null,
    var configurations: Map<*,*>? = null,
    var accessories: List<ProductAvailabilityItem>? = null
) {
    var quantityAvailable: Int = 0
}

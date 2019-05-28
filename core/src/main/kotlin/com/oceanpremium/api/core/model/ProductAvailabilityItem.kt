package com.oceanpremium.api.core.model

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class ProductAvailabilityItem(
    val id: Int,
    val quantity: Int,
    var period: RentalPeriod? = null,
    var location: RentalLocation? = null,
    var configuration: Map<*,*>? = null,
    var accessories: List<ProductAvailabilityItem>? = null
) {
    var quantityAvailable: Int? = null
    var available: Boolean = false
}

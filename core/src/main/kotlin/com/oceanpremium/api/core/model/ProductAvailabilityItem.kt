package com.oceanpremium.api.core.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.oceanpremium.api.core.enum.AvailabilityStateType

@JsonInclude(JsonInclude.Include.NON_NULL)
class ProductAvailabilityItem(
    val id: Int,
    val quantity: Int,
    var period: RentalPeriod? = null,
    var location: RentalLocation? = null,
    var configurations: List<Map<*,*>>? = null,
    var accessories: List<ProductAvailabilityItem>? = null
) {
    var quantityAvailable: Int = 0
    var available: Boolean = false
    var availabilityState: AvailabilityStateType? = null
    var message: String? = null
}

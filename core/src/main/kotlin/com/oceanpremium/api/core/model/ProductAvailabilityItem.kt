package com.oceanpremium.api.core.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.oceanpremium.api.core.currentrms.response.dto.product.ImageSource
import com.oceanpremium.api.core.currentrms.response.dto.product.PricingDto
import com.oceanpremium.api.core.enum.AvailabilityStateType
import java.util.*

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
    var availabilityState: AvailabilityStateType? = null
    var message: String? = null
    var name: String? = null
    var rates: List<PricingDto>? = null
    var images: List<ImageSource>? = null
    var totalPrice: String? = null
    val uuid: UUID = UUID.randomUUID()

    fun computeTotalPrice(): Double {
        var totalPrice = 0.0

        if (rates?.first()?.price != null) {
            totalPrice = quantity * rates?.first()?.price?.toDouble()!!
        }

        return totalPrice
    }
}

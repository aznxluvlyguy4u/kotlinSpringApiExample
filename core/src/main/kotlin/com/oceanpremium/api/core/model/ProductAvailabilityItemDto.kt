package com.oceanpremium.api.core.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.oceanpremium.api.core.currentrms.response.dto.product.ImageSource
import com.oceanpremium.api.core.currentrms.response.dto.product.PricingDto
import com.oceanpremium.api.core.enum.AvailabilityStateType
import java.util.*

@JsonPropertyOrder(
    "id",
    "uuid",
    "name",
    "quantity",
    "rates",
    "quantityAvailable",
    "availabilityState",
    "totalPrice",
    "totalCostProducts",
    "totalCostAccessories",
    "period",
    "location"
)
@JsonInclude(JsonInclude.Include.NON_NULL)
class ProductAvailabilityItemDto(
    val id: Int,
    val quantity: Int,
    var period: RentalPeriod? = null,
    var location: RentalLocation? = null,
    var configurations: List<Map<*, *>>? = null,
    var accessories: List<ProductAvailabilityItemDto> = listOf()
) {
    var quantityAvailable: Int = 0
    var availabilityState: AvailabilityStateType? = null
    var message: String? = null
    var name: String? = null
    var rates: List<PricingDto>? = null
    var images: List<ImageSource>? = null
    @JsonProperty("totalPrice")
    var totalCost: String? = null
    var totalCostProducts: String? = null
    var totalCostAccessories: String? = null
    val uuid: UUID = UUID.randomUUID()
    var stores: Stores? = null
    var stockDetermination: StockDetermination? = null
}

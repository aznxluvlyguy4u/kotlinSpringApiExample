package com.oceanpremium.api.core.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonInclude(JsonInclude.Include.NON_NULL)

@JsonPropertyOrder(
    "yachtName",
    "contactDetails",
    "message",
    "products",
    "totalCost",
    "unavailableProducts",
    "totalCostUnavailableProducts"
)
class OrderDto(
    val contactDetails: ContactDetailsDto,
    var products: List<ProductAvailabilityItemDto>,
    val message: String? = null,
    var totalCost: String? = null,
    var yachtName: String? = null
) {
    var unavailableProducts: List<ProductAvailabilityItemDto> = listOf()
    var totalCostUnavailableProducts: String? = null
}

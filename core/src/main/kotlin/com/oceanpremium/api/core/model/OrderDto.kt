package com.oceanpremium.api.core.model

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)

class OrderDto(
    val contactDetails: ContactDetailsDto,
    var products: List<ProductAvailabilityItemDto>,
    val message: String? = null,
    var totalPrice: String? = null,
    var yachtName: String? = null
) {
    var totalPriceUnavailableProducts: String? = null
    var unavailableProducts: List<ProductAvailabilityItemDto> = listOf()
}

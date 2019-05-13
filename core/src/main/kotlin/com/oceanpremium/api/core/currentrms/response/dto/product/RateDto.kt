package com.oceanpremium.api.core.currentrms.response.dto.product

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class PricingDto(
    var quantityAvailable: String? = null,
    val price: String?,
    val chargePeriod: String?
)

@JsonInclude(JsonInclude.Include.NON_NULL)
class RateDto(val pricings: List<PricingDto>)
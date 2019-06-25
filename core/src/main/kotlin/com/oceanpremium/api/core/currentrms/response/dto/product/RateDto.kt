package com.oceanpremium.api.core.currentrms.response.dto.product

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class PricingDto(
    var quantityAvailable: String? = null,
    var price: String?,
    val chargePeriod: String?
) {
    init {
        try {
            if (price != null && price!!.isNotEmpty()) {
                price = "%.2f".format(price!!.toDouble())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

@JsonInclude(JsonInclude.Include.NON_NULL)
class RateDto(val pricings: List<PricingDto>)

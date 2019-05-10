package com.oceanpremium.api.core.currentrms.response.dto.product

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
class ProductDto(
    val id: Double?,
    val name: String?,
    val description: String?,

    @JsonProperty("rental_quantity_available")
    val rentalQuantityAvailable: String?,

    @JsonProperty("rental_price")
    val rentalPrice: String?,

    @JsonProperty("rental_lead_charge_period_name")
    val rentalLeadChargePeriodName: String?,

    @JsonProperty("custom_fields")
    var customFields: ProductCustomFieldsDto? = null
)

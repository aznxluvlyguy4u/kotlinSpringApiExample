package com.oceanpremium.api.core.currentrms.response.dto.product

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class ProductDto(
    val id: Double?,
    val name: String?,
    val description: String?,
    val rental_quantity_available: String?,
    val rental_price: String?,
    val rental_lead_charge_period_name: String?,
    var custom_fields: ProductCustomFieldsDto? = null
)

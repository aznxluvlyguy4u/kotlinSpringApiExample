package com.oceanpremium.api.core.currentrms.response.dto.product

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class ProductDto(
    val id: Double?,
    val name: String?,
    val description: String?,
    val rentalQuantityAvailable: String?,
    val rentalPrice: String?,
    val rentalLeadChargePeriodName: String?,
    var customFields: ProductCustomFieldsDto? = null
)

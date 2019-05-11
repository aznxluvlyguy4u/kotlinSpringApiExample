package com.oceanpremium.api.core.currentrms.response.dto.product

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class ProductGroupDto (
    val id: Double?,
    val name: String?,
    val description: String?,
    var customFields: ProductGroupCustomFieldsDto? = null
)
package com.oceanpremium.api.core.currentrms.response.dto.product

class ProductGroupDto (
    val id: Double?,
    val name: String?,
    val description: String?,
    var custom_fields: ProductGroupCustomFieldsDto? = null
)
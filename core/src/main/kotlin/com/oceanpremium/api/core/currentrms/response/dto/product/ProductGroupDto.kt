package com.oceanpremium.api.core.currentrms.response.dto.product

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
class ProductGroupDto (
    val id: Double?,
    val name: String?,
    val description: String?,

    @JsonProperty("custom_fields")
    var customFields: ProductGroupCustomFieldsDto? = null
)
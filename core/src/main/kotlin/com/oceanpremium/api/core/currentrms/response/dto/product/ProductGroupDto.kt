package com.oceanpremium.api.core.currentrms.response.dto.product

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class ProductGroupDto (
    var id: Int? = null,
    var name: String? = null,
    var description: String? = null,
    var customFields: ProductGroupCustomFieldsDto? = null
)
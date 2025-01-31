package com.oceanpremium.api.core.currentrms.response.dto.product

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class ProductGroupCustomFieldsDto(
    var publicIconUrl: String? = null,
    var publicIconThumbUrl: String? = null
)
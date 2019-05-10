package com.oceanpremium.api.core.currentrms.response.dto.product

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class ProductGroupCustomFieldsDto(
    var public_icon_url: String? = null,
    var public_icon_thumb_url: String? = null
)
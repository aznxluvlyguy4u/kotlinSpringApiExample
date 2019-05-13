package com.oceanpremium.api.core.currentrms.response.dto.product

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class ProductCustomFieldsDto(
    var storeId: Int? = null,
    var publicIconUrl: String? = null,
    var publicIconThumbUrl: String? = null
)
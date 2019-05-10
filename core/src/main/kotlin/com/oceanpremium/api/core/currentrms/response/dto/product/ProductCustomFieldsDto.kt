package com.oceanpremium.api.core.currentrms.response.dto.product

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
class ProductCustomFieldsDto(
    @JsonProperty("store_id")
    var storeId: String? = null,

    @JsonProperty("public_icon_url")
    var publicIconUrl: String? = null,

    @JsonProperty("public_icon_thumb_url")
    var publicIconThumbUrl: String? = null
)
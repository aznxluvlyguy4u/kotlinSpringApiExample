package com.oceanpremium.api.core.currentrms.response.dto.product

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class StoreQuantityDto(
    var storeId: String? = null,
    var quantityAvailable: String? = null
)

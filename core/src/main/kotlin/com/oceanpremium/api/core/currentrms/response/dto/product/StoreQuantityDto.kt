package com.oceanpremium.api.core.currentrms.response.dto.product

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class StoreQuantityDto(
    var storeId: Int? = null,
    var rentalQuantityAvailable: String? = null
)

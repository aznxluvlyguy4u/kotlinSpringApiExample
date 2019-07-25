package com.oceanpremium.api.core.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.oceanpremium.api.core.enum.AvailabilityStateType

@JsonInclude(JsonInclude.Include.NON_NULL)
class StockDetermination(
    val quantityTotallyAvailable: Int,
    val quantityRequested: Int,
    val quantitySufficient: Int,
    val quantityDeficient: Int,
    val stores: Stores? = null,
    val availabilityState: AvailabilityStateType
)

@JsonInclude(JsonInclude.Include.NON_NULL)
class Stores(
    var native: List<Store>? = null,
    var alternative: List<Store>? = null,
    var gray: List<Store>? = null,
    var newItems: List<Store>? = null,
    @JsonIgnore
    var all: List<Store>? = null
)

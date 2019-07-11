package com.oceanpremium.api.core.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.oceanpremium.api.core.enum.WarehouseStoreType
import com.oceanpremium.api.core.util.DateTimeUtil

@JsonInclude(JsonInclude.Include.NON_NULL)
class Store(
    var id: Int = 0,
    val name: String,
    @JsonIgnore
    var minimumDeliveryHours: Int? = 0,
    var deliveryCost: Double? = 0.0,
    var quantityAvailable: String? = "0.0"
) {
    var deliveryPeriod = DateTimeUtil.toDurationISO8601HoursDuration(minimumDeliveryHours!!).toString()
    var type: WarehouseStoreType = WarehouseStoreType.NONE
}

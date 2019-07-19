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
    var deliveryPeriod = determineDeliveryPeriod()
    var type: WarehouseStoreType = WarehouseStoreType.NONE

    private fun determineDeliveryPeriod(): String? {
        if (minimumDeliveryHours == null) {
            return null
        }

        return DateTimeUtil.toISO8601DurationPeriod(minimumDeliveryHours!!).toString()
    }

    override fun toString(): String {
        return "Store: <id: $id, type: $type, name: $name>"
    }
}

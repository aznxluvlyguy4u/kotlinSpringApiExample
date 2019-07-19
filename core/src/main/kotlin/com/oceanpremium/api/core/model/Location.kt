package com.oceanpremium.api.core.model

import java.util.*

/***
 * The Location that is selected from the frontend / client side.
 *
 */
class Location(var name: String? = null, var id: Int = 0,  var regionId: Int = 0, var storeIds: SortedSet<Int> = sortedSetOf()) {
    override fun toString(): String {
        return "Location: <id: $id, name: $name, regionId:$regionId>"
    }
}

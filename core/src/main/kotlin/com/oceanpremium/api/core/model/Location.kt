package com.oceanpremium.api.core.model

import java.util.*

class Location(var name: String? = null, var id: Int = 0,  var regionId: Int = 0, var storeIds: SortedSet<Int> = sortedSetOf())

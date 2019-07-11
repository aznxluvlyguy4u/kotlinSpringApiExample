package com.oceanpremium.api.core.model

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class Stores(
    var native: List<Store>? = null,
    var alternative: List<Store>? = null,
    var gray: List<Store>? = null,
    var newItems: List<Store>? = null,
    var all: List<Store>? = null
)

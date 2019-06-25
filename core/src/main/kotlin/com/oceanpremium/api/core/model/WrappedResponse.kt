package com.oceanpremium.api.core.model

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class WrappedResponse(
    val code: Int,
    var data: Any? = null,
    var meta: Any? = null
)

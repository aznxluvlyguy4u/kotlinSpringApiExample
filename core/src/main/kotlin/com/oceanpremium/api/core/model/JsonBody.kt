package com.oceanpremium.api.core.model

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class JsonBody(
    var code: Int? = null,
    var data: Any? = null,
    var status: String? = null
)
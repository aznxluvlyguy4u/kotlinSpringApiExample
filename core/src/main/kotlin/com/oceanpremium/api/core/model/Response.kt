package com.oceanpremium.api.core.model

import com.fasterxml.jackson.annotation.JsonProperty

class Response(
    @field:JsonProperty("success")
    val success: Boolean, @field:JsonProperty("message")
    val message: Any?
)

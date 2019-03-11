package com.oceanpremium.api.model

import com.fasterxml.jackson.annotation.JsonProperty

class Response(
    @field:JsonProperty("success")
    private val success: Boolean, @field:JsonProperty("message")
    private val message: Any?
)

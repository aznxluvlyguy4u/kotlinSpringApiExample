package com.oceanpremium.api.entity

import com.fasterxml.jackson.annotation.JsonProperty

class Token(
    @field:JsonProperty("accessToken")
    var accessToken: String?,
    @field:JsonProperty("refreshToken")
    var refreshToken: String?,
    @field:JsonProperty("expires")
    var expires: Long?,
    @field:JsonProperty("emailAddress")
    var emailAddress: String?
)
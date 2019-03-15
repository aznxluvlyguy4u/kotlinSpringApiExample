package com.oceanpremium.api.entity

import com.fasterxml.jackson.annotation.JsonProperty

class Token(
    @field:JsonProperty("accessToken")
    val accessToken: String,
    @field:JsonProperty("refreshToken")
    val refresToken: String,
    @field:JsonProperty("expires")
    val expires: Long,
    @field:JsonProperty("emailAddress")
    val emailAddress: String
)
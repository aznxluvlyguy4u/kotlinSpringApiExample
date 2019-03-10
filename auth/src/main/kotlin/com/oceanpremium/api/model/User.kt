package com.oceanpremium.api.model

import com.fasterxml.jackson.annotation.JsonProperty

class User(
    @field:JsonProperty("name")
    var name: String?,
    @field:JsonProperty("emailAddress")
    var emailAddress: String?
)
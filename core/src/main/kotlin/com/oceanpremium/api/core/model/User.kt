package com.oceanpremium.api.core.model

import com.fasterxml.jackson.annotation.JsonProperty

class User(
    @field:JsonProperty("name")
    var name: String?,
    @field:JsonProperty("emailAddress")
    var emailAddress: String?
) {
    override fun toString(): String {
        return "name: $name, emailAddress: $emailAddress"
    }
}
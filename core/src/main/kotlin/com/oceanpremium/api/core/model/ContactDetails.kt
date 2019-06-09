package com.oceanpremium.api.core.model

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class ContactDetails(
    val firstName: String,
    val surName: String,
    val emailAddress: String,
    val country: String,
    val city: String
)

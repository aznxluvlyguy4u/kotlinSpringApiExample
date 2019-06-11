package com.oceanpremium.api.core.model

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class ContactDetailsDto(
    val firstName: String,
    val surName: String,
    val emailAddress: String,
    val phoneNumber: String,
    val country: String,
    val city: String
) {
    var fullName: String? = null

    init {
        fullName = "$firstName $surName"
    }
}

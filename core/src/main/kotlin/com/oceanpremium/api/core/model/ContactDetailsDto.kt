package com.oceanpremium.api.core.model

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class ContactDetails(
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

    override fun toString(): String {
        return "firstName: $firstName, surName: $surName, fullName: $fullName, emailAddress: $emailAddress, phoneNumber: $phoneNumber, country: $country, city: $city"
    }
}

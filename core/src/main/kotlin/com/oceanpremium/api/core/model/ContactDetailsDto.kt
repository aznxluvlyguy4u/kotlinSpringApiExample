package com.oceanpremium.api.core.model

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class AddressDto(
    var streetName: String? = null,
    var streetNumber: String? = null,
    var postalCode: String? = null,
    var city: String? = null,
    var country: String? = null) {
    var streetNumberBlock: String? = null
}

@JsonInclude(JsonInclude.Include.NON_NULL)
class ContactDetailsDto(
    val firstName: String,
    val surName: String,
    val emailAddress: String,
    val phoneNumber: String,
    var address: AddressDto = AddressDto()
) {
    var fullName: String? = null

    init {
        fullName = "$firstName $surName"
    }
}

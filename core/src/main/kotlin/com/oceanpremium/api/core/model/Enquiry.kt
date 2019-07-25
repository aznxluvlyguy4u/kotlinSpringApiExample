package com.oceanpremium.api.core.model

import com.fasterxml.jackson.annotation.JsonIgnore

class Office(
    val id: Int,
    var name: String = "-",
    @JsonIgnore
    val emailAddress: String? = null
)

class Enquiry(
    val name: String,
    val emailAddress: String,
    val phoneNumber: String,
    val message: String,
    val office: Office
)

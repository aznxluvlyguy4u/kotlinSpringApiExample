package com.oceanpremium.api.core.model

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class Order(val contactDetails: ContactDetails, val products: List<ProductAvailabilityItem>, val message: String? = null)

package com.oceanpremium.api.core.model

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class Order(val contactDetails: ContactDetails, var products: List<ProductAvailabilityItem>, val message: String? = null) {

    var totalPrice: String? = null

    init {
        var subTotal = 0.0

        products.forEach {
            if (it.rates != null && it.rates!!.isNotEmpty() && it.rates!!.first().price != null) {
                subTotal += it.quantity * it.rates!!.first().price!!.toDouble()
            }
        }

        totalPrice = "%.2f".format(subTotal)
    }

    override fun toString(): String {
        return "contactDetails: $contactDetails, message: $message, products: $products"
    }
}

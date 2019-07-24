package com.oceanpremium.api.core.model

import com.fasterxml.jackson.annotation.JsonIgnore

class ProductAvailabilityResponse(val totalPrice: String,
                                  val products: List<ProductAvailabilityItemDto>,
                                  @JsonIgnore
                                  val availableProducts: List<ProductAvailabilityItemDto>,
                                  @JsonIgnore
                                  val unavailableProducts: List<ProductAvailabilityItemDto>,
                                  @JsonIgnore
                                  val totalPriceUnavailableProducts: String)

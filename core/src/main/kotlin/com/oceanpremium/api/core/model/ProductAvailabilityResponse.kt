package com.oceanpremium.api.core.model

class ProductAvailabilityResponse(val totalPrice: String,
                                  val products: List<ProductAvailabilityItemDto>,
                                  val availableProducts: List<ProductAvailabilityItemDto>,
                                  val unavailableProducts: List<ProductAvailabilityItemDto>,
                                  val totalPriceUnavailableProducts: String)

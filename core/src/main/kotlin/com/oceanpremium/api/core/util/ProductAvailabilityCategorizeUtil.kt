package com.oceanpremium.api.core.util

import com.oceanpremium.api.core.enum.AvailabilityStateType
import com.oceanpremium.api.core.model.ProductAvailabilityItemDto

object ProductAvailabilityCategorizeUtil {

    fun getProductsOfAvailabilityType(productItems: List<ProductAvailabilityItemDto>, availabilityType: AvailabilityStateType): List<ProductAvailabilityItemDto> {

        if (availabilityType == AvailabilityStateType.AVAILABLE) {
            return productItems.filter {
                it.availabilityState == AvailabilityStateType.AVAILABLE
                        || it.availabilityState == AvailabilityStateType.AVAILABLE_BUT_ACCESSORY_NOT_AVAILABLE
                        || it.availabilityState == AvailabilityStateType.AVAILABLE_BUT_DELAYED
            }
        }

        if (availabilityType == AvailabilityStateType.NOT_AVAILABLE) {
            return productItems.filter { item ->
                item.availabilityState == AvailabilityStateType.NOT_AVAILABLE
            }
        }

        return emptyList()
    }

}

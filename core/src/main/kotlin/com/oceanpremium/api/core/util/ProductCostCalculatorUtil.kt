//package com.oceanpremium.api.core.util
//
//import com.oceanpremium.api.core.enum.AvailabilityStateType
//import com.oceanpremium.api.core.model.ProductAvailabilityItemDto
//
//object ProductCostCalculatorUtil {
//
//
//
//    /**
//     * Compute the cost of only unavailable products and accessories.
//     */
//     fun determineCostOfUnavailableProducts(productItems: List<ProductAvailabilityItemDto>): Double  {
//        var unavailableProductsTotals = 0.0
//        var unavailableAccessoriesTotals = 0.0
//
//        // Available - products
//        val unavailableProducts = productItems.filter {
//            it.availabilityState == AvailabilityStateType.NOT_AVAILABLE
//        }
//
//        computeTotalCost(unavailableProducts)
//
//        unavailableProducts.forEach {
//            unavailableProductsTotals += it.totalCostProducts?.toDouble()!!
//        }
//
//        // Available - accessories
//        val unavailableAccessories = productItems.filter { item ->
//            item.accessories.any { it.availabilityState == AvailabilityStateType.NOT_AVAILABLE }
//        }
//
//        computeTotalCost(unavailableAccessories)
//
//        unavailableAccessories.forEach {
//            unavailableAccessoriesTotals += it.totalCostAccessories?.toDouble()!!
//        }
//
//        return unavailableProductsTotals + unavailableAccessoriesTotals
//    }
//
//    /**
//     *  Compute the cost of each item separately.
//     */
//     fun computeTotalCost(productItems: List<ProductAvailabilityItemDto>) {
//        var totalProductsCost = 0.0
//
//        productItems.forEach { productItem ->
//            if (productItem.rates?.first()?.price != null) {
//                totalProductsCost += productItem.quantity * productItem.rates?.first()?.price!!.toDouble()
//            }
//
//            var totalAccessoriesCost = 0.0
//            productItem.accessories.forEach { accessoryItem ->
//
//                if (accessoryItem.rates?.first()?.price != null) {
//                    totalAccessoriesCost +=  accessoryItem.quantity * accessoryItem.rates?.first()?.price!!.toDouble()
//
//                }
//                accessoryItem.totalCost = "%.2f".format(totalAccessoriesCost)
//            }
//
//            productItem.totalCost = "%.2f".format(totalProductsCost + totalAccessoriesCost)
//            productItem.totalCostProducts = "%.2f".format(totalProductsCost)
//            productItem.totalCostAccessories = "%.2f".format(totalAccessoriesCost)
//            totalProductsCost = 0.0
//            totalAccessoriesCost = 0.0
//        }
//    }
//}

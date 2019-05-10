package com.oceanpremium.api.core.currentrms.response.models

import com.fasterxml.jackson.annotation.JsonInclude
import retrofit2.Response

@JsonInclude(JsonInclude.Include.NON_NULL)
class Meta(response: Response<Any>?) {
    var total_row_count: Int = 0
    var row_count: Int = 0
    var page: Int = 0
    var per_page: Int = 0

    init {
        mapToDto(response)
    }

    private fun mapToDto(response: Response<Any>?) {
        val responseBody = response?.body() as Map<String, Any>

        when {
            responseBody.containsKey("meta") -> {
                val metaBody = responseBody["meta"] as Map<String, Any>
                total_row_count = (metaBody["total_row_count"] as Double).toInt()
                row_count = (metaBody["row_count"] as Double).toInt()
                page = (metaBody["page"] as Double).toInt()
                per_page = (metaBody["per_page"] as Double).toInt()
            }
        }
    }
}

@JsonInclude(JsonInclude.Include.NON_NULL)
class ProductCustomFields(
    var store_id: String? = null,
    var public_icon_url: String? = null,
    var public_icon_thumb_url: String? = null
)

@JsonInclude(JsonInclude.Include.NON_NULL)
class ProductsSearchDto(response: Response<Any>?) {

    var data: Any? = null
    var meta: Any? = null

    init {
        mapToDto(response)
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    internal class Product(
        val id: Double,
        val name: String,
        val description: String,
        val rental_quantity_available: String,
        val rental_price: String,
        val rental_lead_charge_period_name: String,
        var custom_fields: ProductCustomFields? = null
    )

    fun mapToDto(response: Response<Any>?) {
        val products: MutableList<Product> = mutableListOf()
        val responseBody = response?.body() as Map<String, Any>

        when {
            responseBody.containsKey("products") -> {
                meta = Meta(response)

                val productsBody = responseBody["products"] as List<Map<String, Any>>

                productsBody.forEach {
                    val id = it["id"] as Double
                    val name = it["name"] as String
                    val description = it["description"] as String
                    val rentalPrice = it["rental_price"] as String
                    val rentalQuantityAvailable = it["rental_quantity_available"] as String
                    val rentalLeadChargePeriodName = it["rental_lead_charge_period_name"] as String

                    var storeId: String? = null
                    var publicIconUrl: String? = null
                    var publicIconThumbUrl: String? = null

                    when {
                        it.contains("custom_fields") -> {
                            val customFieldsBody = it["custom_fields"] as Map<String, Any>

                            when {
                                customFieldsBody.containsKey("store_id") -> storeId =
                                    customFieldsBody["store_id"] as String
                                customFieldsBody.containsKey("public_icon_thumb_url") -> publicIconThumbUrl =
                                    customFieldsBody["public_icon_thumb_url"] as String
                                customFieldsBody.containsKey("public_icon_url") -> publicIconUrl =
                                    customFieldsBody["public_icon_url"] as String
                            }
                        }
                    }

                    val customFields = ProductCustomFields(storeId, publicIconUrl, publicIconThumbUrl)
                    val productItem = Product(
                        id,
                        name,
                        description,
                        rentalQuantityAvailable,
                        rentalPrice,
                        rentalLeadChargePeriodName,
                        customFields
                    )

                    products.add(productItem)

                }
            }
        }

        data = products
    }
}
//
//class ProductItemSearchDto(response: Response<Any>?) : BaseDto {
//
//    var id: Double? = 0.0
//    var name: String? = null
//    var description: String? = null
//    var rental_quantity_available: String? = null
//    var rental_price: String? = null
//    var rental_lead_charge_period_name: String? = null
//    var custom_fields: ProductCustomFields? = null
//
//    init {
//        mapToDto(response)
//    }
//
//    override fun mapToDto(response: Response<Any>?): Any? {
//
//        var result: Any? = null
//        val responseBody = response?.body() as Map<String, Any>
//
//        when {
//            responseBody.containsKey("product") -> {
//                val productsBody = responseBody["product"] as Map<String, Any>
//                var customFields: ProductCustomFields? = null
//
//
//
//
//                if (productsBody.contains("custom_fields")) {
//                    val customFieldsBody = productsBody["custom_fields"] as Map<String, Any>
//                    var storeId: String? = null
//                    var publicIconUrl: String? = null
//                    var publicIconThumbUrl: String? = null
//
//                    when {
//                        customFieldsBody.containsKey("store_id") -> storeId = customFieldsBody["store_id"] as String
//                        customFieldsBody.containsKey("public_icon_thumb_url") -> publicIconThumbUrl =
//                            customFieldsBody["public_icon_thumb_url"] as String
//                        customFieldsBody.containsKey("public_icon_url") -> publicIconUrl =
//                            customFieldsBody["public_icon_url"] as String
//                    }
//                    customFields = ProductCustomFields(storeId, publicIconUrl, publicIconThumbUrl)
//                }
//                val id = productsBody["id"] as Double
//                val name = productsBody["name"] as String
//                val description = productsBody["description"] as String
//                val rentalPrice = productsBody["rental_price"] as String
//                val rentalQuantityAvailable = productsBody["rental_quantity_available"] as String
//                val rentalLeadChargePeriodName = productsBody["rental_lead_charge_period_name"] as String
//
//                result = Product(
//                    id,
//                    name,
//                    description,
//                    rentalQuantityAvailable,
//                    rentalPrice,
//                    rentalLeadChargePeriodName,
//                    customFields
//                )
//
//            }
//        }
//
//        return result
//    }
//}
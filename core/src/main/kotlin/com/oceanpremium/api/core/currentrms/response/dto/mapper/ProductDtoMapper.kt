package com.oceanpremium.api.core.currentrms.response.dto.mapper

import com.fasterxml.jackson.annotation.JsonInclude
import com.oceanpremium.api.core.currentrms.response.dto.product.ProductCustomFieldsDto
import com.oceanpremium.api.core.currentrms.response.dto.product.ProductDto
import org.springframework.http.HttpStatus
import retrofit2.Response

@JsonInclude(JsonInclude.Include.NON_NULL)
class ProductDtoMapper(var code: Int, response: Response<Any>?) {

    var httpStatus: HttpStatus = HttpStatus.valueOf(code)
    var data: Any? = null
    var meta = MetaDtoMapper(response)

    init {
       data = mapToDto(response)
    }

    /**
     * Determine if response contains either a JSON object response or a JSON array response,
     * and parse accordingly.
     */
    private fun mapToDto(response: Response<Any>?) : Any? {

        if(response?.body() == null) {
            return response?.message()
        }

        val responseBody = response.body() as Map<String, Any>

        /**
         * CurrentRms returns a 200 OK for empty result sets. As a best practice for REST API,
         * the response should be a 404 NOT FOUND when an empty result set is returned.
         *
         * See @link: https://stackoverflow.com/questions/11746894/what-is-the-proper-rest-response-code-for-a-valid-request-but-an-empty-data
         *
         * Therefore, override the 200 OK response code and return a 404 NOT FOUND response code and error message instead.
         */
        if (isResultEmpty(responseBody)) {
            code = HttpStatus.NOT_FOUND.value()
        }

        return when {
            responseBody.containsKey("products") -> {
                mapJsonArray(response)
            }
            responseBody.containsKey("product") -> {
               mapJsonObjectToDto(responseBody["product"] as Map<String, Any>)
            }
            else -> null
        }
    }

    /**
     * Map list of items to list of dto's
     */
    private fun mapJsonArray(response: Response<Any>?) : List<ProductDto> {
        val responseBody = response?.body() as Map<String, Any>
        val products: MutableList<ProductDto> = mutableListOf()
        val productsItemsBody = responseBody["products"] as List<Map<String, Any>>

        productsItemsBody.forEach {
            products.add(mapJsonObjectToDto(it))
        }

        return products
    }

    /**
     * Map a single item to dto
     */
    private fun mapJsonObjectToDto(itemBody: Map<String, Any>): ProductDto {
        var id: Double? = null
        var name: String? = null
        var description: String? = null
        var rentalPrice: String? = null
        var customFields: ProductCustomFieldsDto? = null
        var rentalQuantityAvailable: String? = null
        var rentalLeadChargePeriodName: String? = null

        if (itemBody.containsKey("id")) {
            id = itemBody["id"] as Double?
        }

        if (itemBody.containsKey("name")) {
            name = itemBody["name"] as String?
        }

        if (itemBody.containsKey("description")) {
            description = itemBody["description"] as String?
        }

        if (itemBody.containsKey("rental_price")) {
            rentalPrice = itemBody["rental_price"] as String?
        }

        if (itemBody.containsKey("custom_fields")) {
            customFields = mapCustomFieldsToDto(itemBody)
        }

        if (itemBody.containsKey("rental_quantity_available")) {
            rentalQuantityAvailable = itemBody["rental_quantity_available"] as String?
        }

        if (itemBody.containsKey("rental_lead_charge_period_name")) {
            rentalLeadChargePeriodName = itemBody["rental_lead_charge_period_name"] as String?
        }

        return ProductDto(
            id,
            name,
            description,
            rentalQuantityAvailable,
            rentalPrice,
            rentalLeadChargePeriodName,
            customFields
        )
    }

    /**
     * Map custom fields to dto
     */
    private fun mapCustomFieldsToDto(itemBody: Map<String, Any>): ProductCustomFieldsDto {
        var storeId: String? = null
        var publicIconUrl: String? = null
        var publicIconThumbUrl: String? = null

        when {
            itemBody.contains("custom_fields") -> {
                val customFieldsBody = itemBody["custom_fields"] as Map<String, Any>

                when {
                    customFieldsBody.containsKey("store_id") -> storeId =
                        customFieldsBody["store_id"] as String?
                    customFieldsBody.containsKey("public_icon_thumb_url") -> publicIconThumbUrl =
                        customFieldsBody["public_icon_thumb_url"] as String?
                    customFieldsBody.containsKey("public_icon_url") -> publicIconUrl =
                        customFieldsBody["public_icon_url"] as String?
                }
            }
        }

        return ProductCustomFieldsDto(
            storeId,
            publicIconUrl,
            publicIconThumbUrl
        )
    }

    /**
     *
     * In case a result SET (array) of items is returned, current rms returns a meta object containing details about
     * pagination and the size of the result set. Utilize the meta object to check on row_count and determine if
     * the result set is empty or not.
     */
    private fun isResultEmpty(objectBody: Map<*, *>): Boolean {

        val metaKey = "meta"
        val rowCountKey = "row_count"

        if (objectBody.containsKey(metaKey)) {
            val meta = objectBody[metaKey] as Map<*, *>

            if (meta.containsKey(rowCountKey)) {
                return when (meta[rowCountKey] as Double) {
                    0.0 -> {
                        true
                    }
                    else -> {
                        false
                    }
                }
            }
        }

        return false
    }
}
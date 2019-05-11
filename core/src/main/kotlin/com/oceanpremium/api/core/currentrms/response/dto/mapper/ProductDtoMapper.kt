package com.oceanpremium.api.core.currentrms.response.dto.mapper

import com.fasterxml.jackson.annotation.JsonInclude
import com.oceanpremium.api.core.currentrms.response.CurrentRmsApiResponse
import com.oceanpremium.api.core.exception.ServerErrorException
import com.oceanpremium.api.core.currentrms.response.dto.product.ProductCustomFieldsDto
import com.oceanpremium.api.core.currentrms.response.dto.product.ProductDto
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import retrofit2.Response

@JsonInclude(JsonInclude.Include.NON_NULL)
class ProductDtoMapper(code: Int, response: Response<Any>?) : CurrentRmsBaseDtoMapper(code) {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    init {
        /**
         * The root node of the response payload that will contain the response result for data key
         */
       data = mapToDto(response)
    }

    /**
     * Determine if response contains either a JSON object response or a JSON array response,
     * and parse accordingly.
     */
    private fun mapToDto(response: Response<Any>?) : Any? {

        when {
            response != null -> if(!response.isSuccessful) {
                data = CurrentRmsApiResponse.ErrorMessage(response.code(), response.message())

                return data
            }
        }

        val responseBody = response?.body() as Map<*, *>

        return when {
            responseBody.containsKey("products") -> {

                /**
                 * CurrentRms returns a 200 OK for empty result sets. As a best practice for REST API,
                 * the response should be a 404 NOT FOUND when an empty result set is returned.
                 *
                 * See @link: https://stackoverflow.com/questions/11746894/what-is-the-proper-rest-response-code-for-a-valid-request-but-an-empty-data
                 *
                 * Therefore, override the 200 OK response code and return a 404 NOT FOUND response code and error message instead.
                 */
                val metaMapper = MetaDtoMapper(response)

                if (metaMapper.overrideHttpStatus) {
                    httpStatus = HttpStatus.NOT_FOUND
                    data = CurrentRmsApiResponse.ErrorMessage(response.code(), response.message())

                    return data
                }

                meta = metaMapper.meta
                mapJsonArray(response)
            }
            responseBody.containsKey("product") -> {
               mapJsonObjectToDto(responseBody["product"] as Map<*, *>)
            }
            else -> null
        }
    }

    /**
     * Map list of items to list of dtoMapper
     */
    private fun mapJsonArray(response: Response<Any>?) : List<ProductDto> {
        val responseBody = response?.body() as Map<*, *>
        val products: MutableList<ProductDto> = mutableListOf()
        val productsItemsBody = responseBody["products"] as List<Map<*, *>>

        productsItemsBody.forEach {
            products.add(mapJsonObjectToDto(it))
        }

        return products
    }

    /**
     * Map a single item to dtoMapper
     */
    @Throws(ServerErrorException::class)
    private fun mapJsonObjectToDto(itemBody: Map<*, *>): ProductDto {
        var id: Int? = null
        var name: String? = null
        var description: String? = null
        var rentalPrice: String? = null
        var customFields: ProductCustomFieldsDto? = null
        var rentalQuantityAvailable: String? = null
        var rentalLeadChargePeriodName: String? = null

        try {
            if (itemBody.containsKey("id")) {
                id = (itemBody["id"] as Double?)?.toInt()
            }

            if (itemBody.containsKey("name")) {
                name = itemBody["name"] as String?
            }

            if (itemBody.containsKey("description")) {
                description = itemBody["description"] as String?
            }

            if (itemBody.containsKey("custom_fields")) {
                customFields = mapCustomFieldsToDto(itemBody)
            }

            val rates = mapProductRates(itemBody)

            if (rates.containsKey("rental_price")) {
                rates["rental_price"] as String?
            }

            if (rates.containsKey("rental_quantity_available")) {
                rentalQuantityAvailable = rates["rental_quantity_available"] as String?
            }

            if (rates.containsKey("rental_lead_charge_period_name")) {
                rentalLeadChargePeriodName = rates["rental_lead_charge_period_name"] as String?
            }
        } catch (e: Exception) {
            val message = "Failed to map product response to Dto: ${e.message}"
            logger.error(message)
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

    private fun mapProductRates(itemBody: Map<*, *>): Map<*, *> {
        val rates: MutableMap<String, Any?> = mutableMapOf()

        if (itemBody.containsKey("rental_price")
            && itemBody.containsKey("rental_quantity_available")
            && itemBody.containsKey("rental_lead_charge_period_name")) {
            rates["rental_price"] = itemBody["rental_price"]
            rates["rental_quantity_available"] = itemBody["rental_quantity_available"] as String?
            rates["rental_lead_charge_period_name"] = itemBody["rental_lead_charge_period_name"] as String?
        } else {
            if (itemBody.containsKey("rental_rate")) {
                val rentalRate = (itemBody["rental_rates"] as List<Map<*, *>>).first()

                rates["rental_price"] = rentalRate["price"]
                rates["rental_lead_charge_period_name"] = rentalRate["rate_definition_name"] as String?
            }
        }

        return rates
    }

    /**
     * Map custom fields to dtoMapper
     */
    private fun mapCustomFieldsToDto(itemBody: Map<*, *>): ProductCustomFieldsDto? {
        var storeId: Int? = null
        var publicIconThumbUrl: String? = null
        var publicIconUrl: String? = null

        when {
            itemBody.contains("custom_fields") -> {
                val customFieldsBody = itemBody["custom_fields"] as Map<*, *>

                when {
                    customFieldsBody.containsKey("store_id") ->

                        if ((customFieldsBody["store_id"] as String?)!!.isNotEmpty()) {
                            storeId = (customFieldsBody["store_id"] as String?)!!.toInt()
                        }
                }

                when {
                    customFieldsBody.containsKey("public_icon_thumb_url") -> publicIconThumbUrl =
                        customFieldsBody["public_icon_thumb_url"] as String?
                }

                when {
                    customFieldsBody.containsKey("public_icon_url") -> publicIconUrl =
                        customFieldsBody["public_icon_url"] as String?
                }
            }
        }

        if (storeId == null && publicIconThumbUrl.isNullOrEmpty() && publicIconUrl.isNullOrEmpty()) {
            return null
        }

        return ProductCustomFieldsDto(
            storeId,
            publicIconUrl,
            publicIconThumbUrl
        )
    }
}
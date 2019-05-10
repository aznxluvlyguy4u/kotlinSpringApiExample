package com.oceanpremium.api.core.currentrms.response.dto.mapper

import com.fasterxml.jackson.annotation.JsonInclude
import com.oceanpremium.api.core.currentrms.response.dto.product.ProductGroupCustomFieldsDto
import com.oceanpremium.api.core.currentrms.response.dto.product.ProductGroupDto
import retrofit2.Response

@JsonInclude(JsonInclude.Include.NON_NULL)
class ProductGroupDtoMapper(var code: Int, response: Response<Any>?) {

    var data: Any? = null

    // A collection always has a meta node in its response
    var meta = MetaDtoMapper(response)

    init {
        data = mapToDto(response)
    }

    /**
     * Determine if response contains either a JSON object response or a JSON array response,
     * and parse accordingly.
     */
    private fun mapToDto(response: Response<Any>?) : Any? {
        val responseBody = response?.body() as Map<String, Any>

        return when {
            responseBody.containsKey("product_groups") -> {
                mapJsonArray(response)
            }
            responseBody.containsKey("product_group") -> {
                mapJsonObjectToDto(responseBody["product_group"] as Map<String, Any>)
            }
            else -> null
        }

    }

    private fun mapJsonArray(response: Response<Any>?) : List<ProductGroupDto> {
        val responseBody = response?.body() as Map<String, Any>
        val productGroups: MutableList<ProductGroupDto> = mutableListOf()
        val productsItemsBody = responseBody["product_groups"] as List<Map<String, Any>>

        productsItemsBody.forEach {
            productGroups.add(mapJsonObjectToDto(it))
        }

        return productGroups
    }

    /**
     * Map a single item to dto
     */
    private fun mapJsonObjectToDto(itemBody: Map<String, Any>): ProductGroupDto {
        var id: Double? = null
        var name: String? = null
        var description: String? = null
        var customFields: ProductGroupCustomFieldsDto? = null

        if (itemBody.containsKey("id")) {
            id = itemBody["id"] as Double?
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

        return ProductGroupDto(
            id,
            name,
            description,
            customFields
        )
    }

    /**
     * Map custom fields to dto
     */
    private fun mapCustomFieldsToDto(itemBody: Map<String, Any>): ProductGroupCustomFieldsDto {
        var publicIconUrl: String? = null
        var publicIconThumbUrl: String? = null

        when {
            itemBody.contains("custom_fields") -> {
                val customFieldsBody = itemBody["custom_fields"] as Map<String, Any>

                when {
                    customFieldsBody.containsKey("public_icon_thumb_url") -> publicIconThumbUrl =
                        customFieldsBody["public_icon_thumb_url"] as String?
                    customFieldsBody.containsKey("public_icon_url") -> publicIconUrl =
                        customFieldsBody["public_icon_url"] as String?
                }
            }
        }

        return ProductGroupCustomFieldsDto(
            publicIconUrl,
            publicIconThumbUrl
        )
    }

}
package com.oceanpremium.api.core.currentrms.response.dto.mapper

import com.fasterxml.jackson.annotation.JsonInclude
import com.oceanpremium.api.core.currentrms.response.dto.product.ProductGroupCustomFieldsDto
import com.oceanpremium.api.core.currentrms.response.dto.product.ProductGroupDto
import com.oceanpremium.api.core.exception.handler.ApiError
import com.oceanpremium.api.core.exception.throwable.ServerErrorException
import org.slf4j.LoggerFactory
import retrofit2.Response

@JsonInclude(JsonInclude.Include.NON_NULL)
class ProductGroupDtoMapper(var code: Int, response: Response<Any>?) : CurrentRmsBaseDtoMapper(code) {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    init {
        /**
         * The root node of the response payload that will contain the response result for data key.
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
                data = ApiError(code = response.code(), message = response.message())

                return data
            }
        }

        val responseBody = response?.body() as Map<*, *>

        return when {
            responseBody.containsKey("product_groups") -> {
                meta = MetaDtoMapper(response).meta
                mapJsonArray(response)
            }
            responseBody.containsKey("product_group") -> {
                mapJsonObjectToDto(responseBody["product_group"] as Map<*, *>)
            }
            else -> null
        }

    }

    /**
     * Map list of items to list of dtoMapper.
     */
    private fun mapJsonArray(response: Response<Any>?) : List<ProductGroupDto> {
        val responseBody = response?.body() as Map<*, *>
        val productGroups: MutableList<ProductGroupDto> = mutableListOf()
        @Suppress("UNCHECKED_CAST")
        val productsItemsBody = responseBody["product_groups"] as List<Map<*, *>>

        productsItemsBody.forEach {
            productGroups.add(mapJsonObjectToDto(it))
        }

        return productGroups
    }

    /**
     * Map a single item to dtoMapper.
     */
    private fun mapJsonObjectToDto(itemBody: Map<*, *>): ProductGroupDto {
        var id: Int? = null
        var name: String? = null
        var description: String? = null
        var customFields: ProductGroupCustomFieldsDto? = null

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
        } catch (e: Exception) {
            e.printStackTrace()
            val message = "Failed to map product response to Dto: ${e.message}"
            logger.error(message)

            throw ServerErrorException(message)
        }

        return ProductGroupDto(
            id,
            name,
            description,
            customFields
        )
    }

    /**
     * Map custom fields to dtoMapper.
     */
    private fun mapCustomFieldsToDto(itemBody: Map<*, *>): ProductGroupCustomFieldsDto? {
        var storeId: String? = null
        var publicIconUrl: String? = null
        var publicIconThumbUrl: String? = null

        when {
            itemBody.contains("custom_fields") -> {
                val customFieldsBody = itemBody["custom_fields"] as Map<*, *>

                when {
                    customFieldsBody.containsKey("store_id") -> storeId =
                        customFieldsBody["store_id"] as String?
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

        if (storeId.isNullOrEmpty() && publicIconThumbUrl.isNullOrEmpty() && publicIconUrl.isNullOrEmpty()) {
            return null
        }

        return ProductGroupCustomFieldsDto(
            publicIconUrl,
            publicIconThumbUrl
        )
    }

}
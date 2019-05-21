package com.oceanpremium.api.core.currentrms.response.dto.mapper

import com.oceanpremium.api.core.currentrms.response.dto.product.*
import com.oceanpremium.api.core.exception.throwable.BadRequestException
import org.slf4j.LoggerFactory

class AccessoryDtoMapper(itemBody: Map<*, *>) {

    var data: List<ProductAccessoryDto> = listOf()

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
        private const val ACCESSORIES_KEY = "accessories"
    }

    init {
        data = mapJsonArray(itemBody)
    }

    /**
     * Map list of items to list of dto's
     */
    private fun mapJsonArray(itemBody: Map<*, *>): List<ProductAccessoryDto> {
        val products: MutableList<ProductAccessoryDto> = mutableListOf()
        @Suppress("UNCHECKED_CAST")

        if (itemBody.containsKey(ACCESSORIES_KEY)) {
            val productsItemsBody = itemBody[ACCESSORIES_KEY] as List<Map<*, *>>

            productsItemsBody.forEach {
                products.add(mapJsonObjectToDto(it))
            }
        }

        return products
    }

    /**
     * Map a single item to dto
     */
    @Throws(BadRequestException::class)
    private fun mapJsonObjectToDto(itemBody: Map<*, *>): ProductAccessoryDto {
        var id: Int? = null
        var name: String? = null
        var description: String? = null
        var customFields: ProductCustomFieldsDto? = null
        var quantity: String? = null
        val imageSources = mapImageSourcesToDto(itemBody, customFields)
        var inclusionType : String? = null

        try {
            if (itemBody.containsKey("related_id")) {
                id = (itemBody["related_id"] as Double?)?.toInt()
            }

            if (itemBody.containsKey("related_name")) {
                name = itemBody["related_name"] as String?
            }

            if (itemBody.containsKey("description")) {
                description = itemBody["description"] as String?
            }

            if (itemBody.containsKey("custom_fields")) {
                customFields = mapCustomFieldsToDto(itemBody)
            }

            if (itemBody.containsKey("inclusion_type_name")) {
                inclusionType = itemBody["inclusion_type_name"] as String?
            }

            if (itemBody.containsKey("quantity")) {
                quantity = itemBody["quantity"] as String?
            }
        } catch (e: Exception) {
            e.printStackTrace()

            val message = "Failed to map product accessory response to Dto: ${e.message}"
            logger.error(message)

            throw BadRequestException(e.message)
        }

        return ProductAccessoryDto(
            id,
            name,
            description,
            inclusionType,
            quantity,
            imageSources.sources,
            customFields
        )
    }

    /**
     * image urls are setup as both primary fields and custom fields in current rms.
     * Per default get the primary image fields, if custom fields are set, check if public image icon url is set
     */
    @Throws(BadRequestException::class)
    private fun mapImageSourcesToDto(itemBody: Map<*, *>, customFieldsDto: ProductCustomFieldsDto?): ImageDto {
        val imageSources: MutableList<ImageSource> = mutableListOf()

        when {
            itemBody.containsKey("related_icon_url") && itemBody.containsKey("related_icon_thumb_url") -> {
                imageSources.add(ImageSource(itemBody["related_icon_url"] as String?, itemBody["related_icon_thumb_url"] as String?))
            }

            itemBody.containsKey("icon")  -> {
                val icon = itemBody["icon"] as Map<*,*>?
                var imageUrl: String? = null
                var thumbUrl: String? = null

                try {
                    if (icon != null) {
                        if (icon.containsKey("url")) {
                            imageUrl = icon["url"] as String?
                        }

                        if (icon.containsKey("thumb_url")) {
                            thumbUrl = icon["thumb_url"] as String?
                        }

                        if (imageUrl != null && thumbUrl != null) {
                            imageSources.add(ImageSource(imageUrl, thumbUrl))
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()

                    val message = "Failed to map product response to Dto: ${e.message}"
                    logger.error(message)

                    throw BadRequestException(e.message)
                }
            }
            else -> if (!customFieldsDto?.publicIconUrl.isNullOrEmpty()) {
                val imageUrl = customFieldsDto!!.publicIconUrl
                var imageThumbUrl: String? = imageUrl

                if (!customFieldsDto.publicIconThumbUrl.isNullOrEmpty()) {
                    imageThumbUrl = customFieldsDto.publicIconThumbUrl
                }

                if (imageUrl != null && imageThumbUrl != null) {
                    imageSources.add(ImageSource(imageUrl, imageThumbUrl))
                }
            }
        }

        return ImageDto(imageSources)
    }

    /**
     * Map custom fields to dtoMapper
     */
    private fun mapCustomFieldsToDto(itemBody: Map<*, *>): ProductCustomFieldsDto? {
        var storeId: Int? = null
        var publicIconThumbUrl: String? = null
        var publicIconUrl: String? = null

        try {
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
        } catch (e: Exception) {
            e.printStackTrace()

            val message = "Failed to map product response to Dto: ${e.message}"
            logger.error(message)

            throw BadRequestException(e.message)
        }

        return ProductCustomFieldsDto(
            storeId,
            publicIconUrl,
            publicIconThumbUrl
        )
    }

}

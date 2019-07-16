package com.oceanpremium.api.core.currentrms.response.dto.mapper

import com.fasterxml.jackson.annotation.JsonInclude
import com.oceanpremium.api.core.currentrms.response.dto.product.*
import com.oceanpremium.api.core.exception.handler.ApiError
import com.oceanpremium.api.core.exception.throwable.NotFoundException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import retrofit2.Response
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.oceanpremium.api.core.enum.AccessoryType
import com.oceanpremium.api.core.model.ConfigPropertyField
import com.oceanpremium.api.core.exception.throwable.BadRequestException
import com.oceanpremium.api.core.util.FileSizeFormatUtil

@JsonInclude(JsonInclude.Include.NON_NULL)
class ErrorResponse {
    val errors: MutableList<Any> = mutableListOf()
}

@JsonInclude(JsonInclude.Include.NON_NULL)
class ProductDtoMapper(code: Int, response: Response<Any>?) : CurrentRmsBaseDtoMapper(code) {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
        private const val ACCESSORIES_KEY = "accessories"
        private const val CUSTOM_FIELDS_KEY = "custom_fields"
        private const val STORE_QUANTITIES_KEY = "store_quantities"
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
    @Throws(NotFoundException::class)
    private fun mapToDto(response: Response<Any>?): Any? {

        when {
            // Request failed, build error message
            response != null && !response.isSuccessful -> {
                val errorBody = response.errorBody()

                error = when {
                    errorBody != null -> {
                        val type = object : TypeToken<ErrorResponse>() {}.type
                        val errorResponse: ErrorResponse? = Gson().fromJson(response.errorBody()!!.charStream(), type)

                        ApiError(code = response.code(), message = errorResponse)
                    }

                    else -> {
                        ApiError(code = response.code(), message = response.message())
                    }
                }

                return null
            }

            // Request succeeded, parse response payload
            response != null && response.isSuccessful -> {
                val responseBody = response.body() as Map<*, *>

                data = when {
                    // Product Inventory
                    responseBody.containsKey("products") -> {

                        /**
                         * CurrentRms returns a 200 OK for empty result sets. AND after accepting multi store ids as input,
                         * when no store ids are given, default response is given of the store id with lowest ID.
                         * As a best practice for REST API, the response should be a 404 NOT FOUND when an empty result set is returned OR when no store ids is supplied.
                         *
                         * See @link: https://stackoverflow.com/questions/11746894/what-is-the-proper-rest-response-code-for-a-valid-request-but-an-empty-data
                         *
                         * Therefore, override the 200 OK response code and return a 404 NOT FOUND response code and error message instead.
                         */
                        val metaMapper = MetaDtoMapper(response)

                        if (metaMapper.overrideHttpStatus) {
                            httpStatus = HttpStatus.NOT_FOUND

                            val errorResponse = ErrorResponse()
                            errorResponse.errors.add(httpStatus.reasonPhrase)

                            error = ApiError(code = httpStatus.value(), message = errorResponse)

                            return null
                        }

                        meta = metaMapper.meta
                        mapJsonArray(response)
                    }

                    // Single Product
                    responseBody.containsKey("product") -> {
                        mapJsonObjectToDto(responseBody["product"] as Map<*, *>)
                    }

                    else -> null
                }
            }
        }

        return data
    }

    /**
     * Map list of items to list of dtoMapper
     */
    private fun mapJsonArray(response: Response<Any>?): List<ProductDto> {
        val responseBody = response?.body() as Map<*, *>
        val products: MutableList<ProductDto> = mutableListOf()
        @Suppress("UNCHECKED_CAST")
        val productsItemsBody = responseBody["products"] as List<Map<*, *>>

        productsItemsBody.forEach {
            products.add(mapJsonObjectToDto(it))
        }

        return products
    }

    /**
     * Map a single item to dtoMapper
     */
    @Throws(BadRequestException::class)
    private fun mapJsonObjectToDto(itemBody: Map<*, *>): ProductDto {
        var id: Int? = null
        val name: String? = mapProductName(itemBody)
        val seoFriendlyProductName = mapSeoFriendlyProductName(itemBody)
        var type: String? = null
        val productGroup: ProductGroupDto? = mapProductGroupToDto(itemBody)
        var customFields: ProductCustomFieldsDto? = null
        val rates = mapProductRatesToDto(itemBody)
        val imageSources: ImageDto?
        val attachments: List<AttachmentDto>? = mapAttachments(itemBody)
        val rawConfigurationIds = mapConfigIds(itemBody)
        val descriptions = mapDescriptionText(itemBody)
        val storeQuantities: List<StoreQuantityDto>? = mapStoreQuantities(itemBody)
        val accessories: List<ProductDto> = mapAccessoriesToProductDto(itemBody)

        try {
            if (itemBody.containsKey("id")) {
                id = (itemBody["id"] as Double?)?.toInt()
            }

            if (itemBody.containsKey("custom_fields")) {
                customFields = mapCustomFieldsToDto(itemBody)
            }

            if (itemBody.containsKey("type")) {
                type = itemBody["type"] as String?
            }

            imageSources = mapImageSourcesToDto(itemBody)

        } catch (e: Exception) {
            e.printStackTrace()

            val message = "Failed to map product response to Dto: ${e.message}"
            logger.error(message)

            throw BadRequestException(e.message)
        }

        return ProductDto(
            id,
            name,
            descriptions,
            type,
            productGroup,
            rates.pricings,
            imageSources.sources,
            customFields,
            attachments,
            storeQuantities,
            rawConfigurationIds,
            accessories = accessories,
            seoFriendlyName = seoFriendlyProductName
        )
    }

    /**
     * image urls are setup as both primary fields and custom fields in current rms.
     * Per default get the primary image fields, if custom fields are set, check if public image icon url is set
     */
    @Throws(BadRequestException::class)
    private fun mapImageSourcesToDto(itemBody: Map<*, *>): ImageDto {
        val imageSources: MutableList<ImageSource> = mutableListOf()
        var imageSourceCandidate: ImageSource? = null

        // Grab custom product image urls (public friendly images)
        // otherwise, no public friendly images where found, thus revert to the product images set for a given product in currentRMS.
        try {
            if (itemBody.containsKey("icon")) {
                val icon = itemBody["icon"] as Map<*, *>?
                var imageUrl: String? = null
                var thumbUrl: String? = null

                when {
                    icon != null -> {
                        when {
                            icon.containsKey("url") -> imageUrl = icon["url"] as String?
                        }

                        when {
                            icon.containsKey("thumb_url") -> thumbUrl = icon["thumb_url"] as String?
                        }

                        when {
                            imageUrl != null && thumbUrl != null -> imageSourceCandidate =
                                ImageSource(imageUrl, thumbUrl)
                        }
                    }
                }
            } else {
                if (itemBody.containsKey("icon_thumb_url") && itemBody.containsKey("icon_url")) {
                    imageSourceCandidate =
                        ImageSource(itemBody["icon_url"] as String?, itemBody["icon_thumb_url"] as String?)
                }
            }

            if (itemBody.containsKey(CUSTOM_FIELDS_KEY)) {
                @Suppress("UNCHECKED_CAST")
                val customFieldsBody = itemBody[CUSTOM_FIELDS_KEY] as Map<String, *>
                var imageUrl: String? = null
                var thumbUrl: String? = null
                val imageUrlKey = "custom_product_public_icon_url"
                val imageThumbUrlKey = "custom_product_public_icon_thumb_url"

                when {
                    customFieldsBody.containsKey(imageUrlKey)
                            && (customFieldsBody[imageUrlKey] as String?) != null
                            && (customFieldsBody[imageUrlKey] as String).isNotEmpty() -> imageUrl =
                        customFieldsBody[imageUrlKey] as String?
                }

                when {
                    customFieldsBody.containsKey(imageThumbUrlKey)
                            && (customFieldsBody[imageThumbUrlKey] as String?) != null
                            && (customFieldsBody[imageThumbUrlKey] as String).isNotEmpty() -> thumbUrl =
                        customFieldsBody[imageThumbUrlKey] as String?
                }

                when {
                    imageUrl != null && thumbUrl != null -> imageSourceCandidate = ImageSource(imageUrl, thumbUrl)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()

            val message = "Failed to map product images response to Dto: ${e.message}"
            logger.error(message)

            throw BadRequestException(e.message)
        }

        if (imageSourceCandidate != null) {
            imageSources.add(imageSourceCandidate)
        }

        return ImageDto(imageSources)
    }

    @Throws(BadRequestException::class)
    private fun mapProductRatesToDto(itemBody: Map<*, *>, isAccessory: Boolean = false): RateDto {
        val rates: MutableList<PricingDto> = mutableListOf()
        try {
            when {
                // Parse rates for accessory products
                isAccessory -> {
                    logger.debug("parse rates for accessories: $itemBody")
                    var quantityStr: String? = null

                    if(itemBody.containsKey("quantity")) {
                        val quantity = (itemBody["quantity"] as String?)?.toDouble()
                        if (quantity != null) {
                            quantityStr = "%.2f".format(quantity)
                        }
                    }

                    if (itemBody.containsKey("item")) {
                        @Suppress("UNCHECKED_CAST")
                        val item = itemBody["item"] as Map<*,*>

                        val rentalRate = item["rental_rate"] as Map<*, *>
                        val rentalPrice = rentalRate["price"] as String?
                        val rentalLeadChargePeriodName = "Daily"

                        when {
                            !rentalPrice.isNullOrEmpty() -> {
                                rates.add(
                                    PricingDto(
                                        quantityAvailable = quantityStr,
                                        price = rentalPrice,
                                        chargePeriod = rentalLeadChargePeriodName
                                    )
                                )
                            }
                        }
                    }
                }

                // Parse rates when availability is requested
                itemBody.containsKey("rental_price")
                        && itemBody.containsKey("rental_quantity_available")
                        && itemBody.containsKey("rental_lead_charge_period_name") -> {
                    val rentalPrice = itemBody["rental_price"] as String?
                    val rentalQuantityAvailable = itemBody["rental_quantity_available"] as String?
                    val rentalLeadChargePeriodName = itemBody["rental_lead_charge_period_name"] as String?

                    rates.add(
                        PricingDto(
                            rentalQuantityAvailable,
                            rentalPrice,
                            rentalLeadChargePeriodName
                        )
                    )
                }

                // Parse rates when product by id is requested
                else -> if (itemBody.containsKey("rental_rate")) {
                    @Suppress("UNCHECKED_CAST")
                    val rentalRate = itemBody["rental_rate"] as Map<*, *>
                    val rentalPrice = rentalRate["price"] as String?
                    val rentalLeadChargePeriodName = "Daily"

                    when {
                        !rentalPrice.isNullOrEmpty() -> {
                            rates.add(
                                PricingDto(
                                    price = rentalPrice,
                                    chargePeriod = rentalLeadChargePeriodName
                                )
                            )
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()

            val message = "Failed to map product response to Dto: ${e.message}"
            logger.error(message)

            throw BadRequestException(e.message)
        }

        return RateDto(rates)
    }

    private fun mapProductGroupToDto(itemBody: Map<*, *>): ProductGroupDto? {
        var productGroupId: Int? = null
        var productGroupName: String? = null

        try {
            if (itemBody.containsKey("product_group_id")
                && itemBody.containsKey("product_group_name")
            ) {
                productGroupId = (itemBody["product_group_id"] as Double?)?.toInt()
                productGroupName = itemBody["product_group_name"] as String?

            } else {
                if (itemBody.containsKey("product_group")) {
                    val productGroup = itemBody["product_group"] as Map<*, *>

                    when {
                        productGroup.containsKey("id") && productGroup.containsKey("name") -> {
                            productGroupId = (productGroup["id"] as Double?)?.toInt()
                            productGroupName = productGroup["name"] as String?
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()

            val message = "Failed to map product response to Dto: ${e.message}"
            logger.error(message)

            throw BadRequestException(e.message)
        }

        if (productGroupId == null && productGroupName == null) {
            return null
        }

        return ProductGroupDto(id = productGroupId, name = productGroupName)
    }

    /**
     * Map custom fields to dtoMapper
     */
    private fun mapCustomFieldsToDto(itemBody: Map<*, *>): ProductCustomFieldsDto? {
        val storeIdKey = "store_id"
        var storeId: Int? = null

        try {
            when {
                itemBody.contains(CUSTOM_FIELDS_KEY) -> {
                    val customFieldsBody = itemBody[CUSTOM_FIELDS_KEY] as Map<*, *>

                    when {
                        customFieldsBody.containsKey(storeIdKey) ->
                            if ((customFieldsBody[storeIdKey] as String?)!!.isNotEmpty()) {
                                storeId = (customFieldsBody[storeIdKey] as String?)!!.toInt()
                            }
                    }
                }
            }

            if (storeId == null) {
                return null
            }

        } catch (e: Exception) {
            e.printStackTrace()

            val message = "Failed to map product response to Dto: ${e.message}"
            logger.error(message)

            throw BadRequestException(e.message)
        }

        return ProductCustomFieldsDto(
            storeId
        )
    }

    /**
     * Grab accessory ids to get the full accessories details in additional API calls.
     * There are three types of accessories in Current RMS: Mandatory, Optional , Default.
     */
    private fun mapAccessoriesToProductDto(itemBody: Map<*, *>): List<ProductDto> {
        val items: MutableList<ProductDto> = mutableListOf()
        val accessoryIdKey = "related_id"

        if (itemBody.containsKey(ACCESSORIES_KEY)) {
            @Suppress("UNCHECKED_CAST")
            val productsItemsBody = itemBody[ACCESSORIES_KEY] as List<Map<*, *>>

            productsItemsBody.forEach {
                var id: Int? = null

                try {

                    if (it.containsKey(accessoryIdKey) && it[accessoryIdKey] != null) {
                        id = (it[accessoryIdKey] as Double).toInt()
                    }

                    val seoFriendlyName = mapSeoFriendlyProductName(it)
                    val rates = mapProductRatesToDto(it, true)


                    if (it.containsKey("item")) {
                        @Suppress("UNCHECKED_CAST")
                        val item = it["item"] as Map<String, String>

                        val name: String? = mapProductName(item)
                        var type: String? = null
                        val productGroup: ProductGroupDto? = mapProductGroupToDto(item)
                        var customFields: ProductCustomFieldsDto? = null
                        val imageSources = mapImageSourcesToDto(item)
                        val attachments: List<AttachmentDto>? = mapAttachments(item)
                        val rawConfigurationIds = mapConfigIds(item)
                        val descriptions = mapDescriptionText(item)

                        when {
                            item.containsKey("custom_fields") -> customFields = mapCustomFieldsToDto(itemBody)
                        }

                        when {
                            it.containsKey("inclusion_type_name") && it["inclusion_type_name"] != null -> {
                                val accessoryType = it["inclusion_type_name"] as String
                                type = AccessoryType.valueOf(accessoryType.toUpperCase()).name
                                logger.debug("Found accessory with Id: $id and type: $type")
                            }
                        }

                        val accessoryProductItem = ProductDto(
                            id,
                            name,
                            descriptions,
                            type,
                            productGroup,
                            rates.pricings,
                            imageSources.sources,
                            customFields,
                            attachments,
                            rawConfigurationIds = rawConfigurationIds
                        )

                        when {
                            seoFriendlyName != null -> accessoryProductItem.seoFriendlyName = seoFriendlyName
                        }

                        items.add(accessoryProductItem)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()

                    val message = "Failed to map accessories product response to Dto: ${e.message}"
                    logger.error(message)

                    throw BadRequestException(e.message)
                }
            }
        }

        return items
    }

    /**
     * Grab custom field config ids, so we can resolve the configurations option names for the product.
     * This depends on a HARDCODED FORMAT OF CUSTOM FIELDS (custom_product_config_option_)
     */
    private fun mapConfigIds(itemBody: Map<*, *>): List<ConfigPropertyField> {
        val items: MutableList<ConfigPropertyField> = mutableListOf()

        try {

            when {
                // Grab custom fields, find specifically keys that contain custom_product_config_option_ substring
                itemBody.contains("custom_fields") -> {
                    val customFieldsBody = itemBody["custom_fields"] as Map<*, *>

                    val configKeysToProcess: MutableList<String> = mutableListOf()

                    customFieldsBody.keys.forEach {
                        it as String
                        if (it.contains("custom_product_config_option_")) {
                            // Build collection of config
                            configKeysToProcess.add(it)
                        }
                    }

                    logger.debug("$customFieldsBody")

                    configKeysToProcess.forEach {
                        val configName: String? = it
                        @Suppress("UNCHECKED_CAST")
                        val rawConfigIds = customFieldsBody[it] as List<Double>?
                        val preparedConfigIds: MutableList<Int> = mutableListOf()

                        // Parse list of doubles to list of ints
                        rawConfigIds?.forEach { idItem ->
                            val id = idItem.toInt()
                            preparedConfigIds.add(id)
                        }

                        if (configName != null && preparedConfigIds.size > 0) {
                            logger.debug("HIT list:$configName: $preparedConfigIds")
                            val configIds =
                                ConfigPropertyField(configName, preparedConfigIds)
                            items.add(configIds)
                        }

                    }

                }
            }

        } catch (e: Exception) {
            e.printStackTrace()

            val message = "Failed to map product response to Dto: ${e.message}"
            logger.error(message)

            throw BadRequestException(e.message)
        }

        return items
    }

    /**
     * Grab the product specific product attachments, for the given product.
     */
    private fun mapAttachments(itemBody: Map<*, *>): List<AttachmentDto>? {
        val attachments: MutableList<AttachmentDto> = mutableListOf()

        try {
            if (itemBody.containsKey("attachments")) {
                @Suppress("UNCHECKED_CAST")
                val attachmentItems = itemBody["attachments"] as List<Map<*, *>>

                attachmentItems.forEach {

                    var mimeType: String? = null
                    var fileSize: String? = null
                    var fileUrl: String? = null

                    if (it.containsKey("attachment_content_type")) {
                        mimeType = it["attachment_content_type"] as String?
                    }

                    if (it.containsKey("attachment_file_size")) {
                        val size = (it["attachment_file_size"] as Double?)?.toInt()

                        when {
                            size != null -> fileSize = FileSizeFormatUtil.convert(size)
                        }
                    }

                    if (it.containsKey("attachment_url")) {
                        fileUrl = it["attachment_url"] as String?
                    }

                    if (mimeType != null && fileSize != null && fileUrl != null) {
                        attachments.add(AttachmentDto(mimeType, fileSize, fileUrl))
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()

            val message = "Failed to map product attachments response to Dto: ${e.message}"
            logger.error(message)

            throw BadRequestException(e.message)
        }

        return attachments
    }

    /**
     * Grab the custom fields for the full details of a product item.
     */
    private fun mapDescriptionText(itemBody: Map<*, *>): Map<*, *> {
        val descriptions = mutableMapOf<String, Any>()
        val customProductDescriptionKey = "custom_product_description_"
        val headKeyPrefix = customProductDescriptionKey + "head_"
        val paragraphKeyPrefix = customProductDescriptionKey + "paragraph_"
        val sectionKey = "section"
        val dimensionsKey = customProductDescriptionKey + "dimensions"
        val productDescriptionKey = "description"
        val countOfSectionAndParagraphs = 9

        // Grab the custom product description
        try {
            when {
                itemBody.contains(CUSTOM_FIELDS_KEY) -> {
                    @Suppress("UNCHECKED_CAST")
                    val customFieldsBody = itemBody[CUSTOM_FIELDS_KEY] as Map<String, *>

                    if (customFieldsBody.containsKey(dimensionsKey) && (customFieldsBody[dimensionsKey] as String).isNotEmpty()) {
                        descriptions["dimensions"] = customFieldsBody[dimensionsKey] as String
                    }

                    for (x in 1..countOfSectionAndParagraphs) {
                        val headKey = headKeyPrefix + x
                        val paragraphKey = paragraphKeyPrefix + x

                        var head: String? = null
                        var paragraph: String? = null

                        if (customFieldsBody.containsKey(headKey) && (customFieldsBody[headKey] as String).isNotEmpty()) {
                            head = customFieldsBody[headKey] as String
                        }

                        if (customFieldsBody.containsKey(paragraphKey) && (customFieldsBody[paragraphKey] as String).isNotEmpty()) {
                            paragraph = customFieldsBody[paragraphKey] as String
                        }

                        if (head != null || paragraph != null) {
                            descriptions["$sectionKey$x"] = DescriptionSectionDto(head, paragraph)
                        }
                    }
                }
            }

            // Revert to original product description, because custom product description either is not set or failed
            // to parse
            if (descriptions.isEmpty()
                && itemBody.containsKey(productDescriptionKey)
                && itemBody[productDescriptionKey] as String? != null
                && (itemBody[productDescriptionKey] as String).isNotEmpty()
            ) {
                descriptions[sectionKey + "1"] =
                    DescriptionSectionDto(mapProductName(itemBody), itemBody[productDescriptionKey] as String)
            }
        } catch (e: Exception) {
            e.printStackTrace()

            val message = "Failed to map product description response to Dto: ${e.message}"
            logger.error(message)

            throw BadRequestException(e.message)
        }

        return descriptions
    }

    /**
     * Grab the product name.
     */
    private fun mapProductName(itemBody: Map<*, *>): String? {
        var productName: String? = null
        val originalProductNameKey = "name"

        try {
            if (itemBody.containsKey(originalProductNameKey)) {
                productName = itemBody[originalProductNameKey] as String?
            }
        } catch (e: Exception) {
            e.printStackTrace()

            val message = "Failed to map product name response to Dto: ${e.message}"
            logger.error(message)

            throw BadRequestException(e.message)
        }

        return productName
    }

    /**
     * Grab the custom SEO friendly custom field.
     */
    private fun mapSeoFriendlyProductName(itemBody: Map<*, *>): String? {
        var seoFriendlyProductName: String? = null
        val customSeoProductTitle = "custom_product_description_seo_title"

        try {
            // grab SEO friendly product name
            if (itemBody.contains(CUSTOM_FIELDS_KEY)) {
                @Suppress("UNCHECKED_CAST")
                val customFieldsBody = itemBody[CUSTOM_FIELDS_KEY] as Map<String, *>

                if (customFieldsBody.containsKey(customSeoProductTitle)
                    && (customFieldsBody[customSeoProductTitle] as String).isNotEmpty()
                ) {
                    seoFriendlyProductName = customFieldsBody[customSeoProductTitle] as String
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()

            val message = "Failed to map product sea product name response to Dto: ${e.message}"
            logger.error(message)

            throw BadRequestException(e.message)
        }

        return seoFriendlyProductName
    }

    /**
     * Map the quantityAvailable per store when using multi store id querying
     */
    private fun mapStoreQuantities(itemBody: Map<*, *>): List<StoreQuantityDto>? {
        val storeQuantitiesDtos: MutableList<StoreQuantityDto> = mutableListOf()

        try {
            if (itemBody.containsKey(STORE_QUANTITIES_KEY)
            ) {
                @Suppress("UNCHECKED_CAST")
                val storeQuantities = itemBody.get(key = STORE_QUANTITIES_KEY) as List<Map<*, *>>

                storeQuantities.forEach {
                    try {
                        when {
                            it.containsKey("store_id") && it.containsKey("rental_quantity_available") -> {
                                val storeId = (it["store_id"] as Double?)?.toInt()

                                val rentalQuantityAvailable = when {
                                    it["rental_quantity_available"].toString() as String? != null
                                            && it["rental_quantity_available"].toString() as String? != "null" -> {
                                        it["rental_quantity_available"].toString()
                                    }
                                    else ->
                                        "0.0"
                                }

                                when {
                                    storeId != null ->
                                        storeQuantitiesDtos.add(StoreQuantityDto(storeId, rentalQuantityAvailable))
                                }
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()

                        val message = "Failed to map product store quantities to Dto: ${e.message}"
                        logger.error(message)

                        throw BadRequestException(e.message)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()

            val message = "Failed to map store quantities response to Dto: ${e.message}"
            logger.error(message)

            throw BadRequestException(e.message)
        }

        return storeQuantitiesDtos
    }
}

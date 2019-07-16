package com.oceanpremium.api.core.currentrms.response.dto.product

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.oceanpremium.api.core.model.ConfigProperty
import com.oceanpremium.api.core.model.ConfigPropertyField

@JsonPropertyOrder(
    "id",
    "name",
    "seoFriendlyName",
    "type",
    "description",
    "rates",
    "images",
    "attachments",
    "accessories",
    "storeQuantities"
)
@JsonInclude(JsonInclude.Include.NON_NULL)
class ProductDto(
    val id: Int?,
    val name: String?,
    val description: Map<*,*>,
    var type: String? = null,
    val productGroup: ProductGroupDto?,
    val rates: List<PricingDto>,
    val images: List<ImageSource>,
    var customFields: ProductCustomFieldsDto? = null,
    val attachments: List<AttachmentDto>? = null,
    var storeQuantities: List<StoreQuantityDto>? = null,
    @JsonIgnore
    var rawConfigurationIds: List<ConfigPropertyField>? = null,
    @JsonIgnore
    var configurations: List<ConfigProperty>? = null,
    var accessories: List<ProductDto>? = null,
    var seoFriendlyName: String? = name
)

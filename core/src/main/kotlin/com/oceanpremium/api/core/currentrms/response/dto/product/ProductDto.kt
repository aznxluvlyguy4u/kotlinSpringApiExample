package com.oceanpremium.api.core.currentrms.response.dto.product

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.oceanpremium.api.core.currentrms.response.dto.config.ConfigProperty
import com.oceanpremium.api.core.currentrms.response.dto.config.ConfigPropertyField

@JsonInclude(JsonInclude.Include.NON_NULL)
class AccessoryItem(
    val id: Int,
    val type: String,
    val quantity: String? = null
)

@JsonInclude(JsonInclude.Include.NON_NULL)
class ProductDto(
    val id: Int?,
    val name: String?,
    @JsonIgnore
    val description: DescriptionDto,
    var type: String? = null,
    val productGroup: ProductGroupDto?,
    val rates: List<PricingDto>,
    val images: List<ImageSource>,
    var customFields: ProductCustomFieldsDto? = null,
    @JsonIgnore
    val accesoryIds: List<AccessoryItem>? = null,
    val attachments: List<AttachmentDto>? = null,
    @JsonIgnore
    var rawConfigurationIds: List<ConfigPropertyField>? = null,
    var configurations: List<ConfigProperty>? = null
) {
    var accessories: MutableList<ProductDto>? = null
}

package com.oceanpremium.api.core.currentrms.response.dto.product

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude

class AccessoryItem(
    val id: Int,
    val type: String
)

@JsonInclude(JsonInclude.Include.NON_NULL)
class ProductDto(
    val id: Int?,
    val name: String?,
    val description: String?,
    var type: String? = null,
    val productGroup: ProductGroupDto?,
    val rates: List<PricingDto>,
    val images: List<ImageSource>,
    var customFields: ProductCustomFieldsDto? = null,
    @JsonIgnore
    val accesoryIds: List<AccessoryItem>? = null
) {
    var accessories: MutableList<ProductDto>? = null
}

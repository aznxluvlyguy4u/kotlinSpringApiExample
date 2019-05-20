package com.oceanpremium.api.core.currentrms.response.dto.product

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class ProductDto(
    val id: Int?,
    val name: String?,
    val description: String?,
    val productGroup: ProductGroupDto?,
    val rates: List<PricingDto>,
    val images: List<ImageSource>,
    var customFields: ProductCustomFieldsDto? = null,
    var accessories: List<ProductAccessoryDto>?
)

@JsonInclude(JsonInclude.Include.NON_NULL)
class ProductAccessoryDto(
    val id: Int?,
    val name: String?,
    val description: String?,
    val inclusionType: String?,
    val rates: List<PricingDto>,
    val images: List<ImageSource>,
    var customFields: ProductCustomFieldsDto? = null
)

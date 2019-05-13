package com.oceanpremium.api.core.currentrms.response.dto.product

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class ImageSource(val fullImageUrl: String? = null, val thumbnailUrl: String? = null)

@JsonInclude(JsonInclude.Include.NON_NULL)
class ImageDto(val sources: List<ImageSource>)

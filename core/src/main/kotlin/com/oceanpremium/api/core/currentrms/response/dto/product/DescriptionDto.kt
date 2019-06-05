package com.oceanpremium.api.core.currentrms.response.dto.product

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class DescriptionDto(val heading1: String? = null, val heading2: String? = null, val list: List<String>? = null)

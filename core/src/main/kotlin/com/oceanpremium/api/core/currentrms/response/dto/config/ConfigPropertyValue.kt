package com.oceanpremium.api.core.currentrms.response.dto.config

class ConfigProperty(val id: Int? = null, val name: String? = null, val values: List<ConfigPropertyValue>? = null)

class ConfigPropertyValue(val id: Int? = null, val name: String? = null)

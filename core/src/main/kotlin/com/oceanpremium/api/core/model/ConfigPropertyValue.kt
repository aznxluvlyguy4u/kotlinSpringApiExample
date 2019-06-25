package com.oceanpremium.api.core.model

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class ConfigPropertyField(val fieldName: String, var ids: List<Int>? = null)

@JsonInclude(JsonInclude.Include.NON_NULL)
class ConfigProperty(var id: Int? = null, var name: String? = null, var values: List<ConfigPropertyValue>? = null)

@JsonInclude(JsonInclude.Include.NON_NULL)
class ConfigPropertyValue(var id: Int? = null, var name: String? = null)

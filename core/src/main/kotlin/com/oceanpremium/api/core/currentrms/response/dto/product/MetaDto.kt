package com.oceanpremium.api.core.currentrms.response.dto.product

import com.fasterxml.jackson.annotation.JsonProperty

class MetaDto(
    @JsonProperty("total_row_count")
    var totalRowCount: Int = 0,

    @JsonProperty("row_count")
    var rowCount: Int = 0,

    var page: Int = 0,

    @JsonProperty("per_page")
    var perPage: Int = 0
)
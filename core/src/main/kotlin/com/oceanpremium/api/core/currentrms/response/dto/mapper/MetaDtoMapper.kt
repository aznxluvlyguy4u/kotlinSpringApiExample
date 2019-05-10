package com.oceanpremium.api.core.currentrms.response.dto.mapper

import com.fasterxml.jackson.annotation.JsonInclude
import retrofit2.Response

@JsonInclude(JsonInclude.Include.NON_NULL)
class MetaDtoMapper(response: Response<Any>?) {
    var total_row_count: Int = 0
    var row_count: Int = 0
    var page: Int = 0
    var per_page: Int = 0

    init {
        mapToDto(response)
    }

    private fun mapToDto(response: Response<Any>?) {

        if(response?.body() == null) {
            return
        }

        val responseBody = response?.body() as Map<String, Any>

        when {
            responseBody.containsKey("meta") -> {
                val metaBody = responseBody["meta"] as Map<String, Any>

                if(metaBody.containsKey("total_row_count")) {
                    total_row_count = (metaBody["total_row_count"] as Double).toInt()
                }

                if(metaBody.containsKey("row_count")) {
                    row_count = (metaBody["row_count"] as Double).toInt()
                }

                if(metaBody.containsKey("page")) {
                    page = (metaBody["page"] as Double).toInt()
                }

                if(metaBody.containsKey("per_page")) {
                    per_page = (metaBody["per_page"] as Double).toInt()
                }

            }
        }
    }
}
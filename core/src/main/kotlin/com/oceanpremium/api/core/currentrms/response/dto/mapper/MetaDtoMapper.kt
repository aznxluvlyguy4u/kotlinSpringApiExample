package com.oceanpremium.api.core.currentrms.response.dto.mapper

import com.fasterxml.jackson.annotation.JsonInclude
import com.oceanpremium.api.core.currentrms.response.dto.product.MetaDto
import retrofit2.Response

@JsonInclude(JsonInclude.Include.NON_NULL)
class MetaDtoMapper(response: Response<Any>?) {

    var meta: Any? = null
    var overrideHttpStatus: Boolean = false

    init {
        /**
         * The root node of the response payload that will contain the response result for meta key
         */
        meta = mapToDto(response)
    }

    private fun mapToDto(response: Response<Any>?): Any {
        val responseBody = response?.body() as Map<*, *>
        var totalRowCount: Int = 0
        var rowCount: Int = 0
        var page: Int = 0
        var perPage: Int = 0

        when {
            response.body() != null -> {
                when {
                    responseBody.containsKey("meta") -> {
                        val metaBody = responseBody["meta"] as Map<*, *>
                        when {
                            metaBody.containsKey("total_row_count") -> totalRowCount = (metaBody["total_row_count"] as Double).toInt()
                        }

                        when {
                            metaBody.containsKey("row_count") -> rowCount = (metaBody["row_count"] as Double).toInt()
                        }

                        when {
                            metaBody.containsKey("page") -> page = (metaBody["page"] as Double).toInt()
                        }

                        when {
                            metaBody.containsKey("per_page") -> perPage = (metaBody["per_page"] as Double).toInt()
                        }

                    }
                }
            }
        }

        /**
         *
         * In case a result SET (array) of items is returned, current rms returns a meta object containing details about
         * pagination and the size of the result set. Utilize the meta object to check on rowCount and determine if
         * the result set is empty or not.
         */
        when (rowCount) {
            0 -> overrideHttpStatus = true
        }

        return MetaDto(totalRowCount, rowCount, page, perPage)
    }
}
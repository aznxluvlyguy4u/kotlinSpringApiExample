package com.oceanpremium.api.core.currentrms.response.dto.mapper

import com.fasterxml.jackson.annotation.JsonInclude
import com.oceanpremium.api.core.currentrms.response.dto.product.MetaDto
import com.oceanpremium.api.core.exception.throwable.BadRequestException
import org.slf4j.LoggerFactory
import retrofit2.Response

@JsonInclude(JsonInclude.Include.NON_NULL)
class MetaDtoMapper(response: Response<Any>?) {

    var meta: Any? = null
    var overrideHttpStatus: Boolean = false

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    init {
        /**
         * The root node of the response payload that will contain the response result for meta key
         */
        meta = mapToDto(response)
    }

    private fun mapToDto(response: Response<Any>?): Any {
        val responseBody = response?.body() as Map<*, *>
        var totalRowCount = 0
        var rowCount = 0
        var page = 0
        var perPage = 0

        when {
            response.body() != null -> {
                try {
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
                } catch (e: Exception) {
                    e.printStackTrace()

                    val message = "Failed to map product META response to Dto: ${e.message}"
                    logger.error(message)

                    throw BadRequestException(e.message)
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
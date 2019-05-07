package com.oceanpremium.api.core.currentrms.response

import com.oceanpremium.api.core.enum.HTTPStatusCodeRange
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

/**
 * Response class that will contain response body.
 *
 */

/**
 * Response builder that will create a wrapped response based on input code and data.
 *
 * Current RMS API uses the standard HTML status codes as follows:
 *
 * - 200 OK
 * Standard response for successful HTTP requests. The actual response will depend on the request method used.
 * In a GET request, the response will contain an entity corresponding to the requested resource.
 * In a POST request, the response will contain an entity describing or containing the result of the action.
 *
 * - 204 No Content
 * The server successfully processed the request, but is not returning any content.
 *
 * - 400 Bad Request
 * Your request was badly formed - this is usually an error in your JSON formatting
 *
 * - 401 Unauthorized
 * Specifically used when authentication is required and has failed or has not been provided.
 *
 * - 404 Not Found
 * You requested a record that does not exist or used a URL that is not recognised.
 *
 * - 422 Unprocessable Entity
 * The request was well-formed but was unable to be followed due to semantic errors.
 */
class CurrentRmsApiResponse(body: Any?, status: HttpStatus) : ResponseEntity<Any>(body, status) {
    companion object {
        inline fun build(block: Builder.() -> Unit) = Builder().apply(block).build()
    }

    /**
     * Builder for creating a ApiGatewayResponse.
     */
    class Builder {

        private val logger = LoggerFactory.getLogger(this::class.java)
        private var rawBody: Map<String, Any>? = null

        var statusCode: HttpStatus = HttpStatus.OK
        var objectBody: Any? = null

        fun build(): ResponseEntity<*> {
            return buildResponse(statusCode, objectBody)
        }

        @Throws(Exception::class)
        private fun buildResponse(statusCode: HttpStatus, objectBody: Any?): ResponseEntity<*> {

            return when {
                // HTTP 1.x.x - informational
                statusCode.value() >= HTTPStatusCodeRange.INFORMATIONAL.code && statusCode.value() < HTTPStatusCodeRange.SUCCESS.code -> {
                    logger.debug("Setting up INFORMATIONAL response")

                    try {
                        if (objectBody == null) {
                            buildSuccessResponse(statusCode, statusCode.name)
                        } else {
                            buildSuccessResponse(statusCode, objectBody)
                        }
                    } catch (e: Exception) {
                        logger.error("Failed to build INFORMATIONAL response: $e")
                    } as ResponseEntity<*>
                }

                // HTTP 2.x.x - success's
                statusCode.value() >= HTTPStatusCodeRange.SUCCESS.code && statusCode.value() < HTTPStatusCodeRange.REDIRECT.code -> {
                    logger.debug("Setting up SUCCESS response")

                    try {
                        if (objectBody == null) {
                            buildSuccessResponse(statusCode, statusCode.name)
                        } else {
                            val isEmptyResult= isResultEmpty(objectBody as Map<*, *>)

                            if (isEmptyResult) {
                                return buildSuccessResponse(HttpStatus.NOT_FOUND, objectBody)
                            }

                            buildSuccessResponse(statusCode, objectBody)
                        }
                    } catch (e: Exception) {
                        logger.error("Failed to build SUCCESS response: $e")
                    } as ResponseEntity<*>
                }

                // HTTP 3.x.x - redirects
                statusCode.value() >= HTTPStatusCodeRange.REDIRECT.code && statusCode.value() < HTTPStatusCodeRange.CLIENT_ERROR.code -> {
                    logger.debug("Setting up REDIRECT response")

                    try {
                        if (objectBody == null) {
                            buildSuccessResponse(statusCode, statusCode.name)
                        } else {
                            buildSuccessResponse(statusCode, objectBody)
                        }
                    } catch (e: Exception) {
                        logger.error("Failed to build REDIRECT response: $e")
                    } as ResponseEntity<*>
                }

                // HTTP 4.x.x - client errors
                statusCode.value() >= HTTPStatusCodeRange.CLIENT_ERROR.code && statusCode.value() < HTTPStatusCodeRange.SERVER_ERROR.code -> {
                    logger.debug("Setting up CLIENT ERROR response")

                    try {
                        if (objectBody == null) {
                            buildErrorResponse(statusCode, statusCode.name)
                        } else {
                            buildErrorResponse(statusCode, objectBody as String)
                        }

                    } catch (e: Exception) {
                        logger.error("Failed to build CLIENT ERROR response: $e")
                    } as ResponseEntity<*>
                }

                // HTTP 5.x.x - server errors
                statusCode.value() >= HTTPStatusCodeRange.SERVER_ERROR.code && statusCode.value() < HTTPStatusCodeRange.CUSTOM.code -> {
                    logger.debug("Setting up SERVER ERROR response")

                    try {
                        if (objectBody == null) {
                            buildErrorResponse(statusCode, statusCode.name)
                        } else {
                            buildErrorResponse(statusCode, objectBody as String)
                        }
                    } catch (e: Exception) {
                        logger.error("Failed to build SERVER ERROR response: $e")
                    } as ResponseEntity<*>
                }

                //HTTP 6.x.x - customs
                statusCode.value() >= HTTPStatusCodeRange.CUSTOM.code -> {
                    logger.debug("Setting up CUSTOM response")

                    try {
                        if (objectBody == null) {
                            buildSuccessResponse(statusCode, statusCode.name)
                        } else {
                            buildSuccessResponse(statusCode, objectBody)
                        }
                    } catch (e: Exception) {
                        logger.error("Failed to build CUSTOM response: $e")
                    } as ResponseEntity<*>
                }

                /**
                 * See {@link io.sheep.enumerator.HTTPStatusRange} for supported HTTP status codes
                 */
                else -> {
                    throw Exception(
                        "Not a supported HTTP status code: $statusCode, either use supported HTTP Status Codes " +
                                "or setup a custom HTTP Status code in 6.x.x range and register it in the HTTPStatusCode enum"
                    )
                }
            }
        }


        /**
         * CurrentRMS API returns 200 OK when a resource is not found / a result set is empty
         * Proper API response code would be 404 NOT FOUND, therefore overriding a request that
         * responds with 200 OK but has not result.
         */
        private fun isResultEmpty(objectBody: Map<*, *>): Boolean {

            val metaKey = "meta"
            val rowCountKey = "row_count"

            if (objectBody.containsKey(metaKey)) {
                val meta = objectBody[metaKey] as Map<*, *>

                if (meta.containsKey(rowCountKey)) {
                    return when (meta[rowCountKey] as Double) {
                        0.0 -> {
                            true
                        }
                        else -> {
                            false
                        }
                    }
                }
            }

            return false
        }

        /**
         * Build a response for 1.x.x, 2.x.x, 3.x.x and 6.x.x range.
         */
        @Throws(RuntimeException::class)
        private fun buildSuccessResponse(
            statusCode: HttpStatus,
            objectBody: Any?
        ): ResponseEntity<Any> {
            var body: Map<String, Any>? = null

            when {
                rawBody != null -> body = rawBody

                objectBody != null ->
                    body = mapOf("statusCode" to  statusCode.value(), "data" to objectBody)
            }

            return ResponseEntity(body as Any, statusCode)
        }

        /**
         * Build a response for 4.xx and 5.x.x range.
         */
        @Throws(RuntimeException::class)
        private fun buildErrorResponse(
            statusCode: HttpStatus,
            objectBody: String?
        ): ResponseEntity<Any> {
            val body = mapOf("statusCode" to  statusCode.value(), "errorMessage" to objectBody)
            return ResponseEntity(body as Any, statusCode)
        }
    }
}

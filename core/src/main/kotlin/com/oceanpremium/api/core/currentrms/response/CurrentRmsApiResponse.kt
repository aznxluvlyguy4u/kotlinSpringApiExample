package com.oceanpremium.api.core.currentrms.response

import com.fasterxml.jackson.annotation.JsonInclude
import com.oceanpremium.api.core.enum.HTTPStatusCodeRange
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import retrofit2.Response

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
        var statusCode: HttpStatus = HttpStatus.OK
        var rawResponse: Response<Any>? = null
        var dtoData: Any? = null
        var dtoMeta: Any? = null
        var error: Exception? = null

        fun build(): ResponseEntity<*> {
            return buildResponse(statusCode, rawResponse, dtoData, dtoMeta, error)
        }

        @Throws(Exception::class)
        private fun buildResponse(
            statusCode: HttpStatus,
            rawResponse:  Response<Any>?,
            dtoData: Any? = null,
            dtoMeta: Any? = null,
            error: Exception? = null
        ): ResponseEntity<*> {

            return when {

                // HTTP 2xx - success's
                statusCode.value() >= HTTPStatusCodeRange.SUCCESS.code && statusCode.value() < HTTPStatusCodeRange.REDIRECT.code -> {
                    logger.debug("Setting up SUCCESS response")

                     buildSuccessResponse(statusCode, rawResponse, dtoData, dtoMeta)
                }

                // HTTP 4xx - client errors and 5xx server errors
                statusCode.value() >= HTTPStatusCodeRange.CLIENT_ERROR.code
                        && statusCode.value() < HTTPStatusCodeRange.SERVER_ERROR.code ||
                        statusCode.value() >= HTTPStatusCodeRange.SERVER_ERROR.code
                        && statusCode.value() < HTTPStatusCodeRange.CUSTOM.code -> {
                    logger.debug("Setting up ERROR response")

                     when (rawResponse) {
                        null -> if (error != null) {
                            buildErrorResponse(statusCode = statusCode, message = error, rawResponse = null)
                        } else {
                            buildErrorResponse(statusCode = statusCode, message = Exception(statusCode.reasonPhrase), rawResponse = null)
                        }
                        else -> buildErrorResponse(statusCode = statusCode, message = error, rawResponse = rawResponse)
                    }
                }

                /**
                 * See {@link io.sheep.enumerator.HTTPStatusRange} for supported HTTP status codes
                 */
                else -> {
                    throw Exception("Not a supported HTTP status code: $statusCode, either use supported HTTP Status Codes")
                }
            }
        }

        /**
         * Build a response for Informationals: 1xx, OK's: 2xx and Redirects: 3xx
         *
         * See @link: https://en.wikipedia.org/wiki/List_of_HTTP_status_codes
         */
        @Throws(RuntimeException::class)
        private fun buildSuccessResponse(
            statusCode: HttpStatus,
            rawResponse: Response<Any>?,
            dtoData: Any? = null,
            dtoMeta: Any? = null
        ): ResponseEntity<Any> {
            val wrappedBody = WrappedResponse(code = statusCode.value(), data = rawResponse, meta = dtoMeta)

            if (dtoData != null ) {
                wrappedBody.data = dtoData
            }

            return ResponseEntity(wrappedBody, statusCode)
        }

        /**
         * Build a response for Client errors: 4xx and Server errors: 5xx.
         *
         * See @link: https://en.wikipedia.org/wiki/List_of_HTTP_status_codes
         */
        @Throws(RuntimeException::class)
        private fun buildErrorResponse(
            statusCode: HttpStatus,
            rawResponse: Response<Any>?,
            message: Exception?
            ): ResponseEntity<Any> {

            val wrappedBody = when {
                message != null -> WrappedResponse(code = statusCode.value(), error = ErrorMessage(statusCode.value(), message))
                rawResponse?.message() != null -> WrappedResponse(code = statusCode.value(), error = ErrorMessage(statusCode.value(), Exception(rawResponse.message())))
                else -> WrappedResponse(code = statusCode.value(), error = ErrorMessage(statusCode.value(), Exception("Something went wrong, please try again.")))
            }

            return ResponseEntity(wrappedBody, statusCode)
        }
    }
}

@JsonInclude(JsonInclude.Include.NON_NULL)
class ErrorMessage(errorCode: Int, errorMessage: Exception)

@JsonInclude(JsonInclude.Include.NON_NULL)
class WrappedResponse(
    val code: Int,
    var data: Any? = null,
    var meta: Any? = null,
    var error: ErrorMessage? = ErrorMessage(418, Exception("418 - Im' a Teapot"))
)
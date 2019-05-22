package com.oceanpremium.api.core.currentrms.response

import com.fasterxml.jackson.annotation.JsonInclude
import com.oceanpremium.api.core.currentrms.response.dto.mapper.CurrentRmsBaseDtoMapper
import com.oceanpremium.api.core.currentrms.response.dto.mapper.ErrorResponse
import com.oceanpremium.api.core.enum.HTTPStatusCodeRange
import com.oceanpremium.api.core.exception.handler.ApiError
import com.oceanpremium.api.core.exception.throwable.BadRequestException
import com.oceanpremium.api.core.exception.throwable.NotFoundException
import com.oceanpremium.api.core.exception.throwable.UnauthorizedException
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
 *
 * - 429 Too many requests
 *  Rate limit has been reached, and requests are discarded, check the 'X-RateLimit-Reset' header for
 *  when the limit expires and retrying requests can be done.
 */
class CurrentRmsApiResponse(body: Any?, status: HttpStatus) : ResponseEntity<Any>(body, status) {

    companion object {
        inline fun build(block: Builder.() -> Unit) = Builder().apply(block).build()
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    internal class WrappedResponse(
        val code: Int,
        var data: Any? = null,
        var meta: Any? = null
    )

    /**
     * Builder for creating a ApiGatewayResponse.
     */
    class Builder {

        private val logger = LoggerFactory.getLogger(this::class.java)
        var rawResponse: Response<Any>? = null
        var dtoMapper: CurrentRmsBaseDtoMapper? = null
        var error: ApiError? = null

        fun build(): ResponseEntity<*> {
            return buildResponse(rawResponse, dtoMapper, error)
        }

        @Throws(Exception::class, UnauthorizedException::class, BadRequestException::class)
        private fun buildResponse(
            rawResponse: Response<Any>?,
            dtoMapper: CurrentRmsBaseDtoMapper? = null,
            error: ApiError? = null
        ): ResponseEntity<*> {

            val statusCode = dtoMapper?.httpStatus!!

            return when {

                // HTTP 2xx - success's
                statusCode.value() >= HTTPStatusCodeRange.SUCCESS.code
                        && statusCode.value() < HTTPStatusCodeRange.REDIRECT.code -> {

                    /**
                     * Override the Current RMS API response with a 404 , because it returned an empty result set.
                     */
                    if (dtoMapper.httpStatus == HttpStatus.NOT_FOUND) {

                        var errorResponse: ApiError? = error

                        when (error) {
                            null -> if (dtoMapper.error  != null) {
                                errorResponse = dtoMapper.error
                            }
                        }

                        buildErrorResponse(
                            statusCode = dtoMapper.httpStatus,
                            error = errorResponse,
                            rawResponse = rawResponse
                        )
                    }

                    logger.debug("Setting up SUCCESS response")
                    buildSuccessResponse(rawResponse, dtoMapper)
                }

                // HTTP 4xx - client errors and 5xx server errors
                statusCode.value() >= HTTPStatusCodeRange.CLIENT_ERROR.code
                        && statusCode.value() < HTTPStatusCodeRange.SERVER_ERROR.code ||
                        statusCode.value() >= HTTPStatusCodeRange.SERVER_ERROR.code
                        && statusCode.value() < HTTPStatusCodeRange.CUSTOM.code -> {

                    logger.debug("Setting up ERROR response")

                    when (rawResponse) {
                        null ->
                            if (error != null) {
                                buildErrorResponse(statusCode = statusCode, error = error, rawResponse = null)
                            } else {

                                val errorResponse = ErrorResponse()
                                errorResponse.errors.add(statusCode.reasonPhrase)

                                buildErrorResponse(
                                    statusCode = statusCode,
                                    error = ApiError(code = statusCode.value(), exception = NotFoundException(), message = errorResponse),
                                    rawResponse = null
                                )
                            }
                        else -> buildErrorResponse(
                            statusCode = statusCode,
                            error = error,
                            rawResponse = rawResponse
                        )
                    }
                }

                /**
                 * See {@link HTTPStatusRange} for supported HTTP status codes
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
            rawResponse: Response<Any>?,
            dtoMapper: CurrentRmsBaseDtoMapper
        ): ResponseEntity<Any> {
            val statusCode = dtoMapper.httpStatus
            val wrappedBody =
                WrappedResponse(code = dtoMapper.httpStatus.value(), data = rawResponse, meta = dtoMapper.meta)

            if (dtoMapper.data != null) {
                wrappedBody.data = dtoMapper.data
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
            error: ApiError?
        ): ResponseEntity<Any> {
            val wrappedBody = when {

                error != null -> {
                    logger.debug("Error not null returning passed error as response: $error")

                    error
                }

                rawResponse?.errorBody() != null -> {
                    logger.debug("No error object passed, setting rawResponse errorBody: $rawResponse.message()")

                    val errorResponse = ErrorResponse()
                    errorResponse.errors.add(rawResponse.message())

                    ApiError(code = statusCode.value(), message = errorResponse)
                }

                rawResponse?.message() != null -> {
                    logger.debug("No error object passed, setting rawResponse errorMessage: $rawResponse.message()")

                    val errorResponse = ErrorResponse()
                    errorResponse.errors.add(rawResponse.message())
                    ApiError(code = statusCode.value(), exception = NotFoundException(), message = errorResponse)
                }

                else -> {
                    logger.debug("No error object passed or raw error response available, http reason phrase as error message: ${HttpStatus.valueOf(rawResponse?.code()!!).reasonPhrase}")

                    val errorResponse = ErrorResponse()
                    errorResponse.errors.add("Error ${HttpStatus.valueOf(rawResponse.code()).reasonPhrase}")

                    ApiError(
                        code = statusCode.value(),
                        exception = NotFoundException(),
                        message = errorResponse
                    )
                }
            }

            return ResponseEntity(wrappedBody, statusCode)
        }

    }
}

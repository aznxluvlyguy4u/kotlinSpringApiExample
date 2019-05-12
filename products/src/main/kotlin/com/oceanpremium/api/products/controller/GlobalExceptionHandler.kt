package com.oceanpremium.api.products.controller

import com.fasterxml.jackson.annotation.JsonInclude
import com.oceanpremium.api.core.enum.EnvironmentType
import com.oceanpremium.api.core.exception.BadRequestException
import com.oceanpremium.api.core.exception.NotFoundException
import com.oceanpremium.api.core.exception.ServerErrorException
import com.oceanpremium.api.core.exception.UnauthorizedException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ApiError(var code: Int? = null, var exception: Any? = null, var message: String? = null)

/**
 * Exception handler that provides handling for exceptions thrown throughout the API.
 */
@ControllerAdvice
class GlobalExceptionHandler {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
        private const val ENVIRONMENT = "env"
        private var env: String? = null
        private var showStacktrace: Boolean = false
    }

    init {
        env = System.getenv(ENVIRONMENT) ?: "dev"

        when {
            env != null -> if(env != EnvironmentType.PRODUCTION.type) {

               // show stacktrace when returning an error response object
                showStacktrace = true

                logger.debug("Exception stacktrace returning is: $showStacktrace")
            }
        }
    }

    /**
     * Catch unauthorized exception, and return a custom error response.
     */
    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(ex: Exception, request: WebRequest): ResponseEntity<ApiError> {
        logger.debug("Build 404 NOT FOUND response")
        val status = HttpStatus.NOT_FOUND
        val apiError = when {
            showStacktrace -> ApiError(status.value(), ex, status.reasonPhrase)
            else -> ApiError(code = status.value(), message = status.reasonPhrase)
        }


        return ResponseEntity(apiError, status)
    }

    /**
     * Catch unauthorized exception, and return a custom error response.
     */
    @ExceptionHandler(UnauthorizedException::class)
    fun handleUnauthorizedException(ex: Exception, request: WebRequest): ResponseEntity<ApiError> {
        logger.debug("Build 401 Unauthorized response")
        val status = HttpStatus.UNAUTHORIZED
        val apiError = when {
            showStacktrace -> ApiError(status.value(), ex, status.reasonPhrase)
            else -> ApiError(code = status.value(), message = status.reasonPhrase)
        }
        return ResponseEntity(apiError, status)
    }

    /**
     * Catch bad request exception, and return a custom error response.
     */
    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequestException(ex: BadRequestException, request: WebRequest): ResponseEntity<ApiError> {
        logger.debug("Build 400 BAD REQUEST response")
        val status = HttpStatus.BAD_REQUEST
        val apiError = when {
            showStacktrace -> ApiError(status.value(), ex, status.reasonPhrase)
            else -> ApiError(code = status.value(), message = status.reasonPhrase)
        }
        return ResponseEntity(
            apiError,
            status
        )
    }

    /**
     * Catch internal server exception, and return a custom error response.
     */
    @ExceptionHandler(ServerErrorException::class)
    fun handleInternalServerErrorException(ex: ServerErrorException, request: WebRequest): ResponseEntity<ApiError> {
        logger.debug("Build 500 INTERNAL SERVER ERROR response")
        val status = HttpStatus.INTERNAL_SERVER_ERROR
        val apiError = when {
            showStacktrace -> ApiError(status.value(), ex, status.reasonPhrase)
            else -> ApiError(code = status.value(), message = status.reasonPhrase)
        }
        return ResponseEntity(
            apiError,
            status
        )
    }

    /**
     * Catch throwable, and return a custom error response.
     */
    @ExceptionHandler(Throwable::class)
    fun handleThrowable(ex: Throwable, request: WebRequest): ResponseEntity<ApiError> {
        logger.debug("Build general 500 INTERNAL SERVER ERROR response")

        val status = HttpStatus.INTERNAL_SERVER_ERROR
        val exception = ServerErrorException(ex.message)
        val apiError = when {
            showStacktrace -> ApiError(status.value(), exception, status.reasonPhrase)
            else -> ApiError(code = status.value(), message = status.reasonPhrase)
        }
        return ResponseEntity(
            apiError,
            status
        )
    }
}
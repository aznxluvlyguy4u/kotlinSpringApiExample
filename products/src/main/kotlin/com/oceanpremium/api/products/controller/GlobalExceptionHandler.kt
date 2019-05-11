package com.oceanpremium.api.products.controller

import com.oceanpremium.api.core.exception.BadRequestException
import com.oceanpremium.api.core.exception.NotFoundException
import com.oceanpremium.api.core.exception.ServerErrorException
import com.oceanpremium.api.core.exception.UnauthorizedException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

class ApiError(var code: Int? = null, var exception: Exception? = null, var message: String? = null)

/**
 * Exception handler that provides handling for exceptions thrown throughout the API.
 */
@ControllerAdvice
class GlobalExceptionHandler {

    /**
     * Catch unauthorized exception, and return a custom error response.
     */
    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(ex: Exception, request: WebRequest): ResponseEntity<ApiError> {
        val status = HttpStatus.NOT_FOUND
        val apiError = ApiError(status.value(), ex, status.reasonPhrase)

        return ResponseEntity(apiError, status)
    }

    /**
     * Catch unauthorized exception, and return a custom error response.
     */
    @ExceptionHandler(UnauthorizedException::class)
    fun handleUnauthorizedException(ex: Exception, request: WebRequest): ResponseEntity<ApiError> {
        val status = HttpStatus.UNAUTHORIZED
        val apiError = ApiError(status.value(), ex, status.reasonPhrase)

        return ResponseEntity(apiError, status)
    }

    /**
     * Catch bad request exception, and return a custom error response.
     */
    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequestException(ex: BadRequestException, request: WebRequest): ResponseEntity<ApiError> {
        val httpStatus = HttpStatus.BAD_REQUEST
        val apiError = ApiError(httpStatus.value(), ex, httpStatus.reasonPhrase)

        return ResponseEntity(
            apiError,
            httpStatus
        )
    }

    /**
     * Catch internal server exception, and return a custom error response.
     */
    @ExceptionHandler(ServerErrorException::class)
    fun handleInternalServerErrorException(ex: BadRequestException, request: WebRequest): ResponseEntity<ApiError> {
        val httpStatus = HttpStatus.INTERNAL_SERVER_ERROR
        val apiError = ApiError(httpStatus.value(), ex, httpStatus.reasonPhrase)

        return ResponseEntity(
            apiError,
            httpStatus
        )
    }

    /**
     * Catch throwable, and return a custom error response.
     */
    @ExceptionHandler(Throwable::class)
    fun handleThrowable(ex: Throwable, request: WebRequest): ResponseEntity<ApiError> {
        val httpStatus = HttpStatus.INTERNAL_SERVER_ERROR
        val exception = ServerErrorException(ex.message)
        val apiError = ApiError(httpStatus.value(), exception, httpStatus.reasonPhrase)

        return ResponseEntity(
            apiError,
            httpStatus
        )
    }
}
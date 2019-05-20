package com.oceanpremium.api.core.exception.throwable

/**
 * Code: 400 Bad Request
 * Description: The request was badly formed - this is usually an error in the JSON formatting
 */
class BadRequestException(override val message: String? = "The request was badly formed") : Exception(message)
package com.oceanpremium.api.core.exception.throwable

/**
 * Code: 404 Not Found
 * The requested resource does not exist.
 */
class NotFoundException(override val message: String? = "The requested resource does not exist") : Exception(message)
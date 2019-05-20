package com.oceanpremium.api.core.exception.throwable

/**
 * Code: 500 Internal Server Error
 * A fatal error occured in the api.
 */
class ServerErrorException(override val message: String? = "A fatal error occured") : Exception(message)
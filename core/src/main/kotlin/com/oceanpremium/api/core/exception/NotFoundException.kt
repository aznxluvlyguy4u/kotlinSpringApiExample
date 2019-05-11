package com.oceanpremium.api.core.exception

/**
 * Code: 404 Not Found
 * The requested resource does not exist.
 */
class NotFoundException(override val message: String? = "The requested resource does not exist") : Exception(message) {
    @Synchronized
    override fun fillInStackTrace(): Throwable {
        return this
    }
}
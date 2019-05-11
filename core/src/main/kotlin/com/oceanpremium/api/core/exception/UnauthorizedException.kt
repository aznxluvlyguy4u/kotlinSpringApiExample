package com.oceanpremium.api.core.exception

import com.oceanpremium.api.core.enum.AuthorizationType

/**
 * Code 401
 * The request was unauthorized, for example the credentials are invalid.
 * There are two types of authorizations: The API itself and a third-party API.
 */
class UnauthorizedException(override val message: String? = "The request was unauthorized, make sure to provide valid credentials", val type: AuthorizationType) : Exception(message) {
    @Synchronized
    override fun fillInStackTrace(): Throwable {
        return this
    }
}
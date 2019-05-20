package com.oceanpremium.api.core.exception.throwable

/**
 * Code: 429 Too Many requests
 * The rate limit of the API has kicked in and discards the requests.
 */
class TooManyRequestsException(
    override val message: String? = "Requests to the CurrentRMS API are rate limited, " +
            "requests are limited to a maximum of 60 requests over any 60 second period") : Exception(message)
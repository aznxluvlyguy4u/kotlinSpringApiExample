package com.oceanpremium.api.core.enum

/**
 * @link https://en.wikipedia.org/wiki/List_of_HTTP_status_codes
 */
enum class HTTPStatusCodeRange(val code: Int) {
    INFORMATIONAL(100),
    SUCCESS(200),
    REDIRECT(300),
    CLIENT_ERROR(400),
    SERVER_ERROR(500),

    /**
     * Use the 6.x.x range for setting up custom HTTP Status codes,
     * also register the custom code in the {@link io.sheep.enumerator.HTTPStatusCode} enum in 6.x.x range
     */
    CUSTOM(600)
}

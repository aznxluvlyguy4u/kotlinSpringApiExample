package com.oceanpremium.api.currentrms.client.interceptor

import okhttp3.*
import org.slf4j.LoggerFactory
import java.io.IOException

class CurrentRmsConfig(val accessToken: String, val subDomain: String, val baseApiUrl: String)

class CurrentRmsConfigInterceptor(private val currentRmsConfig: CurrentRmsConfig) : Interceptor, Authenticator {

    companion object {
        private const val CURRENT_RMS_AUTH_HEADER = "X-AUTH-TOKEN"
        private const val CURRENT_RMS_AUTH_SUBDOMAIN = "X-SUBDOMAIN"
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    /**
     * Interceptor class for setting of the headers for every request
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder().build()

        return chain.proceed(request)
    }

    /**
     * Authenticator for when the token need to be provided or refresh
     * everytime we get a 401 error code
     */
    @Throws(IOException::class)
    override fun authenticate(route: Route?, response: Response?): Request? {
        var requestAvailable: Request? = null
        try {
            requestAvailable = response?.request()?.newBuilder()
                ?.addHeader(CURRENT_RMS_AUTH_HEADER, currentRmsConfig.accessToken)
                ?.addHeader(CURRENT_RMS_AUTH_SUBDOMAIN, currentRmsConfig.subDomain)
                ?.build()

            return requestAvailable
        } catch (e: Exception) {
            logger.error("Failed to authenticate with CURRENT-RMS API: ${e.message}")
        }
        return requestAvailable
    }
}
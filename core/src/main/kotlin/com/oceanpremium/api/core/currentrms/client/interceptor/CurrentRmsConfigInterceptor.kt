package com.oceanpremium.api.core.currentrms.client.interceptor

import okhttp3.*

class CurrentRmsConfig(val accessToken: String, val subDomain: String, val baseApiUrl: String)

class CurrentRmsConfigInterceptor(private val currentRmsConfig: CurrentRmsConfig) : Interceptor {

    companion object {
        private const val CURRENT_RMS_AUTH_HEADER = "X-AUTH-TOKEN"
        private const val CURRENT_RMS_AUTH_SUBDOMAIN = "X-SUBDOMAIN"
    }

    /**
     * Interceptor class for setting of the headers for every request
     */
    override fun intercept(chain: Interceptor.Chain): Response {

        val original = chain.request()

        // Request customization: add request headers
        val requestBuilder = original.newBuilder()
            .addHeader(CURRENT_RMS_AUTH_HEADER, currentRmsConfig.accessToken)
            .addHeader(CURRENT_RMS_AUTH_SUBDOMAIN, currentRmsConfig.subDomain)

        val request = requestBuilder.build()

        return chain.proceed(request)
    }
}
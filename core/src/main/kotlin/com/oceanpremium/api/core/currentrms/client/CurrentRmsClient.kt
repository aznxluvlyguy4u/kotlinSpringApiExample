package com.oceanpremium.api.core.currentrms.client

import com.oceanpremium.api.core.currentrms.client.interceptor.CurrentRmsConfig
import com.oceanpremium.api.core.currentrms.client.interceptor.CurrentRmsConfigInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Service
class CurrentRmsClient {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
        private const val CURRENT_RMS_TOKEN = "current_rms_token"
        private const val CURRENT_RMS_SUBDOMAIN = "current_rms_subdomain"
        private const val CURRENT_RMS_API_URL = "current_rms_api_url"
        private const val REQUEST_TIMEOUT: Long = 20
    }

    private var retrofitClient: Retrofit? = null

    fun getRetrofitClient(): Retrofit {

        if (retrofitClient != null) {
            return retrofitClient!!
        }

        val currentRmsConfig = getCurrentRmsConfig()

        logger.debug("Build new retrofit client")
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val currentRmsConfigInterceptor = CurrentRmsConfigInterceptor(currentRmsConfig)

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        httpClient.connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
        httpClient.readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
        httpClient.addInterceptor(currentRmsConfigInterceptor)
        val okHttp = httpClient.build()

        retrofitClient = Retrofit.Builder()
            .baseUrl(currentRmsConfig.baseApiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp)
            .build()

        return retrofitClient!!
    }

    private fun getCurrentRmsConfig(): CurrentRmsConfig {
        return CurrentRmsConfig(
            System.getenv(CURRENT_RMS_TOKEN) ?: null ?: throw Exception("Env var: $CURRENT_RMS_TOKEN not set"),
            System.getenv(CURRENT_RMS_SUBDOMAIN) ?: null ?: throw Exception("Env var: $CURRENT_RMS_SUBDOMAIN not set"),
            System.getenv(CURRENT_RMS_API_URL) ?: null ?: throw Exception("Env var: $CURRENT_RMS_API_URL not set")
        )
    }
}
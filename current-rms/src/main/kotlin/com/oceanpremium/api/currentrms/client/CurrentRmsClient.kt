package com.oceanpremium.api.currentrms.client

import com.oceanpremium.api.currentrms.client.interceptor.CurrentRmsConfig
import com.oceanpremium.api.currentrms.client.interceptor.CurrentRmsConfigInterceptor
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
        private const val CURRENT_RMS_TOKEN = "CURRENT_RMS_TOKEN"
        private const val CURRENT_RMS_SUBDOMAIN = "CURRENT_RMS_SUBDOMAIN"
        private const val CURRENT_RMS_API_URL = "CURRENT_RMS_API_URL"
    }

    private var retrofitClient: Retrofit? = null

    fun getRetrofitClient(): Retrofit {

        if (retrofitClient != null) {
            return retrofitClient!!
        }

        val currentRmsConfig = getCurrentRmsConfig()

        logger.debug("Build new retrofit client")
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.HEADERS
        logging.level = HttpLoggingInterceptor.Level.BODY

        val currentRmsConfigInterceptor = CurrentRmsConfigInterceptor(currentRmsConfig)

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        httpClient.connectTimeout(20, TimeUnit.SECONDS)
        httpClient.readTimeout(20, TimeUnit.SECONDS)
        httpClient.addInterceptor(currentRmsConfigInterceptor)
        httpClient.authenticator(currentRmsConfigInterceptor)

        retrofitClient = Retrofit.Builder()
            .baseUrl(currentRmsConfig.baseApiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofitClient!!
    }

    private fun getCurrentRmsConfig(): CurrentRmsConfig {
        return CurrentRmsConfig(
            System.getenv(CURRENT_RMS_API_URL) ?: null ?: throw Exception("Env var: $CURRENT_RMS_API_URL not set"),
            System.getenv(CURRENT_RMS_TOKEN) ?: null ?: throw Exception("Env var: $CURRENT_RMS_TOKEN not set"),
            System.getenv(CURRENT_RMS_SUBDOMAIN) ?: null ?: throw Exception("Env var: $CURRENT_RMS_SUBDOMAIN not set")
        )
    }
}
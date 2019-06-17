package com.oceanpremium.api.core.ipdata

import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.slf4j.LoggerFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor

class IPDataConfig(val accessToken: String, val baseApiUrl: String)

class IPDataClient {
    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
        private const val IP_DATA_TOKEN = "geo_lookup_api_token"
        private const val IP_DATA_API_URL = "geo_lookup_api_url"
        private const val REQUEST_TIMEOUT: Long = 20
        // Set a constant for request throttling
        private const val MAX_CONCURRENT_REQUESTS = 100
    }

    private var retrofitClient: Retrofit? = null

    fun getRetrofitClient(): Retrofit {

        if (retrofitClient != null) {
            return retrofitClient!!
        }

        val ipDataConfig = getIpDataConfig()

        logger.debug("Build new retrofit client")
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.NONE

        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
        httpClientBuilder.readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
        httpClientBuilder.addInterceptor(logging)

        val threadPoolExecutor = ThreadPoolExecutor(MAX_CONCURRENT_REQUESTS, MAX_CONCURRENT_REQUESTS, 1, TimeUnit.MINUTES, LinkedBlockingQueue())
        val dispatcher = Dispatcher(threadPoolExecutor)
        httpClientBuilder.dispatcher(dispatcher)

        val okHttpClient = httpClientBuilder.build()
        okHttpClient.dispatcher().maxRequests = MAX_CONCURRENT_REQUESTS

        retrofitClient = Retrofit.Builder()
            .baseUrl(ipDataConfig.baseApiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        return retrofitClient!!
    }

    fun getIpDataConfig(): IPDataConfig {
        return IPDataConfig(
            System.getenv(IP_DATA_TOKEN) ?: null
            ?: throw Exception("Env var: $IP_DATA_TOKEN not set"),
            System.getenv(IP_DATA_API_URL) ?: null
            ?: throw Exception("Env var: $IP_DATA_API_URL not set")
        )
    }
}

package com.oceanpremium.api.core.currentrms

import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.slf4j.LoggerFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import java.io.IOException
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor

class CurrentRmsConfig(val accessToken: String, val subDomain: String, val baseApiUrl: String)

/**
 * Provides delay duration in milliseconds.
 **/
class DelayProvider(val delay: Long)

/**
 * Constructs [DelayInterceptor] where delay duration is set fixed on given initial value.
 *
 * @param delayProvider delay duration in milliseconds provider. Negative values provided by
 * this provider will cause no delay.
 */
class DelayInterceptor(private val delayProvider: DelayProvider) : Interceptor {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val delay = delayProvider.delay
        if (delay > 0) {
            try {
                logger.debug("Delay request for: ${delayProvider.delay} ms")
                Thread.sleep(delay)
            } catch (e: InterruptedException) {
                e.printStackTrace()
                logger.error(e.message)
            }
        }
        return chain.proceed(chain.request())
    }
}

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

class CurrentRmsClient {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
        private const val CURRENT_RMS_TOKEN = "current_rms_token"
        private const val CURRENT_RMS_SUBDOMAIN = "current_rms_subdomain"
        private const val CURRENT_RMS_API_URL = "current_rms_api_url"
        private const val REQUEST_TIMEOUT: Long = 20
        // Set a constant for request throttling
        private const val REQUEST_DELAY: Long = 0
        private const val MAX_CONCURRENT_REQUESTS = 100
    }

    private var retrofitClient: Retrofit? = null

    fun getRetrofitClient(): Retrofit {

        if (retrofitClient != null) {
            return retrofitClient!!
        }

        val currentRmsConfig = getCurrentRmsConfig()

        logger.debug("Build new retrofit client")

        /**
         * If you want to debug the outgoing HTTP requests, toggle the logging.level to, for example:
         *
         * Logs request and response lines and their respective headers and bodies (if present):
         *
         * - Level.HEADERS
         *
         * Logs request and response lines and their respective headers:
         *
         * - Level.BASIC
         *
         * Logs request and response lines and their respective headers and bodies (if present):
         *
         * - Level.BODY
         *
         * Default log level set (no logging):
         *
         *  - Level.NONE
         *
         * See also @link https://stackoverflow.com/questions/32514410/logging-with-retrofit-2
         */
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.NONE

        val currentRmsConfigInterceptor = CurrentRmsConfigInterceptor(currentRmsConfig)
        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
        httpClientBuilder.readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
        httpClientBuilder.addInterceptor(logging)
        httpClientBuilder.addInterceptor(currentRmsConfigInterceptor)
        httpClientBuilder.addInterceptor(DelayInterceptor(DelayProvider(REQUEST_DELAY)))

        val threadPoolExecutor = ThreadPoolExecutor(MAX_CONCURRENT_REQUESTS, MAX_CONCURRENT_REQUESTS, 1, TimeUnit.MINUTES, LinkedBlockingQueue())
        val dispatcher = Dispatcher(threadPoolExecutor)
        httpClientBuilder.dispatcher(dispatcher)

        val okHttpClient = httpClientBuilder.build()
        okHttpClient.dispatcher().maxRequests = MAX_CONCURRENT_REQUESTS

        retrofitClient = Retrofit.Builder()
            .baseUrl(currentRmsConfig.baseApiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        return retrofitClient!!
    }

    private fun getCurrentRmsConfig(): CurrentRmsConfig {
        return CurrentRmsConfig(
            System.getenv(CURRENT_RMS_TOKEN) ?: null
            ?: throw Exception("Env var: $CURRENT_RMS_TOKEN not set"),
            System.getenv(CURRENT_RMS_SUBDOMAIN) ?: null
            ?: throw Exception("Env var: $CURRENT_RMS_SUBDOMAIN not set"),
            System.getenv(CURRENT_RMS_API_URL) ?: null
            ?: throw Exception("Env var: $CURRENT_RMS_API_URL not set")
        )
    }
}

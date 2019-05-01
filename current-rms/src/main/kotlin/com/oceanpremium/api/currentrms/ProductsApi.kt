package com.oceanpremium.api.currentrms

import okhttp3.*
import java.io.IOException
import org.springframework.stereotype.Service
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap
import java.util.concurrent.TimeUnit

class CurrentRmsConfig(val accessToken: String, val subDomain: String)

class CurrentRMSConfigInterceptor(private val currentRmsConfig: CurrentRmsConfig) : Interceptor, Authenticator {

    companion object {
        private const val CURRENT_RMS_AUTH_HEADER = "X-AUTH-TOKEN"
        private const val CURRENT_RMS_AUTH_SUBDOMAIN = "X-SUBDOMAIN"
        private const val API_ACCEPTED_CONTENT_TYPE = "application/json"
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    /**
     * Interceptor class for setting of the headers for every request
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        request = request.newBuilder()
            .build()

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

@Service
class CurrentRmsApiClient {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
        private val CURRENT_RMS_TOKEN = "CURRENT_RMS_TOKEN"
        private val CURRENT_RMS_SUBDOMAIN = "CURRENT_RMS_SUBDOMAIN"
        private val baseApi = "https://api.current-rms.com/api/v1/"
    }

    private var retrofitClient: Retrofit? = null

    fun getRetrofitClient(): Retrofit {

        if (retrofitClient != null) {
            return retrofitClient!!
        }

        val currentRmsConfig = getCurrentRmsConfig()

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.HEADERS
        logging.level = HttpLoggingInterceptor.Level.BODY

        val currentRmsConfigInterceptor = CurrentRMSConfigInterceptor(currentRmsConfig)

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        httpClient.connectTimeout(20, TimeUnit.SECONDS)
        httpClient.readTimeout(20, TimeUnit.SECONDS)
        httpClient.addInterceptor(currentRmsConfigInterceptor)
        httpClient.authenticator(currentRmsConfigInterceptor)

        retrofitClient = Retrofit.Builder()
            .baseUrl(baseApi)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofitClient!!
    }

    private fun getCurrentRmsConfig(): CurrentRmsConfig {
        return CurrentRmsConfig(
            System.getenv(CURRENT_RMS_TOKEN) ?: null ?: throw Exception("Env var: $CURRENT_RMS_TOKEN not set"),
            System.getenv(CURRENT_RMS_SUBDOMAIN) ?: null ?: throw Exception("Env var: $CURRENT_RMS_SUBDOMAIN not set")
        )
    }
}


interface ProductsApi {
    @GET("products")
    fun getProducts(
        @QueryMap map: Map<String, String>
    ): Call<Any>
}

@Service
class ProductsApiImpl(@Autowired apiClient: CurrentRmsApiClient) {

    private val productsApi = apiClient.getRetrofitClient().create(ProductsApi::class.java)

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    @Throws(IOException::class)
    fun getProducts(map: Map<String, String>): Any {
        val retrofitCall = productsApi.getProducts(map = map)

        val response = retrofitCall.execute()

        if (!response.isSuccessful) {
            throw IOException(
                if (response.errorBody() != null) {
                    response.errorBody().toString()
                } else {
                    logger.error("Failed Current RMS API call: $response")
                    "Unknown error"
                }
            )
        }

        return response.body()!!
    }
}
package com.oceanpremium.api.core.ipdata

import io.sentry.Sentry
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Interface for IP DATA API, used by Retrofit to create API call service.
 */
interface IPDataApi {
    /**
     * Endpoint to get the GEO locaiton details by given IP.
     */
    @GET("{ip}")
    fun getGeoLocation(
        @Path("ip") ip: String = "143.179.43.75",
        @Query("api-key") apiToken: String = "fb151bc082e9db7880e9700d2452dd06f44f91574216065f2c6fd568"
    ): Call<Any>
}

class IPDataApiImpl(private val ipDataClient: IPDataClient = IPDataClient()) {

    private val ipDataApi = ipDataClient.getRetrofitClient().create(IPDataApi::class.java)
    private val ipDataApiConfig = ipDataClient.getIpDataConfig()

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    /**
     * @inherit
     */
    fun getGeoLocation(ip: String): Response<Any>? {
        val retrofitCall = ipDataApi.getGeoLocation(ip, ipDataApiConfig.accessToken)
        val apiUrl = "${ipDataApiConfig.baseApiUrl}/$ip"

        lateinit var response: Response<Any>

        try {
            response = retrofitCall.execute()
        } catch (e: Exception) {
            e.printStackTrace()
            logger.error("IP Data API call $apiUrl failed: ${e.message}")
            Sentry.capture(e)

            throw e
        }

        logger.debug("IP Data API call: $apiUrl HTTP status: ${response.code()}")

        when {
            response.isSuccessful -> {
                logger.debug("IP Data API $apiUrl response body: ${response.body()}")
            }
            else -> {
                logger.debug("Request to IP Data API $apiUrl failed: ${response.message()}")
            }
        }

        return response
    }
}

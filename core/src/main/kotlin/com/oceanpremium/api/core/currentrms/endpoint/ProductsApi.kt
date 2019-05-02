package com.oceanpremium.api.core.currentrms.endpoint

import com.oceanpremium.api.core.currentrms.client.CurrentRmsClient
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap
import java.io.IOException

interface ProductsApi {
    @GET("products")
    fun getProducts(
        @QueryMap map: Map<String, String>
    ): Call<Any>
}

@Service
class ProductsApiImpl(@Autowired currentRmsClient: CurrentRmsClient) {

    private val productsApi = currentRmsClient.getRetrofitClient().create(ProductsApi::class.java)

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    @Throws(IOException::class)
    fun getProducts(map: Map<String, String>): retrofit2.Response<Any>? {
        val retrofitCall = productsApi.getProducts(map = map)
        val response = retrofitCall.execute()

        logger.debug("Current RMS API call - HTTP status: ${response.code()}")

        when {
            response.isSuccessful -> {
                logger.debug("Current RMS API response body: ${response.body()}")
            }
            else ->  {
                logger.debug("Request to Current RMS API failed: ${response.message()}")
            }
        }

        return response
    }
}
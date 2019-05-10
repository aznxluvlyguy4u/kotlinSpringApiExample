package com.oceanpremium.api.core.currentrms

import com.oceanpremium.api.core.enum.CurrentRmsSaleType
import org.slf4j.LoggerFactory
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap
import java.io.IOException

interface ProductsApi {
    @GET("products")
    fun getProducts(
        @QueryMap map: Map<String, String>
    ): Call<Any>

    @GET("products/{productId}")
    fun getProductById(
        @Path("productId") productId: Int
    ): Call<Any>

    @GET("product_groups")
    fun getProductGroups(
        @QueryMap map: Map<String, String>
    ): Call<Any>

    @GET("products/inventory")
    fun getProductsInventory(
        @QueryMap map: Map<String, String>
    ): Call<Any>
}

class ProductsApiImpl(currentRmsClient: CurrentRmsClient = CurrentRmsClient()) {

    private val productsApi = currentRmsClient.getRetrofitClient().create(ProductsApi::class.java)

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
        private const val ACTIVE_PRODUCT_QUERY = "q[active_eq]"
        private const val FILTER_MODE_QUERY = "filtermode[]"

    }

    @Throws(IOException::class)
    fun getProductById(productId: Int): retrofit2.Response<Any>? {
        val retrofitCall = productsApi.getProductById(productId)
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

    @Throws(IOException::class)
    fun getProductGroups(map: Map<String, String>): retrofit2.Response<Any>? {
        val retrofitCall = productsApi.getProductGroups(map = map)
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

    @Throws(IOException::class)
    fun getProductsInventory(map: MutableMap<String, String>): retrofit2.Response<Any>? {
        when {
            !map.containsKey(ACTIVE_PRODUCT_QUERY) -> map[ACTIVE_PRODUCT_QUERY] = "true"
            !map.containsKey(FILTER_MODE_QUERY) -> map[FILTER_MODE_QUERY] = CurrentRmsSaleType.RENTAL.type
        }

        val retrofitCall = productsApi.getProductsInventory(map = map)
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

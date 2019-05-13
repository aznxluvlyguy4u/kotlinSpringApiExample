package com.oceanpremium.api.core.currentrms

import com.oceanpremium.api.core.enum.AuthorizationType
import com.oceanpremium.api.core.enum.CurrentRmsSaleType
import com.oceanpremium.api.core.exception.throwable.BadRequestException
import com.oceanpremium.api.core.exception.throwable.NotFoundException
import com.oceanpremium.api.core.exception.throwable.ServerErrorException
import com.oceanpremium.api.core.exception.throwable.UnauthorizedException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

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
        private const val DEFAULT_STORE_ID_QUERY = "store_id"

    }

    fun getProductById(productId: Int): Response<Any>? {
        val retrofitCall = productsApi.getProductById(productId)
        val response = retrofitCall.execute()

        logger.debug("Current RMS API call - HTTP status: ${response.code()}")

        when {
            response.isSuccessful -> {
                logger.debug("Current RMS API response body: ${response.body()}")
            }
            else ->  {
                logger.debug("Request to Current RMS API failed: ${response.message()}")

                handleException(response)
            }
        }

        return response
    }

    fun getProducts(map: Map<String, String>): Response<Any>? {
        val retrofitCall = productsApi.getProducts(map = map)
        val response = retrofitCall.execute()

        logger.debug("Current RMS API call - HTTP status: ${response.code()}")

        when {
            response.isSuccessful -> {
                logger.debug("Current RMS API response body: ${response.body()}")
            }
            else ->  {
                logger.debug("Request to Current RMS API failed: ${response.message()}")

                handleException(response)
            }
        }

        return response
    }

    fun getProductGroups(map: Map<String, String>): Response<Any>? {
        val retrofitCall = productsApi.getProductGroups(map = map)
        val response = retrofitCall.execute()

        logger.debug("Current RMS API call - HTTP status: ${response.code()}")

        when {
            response.isSuccessful -> {
                logger.debug("Current RMS API response body: ${response.body()}")
            }
            else ->  {
                logger.debug("Request to Current RMS API failed: ${response.message()}")

                handleException(response)
            }
        }

        return response
    }

    fun getProductsInventory(map: MutableMap<String, String>): Response<Any>? {

        // Set default query parameters if these are not present, for propery product querying on current rms.
        val validatedMap = map.toMutableMap()

        when {
            !map.containsKey(ACTIVE_PRODUCT_QUERY) -> validatedMap[ACTIVE_PRODUCT_QUERY] = "true"
        }

        when {
            !map.containsKey(FILTER_MODE_QUERY) -> validatedMap[FILTER_MODE_QUERY] = "rental"
        }

        when {
            !map.containsKey(DEFAULT_STORE_ID_QUERY) -> validatedMap[DEFAULT_STORE_ID_QUERY] = "5"
        }

        val retrofitCall = productsApi.getProductsInventory(map = validatedMap)
        val response = retrofitCall.execute()

        logger.debug("Current RMS API call - HTTP status: ${response.code()}")

        when {
            response.isSuccessful -> {
                logger.debug("Current RMS API response body: ${response.body()}")
            }
            else ->  {
                logger.debug("Request to Current RMS API failed: ${response.message()}")
                handleException(response)
            }
        }

        return response
    }

    /**
     * Based on returned HTTP Status Code, throw appropriate exception so that a corresponding (wrapped) error response
     * payload can be build and returned.
     */
    @Throws(UnauthorizedException::class, NotFoundException::class, BadRequestException::class, ServerErrorException::class)
    private fun handleException(response: Response<Any>) {
        if (response.code() == HttpStatus.UNAUTHORIZED.value()) {
            throw UnauthorizedException(type = AuthorizationType.THIRD_PARTY)
        }

        if (response.code() == HttpStatus.NOT_FOUND.value()) {
            throw NotFoundException()
        }

        if (response.code() == HttpStatus.BAD_REQUEST.value()) {
            throw BadRequestException()
        }

        if (response.code() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            throw ServerErrorException()
        }
    }
}

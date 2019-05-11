package com.oceanpremium.api.core.currentrms

import com.oceanpremium.api.core.enum.AuthorizationType
import com.oceanpremium.api.core.enum.CurrentRmsSaleType
import com.oceanpremium.api.core.exception.BadRequestException
import com.oceanpremium.api.core.exception.NotFoundException
import com.oceanpremium.api.core.exception.ServerErrorException
import com.oceanpremium.api.core.exception.UnauthorizedException
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
    }

    @Throws(UnauthorizedException::class)
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

                handleException(response)
            }
        }

        return response
    }

    @Throws(UnauthorizedException::class)
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

                handleException(response)
            }
        }

        return response
    }

    @Throws(UnauthorizedException::class)
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

                handleException(response)
            }
        }

        return response
    }

    @Throws(UnauthorizedException::class)
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
                handleException(response)
            }
        }

        return response
    }

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

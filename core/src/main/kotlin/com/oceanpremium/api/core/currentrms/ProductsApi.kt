package com.oceanpremium.api.core.currentrms

import com.oceanpremium.api.core.currentrms.response.dto.parameter.QueryParametersResolver
import com.oceanpremium.api.core.currentrms.response.dto.parameter.QueryParametersResolverImpl
import com.oceanpremium.api.core.enum.AuthorizationType
import com.oceanpremium.api.core.exception.throwable.*
import io.sentry.Sentry
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

/**
 * Interface for Products API, used by Retrofit to create API call service.
 */
interface ProductsApi {
    /**
     * Endpoint to query products by given query parameters.
     */
    @GET("products")
    fun getProducts(
        @QueryMap map: Map<String, String>
    ): Call<Any>

    /**
     * Endpoint to get a product by it's ID.
     */
    @GET("products/{productId}")
    fun getProductById(
        @Path("productId") productId: Int
    ): Call<Any>

    /**
     * Endpoint to get all product groups.
     */
    @GET("product_groups")
    fun getProductGroups(
        @QueryMap map: Map<String, String>
    ): Call<Any>

    /**
     * Endpoint to query products based on their availabitlity and given query paramters.
     */
    @GET("products/inventory")
    fun getProductsInventory(
        @QueryMap map: Map<String, String>
    ): Call<Any>

    /**
     * Endpoint to query products based on their availabitlity and given query paramters.
     */
    @GET("list_names")
    fun getProductConfigOptions(
        @QueryMap map: Map<String, String>
    ): Call<Any>

}

class ProductsApiImpl(
    currentRmsClient: CurrentRmsClient = CurrentRmsClient(),
    private  val queryParametersResolver: QueryParametersResolver = QueryParametersResolverImpl()
)  {

    private val productsApi = currentRmsClient.getRetrofitClient().create(ProductsApi::class.java)

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
        private const val RATE_LIMIT_EXPIRATION_HEADER = "X-RateLimit-Reset"
    }

    /**
     * @inherit
     */
    fun getProductById(productId: Int): Response<Any>? {
        val retrofitCall = productsApi.getProductById(productId)
        lateinit var response: Response<Any>

        try {
            response = retrofitCall.execute()
        } catch (e: Exception) {
            e.printStackTrace()
            logger.error("Request to Current RMS API failed: ${e.message}")
            Sentry.capture(e)

            throw CurrentRmsAPIException(e.message)
        }

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
     * @inherit
     */
    fun getProducts(queryParameters: Map<String, String>, headers: HttpHeaders): Response<Any>? {
        val validatedMap = queryParametersResolver.resolveGetProducts(queryParameters, headers)
        val retrofitCall = productsApi.getProducts(map = validatedMap)
        lateinit var response: Response<Any>

        try {
            response = retrofitCall.execute()

            logger.debug("Current RMS API call - HTTP status: ${response.code()}")

            when {
                response != null && response.isSuccessful -> {
                    logger.debug("Current RMS API response body: ${response.body()}")
                }

                response != null && !response.isSuccessful -> {
                    logger.debug("Request to Current RMS API failed: ${response.message()}")

                    handleException(response)
                }

                else ->  {
                    val message = "Request to Current RMS API failed, response object is null"
                    logger.error(message)

                    val exception = CurrentRmsAPIException(message)
                    Sentry.capture(exception)
                    throw exception
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            logger.error("Request to Current RMS API failed$ ${e.message}")
            Sentry.capture(e)

            throw CurrentRmsAPIException(e.message)
        }

        return response
    }

    /**
     * @inherit
     */
    fun getProductGroups(queryParameters: Map<String, String>, headers: HttpHeaders): Response<Any>? {
        val validatedMap = queryParametersResolver.resolveGetProductGroups(queryParameters, headers)
        val retrofitCall = productsApi.getProductGroups(map = validatedMap)
        lateinit var response: Response<Any>

        try {
            response = retrofitCall.execute()
        } catch (e: Exception) {
            e.printStackTrace()
            logger.error("Request to Current RMS API failed: ${e.message}")
            Sentry.capture(e)

            throw CurrentRmsAPIException(e.message)
        }

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
     * @inherit
     */
    fun getProductsInventory(queryParameters: MutableMap<String, String>, headers: HttpHeaders): Response<Any>? {
        val validatedMap = queryParametersResolver.resolveGetProductsInventory(queryParameters, headers)
        val retrofitCall = productsApi.getProductsInventory(map = validatedMap)
        lateinit var response: Response<Any>

        try {
            response = retrofitCall.execute()
        } catch (e: Exception) {
            e.printStackTrace()
            logger.error("Request to Current RMS API failed: ${e.message}")
            Sentry.capture(e)

            throw CurrentRmsAPIException(e.message)
        }

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
     * @inherit
     */
    fun getProductConfigOptions(): Response<Any>? {
        val retrofitCall = productsApi.getProductConfigOptions(map = mapOf("q[name_cont]" to "ProductConfig"))

        lateinit var response: Response<Any>

        try {
            response = retrofitCall.execute()
        } catch (e: Exception) {
            e.printStackTrace()
            logger.error("Request to Current RMS API /api/v1/list_names failed: ${e.message}")
            Sentry.capture(e)

            throw CurrentRmsAPIException(e.message)
        }

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
     *
     * Register a handler for any exception newly added and thrown to the GlobalException handler
     * @see com.oceanpremium.api.core.exception.handler.GlobalExceptionHandler#handleExceptionType.
     */
    @Throws(
        UnauthorizedException::class,
        BadRequestException::class,
        TooManyRequestsException::class,
        CurrentRmsAPIException::class
    )
    private fun handleException(response: Response<Any>) {

        // HTTP 400
        if (response.code() == HttpStatus.BAD_REQUEST.value()) {
            logger.warn(response.message())
            val exception = BadRequestException(response.message())
            Sentry.capture(exception)

            throw exception
        }

        // HTTP 401
        if (response.code() == HttpStatus.UNAUTHORIZED.value()) {
            logger.warn(response.message())
            val exception = UnauthorizedException(type = AuthorizationType.THIRD_PARTY, message = response.message())
            Sentry.capture(exception)

            throw exception
        }

        // HTTP 429
        if (response.code() == HttpStatus.TOO_MANY_REQUESTS.value()) {
            val headers = response.headers()

            val message = "Current RMS requests has been rate limited"
            logger.error(message)

            when {
                headers.get(RATE_LIMIT_EXPIRATION_HEADER) != null -> {
                    logger.debug("$RATE_LIMIT_EXPIRATION_HEADER = ${headers.get(RATE_LIMIT_EXPIRATION_HEADER)}")
                    val errorMessage = "$message. Limit expires on: ${headers.get(RATE_LIMIT_EXPIRATION_HEADER)}"
                    logger.error(errorMessage)
                    val exception = TooManyRequestsException(errorMessage)
                    Sentry.capture(exception)

                    throw exception
                }
                else -> {
                    logger.debug("No $RATE_LIMIT_EXPIRATION_HEADER header was provided")
                    val exception = TooManyRequestsException(message)
                    Sentry.capture(exception)

                    throw exception
                }
            }
        }

        // HTTP 500
        if (response.code() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            val exception = CurrentRmsAPIException(response.message())
            Sentry.capture(exception)

            throw exception
        }
    }
}

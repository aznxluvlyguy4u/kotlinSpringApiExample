package com.oceanpremium.api.core.currentrms

import com.oceanpremium.api.core.enum.AuthorizationType
import com.oceanpremium.api.core.exception.throwable.*
import com.oceanpremium.api.core.util.DateTimeUtil
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap
import java.net.UnknownHostException
import java.util.*

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

interface QueryParametersResolver {
    fun resolveGetProductsInventory(map: Map<String, String>): Map<String, String>
}

class QueryParametersResolverImpl : QueryParametersResolver {

    companion object {
        private const val ACTIVE_PRODUCT_QUERY = "q[active_eq]"
        private const val FILTER_MODE_QUERY = "filtermode[]"
        private const val DEFAULT_STORE_ID_QUERY = "store_id"
        private const val START_DATE_QUERY = "start_date"
        private const val END_DATE_QUERY = "end_date"
    }

    /**
     * Append default query parameters if the defaults are not present, for proper / valid product querying.
     *
     * see @link https://bitbucket.org/oceanpremium/ocean-premium-api/wiki/Current%20RMS%20API%20request%20examples#markdown-header-products-inventory
     * see @link https://dudesoftechnology.atlassian.net/browse/OPP-184
     * see @link https://api.current-rms.com/doc#header-searching-with-the-query-engine
     *
     * - q[active_eq]=true
     * - filtermode[]=all
     * - store_id=5
     * - start_date=yyy-mm-dd
     * - end_date=yyy-mm-dd
     *
     */
    override fun resolveGetProductsInventory(map: Map<String, String>): Map<String, String> {
        val validatedMap = map.toMutableMap()

        // ------------------------------------------------------------------------------------------ Static parameters

        // Only query active products
        when {
            !map.containsKey(ACTIVE_PRODUCT_QUERY) -> validatedMap[ACTIVE_PRODUCT_QUERY] = "true"
        }

        // Only query products of rental type
        when {
            !map.containsKey(FILTER_MODE_QUERY) -> validatedMap[FILTER_MODE_QUERY] = "rental"
        }

        // Only query products on a specific store (for now)
        when {
            !map.containsKey(DEFAULT_STORE_ID_QUERY) -> validatedMap[DEFAULT_STORE_ID_QUERY] = "5"
        }

        // ----------------------------------------------------------------------------------------- Dynamic parameters

        // Check if date interval boundaries (start & end) are given, otherwise set query date interval to ONE day
        when {
            // If a startDate is given but no endDate, set endDate equal to startDate

            // No time interval boundaries supplied
            !map.containsKey(START_DATE_QUERY) && !map.containsKey(END_DATE_QUERY) -> {
                val now = DateTimeUtil.toISO8601UTC(Date())
                validatedMap[START_DATE_QUERY] = now
                validatedMap[END_DATE_QUERY] = now
            }

            // Only a startDate is supplied
            map.containsKey(START_DATE_QUERY) && !map.containsKey(END_DATE_QUERY) -> {
                validatedMap[END_DATE_QUERY] = validatedMap[START_DATE_QUERY] as String
            }

            // Only an endDate is supplied
            !map.containsKey(START_DATE_QUERY) && map.containsKey(END_DATE_QUERY) -> {
                validatedMap[START_DATE_QUERY] = validatedMap[END_DATE_QUERY] as String
            }
        }


        return validatedMap
    }

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
                    logger.debug("Request to Current RMS API failed")
                    throw ServerErrorException()
                }
            }
        } catch (e: UnknownHostException) {
            e.printStackTrace()
            logger.error("Request to Current RMS API failed")

            throw ServerErrorException(e.message)
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

        val validatedMap = queryParametersResolver.resolveGetProductsInventory(map)

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
     *
     * Register a handler for any exception newly added and thrown to the GlobalException handler
     * @see com.oceanpremium.api.core.exception.handler.GlobalExceptionHandler#handleExceptionType.
     */
    @Throws(
        UnauthorizedException::class,
        NotFoundException::class,
        BadRequestException::class,
        ServerErrorException::class,
        TooManyRequestsException::class
    )
    private fun handleException(response: Response<Any>) {

        // HTTP 400
        if (response.code() == HttpStatus.BAD_REQUEST.value()) {
            throw BadRequestException()
        }

        // HTTP 401
        if (response.code() == HttpStatus.UNAUTHORIZED.value()) {
            throw UnauthorizedException(type = AuthorizationType.THIRD_PARTY)
        }

        // HTTP 429
        if (response.code() == HttpStatus.TOO_MANY_REQUESTS.value()) {
            val headers = response.headers()

            val message = "Current RMS requests has been rate limited"
            logger.error(message)

            when {
                headers.get(RATE_LIMIT_EXPIRATION_HEADER) != null -> {
                    logger.debug("$RATE_LIMIT_EXPIRATION_HEADER = ${headers.get(RATE_LIMIT_EXPIRATION_HEADER)}")
                    throw TooManyRequestsException("$message. Limit expires on: ${headers.get(RATE_LIMIT_EXPIRATION_HEADER)}")
                }
                else -> {
                    logger.debug("No $RATE_LIMIT_EXPIRATION_HEADER header was provided")
                    throw TooManyRequestsException(message)
                }
            }
        }

        // HTTP 500
        if (response.code() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            throw ServerErrorException()
        }
    }
}

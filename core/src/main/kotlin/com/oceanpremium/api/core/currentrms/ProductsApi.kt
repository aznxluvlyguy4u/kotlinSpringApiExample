package com.oceanpremium.api.core.currentrms

import com.oceanpremium.api.core.enum.AuthorizationType
import com.oceanpremium.api.core.exception.throwable.*
import com.oceanpremium.api.core.util.DateTimeUtil
import com.oceanpremium.api.core.util.DateTimeUtil.DEFAULT_API_DATE_FORMATE
import io.sentry.Sentry
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

@Suppress("KDocUnresolvedReference")
class QueryParametersResolverImpl : QueryParametersResolver {

    companion object {
        private const val ACTIVE_PRODUCT_QUERY = "q[active_eq]"
        private const val FILTER_MODE_QUERY = "filtermode[]"
        private const val DEFAULT_STORE_ID_QUERY = "store_id"
        private const val START_DATE_QUERY = "starts_at"
        private const val END_DATE_QUERY = "ends_at"
        private const val ACCESSORY_ONLY_QUERY = "q[product_accessory_only_eq]"
        private const val FUNCTIONAL_INTEGRATION_GROUP_QUERY = "q[product_product_group_name_not_eq]"
        private const val FUNCTIONAL_INTEGRATION_GROUP_NAME = "functionalintegrationtest"
        private const val COLLECTION_LOCATION_KEY = "collection_location_id"
        private const val DELIVERY_LOCATION_KEY = "delivery_location_id"

    }

    /**
     * Append default query parameters if the defaults are not present, for proper / valid product querying.
     *
     * see @link https://bitbucket.org/oceanpremium/ocean-premium-api/wiki/Current%20RMS%20API%20request%20examples#markdown-header-products-inventory
     * see @link https://dudesoftechnology.atlassian.net/browse/OPP-184
     * see @link https://api.current-rms.com/doc#header-searching-with-the-query-engine
     *
     * - q[product_accessory_only_eq]=false
     * - q[active_eq]=true
     * - filtermode[]=rental
     * - store_id=5
     * - start_date=yyyy-mm-dd or yyyy-MM-dd'T'HH:mm'Z'
     * - end_date=yyyy-mm-dd or yyyy-MM-dd'T'HH:mm'Z'
     * - q[product_product_group_name_not_eq]=PRODUCT_GROUP_SETUP_FOR_TESTING
     * - delivery_location_id=3
     * - collection_location_id=5
     *
     * Final result query outgoing to currentRMS NEEDS TO BE of the following MINIMAL form:
     *
     * 1  q[product_tags_name_cont]=SEARCH_KEY_STRING
     * 2  q[active_eq]=true
     * 3  filtermode[]=rental
     * 4  store_id=STORE_ID_INT
     * 5  q[product_accessory_only_eq]=false
     * 6  starts_at=YYYY-MM-DD
     * 7  ends_at=YYYY-MM-DD
     * 8  q[product_product_group_name_not_eq]=functionalintegrationtest
     *
     */
    override fun resolveGetProductsInventory(map: Map<String, String>): Map<String, String> {
        val validatedMap = map.toMutableMap()

        /**
         * WARNING - DO NOT DELETE OR CHANGE THE FUNCTIONAL_INTEGRATION_GROUP_QUERY key / value
         *
         * THIS EXCLUDES TEST DATA IN CURRENT RMS (FOR CI INTEGRATION TESTS PURPOSE) FROM THE PUBLIC SEARCH RESULTS
         *
         * CONSULT THE WIKI FIRST, IF YOU NEED TO EDIT THIS
         *
         * see @link https://bitbucket.org/oceanpremium/ocean-premium-api/wiki/Setting%20up%20test%20data
         */
        validatedMap[FUNCTIONAL_INTEGRATION_GROUP_QUERY] = FUNCTIONAL_INTEGRATION_GROUP_NAME


        /**
         * Static parameters, check if the default mandatory query parameters are given, otherwise append it.
         */

        // Only query active products
        if (!validatedMap.containsKey(ACTIVE_PRODUCT_QUERY)) {
            validatedMap[ACTIVE_PRODUCT_QUERY] = "true"
        }

        // Only query products of rental type
        if (!validatedMap.containsKey(FILTER_MODE_QUERY)) {
            validatedMap[FILTER_MODE_QUERY] = "rental"
        }

        // Only query products and accessories that are rentable on it self (exclude non-rentable accessories)
        if (!validatedMap.containsKey(ACCESSORY_ONLY_QUERY)) {
            validatedMap[ACCESSORY_ONLY_QUERY] = "false"
        }

        /**
         * Dynamic parameters, check if date interval boundaries (start- & end date) are given,
         * otherwise set query date interval to ONE day. If a start date is given but no end date,
         * set end date equal to start date.
         */

        // No time interval boundaries supplied, create an interval of ONE day
        if (!validatedMap.containsKey(START_DATE_QUERY) && !validatedMap.containsKey(END_DATE_QUERY) ) {
            val now = DateTimeUtil.toISO8601UTC(Date(), format = DEFAULT_API_DATE_FORMATE)
            validatedMap[START_DATE_QUERY] = now
            validatedMap[END_DATE_QUERY] = now
        }

        // Only a start date is supplied
        if (map.containsKey(START_DATE_QUERY) && !map.containsKey(END_DATE_QUERY)) {
            validatedMap[END_DATE_QUERY] = validatedMap[START_DATE_QUERY] as String
        }

        // Only an end date is supplied
        if (!map.containsKey(START_DATE_QUERY) && map.containsKey(END_DATE_QUERY)) {
            validatedMap[START_DATE_QUERY] = validatedMap[END_DATE_QUERY] as String
        }

        // If a location/collection id is given, grab it, resolve it to store id and append it to the map,
        // and remove the location/collection id from the map as current rms does not recognize those fields
        if (validatedMap.containsKey(COLLECTION_LOCATION_KEY)) {
            validatedMap.remove(COLLECTION_LOCATION_KEY)
        }

        if (validatedMap.containsKey(DELIVERY_LOCATION_KEY)) {
            validatedMap.remove(DELIVERY_LOCATION_KEY)
        }

        // Use to location/collection id's to resolve to a store id
        // Only query products on a specific store (for now)
        if (!validatedMap.containsKey(DEFAULT_STORE_ID_QUERY)) {
            validatedMap[DEFAULT_STORE_ID_QUERY] = "5"
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
                    val message = "Request to Current RMS API failed, response returned null"
                    logger.error(message)

                    val exception = ServerErrorException(message)
                    Sentry.capture(exception)
                    throw exception
                }
            }
        } catch (e: UnknownHostException) {
            e.printStackTrace()
            logger.error("Request to Current RMS API failed")
            Sentry.capture(e)

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
            val exception = ServerErrorException(response.message())
            Sentry.capture(exception)

            throw exception
        }
    }
}

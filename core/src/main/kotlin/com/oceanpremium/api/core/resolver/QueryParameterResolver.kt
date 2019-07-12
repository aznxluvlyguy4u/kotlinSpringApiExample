package com.oceanpremium.api.core.resolver

import com.oceanpremium.api.core.exception.throwable.BadRequestException
import com.oceanpremium.api.core.util.DateTimeUtil
import com.oceanpremium.api.core.util.DateTimeUtil.CURRENT_RMS_API_DATE_ISO8601_FORMAT
import org.joda.time.DateTime
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders

interface QueryParametersResolver {

    fun resolveGetProducts(map: Map<String, String>, headers: HttpHeaders): Map<String, String>

    fun resolveGetProductsInventory(map: Map<String, String>, headers: HttpHeaders, storeIds: List<Int>?): ProductsInventoryValidatedMap

    fun resolveGetProductGroups(map: Map<String, String>, headers: HttpHeaders): Map<String, String>

    fun getHost(headers: HttpHeaders): String? {
        val hostKey = "Host"

        return when {
            headers.containsKey(hostKey) -> {
                val host = headers[hostKey] as List<String>?

                when {
                    host != null && host.isNotEmpty() -> {
                        val address = host.first()

                        address
                    }
                    else -> null
                }
            }
            else -> return null
        }
    }
}

class ProductsInventoryValidatedMap(val storeIdsQueryParams: List<Int>?, val uniqueQueryParams: Map<String, String>)

@Suppress("KDocUnresolvedReference")
class QueryParametersResolverImpl : QueryParametersResolver {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
        const val STORE_ID_QUERY_PARAMS = "store_id[]"
        private const val ACTIVE_PRODUCT_QUERY = "q[active_eq]"
        private const val FILTER_MODE_QUERY = "filtermode[]"
        private const val START_DATE_QUERY = "starts_at"
        private const val END_DATE_QUERY = "ends_at"
        private const val ACCESSORY_ONLY_QUERY = "q[product_accessory_only_eq]"
        private const val FUNCTIONAL_INTEGRATION_GROUP_QUERY = "q[product_product_group_name_not_eq]"
        const val FUNCTIONAL_INTEGRATION_GROUP_NAME = "FunctionalIntegrationTest"
        const val COLLECTION_LOCATION_KEY = "collection_location_id"
        const val DELIVERY_LOCATION_KEY = "delivery_location_id"
        private const val PRODUCT_TAGS_SEARCH_QUERY = "q[product_tags_name_cont]"
        private const val PRODUCT_ID_SEARCH_QUERY = "q[product_id_eq]"
        private const val PRODUCT_GROUPS_ID_SEARCH_QUERY = "q[product_group_id_eq]"
        private const val NAME_NOT_EQ_QUERY = "q[name_not_eq]"
        private const val LOCALHOST = "localhost"
        private const val PRODUCTS_TAG_SEARCH_QUERY = "tags"
        private const val PAGE_KEY = "page"
        private const val PER_PAGE_KEY = "per_page"
        const val KEYWORD_LESS_TAG = "keywordless"
        const val PRODUCT_TAGS_SEARCH_EQ_QUERY = "q[product_tags_name_eq]"
    }

    /**
     * Append default query parameters for proper / valid product querying.
     *
     * see @link https://bitbucket.org/oceanpremium/ocean-premium-api/wiki/Current%20RMS%20API%20request%20examples#markdown-header-products-inventory
     * see @link https://dudesoftechnology.atlassian.net/browse/OPP-184
     * see @link https://api.current-rms.com/doc#header-searching-with-the-query-engine
     *
     *
     * Final result query outgoing to currentRMS NEEDS TO BE of the following MINIMAL form:
     *
     * Search on TAG
     * 1  q[product_tags_name_cont]=SEARCH_KEY_STRING
     * 2  q[active_eq]=true
     * 3  filtermode[]=rental
     * 4  store_id[]=STORE_ID_INT
     * 5  q[product_accessory_only_eq]=false
     * 6  starts_at=YYYY-MM-DD
     * 7  ends_at=YYYY-MM-DD
     * 8  q[product_product_group_name_not_eq]=FunctionalIntegrationTest
     *
     *  OR
     *
     * SEARCH on PRODUCT GROUP
     *
     * 1  q[product_group_id_eq]=19
     * 2  q[active_eq]=true
     * 3  filtermode[]=rental
     * 4  store_id[]=STORE_ID_INT
     * 5  q[product_accessory_only_eq]=false
     * 6  starts_at=YYYY-MM-DD
     * 7  ends_at=YYYY-MM-DD
     * 8  q[product_product_group_name_not_eq]=FunctionalIntegrationTest
     *
     * OR
     *
     * SEARCH ON PRODUCT ID
     *
     * 1  q[product_id_eq]=247
     * 2  q[active_eq]=true
     * 3  filtermode[]=rental
     * 4  store_id[]=STORE_ID_INT
     * 5  q[product_accessory_only_eq]=false
     * 6  starts_at=YYYY-MM-DD
     * 7  ends_at=YYYY-MM-DD
     * 8  q[product_product_group_name_not_eq]=FunctionalIntegrationTest
     */
    override fun resolveGetProductsInventory(map: Map<String, String>, headers: HttpHeaders, storeIds: List<Int>?): ProductsInventoryValidatedMap {
        val uniqueQueryParamsMap = mutableMapOf<String, String>()
        val host = getHost(headers)

        // Set unique query params to validatedMap
        try {
            when {
                // Only query product with tags containing the search value
                map.containsKey(PRODUCT_TAGS_SEARCH_QUERY) ->  {
                    uniqueQueryParamsMap[PRODUCT_TAGS_SEARCH_QUERY] = map[PRODUCT_TAGS_SEARCH_QUERY] as String
                }
                // Only query product with group ids containing the search value
                map.containsKey(PRODUCT_GROUPS_ID_SEARCH_QUERY)-> {
                    uniqueQueryParamsMap[PRODUCT_GROUPS_ID_SEARCH_QUERY] = map[PRODUCT_GROUPS_ID_SEARCH_QUERY] as String
                }
                // Only query product with group ids containing the search value
                map.containsKey(PRODUCT_ID_SEARCH_QUERY)-> {
                    uniqueQueryParamsMap[PRODUCT_ID_SEARCH_QUERY] = map[PRODUCT_ID_SEARCH_QUERY] as String
                }
                else -> {
                    // Cannot continue, the minimal input is a pickup location which is not present, when no keyword is provided
                    if (!map.containsKey(DELIVERY_LOCATION_KEY)) {
                        throw BadRequestException("Cannot continue search, need at minimum, either a search keyword or a delivery location.")
                    } else {
                        uniqueQueryParamsMap[PRODUCT_TAGS_SEARCH_EQ_QUERY] = KEYWORD_LESS_TAG
                    }
                }
            }

            /**
             * Static parameters, add default mandatory query parameters.
             */
            // Only query active products
            uniqueQueryParamsMap[ACTIVE_PRODUCT_QUERY] = "true"

            // Only query products of rental type
            uniqueQueryParamsMap[FILTER_MODE_QUERY] = "rental"

            // Only query products and accessories that are rentable on it self (exclude non-rentable accessories)
            if (!map.containsKey(ACCESSORY_ONLY_QUERY)) {
                uniqueQueryParamsMap[ACCESSORY_ONLY_QUERY] = "false"
            }

            /**
             * WARNING - DO NOT DELETE OR CHANGE THE FUNCTIONAL_INTEGRATION_GROUP_QUERY KEY / VALUE
             *
             * THIS EXCLUDES TEST DATA IN CURRENT RMS (FOR CI INTEGRATION TESTS PURPOSE) FROM THE PUBLIC SEARCH RESULTS
             *
             * CONSULT THE WIKI FIRST, IF YOU NEED TO EDIT THIS
             *
             * see @link https://bitbucket.org/oceanpremium/ocean-premium-api/wiki/Setting%20up%20test%20data
             */
            if (host != null && !host.contains(LOCALHOST)) {
                logger.debug("Running other then $LOCALHOST, DISABLE functional integration test product querying")

                when {
                    !map.containsKey(FUNCTIONAL_INTEGRATION_GROUP_QUERY) -> uniqueQueryParamsMap[FUNCTIONAL_INTEGRATION_GROUP_QUERY] =
                        FUNCTIONAL_INTEGRATION_GROUP_NAME
                }
            }

            /**
             * Dynamic parameters, check if date interval boundaries (start- & end date) are given,
             * otherwise set query date interval to ONE day. If a start date is given but no end date,
             * set end date equal to start date.
             *
             * See also @link: https://api.current-rms.com/doc#header-dates
             * for the needed format of DateTimes to properly consume CurrentRMS API.
             *
             * Format needs to be like the following: 2015-06-28T23:00:00.000Z
             */

            // A time interval with boundaries is supplied, validate if boundaries are valid
            if (map.containsKey(START_DATE_QUERY) && map.containsKey(
                    END_DATE_QUERY
                )
            ) {
                val startDate = DateTimeUtil.fromISO8601UTC(map[START_DATE_QUERY] as String, CURRENT_RMS_API_DATE_ISO8601_FORMAT)
                val endDate = DateTimeUtil.fromISO8601UTC(map[END_DATE_QUERY] as String, CURRENT_RMS_API_DATE_ISO8601_FORMAT)

                when {
                    startDate != null && endDate != null -> when {
                        endDate.isBefore(startDate) -> throw BadRequestException("Collection date: $endDate may not be before delivery date: $startDate")
                        else -> {
                            val startDateAtNoon = startDate.withTime(DateTimeUtil.NOON, 0, 0, 0)
                            val endDateAtNoon = endDate.withTime(DateTimeUtil.NOON, 0, 0, 0)

                            uniqueQueryParamsMap[START_DATE_QUERY] = DateTimeUtil.toISO8601UTC(startDateAtNoon, CURRENT_RMS_API_DATE_ISO8601_FORMAT)!!
                            uniqueQueryParamsMap[END_DATE_QUERY] = DateTimeUtil.toISO8601UTC(endDateAtNoon, CURRENT_RMS_API_DATE_ISO8601_FORMAT)!!
                        }
                    }
                }
            }

            // No time interval boundaries supplied, create an interval of ONE day
            if (!map.containsKey(START_DATE_QUERY) && !map.containsKey(END_DATE_QUERY)) {
                val todayAtNoon = DateTime().withTime(DateTimeUtil.NOON, 0, 0, 0)
                val todayAtNoonStr =
                    DateTimeUtil.toISO8601UTC(todayAtNoon, CURRENT_RMS_API_DATE_ISO8601_FORMAT)!!
                uniqueQueryParamsMap[START_DATE_QUERY] = todayAtNoonStr

                val tomorrowAtNoon = todayAtNoon.plusDays(1)
                val tomorrowAtNoonStr =
                    DateTimeUtil.toISO8601UTC(tomorrowAtNoon, CURRENT_RMS_API_DATE_ISO8601_FORMAT)!!
                uniqueQueryParamsMap[END_DATE_QUERY] = tomorrowAtNoonStr
            }

            // Only a start date is supplied
            if (map.containsKey(START_DATE_QUERY) && !map.containsKey(END_DATE_QUERY)) {
                val startDate = DateTimeUtil.fromISO8601UTC(map[START_DATE_QUERY] as String, CURRENT_RMS_API_DATE_ISO8601_FORMAT)

                when {
                    startDate != null -> {
                        val startDateAtNoon = startDate.withTime(DateTimeUtil.NOON, 0, 0, 0)
                        val endDateAtNoon = startDate.plusDays(1)

                        uniqueQueryParamsMap[START_DATE_QUERY] = DateTimeUtil.toISO8601UTC(startDateAtNoon, CURRENT_RMS_API_DATE_ISO8601_FORMAT)!!
                        uniqueQueryParamsMap[END_DATE_QUERY] = DateTimeUtil.toISO8601UTC(endDateAtNoon, CURRENT_RMS_API_DATE_ISO8601_FORMAT)!!
                    }
                    else -> throw BadRequestException("Failed to parse delivery date: $startDate")
                }
            }

            // Only an end date is supplied
            if (!map.containsKey(START_DATE_QUERY) && map.containsKey(END_DATE_QUERY)) {
                val endDate = DateTimeUtil.fromISO8601UTC(map[END_DATE_QUERY] as String, CURRENT_RMS_API_DATE_ISO8601_FORMAT)

                when {
                    endDate != null -> {
                        val endDateAtNoon = endDate.withTime(DateTimeUtil.NOON, 0, 0, 0)
                        val startDateAtNoon = endDateAtNoon.minusDays(1)

                        uniqueQueryParamsMap[START_DATE_QUERY] = DateTimeUtil.toISO8601UTC(startDateAtNoon, CURRENT_RMS_API_DATE_ISO8601_FORMAT)!!
                        uniqueQueryParamsMap[END_DATE_QUERY] = DateTimeUtil.toISO8601UTC(endDateAtNoon, CURRENT_RMS_API_DATE_ISO8601_FORMAT)!!
                    }
                    else -> throw BadRequestException("Failed to parse delivery date: $endDate")
                }
            }

            // Explicit pagination parameters are given, map them
            if (map.containsKey(PAGE_KEY)) {
                uniqueQueryParamsMap[PAGE_KEY] = map[PAGE_KEY] as String
            }

            if (map.containsKey(PER_PAGE_KEY)) {
                uniqueQueryParamsMap[PER_PAGE_KEY] = map[PER_PAGE_KEY] as String
            }
        } catch (e: Exception) {
            e.printStackTrace()
            val message = "Failed to parse query parameters: ${e.message}"
            logger.error(message)

            throw BadRequestException(message)
        }

        logger.debug("Validated query parameters set build: $uniqueQueryParamsMap")

        return ProductsInventoryValidatedMap(storeIds, uniqueQueryParamsMap)
    }

    override fun resolveGetProducts(map: Map<String, String>, headers: HttpHeaders): Map<String, String> {
        val validatedMap = map.toMutableMap()

        val host = getHost(headers)

        try {

            when {
                // Only query product with tags containing the search value
                map.containsKey(PRODUCTS_TAG_SEARCH_QUERY) -> {
                    validatedMap[PRODUCTS_TAG_SEARCH_QUERY] = map[PRODUCTS_TAG_SEARCH_QUERY] as String
                }

                // Only query product with group ids containing the search value
                map.containsKey(PRODUCT_GROUPS_ID_SEARCH_QUERY) -> {
                    validatedMap[PRODUCT_GROUPS_ID_SEARCH_QUERY] = map[PRODUCT_GROUPS_ID_SEARCH_QUERY] as String
                }

                // Cannot continue, the minimal input is a search keyword which is not present
                else -> {
                    throw BadRequestException("Cannot continue search, need a search query")
                }
            }

            /**
             * Static parameters, add default mandatory query parameters.
             */
            // Only query active products
            validatedMap[ACTIVE_PRODUCT_QUERY] = "true"

            // Only query products of rental type
            validatedMap[FILTER_MODE_QUERY] = "rental"

            // Only query products and accessories that are rentable on it self (exclude non-rentable accessories)
            validatedMap[ACCESSORY_ONLY_QUERY] = "false"

            // Explicit pagination parameters are given, map them
            if (map.containsKey(PAGE_KEY)) {
                validatedMap[PAGE_KEY] = map[PAGE_KEY] as String
            }

            if (map.containsKey(PER_PAGE_KEY)) {
                validatedMap[PER_PAGE_KEY] = map[PER_PAGE_KEY] as String
            }

            /**
             * WARNING - DO NOT DELETE OR CHANGE THE FUNCTIONAL_INTEGRATION_GROUP_QUERY KEY / VALUE
             *
             * THIS EXCLUDES TEST DATA IN CURRENT RMS (FOR CI INTEGRATION TESTS PURPOSE) FROM THE PUBLIC SEARCH RESULTS
             *
             * CONSULT THE WIKI FIRST, IF YOU NEED TO EDIT THIS
             *
             * see @link https://bitbucket.org/oceanpremium/ocean-premium-api/wiki/Setting%20up%20test%20data
             */
            if (host != null && !host.contains(LOCALHOST)) {
                logger.debug("Running other then $LOCALHOST, DISABLE functional integration test product querying")

                when {
                    !map.containsKey(FUNCTIONAL_INTEGRATION_GROUP_QUERY) -> validatedMap[FUNCTIONAL_INTEGRATION_GROUP_QUERY] =
                        FUNCTIONAL_INTEGRATION_GROUP_NAME
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            val message = "Failed to parse query parameters: ${e.message}"
            logger.error(message)

            throw BadRequestException(message)
        }

        return validatedMap
    }

    override fun resolveGetProductGroups(map: Map<String, String>, headers: HttpHeaders): Map<String, String> {
        val validatedMap = map.toMutableMap()
        val host = getHost(headers)

        /**
         * WARNING - DO NOT DELETE OR CHANGE THE FUNCTIONAL_INTEGRATION_GROUP_QUERY key / value
         *
         * THIS EXCLUDES TEST DATA IN CURRENT RMS (FOR CI INTEGRATION TESTS PURPOSE) FROM THE PUBLIC SEARCH RESULTS
         *
         * CONSULT THE WIKI FIRST, IF YOU NEED TO EDIT THIS
         *
         * see @link https://bitbucket.org/oceanpremium/ocean-premium-api/wiki/Setting%20up%20test%20data
         */

        if (host != null && !host.contains(LOCALHOST)) {
            logger.debug("Running other then localhost, disable functional integration test product querying")

            when {
                !map.containsKey(NAME_NOT_EQ_QUERY) -> validatedMap[NAME_NOT_EQ_QUERY] =
                    FUNCTIONAL_INTEGRATION_GROUP_NAME
            }
        }

        // Explicit pagination parameters are given, map them
        if (map.containsKey(PAGE_KEY)) {
            validatedMap[PAGE_KEY] = map[PAGE_KEY] as String
        }

        if (map.containsKey(PER_PAGE_KEY)) {
            validatedMap[PER_PAGE_KEY] = map[PER_PAGE_KEY] as String
        }

        return validatedMap
    }
}

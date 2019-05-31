package com.oceanpremium.api.core.currentrms.response.dto.parameter

import com.oceanpremium.api.core.currentrms.builder.LocationBuilder
import com.oceanpremium.api.core.currentrms.builder.StoreBuilder
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired

interface LocationStoreResolver {
    fun resolveStoreByLocation(queryParameters: Map<*, *>): List<Int>?
}

class LocationStoreResolverImpl(
    @Autowired private val locationBuilder: LocationBuilder,
    @Autowired private val storeBuilder: StoreBuilder
) : LocationStoreResolver {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    /**
     * If a location/collection id is given, grab it, resolve it to store id and append it to the map,
     * and remove the location/collection id from the map as current rms does not recognize those fields
     */
    override fun resolveStoreByLocation(queryParameters: Map<*, *>): List<Int>? {

        return when {
            // Both collection- & delivery location are given
            queryParameters.containsKey(QueryParametersResolverImpl.COLLECTION_LOCATION_KEY)
                    && queryParameters.containsKey(QueryParametersResolverImpl.DELIVERY_LOCATION_KEY) -> {
                logger.debug("Both collection- & delivery location are given")

                val deliveryLocationId =
                    (queryParameters[QueryParametersResolverImpl.DELIVERY_LOCATION_KEY] as String).toInt()
                val location = locationBuilder.getAllLocations().find { location -> location.id == deliveryLocationId }

                location?.storeIds?.toList()
            }

            // Only collection location is given
            queryParameters.containsKey(QueryParametersResolverImpl.COLLECTION_LOCATION_KEY)
                    && !queryParameters.containsKey(QueryParametersResolverImpl.DELIVERY_LOCATION_KEY) -> {
                logger.debug("Only collection location is given")

                storeBuilder.getAllStoreIds().toList()
            }

            // Only delivery location is given
            !queryParameters.containsKey(QueryParametersResolverImpl.COLLECTION_LOCATION_KEY)
                    && queryParameters.containsKey(QueryParametersResolverImpl.DELIVERY_LOCATION_KEY) -> {
                logger.debug("Only delivery location is given")

                val deliveryLocationId =
                    (queryParameters[QueryParametersResolverImpl.DELIVERY_LOCATION_KEY] as String).toInt()
                val location = locationBuilder.getAllLocations().find { location -> location.id == deliveryLocationId }

                location?.storeIds?.toList()
            }

            // Both collection- & delivery location are not given
            !queryParameters.containsKey(QueryParametersResolverImpl.COLLECTION_LOCATION_KEY)
                    && !queryParameters.containsKey(QueryParametersResolverImpl.DELIVERY_LOCATION_KEY) -> {
                logger.debug("Both collection- & delivery location are not given")

                storeBuilder.getAllStoreIds().toList()
            }

            else -> {
                logger.warn("No input parameters given to resolve to a location and map to corresponding stores")

                null
            }
        }
    }

}

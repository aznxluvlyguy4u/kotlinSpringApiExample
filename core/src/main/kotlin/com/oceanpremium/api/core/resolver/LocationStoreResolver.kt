package com.oceanpremium.api.core.resolver

import com.oceanpremium.api.core.currentrms.builder.LocationBuilder
import com.oceanpremium.api.core.currentrms.builder.StoreBuilder
import com.oceanpremium.api.core.model.Store
import com.oceanpremium.api.core.model.Stores
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import javax.ws.rs.BadRequestException

interface LocationStoreResolver {
    fun resolveStoresByLocation(queryParameters: Map<String, String>): Stores?
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
    @Throws(BadRequestException::class)
    override fun resolveStoresByLocation(queryParameters: Map<String, String>): Stores? {

        return when {
            // Both collection- & delivery location are given
            queryParameters.containsKey(QueryParametersResolverImpl.COLLECTION_LOCATION_KEY)
                    && queryParameters.containsKey(QueryParametersResolverImpl.DELIVERY_LOCATION_KEY) -> {

                logger.debug("Both collection- & delivery location are given (only using delivery location)")

                val allStores: MutableList<Store> = mutableListOf()
                val deliveryLocationId = (queryParameters[QueryParametersResolverImpl.DELIVERY_LOCATION_KEY] as String).toInt()
                val deliveryLocation = locationBuilder.findById(deliveryLocationId)

                if (deliveryLocation != null) {
                    allStores.addAll(deliveryLocation.nativeStores.sortedWith(compareBy { it.id }))
                    allStores.addAll(deliveryLocation.alternativeStores.sortedWith(compareBy { it.id }))
                    allStores.addAll(deliveryLocation.grayStores.sortedWith(compareBy { it.id }))
                    allStores.addAll(deliveryLocation.newItemsStores.sortedWith(compareBy { it.id }))
                }

                return Stores(
                    deliveryLocation?.nativeStores,
                    deliveryLocation?.alternativeStores,
                    deliveryLocation?.grayStores,
                    deliveryLocation?.newItemsStores,
                    allStores
                )
            }

            // Only collection location is given
            queryParameters.containsKey(QueryParametersResolverImpl.COLLECTION_LOCATION_KEY)
                    && !queryParameters.containsKey(QueryParametersResolverImpl.DELIVERY_LOCATION_KEY) -> {
                logger.debug("Only collection location is given")

                return Stores(
                    native = storeBuilder.getAllStores(),
                    all = storeBuilder.getAllStores()
                )
            }

            // Only delivery location is given
            !queryParameters.containsKey(QueryParametersResolverImpl.COLLECTION_LOCATION_KEY)
                    && queryParameters.containsKey(QueryParametersResolverImpl.DELIVERY_LOCATION_KEY) -> {
                logger.debug("Only delivery location is given")

                val deliveryLocationId =
                    (queryParameters[QueryParametersResolverImpl.DELIVERY_LOCATION_KEY] as String).toInt()
                val deliveryLocation =
                    locationBuilder.getAllLocations().find { location -> location.id == deliveryLocationId }

                val allStores: MutableList<Store> = mutableListOf()

                if (deliveryLocation != null) {
                    allStores.addAll(deliveryLocation.nativeStores.sortedWith(compareBy { it.id }))
                    allStores.addAll(deliveryLocation.alternativeStores.sortedWith(compareBy { it.id }))
                    allStores.addAll(deliveryLocation.grayStores.sortedWith(compareBy { it.id }))
                    allStores.addAll(deliveryLocation.newItemsStores.sortedWith(compareBy { it.id }))
                }

                return Stores(
                    deliveryLocation?.nativeStores,
                    deliveryLocation?.alternativeStores,
                    deliveryLocation?.grayStores,
                    deliveryLocation?.newItemsStores,
                    allStores
                )
            }

            // Both collection- & delivery location are not given
            !queryParameters.containsKey(QueryParametersResolverImpl.COLLECTION_LOCATION_KEY)
                    && !queryParameters.containsKey(QueryParametersResolverImpl.DELIVERY_LOCATION_KEY) -> {
                logger.debug("Both collection- & delivery location are not given")


                return Stores(
                    native = storeBuilder.getAllStores(),
                    all = storeBuilder.getAllStores()
                )
            }

            else -> {
                logger.warn("No input parameters given to resolve to a location and map to corresponding stores")

                null
            }
        }
    }

}

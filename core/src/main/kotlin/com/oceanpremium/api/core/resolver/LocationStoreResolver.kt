package com.oceanpremium.api.core.resolver

import com.oceanpremium.api.core.currentrms.builder.LocationBuilder
import com.oceanpremium.api.core.currentrms.builder.Store
import com.oceanpremium.api.core.currentrms.builder.StoreBuilder
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired

interface LocationStoreResolver {
    fun resolveStoresByLocation(queryParameters: Map<String, String>): WrappedStores?
}

class WrappedStores (
    var nativeStores: List<Store>?,
    var alternativeStores: List<Store>?,
    var grayStores: List<Store>?,
    var newItemStores: List<Store>?,
    var allStores: List<Store>?
)

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
    override fun resolveStoresByLocation(queryParameters: Map<String, String>) : WrappedStores? {

        return when {
            // Both collection- & delivery location are given
            queryParameters.containsKey(QueryParametersResolverImpl.COLLECTION_LOCATION_KEY)
                    && queryParameters.containsKey(QueryParametersResolverImpl.DELIVERY_LOCATION_KEY) -> {

                logger.debug("Both collection- & delivery location are given (only using delivery location)")

                val deliveryLocationId =
                    (queryParameters[QueryParametersResolverImpl.DELIVERY_LOCATION_KEY] as String).toInt()

                val deliveryLocation = locationBuilder.getAllLocations().find { location
                    ->
                    location.id == deliveryLocationId
                }

                val wrappedStores = WrappedStores(listOf(), listOf(), listOf(), listOf(), listOf())
                val allStores: MutableList<Store> = mutableListOf()
                if (deliveryLocation?.nativeStores != null) allStores.addAll(deliveryLocation.nativeStores)
                if (deliveryLocation?.alternativeStores != null) allStores.addAll(deliveryLocation.alternativeStores)
                if (deliveryLocation?.grayStores != null) allStores.addAll(deliveryLocation.grayStores)
                if (deliveryLocation?.newItemsStores != null) allStores.addAll(deliveryLocation.newItemsStores)

                wrappedStores.nativeStores = deliveryLocation?.nativeStores
                wrappedStores.alternativeStores = deliveryLocation?.alternativeStores
                wrappedStores.grayStores = deliveryLocation?.grayStores
                wrappedStores.newItemStores = deliveryLocation?.newItemsStores
                wrappedStores.allStores = allStores

                return  wrappedStores

            }

            // Only collection location is given
            queryParameters.containsKey(QueryParametersResolverImpl.COLLECTION_LOCATION_KEY)
                    && !queryParameters.containsKey(QueryParametersResolverImpl.DELIVERY_LOCATION_KEY) -> {
                logger.debug("Only collection location is given")

                val wrappedStores = WrappedStores(listOf(), null, null, null, null)
                wrappedStores.nativeStores = storeBuilder.getAllStores()

                return  wrappedStores
            }

            // Only delivery location is given
            !queryParameters.containsKey(QueryParametersResolverImpl.COLLECTION_LOCATION_KEY)
                    && queryParameters.containsKey(QueryParametersResolverImpl.DELIVERY_LOCATION_KEY) -> {
                logger.debug("Only delivery location is given")

                val deliveryLocationId =
                    (queryParameters[QueryParametersResolverImpl.DELIVERY_LOCATION_KEY] as String).toInt()
                val deliveryLocation = locationBuilder.getAllLocations().find { location -> location.id == deliveryLocationId }

                val wrappedStores = WrappedStores(listOf(), listOf(), listOf(), listOf(), listOf())
                val allStores: MutableList<Store> = mutableListOf()
                if (deliveryLocation?.nativeStores != null) allStores.addAll(deliveryLocation.nativeStores)
                if (deliveryLocation?.alternativeStores != null) allStores.addAll(deliveryLocation.alternativeStores)
                if (deliveryLocation?.grayStores != null) allStores.addAll(deliveryLocation.grayStores)
                if (deliveryLocation?.newItemsStores != null) allStores.addAll(deliveryLocation.newItemsStores)

                wrappedStores.nativeStores = deliveryLocation?.nativeStores
                wrappedStores.alternativeStores = deliveryLocation?.alternativeStores
                wrappedStores.grayStores = deliveryLocation?.grayStores
                wrappedStores.newItemStores = deliveryLocation?.newItemsStores
                wrappedStores.allStores = allStores

                return  wrappedStores
            }

            // Both collection- & delivery location are not given
            !queryParameters.containsKey(QueryParametersResolverImpl.COLLECTION_LOCATION_KEY)
                    && !queryParameters.containsKey(QueryParametersResolverImpl.DELIVERY_LOCATION_KEY) -> {
                logger.debug("Both collection- & delivery location are not given")

                val wrappedStores = WrappedStores(listOf(), null, null, null, null)
                wrappedStores.nativeStores = storeBuilder.getAllStores()

                return  wrappedStores
            }

            else -> {
                logger.warn("No input parameters given to resolve to a location and map to corresponding stores")

                null
            }
        }
    }

}

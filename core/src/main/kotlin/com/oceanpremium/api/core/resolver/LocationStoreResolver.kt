package com.oceanpremium.api.core.resolver

import com.oceanpremium.api.core.currentrms.builder.LocationBuilder
import com.oceanpremium.api.core.currentrms.builder.StoreBuilder
import com.oceanpremium.api.core.exception.throwable.BadRequestException
import com.oceanpremium.api.core.model.Store
import com.oceanpremium.api.core.model.Stores
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired

interface LocationStoreResolver {
    fun resolveStoresByLocation(queryParameters: Map<String, String>): Stores?
}

class LocationStoreResolverImpl(
    @Autowired private val locationBuilder: LocationBuilder,
    @Autowired private val storeBuilder: StoreBuilder
) : LocationStoreResolver {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
        private val idRegex = "\\d+".toRegex()
    }

    /**
     * If a location/collection id is given, grab it, resolve it to store id and append it to the map,
     * and remove the location/collection id from the map as current rms does not recognize those fields
     */
    @Throws(BadRequestException::class)
    override fun resolveStoresByLocation(queryParameters: Map<String, String>): Stores? {

        validateQueryParameter(queryParameters)

        return when {
            // Both collection- & delivery location are given
            queryParameters.containsKey(QueryParametersResolverImpl.COLLECTION_LOCATION_KEY)
                    && queryParameters.containsKey(QueryParametersResolverImpl.DELIVERY_LOCATION_KEY) -> {

                logger.debug("Both collection- & delivery location are given (only using delivery location)")

                val allStores: MutableList<Store> = mutableListOf()
                val deliveryLocationId =
                    (queryParameters[QueryParametersResolverImpl.DELIVERY_LOCATION_KEY] as String).toInt()
                val deliveryLocation = locationBuilder.findById(deliveryLocationId)

                if (deliveryLocation != null) {
                    allStores.addAll(deliveryLocation.nativeStores.sortedBy { it.id })
                    allStores.addAll(deliveryLocation.alternativeStores.sortedBy { it.id })
                    allStores.addAll(deliveryLocation.grayStores.sortedBy { it.id })
                    allStores.addAll(deliveryLocation.newItemsStores.sortedBy { it.id } )
                }

                logger.debug("Resolved storeIds: ${allStores.sortedBy { it.id }} for delivery location: $deliveryLocation")

                return Stores(
                    deliveryLocation?.nativeStores,
                    deliveryLocation?.alternativeStores,
                    deliveryLocation?.grayStores,
                    deliveryLocation?.newItemsStores,
                    allStores.sortedBy { it.id }
                )
            }

            // Only collection location is given
            queryParameters.containsKey(QueryParametersResolverImpl.COLLECTION_LOCATION_KEY)
                    && !queryParameters.containsKey(QueryParametersResolverImpl.DELIVERY_LOCATION_KEY) -> {
                logger.debug("Only collection location is given")

                return Stores(
                    native = storeBuilder.getAllStores().sortedBy { it.id },
                    all = storeBuilder.getAllStores().sortedBy { it.id }
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
                    allStores.addAll(deliveryLocation.nativeStores.sortedBy { it.id })
                    allStores.addAll(deliveryLocation.alternativeStores.sortedBy { it.id })
                    allStores.addAll(deliveryLocation.grayStores.sortedBy { it.id })
                    allStores.addAll(deliveryLocation.newItemsStores.sortedBy { it.id })
                }

                logger.debug("Resolved storeIds: ${allStores.sortedBy { it.id }} for delivery location: $deliveryLocation")

                return Stores(
                    deliveryLocation?.nativeStores,
                    deliveryLocation?.alternativeStores,
                    deliveryLocation?.grayStores,
                    deliveryLocation?.newItemsStores,
                    allStores.sortedBy { it.id }
                )
            }

            // Both collection- & delivery location are not given
            !queryParameters.containsKey(QueryParametersResolverImpl.COLLECTION_LOCATION_KEY)
                    && !queryParameters.containsKey(QueryParametersResolverImpl.DELIVERY_LOCATION_KEY) -> {
                logger.debug("Both collection- & delivery location are not given")

                return Stores(
                    native = storeBuilder.getAllStores().sortedBy { it.id },
                    all = storeBuilder.getAllStores().sortedBy { it.id }
                )
            }

            else -> {
                logger.warn("No input parameters given to resolve to a location and map to corresponding stores")

                null
            }
        }
    }

    private fun validateQueryParameter(queryParameters: Map<String, String>) {

        when {
            queryParameters.containsKey(QueryParametersResolverImpl.DELIVERY_LOCATION_KEY)
                    && !(queryParameters[QueryParametersResolverImpl.DELIVERY_LOCATION_KEY] as String).matches(idRegex) ->
                throw BadRequestException(
                    "Query parameter value for key: ${QueryParametersResolverImpl.DELIVERY_LOCATION_KEY}," +
                            " is NOT valid. Provide a valid ID of type Integer, for example: " +
                            "${QueryParametersResolverImpl.DELIVERY_LOCATION_KEY}=1234"
                )
            queryParameters.containsKey(QueryParametersResolverImpl.COLLECTION_LOCATION_KEY)
                    && !(queryParameters[QueryParametersResolverImpl.COLLECTION_LOCATION_KEY] as String).matches(idRegex)
            -> throw BadRequestException(
                "Query parameter value for key: ${QueryParametersResolverImpl.DELIVERY_LOCATION_KEY}," +
                        "is NOT valid. Provide a valid ID of type Integer, for example: " +
                        "${QueryParametersResolverImpl.COLLECTION_LOCATION_KEY}=1234"
            )
        }
    }

}

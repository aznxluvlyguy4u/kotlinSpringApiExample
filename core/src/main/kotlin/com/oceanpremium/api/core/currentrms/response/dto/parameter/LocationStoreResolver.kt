package com.oceanpremium.api.core.currentrms.response.dto.parameter

interface LocationStoreResolver {
    fun resolveStoreByLocation(queryParameters: Map<*,*>): List<Int>
}

class LocationStoreResolverImpl : LocationStoreResolver {

    private val mockedList = listOf(1, 2, 3, 4, 5, 6, 7 , 8)
    /**
     * If a location/collection id is given, grab it, resolve it to store id and append it to the map,
     * and remove the location/collection id from the map as current rms does not recognize those fields
     */
    override fun resolveStoreByLocation(queryParameters: Map<*,*>): List<Int> {

        // Both collection- & delivery location are given
        if (queryParameters.containsKey(QueryParametersResolverImpl.COLLECTION_LOCATION_KEY)
            && queryParameters.containsKey(QueryParametersResolverImpl.DELIVERY_LOCATION_KEY)) {
//            val collectionLocationId = (queryParameters[QueryParametersResolverImpl.COLLECTION_LOCATION_KEY] as String).toInt()
//            val deliveryLocationId = (queryParameters[QueryParametersResolverImpl.DELIVERY_LOCATION_KEY] as String).toInt()

            return mockedList
        }

        // Only collection location is given
        if (queryParameters.containsKey(QueryParametersResolverImpl.COLLECTION_LOCATION_KEY)
            && !queryParameters.containsKey(QueryParametersResolverImpl.DELIVERY_LOCATION_KEY)) {
//            val collectionLocationId = (queryParameters[QueryParametersResolverImpl.COLLECTION_LOCATION_KEY] as String).toInt()

            return mockedList
        }

        // Only delivery location is given
        if (!queryParameters.containsKey(QueryParametersResolverImpl.COLLECTION_LOCATION_KEY)
            && queryParameters.containsKey(QueryParametersResolverImpl.DELIVERY_LOCATION_KEY))  {
//            val deliveryLocationId = (queryParameters[QueryParametersResolverImpl.DELIVERY_LOCATION_KEY] as String).toInt()

            return mockedList
        }

        // Both collection- & delivery location are not given
        if (!queryParameters.containsKey(QueryParametersResolverImpl.COLLECTION_LOCATION_KEY)
            && !queryParameters.containsKey(QueryParametersResolverImpl.DELIVERY_LOCATION_KEY)) {

            return mockedList
        }

        return listOf()
    }

}

package com.oceanpremium.api.locations.builder

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

class Location(var name: String? = null, var id: Int = 0,  var regionId: Int = 0, var stores: MutableList<Store> = mutableListOf()) {
    fun addStore(store: Store) {
        stores.add(store)
    }
}

class Region(private val name: String, var id: Int = 0, val locations: MutableList<Location> = mutableListOf(), var stores: MutableList<Store> = mutableListOf()) {

    fun addLocation(location: Location) {
        location.regionId = id

        if (location.name == null) {
            location.name = name
        }

        locations.add(location)
    }

    fun addStore(store: Store) {
        stores.add(store)
    }
}

class Store(val name: String, var id: Int = 0, private val regions: MutableList<Region> = mutableListOf()) {

    fun addRegion(region: List<Region>) {
        regions.addAll(region)
    }
}

interface LocationBuilder {
    fun getAllLocations(): List<Location>
}

@Service
class LocationBuilderImpl(
    @Autowired private val regionBuilder: RegionBuilder,
    @Autowired private val storeBuilder: StoreBuilder
): LocationBuilder {

    override fun getAllLocations(): List<Location> {

        val regions = regionBuilder.getAllRegions()
        val locations: MutableList<Location> = mutableListOf()

        var j = 1

        regions.forEach { region ->
            region.locations.forEach { location ->
                location.id = j
                location.regionId = region.id
                location.stores = region.stores
                j++
            }

            locations.addAll(region.locations)
        }

        return locations
    }
}

package com.oceanpremium.api.locations.builder

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

class Location(var name: String? = null, var id: Int = 0,  var storeId: Int = 0)

class Store(private val name: String, var id: Int = 0, val locations: MutableList<Location> = mutableListOf()) {

    fun addLocation(location: Location) {
        location.storeId = id

        if (location.name == null) {
            location.name = name
        }

        locations.add(location)
    }
}

interface LocationBuilder {
    fun getAllLocations(): List<Location>
}

@Service
class LocationBuilderImpl(@Autowired private val storeBuilder: StoreBuilder): LocationBuilder {

    override fun getAllLocations(): List<Location> {

        val stores = storeBuilder.getAllStores()
        val locations: MutableList<Location> = mutableListOf()

        var j = 1

        stores.forEach { store ->
            store.locations.forEach { location ->
                location.id = j
                location.storeId = store.id
                j++
            }

            locations.addAll(store.locations)
        }

        return locations.sortedWith(compareBy({ it.name }))
    }
}
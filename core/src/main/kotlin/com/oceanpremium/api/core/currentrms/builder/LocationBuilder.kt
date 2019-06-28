package com.oceanpremium.api.core.currentrms.builder

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

class Location(
    var name: String? = null,
    var id: Int = 0,
    var regionId: Int = 0,
    var storeIds: SortedSet<Int> = sortedSetOf(),
    var nativeStores: MutableList<Store> = mutableListOf(),
    var alternativeStores: MutableList<Store> = mutableListOf(),
    var grayStores: MutableList<Store> = mutableListOf(),
    var newItemsStores: MutableList<Store> = mutableListOf()
) {
    fun addNativeStore(store: Store) {
        nativeStores.add(store)
    }

    fun addAlternativeStore(store: Store) {
        alternativeStores.add(store)
    }

    fun addGrayStore(store: Store) {
        grayStores.add(store)
    }

    fun addNewItemsStore(store: Store) {
        newItemsStores.add(store)
    }
}

class Region(
    private val name: String,
    var id: Int = 0,
    val locations: MutableList<Location> = mutableListOf()
) {

    fun addLocation(location: Location) {
        location.regionId = id

        if (location.name == null) {
            location.name = name
        }

        locations.add(location)
    }
}

class Store(
    @JsonIgnore
    val name: String,
    var id: Int = 0,
    var delayInHours: Int? = null,
    var deliveryCostInEuro: Int? = null
)

interface LocationBuilder {
    fun getAllLocations(): List<Location>
}

@Service
class LocationBuilderImpl(
    @Autowired private val regionBuilder: RegionBuilder
): LocationBuilder {

    override fun getAllLocations(): List<Location> {

        val regions = regionBuilder.getAllRegions()
        val locations: MutableList<Location> = mutableListOf()

        var j = 1

        regions.forEach { region ->
            region.locations.forEach { location ->
                location.id = j
                location.regionId = region.id
                j++
            }

            locations.addAll(region.locations)
        }

        return locations.sortedWith(compareBy({ it.name }))
    }
}

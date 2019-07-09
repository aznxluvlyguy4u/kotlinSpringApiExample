package com.oceanpremium.api.core.currentrms.builder

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

class Location(
    var name: String? = null,
    var id: Int = 0,
    var regionId: Int = 0,
    var nativeStores: MutableList<Store> = mutableListOf(),
    var alternativeStores: MutableList<Store> = mutableListOf(),
    var grayStores: MutableList<Store> = mutableListOf(),
    var newItemsStores: MutableList<Store> = mutableListOf()
)

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
    var minimumDeliveryHours: Int? = null,
    var deliveryCost: Double? = null
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

        return locations.sortedWith(compareBy { it.name })
    }
}

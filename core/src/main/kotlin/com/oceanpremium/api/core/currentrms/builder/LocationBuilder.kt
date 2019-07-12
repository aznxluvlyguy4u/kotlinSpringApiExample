package com.oceanpremium.api.core.currentrms.builder

import com.oceanpremium.api.core.enum.WarehouseStoreType
import com.oceanpremium.api.core.model.Store
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


interface LocationBuilder {
    fun getAllLocations(): List<Location>
    fun findById(locationId: Int): Location?
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

        categorizeWarehouse(locations)

        return locations.sortedWith(compareBy { it.name })
    }

    override fun findById(locationId: Int): Location? {
        return getAllLocations().firstOrNull { it.id == locationId }
    }

    private fun categorizeWarehouse(locations: List<Location>) {
        locations.forEach { location ->
            location.nativeStores.forEach { store ->
                store.type = WarehouseStoreType.NATIVE
            }

            location.alternativeStores.forEach {store ->
                store.type = WarehouseStoreType.ALTERNATIVE
            }

            location.grayStores.forEach {store ->
                store.type = WarehouseStoreType.GRAY
            }

            location.newItemsStores.forEach {store ->
                store.type = WarehouseStoreType.NEW_ITEMS
            }
        }
    }

}

package com.oceanpremium.api.core.currentrms.builder

import com.oceanpremium.api.core.model.Store
import org.springframework.stereotype.Service
import java.util.*

interface StoreBuilder {
    fun getAllStores(): List<Store>
    fun getAllStoreIds(): SortedSet<Int>
}

/**
 * Source @link: https://docs.google.com/spreadsheets/d/1Jpx59haz_8cZw0uGUBSZX47mQDPv0V3OvgJUtr--JzA/edit#gid=0
 */
@Service
class StoreBuilderImpl : StoreBuilder {

    fun buildPalmaNG(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store(
            3,
            "Palma de Mallorca (NG)",
            minimumDeliveryHours,
            deliveryCost?.toDouble()
        )
    }

    fun buildM55(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store(
            5,
            "M55",
            minimumDeliveryHours,
            deliveryCost?.toDouble()
        )
    }

    fun buildStMaarten(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store(
            9,
            "St. Maarten (DSM)",
            minimumDeliveryHours,
            deliveryCost?.toDouble()
        )
    }

    fun buildAthensH360(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store(
            10,
            "Athens (H360)",
            minimumDeliveryHours,
            deliveryCost?.toDouble()
        )
    }

    fun buildOlbiaNA(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store(
            11,
            "Olbia (NA)",
            minimumDeliveryHours,
            deliveryCost?.toDouble()
        )
    }

    fun buildFortLauderdale(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store(
            13,
            "Fort Lauderdale (YC)",
            minimumDeliveryHours,
            deliveryCost?.toDouble()
        )
    }

    fun buildAntigua(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store(
            14,
            "Antigua (AC)",
            minimumDeliveryHours,
            deliveryCost?.toDouble()
        )
    }

    fun buildNapoli(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store(
            15,
            "Napoli (MLF)",
            minimumDeliveryHours,
            deliveryCost?.toDouble()
        )
    }

    fun buildGenova(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store(
            16,
            "Genova (Maremoto)",
            minimumDeliveryHours,
            deliveryCost?.toDouble()
        )
    }

    fun buildAntibes(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store(
            18,
            "Antibes",
            minimumDeliveryHours,
            deliveryCost?.toDouble()
        )
    }

    fun buildPalmaED(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store(
            19,
            "Palma de Mallorca (ED)",
            minimumDeliveryHours,
            deliveryCost?.toDouble()
        )
    }

    fun buildIbiza(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store(
            20,
            "Ibiza (Rodriquez)",
            minimumDeliveryHours,
            deliveryCost?.toDouble()
        )
    }

    fun buildOlbiaSYS(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store(
            21,
            "Olbia (SYS)",
            minimumDeliveryHours,
            deliveryCost?.toDouble()
        )
    }

    fun buildNewItemsStore(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store(
            22,
            "New Items Store",
            minimumDeliveryHours,
            deliveryCost?.toDouble()
        )
    }

    fun buildPalermo(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store(
            23,
            "Palermo (Artemis)",
            minimumDeliveryHours,
            deliveryCost?.toDouble()
        )
    }

    fun buildRiposto(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store(
            24,
            "Riposto (Artemis)",
            minimumDeliveryHours,
            deliveryCost?.toDouble()
        )
    }

    fun buildDubrovnikBWA(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store(
            24, "Dubrovnik (BWA)", minimumDeliveryHours, deliveryCost?.toDouble()
        )
    }

    fun buildMalta(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store(
            25,
            "Malta",
            minimumDeliveryHours,
            deliveryCost?.toDouble()
        )
    }

    fun buildAthensSotiris(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store(
            26,
            "Athens (Sotiris)",
            minimumDeliveryHours,
            deliveryCost?.toDouble()
        )
    }

    fun buildCorfu(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store(
            27,
            "Corfu",
            minimumDeliveryHours,
            deliveryCost?.toDouble()
        )
    }

    fun buildKosA1(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store(
            28,
            "Kos (A1)",
            minimumDeliveryHours,
            deliveryCost?.toDouble()
        )
    }

    fun buildRhodes(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store(
            29,
            "Rhodes (Roditis)",
            minimumDeliveryHours,
            deliveryCost?.toDouble()
        )
    }

    fun buildVenice(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store(
            30,
            "Venice (Katarina)",
            minimumDeliveryHours,
            deliveryCost?.toDouble()
        )
    }

    fun buildZadar(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store(
            31,
            "Zadar (Alessia)",
            minimumDeliveryHours,
            deliveryCost?.toDouble()
        )
    }

    fun buildSplit(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store(
            32,
            "Split (Luka)",
            minimumDeliveryHours,
            deliveryCost?.toDouble()
        )
    }

    fun buildDubrovnikKristijan(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store(
            33,
            "Dubrovnik (Kristijan)",
            minimumDeliveryHours,
            deliveryCost?.toDouble()
        )
    }

    fun buildBCM(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store(
            35,
            "BCM (Kotor)",
            minimumDeliveryHours,
            deliveryCost?.toDouble()
        )
    }

    fun buildTivat(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store(
            36,
            "Tivat (BWA)",
            minimumDeliveryHours,
            deliveryCost?.toDouble()
        )
    }

    fun buildMale(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store(
            37,
            "Male",
            minimumDeliveryHours,
            deliveryCost?.toDouble()
        )
    }

    override fun getAllStores(): List<Store> {
        val allStores: MutableList<Store> = mutableListOf()

        allStores.add(buildPalmaNG())
        allStores.add(buildM55())
        allStores.add(buildStMaarten())
        allStores.add(buildAthensH360())
        allStores.add(buildOlbiaNA())
        allStores.add(buildFortLauderdale())
        allStores.add(buildAntigua())
        allStores.add(buildNapoli())
        allStores.add(buildGenova())
        allStores.add(buildAntibes())
        allStores.add(buildPalmaED())
        allStores.add(buildIbiza())
        allStores.add(buildOlbiaSYS())
        allStores.add(buildNewItemsStore())
        allStores.add(buildPalermo())
        allStores.add(buildRiposto())
        allStores.add(buildDubrovnikBWA())
        allStores.add(buildMalta())
        allStores.add(buildAthensSotiris())
        allStores.add(buildCorfu())
        allStores.add(buildKosA1())
        allStores.add(buildRhodes())
        allStores.add(buildVenice())
        allStores.add(buildZadar())
        allStores.add(buildSplit())
        allStores.add(buildDubrovnikKristijan())
        allStores.add(buildBCM())
        allStores.add(buildTivat())
        allStores.add(buildMale())

        return allStores
    }

    override fun getAllStoreIds(): SortedSet<Int> {
        val allStoreIds: SortedSet<Int> = sortedSetOf()
        getAllStores().forEach { allStoreIds.add(it.id) }

        return allStoreIds
    }
}

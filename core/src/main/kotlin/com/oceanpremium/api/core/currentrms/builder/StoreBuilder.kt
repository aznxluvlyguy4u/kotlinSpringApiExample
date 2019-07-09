package com.oceanpremium.api.core.currentrms.builder

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

    fun buildM55(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store("M55", 5, minimumDeliveryHours, deliveryCost?.toDouble())
    }

    fun buildAntibes(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store("Antibes", 18, minimumDeliveryHours, deliveryCost?.toDouble())
    }

    fun buildPalmaED(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store("Palma de Mallorca (ED)", 19, minimumDeliveryHours, deliveryCost?.toDouble())
    }

    fun buildPalmaNG(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store("Palma de Mallorca (NG)", 3, minimumDeliveryHours, deliveryCost?.toDouble())
    }

    fun buildIbiza(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store("Ibiza (Rodriquez)", 20, minimumDeliveryHours, deliveryCost?.toDouble())
    }

    fun buildOlbiaNA(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store("Olbia (NA)", 11, minimumDeliveryHours, deliveryCost?.toDouble())
    }

    fun buildOlbiaSYS(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store("Olbia (SYS)", 21, minimumDeliveryHours, deliveryCost?.toDouble())
    }

    fun buildGenova(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store("Genova (Maremoto)", 16, minimumDeliveryHours, deliveryCost?.toDouble())
    }

    fun buildNapoli(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store("Napoli (MLF)", 15, minimumDeliveryHours, deliveryCost?.toDouble())
    }

    fun buildPalermo(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store("Palermo (Artemis)", 23, minimumDeliveryHours, deliveryCost?.toDouble())
    }

    fun buildRiposto(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store("Riposto (Artemis)", 24, minimumDeliveryHours, deliveryCost?.toDouble())
    }

    fun buildMalta(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store("Malta", 25, minimumDeliveryHours, deliveryCost?.toDouble())
    }

    fun buildAthensSotiris(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store("Athens (Sotiris)", 26, minimumDeliveryHours, deliveryCost?.toDouble())
    }

    fun buildAthensH360(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store("Athens (H360)", 10, minimumDeliveryHours, deliveryCost?.toDouble())
    }

    fun buildCorfu(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store("Corfu", 27, minimumDeliveryHours, deliveryCost?.toDouble())
    }

    fun buildKosA1(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store("Kos (A1)", 28, minimumDeliveryHours, deliveryCost?.toDouble())
    }

    fun buildRhodes(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store("Rhodes (Roditis)", 29, minimumDeliveryHours, deliveryCost?.toDouble())
    }

    fun buildVenice(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store("Venice (Katarina)", 30, minimumDeliveryHours, deliveryCost?.toDouble())
    }

    fun buildZadar(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store("Zadar (Alessia)", 31, minimumDeliveryHours, deliveryCost?.toDouble())
    }

    fun buildSplit(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store("Split (Luka)", 32, minimumDeliveryHours, deliveryCost?.toDouble())
    }

    fun buildDubrovnikKristijan(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store("Dubrovnik (Kristijan)", 33, minimumDeliveryHours, deliveryCost?.toDouble())
    }

    fun buildDubrovnikBWA(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store("Dubrovnik (BWA)", 24, minimumDeliveryHours, deliveryCost?.toDouble())
    }

    fun buildBCM(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store("BCM (Kotor)", 35, minimumDeliveryHours, deliveryCost?.toDouble())
    }

    fun buildTivat(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store("Tivat (BWA)", 36, minimumDeliveryHours, deliveryCost?.toDouble())
    }

    fun buildAntigua(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store("Antigua (AC)", 14, minimumDeliveryHours, deliveryCost?.toDouble())
    }

    fun buildStMaarten(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store("St. Maarten (DSM)", 9, minimumDeliveryHours, deliveryCost?.toDouble())
    }

    fun buildFortLauderdale(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store("Fort Lauderdale (YC)", 13, minimumDeliveryHours, deliveryCost?.toDouble())
    }

    fun buildMale(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store("Male", 37, minimumDeliveryHours, deliveryCost?.toDouble())
    }

    fun buildNewItemsStore(minimumDeliveryHours: Int? = null, deliveryCost: Int? = null): Store {
        return Store("New Items Store", 22, minimumDeliveryHours, deliveryCost?.toDouble())
    }

    override fun getAllStores(): List<Store> {
        val allStores: MutableList<Store> = mutableListOf()

        allStores.add(buildM55())
        allStores.add(buildAntibes())
        allStores.add(buildPalmaED())
        allStores.add(buildPalmaNG())
        allStores.add(buildIbiza())
        allStores.add(buildOlbiaNA())
        allStores.add(buildOlbiaSYS())
        allStores.add(buildGenova())
        allStores.add(buildNapoli())
        allStores.add(buildPalermo())
        allStores.add(buildRiposto())
        allStores.add(buildMalta())
        allStores.add(buildAthensSotiris())
        allStores.add(buildAthensH360())
        allStores.add(buildCorfu())
        allStores.add(buildKosA1())
        allStores.add(buildRhodes())
        allStores.add(buildVenice())
        allStores.add(buildZadar())
        allStores.add(buildSplit())
        allStores.add(buildDubrovnikKristijan())
        allStores.add(buildDubrovnikBWA())
        allStores.add(buildBCM())
        allStores.add(buildTivat())
        allStores.add(buildAntigua())
        allStores.add(buildStMaarten())
        allStores.add(buildFortLauderdale())
        allStores.add(buildMale())
        allStores.add(buildNewItemsStore())

        return allStores
    }

    override fun getAllStoreIds(): SortedSet<Int> {
        val allStoreIds: SortedSet<Int> = sortedSetOf()

        allStoreIds.add(5)
        allStoreIds.add(18)
        allStoreIds.add(19)
        allStoreIds.add(3)
        allStoreIds.add(20)
        allStoreIds.add(11)
        allStoreIds.add(16)
        allStoreIds.add(15)
        allStoreIds.add(23)
        allStoreIds.add(24)
        allStoreIds.add(25)
        allStoreIds.add(26)
        allStoreIds.add(10)
        allStoreIds.add(27)
        allStoreIds.add(28)
        allStoreIds.add(29)
        allStoreIds.add(30)
        allStoreIds.add(31)
        allStoreIds.add(32)
        allStoreIds.add(33)
        allStoreIds.add(24)
        allStoreIds.add(35)
        allStoreIds.add(36)
        allStoreIds.add(14)
        allStoreIds.add(9)
        allStoreIds.add(13)
        allStoreIds.add(37)
        allStoreIds.add(22)

        return allStoreIds
    }
}

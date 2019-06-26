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
class StoreBuilderImpl: StoreBuilder {

    fun buildM55(delayInHours: Int? = null, deliveryCostInEuro: Int? = null): Store {
        return Store("M55", 5, delayInHours, deliveryCostInEuro)
    }

    fun buildAntibes(delayInHours: Int? = null, deliveryCostInEuro: Int? = null): Store {
        return Store("Antibes", 18, delayInHours, deliveryCostInEuro)
    }

    fun buildPalmaED(delayInHours: Int? = null, deliveryCostInEuro: Int? = null): Store {
        return Store("Palma de Mallorca (ED)", 19, delayInHours, deliveryCostInEuro)
    }

    fun buildPalmaNG(delayInHours: Int? = null, deliveryCostInEuro: Int? = null): Store {
        return Store("Palma de Mallorca (NG)", 3, delayInHours, deliveryCostInEuro)
    }

    fun buildIbiza(delayInHours: Int? = null, deliveryCostInEuro: Int? = null): Store {
        return Store("Ibiza (Rodriquez)", 20, delayInHours, deliveryCostInEuro)
    }

    fun buildOlbiaNA(delayInHours: Int? = null, deliveryCostInEuro: Int? = null): Store {
        return Store("Olbia (NA)", 11, delayInHours, deliveryCostInEuro)
    }

    fun buildOlbiaSYS(delayInHours: Int? = null, deliveryCostInEuro: Int? = null): Store {
        return Store("Olbia (SYS)", 21, delayInHours, deliveryCostInEuro)
    }

    fun buildGenova(delayInHours: Int? = null, deliveryCostInEuro: Int? = null): Store {
        return Store("Genova (Maremoto)", 16, delayInHours, deliveryCostInEuro)
    }

    fun buildNapoli(delayInHours: Int? = null, deliveryCostInEuro: Int? = null): Store {
        return Store("Napoli (MLF)", 15, delayInHours, deliveryCostInEuro)
    }

    fun buildPalermo(delayInHours: Int? = null, deliveryCostInEuro: Int? = null): Store {
        return Store("Palermo (Artemis)", 23, delayInHours, deliveryCostInEuro)
    }

    fun buildRiposto(delayInHours: Int? = null, deliveryCostInEuro: Int? = null): Store {
        return Store("Riposto (Artemis)", 24, delayInHours, deliveryCostInEuro)
    }

    fun buildMalta(delayInHours: Int? = null, deliveryCostInEuro: Int? = null): Store {
        return Store("Malta", 25, delayInHours, deliveryCostInEuro)
    }

    fun buildAthensSotiris(delayInHours: Int? = null, deliveryCostInEuro: Int? = null): Store {
        return Store("Athens (Sotiris)", 26, delayInHours, deliveryCostInEuro)
    }

    fun buildAthensH360(delayInHours: Int? = null, deliveryCostInEuro: Int? = null): Store {
        return Store("Athens (H360)", 10, delayInHours, deliveryCostInEuro)
    }

    fun buildCorfu(delayInHours: Int? = null, deliveryCostInEuro: Int? = null): Store {
        return Store("Corfu", 27, delayInHours, deliveryCostInEuro)
    }

    fun buildKosA1(delayInHours: Int? = null, deliveryCostInEuro: Int? = null): Store {
        return Store("Kos (A1)", 28, delayInHours, deliveryCostInEuro)
    }

    fun buildRhodes(delayInHours: Int? = null, deliveryCostInEuro: Int? = null): Store {
        return Store("Rhodes (Roditis)", 29, delayInHours, deliveryCostInEuro)
    }

    fun buildVenice(delayInHours: Int? = null, deliveryCostInEuro: Int? = null): Store {
        return Store("Venice (Katarina)", 30, delayInHours, deliveryCostInEuro)
    }

    fun buildZadar(delayInHours: Int? = null, deliveryCostInEuro: Int? = null): Store {
        return Store("Zadar (Alessia)", 31, delayInHours, deliveryCostInEuro)
    }

    fun buildSplit(delayInHours: Int? = null, deliveryCostInEuro: Int? = null): Store {
        return Store("Split (Luka)", 32, delayInHours, deliveryCostInEuro)
    }

    fun buildDubrovnikKristijan(delayInHours: Int? = null, deliveryCostInEuro: Int? = null): Store {
        return Store("Dubrovnik (Kristijan)", 33, delayInHours, deliveryCostInEuro)
    }

    fun buildDubrovnikBWA(delayInHours: Int? = null, deliveryCostInEuro: Int? = null): Store {
        return Store("Dubrovnik (BWA)", 24, delayInHours, deliveryCostInEuro)
    }

    fun buildBCM(delayInHours: Int? = null, deliveryCostInEuro: Int? = null): Store {
        return Store("BCM (Kotor)", 35, delayInHours, deliveryCostInEuro)
    }

    fun buildTivat(delayInHours: Int? = null, deliveryCostInEuro: Int? = null): Store {
        return Store("Tivat (BWA)", 36, delayInHours, deliveryCostInEuro)
    }

    fun buildAntigua(delayInHours: Int? = null, deliveryCostInEuro: Int? = null): Store {
        return Store("Antigua (AC)", 14, delayInHours, deliveryCostInEuro)
    }

    fun buildStMaarten(delayInHours: Int? = null, deliveryCostInEuro: Int? = null): Store {
        return Store("St. Maarten (DSM)", 9, delayInHours, deliveryCostInEuro)
    }

    fun buildFortLauderdale(delayInHours: Int? = null, deliveryCostInEuro: Int? = null): Store {
        return Store("Fort Lauderdale (YC)", 13, delayInHours, deliveryCostInEuro)
    }

    fun buildMale(delayInHours: Int? = null, deliveryCostInEuro: Int? = null): Store {
        return Store("Male", 37, delayInHours, deliveryCostInEuro)
    }

    fun buildNewItemsStore(delayInHours: Int? = null, deliveryCostInEuro: Int? = null): Store {
        return Store("New Items Store", 22, delayInHours, deliveryCostInEuro)
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

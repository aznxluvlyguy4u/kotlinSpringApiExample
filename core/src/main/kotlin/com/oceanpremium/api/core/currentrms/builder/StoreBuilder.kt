package com.oceanpremium.api.core.currentrms.builder

import org.springframework.beans.factory.annotation.Autowired
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
class StoreBuilderImpl(@Autowired private val regionBuilder: RegionBuilder): StoreBuilder {

    fun buildM55(): Store {
        return Store("M55", 5)
    }

    fun buildAntibes(): Store {
        return Store("Antibes", 18)
    }

    fun buildPalmaED(): Store {
        return Store("Palma de Mallorca (ED)", 19)
    }

    fun buildPalmaNG(): Store {
        return Store("Palma de Mallorca (NG)", 3)
    }

    fun buildIbiza(): Store {
        return Store("Ibiza (Rodriquez)", 20)
    }

    fun buildOlbiaNA(): Store {
        return Store("Olbia (NA)", 11)
    }

    fun buildGenova(): Store {
        return Store("Genova (Maremoto)", 16)
    }

    fun buildNapoli(): Store {
        return Store("Napoli (MLF)", 15)
    }

    fun buildPalermo(): Store {
        return Store("Palermo (Artemis)", 23)
    }

    fun buildRiposto(): Store {
        return Store("Riposto (Artemis)", 24)
    }

    fun buildMalta(): Store {
        return Store("Malta", 25)
    }

    fun buildAthensSotiris(): Store {
        return Store("Athens (Sotiris)", 26)
    }

    fun buildAthensH360(): Store {
        return Store("Athens (H360)", 10)
    }

    fun buildCorfu(): Store {
        return Store("Corfu", 27)
    }

    fun buildKosA1(): Store {
        return Store("Kos (A1)", 28)
    }

    fun buildRhodes(): Store {
        return Store("Rhodes (Roditis)", 29)
    }

    fun buildVenice(): Store {
        return Store("Venice (Katarina)", 30)
    }

    fun buildZadar(): Store {
        return Store("Zadar (Alessia)", 31)
    }

    fun buildSplit(): Store {
        return Store("Split (Luka)", 32)
    }

    fun buildDubrovnikKristijan(): Store {
        return Store("Dubrovnik (Kristijan)", 33)
    }

    fun buildDubrovnikBWA(): Store {
        return Store("Dubrovnik (BWA)", 24)
    }

    fun buildBCM(): Store {
        return Store("BCM (Kotor)", 35)
    }

    fun buildTivat(): Store {
        return Store("Tivat (BWA)", 36)
    }

    fun buildAntigua(): Store {
        return Store("Antigua (AC)", 14)
    }

    fun buildStMaarten(): Store {
        return Store("St. Maarten (DSM)", 9)
    }

    fun buildFortLauderdale(): Store {
        return Store("Fort Lauderdale (YC)", 13)
    }

    fun buildMale(): Store {
        return Store("Male", 37)
    }

    fun buildNewItemsStore(): Store {
        return Store("New Items Store", 22)
    }

    override fun getAllStores(): List<Store> {
        val allStores: MutableList<Store> = mutableListOf()

        allStores.add(buildM55())
        allStores.add(buildAntibes())
        allStores.add(buildPalmaED())
        allStores.add(buildPalmaNG())
        allStores.add(buildIbiza())
        allStores.add(buildOlbiaNA())
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

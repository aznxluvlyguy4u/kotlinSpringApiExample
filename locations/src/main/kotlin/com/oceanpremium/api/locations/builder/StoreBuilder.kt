package com.oceanpremium.api.locations.builder

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

interface StoreBuilder {
    fun getAllStores(): List<Store>
}

/**
 * Source @link: https://docs.google.com/spreadsheets/d/1Jpx59haz_8cZw0uGUBSZX47mQDPv0V3OvgJUtr--JzA/edit#gid=0
 */
@Service
class StoreBuilderImpl(@Autowired private val regionBuilder: RegionBuilder): StoreBuilder {

    private fun buildAntibes(): List<Store> {
        val stores: MutableList<Store> = mutableListOf()

        // ANTIBES
        val antibes = Store("Antibes")
        antibes.addRegion(regionBuilder.buildSpainMainLand())
        antibes.addRegion(regionBuilder.buildBaleraicsIslands())
        antibes.addRegion(regionBuilder.buildSouthOfFrance())
        antibes.addRegion(regionBuilder.buildItaly())
        antibes.addRegion(regionBuilder.buildSicily())
        antibes.addRegion(regionBuilder.buildMalta())
        antibes.addRegion(regionBuilder.buildCorsica())
        antibes.addRegion(regionBuilder.buildSardinia())
        antibes.addRegion(regionBuilder.buildCroatiaAndMontenegro())
        antibes.addRegion(regionBuilder.buildAlbania())
        antibes.addRegion(regionBuilder.buildGreece())
        antibes.addRegion(regionBuilder.buildTurkey())

        stores.add(antibes)

        return stores.toList()
    }

    private fun buildPalma(): List<Store> {
        val stores: MutableList<Store> = mutableListOf()

        // Palma
        val palma = Store("Palma")
        palma.addRegion(regionBuilder.buildSpainMainLand())
        palma.addRegion(regionBuilder.buildBaleraicsIslands())
        palma.addRegion(regionBuilder.buildSouthOfFrance())
        palma.addRegion(regionBuilder.buildItaly())
        palma.addRegion(regionBuilder.buildSicily())
        palma.addRegion(regionBuilder.buildMalta())
        palma.addRegion(regionBuilder.buildCorsica())
        palma.addRegion(regionBuilder.buildSardinia())
        palma.addRegion(regionBuilder.buildCroatiaAndMontenegro())
        palma.addRegion(regionBuilder.buildAlbania())
        palma.addRegion(regionBuilder.buildGreece())
        palma.addRegion(regionBuilder.buildTurkey())

        stores.add(palma)

        return stores.toList()
    }

    override fun getAllStores(): List<Store> {
        val allStores: MutableList<Store> = mutableListOf()

        allStores.addAll(buildAntibes())
        allStores.addAll(buildPalma())

//        var i = 1
//        allStores.forEach {
//            it.id = i
//            i++
//        }

        return allStores
    }
}

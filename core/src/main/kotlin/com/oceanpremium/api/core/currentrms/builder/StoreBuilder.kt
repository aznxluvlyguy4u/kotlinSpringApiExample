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

    //TODO Store does not exist in current data dump
    private fun buildAntibes(): List<Store> {
        val stores: MutableList<Store> = mutableListOf()

        // ANTIBES
        val antibes = Store("Antibes", 99)
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
        val palma = Store("Palma", 3)
        palma.addRegion(regionBuilder.buildBaleraicsIslands())

        stores.add(palma)

        return stores.toList()
    }

    private fun buildOlbia(): List<Store> {
        val stores: MutableList<Store> = mutableListOf()

        // Palma
        val olbia = Store("Olbia", 11)
        olbia.addRegion(regionBuilder.buildSouthOfFrance())
        olbia.addRegion(regionBuilder.buildItaly())
        olbia.addRegion(regionBuilder.buildSicily())
        olbia.addRegion(regionBuilder.buildMalta())
        olbia.addRegion(regionBuilder.buildCorsica())
        olbia.addRegion(regionBuilder.buildSardinia())

        stores.add(olbia)

        return stores.toList()
    }

    private fun buildM55(): List<Store> {
        val stores: MutableList<Store> = mutableListOf()

        // M55
        val m55 = Store("M55", 5)
        m55.addRegion(regionBuilder.buildSpainMainLand())
        m55.addRegion(regionBuilder.buildBaleraicsIslands())
        m55.addRegion(regionBuilder.buildSouthOfFrance())
        m55.addRegion(regionBuilder.buildItaly())
        m55.addRegion(regionBuilder.buildSicily())
        m55.addRegion(regionBuilder.buildMalta())
        m55.addRegion(regionBuilder.buildCorsica())
        m55.addRegion(regionBuilder.buildSardinia())
        m55.addRegion(regionBuilder.buildCroatiaAndMontenegro())
        m55.addRegion(regionBuilder.buildAlbania())
        m55.addRegion(regionBuilder.buildGreece())
        m55.addRegion(regionBuilder.buildTurkey())
        m55.addRegion(regionBuilder.buildCaribbean())
        m55.addRegion(regionBuilder.buildFloridaAndBahamas())
        m55.addRegion(regionBuilder.buildMaldives())

        stores.add(m55)

        return stores.toList()
    }

    private fun buildNapoli(): List<Store> {
        val stores: MutableList<Store> = mutableListOf()

        // Napoli
        val napoli = Store("Napoli", 15)
        napoli.addRegion(regionBuilder.buildSouthOfFrance())
        napoli.addRegion(regionBuilder.buildItaly())
        napoli.addRegion(regionBuilder.buildSicily())
        napoli.addRegion(regionBuilder.buildMalta())
        napoli.addRegion(regionBuilder.buildCorsica())
        napoli.addRegion(regionBuilder.buildSardinia())
        napoli.addRegion(regionBuilder.buildCroatiaAndMontenegro())
        napoli.addRegion(regionBuilder.buildAlbania())
        napoli.addRegion(regionBuilder.buildGreece())
        napoli.addRegion(regionBuilder.buildTurkey())

        stores.add(napoli)

        return stores.toList()
    }

    //TODO Split buildSpain and split buildItaly
    private fun buildAthene(): List<Store> {
        val stores: MutableList<Store> = mutableListOf()

        // Athene
        val athene = Store("Athene", 10)
        athene.addRegion(regionBuilder.buildItaly())
        athene.addRegion(regionBuilder.buildSicily())
        athene.addRegion(regionBuilder.buildCorsica())
        athene.addRegion(regionBuilder.buildSardinia())
        athene.addRegion(regionBuilder.buildCroatiaAndMontenegro())
        athene.addRegion(regionBuilder.buildAlbania())
        athene.addRegion(regionBuilder.buildGreece())
        athene.addRegion(regionBuilder.buildTurkey())

        stores.add(athene)

        return stores.toList()
    }

    //TODO Store does not exist in current data dump
    private fun buildCroatia(): List<Store> {
        val stores: MutableList<Store> = mutableListOf()

        // CROATIA
        val croatia = Store("Croatia", 98)
        croatia.addRegion(regionBuilder.buildSpainMainLand())
        croatia.addRegion(regionBuilder.buildBaleraicsIslands())
        croatia.addRegion(regionBuilder.buildSouthOfFrance())
        croatia.addRegion(regionBuilder.buildItaly())
        croatia.addRegion(regionBuilder.buildSicily())
        croatia.addRegion(regionBuilder.buildMalta())
        croatia.addRegion(regionBuilder.buildCorsica())
        croatia.addRegion(regionBuilder.buildSardinia())
        croatia.addRegion(regionBuilder.buildCroatiaAndMontenegro())
        croatia.addRegion(regionBuilder.buildAlbania())
        croatia.addRegion(regionBuilder.buildGreece())
        croatia.addRegion(regionBuilder.buildTurkey())

        stores.add(croatia)

        return stores.toList()
    }

    private fun buildAntiguaSxm(): List<Store> {
        val stores: MutableList<Store> = mutableListOf()

        // AntiguaSxm
        val antiguaSxm = Store("AntiguaSxm", 14)
        antiguaSxm.addRegion(regionBuilder.buildSpainMainLand())
        antiguaSxm.addRegion(regionBuilder.buildBaleraicsIslands())
        antiguaSxm.addRegion(regionBuilder.buildSouthOfFrance())
        antiguaSxm.addRegion(regionBuilder.buildItaly())
        antiguaSxm.addRegion(regionBuilder.buildSicily())
        antiguaSxm.addRegion(regionBuilder.buildMalta())
        antiguaSxm.addRegion(regionBuilder.buildCorsica())
        antiguaSxm.addRegion(regionBuilder.buildSardinia())
        antiguaSxm.addRegion(regionBuilder.buildCroatiaAndMontenegro())
        antiguaSxm.addRegion(regionBuilder.buildAlbania())
        antiguaSxm.addRegion(regionBuilder.buildGreece())
        antiguaSxm.addRegion(regionBuilder.buildTurkey())
        antiguaSxm.addRegion(regionBuilder.buildCaribbean())
        antiguaSxm.addRegion(regionBuilder.buildFloridaAndBahamas())
        antiguaSxm.addRegion(regionBuilder.buildMaldives())

        stores.add(antiguaSxm)

        return stores.toList()
    }

    //TODO Store does not exist in current data dump
    private fun buildFtl(): List<Store> {
        val stores: MutableList<Store> = mutableListOf()

        // FTL
        val ftl = Store("FTL", 97)
        ftl.addRegion(regionBuilder.buildSpainMainLand())
        ftl.addRegion(regionBuilder.buildBaleraicsIslands())
        ftl.addRegion(regionBuilder.buildSouthOfFrance())
        ftl.addRegion(regionBuilder.buildItaly())
        ftl.addRegion(regionBuilder.buildSicily())
        ftl.addRegion(regionBuilder.buildMalta())
        ftl.addRegion(regionBuilder.buildCorsica())
        ftl.addRegion(regionBuilder.buildSardinia())
        ftl.addRegion(regionBuilder.buildCroatiaAndMontenegro())
        ftl.addRegion(regionBuilder.buildAlbania())
        ftl.addRegion(regionBuilder.buildGreece())
        ftl.addRegion(regionBuilder.buildTurkey())
        ftl.addRegion(regionBuilder.buildCaribbean())
        ftl.addRegion(regionBuilder.buildFloridaAndBahamas())
        ftl.addRegion(regionBuilder.buildMaldives())

        stores.add(ftl)

        return stores.toList()
    }


    override fun getAllStores(): List<Store> {
        val allStores: MutableList<Store> = mutableListOf()

        allStores.addAll(buildAntibes())
        allStores.addAll(buildPalma())
        allStores.addAll(buildOlbia())
        allStores.addAll(buildM55())
        allStores.addAll(buildNapoli())
        allStores.addAll(buildAthene())
        allStores.addAll(buildCroatia())
        allStores.addAll(buildAntiguaSxm())
        allStores.addAll(buildFtl())

        return allStores
    }

    override fun getAllStoreIds(): SortedSet<Int> {
        val allStoreIds: SortedSet<Int> = sortedSetOf()

        allStoreIds.add(99)
        allStoreIds.add(3)
        allStoreIds.add(11)
        allStoreIds.add(5)
        allStoreIds.add(15)
        allStoreIds.add(10)
        allStoreIds.add(98)
        allStoreIds.add(14)
        allStoreIds.add(97)

        return allStoreIds
    }
}

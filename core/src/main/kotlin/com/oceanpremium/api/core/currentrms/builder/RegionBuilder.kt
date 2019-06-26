package com.oceanpremium.api.core.currentrms.builder

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

interface RegionBuilder {
    fun getAllRegions(): List<Region>

    fun buildSpainMainLand1(): List<Region>
    fun buildSpainMainLand2(): List<Region>
    fun buildBaleraicsIslands(): List<Region>
    fun buildSouthOfFrance(): List<Region>
    fun buildItaly(): List<Region>
    fun buildSicily(): List<Region>
    fun buildMalta(): List<Region>
    fun buildCorsica(): List<Region>
    fun buildSardinia(): List<Region>
    fun buildCroatiaAndMontenegro(): List<Region>
    fun buildAlbania(): List<Region>
    fun buildGreece(): List<Region>
    fun buildTurkey(): List<Region>
    fun buildCaribbean(): List<Region>
    fun buildFloridaAndBahamas(): List<Region>
    fun buildMaldives(): List<Region>

}

/**
 * Source @link: https://docs.google.com/spreadsheets/d/1Jpx59haz_8cZw0uGUBSZX47mQDPv0V3OvgJUtr--JzA/edit#gid=0
 */
@Service
class RegionBuilderImpl(@Autowired private val storeBuilder: StoreBuilderImpl): RegionBuilder {

    override fun buildSpainMainLand1(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        // Spain - mainland1

        // Gibraltar
        val gibraltar = Region("Gibraltar", 1)

        val gibraltarLoc = Location("Gibraltar")
        gibraltarLoc.addAlternativeStore(storeBuilder.buildAntibes(18, 1734))
        gibraltarLoc.addGrayStore(storeBuilder.buildM55(168))
        gibraltarLoc.addGrayStore(storeBuilder.buildPalmaED(72))
        gibraltarLoc.addGrayStore(storeBuilder.buildPalmaNG(72))
        gibraltarLoc.addGrayStore(storeBuilder.buildIbiza(96))
        gibraltarLoc.addGrayStore(storeBuilder.buildOlbiaNA(144))
        gibraltarLoc.addGrayStore(storeBuilder.buildOlbiaSYS(144))
        gibraltarLoc.addGrayStore(storeBuilder.buildGenova(144))
        gibraltarLoc.addGrayStore(storeBuilder.buildNapoli(168))
        gibraltarLoc.addGrayStore(storeBuilder.buildPalermo(216))
        gibraltarLoc.addGrayStore(storeBuilder.buildRiposto(216))
        gibraltarLoc.addGrayStore(storeBuilder.buildMalta(216))
        gibraltarLoc.addGrayStore(storeBuilder.buildAthensSotiris(192))
        gibraltarLoc.addGrayStore(storeBuilder.buildAthensH360(192))
        gibraltarLoc.addGrayStore(storeBuilder.buildCorfu(216))
        gibraltarLoc.addGrayStore(storeBuilder.buildKosA1(216))
        gibraltarLoc.addGrayStore(storeBuilder.buildRhodes(216))
        gibraltarLoc.addGrayStore(storeBuilder.buildVenice(168))
        gibraltarLoc.addGrayStore(storeBuilder.buildZadar(196))
        gibraltarLoc.addGrayStore(storeBuilder.buildSplit(196))
        gibraltarLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(196))
        gibraltarLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(196))
        gibraltarLoc.addGrayStore(storeBuilder.buildBCM(216))
        gibraltarLoc.addGrayStore(storeBuilder.buildTivat(216))
        gibraltarLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(144))
        gibraltar.addLocation(gibraltarLoc)

        regions.add(gibraltar)

        // Marbella
        val marbella = Region("Marbella", 2)

        val calaDeMijasLoc = Location("Cala de Mijas")
        val marbellaLoc = Location("Marbella")
        val laLacaidesaLoc = Location("La Lacaidesa")

        calaDeMijasLoc.addAlternativeStore(storeBuilder.buildAntibes(17, 1646))
        marbellaLoc.addAlternativeStore(storeBuilder.buildAntibes(17, 1659))
        laLacaidesaLoc.addAlternativeStore(storeBuilder.buildAntibes(17, 1721))

        marbella.addLocation(marbellaLoc)
        marbella.addLocation(calaDeMijasLoc)
        marbella.addLocation(laLacaidesaLoc)

        marbella.locations.forEach { location ->
            location.addGrayStore(storeBuilder.buildM55(168))
            location.addGrayStore(storeBuilder.buildPalmaED(96))
            location.addGrayStore(storeBuilder.buildPalmaNG(96))
            location.addGrayStore(storeBuilder.buildIbiza(96))
            location.addGrayStore(storeBuilder.buildOlbiaNA(144))
            location.addGrayStore(storeBuilder.buildOlbiaSYS(144))
            location.addGrayStore(storeBuilder.buildGenova(144))
            location.addGrayStore(storeBuilder.buildNapoli(168))
            location.addGrayStore(storeBuilder.buildPalermo(216))
            location.addGrayStore(storeBuilder.buildRiposto(216))
            location.addGrayStore(storeBuilder.buildMalta(216))
            location.addGrayStore(storeBuilder.buildAthensSotiris(192))
            location.addGrayStore(storeBuilder.buildAthensH360(192))
            location.addGrayStore(storeBuilder.buildCorfu(216))
            location.addGrayStore(storeBuilder.buildKosA1(216))
            location.addGrayStore(storeBuilder.buildRhodes(216))
            location.addGrayStore(storeBuilder.buildVenice(168))
            location.addGrayStore(storeBuilder.buildZadar(196))
            location.addGrayStore(storeBuilder.buildSplit(196))
            location.addGrayStore(storeBuilder.buildDubrovnikKristijan(196))
            location.addGrayStore(storeBuilder.buildDubrovnikBWA(196))
            location.addGrayStore(storeBuilder.buildBCM(216))
            location.addGrayStore(storeBuilder.buildTivat(216))
            location.addNewItemsStore(storeBuilder.buildNewItemsStore(144))
        }

        regions.add(marbella)

        // Malaga
        val malaga = Region("Malaga", 3)

        val fuengirolaLoc = Location("Fuengirola")
        val malagaLoc = Location("Malaga")
        val almunecarLoc = Location("Almunecar")

        fuengirolaLoc.addAlternativeStore(storeBuilder.buildAntibes(17, 1632))
        malagaLoc.addAlternativeStore(storeBuilder.buildAntibes(17, 1605))
        almunecarLoc.addAlternativeStore(storeBuilder.buildAntibes(16, 1566))

        malaga.addLocation(fuengirolaLoc)
        malaga.addLocation(malagaLoc)
        malaga.addLocation(almunecarLoc)

        malaga.locations.forEach { location ->
            location.addGrayStore(storeBuilder.buildM55(168))
            location.addGrayStore(storeBuilder.buildPalmaED(96))
            location.addGrayStore(storeBuilder.buildPalmaNG(96))
            location.addGrayStore(storeBuilder.buildIbiza(96))
            location.addGrayStore(storeBuilder.buildOlbiaNA(144))
            location.addGrayStore(storeBuilder.buildOlbiaSYS(144))
            location.addGrayStore(storeBuilder.buildGenova(144))
            location.addGrayStore(storeBuilder.buildNapoli(168))
            location.addGrayStore(storeBuilder.buildPalermo(216))
            location.addGrayStore(storeBuilder.buildRiposto(216))
            location.addGrayStore(storeBuilder.buildMalta(216))
            location.addGrayStore(storeBuilder.buildAthensSotiris(192))
            location.addGrayStore(storeBuilder.buildAthensH360(192))
            location.addGrayStore(storeBuilder.buildCorfu(216))
            location.addGrayStore(storeBuilder.buildKosA1(216))
            location.addGrayStore(storeBuilder.buildRhodes(216))
            location.addGrayStore(storeBuilder.buildVenice(168))
            location.addGrayStore(storeBuilder.buildZadar(196))
            location.addGrayStore(storeBuilder.buildSplit(196))
            location.addGrayStore(storeBuilder.buildDubrovnikKristijan(196))
            location.addGrayStore(storeBuilder.buildDubrovnikBWA(196))
            location.addGrayStore(storeBuilder.buildBCM(216))
            location.addGrayStore(storeBuilder.buildTivat(216))
            location.addNewItemsStore(storeBuilder.buildNewItemsStore(144))
        }

        regions.add(malaga)

        // Almeria
        val almeria = Region("Almeria", 4)

        val aguilasLoc = Location("Aguilas")
        val almeriaLoc = Location("Almeria")
        val mortilLoc = Location("Mortil")

        aguilasLoc.addAlternativeStore(storeBuilder.buildAntibes(14, 1315))
        almeriaLoc.addAlternativeStore(storeBuilder.buildAntibes(15, 1424))
        mortilLoc.addAlternativeStore(storeBuilder.buildAntibes(16, 1532))

        almeria.addLocation(aguilasLoc)
        almeria.addLocation(almeriaLoc)
        almeria.addLocation(mortilLoc)

        almeria.locations.forEach { location ->
            location.addGrayStore(storeBuilder.buildM55(168))
            location.addGrayStore(storeBuilder.buildPalmaED(96))
            location.addGrayStore(storeBuilder.buildPalmaNG(96))
            location.addGrayStore(storeBuilder.buildIbiza(96))
            location.addGrayStore(storeBuilder.buildOlbiaNA(144))
            location.addGrayStore(storeBuilder.buildOlbiaSYS(144))
            location.addGrayStore(storeBuilder.buildGenova(144))
            location.addGrayStore(storeBuilder.buildNapoli(168))
            location.addGrayStore(storeBuilder.buildPalermo(216))
            location.addGrayStore(storeBuilder.buildRiposto(216))
            location.addGrayStore(storeBuilder.buildMalta(216))
            location.addGrayStore(storeBuilder.buildAthensSotiris(192))
            location.addGrayStore(storeBuilder.buildAthensH360(192))
            location.addGrayStore(storeBuilder.buildCorfu(216))
            location.addGrayStore(storeBuilder.buildKosA1(216))
            location.addGrayStore(storeBuilder.buildRhodes(216))
            location.addGrayStore(storeBuilder.buildVenice(168))
            location.addGrayStore(storeBuilder.buildZadar(196))
            location.addGrayStore(storeBuilder.buildSplit(196))
            location.addGrayStore(storeBuilder.buildDubrovnikKristijan(196))
            location.addGrayStore(storeBuilder.buildDubrovnikBWA(196))
            location.addGrayStore(storeBuilder.buildBCM(216))
            location.addGrayStore(storeBuilder.buildTivat(216))
            location.addNewItemsStore(storeBuilder.buildNewItemsStore(144))
        }

        regions.add(almeria)

        return regions.toList()
    }

    override fun buildSpainMainLand2(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        // Alicante
        val alicante = Region("Alicante", 5)

        val calpLoc = Location("Calp")
        val alicanteLoc = Location("Alicante")
        val cartagenaLoc = Location("Cartagena")

        calpLoc.addAlternativeStore(storeBuilder.buildPalmaED(24, 300))
        calpLoc.addAlternativeStore(storeBuilder.buildPalmaNG(24, 300))
        calpLoc.addAlternativeStore(storeBuilder.buildAntibes(12, 1188))

        calpLoc.addGrayStore(storeBuilder.buildM55(168))
        calpLoc.addGrayStore(storeBuilder.buildIbiza(96))
        calpLoc.addGrayStore(storeBuilder.buildOlbiaNA(144))
        calpLoc.addGrayStore(storeBuilder.buildOlbiaSYS(144))
        calpLoc.addGrayStore(storeBuilder.buildGenova(144))
        calpLoc.addGrayStore(storeBuilder.buildNapoli(168))
        calpLoc.addGrayStore(storeBuilder.buildPalermo(216))
        calpLoc.addGrayStore(storeBuilder.buildRiposto(216))
        calpLoc.addGrayStore(storeBuilder.buildMalta(216))
        calpLoc.addGrayStore(storeBuilder.buildAthensSotiris(192))
        calpLoc.addGrayStore(storeBuilder.buildAthensH360(192))
        calpLoc.addGrayStore(storeBuilder.buildCorfu(216))
        calpLoc.addGrayStore(storeBuilder.buildKosA1(216))
        calpLoc.addGrayStore(storeBuilder.buildRhodes(216))
        calpLoc.addGrayStore(storeBuilder.buildVenice(168))
        calpLoc.addGrayStore(storeBuilder.buildZadar(196))
        calpLoc.addGrayStore(storeBuilder.buildSplit(196))
        calpLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(196))
        calpLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(196))
        calpLoc.addGrayStore(storeBuilder.buildBCM(216))
        calpLoc.addGrayStore(storeBuilder.buildTivat(216))
        calpLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(144))

        alicanteLoc.addAlternativeStore(storeBuilder.buildPalmaED(24, 300))
        alicanteLoc.addAlternativeStore(storeBuilder.buildPalmaNG(24, 300))
        alicanteLoc.addAlternativeStore(storeBuilder.buildAntibes(12, 1166))

        alicanteLoc.addGrayStore(storeBuilder.buildM55(168))
        alicanteLoc.addGrayStore(storeBuilder.buildIbiza(96))
        alicanteLoc.addGrayStore(storeBuilder.buildOlbiaNA(144))
        alicanteLoc.addGrayStore(storeBuilder.buildOlbiaSYS(144))
        alicanteLoc.addGrayStore(storeBuilder.buildGenova(144))
        alicanteLoc.addGrayStore(storeBuilder.buildNapoli(168))
        alicanteLoc.addGrayStore(storeBuilder.buildPalermo(216))
        alicanteLoc.addGrayStore(storeBuilder.buildRiposto(216))
        alicanteLoc.addGrayStore(storeBuilder.buildMalta(216))
        alicanteLoc.addGrayStore(storeBuilder.buildAthensSotiris(192))
        alicanteLoc.addGrayStore(storeBuilder.buildAthensH360(192))
        alicanteLoc.addGrayStore(storeBuilder.buildCorfu(216))
        alicanteLoc.addGrayStore(storeBuilder.buildKosA1(216))
        alicanteLoc.addGrayStore(storeBuilder.buildRhodes(216))
        alicanteLoc.addGrayStore(storeBuilder.buildVenice(168))
        alicanteLoc.addGrayStore(storeBuilder.buildZadar(196))
        alicanteLoc.addGrayStore(storeBuilder.buildSplit(196))
        alicanteLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(196))
        alicanteLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(196))
        alicanteLoc.addGrayStore(storeBuilder.buildBCM(216))
        alicanteLoc.addGrayStore(storeBuilder.buildTivat(216))
        alicanteLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(144))


        cartagenaLoc.addAlternativeStore(storeBuilder.buildPalmaED(24, 300))
        cartagenaLoc.addAlternativeStore(storeBuilder.buildPalmaNG(24, 300))
        cartagenaLoc.addAlternativeStore(storeBuilder.buildAntibes(13, 1261))

        cartagenaLoc.addGrayStore(storeBuilder.buildM55(168))
        cartagenaLoc.addGrayStore(storeBuilder.buildIbiza(72))
        cartagenaLoc.addGrayStore(storeBuilder.buildOlbiaNA(144))
        cartagenaLoc.addGrayStore(storeBuilder.buildOlbiaSYS(144))
        cartagenaLoc.addGrayStore(storeBuilder.buildGenova(144))
        cartagenaLoc.addGrayStore(storeBuilder.buildNapoli(168))
        cartagenaLoc.addGrayStore(storeBuilder.buildPalermo(216))
        cartagenaLoc.addGrayStore(storeBuilder.buildRiposto(216))
        cartagenaLoc.addGrayStore(storeBuilder.buildMalta(216))
        cartagenaLoc.addGrayStore(storeBuilder.buildAthensSotiris(192))
        cartagenaLoc.addGrayStore(storeBuilder.buildAthensH360(192))
        cartagenaLoc.addGrayStore(storeBuilder.buildCorfu(216))
        cartagenaLoc.addGrayStore(storeBuilder.buildKosA1(216))
        cartagenaLoc.addGrayStore(storeBuilder.buildRhodes(216))
        cartagenaLoc.addGrayStore(storeBuilder.buildVenice(168))
        cartagenaLoc.addGrayStore(storeBuilder.buildZadar(196))
        cartagenaLoc.addGrayStore(storeBuilder.buildSplit(196))
        cartagenaLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(196))
        cartagenaLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(196))
        cartagenaLoc.addGrayStore(storeBuilder.buildBCM(216))
        cartagenaLoc.addGrayStore(storeBuilder.buildTivat(216))
        cartagenaLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(144))

        alicante.addLocation(calpLoc)
        alicante.addLocation(alicanteLoc)
        alicante.addLocation(cartagenaLoc)

        regions.add(alicante)

        // Valencia
        val valencia = Region("Valencia",6)

        val tarragonaLoc = Location("Tarragona")
        val valenciaLoc = Location("Valencia")

        tarragonaLoc.addAlternativeStore(storeBuilder.buildPalmaED(24, 300))
        tarragonaLoc.addAlternativeStore(storeBuilder.buildPalmaNG(24, 300))
        tarragonaLoc.addAlternativeStore(storeBuilder.buildAntibes(8, 736))

        valenciaLoc.addAlternativeStore(storeBuilder.buildPalmaED(24, 300))
        valenciaLoc.addAlternativeStore(storeBuilder.buildPalmaNG(24, 300))
        valenciaLoc.addAlternativeStore(storeBuilder.buildAntibes(10, 987))

        valencia.addLocation(tarragonaLoc)
        valencia.addLocation(valenciaLoc)

        valencia.locations.forEach { location ->
            location.addGrayStore(storeBuilder.buildM55(168))
            location.addGrayStore(storeBuilder.buildIbiza(72))
            location.addGrayStore(storeBuilder.buildOlbiaNA(144))
            location.addGrayStore(storeBuilder.buildOlbiaSYS(144))
            location.addGrayStore(storeBuilder.buildGenova(144))
            location.addGrayStore(storeBuilder.buildNapoli(168))
            location.addGrayStore(storeBuilder.buildPalermo(216))
            location.addGrayStore(storeBuilder.buildRiposto(216))
            location.addGrayStore(storeBuilder.buildMalta(216))
            location.addGrayStore(storeBuilder.buildAthensSotiris(192))
            location.addGrayStore(storeBuilder.buildAthensH360(192))
            location.addGrayStore(storeBuilder.buildCorfu(216))
            location.addGrayStore(storeBuilder.buildKosA1(216))
            location.addGrayStore(storeBuilder.buildRhodes(216))
            location.addGrayStore(storeBuilder.buildVenice(168))
            location.addGrayStore(storeBuilder.buildZadar(196))
            location.addGrayStore(storeBuilder.buildSplit(196))
            location.addGrayStore(storeBuilder.buildDubrovnikKristijan(196))
            location.addGrayStore(storeBuilder.buildDubrovnikBWA(196))
            location.addGrayStore(storeBuilder.buildBCM(216))
            location.addGrayStore(storeBuilder.buildTivat(216))
            location.addNewItemsStore(storeBuilder.buildNewItemsStore(144))
        }

        regions.add(valencia)


        // Denia
        val denia = Region("Denia",7)

        val deniaLoc = Location("Denia")

        deniaLoc.addAlternativeStore(storeBuilder.buildPalmaED(24, 300))
        deniaLoc.addAlternativeStore(storeBuilder.buildPalmaNG(24, 300))
        deniaLoc.addAlternativeStore(storeBuilder.buildAntibes(11, 1101))

        deniaLoc.addGrayStore(storeBuilder.buildM55(168))
        deniaLoc.addGrayStore(storeBuilder.buildIbiza(72))
        deniaLoc.addGrayStore(storeBuilder.buildOlbiaNA(144))
        deniaLoc.addGrayStore(storeBuilder.buildOlbiaSYS(144))
        deniaLoc.addGrayStore(storeBuilder.buildGenova(144))
        deniaLoc.addGrayStore(storeBuilder.buildNapoli(168))
        deniaLoc.addGrayStore(storeBuilder.buildPalermo(216))
        deniaLoc.addGrayStore(storeBuilder.buildRiposto(216))
        deniaLoc.addGrayStore(storeBuilder.buildMalta(216))
        deniaLoc.addGrayStore(storeBuilder.buildAthensSotiris(192))
        deniaLoc.addGrayStore(storeBuilder.buildAthensH360(192))
        deniaLoc.addGrayStore(storeBuilder.buildCorfu(216))
        deniaLoc.addGrayStore(storeBuilder.buildKosA1(216))
        deniaLoc.addGrayStore(storeBuilder.buildRhodes(216))
        deniaLoc.addGrayStore(storeBuilder.buildVenice(168))
        deniaLoc.addGrayStore(storeBuilder.buildZadar(196))
        deniaLoc.addGrayStore(storeBuilder.buildSplit(196))
        deniaLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(196))
        deniaLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(196))
        deniaLoc.addGrayStore(storeBuilder.buildBCM(216))
        deniaLoc.addGrayStore(storeBuilder.buildTivat(216))
        deniaLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(144))

        denia.addLocation(deniaLoc)

        regions.add(denia)


        // Barcelona
        val barcelona = Region("Barcelona",8)

        val barcelonaLoc = Location("Barcelona")
        val sitgesLoc = Location("Sitges")

        barcelonaLoc.addAlternativeStore(storeBuilder.buildPalmaED(18, 300))
        barcelonaLoc.addAlternativeStore(storeBuilder.buildPalmaNG(18, 300))
        barcelonaLoc.addAlternativeStore(storeBuilder.buildAntibes(7, 652))

        sitgesLoc.addAlternativeStore(storeBuilder.buildPalmaED(24, 300))
        sitgesLoc.addAlternativeStore(storeBuilder.buildPalmaNG(24, 300))
        sitgesLoc.addAlternativeStore(storeBuilder.buildAntibes(7, 683))

        barcelona.addLocation(barcelonaLoc)
        barcelona.addLocation(sitgesLoc)

        barcelona.locations.forEach { location ->
            location.addGrayStore(storeBuilder.buildM55(168))
            location.addGrayStore(storeBuilder.buildIbiza(72))
            location.addGrayStore(storeBuilder.buildOlbiaNA(120))
            location.addGrayStore(storeBuilder.buildOlbiaSYS(120))
            location.addGrayStore(storeBuilder.buildGenova(120))
            location.addGrayStore(storeBuilder.buildNapoli(168))
            location.addGrayStore(storeBuilder.buildPalermo(216))
            location.addGrayStore(storeBuilder.buildRiposto(216))
            location.addGrayStore(storeBuilder.buildMalta(216))
            location.addGrayStore(storeBuilder.buildAthensSotiris(192))
            location.addGrayStore(storeBuilder.buildAthensH360(192))
            location.addGrayStore(storeBuilder.buildCorfu(216))
            location.addGrayStore(storeBuilder.buildKosA1(216))
            location.addGrayStore(storeBuilder.buildRhodes(216))
            location.addGrayStore(storeBuilder.buildVenice(168))
            location.addGrayStore(storeBuilder.buildZadar(196))
            location.addGrayStore(storeBuilder.buildSplit(196))
            location.addGrayStore(storeBuilder.buildDubrovnikKristijan(196))
            location.addGrayStore(storeBuilder.buildDubrovnikBWA(196))
            location.addGrayStore(storeBuilder.buildBCM(216))
            location.addGrayStore(storeBuilder.buildTivat(216))
            location.addNewItemsStore(storeBuilder.buildNewItemsStore(120))
        }

        regions.add(barcelona)

        // Roses
        val roses = Region("Roses",9)

        val rosesLoc = Location("Roses")

        rosesLoc.addAlternativeStore(storeBuilder.buildPalmaED(24, 300))
        rosesLoc.addAlternativeStore(storeBuilder.buildPalmaNG(24, 300))
        rosesLoc.addAlternativeStore(storeBuilder.buildAntibes(6, 541))

        rosesLoc.addGrayStore(storeBuilder.buildM55(168))
        rosesLoc.addGrayStore(storeBuilder.buildIbiza(72))
        rosesLoc.addGrayStore(storeBuilder.buildOlbiaNA(120))
        rosesLoc.addGrayStore(storeBuilder.buildOlbiaSYS(120))
        rosesLoc.addGrayStore(storeBuilder.buildGenova(120))
        rosesLoc.addGrayStore(storeBuilder.buildNapoli(168))
        rosesLoc.addGrayStore(storeBuilder.buildPalermo(216))
        rosesLoc.addGrayStore(storeBuilder.buildRiposto(216))
        rosesLoc.addGrayStore(storeBuilder.buildMalta(216))
        rosesLoc.addGrayStore(storeBuilder.buildAthensSotiris(192))
        rosesLoc.addGrayStore(storeBuilder.buildAthensH360(192))
        rosesLoc.addGrayStore(storeBuilder.buildCorfu(216))
        rosesLoc.addGrayStore(storeBuilder.buildKosA1(216))
        rosesLoc.addGrayStore(storeBuilder.buildRhodes(216))
        rosesLoc.addGrayStore(storeBuilder.buildVenice(168))
        rosesLoc.addGrayStore(storeBuilder.buildZadar(196))
        rosesLoc.addGrayStore(storeBuilder.buildSplit(196))
        rosesLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(196))
        rosesLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(196))
        rosesLoc.addGrayStore(storeBuilder.buildBCM(216))
        rosesLoc.addGrayStore(storeBuilder.buildTivat(216))
        rosesLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(120))

        roses.addLocation(rosesLoc)

        regions.add(roses)

        return regions.toList()
    }

    override fun buildBaleraicsIslands(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        //Balearic's Islands
        val ibiza = Region("Ibiza",10)
        ibiza.addLocation(Location("Ibiza Island"))
        ibiza.addLocation(Location("Formentera island"))
        regions.add(ibiza)

        val mallorca = Region("Mallorca",11)
        mallorca.addLocation(Location("Island - North/East"))
        mallorca.addLocation(Location("Palma"))
        mallorca.addLocation(Location("Port Portals"))
        mallorca.addLocation(Location("Porto Adriano"))
        regions.add(mallorca)

        val menorca = Region("Menorca",12)
        menorca.addLocation(Location("Menorca Island"))
        regions.add(menorca)

        var j = 10
        regions.forEach { region ->

            region.addStore(Store("Antibes", 99))
            region.addStore(Store("Palma", 3))
            region.addStore(Store("M55", 5))
            region.addStore(Store("Croatia", 98))
            region.addStore(Store("AntiguaSxm", 14))
            region.addStore(Store("FTL", 13))

            region.locations.forEach { location ->
                location.id = j
                j++
            }
        }

        return regions.toList()
    }

    override fun buildSouthOfFrance(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        //South Of France
        val portVendres = Region("Port Vendres",13)
        portVendres.addLocation(Location())
        regions.add(portVendres)

        val marseilles = Region("Marseilles",14)
        marseilles.addLocation(Location())
        regions.add(marseilles)

        val toulon = Region("Toulon",15)
        toulon.addLocation(Location())
        toulon.addLocation(Location("Cavalaire sur Mer"))
        toulon.addLocation(Location("Hyeres"))
        toulon.addLocation(Location("La Ciotat"))
        regions.add(toulon)

        val stTropez = Region("St. Tropez",16)
        stTropez.addLocation(Location())
        stTropez.addLocation(Location("Saint Raphael"))
        stTropez.addLocation(Location("Pampellonne beach"))
        regions.add(stTropez)

        val cannes = Region("Cannes",17)
        cannes.addLocation(Location())
        cannes.addLocation(Location("Mandelieu la Napoule"))
        cannes.addLocation(Location("Theule sur Mer"))
        regions.add(cannes)

        val antibes = Region("Antibes",18)
        antibes.addLocation(Location())
        antibes.addLocation(Location("Marina Baie Des Anges"))
        antibes.addLocation(Location("Golfe Juan"))
        regions.add(antibes)

        val nice = Region("Nice",19)
        nice.addLocation(Location())
        nice.addLocation(Location("Villefranche sur Mer"))
        nice.addLocation(Location("Cagnes sur Mer"))
        regions.add(nice)

        val monaco = Region("Monaco",20)
        monaco.addLocation(Location())
        monaco.addLocation(Location("Cap d'Ail"))
        monaco.addLocation(Location("Saint Jean Cap Ferrat"))
        monaco.addLocation(Location("Beaulieu sur Mer"))
        regions.add(monaco)

        var j = 20
        regions.forEach { region ->

            region.addStore(Store("Antibes", 99))
            region.addStore(Store("Olbia", 11))
            region.addStore(Store("M55", 5))
            region.addStore(Store("Napoli", 15))
            region.addStore(Store("Croatia", 98))
            region.addStore(Store("AntiguaSxm", 14))
            region.addStore(Store("FTL", 13))

            region.locations.forEach { location ->
                location.id = j
                j++
            }
        }

        return regions.toList()
    }

    override fun buildItaly(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        /**
         * Liguria Italy
         */
        val sanremo = Region("Sanremo",21)
        sanremo.addLocation(Location())
        sanremo.addLocation(Location("Imperia"))
        sanremo.addLocation(Location("Ventimiglia"))
        regions.add(sanremo)

        /**
         * Tuscany Italy
         */
        val loano = Region("Loano",22)
        loano.addLocation(Location())
        loano.addLocation(Location("Albenga"))
        loano.addLocation(Location("Savona"))
        loano.addLocation(Location("Varazze"))
        regions.add(loano)

        val genoa = Region("Genoa",23)
        genoa.addLocation(Location("Genoa Aeroporto"))
        genoa.addLocation(Location("Genoa molo vecchio"))
        regions.add(genoa)

        val portofino = Region("Portofino",24)
        portofino.addLocation(Location())
        regions.add(portofino)

        val laSpezia = Region("La Spezia",25)
        laSpezia.addLocation(Location())
        regions.add(laSpezia)

        val viareggio = Region("Viareggio",26)
        viareggio.addLocation(Location())
        regions.add(viareggio)

        val livorno = Region("Livorno",27)
        livorno.addLocation(Location())
        regions.add(livorno)

        val piombino = Region("Piombino",28)
        piombino.addLocation(Location())
        regions.add(piombino)

        val grosseto = Region("Grosseto",29)
        grosseto.addLocation(Location())
        regions.add(grosseto)

        val argentario = Region("Argentario",30)
        argentario.addLocation(Location())
        regions.add(argentario)

        val civitavecchia = Region("Civitavecchia",31)
        civitavecchia.addLocation(Location())
        regions.add(civitavecchia)

        /**
         * Amalfi (Italy)
         */
        val gaeta = Region("Gaeta",32)
        gaeta.addLocation(Location())
        regions.add(gaeta)

        val naples = Region("Naples",33)
        naples.addLocation(Location())
        regions.add(naples)

        val marinadiStabia = Region("Marina di Stabia",34)
        marinadiStabia.addLocation(Location())
        regions.add(marinadiStabia)

        val sorrento = Region("Sorrento",35)
        sorrento.addLocation(Location())
        regions.add(sorrento)

        val capri = Region("Capri",36)
        capri.addLocation(Location())
        regions.add(capri)

        val positano = Region("Positano",37)
        positano.addLocation(Location())
        regions.add(positano)

        val amalfi = Region("Amalfi",38)
        amalfi.addLocation(Location())
        regions.add(amalfi)

        val salerno = Region("Salerno",39)
        salerno.addLocation(Location())
        regions.add(salerno)

        //South of Italy
        val tropea = Region("Tropea",40)
        tropea.addLocation(Location())
        regions.add(tropea)

        val reggioDiCalabria = Region("Reggio di Calabria",41)
        reggioDiCalabria.addLocation(Location())
        regions.add(reggioDiCalabria)

        val brindisi = Region("Brindisi",42)
        brindisi.addLocation(Location())
        regions.add(brindisi)

        val otranto = Region("Otranto",43)
        otranto.addLocation(Location())
        regions.add(otranto)

        val bari = Region("Bari",44)
        bari.addLocation(Location())
        regions.add(bari)

        var j = 30
        regions.forEach { region ->

            region.addStore(Store("Antibes", 99))
            region.addStore(Store("Olbia", 11))
            region.addStore(Store("M55", 5))
            region.addStore(Store("Napoli", 15))
            region.addStore(Store("Athene", 10))
            region.addStore(Store("Croatia", 98))
            region.addStore(Store("AntiguaSxm", 14))
            region.addStore(Store("FTL", 13))

            region.locations.forEach { location ->
                location.id = j
                j++
            }
        }

        return regions.toList()
    }

    override fun buildSicily(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        // Sicily
        val palermo = Region("Palermo",45)
        palermo.addLocation(Location())
        regions.add(palermo)

        val millazzo = Region("Millazzo",46)
        millazzo.addLocation(Location())
        regions.add(millazzo)

        val messina = Region("Messina",47)
        messina.addLocation(Location())
        regions.add(messina)

        val taormina = Region("Taormina",48)
        taormina.addLocation(Location())
        regions.add(taormina)

        val riposto = Region("Riposto",49)
        riposto.addLocation(Location())
        regions.add(riposto)

        val catania = Region("Catania",50)
        catania.addLocation(Location())
        regions.add(catania)

        val syracuse = Region("Syracuse",51)
        syracuse.addLocation(Location())
        regions.add(syracuse)

        var j = 50
        regions.forEach { region ->

            region.addStore(Store("Antibes", 99))
            region.addStore(Store("Olbia", 11))
            region.addStore(Store("M55", 5))
            region.addStore(Store("Napoli", 15))
            region.addStore(Store("Athene", 10))
            region.addStore(Store("Croatia", 98))
            region.addStore(Store("AntiguaSxm", 14))
            region.addStore(Store("FTL", 13))

            region.locations.forEach { location ->
                location.id = j
                j++
            }
        }

        return regions.toList()
    }

    override fun buildMalta(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        // Malta
        val maltaIsland = Region("Malta Island",52)
        maltaIsland.addLocation(Location())
        regions.add(maltaIsland)

        val gozoIsland = Region("Gozo Island",53)
        gozoIsland.addLocation(Location())
        regions.add(gozoIsland)

        var j = 60
        regions.forEach { region ->

            region.addStore(Store("Antibes", 99))
            region.addStore(Store("Olbia", 11))
            region.addStore(Store("M55", 5))
            region.addStore(Store("Napoli", 15))
            region.addStore(Store("Croatia", 98))
            region.addStore(Store("AntiguaSxm", 14))
            region.addStore(Store("FTL", 13))

            region.locations.forEach { location ->
                location.id = j
                j++
            }
        }

        return regions.toList()
    }

    override fun buildCorsica(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        // Corsica
        val calvi = Region("Calvi",54)
        calvi.addLocation(Location())
        regions.add(calvi)

        val saintFlorent = Region("Saint Florent",55)
        saintFlorent.addLocation(Location())
        regions.add(saintFlorent)

        val bastia = Region("Bastia",56)
        bastia.addLocation(Location())
        regions.add(bastia)

        val portoVecchio = Region("Porto Vecchio",57)
        portoVecchio.addLocation(Location())
        regions.add(portoVecchio)

        val bonifacio = Region("Bonifacio",58)
        bonifacio.addLocation(Location())
        regions.add(bonifacio)

        val ajjaccio = Region("Ajjaccio",59)
        ajjaccio.addLocation(Location())
        regions.add(ajjaccio)

        var j = 70
        regions.forEach { region ->

            region.addStore(Store("Antibes", 99))
            region.addStore(Store("Olbia", 11))
            region.addStore(Store("M55", 5))
            region.addStore(Store("Napoli", 15))
            region.addStore(Store("Athene", 10))
            region.addStore(Store("Croatia", 98))
            region.addStore(Store("AntiguaSxm", 14))
            region.addStore(Store("FTL", 13))

            region.locations.forEach { location ->
                location.id = j
                j++
            }
        }

        return regions.toList()
    }

    override fun buildSardinia(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        // Sardinia
        val baiaSardinia = Region("Baia Sardinia",60)
        baiaSardinia.addLocation(Location())
        regions.add(baiaSardinia)

        val portoCervo = Region("Porto Cervo",61)
        portoCervo.addLocation(Location())
        regions.add(portoCervo)

        val portoRotondo = Region("Porto Rotondo",62)
        portoRotondo.addLocation(Location())
        regions.add(portoRotondo)

        val portoSanPaolo = Region("Porto San paolo",63)
        portoSanPaolo.addLocation(Location())
        regions.add(portoSanPaolo)

        val olbia = Region("Olbia",64)
        olbia.addLocation(Location())
        regions.add(olbia)

        val palau = Region("Palau",65)
        palau.addLocation(Location())
        regions.add(palau)

        val poltuQuatu = Region("Poltu Quatu",66)
        poltuQuatu.addLocation(Location())
        regions.add(poltuQuatu)

        val calaBitta = Region("Cala Bitta",67)
        calaBitta.addLocation(Location())
        regions.add(calaBitta)

        val cannigione = Region("Cannigione",68)
        cannigione.addLocation(Location())
        regions.add(cannigione)

        val cagliari = Region("Cagliari",69)
        cagliari.addLocation(Location())
        regions.add(cagliari)

        val laMaddalena = Region("La Maddalena",70)
        laMaddalena.addLocation(Location())
        regions.add(laMaddalena)

        var j = 80
        regions.forEach { region ->

            region.addStore(Store("Antibes", 99))
            region.addStore(Store("Olbia", 11))
            region.addStore(Store("M55", 5))
            region.addStore(Store("Napoli", 15))
            region.addStore(Store("Athene", 10))
            region.addStore(Store("Croatia", 98))
            region.addStore(Store("AntiguaSxm", 14))
            region.addStore(Store("FTL", 13))

            region.locations.forEach { location ->
                location.id = j
                j++
            }
        }

        return regions.toList()
    }

    override fun buildCroatiaAndMontenegro(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        // Croatia & Montenegro
        val venice = Region("Venice",71)
        venice.addLocation(Location())
        regions.add(venice)

        val piran = Region("Piran",72)
        piran.addLocation(Location())
        regions.add(piran)

        val rovinj = Region("Rovinj",73)
        rovinj.addLocation(Location())
        regions.add(rovinj)

        val pula = Region("Pula",74)
        pula.addLocation(Location())
        regions.add(pula)

        val zadar = Region("Zadar",75)
        zadar.addLocation(Location())
        regions.add(zadar)

        val sibenik = Region("Sibenik",76)
        sibenik.addLocation(Location())
        regions.add(sibenik)

        val trogir = Region("Trogir",77)
        trogir.addLocation(Location())
        regions.add(trogir)

        val split = Region("Split",78)
        split.addLocation(Location())
        regions.add(split)

        val hvar = Region("Hvar",79)
        hvar.addLocation(Location())
        regions.add(hvar)

        val dubrovnik = Region("Dubrovnik",80)
        dubrovnik.addLocation(Location())
        regions.add(dubrovnik)

        val cavtat = Region("Cavtat",81)
        cavtat.addLocation(Location())
        regions.add(cavtat)

        val kotor = Region("Kotor",82)
        kotor.addLocation(Location())
        regions.add(kotor)

        val tivat = Region("Tivat",83)
        tivat.addLocation(Location())
        regions.add(tivat)

        var j = 90
        regions.forEach { region ->

            region.addStore(Store("Antibes", 99))
            region.addStore(Store("M55", 5))
            region.addStore(Store("Napoli", 15))
            region.addStore(Store("Athene", 10))
            region.addStore(Store("Croatia", 98))
            region.addStore(Store("AntiguaSxm", 14))
            region.addStore(Store("FTL", 13))

            region.locations.forEach { location ->
                location.id = j
                j++
            }
        }

        return regions.toList()
    }

    override fun buildAlbania(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        //Albania
        val sarande = Region("Sarande",84)
        sarande.addLocation(Location())
        regions.add(sarande)

        var j = 110
        regions.forEach { region ->

            region.addStore(Store("Antibes", 99))
            region.addStore(Store("M55", 5))
            region.addStore(Store("Napoli", 15))
            region.addStore(Store("Athene", 10))
            region.addStore(Store("Croatia", 98))
            region.addStore(Store("AntiguaSxm", 14))
            region.addStore(Store("FTL", 13))

            region.locations.forEach { location ->
                location.id = j
                j++
            }
        }

        return regions.toList()
    }

    override fun buildGreece(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        /**
         * Greece (Ionian)
         */
        val corfu = Region("Corfu",85)
        corfu.addLocation(Location())
        regions.add(corfu)

        val zakynthos = Region("Zakynthos",86)
        zakynthos.addLocation(Location())
        regions.add(zakynthos)

        val kefalonia = Region("Kefalonia",87)
        kefalonia.addLocation(Location())
        regions.add(kefalonia)

        /**
         * Greece (Eagen)
         */
        val athens = Region("Athens",88)
        athens.addLocation(Location())
        regions.add(athens)

        val mikonos = Region("Mikonos",89)
        mikonos.addLocation(Location())
        regions.add(mikonos)

        val santorini = Region("Santorini",90)
        santorini.addLocation(Location())
        regions.add(santorini)

        val rhodes = Region("Rhodes",91)
        rhodes.addLocation(Location())
        regions.add(rhodes)

        val kos = Region("Kos",92)
        kos.addLocation(Location())
        regions.add(kos)

        var j = 120
        regions.forEach { region ->

            region.addStore(Store("Antibes", 99))
            region.addStore(Store("M55", 5))
            region.addStore(Store("Napoli", 15))
            region.addStore(Store("Athene", 10))
            region.addStore(Store("Croatia", 98))
            region.addStore(Store("AntiguaSxm", 14))
            region.addStore(Store("FTL", 13))

            region.locations.forEach { location ->
                location.id = j
                j++
            }
        }

        return regions.toList()
    }

    override fun buildTurkey(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        //Turkey
        val bodrum = Region("Bodrum",93)
        bodrum.addLocation(Location())
        regions.add(bodrum)

        val izmir = Region("Izmir",94)
        izmir.addLocation(Location())
        regions.add(izmir)

        val marmaris = Region("Marmaris",95)
        marmaris.addLocation(Location())
        regions.add(marmaris)

        val antalya = Region("Antalya",96)
        antalya.addLocation(Location())
        regions.add(antalya)

        var j = 130
        regions.forEach { region ->

            region.addStore(Store("Antibes", 99))
            region.addStore(Store("M55", 5))
            region.addStore(Store("Napoli", 15))
            region.addStore(Store("Athene", 10))
            region.addStore(Store("Croatia", 98))
            region.addStore(Store("AntiguaSxm", 14))
            region.addStore(Store("FTL", 13))

            region.locations.forEach { location ->
                location.id = j
                j++
            }
        }

        return regions.toList()
    }

    override fun buildCaribbean(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        // Caribbean
        val saintMaarten = Region("Saint Maarten",97)
        saintMaarten.addLocation(Location())
        regions.add(saintMaarten)

        val antigua = Region("Antigua",98)
        antigua.addLocation(Location())
        regions.add(antigua)

        val stBarths = Region("St. barths",99)
        stBarths.addLocation(Location())
        regions.add(stBarths)

        val stLucia = Region("St. Lucia",100)
        stLucia.addLocation(Location())
        regions.add(stLucia)

        val dominica = Region("Dominica",101)
        dominica.addLocation(Location())
        regions.add(dominica)

        val guadeloupe = Region("Guadeloupe",102)
        guadeloupe.addLocation(Location())
        regions.add(guadeloupe)

        val bvi = Region("BVI",103)
        bvi.addLocation(Location())
        regions.add(bvi)

        val usvi = Region("USVI",104)
        usvi.addLocation(Location())
        regions.add(usvi)

        var j = 140
        regions.forEach { region ->

            region.addStore(Store("M55", 5))
            region.addStore(Store("AntiguaSxm", 14))
            region.addStore(Store("FTL", 13))

            region.locations.forEach { location ->
                location.id = j
                j++
            }
        }

        return regions.toList()
    }

    override fun buildFloridaAndBahamas(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        //Florida & Bahamas
        val miami = Region("Miami",105)
        miami.addLocation(Location())
        regions.add(miami)

        val fortLauderdale = Region("Fort Lauderdale",106)
        fortLauderdale.addLocation(Location())
        regions.add(fortLauderdale)

        val westPalmBeach = Region("West Palm beach",107)
        westPalmBeach.addLocation(Location())
        regions.add(westPalmBeach)

        val nassau = Region("Nassau",108)
        nassau.addLocation(Location())
        regions.add(nassau)

        var j = 150
        regions.forEach { region ->

            region.addStore(Store("M55", 5))
            region.addStore(Store("AntiguaSxm", 14))
            region.addStore(Store("FTL", 13))

            region.locations.forEach { location ->
                location.id = j
                j++
            }
        }

        return regions.toList()
    }

    override fun buildMaldives(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        //Maldives
        val male = Region("Male",109)
        male.addLocation(Location())
        regions.add(male)

        var j = 160
        regions.forEach { region ->

            region.addStore(Store("M55", 5))
            region.addStore(Store("AntiguaSxm", 14))
            region.addStore(Store("FTL", 13))

            region.locations.forEach { location ->
                location.id = j
                j++
            }
        }

        return regions.toList()
    }

    override fun getAllRegions(): List<Region> {
        val allRegions: MutableList<Region> = mutableListOf()

        allRegions.addAll(buildSpainMainLand1())
        allRegions.addAll(buildSpainMainLand2())
        allRegions.addAll(buildBaleraicsIslands())
        allRegions.addAll(buildSouthOfFrance())
        allRegions.addAll(buildItaly())
        allRegions.addAll(buildSicily())
        allRegions.addAll(buildMalta())
        allRegions.addAll(buildCorsica())
        allRegions.addAll(buildSardinia())
        allRegions.addAll(buildCroatiaAndMontenegro())
        allRegions.addAll(buildAlbania())
        allRegions.addAll(buildGreece())
        allRegions.addAll(buildTurkey())
        allRegions.addAll(buildCaribbean())
        allRegions.addAll(buildFloridaAndBahamas())
        allRegions.addAll(buildMaldives())

        return allRegions
    }
}

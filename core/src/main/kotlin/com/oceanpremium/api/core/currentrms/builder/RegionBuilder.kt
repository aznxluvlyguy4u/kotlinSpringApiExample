package com.oceanpremium.api.core.currentrms.builder

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

interface RegionBuilder {
    fun getAllRegions(): List<Region>

    fun buildSpainMainLand1(): List<Region>
    fun buildSpainMainLand2(): List<Region>
    fun buildBaleraicsIslands(): List<Region>
    fun buildSouthOfFrance(): List<Region>
    fun buildLiguriaItaly(): List<Region>
    fun buildTuscanyItaly(): List<Region>
    fun buildAmalfiItaly(): List<Region>
    fun buildSouthOfItaly(): List<Region>
    fun buildSicily(): List<Region>
    fun buildMalta(): List<Region>
    fun buildCorsica(): List<Region>
    fun buildSardinia(): List<Region>
    fun buildCroatiaAndMontenegro(): List<Region>
    fun buildAlbania(): List<Region>
    fun buildGreeceIonian(): List<Region>
    fun buildGreeceEagen(): List<Region>
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

        // Ibiza
        val ibiza = Region("Ibiza",10)

        val ibizaIslandLoc = Location("Ibiza Island")
        val formenteraIslandLoc = Location("Formentera Island")

        ibizaIslandLoc.addNativeStore(storeBuilder.buildIbiza(1, 50))
        ibizaIslandLoc.addAlternativeStore(storeBuilder.buildPalmaED(5, 200))
        ibizaIslandLoc.addAlternativeStore(storeBuilder.buildPalmaNG(5, 200))

        ibizaIslandLoc.addGrayStore(storeBuilder.buildM55(168))
        ibizaIslandLoc.addGrayStore(storeBuilder.buildAntibes(19))
        ibizaIslandLoc.addGrayStore(storeBuilder.buildOlbiaNA(168))
        ibizaIslandLoc.addGrayStore(storeBuilder.buildOlbiaSYS(168))
        ibizaIslandLoc.addGrayStore(storeBuilder.buildGenova(120))
        ibizaIslandLoc.addGrayStore(storeBuilder.buildNapoli(168))
        ibizaIslandLoc.addGrayStore(storeBuilder.buildPalermo(216))
        ibizaIslandLoc.addGrayStore(storeBuilder.buildRiposto(216))
        ibizaIslandLoc.addGrayStore(storeBuilder.buildMalta(216))
        ibizaIslandLoc.addGrayStore(storeBuilder.buildAthensSotiris(216))
        ibizaIslandLoc.addGrayStore(storeBuilder.buildAthensH360(216))
        ibizaIslandLoc.addGrayStore(storeBuilder.buildCorfu(216))
        ibizaIslandLoc.addGrayStore(storeBuilder.buildKosA1(216))
        ibizaIslandLoc.addGrayStore(storeBuilder.buildRhodes(216))
        ibizaIslandLoc.addGrayStore(storeBuilder.buildVenice(96))
        ibizaIslandLoc.addGrayStore(storeBuilder.buildZadar(120))
        ibizaIslandLoc.addGrayStore(storeBuilder.buildSplit(120))
        ibizaIslandLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(120))
        ibizaIslandLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(120))
        ibizaIslandLoc.addGrayStore(storeBuilder.buildBCM(216))
        ibizaIslandLoc.addGrayStore(storeBuilder.buildTivat(216))
        ibizaIslandLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(72))

        formenteraIslandLoc.addNativeStore(storeBuilder.buildIbiza(2, 100))
        formenteraIslandLoc.addAlternativeStore(storeBuilder.buildPalmaED(6, 250))
        formenteraIslandLoc.addAlternativeStore(storeBuilder.buildPalmaNG(6, 200))

        formenteraIslandLoc.addGrayStore(storeBuilder.buildM55(168))
        formenteraIslandLoc.addGrayStore(storeBuilder.buildAntibes(23))
        formenteraIslandLoc.addGrayStore(storeBuilder.buildOlbiaNA(168))
        formenteraIslandLoc.addGrayStore(storeBuilder.buildOlbiaSYS(168))
        formenteraIslandLoc.addGrayStore(storeBuilder.buildGenova(120))
        formenteraIslandLoc.addGrayStore(storeBuilder.buildNapoli(168))
        formenteraIslandLoc.addGrayStore(storeBuilder.buildPalermo(216))
        formenteraIslandLoc.addGrayStore(storeBuilder.buildRiposto(216))
        formenteraIslandLoc.addGrayStore(storeBuilder.buildMalta(216))
        formenteraIslandLoc.addGrayStore(storeBuilder.buildAthensSotiris(216))
        formenteraIslandLoc.addGrayStore(storeBuilder.buildAthensH360(216))
        formenteraIslandLoc.addGrayStore(storeBuilder.buildCorfu(216))
        formenteraIslandLoc.addGrayStore(storeBuilder.buildKosA1(216))
        formenteraIslandLoc.addGrayStore(storeBuilder.buildRhodes(216))
        formenteraIslandLoc.addGrayStore(storeBuilder.buildVenice(96))
        formenteraIslandLoc.addGrayStore(storeBuilder.buildZadar(120))
        formenteraIslandLoc.addGrayStore(storeBuilder.buildSplit(120))
        formenteraIslandLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(120))
        formenteraIslandLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(120))
        formenteraIslandLoc.addGrayStore(storeBuilder.buildBCM(216))
        formenteraIslandLoc.addGrayStore(storeBuilder.buildTivat(216))
        formenteraIslandLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(72))

        ibiza.addLocation(ibizaIslandLoc)
        ibiza.addLocation(formenteraIslandLoc)

        regions.add(ibiza)


        // Mallorca
        val mallorca = Region("Mallorca",11)

        val islandNorthEastLoc = Location("Island - North/East")
        val palmaLoc = Location("Palma")
        val portPortalsLoc = Location("Port Portals")
        val portoAdrianoLoc = Location("Porto Adriano")

        islandNorthEastLoc.addNativeStore(storeBuilder.buildPalmaED(2, 100))
        islandNorthEastLoc.addNativeStore(storeBuilder.buildPalmaNG(2, 100))
        islandNorthEastLoc.addAlternativeStore(storeBuilder.buildIbiza(6, 200))

        palmaLoc.addNativeStore(storeBuilder.buildPalmaED(1, 50))
        palmaLoc.addNativeStore(storeBuilder.buildPalmaNG(1, 50))
        palmaLoc.addAlternativeStore(storeBuilder.buildIbiza(6, 200))

        portPortalsLoc.addNativeStore(storeBuilder.buildPalmaED(1, 50))
        portPortalsLoc.addNativeStore(storeBuilder.buildPalmaNG(1, 50))
        portPortalsLoc.addAlternativeStore(storeBuilder.buildIbiza(6, 200))

        portoAdrianoLoc.addNativeStore(storeBuilder.buildPalmaED(1, 50))
        portoAdrianoLoc.addNativeStore(storeBuilder.buildPalmaNG(1, 50))
        portoAdrianoLoc.addAlternativeStore(storeBuilder.buildIbiza(6, 200))

        mallorca.addLocation(islandNorthEastLoc)
        mallorca.addLocation(palmaLoc)
        mallorca.addLocation(portPortalsLoc)
        mallorca.addLocation(portoAdrianoLoc)

        mallorca.locations.forEach { location ->
            location.addGrayStore(storeBuilder.buildM55(168))
            location.addGrayStore(storeBuilder.buildAntibes(18))
            location.addGrayStore(storeBuilder.buildOlbiaNA(168))
            location.addGrayStore(storeBuilder.buildOlbiaSYS(168))
            location.addGrayStore(storeBuilder.buildGenova(120))
            location.addGrayStore(storeBuilder.buildNapoli(168))
            location.addGrayStore(storeBuilder.buildPalermo(216))
            location.addGrayStore(storeBuilder.buildRiposto(216))
            location.addGrayStore(storeBuilder.buildMalta(216))
            location.addGrayStore(storeBuilder.buildAthensSotiris(216))
            location.addGrayStore(storeBuilder.buildAthensH360(216))
            location.addGrayStore(storeBuilder.buildCorfu(216))
            location.addGrayStore(storeBuilder.buildKosA1(216))
            location.addGrayStore(storeBuilder.buildRhodes(216))
            location.addGrayStore(storeBuilder.buildVenice(96))
            location.addGrayStore(storeBuilder.buildZadar(120))
            location.addGrayStore(storeBuilder.buildSplit(120))
            location.addGrayStore(storeBuilder.buildDubrovnikKristijan(120))
            location.addGrayStore(storeBuilder.buildDubrovnikBWA(120))
            location.addGrayStore(storeBuilder.buildBCM(216))
            location.addGrayStore(storeBuilder.buildTivat(216))
            location.addNewItemsStore(storeBuilder.buildNewItemsStore(72))
        }

        regions.add(mallorca)


        // Menorca
        val menorca = Region("Menorca",12)

        val menorcaLoc = Location("Menorca Island")

        menorcaLoc.addNativeStore(storeBuilder.buildPalmaED(3, 200))
        menorcaLoc.addNativeStore(storeBuilder.buildPalmaNG(3, 200))
        menorcaLoc.addAlternativeStore(storeBuilder.buildIbiza(8, 300))

        menorcaLoc.addGrayStore(storeBuilder.buildM55(168))
        menorcaLoc.addGrayStore(storeBuilder.buildAntibes(17))
        menorcaLoc.addGrayStore(storeBuilder.buildOlbiaNA(168))
        menorcaLoc.addGrayStore(storeBuilder.buildOlbiaSYS(168))
        menorcaLoc.addGrayStore(storeBuilder.buildGenova(120))
        menorcaLoc.addGrayStore(storeBuilder.buildNapoli(168))
        menorcaLoc.addGrayStore(storeBuilder.buildPalermo(216))
        menorcaLoc.addGrayStore(storeBuilder.buildRiposto(216))
        menorcaLoc.addGrayStore(storeBuilder.buildMalta(216))
        menorcaLoc.addGrayStore(storeBuilder.buildAthensSotiris(216))
        menorcaLoc.addGrayStore(storeBuilder.buildAthensH360(216))
        menorcaLoc.addGrayStore(storeBuilder.buildCorfu(216))
        menorcaLoc.addGrayStore(storeBuilder.buildKosA1(216))
        menorcaLoc.addGrayStore(storeBuilder.buildRhodes(216))
        menorcaLoc.addGrayStore(storeBuilder.buildVenice(96))
        menorcaLoc.addGrayStore(storeBuilder.buildZadar(120))
        menorcaLoc.addGrayStore(storeBuilder.buildSplit(120))
        menorcaLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(120))
        menorcaLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(120))
        menorcaLoc.addGrayStore(storeBuilder.buildBCM(216))
        menorcaLoc.addGrayStore(storeBuilder.buildTivat(216))
        menorcaLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(72))

        menorca.addLocation(menorcaLoc)

        regions.add(menorca)

        return regions.toList()
    }

    override fun buildSouthOfFrance(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        // Port Vendres
        val portVendres = Region("Port Vendres",13)

        val portVendresLoc = Location("Port Vendres")

        portVendresLoc.addNativeStore(storeBuilder.buildAntibes(6, 500))
        portVendresLoc.addAlternativeStore(storeBuilder.buildGenova(24, 450))

        portVendresLoc.addGrayStore(storeBuilder.buildM55(24))
        portVendresLoc.addGrayStore(storeBuilder.buildPalmaED(96))
        portVendresLoc.addGrayStore(storeBuilder.buildPalmaNG(96))
        portVendresLoc.addGrayStore(storeBuilder.buildIbiza(96))
        portVendresLoc.addGrayStore(storeBuilder.buildOlbiaNA(96))
        portVendresLoc.addGrayStore(storeBuilder.buildOlbiaSYS(96))
        portVendresLoc.addGrayStore(storeBuilder.buildNapoli(96))
        portVendresLoc.addGrayStore(storeBuilder.buildPalermo(168))
        portVendresLoc.addGrayStore(storeBuilder.buildRiposto(168))
        portVendresLoc.addGrayStore(storeBuilder.buildMalta(168))
        portVendresLoc.addGrayStore(storeBuilder.buildAthensSotiris(168))
        portVendresLoc.addGrayStore(storeBuilder.buildAthensH360(168))
        portVendresLoc.addGrayStore(storeBuilder.buildCorfu(192))
        portVendresLoc.addGrayStore(storeBuilder.buildKosA1(216))
        portVendresLoc.addGrayStore(storeBuilder.buildRhodes(216))
        portVendresLoc.addGrayStore(storeBuilder.buildVenice(72))
        portVendresLoc.addGrayStore(storeBuilder.buildZadar(96))
        portVendresLoc.addGrayStore(storeBuilder.buildSplit(96))
        portVendresLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(96))
        portVendresLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(96))
        portVendresLoc.addGrayStore(storeBuilder.buildBCM(120))
        portVendresLoc.addGrayStore(storeBuilder.buildTivat(120))
        portVendresLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(72))

        portVendres.addLocation(portVendresLoc)

        regions.add(portVendres)


        // Marseilles
        val marseilles = Region("Marseilles",14)

        val marseillesLoc = Location("Marseilles")

        marseillesLoc.addNativeStore(storeBuilder.buildAntibes(5, 184))
        marseillesLoc.addAlternativeStore(storeBuilder.buildGenova(24, 450))

        marseillesLoc.addGrayStore(storeBuilder.buildM55(24))
        marseillesLoc.addGrayStore(storeBuilder.buildPalmaED(96))
        marseillesLoc.addGrayStore(storeBuilder.buildPalmaNG(96))
        marseillesLoc.addGrayStore(storeBuilder.buildIbiza(96))
        marseillesLoc.addGrayStore(storeBuilder.buildOlbiaNA(96))
        marseillesLoc.addGrayStore(storeBuilder.buildOlbiaSYS(96))
        marseillesLoc.addGrayStore(storeBuilder.buildNapoli(96))
        marseillesLoc.addGrayStore(storeBuilder.buildPalermo(168))
        marseillesLoc.addGrayStore(storeBuilder.buildRiposto(168))
        marseillesLoc.addGrayStore(storeBuilder.buildMalta(168))
        marseillesLoc.addGrayStore(storeBuilder.buildAthensSotiris(168))
        marseillesLoc.addGrayStore(storeBuilder.buildAthensH360(168))
        marseillesLoc.addGrayStore(storeBuilder.buildCorfu(192))
        marseillesLoc.addGrayStore(storeBuilder.buildKosA1(216))
        marseillesLoc.addGrayStore(storeBuilder.buildRhodes(216))
        marseillesLoc.addGrayStore(storeBuilder.buildVenice(72))
        marseillesLoc.addGrayStore(storeBuilder.buildZadar(96))
        marseillesLoc.addGrayStore(storeBuilder.buildSplit(96))
        marseillesLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(96))
        marseillesLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(96))
        marseillesLoc.addGrayStore(storeBuilder.buildBCM(120))
        marseillesLoc.addGrayStore(storeBuilder.buildTivat(120))
        marseillesLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(72))

        marseilles.addLocation(marseillesLoc)
        regions.add(marseilles)


        // Toulon
        val toulon = Region("Toulon",15)

        val cavalaireSurMerLoc = Location("Cavalaire sur Mer")
        val hyeresLoc = Location("Hyeres")
        val toulonLoc = Location("Toulon")
        val laCiotatLoc = Location("La Ciotat")

        cavalaireSurMerLoc.addNativeStore(storeBuilder.buildAntibes(4, 102))
        cavalaireSurMerLoc.addAlternativeStore(storeBuilder.buildGenova(24, 450))

        hyeresLoc.addNativeStore(storeBuilder.buildAntibes(4, 136))
        hyeresLoc.addAlternativeStore(storeBuilder.buildGenova(24, 450))

        toulonLoc.addNativeStore(storeBuilder.buildAntibes(4, 134))
        toulonLoc.addAlternativeStore(storeBuilder.buildGenova(24, 450))

        laCiotatLoc.addNativeStore(storeBuilder.buildAntibes(4, 173))
        laCiotatLoc.addAlternativeStore(storeBuilder.buildGenova(24, 450))

        toulon.addLocation(cavalaireSurMerLoc)
        toulon.addLocation(hyeresLoc)
        toulon.addLocation(toulonLoc)
        toulon.addLocation(laCiotatLoc)

        toulon.locations.forEach { location ->
            location.addGrayStore(storeBuilder.buildM55(24))
            location.addGrayStore(storeBuilder.buildPalmaED(96))
            location.addGrayStore(storeBuilder.buildPalmaNG(96))
            location.addGrayStore(storeBuilder.buildIbiza(96))
            location.addGrayStore(storeBuilder.buildOlbiaNA(96))
            location.addGrayStore(storeBuilder.buildOlbiaSYS(96))
            location.addGrayStore(storeBuilder.buildNapoli(96))
            location.addGrayStore(storeBuilder.buildPalermo(168))
            location.addGrayStore(storeBuilder.buildRiposto(168))
            location.addGrayStore(storeBuilder.buildMalta(168))
            location.addGrayStore(storeBuilder.buildAthensSotiris(168))
            location.addGrayStore(storeBuilder.buildAthensH360(168))
            location.addGrayStore(storeBuilder.buildCorfu(192))
            location.addGrayStore(storeBuilder.buildKosA1(216))
            location.addGrayStore(storeBuilder.buildRhodes(216))
            location.addGrayStore(storeBuilder.buildVenice(72))
            location.addGrayStore(storeBuilder.buildZadar(96))
            location.addGrayStore(storeBuilder.buildSplit(96))
            location.addGrayStore(storeBuilder.buildDubrovnikKristijan(96))
            location.addGrayStore(storeBuilder.buildDubrovnikBWA(96))
            location.addGrayStore(storeBuilder.buildBCM(120))
            location.addGrayStore(storeBuilder.buildTivat(120))
            location.addNewItemsStore(storeBuilder.buildNewItemsStore(72))
        }

        regions.add(toulon)


        // St Tropez
        val stTropez = Region("St. Tropez",16)

        val saintRaphaelLoc = Location("Saint Raphael")
        val stTropezLoc = Location("St. Tropez")
        val pampellonneBeachLoc = Location("Pampellonne beach")

        saintRaphaelLoc.addNativeStore(storeBuilder.buildAntibes(3, 52))
        saintRaphaelLoc.addAlternativeStore(storeBuilder.buildGenova(24, 450))

        stTropezLoc.addNativeStore(storeBuilder.buildAntibes(4, 97))
        stTropezLoc.addAlternativeStore(storeBuilder.buildGenova(24, 450))

        pampellonneBeachLoc.addNativeStore(storeBuilder.buildAntibes(4, 100))
        pampellonneBeachLoc.addAlternativeStore(storeBuilder.buildGenova(24, 450))

        stTropez.addLocation(saintRaphaelLoc)
        stTropez.addLocation(stTropezLoc)
        stTropez.addLocation(pampellonneBeachLoc)

        stTropez.locations.forEach { location ->
            location.addGrayStore(storeBuilder.buildM55(24))
            location.addGrayStore(storeBuilder.buildPalmaED(96))
            location.addGrayStore(storeBuilder.buildPalmaNG(96))
            location.addGrayStore(storeBuilder.buildIbiza(96))
            location.addGrayStore(storeBuilder.buildOlbiaNA(96))
            location.addGrayStore(storeBuilder.buildOlbiaSYS(96))
            location.addGrayStore(storeBuilder.buildNapoli(96))
            location.addGrayStore(storeBuilder.buildPalermo(168))
            location.addGrayStore(storeBuilder.buildRiposto(168))
            location.addGrayStore(storeBuilder.buildMalta(168))
            location.addGrayStore(storeBuilder.buildAthensSotiris(168))
            location.addGrayStore(storeBuilder.buildAthensH360(168))
            location.addGrayStore(storeBuilder.buildCorfu(192))
            location.addGrayStore(storeBuilder.buildKosA1(216))
            location.addGrayStore(storeBuilder.buildRhodes(216))
            location.addGrayStore(storeBuilder.buildVenice(72))
            location.addGrayStore(storeBuilder.buildZadar(96))
            location.addGrayStore(storeBuilder.buildSplit(96))
            location.addGrayStore(storeBuilder.buildDubrovnikKristijan(96))
            location.addGrayStore(storeBuilder.buildDubrovnikBWA(96))
            location.addGrayStore(storeBuilder.buildBCM(120))
            location.addGrayStore(storeBuilder.buildTivat(120))
            location.addNewItemsStore(storeBuilder.buildNewItemsStore(72))
        }

        regions.add(stTropez)


        // Cannes
        val cannes = Region("Cannes",17)

        val cannesLoc = Location("Cannes")
        val mandelieuLaNapouleLoc = Location("Mandelieu la Napoule")
        val theuleSurMerLoc = Location("Theule sur Mer")

        cannesLoc.addNativeStore(storeBuilder.buildAntibes(1, 20))
        cannesLoc.addAlternativeStore(storeBuilder.buildGenova(24, 450))

        mandelieuLaNapouleLoc.addNativeStore(storeBuilder.buildAntibes(2, 25))
        mandelieuLaNapouleLoc.addAlternativeStore(storeBuilder.buildGenova(24, 450))

        theuleSurMerLoc.addNativeStore(storeBuilder.buildAntibes(2, 35))
        theuleSurMerLoc.addAlternativeStore(storeBuilder.buildGenova(24, 450))

        cannes.addLocation(cannesLoc)
        cannes.addLocation(mandelieuLaNapouleLoc)
        cannes.addLocation(theuleSurMerLoc)

        cannes.locations.forEach { location ->
            location.addGrayStore(storeBuilder.buildM55(24))
            location.addGrayStore(storeBuilder.buildPalmaED(96))
            location.addGrayStore(storeBuilder.buildPalmaNG(96))
            location.addGrayStore(storeBuilder.buildIbiza(96))
            location.addGrayStore(storeBuilder.buildOlbiaNA(96))
            location.addGrayStore(storeBuilder.buildOlbiaSYS(96))
            location.addGrayStore(storeBuilder.buildNapoli(96))
            location.addGrayStore(storeBuilder.buildPalermo(168))
            location.addGrayStore(storeBuilder.buildRiposto(168))
            location.addGrayStore(storeBuilder.buildMalta(168))
            location.addGrayStore(storeBuilder.buildAthensSotiris(168))
            location.addGrayStore(storeBuilder.buildAthensH360(168))
            location.addGrayStore(storeBuilder.buildCorfu(192))
            location.addGrayStore(storeBuilder.buildKosA1(216))
            location.addGrayStore(storeBuilder.buildRhodes(216))
            location.addGrayStore(storeBuilder.buildVenice(72))
            location.addGrayStore(storeBuilder.buildZadar(96))
            location.addGrayStore(storeBuilder.buildSplit(96))
            location.addGrayStore(storeBuilder.buildDubrovnikKristijan(96))
            location.addGrayStore(storeBuilder.buildDubrovnikBWA(96))
            location.addGrayStore(storeBuilder.buildBCM(120))
            location.addGrayStore(storeBuilder.buildTivat(120))
            location.addNewItemsStore(storeBuilder.buildNewItemsStore(72))
        }

        regions.add(cannes)


        // Antibes
        val antibes = Region("Antibes",18)

        val marinaBaieDesAngesLoc = Location("Marina Baie Des Anges")
        val antibesLoc = Location("Antibes")
        val golfeJuanLoc = Location("Golfe Juan")

        marinaBaieDesAngesLoc.addNativeStore(storeBuilder.buildAntibes(1, 20))
        marinaBaieDesAngesLoc.addAlternativeStore(storeBuilder.buildGenova(24, 450))

        antibesLoc.addNativeStore(storeBuilder.buildAntibes(1, 15))
        antibesLoc.addAlternativeStore(storeBuilder.buildGenova(24, 450))

        golfeJuanLoc.addNativeStore(storeBuilder.buildAntibes(1, 15))
        golfeJuanLoc.addAlternativeStore(storeBuilder.buildGenova(24, 450))

        antibes.addLocation(marinaBaieDesAngesLoc)
        antibes.addLocation(antibesLoc)
        antibes.addLocation(golfeJuanLoc)

        antibes.locations.forEach { location ->
            location.addGrayStore(storeBuilder.buildM55(24))
            location.addGrayStore(storeBuilder.buildPalmaED(96))
            location.addGrayStore(storeBuilder.buildPalmaNG(96))
            location.addGrayStore(storeBuilder.buildIbiza(96))
            location.addGrayStore(storeBuilder.buildOlbiaNA(96))
            location.addGrayStore(storeBuilder.buildOlbiaSYS(96))
            location.addGrayStore(storeBuilder.buildNapoli(96))
            location.addGrayStore(storeBuilder.buildPalermo(168))
            location.addGrayStore(storeBuilder.buildRiposto(168))
            location.addGrayStore(storeBuilder.buildMalta(168))
            location.addGrayStore(storeBuilder.buildAthensSotiris(168))
            location.addGrayStore(storeBuilder.buildAthensH360(168))
            location.addGrayStore(storeBuilder.buildCorfu(192))
            location.addGrayStore(storeBuilder.buildKosA1(216))
            location.addGrayStore(storeBuilder.buildRhodes(216))
            location.addGrayStore(storeBuilder.buildVenice(72))
            location.addGrayStore(storeBuilder.buildZadar(96))
            location.addGrayStore(storeBuilder.buildSplit(96))
            location.addGrayStore(storeBuilder.buildDubrovnikKristijan(96))
            location.addGrayStore(storeBuilder.buildDubrovnikBWA(96))
            location.addGrayStore(storeBuilder.buildBCM(120))
            location.addGrayStore(storeBuilder.buildTivat(120))
            location.addNewItemsStore(storeBuilder.buildNewItemsStore(72))
        }

        regions.add(antibes)


        // Nice
        val nice = Region("Nice",19)

        val villeFrancheSurMerLoc = Location("Villefranche sur Mer")
        val niceLoc = Location("Nice")
        val cagnesSurMer = Location("Cagnes sur Mer")

        villeFrancheSurMerLoc.addNativeStore(storeBuilder.buildAntibes(1, 35))
        villeFrancheSurMerLoc.addAlternativeStore(storeBuilder.buildGenova(24, 450))

        niceLoc.addNativeStore(storeBuilder.buildAntibes(1, 25))
        niceLoc.addAlternativeStore(storeBuilder.buildGenova(24, 450))

        cagnesSurMer.addNativeStore(storeBuilder.buildAntibes(1, 15))
        cagnesSurMer.addAlternativeStore(storeBuilder.buildGenova(24, 450))

        nice.addLocation(villeFrancheSurMerLoc)
        nice.addLocation(niceLoc)
        nice.addLocation(cagnesSurMer)

        nice.locations.forEach { location ->
            location.addGrayStore(storeBuilder.buildM55(24))
            location.addGrayStore(storeBuilder.buildPalmaED(96))
            location.addGrayStore(storeBuilder.buildPalmaNG(96))
            location.addGrayStore(storeBuilder.buildIbiza(96))
            location.addGrayStore(storeBuilder.buildOlbiaNA(96))
            location.addGrayStore(storeBuilder.buildOlbiaSYS(96))
            location.addGrayStore(storeBuilder.buildNapoli(96))
            location.addGrayStore(storeBuilder.buildPalermo(168))
            location.addGrayStore(storeBuilder.buildRiposto(168))
            location.addGrayStore(storeBuilder.buildMalta(168))
            location.addGrayStore(storeBuilder.buildAthensSotiris(168))
            location.addGrayStore(storeBuilder.buildAthensH360(168))
            location.addGrayStore(storeBuilder.buildCorfu(192))
            location.addGrayStore(storeBuilder.buildKosA1(216))
            location.addGrayStore(storeBuilder.buildRhodes(216))
            location.addGrayStore(storeBuilder.buildVenice(72))
            location.addGrayStore(storeBuilder.buildZadar(96))
            location.addGrayStore(storeBuilder.buildSplit(96))
            location.addGrayStore(storeBuilder.buildDubrovnikKristijan(96))
            location.addGrayStore(storeBuilder.buildDubrovnikBWA(96))
            location.addGrayStore(storeBuilder.buildBCM(120))
            location.addGrayStore(storeBuilder.buildTivat(120))
            location.addNewItemsStore(storeBuilder.buildNewItemsStore(72))
        }

        regions.add(nice)


        // Monaco
        val monaco = Region("Monaco",20)

        val monacoLoc = Location("Monaco")
        val capDAilLoc = Location("Cap d'Ail")
        val saintJeanCapFerratLoc = Location("Saint Jean Cap Ferrat")
        val beaulieuSurMer = Location("Beaulieu sur Mer")

        monacoLoc.addNativeStore(storeBuilder.buildAntibes(2, 50))
        monacoLoc.addAlternativeStore(storeBuilder.buildGenova(24, 450))

        capDAilLoc.addNativeStore(storeBuilder.buildAntibes(2, 45))
        capDAilLoc.addAlternativeStore(storeBuilder.buildGenova(24, 450))

        saintJeanCapFerratLoc.addNativeStore(storeBuilder.buildAntibes(2, 40))
        saintJeanCapFerratLoc.addAlternativeStore(storeBuilder.buildGenova(24, 450))

        beaulieuSurMer.addNativeStore(storeBuilder.buildAntibes(2, 35))
        beaulieuSurMer.addAlternativeStore(storeBuilder.buildGenova(24, 450))

        monaco.addLocation(monacoLoc)
        monaco.addLocation(capDAilLoc)
        monaco.addLocation(saintJeanCapFerratLoc)
        monaco.addLocation(beaulieuSurMer)

        monaco.locations.forEach { location ->
            location.addGrayStore(storeBuilder.buildM55(24))
            location.addGrayStore(storeBuilder.buildPalmaED(96))
            location.addGrayStore(storeBuilder.buildPalmaNG(96))
            location.addGrayStore(storeBuilder.buildIbiza(96))
            location.addGrayStore(storeBuilder.buildOlbiaNA(96))
            location.addGrayStore(storeBuilder.buildOlbiaSYS(96))
            location.addGrayStore(storeBuilder.buildNapoli(96))
            location.addGrayStore(storeBuilder.buildPalermo(168))
            location.addGrayStore(storeBuilder.buildRiposto(168))
            location.addGrayStore(storeBuilder.buildMalta(168))
            location.addGrayStore(storeBuilder.buildAthensSotiris(168))
            location.addGrayStore(storeBuilder.buildAthensH360(168))
            location.addGrayStore(storeBuilder.buildCorfu(192))
            location.addGrayStore(storeBuilder.buildKosA1(216))
            location.addGrayStore(storeBuilder.buildRhodes(216))
            location.addGrayStore(storeBuilder.buildVenice(72))
            location.addGrayStore(storeBuilder.buildZadar(96))
            location.addGrayStore(storeBuilder.buildSplit(96))
            location.addGrayStore(storeBuilder.buildDubrovnikKristijan(96))
            location.addGrayStore(storeBuilder.buildDubrovnikBWA(96))
            location.addGrayStore(storeBuilder.buildBCM(120))
            location.addGrayStore(storeBuilder.buildTivat(120))
            location.addNewItemsStore(storeBuilder.buildNewItemsStore(72))
        }

        regions.add(monaco)

        return regions.toList()
    }

    override fun buildLiguriaItaly(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        /**
         * Liguria Italy
         */
        val sanremo = Region("Sanremo",21)

        val ventimigliaLoc = Location("Ventimiglia")
        val sanremoLoc = Location("Sanremo")
        val imperiaLoc = Location("Imperia")

        ventimigliaLoc.addNativeStore(storeBuilder.buildAntibes(3, 65))
        ventimigliaLoc.addAlternativeStore(storeBuilder.buildGenova(12, 400))

        sanremoLoc.addNativeStore(storeBuilder.buildAntibes(3, 80))
        sanremoLoc.addAlternativeStore(storeBuilder.buildGenova(12, 400))

        imperiaLoc.addNativeStore(storeBuilder.buildAntibes(3, 100))
        imperiaLoc.addAlternativeStore(storeBuilder.buildGenova(12, 400))

        sanremo.addLocation(ventimigliaLoc)
        sanremo.addLocation(sanremoLoc)
        sanremo.addLocation(imperiaLoc)

        sanremo.locations.forEach { location ->
            location.addGrayStore(storeBuilder.buildM55(96))
            location.addGrayStore(storeBuilder.buildPalmaED(96))
            location.addGrayStore(storeBuilder.buildPalmaNG(96))
            location.addGrayStore(storeBuilder.buildIbiza(96))
            location.addGrayStore(storeBuilder.buildOlbiaNA(72))
            location.addGrayStore(storeBuilder.buildOlbiaSYS(72))
            location.addGrayStore(storeBuilder.buildNapoli(72))
            location.addGrayStore(storeBuilder.buildPalermo(120))
            location.addGrayStore(storeBuilder.buildRiposto(120))
            location.addGrayStore(storeBuilder.buildMalta(168))
            location.addGrayStore(storeBuilder.buildAthensSotiris(168))
            location.addGrayStore(storeBuilder.buildAthensH360(168))
            location.addGrayStore(storeBuilder.buildCorfu(192))
            location.addGrayStore(storeBuilder.buildKosA1(216))
            location.addGrayStore(storeBuilder.buildRhodes(216))
            location.addGrayStore(storeBuilder.buildVenice(72))
            location.addGrayStore(storeBuilder.buildZadar(96))
            location.addGrayStore(storeBuilder.buildSplit(96))
            location.addGrayStore(storeBuilder.buildDubrovnikKristijan(96))
            location.addGrayStore(storeBuilder.buildDubrovnikBWA(96))
            location.addGrayStore(storeBuilder.buildBCM(120))
            location.addGrayStore(storeBuilder.buildTivat(120))
            location.addNewItemsStore(storeBuilder.buildNewItemsStore(72))
        }

        regions.add(sanremo)

        return regions.toList()
    }

    override fun buildTuscanyItaly(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        // Loano
        val loano = Region("Loano",22)

        val albengaLoc = Location("Albenga")
        val loanoLoc = Location("Loano")
        val savonaLoc = Location("Savona")
        val varazzeLoc = Location("Varazze")

        albengaLoc.addNativeStore(storeBuilder.buildAntibes(3, 135))
        albengaLoc.addAlternativeStore(storeBuilder.buildGenova(6, 300))

        loanoLoc.addNativeStore(storeBuilder.buildAntibes(3, 150))
        loanoLoc.addAlternativeStore(storeBuilder.buildGenova(6, 300))

        savonaLoc.addNativeStore(storeBuilder.buildAntibes(3, 195))
        savonaLoc.addAlternativeStore(storeBuilder.buildGenova(6, 300))

        varazzeLoc.addNativeStore(storeBuilder.buildAntibes(3, 185))
        varazzeLoc.addAlternativeStore(storeBuilder.buildGenova(6, 300))

        loano.addLocation(albengaLoc)
        loano.addLocation(loanoLoc)
        loano.addLocation(savonaLoc)
        loano.addLocation(varazzeLoc)

        loano.locations.forEach { location ->
            location.addGrayStore(storeBuilder.buildM55(96))
            location.addGrayStore(storeBuilder.buildPalmaED(96))
            location.addGrayStore(storeBuilder.buildPalmaNG(96))
            location.addGrayStore(storeBuilder.buildIbiza(96))
            location.addGrayStore(storeBuilder.buildOlbiaNA(72))
            location.addGrayStore(storeBuilder.buildOlbiaSYS(72))
            location.addGrayStore(storeBuilder.buildNapoli(48))
            location.addGrayStore(storeBuilder.buildPalermo(96))
            location.addGrayStore(storeBuilder.buildRiposto(96))
            location.addGrayStore(storeBuilder.buildMalta(168))
            location.addGrayStore(storeBuilder.buildAthensSotiris(168))
            location.addGrayStore(storeBuilder.buildAthensH360(168))
            location.addGrayStore(storeBuilder.buildCorfu(192))
            location.addGrayStore(storeBuilder.buildKosA1(216))
            location.addGrayStore(storeBuilder.buildRhodes(216))
            location.addGrayStore(storeBuilder.buildVenice(72))
            location.addGrayStore(storeBuilder.buildZadar(96))
            location.addGrayStore(storeBuilder.buildSplit(96))
            location.addGrayStore(storeBuilder.buildDubrovnikKristijan(96))
            location.addGrayStore(storeBuilder.buildDubrovnikBWA(96))
            location.addGrayStore(storeBuilder.buildBCM(120))
            location.addGrayStore(storeBuilder.buildTivat(120))
            location.addNewItemsStore(storeBuilder.buildNewItemsStore(72))
        }

        regions.add(loano)


        // Genoa
        val genoa = Region("Genoa",23)

        val genoaAeroportoLoc = Location("Genoa Aeroporto ")
        val genoaMoloVecchioLoc = Location("Genoa molo vecchio")

        genoaAeroportoLoc.addNativeStore(storeBuilder.buildAntibes(4, 215))
        genoaAeroportoLoc.addAlternativeStore(storeBuilder.buildGenova(2, 300))

        genoaMoloVecchioLoc.addNativeStore(storeBuilder.buildAntibes(4, 220))
        genoaMoloVecchioLoc.addAlternativeStore(storeBuilder.buildGenova(2, 300))

        genoa.addLocation(genoaAeroportoLoc)
        genoa.addLocation(genoaMoloVecchioLoc)

        genoa.locations.forEach { location ->
            location.addGrayStore(storeBuilder.buildM55(96))
            location.addGrayStore(storeBuilder.buildPalmaED(96))
            location.addGrayStore(storeBuilder.buildPalmaNG(96))
            location.addGrayStore(storeBuilder.buildIbiza(96))
            location.addGrayStore(storeBuilder.buildOlbiaNA(72))
            location.addGrayStore(storeBuilder.buildOlbiaSYS(72))
            location.addGrayStore(storeBuilder.buildNapoli(48))
            location.addGrayStore(storeBuilder.buildPalermo(96))
            location.addGrayStore(storeBuilder.buildRiposto(96))
            location.addGrayStore(storeBuilder.buildMalta(168))
            location.addGrayStore(storeBuilder.buildAthensSotiris(168))
            location.addGrayStore(storeBuilder.buildAthensH360(168))
            location.addGrayStore(storeBuilder.buildCorfu(192))
            location.addGrayStore(storeBuilder.buildKosA1(216))
            location.addGrayStore(storeBuilder.buildRhodes(216))
            location.addGrayStore(storeBuilder.buildVenice(72))
            location.addGrayStore(storeBuilder.buildZadar(96))
            location.addGrayStore(storeBuilder.buildSplit(96))
            location.addGrayStore(storeBuilder.buildDubrovnikKristijan(96))
            location.addGrayStore(storeBuilder.buildDubrovnikBWA(96))
            location.addGrayStore(storeBuilder.buildBCM(120))
            location.addGrayStore(storeBuilder.buildTivat(120))
            location.addNewItemsStore(storeBuilder.buildNewItemsStore(72))
        }

        regions.add(genoa)


        // Portofino
        val portofino = Region("Portofino",24)

        val portofinoLoc = Location("Portofino")

        portofinoLoc.addNativeStore(storeBuilder.buildAntibes(4, 260))
        portofinoLoc.addAlternativeStore(storeBuilder.buildGenova(6, 300))

        portofinoLoc.addGrayStore(storeBuilder.buildM55(96))
        portofinoLoc.addGrayStore(storeBuilder.buildPalmaED(96))
        portofinoLoc.addGrayStore(storeBuilder.buildPalmaNG(96))
        portofinoLoc.addGrayStore(storeBuilder.buildIbiza(96))
        portofinoLoc.addGrayStore(storeBuilder.buildOlbiaNA(72))
        portofinoLoc.addGrayStore(storeBuilder.buildOlbiaSYS(72))
        portofinoLoc.addGrayStore(storeBuilder.buildNapoli(48))
        portofinoLoc.addGrayStore(storeBuilder.buildPalermo(96))
        portofinoLoc.addGrayStore(storeBuilder.buildRiposto(96))
        portofinoLoc.addGrayStore(storeBuilder.buildMalta(168))
        portofinoLoc.addGrayStore(storeBuilder.buildAthensSotiris(168))
        portofinoLoc.addGrayStore(storeBuilder.buildAthensH360(168))
        portofinoLoc.addGrayStore(storeBuilder.buildCorfu(192))
        portofinoLoc.addGrayStore(storeBuilder.buildKosA1(216))
        portofinoLoc.addGrayStore(storeBuilder.buildRhodes(216))
        portofinoLoc.addGrayStore(storeBuilder.buildVenice(72))
        portofinoLoc.addGrayStore(storeBuilder.buildZadar(96))
        portofinoLoc.addGrayStore(storeBuilder.buildSplit(96))
        portofinoLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(96))
        portofinoLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(96))
        portofinoLoc.addGrayStore(storeBuilder.buildBCM(120))
        portofinoLoc.addGrayStore(storeBuilder.buildTivat(120))
        portofinoLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(72))

        portofino.addLocation(portofinoLoc)

        regions.add(portofino)


        // La Spezia
        val laSpezia = Region("La Spezia",25)

        val laSpeziaLoc = Location("La Spezia")

        laSpeziaLoc.addNativeStore(storeBuilder.buildAntibes(6, 330))
        laSpeziaLoc.addAlternativeStore(storeBuilder.buildGenova(6, 300))

        laSpeziaLoc.addGrayStore(storeBuilder.buildM55(96))
        laSpeziaLoc.addGrayStore(storeBuilder.buildPalmaED(96))
        laSpeziaLoc.addGrayStore(storeBuilder.buildPalmaNG(96))
        laSpeziaLoc.addGrayStore(storeBuilder.buildIbiza(96))
        laSpeziaLoc.addGrayStore(storeBuilder.buildOlbiaNA(72))
        laSpeziaLoc.addGrayStore(storeBuilder.buildOlbiaSYS(72))
        laSpeziaLoc.addGrayStore(storeBuilder.buildNapoli(48))
        laSpeziaLoc.addGrayStore(storeBuilder.buildPalermo(96))
        laSpeziaLoc.addGrayStore(storeBuilder.buildRiposto(96))
        laSpeziaLoc.addGrayStore(storeBuilder.buildMalta(168))
        laSpeziaLoc.addGrayStore(storeBuilder.buildAthensSotiris(168))
        laSpeziaLoc.addGrayStore(storeBuilder.buildAthensH360(168))
        laSpeziaLoc.addGrayStore(storeBuilder.buildCorfu(192))
        laSpeziaLoc.addGrayStore(storeBuilder.buildKosA1(216))
        laSpeziaLoc.addGrayStore(storeBuilder.buildRhodes(216))
        laSpeziaLoc.addGrayStore(storeBuilder.buildVenice(72))
        laSpeziaLoc.addGrayStore(storeBuilder.buildZadar(96))
        laSpeziaLoc.addGrayStore(storeBuilder.buildSplit(96))
        laSpeziaLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(96))
        laSpeziaLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(96))
        laSpeziaLoc.addGrayStore(storeBuilder.buildBCM(120))
        laSpeziaLoc.addGrayStore(storeBuilder.buildTivat(120))
        laSpeziaLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(72))

        laSpezia.addLocation(laSpeziaLoc)
        regions.add(laSpezia)


        // Viareggio
        val viareggio = Region("Viareggio",26)

        val viareggioLoc = Location("Viareggio")

        viareggioLoc.addNativeStore(storeBuilder.buildAntibes(6, 365))
        viareggioLoc.addAlternativeStore(storeBuilder.buildGenova(6, 300))

        viareggioLoc.addGrayStore(storeBuilder.buildM55(96))
        viareggioLoc.addGrayStore(storeBuilder.buildPalmaED(96))
        viareggioLoc.addGrayStore(storeBuilder.buildPalmaNG(96))
        viareggioLoc.addGrayStore(storeBuilder.buildIbiza(96))
        viareggioLoc.addGrayStore(storeBuilder.buildOlbiaNA(72))
        viareggioLoc.addGrayStore(storeBuilder.buildOlbiaSYS(72))
        viareggioLoc.addGrayStore(storeBuilder.buildNapoli(48))
        viareggioLoc.addGrayStore(storeBuilder.buildPalermo(96))
        viareggioLoc.addGrayStore(storeBuilder.buildRiposto(96))
        viareggioLoc.addGrayStore(storeBuilder.buildMalta(168))
        viareggioLoc.addGrayStore(storeBuilder.buildAthensSotiris(168))
        viareggioLoc.addGrayStore(storeBuilder.buildAthensH360(168))
        viareggioLoc.addGrayStore(storeBuilder.buildCorfu(192))
        viareggioLoc.addGrayStore(storeBuilder.buildKosA1(216))
        viareggioLoc.addGrayStore(storeBuilder.buildRhodes(216))
        viareggioLoc.addGrayStore(storeBuilder.buildVenice(72))
        viareggioLoc.addGrayStore(storeBuilder.buildZadar(96))
        viareggioLoc.addGrayStore(storeBuilder.buildSplit(96))
        viareggioLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(96))
        viareggioLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(96))
        viareggioLoc.addGrayStore(storeBuilder.buildBCM(120))
        viareggioLoc.addGrayStore(storeBuilder.buildTivat(120))
        viareggioLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(72))

        viareggio.addLocation(viareggioLoc)
        regions.add(viareggio)


        // Livorno
        val livorno = Region("Livorno",27)

        val livornoLoc = Location("Livorno")

        livornoLoc.addNativeStore(storeBuilder.buildAntibes(6, 400))
        livornoLoc.addAlternativeStore(storeBuilder.buildGenova(7, 300))

        livornoLoc.addGrayStore(storeBuilder.buildM55(96))
        livornoLoc.addGrayStore(storeBuilder.buildPalmaED(96))
        livornoLoc.addGrayStore(storeBuilder.buildPalmaNG(96))
        livornoLoc.addGrayStore(storeBuilder.buildIbiza(96))
        livornoLoc.addGrayStore(storeBuilder.buildOlbiaNA(72))
        livornoLoc.addGrayStore(storeBuilder.buildOlbiaSYS(72))
        livornoLoc.addGrayStore(storeBuilder.buildNapoli(48))
        livornoLoc.addGrayStore(storeBuilder.buildPalermo(96))
        livornoLoc.addGrayStore(storeBuilder.buildRiposto(96))
        livornoLoc.addGrayStore(storeBuilder.buildMalta(168))
        livornoLoc.addGrayStore(storeBuilder.buildAthensSotiris(168))
        livornoLoc.addGrayStore(storeBuilder.buildAthensH360(168))
        livornoLoc.addGrayStore(storeBuilder.buildCorfu(192))
        livornoLoc.addGrayStore(storeBuilder.buildKosA1(216))
        livornoLoc.addGrayStore(storeBuilder.buildRhodes(216))
        livornoLoc.addGrayStore(storeBuilder.buildVenice(72))
        livornoLoc.addGrayStore(storeBuilder.buildZadar(96))
        livornoLoc.addGrayStore(storeBuilder.buildSplit(96))
        livornoLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(96))
        livornoLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(96))
        livornoLoc.addGrayStore(storeBuilder.buildBCM(120))
        livornoLoc.addGrayStore(storeBuilder.buildTivat(120))
        livornoLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(72))

        livorno.addLocation(livornoLoc)
        regions.add(livorno)


        // Piombino
        val piombino = Region("Piombino",28)

        val piombinoLoc = Location("Piombino")

        piombinoLoc.addNativeStore(storeBuilder.buildAntibes(7, 485))
        piombinoLoc.addAlternativeStore(storeBuilder.buildGenova(8, 300))

        piombinoLoc.addGrayStore(storeBuilder.buildM55(96))
        piombinoLoc.addGrayStore(storeBuilder.buildPalmaED(96))
        piombinoLoc.addGrayStore(storeBuilder.buildPalmaNG(96))
        piombinoLoc.addGrayStore(storeBuilder.buildIbiza(96))
        piombinoLoc.addGrayStore(storeBuilder.buildOlbiaNA(72))
        piombinoLoc.addGrayStore(storeBuilder.buildOlbiaSYS(72))
        piombinoLoc.addGrayStore(storeBuilder.buildNapoli(48))
        piombinoLoc.addGrayStore(storeBuilder.buildPalermo(96))
        piombinoLoc.addGrayStore(storeBuilder.buildRiposto(96))
        piombinoLoc.addGrayStore(storeBuilder.buildMalta(168))
        piombinoLoc.addGrayStore(storeBuilder.buildAthensSotiris(168))
        piombinoLoc.addGrayStore(storeBuilder.buildAthensH360(168))
        piombinoLoc.addGrayStore(storeBuilder.buildCorfu(192))
        piombinoLoc.addGrayStore(storeBuilder.buildKosA1(216))
        piombinoLoc.addGrayStore(storeBuilder.buildRhodes(216))
        piombinoLoc.addGrayStore(storeBuilder.buildVenice(72))
        piombinoLoc.addGrayStore(storeBuilder.buildZadar(96))
        piombinoLoc.addGrayStore(storeBuilder.buildSplit(96))
        piombinoLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(96))
        piombinoLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(96))
        piombinoLoc.addGrayStore(storeBuilder.buildBCM(120))
        piombinoLoc.addGrayStore(storeBuilder.buildTivat(120))
        piombinoLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(72))

        piombino.addLocation(piombinoLoc)
        regions.add(piombino)


        // Grosseto
        val grosseto = Region("Grosseto",29)

        val grossetoLoc = Location("Grosseto")

        grossetoLoc.addNativeStore(storeBuilder.buildAntibes(7, 550))
        grossetoLoc.addAlternativeStore(storeBuilder.buildGenova(8, 300))
        grossetoLoc.addAlternativeStore(storeBuilder.buildNapoli(8, 400))

        grossetoLoc.addGrayStore(storeBuilder.buildM55(96))
        grossetoLoc.addGrayStore(storeBuilder.buildPalmaED(96))
        grossetoLoc.addGrayStore(storeBuilder.buildPalmaNG(96))
        grossetoLoc.addGrayStore(storeBuilder.buildIbiza(96))
        grossetoLoc.addGrayStore(storeBuilder.buildOlbiaNA(72))
        grossetoLoc.addGrayStore(storeBuilder.buildOlbiaSYS(72))
        grossetoLoc.addGrayStore(storeBuilder.buildPalermo(96))
        grossetoLoc.addGrayStore(storeBuilder.buildRiposto(96))
        grossetoLoc.addGrayStore(storeBuilder.buildMalta(168))
        grossetoLoc.addGrayStore(storeBuilder.buildAthensSotiris(168))
        grossetoLoc.addGrayStore(storeBuilder.buildAthensH360(168))
        grossetoLoc.addGrayStore(storeBuilder.buildCorfu(192))
        grossetoLoc.addGrayStore(storeBuilder.buildKosA1(216))
        grossetoLoc.addGrayStore(storeBuilder.buildRhodes(216))
        grossetoLoc.addGrayStore(storeBuilder.buildVenice(72))
        grossetoLoc.addGrayStore(storeBuilder.buildZadar(96))
        grossetoLoc.addGrayStore(storeBuilder.buildSplit(96))
        grossetoLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(96))
        grossetoLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(96))
        grossetoLoc.addGrayStore(storeBuilder.buildBCM(120))
        grossetoLoc.addGrayStore(storeBuilder.buildTivat(120))
        grossetoLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(72))

        grosseto.addLocation(grossetoLoc)
        regions.add(grosseto)


        // Argentario
        val argentario = Region("Argentario",30)

        val argentarioLoc = Location("Argentario")

        argentarioLoc.addNativeStore(storeBuilder.buildAntibes(7, 580))
        argentarioLoc.addAlternativeStore(storeBuilder.buildGenova(9, 300))
        argentarioLoc.addAlternativeStore(storeBuilder.buildNapoli(7, 400))

        argentarioLoc.addGrayStore(storeBuilder.buildM55(96))
        argentarioLoc.addGrayStore(storeBuilder.buildPalmaED(96))
        argentarioLoc.addGrayStore(storeBuilder.buildPalmaNG(96))
        argentarioLoc.addGrayStore(storeBuilder.buildIbiza(96))
        argentarioLoc.addGrayStore(storeBuilder.buildOlbiaNA(72))
        argentarioLoc.addGrayStore(storeBuilder.buildOlbiaSYS(72))
        argentarioLoc.addGrayStore(storeBuilder.buildPalermo(72))
        argentarioLoc.addGrayStore(storeBuilder.buildRiposto(72))
        argentarioLoc.addGrayStore(storeBuilder.buildMalta(168))
        argentarioLoc.addGrayStore(storeBuilder.buildAthensSotiris(168))
        argentarioLoc.addGrayStore(storeBuilder.buildAthensH360(168))
        argentarioLoc.addGrayStore(storeBuilder.buildCorfu(192))
        argentarioLoc.addGrayStore(storeBuilder.buildKosA1(216))
        argentarioLoc.addGrayStore(storeBuilder.buildRhodes(216))
        argentarioLoc.addGrayStore(storeBuilder.buildVenice(72))
        argentarioLoc.addGrayStore(storeBuilder.buildZadar(96))
        argentarioLoc.addGrayStore(storeBuilder.buildSplit(96))
        argentarioLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(96))
        argentarioLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(96))
        argentarioLoc.addGrayStore(storeBuilder.buildBCM(120))
        argentarioLoc.addGrayStore(storeBuilder.buildTivat(120))
        argentarioLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(72))

        argentario.addLocation(argentarioLoc)
        regions.add(argentario)


        // Civitavecchia
        val civitavecchia = Region("Civitavecchia",31)

        val civitavecchiaLoc = Location("Civitavecchia")

        civitavecchiaLoc.addNativeStore(storeBuilder.buildAntibes(8, 640))
        civitavecchiaLoc.addAlternativeStore(storeBuilder.buildGenova(9, 300))
        civitavecchiaLoc.addAlternativeStore(storeBuilder.buildNapoli(6, 300))

        civitavecchiaLoc.addGrayStore(storeBuilder.buildM55(96))
        civitavecchiaLoc.addGrayStore(storeBuilder.buildPalmaED(96))
        civitavecchiaLoc.addGrayStore(storeBuilder.buildPalmaNG(96))
        civitavecchiaLoc.addGrayStore(storeBuilder.buildIbiza(96))
        civitavecchiaLoc.addGrayStore(storeBuilder.buildOlbiaNA(72))
        civitavecchiaLoc.addGrayStore(storeBuilder.buildOlbiaSYS(72))
        civitavecchiaLoc.addGrayStore(storeBuilder.buildPalermo(72))
        civitavecchiaLoc.addGrayStore(storeBuilder.buildRiposto(72))
        civitavecchiaLoc.addGrayStore(storeBuilder.buildMalta(168))
        civitavecchiaLoc.addGrayStore(storeBuilder.buildAthensSotiris(168))
        civitavecchiaLoc.addGrayStore(storeBuilder.buildAthensH360(168))
        civitavecchiaLoc.addGrayStore(storeBuilder.buildCorfu(192))
        civitavecchiaLoc.addGrayStore(storeBuilder.buildKosA1(216))
        civitavecchiaLoc.addGrayStore(storeBuilder.buildRhodes(216))
        civitavecchiaLoc.addGrayStore(storeBuilder.buildVenice(72))
        civitavecchiaLoc.addGrayStore(storeBuilder.buildZadar(96))
        civitavecchiaLoc.addGrayStore(storeBuilder.buildSplit(96))
        civitavecchiaLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(96))
        civitavecchiaLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(96))
        civitavecchiaLoc.addGrayStore(storeBuilder.buildBCM(120))
        civitavecchiaLoc.addGrayStore(storeBuilder.buildTivat(120))
        civitavecchiaLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(72))

        civitavecchia.addLocation(civitavecchiaLoc)
        regions.add(civitavecchia)

        return regions.toList()
    }

    override fun buildAmalfiItaly(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()
        /**
         * Gaeta
         */
        val gaeta = Region("Gaeta",32)

        val gaetaLoc = Location("Gaeta")

        gaetaLoc.addNativeStore(storeBuilder.buildNapoli(3, 1100))
        gaetaLoc.addAlternativeStore(storeBuilder.buildPalermo(18, 900))
        gaetaLoc.addAlternativeStore(storeBuilder.buildRiposto(24, 900))

        gaetaLoc.addGrayStore(storeBuilder.buildM55(96))
        gaetaLoc.addGrayStore(storeBuilder.buildAntibes(12))
        gaetaLoc.addGrayStore(storeBuilder.buildPalmaED(96))
        gaetaLoc.addGrayStore(storeBuilder.buildPalmaNG(96))
        gaetaLoc.addGrayStore(storeBuilder.buildIbiza(96))
        gaetaLoc.addGrayStore(storeBuilder.buildOlbiaNA(72))
        gaetaLoc.addGrayStore(storeBuilder.buildOlbiaSYS(72))
        gaetaLoc.addGrayStore(storeBuilder.buildGenova(48))
        gaetaLoc.addGrayStore(storeBuilder.buildMalta(168))
        gaetaLoc.addGrayStore(storeBuilder.buildAthensSotiris(168))
        gaetaLoc.addGrayStore(storeBuilder.buildAthensH360(168))
        gaetaLoc.addGrayStore(storeBuilder.buildCorfu(192))
        gaetaLoc.addGrayStore(storeBuilder.buildKosA1(216))
        gaetaLoc.addGrayStore(storeBuilder.buildRhodes(216))
        gaetaLoc.addGrayStore(storeBuilder.buildVenice(72))
        gaetaLoc.addGrayStore(storeBuilder.buildZadar(96))
        gaetaLoc.addGrayStore(storeBuilder.buildSplit(96))
        gaetaLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(96))
        gaetaLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(96))
        gaetaLoc.addGrayStore(storeBuilder.buildBCM(120))
        gaetaLoc.addGrayStore(storeBuilder.buildTivat(120))
        gaetaLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(96))

        gaeta.addLocation(gaetaLoc)
        regions.add(gaeta)


        // Naples
        val naples = Region("Naples",33)

        val naplesLoc = Location("Naples")

        naplesLoc.addNativeStore(storeBuilder.buildNapoli(1, 1100))
        naplesLoc.addAlternativeStore(storeBuilder.buildPalermo(18, 900))
        naplesLoc.addAlternativeStore(storeBuilder.buildRiposto(24, 900))

        naplesLoc.addGrayStore(storeBuilder.buildM55(96))
        naplesLoc.addGrayStore(storeBuilder.buildAntibes(12))
        naplesLoc.addGrayStore(storeBuilder.buildPalmaED(96))
        naplesLoc.addGrayStore(storeBuilder.buildPalmaNG(96))
        naplesLoc.addGrayStore(storeBuilder.buildIbiza(96))
        naplesLoc.addGrayStore(storeBuilder.buildOlbiaNA(72))
        naplesLoc.addGrayStore(storeBuilder.buildOlbiaSYS(72))
        naplesLoc.addGrayStore(storeBuilder.buildGenova(48))
        naplesLoc.addGrayStore(storeBuilder.buildMalta(168))
        naplesLoc.addGrayStore(storeBuilder.buildAthensSotiris(168))
        naplesLoc.addGrayStore(storeBuilder.buildAthensH360(168))
        naplesLoc.addGrayStore(storeBuilder.buildCorfu(192))
        naplesLoc.addGrayStore(storeBuilder.buildKosA1(216))
        naplesLoc.addGrayStore(storeBuilder.buildRhodes(216))
        naplesLoc.addGrayStore(storeBuilder.buildVenice(72))
        naplesLoc.addGrayStore(storeBuilder.buildZadar(96))
        naplesLoc.addGrayStore(storeBuilder.buildSplit(96))
        naplesLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(96))
        naplesLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(96))
        naplesLoc.addGrayStore(storeBuilder.buildBCM(120))
        naplesLoc.addGrayStore(storeBuilder.buildTivat(120))
        naplesLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(96))

        naples.addLocation(naplesLoc)
        regions.add(naples)


        // Marina di Stabia
        val marinadiStabia = Region("Marina di Stabia",34)

        val marinadiStabiaLoc = Location("Marina di Stabia")

        marinadiStabiaLoc.addNativeStore(storeBuilder.buildNapoli(2, 1100))
        marinadiStabiaLoc.addAlternativeStore(storeBuilder.buildPalermo(18, 900))
        marinadiStabiaLoc.addAlternativeStore(storeBuilder.buildRiposto(24, 900))

        marinadiStabiaLoc.addGrayStore(storeBuilder.buildM55(96))
        marinadiStabiaLoc.addGrayStore(storeBuilder.buildAntibes(12))
        marinadiStabiaLoc.addGrayStore(storeBuilder.buildPalmaED(96))
        marinadiStabiaLoc.addGrayStore(storeBuilder.buildPalmaNG(96))
        marinadiStabiaLoc.addGrayStore(storeBuilder.buildIbiza(96))
        marinadiStabiaLoc.addGrayStore(storeBuilder.buildOlbiaNA(72))
        marinadiStabiaLoc.addGrayStore(storeBuilder.buildOlbiaSYS(72))
        marinadiStabiaLoc.addGrayStore(storeBuilder.buildGenova(48))
        marinadiStabiaLoc.addGrayStore(storeBuilder.buildMalta(168))
        marinadiStabiaLoc.addGrayStore(storeBuilder.buildAthensSotiris(168))
        marinadiStabiaLoc.addGrayStore(storeBuilder.buildAthensH360(168))
        marinadiStabiaLoc.addGrayStore(storeBuilder.buildCorfu(192))
        marinadiStabiaLoc.addGrayStore(storeBuilder.buildKosA1(216))
        marinadiStabiaLoc.addGrayStore(storeBuilder.buildRhodes(216))
        marinadiStabiaLoc.addGrayStore(storeBuilder.buildVenice(72))
        marinadiStabiaLoc.addGrayStore(storeBuilder.buildZadar(96))
        marinadiStabiaLoc.addGrayStore(storeBuilder.buildSplit(96))
        marinadiStabiaLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(96))
        marinadiStabiaLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(96))
        marinadiStabiaLoc.addGrayStore(storeBuilder.buildBCM(120))
        marinadiStabiaLoc.addGrayStore(storeBuilder.buildTivat(120))
        marinadiStabiaLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(96))

        marinadiStabia.addLocation(marinadiStabiaLoc)

        regions.add(marinadiStabia)


        // Sorrento
        val sorrento = Region("Sorrento",35)

        val sorrentoLoc = Location("Sorrento")

        sorrentoLoc.addNativeStore(storeBuilder.buildNapoli(3, 1100))
        sorrentoLoc.addAlternativeStore(storeBuilder.buildPalermo(18, 900))
        sorrentoLoc.addAlternativeStore(storeBuilder.buildRiposto(24, 900))

        sorrentoLoc.addGrayStore(storeBuilder.buildM55(96))
        sorrentoLoc.addGrayStore(storeBuilder.buildAntibes(12))
        sorrentoLoc.addGrayStore(storeBuilder.buildPalmaED(96))
        sorrentoLoc.addGrayStore(storeBuilder.buildPalmaNG(96))
        sorrentoLoc.addGrayStore(storeBuilder.buildIbiza(96))
        sorrentoLoc.addGrayStore(storeBuilder.buildOlbiaNA(72))
        sorrentoLoc.addGrayStore(storeBuilder.buildOlbiaSYS(72))
        sorrentoLoc.addGrayStore(storeBuilder.buildGenova(48))
        sorrentoLoc.addGrayStore(storeBuilder.buildMalta(168))
        sorrentoLoc.addGrayStore(storeBuilder.buildAthensSotiris(168))
        sorrentoLoc.addGrayStore(storeBuilder.buildAthensH360(168))
        sorrentoLoc.addGrayStore(storeBuilder.buildCorfu(192))
        sorrentoLoc.addGrayStore(storeBuilder.buildKosA1(216))
        sorrentoLoc.addGrayStore(storeBuilder.buildRhodes(216))
        sorrentoLoc.addGrayStore(storeBuilder.buildVenice(72))
        sorrentoLoc.addGrayStore(storeBuilder.buildZadar(96))
        sorrentoLoc.addGrayStore(storeBuilder.buildSplit(96))
        sorrentoLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(96))
        sorrentoLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(96))
        sorrentoLoc.addGrayStore(storeBuilder.buildBCM(120))
        sorrentoLoc.addGrayStore(storeBuilder.buildTivat(120))
        sorrentoLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(96))

        sorrento.addLocation(sorrentoLoc)
        regions.add(sorrento)


        // Capri
        val capri = Region("Capri",36)

        val capriLoc = Location("Capri")

        capriLoc.addNativeStore(storeBuilder.buildNapoli(6, 1100))
        capriLoc.addAlternativeStore(storeBuilder.buildPalermo(18, 900))
        capriLoc.addAlternativeStore(storeBuilder.buildRiposto(24, 900))

        capriLoc.addGrayStore(storeBuilder.buildM55(96))
        capriLoc.addGrayStore(storeBuilder.buildAntibes(14))
        capriLoc.addGrayStore(storeBuilder.buildPalmaED(96))
        capriLoc.addGrayStore(storeBuilder.buildPalmaNG(96))
        capriLoc.addGrayStore(storeBuilder.buildIbiza(96))
        capriLoc.addGrayStore(storeBuilder.buildOlbiaNA(72))
        capriLoc.addGrayStore(storeBuilder.buildOlbiaSYS(72))
        capriLoc.addGrayStore(storeBuilder.buildGenova(48))
        capriLoc.addGrayStore(storeBuilder.buildMalta(168))
        capriLoc.addGrayStore(storeBuilder.buildAthensSotiris(168))
        capriLoc.addGrayStore(storeBuilder.buildAthensH360(168))
        capriLoc.addGrayStore(storeBuilder.buildCorfu(192))
        capriLoc.addGrayStore(storeBuilder.buildKosA1(216))
        capriLoc.addGrayStore(storeBuilder.buildRhodes(216))
        capriLoc.addGrayStore(storeBuilder.buildVenice(72))
        capriLoc.addGrayStore(storeBuilder.buildZadar(96))
        capriLoc.addGrayStore(storeBuilder.buildSplit(96))
        capriLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(96))
        capriLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(96))
        capriLoc.addGrayStore(storeBuilder.buildBCM(120))
        capriLoc.addGrayStore(storeBuilder.buildTivat(120))
        capriLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(96))

        capri.addLocation(capriLoc)
        regions.add(capri)


        // Positano
        val positano = Region("Positano",37)

        val positanoLoc = Location("Positano")

        positanoLoc.addNativeStore(storeBuilder.buildNapoli(3, 1100))
        positanoLoc.addAlternativeStore(storeBuilder.buildPalermo(18, 900))
        positanoLoc.addAlternativeStore(storeBuilder.buildRiposto(24, 900))

        positanoLoc.addGrayStore(storeBuilder.buildM55(96))
        positanoLoc.addGrayStore(storeBuilder.buildAntibes(12))
        positanoLoc.addGrayStore(storeBuilder.buildPalmaED(96))
        positanoLoc.addGrayStore(storeBuilder.buildPalmaNG(96))
        positanoLoc.addGrayStore(storeBuilder.buildIbiza(96))
        positanoLoc.addGrayStore(storeBuilder.buildOlbiaNA(72))
        positanoLoc.addGrayStore(storeBuilder.buildOlbiaSYS(72))
        positanoLoc.addGrayStore(storeBuilder.buildGenova(48))
        positanoLoc.addGrayStore(storeBuilder.buildMalta(168))
        positanoLoc.addGrayStore(storeBuilder.buildAthensSotiris(168))
        positanoLoc.addGrayStore(storeBuilder.buildAthensH360(168))
        positanoLoc.addGrayStore(storeBuilder.buildCorfu(192))
        positanoLoc.addGrayStore(storeBuilder.buildKosA1(216))
        positanoLoc.addGrayStore(storeBuilder.buildRhodes(216))
        positanoLoc.addGrayStore(storeBuilder.buildVenice(72))
        positanoLoc.addGrayStore(storeBuilder.buildZadar(96))
        positanoLoc.addGrayStore(storeBuilder.buildSplit(96))
        positanoLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(96))
        positanoLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(96))
        positanoLoc.addGrayStore(storeBuilder.buildBCM(120))
        positanoLoc.addGrayStore(storeBuilder.buildTivat(120))
        positanoLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(96))

        positano.addLocation(positanoLoc)
        regions.add(positano)


        // Amalfi
        val amalfi = Region("Amalfi",38)

        val amalfiLoc = Location("Amalfi")

        amalfiLoc.addNativeStore(storeBuilder.buildNapoli(3, 1100))
        amalfiLoc.addAlternativeStore(storeBuilder.buildPalermo(18, 900))
        amalfiLoc.addAlternativeStore(storeBuilder.buildRiposto(24, 900))

        amalfiLoc.addGrayStore(storeBuilder.buildM55(96))
        amalfiLoc.addGrayStore(storeBuilder.buildAntibes(14))
        amalfiLoc.addGrayStore(storeBuilder.buildPalmaED(96))
        amalfiLoc.addGrayStore(storeBuilder.buildPalmaNG(96))
        amalfiLoc.addGrayStore(storeBuilder.buildIbiza(96))
        amalfiLoc.addGrayStore(storeBuilder.buildOlbiaNA(72))
        amalfiLoc.addGrayStore(storeBuilder.buildOlbiaSYS(72))
        amalfiLoc.addGrayStore(storeBuilder.buildGenova(48))
        amalfiLoc.addGrayStore(storeBuilder.buildMalta(168))
        amalfiLoc.addGrayStore(storeBuilder.buildAthensSotiris(168))
        amalfiLoc.addGrayStore(storeBuilder.buildAthensH360(168))
        amalfiLoc.addGrayStore(storeBuilder.buildCorfu(192))
        amalfiLoc.addGrayStore(storeBuilder.buildKosA1(216))
        amalfiLoc.addGrayStore(storeBuilder.buildRhodes(216))
        amalfiLoc.addGrayStore(storeBuilder.buildVenice(72))
        amalfiLoc.addGrayStore(storeBuilder.buildZadar(96))
        amalfiLoc.addGrayStore(storeBuilder.buildSplit(96))
        amalfiLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(96))
        amalfiLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(96))
        amalfiLoc.addGrayStore(storeBuilder.buildBCM(120))
        amalfiLoc.addGrayStore(storeBuilder.buildTivat(120))
        amalfiLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(96))

        amalfi.addLocation(amalfiLoc)
        regions.add(amalfi)


        // Salerno
        val salerno = Region("Salerno",39)

        val salernoLoc = Location("Salerno")

        salernoLoc.addNativeStore(storeBuilder.buildNapoli(3, 1100))
        salernoLoc.addAlternativeStore(storeBuilder.buildPalermo(18, 900))
        salernoLoc.addAlternativeStore(storeBuilder.buildRiposto(24, 900))

        salernoLoc.addGrayStore(storeBuilder.buildM55(96))
        salernoLoc.addGrayStore(storeBuilder.buildAntibes(14))
        salernoLoc.addGrayStore(storeBuilder.buildPalmaED(96))
        salernoLoc.addGrayStore(storeBuilder.buildPalmaNG(96))
        salernoLoc.addGrayStore(storeBuilder.buildIbiza(96))
        salernoLoc.addGrayStore(storeBuilder.buildOlbiaNA(72))
        salernoLoc.addGrayStore(storeBuilder.buildOlbiaSYS(72))
        salernoLoc.addGrayStore(storeBuilder.buildGenova(48))
        salernoLoc.addGrayStore(storeBuilder.buildMalta(168))
        salernoLoc.addGrayStore(storeBuilder.buildAthensSotiris(168))
        salernoLoc.addGrayStore(storeBuilder.buildAthensH360(168))
        salernoLoc.addGrayStore(storeBuilder.buildCorfu(192))
        salernoLoc.addGrayStore(storeBuilder.buildKosA1(216))
        salernoLoc.addGrayStore(storeBuilder.buildRhodes(216))
        salernoLoc.addGrayStore(storeBuilder.buildVenice(72))
        salernoLoc.addGrayStore(storeBuilder.buildZadar(96))
        salernoLoc.addGrayStore(storeBuilder.buildSplit(96))
        salernoLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(96))
        salernoLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(96))
        salernoLoc.addGrayStore(storeBuilder.buildBCM(120))
        salernoLoc.addGrayStore(storeBuilder.buildTivat(120))
        salernoLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(96))

        salerno.addLocation(salernoLoc)
        regions.add(salerno)

        return regions.toList()
    }

    override fun buildSouthOfItaly(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        // Tropea
        val tropea = Region("Tropea",40)

        val tropeaLoc = Location("Tropea")

        tropeaLoc.addNativeStore(storeBuilder.buildNapoli(8, 600))
        tropeaLoc.addAlternativeStore(storeBuilder.buildPalermo(12, 500))
        tropeaLoc.addAlternativeStore(storeBuilder.buildRiposto(12, 500))

        tropeaLoc.addGrayStore(storeBuilder.buildM55(120))
        tropeaLoc.addGrayStore(storeBuilder.buildAntibes(22))
        tropeaLoc.addGrayStore(storeBuilder.buildPalmaED(120))
        tropeaLoc.addGrayStore(storeBuilder.buildPalmaNG(120))
        tropeaLoc.addGrayStore(storeBuilder.buildIbiza(120))
        tropeaLoc.addGrayStore(storeBuilder.buildOlbiaNA(72))
        tropeaLoc.addGrayStore(storeBuilder.buildOlbiaSYS(72))
        tropeaLoc.addGrayStore(storeBuilder.buildGenova(72))
        tropeaLoc.addGrayStore(storeBuilder.buildMalta(168))
        tropeaLoc.addGrayStore(storeBuilder.buildAthensSotiris(192))
        tropeaLoc.addGrayStore(storeBuilder.buildAthensH360(192))
        tropeaLoc.addGrayStore(storeBuilder.buildCorfu(192))
        tropeaLoc.addGrayStore(storeBuilder.buildKosA1(216))
        tropeaLoc.addGrayStore(storeBuilder.buildRhodes(216))
        tropeaLoc.addGrayStore(storeBuilder.buildVenice(48))
        tropeaLoc.addGrayStore(storeBuilder.buildZadar(144))
        tropeaLoc.addGrayStore(storeBuilder.buildSplit(144))
        tropeaLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(144))
        tropeaLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(144))
        tropeaLoc.addGrayStore(storeBuilder.buildBCM(216))
        tropeaLoc.addGrayStore(storeBuilder.buildTivat(216))
        tropeaLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(120))

        tropea.addLocation(tropeaLoc)
        regions.add(tropea)


        // Reggio di Calabria
        val reggioDiCalabria = Region("Reggio di Calabria",41)

        val reggioDiCalabriaLoc = Location("Reggio di Calabria")

        reggioDiCalabriaLoc.addNativeStore(storeBuilder.buildNapoli(8, 600))
        reggioDiCalabriaLoc.addAlternativeStore(storeBuilder.buildPalermo(12, 500))
        reggioDiCalabriaLoc.addAlternativeStore(storeBuilder.buildRiposto(12, 500))

        reggioDiCalabriaLoc.addGrayStore(storeBuilder.buildM55(120))
        reggioDiCalabriaLoc.addGrayStore(storeBuilder.buildAntibes(24))
        reggioDiCalabriaLoc.addGrayStore(storeBuilder.buildPalmaED(120))
        reggioDiCalabriaLoc.addGrayStore(storeBuilder.buildPalmaNG(120))
        reggioDiCalabriaLoc.addGrayStore(storeBuilder.buildIbiza(120))
        reggioDiCalabriaLoc.addGrayStore(storeBuilder.buildOlbiaNA(72))
        reggioDiCalabriaLoc.addGrayStore(storeBuilder.buildOlbiaSYS(72))
        reggioDiCalabriaLoc.addGrayStore(storeBuilder.buildGenova(72))
        reggioDiCalabriaLoc.addGrayStore(storeBuilder.buildMalta(168))
        reggioDiCalabriaLoc.addGrayStore(storeBuilder.buildAthensSotiris(192))
        reggioDiCalabriaLoc.addGrayStore(storeBuilder.buildAthensH360(192))
        reggioDiCalabriaLoc.addGrayStore(storeBuilder.buildCorfu(192))
        reggioDiCalabriaLoc.addGrayStore(storeBuilder.buildKosA1(216))
        reggioDiCalabriaLoc.addGrayStore(storeBuilder.buildRhodes(216))
        reggioDiCalabriaLoc.addGrayStore(storeBuilder.buildVenice(48))
        reggioDiCalabriaLoc.addGrayStore(storeBuilder.buildZadar(144))
        reggioDiCalabriaLoc.addGrayStore(storeBuilder.buildSplit(144))
        reggioDiCalabriaLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(144))
        reggioDiCalabriaLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(144))
        reggioDiCalabriaLoc.addGrayStore(storeBuilder.buildBCM(216))
        reggioDiCalabriaLoc.addGrayStore(storeBuilder.buildTivat(216))
        reggioDiCalabriaLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(120))

        reggioDiCalabria.addLocation(reggioDiCalabriaLoc)
        regions.add(reggioDiCalabria)


        // Brindisi
        val brindisi = Region("Brindisi",42)

        val brindisiLoc = Location("Brindisi")

        brindisiLoc.addNativeStore(storeBuilder.buildNapoli(8, 1000))
        brindisiLoc.addAlternativeStore(storeBuilder.buildCorfu(24, 900))

        brindisiLoc.addGrayStore(storeBuilder.buildM55(120))
        brindisiLoc.addGrayStore(storeBuilder.buildAntibes(18))
        brindisiLoc.addGrayStore(storeBuilder.buildPalmaED(120))
        brindisiLoc.addGrayStore(storeBuilder.buildPalmaNG(120))
        brindisiLoc.addGrayStore(storeBuilder.buildIbiza(120))
        brindisiLoc.addGrayStore(storeBuilder.buildOlbiaNA(72))
        brindisiLoc.addGrayStore(storeBuilder.buildOlbiaSYS(72))
        brindisiLoc.addGrayStore(storeBuilder.buildGenova(72))
        brindisiLoc.addGrayStore(storeBuilder.buildPalermo(96))
        brindisiLoc.addGrayStore(storeBuilder.buildRiposto(96))
        brindisiLoc.addGrayStore(storeBuilder.buildMalta(168))
        brindisiLoc.addGrayStore(storeBuilder.buildAthensSotiris(192))
        brindisiLoc.addGrayStore(storeBuilder.buildAthensH360(192))
        brindisiLoc.addGrayStore(storeBuilder.buildKosA1(216))
        brindisiLoc.addGrayStore(storeBuilder.buildRhodes(216))
        brindisiLoc.addGrayStore(storeBuilder.buildVenice(48))
        brindisiLoc.addGrayStore(storeBuilder.buildZadar(144))
        brindisiLoc.addGrayStore(storeBuilder.buildSplit(144))
        brindisiLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(144))
        brindisiLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(144))
        brindisiLoc.addGrayStore(storeBuilder.buildBCM(216))
        brindisiLoc.addGrayStore(storeBuilder.buildTivat(216))
        brindisiLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(120))

        brindisi.addLocation(brindisiLoc)
        regions.add(brindisi)


        // Otranto
        val otranto = Region("Otranto",43)

        val otrantoLoc = Location("Otranto")

        otrantoLoc.addNativeStore(storeBuilder.buildNapoli(9, 1000))
        otrantoLoc.addAlternativeStore(storeBuilder.buildCorfu(24, 900))

        otrantoLoc.addGrayStore(storeBuilder.buildM55(120))
        otrantoLoc.addGrayStore(storeBuilder.buildAntibes(18))
        otrantoLoc.addGrayStore(storeBuilder.buildPalmaED(120))
        otrantoLoc.addGrayStore(storeBuilder.buildPalmaNG(120))
        otrantoLoc.addGrayStore(storeBuilder.buildIbiza(120))
        otrantoLoc.addGrayStore(storeBuilder.buildOlbiaNA(72))
        otrantoLoc.addGrayStore(storeBuilder.buildOlbiaSYS(72))
        otrantoLoc.addGrayStore(storeBuilder.buildGenova(72))
        otrantoLoc.addGrayStore(storeBuilder.buildPalermo(96))
        otrantoLoc.addGrayStore(storeBuilder.buildRiposto(96))
        otrantoLoc.addGrayStore(storeBuilder.buildMalta(168))
        otrantoLoc.addGrayStore(storeBuilder.buildAthensSotiris(192))
        otrantoLoc.addGrayStore(storeBuilder.buildAthensH360(192))
        otrantoLoc.addGrayStore(storeBuilder.buildKosA1(216))
        otrantoLoc.addGrayStore(storeBuilder.buildRhodes(216))
        otrantoLoc.addGrayStore(storeBuilder.buildVenice(48))
        otrantoLoc.addGrayStore(storeBuilder.buildZadar(144))
        otrantoLoc.addGrayStore(storeBuilder.buildSplit(144))
        otrantoLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(144))
        otrantoLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(144))
        otrantoLoc.addGrayStore(storeBuilder.buildBCM(216))
        otrantoLoc.addGrayStore(storeBuilder.buildTivat(216))
        otrantoLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(120))

        otranto.addLocation(otrantoLoc)
        regions.add(otranto)


        // Bari
        val bari = Region("Bari",44)

        val bariLoc = Location("Bari")

        bariLoc.addNativeStore(storeBuilder.buildNapoli(6, 1000))
        bariLoc.addAlternativeStore(storeBuilder.buildCorfu(24, 900))

        bariLoc.addGrayStore(storeBuilder.buildM55(120))
        bariLoc.addGrayStore(storeBuilder.buildAntibes(18))
        bariLoc.addGrayStore(storeBuilder.buildPalmaED(120))
        bariLoc.addGrayStore(storeBuilder.buildPalmaNG(120))
        bariLoc.addGrayStore(storeBuilder.buildIbiza(120))
        bariLoc.addGrayStore(storeBuilder.buildOlbiaNA(72))
        bariLoc.addGrayStore(storeBuilder.buildOlbiaSYS(72))
        bariLoc.addGrayStore(storeBuilder.buildGenova(72))
        bariLoc.addGrayStore(storeBuilder.buildPalermo(96))
        bariLoc.addGrayStore(storeBuilder.buildRiposto(96))
        bariLoc.addGrayStore(storeBuilder.buildMalta(168))
        bariLoc.addGrayStore(storeBuilder.buildAthensSotiris(192))
        bariLoc.addGrayStore(storeBuilder.buildAthensH360(192))
        bariLoc.addGrayStore(storeBuilder.buildKosA1(216))
        bariLoc.addGrayStore(storeBuilder.buildRhodes(216))
        bariLoc.addGrayStore(storeBuilder.buildVenice(48))
        bariLoc.addGrayStore(storeBuilder.buildZadar(144))
        bariLoc.addGrayStore(storeBuilder.buildSplit(144))
        bariLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(144))
        bariLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(144))
        bariLoc.addGrayStore(storeBuilder.buildBCM(216))
        bariLoc.addGrayStore(storeBuilder.buildTivat(216))
        bariLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(120))

        bari.addLocation(bariLoc)
        regions.add(bari)

        return regions.toList()
    }

    override fun buildSicily(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        // Palermo
        val palermo = Region("Palermo",45)

        val palermoLoc = Location("Palermo")

        palermoLoc.addNativeStore(storeBuilder.buildRiposto(5, 150))
        palermoLoc.addNativeStore(storeBuilder.buildPalermo(1, 150))
        palermoLoc.addAlternativeStore(storeBuilder.buildNapoli(18, 1200))

        palermoLoc.addGrayStore(storeBuilder.buildM55(168))
        palermoLoc.addGrayStore(storeBuilder.buildAntibes(168))
        palermoLoc.addGrayStore(storeBuilder.buildPalmaED(120))
        palermoLoc.addGrayStore(storeBuilder.buildPalmaNG(120))
        palermoLoc.addGrayStore(storeBuilder.buildIbiza(120))
        palermoLoc.addGrayStore(storeBuilder.buildOlbiaNA(96))
        palermoLoc.addGrayStore(storeBuilder.buildOlbiaSYS(96))
        palermoLoc.addGrayStore(storeBuilder.buildGenova(96))
        palermoLoc.addGrayStore(storeBuilder.buildMalta(72))
        palermoLoc.addGrayStore(storeBuilder.buildAthensSotiris(192))
        palermoLoc.addGrayStore(storeBuilder.buildAthensH360(192))
        palermoLoc.addGrayStore(storeBuilder.buildCorfu(192))
        palermoLoc.addGrayStore(storeBuilder.buildKosA1(216))
        palermoLoc.addGrayStore(storeBuilder.buildRhodes(216))
        palermoLoc.addGrayStore(storeBuilder.buildVenice(120))
        palermoLoc.addGrayStore(storeBuilder.buildZadar(192))
        palermoLoc.addGrayStore(storeBuilder.buildSplit(192))
        palermoLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(192))
        palermoLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(192))
        palermoLoc.addGrayStore(storeBuilder.buildBCM(216))
        palermoLoc.addGrayStore(storeBuilder.buildTivat(216))
        palermoLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(144))

        palermo.addLocation(palermoLoc)
        regions.add(palermo)


        // Millazzo
        val millazzo = Region("Millazzo",46)

        val millazzoLoc = Location("Millazzo")

        millazzoLoc.addNativeStore(storeBuilder.buildRiposto(3, 150))
        millazzoLoc.addNativeStore(storeBuilder.buildPalermo(4, 150))
        millazzoLoc.addAlternativeStore(storeBuilder.buildNapoli(18, 1200))

        millazzoLoc.addGrayStore(storeBuilder.buildM55(168))
        millazzoLoc.addGrayStore(storeBuilder.buildAntibes(168))
        millazzoLoc.addGrayStore(storeBuilder.buildPalmaED(120))
        millazzoLoc.addGrayStore(storeBuilder.buildPalmaNG(120))
        millazzoLoc.addGrayStore(storeBuilder.buildIbiza(120))
        millazzoLoc.addGrayStore(storeBuilder.buildOlbiaNA(96))
        millazzoLoc.addGrayStore(storeBuilder.buildOlbiaSYS(96))
        millazzoLoc.addGrayStore(storeBuilder.buildGenova(96))
        millazzoLoc.addGrayStore(storeBuilder.buildMalta(72))
        millazzoLoc.addGrayStore(storeBuilder.buildAthensSotiris(192))
        millazzoLoc.addGrayStore(storeBuilder.buildAthensH360(192))
        millazzoLoc.addGrayStore(storeBuilder.buildCorfu(192))
        millazzoLoc.addGrayStore(storeBuilder.buildKosA1(216))
        millazzoLoc.addGrayStore(storeBuilder.buildRhodes(216))
        millazzoLoc.addGrayStore(storeBuilder.buildVenice(120))
        millazzoLoc.addGrayStore(storeBuilder.buildZadar(192))
        millazzoLoc.addGrayStore(storeBuilder.buildSplit(192))
        millazzoLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(192))
        millazzoLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(192))
        millazzoLoc.addGrayStore(storeBuilder.buildBCM(216))
        millazzoLoc.addGrayStore(storeBuilder.buildTivat(216))
        millazzoLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(144))

        millazzo.addLocation(millazzoLoc)
        regions.add(millazzo)


        // Messina
        val messina = Region("Messina",47)

        val messinaLoc = Location("Messina")

        messinaLoc.addNativeStore(storeBuilder.buildRiposto(2, 150))
        messinaLoc.addNativeStore(storeBuilder.buildPalermo(4, 150))
        messinaLoc.addAlternativeStore(storeBuilder.buildNapoli(18, 1200))

        messinaLoc.addGrayStore(storeBuilder.buildM55(168))
        messinaLoc.addGrayStore(storeBuilder.buildAntibes(168))
        messinaLoc.addGrayStore(storeBuilder.buildPalmaED(120))
        messinaLoc.addGrayStore(storeBuilder.buildPalmaNG(120))
        messinaLoc.addGrayStore(storeBuilder.buildIbiza(120))
        messinaLoc.addGrayStore(storeBuilder.buildOlbiaNA(96))
        messinaLoc.addGrayStore(storeBuilder.buildOlbiaSYS(96))
        messinaLoc.addGrayStore(storeBuilder.buildGenova(96))
        messinaLoc.addGrayStore(storeBuilder.buildMalta(72))
        messinaLoc.addGrayStore(storeBuilder.buildAthensSotiris(192))
        messinaLoc.addGrayStore(storeBuilder.buildAthensH360(192))
        messinaLoc.addGrayStore(storeBuilder.buildCorfu(192))
        messinaLoc.addGrayStore(storeBuilder.buildKosA1(216))
        messinaLoc.addGrayStore(storeBuilder.buildRhodes(216))
        messinaLoc.addGrayStore(storeBuilder.buildVenice(120))
        messinaLoc.addGrayStore(storeBuilder.buildZadar(192))
        messinaLoc.addGrayStore(storeBuilder.buildSplit(192))
        messinaLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(192))
        messinaLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(192))
        messinaLoc.addGrayStore(storeBuilder.buildBCM(216))
        messinaLoc.addGrayStore(storeBuilder.buildTivat(216))
        messinaLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(144))

        messina.addLocation(messinaLoc)
        regions.add(messina)


        // Taormina
        val taormina = Region("Taormina",48)

        val taorminaLoc = Location("Taormina")

        taorminaLoc.addNativeStore(storeBuilder.buildRiposto(2, 150))
        taorminaLoc.addNativeStore(storeBuilder.buildPalermo(5, 150))
        taorminaLoc.addAlternativeStore(storeBuilder.buildNapoli(18, 1200))

        taorminaLoc.addGrayStore(storeBuilder.buildM55(168))
        taorminaLoc.addGrayStore(storeBuilder.buildAntibes(168))
        taorminaLoc.addGrayStore(storeBuilder.buildPalmaED(120))
        taorminaLoc.addGrayStore(storeBuilder.buildPalmaNG(120))
        taorminaLoc.addGrayStore(storeBuilder.buildIbiza(120))
        taorminaLoc.addGrayStore(storeBuilder.buildOlbiaNA(96))
        taorminaLoc.addGrayStore(storeBuilder.buildOlbiaSYS(96))
        taorminaLoc.addGrayStore(storeBuilder.buildGenova(96))
        taorminaLoc.addGrayStore(storeBuilder.buildMalta(72))
        taorminaLoc.addGrayStore(storeBuilder.buildAthensSotiris(192))
        taorminaLoc.addGrayStore(storeBuilder.buildAthensH360(192))
        taorminaLoc.addGrayStore(storeBuilder.buildCorfu(192))
        taorminaLoc.addGrayStore(storeBuilder.buildKosA1(216))
        taorminaLoc.addGrayStore(storeBuilder.buildRhodes(216))
        taorminaLoc.addGrayStore(storeBuilder.buildVenice(120))
        taorminaLoc.addGrayStore(storeBuilder.buildZadar(192))
        taorminaLoc.addGrayStore(storeBuilder.buildSplit(192))
        taorminaLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(192))
        taorminaLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(192))
        taorminaLoc.addGrayStore(storeBuilder.buildBCM(216))
        taorminaLoc.addGrayStore(storeBuilder.buildTivat(216))
        taorminaLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(144))

        taormina.addLocation(taorminaLoc)
        regions.add(taormina)


        // Riposto
        val riposto = Region("Riposto",49)

        val ripostoLoc = Location("Riposto")

        ripostoLoc.addNativeStore(storeBuilder.buildRiposto(1, 150))
        ripostoLoc.addNativeStore(storeBuilder.buildPalermo(5, 150))
        ripostoLoc.addAlternativeStore(storeBuilder.buildNapoli(18, 1200))

        ripostoLoc.addGrayStore(storeBuilder.buildM55(168))
        ripostoLoc.addGrayStore(storeBuilder.buildAntibes(168))
        ripostoLoc.addGrayStore(storeBuilder.buildPalmaED(120))
        ripostoLoc.addGrayStore(storeBuilder.buildPalmaNG(120))
        ripostoLoc.addGrayStore(storeBuilder.buildIbiza(120))
        ripostoLoc.addGrayStore(storeBuilder.buildOlbiaNA(96))
        ripostoLoc.addGrayStore(storeBuilder.buildOlbiaSYS(96))
        ripostoLoc.addGrayStore(storeBuilder.buildGenova(96))
        ripostoLoc.addGrayStore(storeBuilder.buildMalta(72))
        ripostoLoc.addGrayStore(storeBuilder.buildAthensSotiris(192))
        ripostoLoc.addGrayStore(storeBuilder.buildAthensH360(192))
        ripostoLoc.addGrayStore(storeBuilder.buildCorfu(192))
        ripostoLoc.addGrayStore(storeBuilder.buildKosA1(216))
        ripostoLoc.addGrayStore(storeBuilder.buildRhodes(216))
        ripostoLoc.addGrayStore(storeBuilder.buildVenice(120))
        ripostoLoc.addGrayStore(storeBuilder.buildZadar(192))
        ripostoLoc.addGrayStore(storeBuilder.buildSplit(192))
        ripostoLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(192))
        ripostoLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(192))
        ripostoLoc.addGrayStore(storeBuilder.buildBCM(216))
        ripostoLoc.addGrayStore(storeBuilder.buildTivat(216))
        ripostoLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(144))

        riposto.addLocation(ripostoLoc)
        regions.add(riposto)


        // Catania
        val catania = Region("Catania",50)

        val cataniaLoc = Location("Catania")

        cataniaLoc.addNativeStore(storeBuilder.buildRiposto(2, 150))
        cataniaLoc.addNativeStore(storeBuilder.buildPalermo(4, 150))
        cataniaLoc.addAlternativeStore(storeBuilder.buildNapoli(18, 1200))

        cataniaLoc.addGrayStore(storeBuilder.buildM55(168))
        cataniaLoc.addGrayStore(storeBuilder.buildAntibes(168))
        cataniaLoc.addGrayStore(storeBuilder.buildPalmaED(120))
        cataniaLoc.addGrayStore(storeBuilder.buildPalmaNG(120))
        cataniaLoc.addGrayStore(storeBuilder.buildIbiza(120))
        cataniaLoc.addGrayStore(storeBuilder.buildOlbiaNA(96))
        cataniaLoc.addGrayStore(storeBuilder.buildOlbiaSYS(96))
        cataniaLoc.addGrayStore(storeBuilder.buildGenova(96))
        cataniaLoc.addGrayStore(storeBuilder.buildMalta(72))
        cataniaLoc.addGrayStore(storeBuilder.buildAthensSotiris(192))
        cataniaLoc.addGrayStore(storeBuilder.buildAthensH360(192))
        cataniaLoc.addGrayStore(storeBuilder.buildCorfu(192))
        cataniaLoc.addGrayStore(storeBuilder.buildKosA1(216))
        cataniaLoc.addGrayStore(storeBuilder.buildRhodes(216))
        cataniaLoc.addGrayStore(storeBuilder.buildVenice(120))
        cataniaLoc.addGrayStore(storeBuilder.buildZadar(192))
        cataniaLoc.addGrayStore(storeBuilder.buildSplit(192))
        cataniaLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(192))
        cataniaLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(192))
        cataniaLoc.addGrayStore(storeBuilder.buildBCM(216))
        cataniaLoc.addGrayStore(storeBuilder.buildTivat(216))
        cataniaLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(144))

        catania.addLocation(cataniaLoc)
        regions.add(catania)


        // Syracuse
        val syracuse = Region("Syracuse",51)

        val syracuseLoc = Location("Syracuse")

        syracuseLoc.addNativeStore(storeBuilder.buildRiposto(2, 150))
        syracuseLoc.addNativeStore(storeBuilder.buildPalermo(5, 150))
        syracuseLoc.addAlternativeStore(storeBuilder.buildNapoli(18, 1200))

        syracuseLoc.addGrayStore(storeBuilder.buildM55(168))
        syracuseLoc.addGrayStore(storeBuilder.buildAntibes(168))
        syracuseLoc.addGrayStore(storeBuilder.buildPalmaED(120))
        syracuseLoc.addGrayStore(storeBuilder.buildPalmaNG(120))
        syracuseLoc.addGrayStore(storeBuilder.buildIbiza(120))
        syracuseLoc.addGrayStore(storeBuilder.buildOlbiaNA(96))
        syracuseLoc.addGrayStore(storeBuilder.buildOlbiaSYS(96))
        syracuseLoc.addGrayStore(storeBuilder.buildGenova(96))
        syracuseLoc.addGrayStore(storeBuilder.buildMalta(72))
        syracuseLoc.addGrayStore(storeBuilder.buildAthensSotiris(192))
        syracuseLoc.addGrayStore(storeBuilder.buildAthensH360(192))
        syracuseLoc.addGrayStore(storeBuilder.buildCorfu(192))
        syracuseLoc.addGrayStore(storeBuilder.buildKosA1(216))
        syracuseLoc.addGrayStore(storeBuilder.buildRhodes(216))
        syracuseLoc.addGrayStore(storeBuilder.buildVenice(120))
        syracuseLoc.addGrayStore(storeBuilder.buildZadar(192))
        syracuseLoc.addGrayStore(storeBuilder.buildSplit(192))
        syracuseLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(192))
        syracuseLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(192))
        syracuseLoc.addGrayStore(storeBuilder.buildBCM(216))
        syracuseLoc.addGrayStore(storeBuilder.buildTivat(216))
        syracuseLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(144))

        syracuse.addLocation(syracuseLoc)
        regions.add(syracuse)

        return regions.toList()
    }

    override fun buildMalta(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        // Malta
        val maltaIsland = Region("Malta Island",52)

        val maltaIslandLoc = Location("Malta Island")

        maltaIslandLoc.addNativeStore(storeBuilder.buildMalta(2, 50))
        maltaIslandLoc.addAlternativeStore(storeBuilder.buildRiposto(48, 500))
        maltaIslandLoc.addAlternativeStore(storeBuilder.buildPalermo(48, 500))

        maltaIslandLoc.addGrayStore(storeBuilder.buildM55(168))
        maltaIslandLoc.addGrayStore(storeBuilder.buildAntibes(168))
        maltaIslandLoc.addGrayStore(storeBuilder.buildPalmaED(168))
        maltaIslandLoc.addGrayStore(storeBuilder.buildPalmaNG(168))
        maltaIslandLoc.addGrayStore(storeBuilder.buildIbiza(168))
        maltaIslandLoc.addGrayStore(storeBuilder.buildOlbiaNA(168))
        maltaIslandLoc.addGrayStore(storeBuilder.buildOlbiaSYS(168))
        maltaIslandLoc.addGrayStore(storeBuilder.buildGenova(168))
        maltaIslandLoc.addGrayStore(storeBuilder.buildNapoli(168))
        maltaIslandLoc.addGrayStore(storeBuilder.buildAthensSotiris(192))
        maltaIslandLoc.addGrayStore(storeBuilder.buildAthensH360(192))
        maltaIslandLoc.addGrayStore(storeBuilder.buildCorfu(192))
        maltaIslandLoc.addGrayStore(storeBuilder.buildKosA1(216))
        maltaIslandLoc.addGrayStore(storeBuilder.buildRhodes(216))
        maltaIslandLoc.addGrayStore(storeBuilder.buildVenice(192))
        maltaIslandLoc.addGrayStore(storeBuilder.buildZadar(240))
        maltaIslandLoc.addGrayStore(storeBuilder.buildSplit(240))
        maltaIslandLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(240))
        maltaIslandLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(240))
        maltaIslandLoc.addGrayStore(storeBuilder.buildBCM(216))
        maltaIslandLoc.addGrayStore(storeBuilder.buildTivat(216))
        maltaIslandLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(168))

        maltaIsland.addLocation(maltaIslandLoc)
        regions.add(maltaIsland)


        // Gozo Island
        val gozoIsland = Region("Gozo Island",53)

        val gozoIslandLoc = Location("Gozo Island")

        gozoIslandLoc.addNativeStore(storeBuilder.buildMalta(4, 150))
        gozoIslandLoc.addAlternativeStore(storeBuilder.buildRiposto(48, 500))
        gozoIslandLoc.addAlternativeStore(storeBuilder.buildPalermo(48, 500))

        gozoIslandLoc.addGrayStore(storeBuilder.buildM55(168))
        gozoIslandLoc.addGrayStore(storeBuilder.buildAntibes(168))
        gozoIslandLoc.addGrayStore(storeBuilder.buildPalmaED(168))
        gozoIslandLoc.addGrayStore(storeBuilder.buildPalmaNG(168))
        gozoIslandLoc.addGrayStore(storeBuilder.buildIbiza(168))
        gozoIslandLoc.addGrayStore(storeBuilder.buildOlbiaNA(168))
        gozoIslandLoc.addGrayStore(storeBuilder.buildOlbiaSYS(168))
        gozoIslandLoc.addGrayStore(storeBuilder.buildGenova(168))
        gozoIslandLoc.addGrayStore(storeBuilder.buildNapoli(168))
        gozoIslandLoc.addGrayStore(storeBuilder.buildAthensSotiris(192))
        gozoIslandLoc.addGrayStore(storeBuilder.buildAthensH360(192))
        gozoIslandLoc.addGrayStore(storeBuilder.buildCorfu(192))
        gozoIslandLoc.addGrayStore(storeBuilder.buildKosA1(216))
        gozoIslandLoc.addGrayStore(storeBuilder.buildRhodes(216))
        gozoIslandLoc.addGrayStore(storeBuilder.buildVenice(192))
        gozoIslandLoc.addGrayStore(storeBuilder.buildZadar(240))
        gozoIslandLoc.addGrayStore(storeBuilder.buildSplit(240))
        gozoIslandLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(240))
        gozoIslandLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(240))
        gozoIslandLoc.addGrayStore(storeBuilder.buildBCM(216))
        gozoIslandLoc.addGrayStore(storeBuilder.buildTivat(216))
        gozoIslandLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(168))

        gozoIsland.addLocation(gozoIslandLoc)
        regions.add(gozoIsland)

        return regions.toList()
    }

    override fun buildCorsica(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        // Corsica
        val calvi = Region("Calvi",54)

        val calviLoc = Location("Calvi")

        calviLoc.addNativeStore(storeBuilder.buildAntibes(16, 746))
        calviLoc.addAlternativeStore(storeBuilder.buildOlbiaNA(12, 970))
        calviLoc.addAlternativeStore(storeBuilder.buildOlbiaSYS(12, 970))

        calviLoc.addGrayStore(storeBuilder.buildM55(14))
        calviLoc.addGrayStore(storeBuilder.buildPalmaED(168))
        calviLoc.addGrayStore(storeBuilder.buildPalmaNG(168))
        calviLoc.addGrayStore(storeBuilder.buildIbiza(168))
        calviLoc.addGrayStore(storeBuilder.buildGenova(96))
        calviLoc.addGrayStore(storeBuilder.buildNapoli(144))
        calviLoc.addGrayStore(storeBuilder.buildPalermo(216))
        calviLoc.addGrayStore(storeBuilder.buildRiposto(216))
        calviLoc.addGrayStore(storeBuilder.buildMalta(216))
        calviLoc.addGrayStore(storeBuilder.buildAthensSotiris(216))
        calviLoc.addGrayStore(storeBuilder.buildAthensH360(216))
        calviLoc.addGrayStore(storeBuilder.buildCorfu(216))
        calviLoc.addGrayStore(storeBuilder.buildKosA1(216))
        calviLoc.addGrayStore(storeBuilder.buildRhodes(216))
        calviLoc.addGrayStore(storeBuilder.buildVenice(144))
        calviLoc.addGrayStore(storeBuilder.buildZadar(192))
        calviLoc.addGrayStore(storeBuilder.buildSplit(192))
        calviLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(192))
        calviLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(192))
        calviLoc.addGrayStore(storeBuilder.buildBCM(216))
        calviLoc.addGrayStore(storeBuilder.buildTivat(216))
        calviLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(168))

        calvi.addLocation(calviLoc)
        regions.add(calvi)


        // Saint Florent
        val saintFlorent = Region("Saint Florent",55)

        val saintFlorentLoc = Location("Saint Florent")

        saintFlorentLoc.addNativeStore(storeBuilder.buildAntibes(15, 678))
        saintFlorentLoc.addAlternativeStore(storeBuilder.buildOlbiaNA(12, 970))
        saintFlorentLoc.addAlternativeStore(storeBuilder.buildOlbiaSYS(12, 970))

        saintFlorentLoc.addGrayStore(storeBuilder.buildM55(13))
        saintFlorentLoc.addGrayStore(storeBuilder.buildPalmaED(168))
        saintFlorentLoc.addGrayStore(storeBuilder.buildPalmaNG(168))
        saintFlorentLoc.addGrayStore(storeBuilder.buildIbiza(168))
        saintFlorentLoc.addGrayStore(storeBuilder.buildGenova(96))
        saintFlorentLoc.addGrayStore(storeBuilder.buildNapoli(144))
        saintFlorentLoc.addGrayStore(storeBuilder.buildPalermo(216))
        saintFlorentLoc.addGrayStore(storeBuilder.buildRiposto(216))
        saintFlorentLoc.addGrayStore(storeBuilder.buildMalta(216))
        saintFlorentLoc.addGrayStore(storeBuilder.buildAthensSotiris(216))
        saintFlorentLoc.addGrayStore(storeBuilder.buildAthensH360(216))
        saintFlorentLoc.addGrayStore(storeBuilder.buildCorfu(216))
        saintFlorentLoc.addGrayStore(storeBuilder.buildKosA1(216))
        saintFlorentLoc.addGrayStore(storeBuilder.buildRhodes(216))
        saintFlorentLoc.addGrayStore(storeBuilder.buildVenice(144))
        saintFlorentLoc.addGrayStore(storeBuilder.buildZadar(192))
        saintFlorentLoc.addGrayStore(storeBuilder.buildSplit(192))
        saintFlorentLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(192))
        saintFlorentLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(192))
        saintFlorentLoc.addGrayStore(storeBuilder.buildBCM(216))
        saintFlorentLoc.addGrayStore(storeBuilder.buildTivat(216))
        saintFlorentLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(168))

        saintFlorent.addLocation(saintFlorentLoc)
        regions.add(saintFlorent)


        // Bastia
        val bastia = Region("Bastia",56)

        val bastiaLoc = Location("Bastia")

        bastiaLoc.addNativeStore(storeBuilder.buildAntibes(15, 654))
        bastiaLoc.addAlternativeStore(storeBuilder.buildOlbiaNA(12, 1070))
        bastiaLoc.addAlternativeStore(storeBuilder.buildOlbiaSYS(12, 1070))

        bastiaLoc.addGrayStore(storeBuilder.buildM55(13))
        bastiaLoc.addGrayStore(storeBuilder.buildPalmaED(168))
        bastiaLoc.addGrayStore(storeBuilder.buildPalmaNG(168))
        bastiaLoc.addGrayStore(storeBuilder.buildIbiza(168))
        bastiaLoc.addGrayStore(storeBuilder.buildGenova(96))
        bastiaLoc.addGrayStore(storeBuilder.buildNapoli(144))
        bastiaLoc.addGrayStore(storeBuilder.buildPalermo(216))
        bastiaLoc.addGrayStore(storeBuilder.buildRiposto(216))
        bastiaLoc.addGrayStore(storeBuilder.buildMalta(216))
        bastiaLoc.addGrayStore(storeBuilder.buildAthensSotiris(216))
        bastiaLoc.addGrayStore(storeBuilder.buildAthensH360(216))
        bastiaLoc.addGrayStore(storeBuilder.buildCorfu(216))
        bastiaLoc.addGrayStore(storeBuilder.buildKosA1(216))
        bastiaLoc.addGrayStore(storeBuilder.buildRhodes(216))
        bastiaLoc.addGrayStore(storeBuilder.buildVenice(144))
        bastiaLoc.addGrayStore(storeBuilder.buildZadar(192))
        bastiaLoc.addGrayStore(storeBuilder.buildSplit(192))
        bastiaLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(192))
        bastiaLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(192))
        bastiaLoc.addGrayStore(storeBuilder.buildBCM(216))
        bastiaLoc.addGrayStore(storeBuilder.buildTivat(216))
        bastiaLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(168))

        bastia.addLocation(bastiaLoc)
        regions.add(bastia)


        // Porto Vecchio
        val portoVecchio = Region("Porto Vecchio",57)

        val portoVecchioLoc = Location("Porto Vecchio")

        portoVecchioLoc.addNativeStore(storeBuilder.buildOlbiaNA(8, 440))
        portoVecchioLoc.addNativeStore(storeBuilder.buildOlbiaSYS(8, 440))
        portoVecchioLoc.addAlternativeStore(storeBuilder.buildAntibes(17, 798))

        portoVecchioLoc.addGrayStore(storeBuilder.buildM55(15))
        portoVecchioLoc.addGrayStore(storeBuilder.buildPalmaED(168))
        portoVecchioLoc.addGrayStore(storeBuilder.buildPalmaNG(168))
        portoVecchioLoc.addGrayStore(storeBuilder.buildIbiza(168))
        portoVecchioLoc.addGrayStore(storeBuilder.buildGenova(96))
        portoVecchioLoc.addGrayStore(storeBuilder.buildNapoli(144))
        portoVecchioLoc.addGrayStore(storeBuilder.buildPalermo(216))
        portoVecchioLoc.addGrayStore(storeBuilder.buildRiposto(216))
        portoVecchioLoc.addGrayStore(storeBuilder.buildMalta(216))
        portoVecchioLoc.addGrayStore(storeBuilder.buildAthensSotiris(216))
        portoVecchioLoc.addGrayStore(storeBuilder.buildAthensH360(216))
        portoVecchioLoc.addGrayStore(storeBuilder.buildCorfu(216))
        portoVecchioLoc.addGrayStore(storeBuilder.buildKosA1(216))
        portoVecchioLoc.addGrayStore(storeBuilder.buildRhodes(216))
        portoVecchioLoc.addGrayStore(storeBuilder.buildVenice(144))
        portoVecchioLoc.addGrayStore(storeBuilder.buildZadar(192))
        portoVecchioLoc.addGrayStore(storeBuilder.buildSplit(192))
        portoVecchioLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(192))
        portoVecchioLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(192))
        portoVecchioLoc.addGrayStore(storeBuilder.buildBCM(216))
        portoVecchioLoc.addGrayStore(storeBuilder.buildTivat(216))
        portoVecchioLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(168))

        portoVecchio.addLocation(portoVecchioLoc)
        regions.add(portoVecchio)


        // Bonifacio
        val bonifacio = Region("Bonifacio",58)

        val bonifacioLoc = Location("Bonifacio")

        bonifacioLoc.addNativeStore(storeBuilder.buildOlbiaNA(7, 340))
        bonifacioLoc.addNativeStore(storeBuilder.buildOlbiaSYS(7, 340))
        bonifacioLoc.addAlternativeStore(storeBuilder.buildAntibes(17, 827))

        bonifacioLoc.addGrayStore(storeBuilder.buildM55(15))
        bonifacioLoc.addGrayStore(storeBuilder.buildPalmaED(168))
        bonifacioLoc.addGrayStore(storeBuilder.buildPalmaNG(168))
        bonifacioLoc.addGrayStore(storeBuilder.buildIbiza(168))
        bonifacioLoc.addGrayStore(storeBuilder.buildGenova(96))
        bonifacioLoc.addGrayStore(storeBuilder.buildNapoli(144))
        bonifacioLoc.addGrayStore(storeBuilder.buildPalermo(216))
        bonifacioLoc.addGrayStore(storeBuilder.buildRiposto(216))
        bonifacioLoc.addGrayStore(storeBuilder.buildMalta(216))
        bonifacioLoc.addGrayStore(storeBuilder.buildAthensSotiris(216))
        bonifacioLoc.addGrayStore(storeBuilder.buildAthensH360(216))
        bonifacioLoc.addGrayStore(storeBuilder.buildCorfu(216))
        bonifacioLoc.addGrayStore(storeBuilder.buildKosA1(216))
        bonifacioLoc.addGrayStore(storeBuilder.buildRhodes(216))
        bonifacioLoc.addGrayStore(storeBuilder.buildVenice(144))
        bonifacioLoc.addGrayStore(storeBuilder.buildZadar(192))
        bonifacioLoc.addGrayStore(storeBuilder.buildSplit(192))
        bonifacioLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(192))
        bonifacioLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(192))
        bonifacioLoc.addGrayStore(storeBuilder.buildBCM(216))
        bonifacioLoc.addGrayStore(storeBuilder.buildTivat(216))
        bonifacioLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(168))

        bonifacio.addLocation(bonifacioLoc)
        regions.add(bonifacio)


        // Ajiaccio
        val ajjaccio = Region("Ajjaccio",59)

        val ajjaccioLoc = Location("Ajjaccio")

        ajjaccioLoc.addNativeStore(storeBuilder.buildOlbiaNA(10, 340))
        ajjaccioLoc.addNativeStore(storeBuilder.buildOlbiaSYS(10, 340))
        ajjaccioLoc.addAlternativeStore(storeBuilder.buildAntibes(17, 804))

        ajjaccioLoc.addGrayStore(storeBuilder.buildM55(15))
        ajjaccioLoc.addGrayStore(storeBuilder.buildPalmaED(168))
        ajjaccioLoc.addGrayStore(storeBuilder.buildPalmaNG(168))
        ajjaccioLoc.addGrayStore(storeBuilder.buildIbiza(168))
        ajjaccioLoc.addGrayStore(storeBuilder.buildGenova(96))
        ajjaccioLoc.addGrayStore(storeBuilder.buildNapoli(144))
        ajjaccioLoc.addGrayStore(storeBuilder.buildPalermo(216))
        ajjaccioLoc.addGrayStore(storeBuilder.buildRiposto(216))
        ajjaccioLoc.addGrayStore(storeBuilder.buildMalta(216))
        ajjaccioLoc.addGrayStore(storeBuilder.buildAthensSotiris(216))
        ajjaccioLoc.addGrayStore(storeBuilder.buildAthensH360(216))
        ajjaccioLoc.addGrayStore(storeBuilder.buildCorfu(216))
        ajjaccioLoc.addGrayStore(storeBuilder.buildKosA1(216))
        ajjaccioLoc.addGrayStore(storeBuilder.buildRhodes(216))
        ajjaccioLoc.addGrayStore(storeBuilder.buildVenice(144))
        ajjaccioLoc.addGrayStore(storeBuilder.buildZadar(192))
        ajjaccioLoc.addGrayStore(storeBuilder.buildSplit(192))
        ajjaccioLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(192))
        ajjaccioLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(192))
        ajjaccioLoc.addGrayStore(storeBuilder.buildBCM(216))
        ajjaccioLoc.addGrayStore(storeBuilder.buildTivat(216))
        ajjaccioLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(168))

        ajjaccio.addLocation(ajjaccioLoc)
        regions.add(ajjaccio)

        return regions.toList()
    }

    override fun buildSardinia(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        // Baia Sardinia
        val baiaSardinia = Region("Baia Sardinia",60)

        val baiaSardiniaLoc = Location("Baia Sardinia")

        baiaSardiniaLoc.addNativeStore(storeBuilder.buildOlbiaNA(3, 70))
        baiaSardiniaLoc.addNativeStore(storeBuilder.buildOlbiaSYS(3, 70))
        baiaSardiniaLoc.addAlternativeStore(storeBuilder.buildNapoli(24, 1100))

        baiaSardiniaLoc.addGrayStore(storeBuilder.buildM55(16))
        baiaSardiniaLoc.addGrayStore(storeBuilder.buildAntibes(16))
        baiaSardiniaLoc.addGrayStore(storeBuilder.buildPalmaED(168))
        baiaSardiniaLoc.addGrayStore(storeBuilder.buildPalmaNG(168))
        baiaSardiniaLoc.addGrayStore(storeBuilder.buildIbiza(168))
        baiaSardiniaLoc.addGrayStore(storeBuilder.buildGenova(96))
        baiaSardiniaLoc.addGrayStore(storeBuilder.buildPalermo(168))
        baiaSardiniaLoc.addGrayStore(storeBuilder.buildRiposto(168))
        baiaSardiniaLoc.addGrayStore(storeBuilder.buildMalta(216))
        baiaSardiniaLoc.addGrayStore(storeBuilder.buildAthensSotiris(168))
        baiaSardiniaLoc.addGrayStore(storeBuilder.buildAthensH360(168))
        baiaSardiniaLoc.addGrayStore(storeBuilder.buildCorfu(216))
        baiaSardiniaLoc.addGrayStore(storeBuilder.buildKosA1(216))
        baiaSardiniaLoc.addGrayStore(storeBuilder.buildRhodes(216))
        baiaSardiniaLoc.addGrayStore(storeBuilder.buildVenice(96))
        baiaSardiniaLoc.addGrayStore(storeBuilder.buildZadar(144))
        baiaSardiniaLoc.addGrayStore(storeBuilder.buildSplit(144))
        baiaSardiniaLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(144))
        baiaSardiniaLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(144))
        baiaSardiniaLoc.addGrayStore(storeBuilder.buildBCM(216))
        baiaSardiniaLoc.addGrayStore(storeBuilder.buildTivat(216))
        baiaSardiniaLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(120))

        baiaSardinia.addLocation(baiaSardiniaLoc)
        regions.add(baiaSardinia)


        // Porto Cervo
        val portoCervo = Region("Porto Cervo",61)

        val portoCervoLoc = Location("Porto Cervo")

        portoCervoLoc.addNativeStore(storeBuilder.buildOlbiaNA(3, 65))
        portoCervoLoc.addNativeStore(storeBuilder.buildOlbiaSYS(3, 65))
        portoCervoLoc.addAlternativeStore(storeBuilder.buildNapoli(24, 1100))

        portoCervoLoc.addGrayStore(storeBuilder.buildM55(16))
        portoCervoLoc.addGrayStore(storeBuilder.buildAntibes(16))
        portoCervoLoc.addGrayStore(storeBuilder.buildPalmaED(168))
        portoCervoLoc.addGrayStore(storeBuilder.buildPalmaNG(168))
        portoCervoLoc.addGrayStore(storeBuilder.buildIbiza(168))
        portoCervoLoc.addGrayStore(storeBuilder.buildGenova(96))
        portoCervoLoc.addGrayStore(storeBuilder.buildPalermo(168))
        portoCervoLoc.addGrayStore(storeBuilder.buildRiposto(168))
        portoCervoLoc.addGrayStore(storeBuilder.buildMalta(216))
        portoCervoLoc.addGrayStore(storeBuilder.buildAthensSotiris(168))
        portoCervoLoc.addGrayStore(storeBuilder.buildAthensH360(168))
        portoCervoLoc.addGrayStore(storeBuilder.buildCorfu(216))
        portoCervoLoc.addGrayStore(storeBuilder.buildKosA1(216))
        portoCervoLoc.addGrayStore(storeBuilder.buildRhodes(216))
        portoCervoLoc.addGrayStore(storeBuilder.buildVenice(96))
        portoCervoLoc.addGrayStore(storeBuilder.buildZadar(144))
        portoCervoLoc.addGrayStore(storeBuilder.buildSplit(144))
        portoCervoLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(144))
        portoCervoLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(144))
        portoCervoLoc.addGrayStore(storeBuilder.buildBCM(216))
        portoCervoLoc.addGrayStore(storeBuilder.buildTivat(216))
        portoCervoLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(120))

        portoCervo.addLocation(portoCervoLoc)
        regions.add(portoCervo)


        // Porto Rotondo
        val portoRotondo = Region("Porto Rotondo",62)

        val portoRotondoLoc = Location("Porto Rotondo")

        portoRotondoLoc.addNativeStore(storeBuilder.buildOlbiaNA(3, 60))
        portoRotondoLoc.addNativeStore(storeBuilder.buildOlbiaSYS(3, 60))
        portoRotondoLoc.addAlternativeStore(storeBuilder.buildNapoli(24, 1100))

        portoRotondoLoc.addGrayStore(storeBuilder.buildM55(16))
        portoRotondoLoc.addGrayStore(storeBuilder.buildAntibes(16))
        portoRotondoLoc.addGrayStore(storeBuilder.buildPalmaED(168))
        portoRotondoLoc.addGrayStore(storeBuilder.buildPalmaNG(168))
        portoRotondoLoc.addGrayStore(storeBuilder.buildIbiza(168))
        portoRotondoLoc.addGrayStore(storeBuilder.buildGenova(96))
        portoRotondoLoc.addGrayStore(storeBuilder.buildPalermo(168))
        portoRotondoLoc.addGrayStore(storeBuilder.buildRiposto(168))
        portoRotondoLoc.addGrayStore(storeBuilder.buildMalta(216))
        portoRotondoLoc.addGrayStore(storeBuilder.buildAthensSotiris(168))
        portoRotondoLoc.addGrayStore(storeBuilder.buildAthensH360(168))
        portoRotondoLoc.addGrayStore(storeBuilder.buildCorfu(216))
        portoRotondoLoc.addGrayStore(storeBuilder.buildKosA1(216))
        portoRotondoLoc.addGrayStore(storeBuilder.buildRhodes(216))
        portoRotondoLoc.addGrayStore(storeBuilder.buildVenice(96))
        portoRotondoLoc.addGrayStore(storeBuilder.buildZadar(144))
        portoRotondoLoc.addGrayStore(storeBuilder.buildSplit(144))
        portoRotondoLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(144))
        portoRotondoLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(144))
        portoRotondoLoc.addGrayStore(storeBuilder.buildBCM(216))
        portoRotondoLoc.addGrayStore(storeBuilder.buildTivat(216))
        portoRotondoLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(120))

        portoRotondo.addLocation(portoRotondoLoc)
        regions.add(portoRotondo)


        // Porto San Paolo
        val portoSanPaolo = Region("Porto San paolo",63)

        val portoSanPaoloLoc = Location("Porto San paolo")

        portoSanPaoloLoc.addNativeStore(storeBuilder.buildOlbiaNA(3, 70))
        portoSanPaoloLoc.addNativeStore(storeBuilder.buildOlbiaSYS(3, 70))
        portoSanPaoloLoc.addAlternativeStore(storeBuilder.buildNapoli(24, 1100))

        portoSanPaoloLoc.addGrayStore(storeBuilder.buildM55(16))
        portoSanPaoloLoc.addGrayStore(storeBuilder.buildAntibes(16))
        portoSanPaoloLoc.addGrayStore(storeBuilder.buildPalmaED(168))
        portoSanPaoloLoc.addGrayStore(storeBuilder.buildPalmaNG(168))
        portoSanPaoloLoc.addGrayStore(storeBuilder.buildIbiza(168))
        portoSanPaoloLoc.addGrayStore(storeBuilder.buildGenova(96))
        portoSanPaoloLoc.addGrayStore(storeBuilder.buildPalermo(168))
        portoSanPaoloLoc.addGrayStore(storeBuilder.buildRiposto(168))
        portoSanPaoloLoc.addGrayStore(storeBuilder.buildMalta(216))
        portoSanPaoloLoc.addGrayStore(storeBuilder.buildAthensSotiris(168))
        portoSanPaoloLoc.addGrayStore(storeBuilder.buildAthensH360(168))
        portoSanPaoloLoc.addGrayStore(storeBuilder.buildCorfu(216))
        portoSanPaoloLoc.addGrayStore(storeBuilder.buildKosA1(216))
        portoSanPaoloLoc.addGrayStore(storeBuilder.buildRhodes(216))
        portoSanPaoloLoc.addGrayStore(storeBuilder.buildVenice(96))
        portoSanPaoloLoc.addGrayStore(storeBuilder.buildZadar(144))
        portoSanPaoloLoc.addGrayStore(storeBuilder.buildSplit(144))
        portoSanPaoloLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(144))
        portoSanPaoloLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(144))
        portoSanPaoloLoc.addGrayStore(storeBuilder.buildBCM(216))
        portoSanPaoloLoc.addGrayStore(storeBuilder.buildTivat(216))
        portoSanPaoloLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(120))

        portoSanPaolo.addLocation(portoSanPaoloLoc)
        regions.add(portoSanPaolo)


        // Olbia
        val olbia = Region("Olbia",64)

        val olbiaLoc = Location("Olbia")

        olbiaLoc.addNativeStore(storeBuilder.buildOlbiaNA(1, 40))
        olbiaLoc.addNativeStore(storeBuilder.buildOlbiaSYS(1, 40))
        olbiaLoc.addAlternativeStore(storeBuilder.buildNapoli(24, 1100))

        olbiaLoc.addGrayStore(storeBuilder.buildM55(15))
        olbiaLoc.addGrayStore(storeBuilder.buildAntibes(15))
        olbiaLoc.addGrayStore(storeBuilder.buildPalmaED(168))
        olbiaLoc.addGrayStore(storeBuilder.buildPalmaNG(168))
        olbiaLoc.addGrayStore(storeBuilder.buildIbiza(168))
        olbiaLoc.addGrayStore(storeBuilder.buildGenova(96))
        olbiaLoc.addGrayStore(storeBuilder.buildPalermo(168))
        olbiaLoc.addGrayStore(storeBuilder.buildRiposto(168))
        olbiaLoc.addGrayStore(storeBuilder.buildMalta(216))
        olbiaLoc.addGrayStore(storeBuilder.buildAthensSotiris(168))
        olbiaLoc.addGrayStore(storeBuilder.buildAthensH360(168))
        olbiaLoc.addGrayStore(storeBuilder.buildCorfu(216))
        olbiaLoc.addGrayStore(storeBuilder.buildKosA1(216))
        olbiaLoc.addGrayStore(storeBuilder.buildRhodes(216))
        olbiaLoc.addGrayStore(storeBuilder.buildVenice(96))
        olbiaLoc.addGrayStore(storeBuilder.buildZadar(144))
        olbiaLoc.addGrayStore(storeBuilder.buildSplit(144))
        olbiaLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(144))
        olbiaLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(144))
        olbiaLoc.addGrayStore(storeBuilder.buildBCM(216))
        olbiaLoc.addGrayStore(storeBuilder.buildTivat(216))
        olbiaLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(120))

        olbia.addLocation(olbiaLoc)
        regions.add(olbia)


        // Palau
        val palau = Region("Palau",65)

        val palauLoc = Location("Palau")

        palauLoc.addNativeStore(storeBuilder.buildOlbiaNA(3, 95))
        palauLoc.addNativeStore(storeBuilder.buildOlbiaSYS(3, 95))
        palauLoc.addAlternativeStore(storeBuilder.buildNapoli(24, 1100))

        palauLoc.addGrayStore(storeBuilder.buildM55(16))
        palauLoc.addGrayStore(storeBuilder.buildAntibes(16))
        palauLoc.addGrayStore(storeBuilder.buildPalmaED(168))
        palauLoc.addGrayStore(storeBuilder.buildPalmaNG(168))
        palauLoc.addGrayStore(storeBuilder.buildIbiza(168))
        palauLoc.addGrayStore(storeBuilder.buildGenova(96))
        palauLoc.addGrayStore(storeBuilder.buildPalermo(168))
        palauLoc.addGrayStore(storeBuilder.buildRiposto(168))
        palauLoc.addGrayStore(storeBuilder.buildMalta(216))
        palauLoc.addGrayStore(storeBuilder.buildAthensSotiris(168))
        palauLoc.addGrayStore(storeBuilder.buildAthensH360(168))
        palauLoc.addGrayStore(storeBuilder.buildCorfu(216))
        palauLoc.addGrayStore(storeBuilder.buildKosA1(216))
        palauLoc.addGrayStore(storeBuilder.buildRhodes(216))
        palauLoc.addGrayStore(storeBuilder.buildVenice(96))
        palauLoc.addGrayStore(storeBuilder.buildZadar(144))
        palauLoc.addGrayStore(storeBuilder.buildSplit(144))
        palauLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(144))
        palauLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(144))
        palauLoc.addGrayStore(storeBuilder.buildBCM(216))
        palauLoc.addGrayStore(storeBuilder.buildTivat(216))
        palauLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(120))

        palau.addLocation(palauLoc)
        regions.add(palau)


        // Poltu Quatu
        val poltuQuatu = Region("Poltu Quatu",66)

        val poltuQuatuLoc = Location("Poltu Quatu")

        poltuQuatuLoc.addNativeStore(storeBuilder.buildOlbiaNA(3, 80))
        poltuQuatuLoc.addNativeStore(storeBuilder.buildOlbiaSYS(3, 80))
        poltuQuatuLoc.addAlternativeStore(storeBuilder.buildNapoli(24, 1100))

        poltuQuatuLoc.addGrayStore(storeBuilder.buildM55(16))
        poltuQuatuLoc.addGrayStore(storeBuilder.buildAntibes(16))
        poltuQuatuLoc.addGrayStore(storeBuilder.buildPalmaED(168))
        poltuQuatuLoc.addGrayStore(storeBuilder.buildPalmaNG(168))
        poltuQuatuLoc.addGrayStore(storeBuilder.buildIbiza(168))
        poltuQuatuLoc.addGrayStore(storeBuilder.buildGenova(96))
        poltuQuatuLoc.addGrayStore(storeBuilder.buildPalermo(168))
        poltuQuatuLoc.addGrayStore(storeBuilder.buildRiposto(168))
        poltuQuatuLoc.addGrayStore(storeBuilder.buildMalta(216))
        poltuQuatuLoc.addGrayStore(storeBuilder.buildAthensSotiris(168))
        poltuQuatuLoc.addGrayStore(storeBuilder.buildAthensH360(168))
        poltuQuatuLoc.addGrayStore(storeBuilder.buildCorfu(216))
        poltuQuatuLoc.addGrayStore(storeBuilder.buildKosA1(216))
        poltuQuatuLoc.addGrayStore(storeBuilder.buildRhodes(216))
        poltuQuatuLoc.addGrayStore(storeBuilder.buildVenice(96))
        poltuQuatuLoc.addGrayStore(storeBuilder.buildZadar(144))
        poltuQuatuLoc.addGrayStore(storeBuilder.buildSplit(144))
        poltuQuatuLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(144))
        poltuQuatuLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(144))
        poltuQuatuLoc.addGrayStore(storeBuilder.buildBCM(216))
        poltuQuatuLoc.addGrayStore(storeBuilder.buildTivat(216))
        poltuQuatuLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(120))

        poltuQuatu.addLocation(poltuQuatuLoc)
        regions.add(poltuQuatu)


        // Cala Bitta
        val calaBitta = Region("Cala Bitta",67)

        val calaBittaLoc = Location("Cala Bitta")

        calaBittaLoc.addNativeStore(storeBuilder.buildOlbiaNA(3, 70))
        calaBittaLoc.addNativeStore(storeBuilder.buildOlbiaSYS(3, 70))
        calaBittaLoc.addAlternativeStore(storeBuilder.buildNapoli(24, 1100))

        calaBittaLoc.addGrayStore(storeBuilder.buildM55(16))
        calaBittaLoc.addGrayStore(storeBuilder.buildAntibes(16))
        calaBittaLoc.addGrayStore(storeBuilder.buildPalmaED(168))
        calaBittaLoc.addGrayStore(storeBuilder.buildPalmaNG(168))
        calaBittaLoc.addGrayStore(storeBuilder.buildIbiza(168))
        calaBittaLoc.addGrayStore(storeBuilder.buildGenova(96))
        calaBittaLoc.addGrayStore(storeBuilder.buildPalermo(168))
        calaBittaLoc.addGrayStore(storeBuilder.buildRiposto(168))
        calaBittaLoc.addGrayStore(storeBuilder.buildMalta(216))
        calaBittaLoc.addGrayStore(storeBuilder.buildAthensSotiris(168))
        calaBittaLoc.addGrayStore(storeBuilder.buildAthensH360(168))
        calaBittaLoc.addGrayStore(storeBuilder.buildCorfu(216))
        calaBittaLoc.addGrayStore(storeBuilder.buildKosA1(216))
        calaBittaLoc.addGrayStore(storeBuilder.buildRhodes(216))
        calaBittaLoc.addGrayStore(storeBuilder.buildVenice(96))
        calaBittaLoc.addGrayStore(storeBuilder.buildZadar(144))
        calaBittaLoc.addGrayStore(storeBuilder.buildSplit(144))
        calaBittaLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(144))
        calaBittaLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(144))
        calaBittaLoc.addGrayStore(storeBuilder.buildBCM(216))
        calaBittaLoc.addGrayStore(storeBuilder.buildTivat(216))
        calaBittaLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(120))

        calaBitta.addLocation(calaBittaLoc)
        regions.add(calaBitta)


        // Cannigione
        val cannigione = Region("Cannigione",68)

        val cannigioneLoc = Location("Cannigione")

        cannigioneLoc.addNativeStore(storeBuilder.buildOlbiaNA(3, 80))
        cannigioneLoc.addNativeStore(storeBuilder.buildOlbiaSYS(3, 80))
        cannigioneLoc.addAlternativeStore(storeBuilder.buildNapoli(24, 1100))

        cannigioneLoc.addGrayStore(storeBuilder.buildM55(16))
        cannigioneLoc.addGrayStore(storeBuilder.buildAntibes(16))
        cannigioneLoc.addGrayStore(storeBuilder.buildPalmaED(168))
        cannigioneLoc.addGrayStore(storeBuilder.buildPalmaNG(168))
        cannigioneLoc.addGrayStore(storeBuilder.buildIbiza(168))
        cannigioneLoc.addGrayStore(storeBuilder.buildGenova(96))
        cannigioneLoc.addGrayStore(storeBuilder.buildPalermo(168))
        cannigioneLoc.addGrayStore(storeBuilder.buildRiposto(168))
        cannigioneLoc.addGrayStore(storeBuilder.buildMalta(216))
        cannigioneLoc.addGrayStore(storeBuilder.buildAthensSotiris(168))
        cannigioneLoc.addGrayStore(storeBuilder.buildAthensH360(168))
        cannigioneLoc.addGrayStore(storeBuilder.buildCorfu(216))
        cannigioneLoc.addGrayStore(storeBuilder.buildKosA1(216))
        cannigioneLoc.addGrayStore(storeBuilder.buildRhodes(216))
        cannigioneLoc.addGrayStore(storeBuilder.buildVenice(96))
        cannigioneLoc.addGrayStore(storeBuilder.buildZadar(144))
        cannigioneLoc.addGrayStore(storeBuilder.buildSplit(144))
        cannigioneLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(144))
        cannigioneLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(144))
        cannigioneLoc.addGrayStore(storeBuilder.buildBCM(216))
        cannigioneLoc.addGrayStore(storeBuilder.buildTivat(216))
        cannigioneLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(120))

        cannigione.addLocation(cannigioneLoc)
        regions.add(cannigione)


        // Cagliari
        val cagliari = Region("Cagliari",69)

        val cagliariLoc = Location("Cagliari")

        cagliariLoc.addNativeStore(storeBuilder.buildOlbiaNA(8, 440))
        cagliariLoc.addNativeStore(storeBuilder.buildOlbiaSYS(8, 440))
        cagliariLoc.addAlternativeStore(storeBuilder.buildNapoli(24, 1100))

        cagliariLoc.addGrayStore(storeBuilder.buildM55(18))
        cagliariLoc.addGrayStore(storeBuilder.buildAntibes(18))
        cagliariLoc.addGrayStore(storeBuilder.buildPalmaED(144))
        cagliariLoc.addGrayStore(storeBuilder.buildPalmaNG(144))
        cagliariLoc.addGrayStore(storeBuilder.buildIbiza(144))
        cagliariLoc.addGrayStore(storeBuilder.buildGenova(96))
        cagliariLoc.addGrayStore(storeBuilder.buildPalermo(168))
        cagliariLoc.addGrayStore(storeBuilder.buildRiposto(168))
        cagliariLoc.addGrayStore(storeBuilder.buildMalta(216))
        cagliariLoc.addGrayStore(storeBuilder.buildAthensSotiris(168))
        cagliariLoc.addGrayStore(storeBuilder.buildAthensH360(168))
        cagliariLoc.addGrayStore(storeBuilder.buildCorfu(216))
        cagliariLoc.addGrayStore(storeBuilder.buildKosA1(216))
        cagliariLoc.addGrayStore(storeBuilder.buildRhodes(216))
        cagliariLoc.addGrayStore(storeBuilder.buildVenice(96))
        cagliariLoc.addGrayStore(storeBuilder.buildZadar(144))
        cagliariLoc.addGrayStore(storeBuilder.buildSplit(144))
        cagliariLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(144))
        cagliariLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(144))
        cagliariLoc.addGrayStore(storeBuilder.buildBCM(216))
        cagliariLoc.addGrayStore(storeBuilder.buildTivat(216))
        cagliariLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(120))

        cagliari.addLocation(cagliariLoc)
        regions.add(cagliari)


        // La Maddalena
        val laMaddalena = Region("La Maddalena",70)

        val laMaddalenaLoc = Location("La Maddalena")

        laMaddalenaLoc.addNativeStore(storeBuilder.buildOlbiaNA(6, 700))
        laMaddalenaLoc.addNativeStore(storeBuilder.buildOlbiaSYS(6, 700))
        laMaddalenaLoc.addAlternativeStore(storeBuilder.buildNapoli(24, 1100))

        laMaddalenaLoc.addGrayStore(storeBuilder.buildM55(17))
        laMaddalenaLoc.addGrayStore(storeBuilder.buildAntibes(16))
        laMaddalenaLoc.addGrayStore(storeBuilder.buildPalmaED(168))
        laMaddalenaLoc.addGrayStore(storeBuilder.buildPalmaNG(168))
        laMaddalenaLoc.addGrayStore(storeBuilder.buildIbiza(168))
        laMaddalenaLoc.addGrayStore(storeBuilder.buildGenova(96))
        laMaddalenaLoc.addGrayStore(storeBuilder.buildPalermo(168))
        laMaddalenaLoc.addGrayStore(storeBuilder.buildRiposto(168))
        laMaddalenaLoc.addGrayStore(storeBuilder.buildMalta(216))
        laMaddalenaLoc.addGrayStore(storeBuilder.buildAthensSotiris(168))
        laMaddalenaLoc.addGrayStore(storeBuilder.buildAthensH360(168))
        laMaddalenaLoc.addGrayStore(storeBuilder.buildCorfu(216))
        laMaddalenaLoc.addGrayStore(storeBuilder.buildKosA1(216))
        laMaddalenaLoc.addGrayStore(storeBuilder.buildRhodes(216))
        laMaddalenaLoc.addGrayStore(storeBuilder.buildVenice(96))
        laMaddalenaLoc.addGrayStore(storeBuilder.buildZadar(144))
        laMaddalenaLoc.addGrayStore(storeBuilder.buildSplit(144))
        laMaddalenaLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(144))
        laMaddalenaLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(144))
        laMaddalenaLoc.addGrayStore(storeBuilder.buildBCM(216))
        laMaddalenaLoc.addGrayStore(storeBuilder.buildTivat(216))
        laMaddalenaLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(120))

        laMaddalena.addLocation(laMaddalenaLoc)
        regions.add(laMaddalena)

        return regions.toList()
    }

    override fun buildCroatiaAndMontenegro(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        // Venice
        val venice = Region("Venice",71)

        val veniceLoc = Location("Venice")

        veniceLoc.addNativeStore(storeBuilder.buildM55(6, 240))
        veniceLoc.addNativeStore(storeBuilder.buildVenice(2, 200))

        veniceLoc.addGrayStore(storeBuilder.buildAntibes(96))
        veniceLoc.addGrayStore(storeBuilder.buildPalmaED(96))
        veniceLoc.addGrayStore(storeBuilder.buildPalmaNG(96))
        veniceLoc.addGrayStore(storeBuilder.buildIbiza(96))
        veniceLoc.addGrayStore(storeBuilder.buildOlbiaNA(96))
        veniceLoc.addGrayStore(storeBuilder.buildOlbiaSYS(96))
        veniceLoc.addGrayStore(storeBuilder.buildGenova(48))
        veniceLoc.addGrayStore(storeBuilder.buildNapoli(72))
        veniceLoc.addGrayStore(storeBuilder.buildPalermo(120))
        veniceLoc.addGrayStore(storeBuilder.buildRiposto(120))
        veniceLoc.addGrayStore(storeBuilder.buildMalta(168))
        veniceLoc.addGrayStore(storeBuilder.buildAthensSotiris(144))
        veniceLoc.addGrayStore(storeBuilder.buildAthensH360(144))
        veniceLoc.addGrayStore(storeBuilder.buildCorfu(168))
        veniceLoc.addGrayStore(storeBuilder.buildKosA1(168))
        veniceLoc.addGrayStore(storeBuilder.buildRhodes(168))
        veniceLoc.addGrayStore(storeBuilder.buildZadar(48))
        veniceLoc.addGrayStore(storeBuilder.buildSplit(48))
        veniceLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(48))
        veniceLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(48))
        veniceLoc.addGrayStore(storeBuilder.buildBCM(72))
        veniceLoc.addGrayStore(storeBuilder.buildTivat(72))
        veniceLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(24))

        venice.addLocation(veniceLoc)
        regions.add(venice)


        // Piran
        val piran = Region("Piran",72)

        val piranLoc = Location("Piran")

        piranLoc.addNativeStore(storeBuilder.buildM55(3, 125))
        piranLoc.addAlternativeStore(storeBuilder.buildVenice(4, 400))

        piranLoc.addGrayStore(storeBuilder.buildAntibes(96))
        piranLoc.addGrayStore(storeBuilder.buildPalmaED(96))
        piranLoc.addGrayStore(storeBuilder.buildPalmaNG(96))
        piranLoc.addGrayStore(storeBuilder.buildIbiza(96))
        piranLoc.addGrayStore(storeBuilder.buildOlbiaNA(120))
        piranLoc.addGrayStore(storeBuilder.buildOlbiaSYS(120))
        piranLoc.addGrayStore(storeBuilder.buildGenova(72))
        piranLoc.addGrayStore(storeBuilder.buildNapoli(96))
        piranLoc.addGrayStore(storeBuilder.buildPalermo(120))
        piranLoc.addGrayStore(storeBuilder.buildRiposto(120))
        piranLoc.addGrayStore(storeBuilder.buildMalta(216))
        piranLoc.addGrayStore(storeBuilder.buildAthensSotiris(120))
        piranLoc.addGrayStore(storeBuilder.buildAthensH360(120))
        piranLoc.addGrayStore(storeBuilder.buildCorfu(168))
        piranLoc.addGrayStore(storeBuilder.buildKosA1(168))
        piranLoc.addGrayStore(storeBuilder.buildRhodes(168))
        piranLoc.addGrayStore(storeBuilder.buildZadar(24))
        piranLoc.addGrayStore(storeBuilder.buildSplit(24))
        piranLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(48))
        piranLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(48))
        piranLoc.addGrayStore(storeBuilder.buildBCM(72))
        piranLoc.addGrayStore(storeBuilder.buildTivat(72))
        piranLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(24))

        piran.addLocation(piranLoc)
        regions.add(piran)


        // Rovinj
        val rovinj = Region("Rovinj",73)

        val rovinjLoc = Location("Rovinj")

        rovinjLoc.addNativeStore(storeBuilder.buildM55(6, 190))
        rovinjLoc.addAlternativeStore(storeBuilder.buildVenice(6, 600))
        rovinjLoc.addAlternativeStore(storeBuilder.buildSplit(8, 550))
        rovinjLoc.addAlternativeStore(storeBuilder.buildDubrovnikKristijan(12, 550))
        rovinjLoc.addAlternativeStore(storeBuilder.buildDubrovnikBWA(12, 550))
        rovinjLoc.addAlternativeStore(storeBuilder.buildZadar(8, 550))

        rovinjLoc.addGrayStore(storeBuilder.buildAntibes(96))
        rovinjLoc.addGrayStore(storeBuilder.buildPalmaED(120))
        rovinjLoc.addGrayStore(storeBuilder.buildPalmaNG(120))
        rovinjLoc.addGrayStore(storeBuilder.buildIbiza(120))
        rovinjLoc.addGrayStore(storeBuilder.buildOlbiaNA(120))
        rovinjLoc.addGrayStore(storeBuilder.buildOlbiaSYS(120))
        rovinjLoc.addGrayStore(storeBuilder.buildGenova(72))
        rovinjLoc.addGrayStore(storeBuilder.buildNapoli(96))
        rovinjLoc.addGrayStore(storeBuilder.buildPalermo(144))
        rovinjLoc.addGrayStore(storeBuilder.buildRiposto(144))
        rovinjLoc.addGrayStore(storeBuilder.buildMalta(216))
        rovinjLoc.addGrayStore(storeBuilder.buildAthensSotiris(120))
        rovinjLoc.addGrayStore(storeBuilder.buildAthensH360(120))
        rovinjLoc.addGrayStore(storeBuilder.buildCorfu(168))
        rovinjLoc.addGrayStore(storeBuilder.buildKosA1(168))
        rovinjLoc.addGrayStore(storeBuilder.buildRhodes(168))
        rovinjLoc.addGrayStore(storeBuilder.buildBCM(72))
        rovinjLoc.addGrayStore(storeBuilder.buildTivat(72))
        rovinjLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(24))

        rovinj.addLocation(rovinjLoc)
        regions.add(rovinj)


        // Pula
        val pula = Region("Pula",74)

        val pulaLoc = Location("Pula")

        pulaLoc.addNativeStore(storeBuilder.buildM55(6, 210))
        pulaLoc.addAlternativeStore(storeBuilder.buildVenice(6, 800))
        pulaLoc.addAlternativeStore(storeBuilder.buildSplit(8, 500))
        pulaLoc.addAlternativeStore(storeBuilder.buildDubrovnikKristijan(12, 500))
        pulaLoc.addAlternativeStore(storeBuilder.buildDubrovnikBWA(12, 500))
        pulaLoc.addAlternativeStore(storeBuilder.buildZadar(8, 500))

        pulaLoc.addGrayStore(storeBuilder.buildAntibes(96))
        pulaLoc.addGrayStore(storeBuilder.buildPalmaED(120))
        pulaLoc.addGrayStore(storeBuilder.buildPalmaNG(120))
        pulaLoc.addGrayStore(storeBuilder.buildIbiza(120))
        pulaLoc.addGrayStore(storeBuilder.buildOlbiaNA(120))
        pulaLoc.addGrayStore(storeBuilder.buildOlbiaSYS(120))
        pulaLoc.addGrayStore(storeBuilder.buildGenova(72))
        pulaLoc.addGrayStore(storeBuilder.buildNapoli(96))
        pulaLoc.addGrayStore(storeBuilder.buildPalermo(144))
        pulaLoc.addGrayStore(storeBuilder.buildRiposto(144))
        pulaLoc.addGrayStore(storeBuilder.buildMalta(216))
        pulaLoc.addGrayStore(storeBuilder.buildAthensSotiris(120))
        pulaLoc.addGrayStore(storeBuilder.buildAthensH360(120))
        pulaLoc.addGrayStore(storeBuilder.buildCorfu(168))
        pulaLoc.addGrayStore(storeBuilder.buildKosA1(168))
        pulaLoc.addGrayStore(storeBuilder.buildRhodes(168))
        pulaLoc.addGrayStore(storeBuilder.buildBCM(72))
        pulaLoc.addGrayStore(storeBuilder.buildTivat(72))
        pulaLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(24))

        pula.addLocation(pulaLoc)
        regions.add(pula)


        // Zadar
        val zadar = Region("Zadar",75)

        val zadarLoc = Location("Zadar")

        zadarLoc.addNativeStore(storeBuilder.buildZadar(2, 50))
        zadarLoc.addNativeStore(storeBuilder.buildSplit(4, 50))
        zadarLoc.addAlternativeStore(storeBuilder.buildDubrovnikKristijan(8, 50))
        zadarLoc.addAlternativeStore(storeBuilder.buildDubrovnikBWA(8, 50))
        zadarLoc.addAlternativeStore(storeBuilder.buildM55(8, 350))

        zadarLoc.addGrayStore(storeBuilder.buildAntibes(120))
        zadarLoc.addGrayStore(storeBuilder.buildPalmaED(120))
        zadarLoc.addGrayStore(storeBuilder.buildPalmaNG(120))
        zadarLoc.addGrayStore(storeBuilder.buildIbiza(120))
        zadarLoc.addGrayStore(storeBuilder.buildOlbiaNA(144))
        zadarLoc.addGrayStore(storeBuilder.buildOlbiaSYS(144))
        zadarLoc.addGrayStore(storeBuilder.buildGenova(96))
        zadarLoc.addGrayStore(storeBuilder.buildNapoli(120))
        zadarLoc.addGrayStore(storeBuilder.buildPalermo(168))
        zadarLoc.addGrayStore(storeBuilder.buildRiposto(168))
        zadarLoc.addGrayStore(storeBuilder.buildMalta(216))
        zadarLoc.addGrayStore(storeBuilder.buildAthensSotiris(144))
        zadarLoc.addGrayStore(storeBuilder.buildAthensH360(144))
        zadarLoc.addGrayStore(storeBuilder.buildCorfu(192))
        zadarLoc.addGrayStore(storeBuilder.buildKosA1(192))
        zadarLoc.addGrayStore(storeBuilder.buildRhodes(192))
        zadarLoc.addGrayStore(storeBuilder.buildVenice(96))
        zadarLoc.addGrayStore(storeBuilder.buildBCM(72))
        zadarLoc.addGrayStore(storeBuilder.buildTivat(72))
        zadarLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(24))

        zadar.addLocation(zadarLoc)
        regions.add(zadar)


        // Sibenik
        val sibenik = Region("Sibenik",76)

        val sibenikLoc = Location("Sibenik")

        sibenikLoc.addNativeStore(storeBuilder.buildZadar(3, 80))
        sibenikLoc.addNativeStore(storeBuilder.buildSplit(3, 80))
        sibenikLoc.addAlternativeStore(storeBuilder.buildDubrovnikKristijan(8, 80))
        sibenikLoc.addAlternativeStore(storeBuilder.buildDubrovnikBWA(8, 80))
        sibenikLoc.addAlternativeStore(storeBuilder.buildM55(8, 400))

        sibenikLoc.addGrayStore(storeBuilder.buildAntibes(120))
        sibenikLoc.addGrayStore(storeBuilder.buildPalmaED(120))
        sibenikLoc.addGrayStore(storeBuilder.buildPalmaNG(120))
        sibenikLoc.addGrayStore(storeBuilder.buildIbiza(120))
        sibenikLoc.addGrayStore(storeBuilder.buildOlbiaNA(144))
        sibenikLoc.addGrayStore(storeBuilder.buildOlbiaSYS(144))
        sibenikLoc.addGrayStore(storeBuilder.buildGenova(96))
        sibenikLoc.addGrayStore(storeBuilder.buildNapoli(120))
        sibenikLoc.addGrayStore(storeBuilder.buildPalermo(168))
        sibenikLoc.addGrayStore(storeBuilder.buildRiposto(168))
        sibenikLoc.addGrayStore(storeBuilder.buildMalta(216))
        sibenikLoc.addGrayStore(storeBuilder.buildAthensSotiris(144))
        sibenikLoc.addGrayStore(storeBuilder.buildAthensH360(144))
        sibenikLoc.addGrayStore(storeBuilder.buildCorfu(192))
        sibenikLoc.addGrayStore(storeBuilder.buildKosA1(192))
        sibenikLoc.addGrayStore(storeBuilder.buildRhodes(192))
        sibenikLoc.addGrayStore(storeBuilder.buildVenice(96))
        sibenikLoc.addGrayStore(storeBuilder.buildBCM(72))
        sibenikLoc.addGrayStore(storeBuilder.buildTivat(72))
        sibenikLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(24))

        sibenik.addLocation(sibenikLoc)
        regions.add(sibenik)


        // Trogir
        val trogir = Region("Trogir",77)

        val trogirLoc = Location("Trogir")

        trogirLoc.addNativeStore(storeBuilder.buildZadar(4, 150))
        trogirLoc.addNativeStore(storeBuilder.buildSplit(2, 150))
        trogirLoc.addAlternativeStore(storeBuilder.buildDubrovnikKristijan(8, 150))
        trogirLoc.addAlternativeStore(storeBuilder.buildDubrovnikBWA(8, 150))
        trogirLoc.addAlternativeStore(storeBuilder.buildM55(10, 440))

        trogirLoc.addGrayStore(storeBuilder.buildAntibes(120))
        trogirLoc.addGrayStore(storeBuilder.buildPalmaED(120))
        trogirLoc.addGrayStore(storeBuilder.buildPalmaNG(120))
        trogirLoc.addGrayStore(storeBuilder.buildIbiza(120))
        trogirLoc.addGrayStore(storeBuilder.buildOlbiaNA(144))
        trogirLoc.addGrayStore(storeBuilder.buildOlbiaSYS(144))
        trogirLoc.addGrayStore(storeBuilder.buildGenova(96))
        trogirLoc.addGrayStore(storeBuilder.buildNapoli(120))
        trogirLoc.addGrayStore(storeBuilder.buildPalermo(168))
        trogirLoc.addGrayStore(storeBuilder.buildRiposto(168))
        trogirLoc.addGrayStore(storeBuilder.buildMalta(216))
        trogirLoc.addGrayStore(storeBuilder.buildAthensSotiris(144))
        trogirLoc.addGrayStore(storeBuilder.buildAthensH360(144))
        trogirLoc.addGrayStore(storeBuilder.buildCorfu(192))
        trogirLoc.addGrayStore(storeBuilder.buildKosA1(192))
        trogirLoc.addGrayStore(storeBuilder.buildRhodes(192))
        trogirLoc.addGrayStore(storeBuilder.buildVenice(96))
        trogirLoc.addGrayStore(storeBuilder.buildBCM(72))
        trogirLoc.addGrayStore(storeBuilder.buildTivat(72))
        trogirLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(24))

        trogir.addLocation(trogirLoc)
        regions.add(trogir)

        
        // Split
        val split = Region("Split",78)

        val splitLoc = Location("Split")

        splitLoc.addNativeStore(storeBuilder.buildZadar(4, 180))
        splitLoc.addNativeStore(storeBuilder.buildSplit(2, 180))
        splitLoc.addAlternativeStore(storeBuilder.buildDubrovnikKristijan(8, 180))
        splitLoc.addAlternativeStore(storeBuilder.buildDubrovnikBWA(8, 180))
        splitLoc.addAlternativeStore(storeBuilder.buildM55(10, 470))

        splitLoc.addGrayStore(storeBuilder.buildAntibes(120))
        splitLoc.addGrayStore(storeBuilder.buildPalmaED(120))
        splitLoc.addGrayStore(storeBuilder.buildPalmaNG(120))
        splitLoc.addGrayStore(storeBuilder.buildIbiza(120))
        splitLoc.addGrayStore(storeBuilder.buildOlbiaNA(144))
        splitLoc.addGrayStore(storeBuilder.buildOlbiaSYS(144))
        splitLoc.addGrayStore(storeBuilder.buildGenova(96))
        splitLoc.addGrayStore(storeBuilder.buildNapoli(120))
        splitLoc.addGrayStore(storeBuilder.buildPalermo(168))
        splitLoc.addGrayStore(storeBuilder.buildRiposto(168))
        splitLoc.addGrayStore(storeBuilder.buildMalta(216))
        splitLoc.addGrayStore(storeBuilder.buildAthensSotiris(144))
        splitLoc.addGrayStore(storeBuilder.buildAthensH360(144))
        splitLoc.addGrayStore(storeBuilder.buildCorfu(192))
        splitLoc.addGrayStore(storeBuilder.buildKosA1(192))
        splitLoc.addGrayStore(storeBuilder.buildRhodes(192))
        splitLoc.addGrayStore(storeBuilder.buildVenice(96))
        splitLoc.addGrayStore(storeBuilder.buildBCM(72))
        splitLoc.addGrayStore(storeBuilder.buildTivat(72))
        splitLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(24))
        
        split.addLocation(splitLoc)
        regions.add(split)


        // Hvar
        val hvar = Region("Hvar",79)

        val hvarLoc = Location("Hvar")

        hvarLoc.addNativeStore(storeBuilder.buildZadar(12, 300))
        hvarLoc.addNativeStore(storeBuilder.buildSplit(12, 300))
        hvarLoc.addAlternativeStore(storeBuilder.buildDubrovnikKristijan(24, 300))
        hvarLoc.addAlternativeStore(storeBuilder.buildDubrovnikBWA(24, 300))
        hvarLoc.addAlternativeStore(storeBuilder.buildM55(24, 600))

        hvarLoc.addGrayStore(storeBuilder.buildAntibes(144))
        hvarLoc.addGrayStore(storeBuilder.buildPalmaED(144))
        hvarLoc.addGrayStore(storeBuilder.buildPalmaNG(144))
        hvarLoc.addGrayStore(storeBuilder.buildIbiza(144))
        hvarLoc.addGrayStore(storeBuilder.buildOlbiaNA(168))
        hvarLoc.addGrayStore(storeBuilder.buildOlbiaSYS(168))
        hvarLoc.addGrayStore(storeBuilder.buildGenova(144))
        hvarLoc.addGrayStore(storeBuilder.buildNapoli(168))
        hvarLoc.addGrayStore(storeBuilder.buildPalermo(192))
        hvarLoc.addGrayStore(storeBuilder.buildRiposto(192))
        hvarLoc.addGrayStore(storeBuilder.buildMalta(216))
        hvarLoc.addGrayStore(storeBuilder.buildAthensSotiris(192))
        hvarLoc.addGrayStore(storeBuilder.buildAthensH360(192))
        hvarLoc.addGrayStore(storeBuilder.buildCorfu(216))
        hvarLoc.addGrayStore(storeBuilder.buildKosA1(216))
        hvarLoc.addGrayStore(storeBuilder.buildRhodes(216))
        hvarLoc.addGrayStore(storeBuilder.buildVenice(144))
        hvarLoc.addGrayStore(storeBuilder.buildBCM(96))
        hvarLoc.addGrayStore(storeBuilder.buildTivat(96))
        hvarLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(72))
        
        hvar.addLocation(hvarLoc)
        regions.add(hvar)


        // Dubrovnik
        val dubrovnik = Region("Dubrovnik",80)

        val dubrovnikLoc = Location("Dubrovnik")

        dubrovnikLoc.addNativeStore(storeBuilder.buildDubrovnikKristijan(2, 450))
        dubrovnikLoc.addNativeStore(storeBuilder.buildDubrovnikBWA(2, 450))
        dubrovnikLoc.addAlternativeStore(storeBuilder.buildZadar(8, 450))
        dubrovnikLoc.addAlternativeStore(storeBuilder.buildSplit(6, 450))
        dubrovnikLoc.addAlternativeStore(storeBuilder.buildM55(18, 680))
        dubrovnikLoc.addAlternativeStore(storeBuilder.buildBCM(4, 200))
        dubrovnikLoc.addAlternativeStore(storeBuilder.buildTivat(6, 200))

        dubrovnikLoc.addGrayStore(storeBuilder.buildAntibes(144))
        dubrovnikLoc.addGrayStore(storeBuilder.buildPalmaED(144))
        dubrovnikLoc.addGrayStore(storeBuilder.buildPalmaNG(144))
        dubrovnikLoc.addGrayStore(storeBuilder.buildIbiza(144))
        dubrovnikLoc.addGrayStore(storeBuilder.buildOlbiaNA(168))
        dubrovnikLoc.addGrayStore(storeBuilder.buildOlbiaSYS(168))
        dubrovnikLoc.addGrayStore(storeBuilder.buildGenova(120))
        dubrovnikLoc.addGrayStore(storeBuilder.buildNapoli(144))
        dubrovnikLoc.addGrayStore(storeBuilder.buildPalermo(168))
        dubrovnikLoc.addGrayStore(storeBuilder.buildRiposto(168))
        dubrovnikLoc.addGrayStore(storeBuilder.buildMalta(216))
        dubrovnikLoc.addGrayStore(storeBuilder.buildAthensSotiris(144))
        dubrovnikLoc.addGrayStore(storeBuilder.buildAthensH360(144))
        dubrovnikLoc.addGrayStore(storeBuilder.buildCorfu(144))
        dubrovnikLoc.addGrayStore(storeBuilder.buildKosA1(192))
        dubrovnikLoc.addGrayStore(storeBuilder.buildRhodes(192))
        dubrovnikLoc.addGrayStore(storeBuilder.buildVenice(120))
        dubrovnikLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(48))

        dubrovnik.addLocation(dubrovnikLoc)
        regions.add(dubrovnik)


        // Cavtat
        val cavtat = Region("Cavtat",81)

        val cavtatLoc = Location("Cavtat")

        cavtatLoc.addNativeStore(storeBuilder.buildDubrovnikKristijan(2, 500))
        cavtatLoc.addNativeStore(storeBuilder.buildDubrovnikBWA(2, 500))
        cavtatLoc.addAlternativeStore(storeBuilder.buildZadar(8, 500))
        cavtatLoc.addAlternativeStore(storeBuilder.buildSplit(6, 500))
        cavtatLoc.addAlternativeStore(storeBuilder.buildM55(18, 680))
        cavtatLoc.addAlternativeStore(storeBuilder.buildBCM(4, 200))
        cavtatLoc.addAlternativeStore(storeBuilder.buildTivat(6, 200))

        cavtatLoc.addGrayStore(storeBuilder.buildAntibes(144))
        cavtatLoc.addGrayStore(storeBuilder.buildPalmaED(144))
        cavtatLoc.addGrayStore(storeBuilder.buildPalmaNG(144))
        cavtatLoc.addGrayStore(storeBuilder.buildIbiza(144))
        cavtatLoc.addGrayStore(storeBuilder.buildOlbiaNA(168))
        cavtatLoc.addGrayStore(storeBuilder.buildOlbiaSYS(168))
        cavtatLoc.addGrayStore(storeBuilder.buildGenova(120))
        cavtatLoc.addGrayStore(storeBuilder.buildNapoli(144))
        cavtatLoc.addGrayStore(storeBuilder.buildPalermo(168))
        cavtatLoc.addGrayStore(storeBuilder.buildRiposto(168))
        cavtatLoc.addGrayStore(storeBuilder.buildMalta(216))
        cavtatLoc.addGrayStore(storeBuilder.buildAthensSotiris(144))
        cavtatLoc.addGrayStore(storeBuilder.buildAthensH360(144))
        cavtatLoc.addGrayStore(storeBuilder.buildCorfu(144))
        cavtatLoc.addGrayStore(storeBuilder.buildKosA1(192))
        cavtatLoc.addGrayStore(storeBuilder.buildRhodes(192))
        cavtatLoc.addGrayStore(storeBuilder.buildVenice(120))
        cavtatLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(48))
        
        cavtat.addLocation(cavtatLoc)
        regions.add(cavtat)

        
        // Kotor
        val kotor = Region("Kotor",82)

        val kotorLoc = Location("Kotor")

        kotorLoc.addNativeStore(storeBuilder.buildBCM(1, 50))
        kotorLoc.addNativeStore(storeBuilder.buildTivat(1, 70))
        kotorLoc.addAlternativeStore(storeBuilder.buildDubrovnikKristijan(4, 700))
        kotorLoc.addAlternativeStore(storeBuilder.buildDubrovnikBWA(4, 700))
        kotorLoc.addAlternativeStore(storeBuilder.buildZadar(12, 700))
        kotorLoc.addAlternativeStore(storeBuilder.buildSplit(12, 700))
        kotorLoc.addAlternativeStore(storeBuilder.buildM55(24, 900))

        kotorLoc.addGrayStore(storeBuilder.buildAntibes(144))
        kotorLoc.addGrayStore(storeBuilder.buildPalmaED(168))
        kotorLoc.addGrayStore(storeBuilder.buildPalmaNG(168))
        kotorLoc.addGrayStore(storeBuilder.buildIbiza(168))
        kotorLoc.addGrayStore(storeBuilder.buildOlbiaNA(168))
        kotorLoc.addGrayStore(storeBuilder.buildOlbiaSYS(168))
        kotorLoc.addGrayStore(storeBuilder.buildGenova(144))
        kotorLoc.addGrayStore(storeBuilder.buildNapoli(168))
        kotorLoc.addGrayStore(storeBuilder.buildPalermo(192))
        kotorLoc.addGrayStore(storeBuilder.buildRiposto(192))
        kotorLoc.addGrayStore(storeBuilder.buildMalta(216))
        kotorLoc.addGrayStore(storeBuilder.buildAthensSotiris(144))
        kotorLoc.addGrayStore(storeBuilder.buildAthensH360(144))
        kotorLoc.addGrayStore(storeBuilder.buildCorfu(144))
        kotorLoc.addGrayStore(storeBuilder.buildKosA1(192))
        kotorLoc.addGrayStore(storeBuilder.buildRhodes(192))
        kotorLoc.addGrayStore(storeBuilder.buildVenice(144))
        kotorLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(72))
        
        kotor.addLocation(kotorLoc)
        regions.add(kotor)


        // Tivat
        val tivat = Region("Tivat",83)

        val tivatLoc = Location("Tivat")

        tivatLoc.addNativeStore(storeBuilder.buildBCM(2, 70))
        tivatLoc.addNativeStore(storeBuilder.buildTivat(2, 50))
        tivatLoc.addAlternativeStore(storeBuilder.buildDubrovnikKristijan(6, 750))
        tivatLoc.addAlternativeStore(storeBuilder.buildDubrovnikBWA(6, 750))
        tivatLoc.addAlternativeStore(storeBuilder.buildZadar(12, 750))
        tivatLoc.addAlternativeStore(storeBuilder.buildSplit(12, 750))
        tivatLoc.addAlternativeStore(storeBuilder.buildM55(24, 900))

        tivatLoc.addGrayStore(storeBuilder.buildAntibes(144))
        tivatLoc.addGrayStore(storeBuilder.buildPalmaED(168))
        tivatLoc.addGrayStore(storeBuilder.buildPalmaNG(168))
        tivatLoc.addGrayStore(storeBuilder.buildIbiza(168))
        tivatLoc.addGrayStore(storeBuilder.buildOlbiaNA(168))
        tivatLoc.addGrayStore(storeBuilder.buildOlbiaSYS(168))
        tivatLoc.addGrayStore(storeBuilder.buildGenova(144))
        tivatLoc.addGrayStore(storeBuilder.buildNapoli(168))
        tivatLoc.addGrayStore(storeBuilder.buildPalermo(192))
        tivatLoc.addGrayStore(storeBuilder.buildRiposto(192))
        tivatLoc.addGrayStore(storeBuilder.buildMalta(216))
        tivatLoc.addGrayStore(storeBuilder.buildAthensSotiris(144))
        tivatLoc.addGrayStore(storeBuilder.buildAthensH360(144))
        tivatLoc.addGrayStore(storeBuilder.buildCorfu(144))
        tivatLoc.addGrayStore(storeBuilder.buildKosA1(192))
        tivatLoc.addGrayStore(storeBuilder.buildRhodes(192))
        tivatLoc.addGrayStore(storeBuilder.buildVenice(144))
        tivatLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(72))
        
        tivat.addLocation(tivatLoc)
        regions.add(tivat)

        return regions.toList()
    }

    override fun buildAlbania(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        // Sarande
        val sarande = Region("Sarande",84)

        val sarandeLoc = Location("Sarande")

        sarandeLoc.addNativeStore(storeBuilder.buildBCM(12, 400))
        sarandeLoc.addNativeStore(storeBuilder.buildTivat(12, 400))
        sarandeLoc.addAlternativeStore(storeBuilder.buildCorfu(168, 1200))
        sarandeLoc.addAlternativeStore(storeBuilder.buildAthensSotiris(168, 1600))
        sarandeLoc.addAlternativeStore(storeBuilder.buildAthensH360(168, 1600))
        sarandeLoc.addAlternativeStore(storeBuilder.buildM55(48, 2000))

        sarandeLoc.addGrayStore(storeBuilder.buildAntibes(300))
        sarandeLoc.addGrayStore(storeBuilder.buildPalmaED(300))
        sarandeLoc.addGrayStore(storeBuilder.buildPalmaNG(300))
        sarandeLoc.addGrayStore(storeBuilder.buildIbiza(300))
        sarandeLoc.addGrayStore(storeBuilder.buildOlbiaNA(300))
        sarandeLoc.addGrayStore(storeBuilder.buildOlbiaSYS(300))
        sarandeLoc.addGrayStore(storeBuilder.buildGenova(300))
        sarandeLoc.addGrayStore(storeBuilder.buildNapoli(300))
        sarandeLoc.addGrayStore(storeBuilder.buildPalermo(300))
        sarandeLoc.addGrayStore(storeBuilder.buildRiposto(300))
        sarandeLoc.addGrayStore(storeBuilder.buildMalta(300))
        sarandeLoc.addGrayStore(storeBuilder.buildKosA1(300))
        sarandeLoc.addGrayStore(storeBuilder.buildRhodes(300))
        sarandeLoc.addGrayStore(storeBuilder.buildVenice(300))
        sarandeLoc.addGrayStore(storeBuilder.buildZadar(300))
        sarandeLoc.addGrayStore(storeBuilder.buildSplit(300))
        sarandeLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(300))
        sarandeLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(300))
        sarandeLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(300))
        
        sarande.addLocation(sarandeLoc)
        regions.add(sarande)

        return regions.toList()
    }

    override fun buildGreeceIonian(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        /**
         * Corfu
         */
        val corfu = Region("Corfu",85)

        val corfuLoc = Location("Corfu")

        corfuLoc.addNativeStore(storeBuilder.buildCorfu(3, 100))
        corfuLoc.addAlternativeStore(storeBuilder.buildAthensSotiris(24, 250))
        corfuLoc.addAlternativeStore(storeBuilder.buildAthensH360(24, 250))
        corfuLoc.addAlternativeStore(storeBuilder.buildM55(26, 2198))

        corfuLoc.addGrayStore(storeBuilder.buildAntibes(216))
        corfuLoc.addGrayStore(storeBuilder.buildPalmaED(216))
        corfuLoc.addGrayStore(storeBuilder.buildPalmaNG(216))
        corfuLoc.addGrayStore(storeBuilder.buildIbiza(216))
        corfuLoc.addGrayStore(storeBuilder.buildOlbiaNA(216))
        corfuLoc.addGrayStore(storeBuilder.buildOlbiaSYS(216))
        corfuLoc.addGrayStore(storeBuilder.buildGenova(192))
        corfuLoc.addGrayStore(storeBuilder.buildNapoli(192))
        corfuLoc.addGrayStore(storeBuilder.buildPalermo(216))
        corfuLoc.addGrayStore(storeBuilder.buildRiposto(216))
        corfuLoc.addGrayStore(storeBuilder.buildMalta(216))
        corfuLoc.addGrayStore(storeBuilder.buildKosA1(216))
        corfuLoc.addGrayStore(storeBuilder.buildRhodes(216))
        corfuLoc.addGrayStore(storeBuilder.buildVenice(168))
        corfuLoc.addGrayStore(storeBuilder.buildZadar(192))
        corfuLoc.addGrayStore(storeBuilder.buildSplit(192))
        corfuLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(192))
        corfuLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(192))
        corfuLoc.addGrayStore(storeBuilder.buildBCM(192))
        corfuLoc.addGrayStore(storeBuilder.buildTivat(192))
        corfuLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(168))

        corfu.addLocation(corfuLoc)
        regions.add(corfu)


        // Zakynthos
        val zakynthos = Region("Zakynthos",86)

        val zakynthosLoc = Location("Zakynthos")

        zakynthosLoc.addNativeStore(storeBuilder.buildCorfu(48, 250))
        zakynthosLoc.addAlternativeStore(storeBuilder.buildAthensSotiris(36, 270))
        zakynthosLoc.addAlternativeStore(storeBuilder.buildAthensH360(36, 270))
        zakynthosLoc.addAlternativeStore(storeBuilder.buildM55(34, 2198))

        zakynthosLoc.addGrayStore(storeBuilder.buildAntibes(216))
        zakynthosLoc.addGrayStore(storeBuilder.buildPalmaED(216))
        zakynthosLoc.addGrayStore(storeBuilder.buildPalmaNG(216))
        zakynthosLoc.addGrayStore(storeBuilder.buildIbiza(216))
        zakynthosLoc.addGrayStore(storeBuilder.buildOlbiaNA(216))
        zakynthosLoc.addGrayStore(storeBuilder.buildOlbiaSYS(216))
        zakynthosLoc.addGrayStore(storeBuilder.buildGenova(216))
        zakynthosLoc.addGrayStore(storeBuilder.buildNapoli(216))
        zakynthosLoc.addGrayStore(storeBuilder.buildPalermo(216))
        zakynthosLoc.addGrayStore(storeBuilder.buildRiposto(216))
        zakynthosLoc.addGrayStore(storeBuilder.buildMalta(216))
        zakynthosLoc.addGrayStore(storeBuilder.buildKosA1(216))
        zakynthosLoc.addGrayStore(storeBuilder.buildRhodes(216))
        zakynthosLoc.addGrayStore(storeBuilder.buildVenice(192))
        zakynthosLoc.addGrayStore(storeBuilder.buildZadar(216))
        zakynthosLoc.addGrayStore(storeBuilder.buildSplit(216))
        zakynthosLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(216))
        zakynthosLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(216))
        zakynthosLoc.addGrayStore(storeBuilder.buildBCM(216))
        zakynthosLoc.addGrayStore(storeBuilder.buildTivat(216))
        zakynthosLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(192))

        zakynthos.addLocation(zakynthosLoc)
        regions.add(zakynthos)


        // Kefalonia
        val kefalonia = Region("Kefalonia",87)

        val kefaloniaLoc = Location("Kefalonia")

        kefaloniaLoc.addNativeStore(storeBuilder.buildCorfu(48, 250))
        kefaloniaLoc.addAlternativeStore(storeBuilder.buildAthensSotiris(36, 270))
        kefaloniaLoc.addAlternativeStore(storeBuilder.buildAthensH360(36, 270))
        kefaloniaLoc.addAlternativeStore(storeBuilder.buildM55(35, 2213))

        kefaloniaLoc.addGrayStore(storeBuilder.buildAntibes(216))
        kefaloniaLoc.addGrayStore(storeBuilder.buildPalmaED(216))
        kefaloniaLoc.addGrayStore(storeBuilder.buildPalmaNG(216))
        kefaloniaLoc.addGrayStore(storeBuilder.buildIbiza(216))
        kefaloniaLoc.addGrayStore(storeBuilder.buildOlbiaNA(216))
        kefaloniaLoc.addGrayStore(storeBuilder.buildOlbiaSYS(216))
        kefaloniaLoc.addGrayStore(storeBuilder.buildGenova(216))
        kefaloniaLoc.addGrayStore(storeBuilder.buildNapoli(216))
        kefaloniaLoc.addGrayStore(storeBuilder.buildPalermo(216))
        kefaloniaLoc.addGrayStore(storeBuilder.buildRiposto(216))
        kefaloniaLoc.addGrayStore(storeBuilder.buildMalta(216))
        kefaloniaLoc.addGrayStore(storeBuilder.buildKosA1(216))
        kefaloniaLoc.addGrayStore(storeBuilder.buildRhodes(216))
        kefaloniaLoc.addGrayStore(storeBuilder.buildVenice(192))
        kefaloniaLoc.addGrayStore(storeBuilder.buildZadar(216))
        kefaloniaLoc.addGrayStore(storeBuilder.buildSplit(216))
        kefaloniaLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(216))
        kefaloniaLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(216))
        kefaloniaLoc.addGrayStore(storeBuilder.buildBCM(216))
        kefaloniaLoc.addGrayStore(storeBuilder.buildTivat(216))
        kefaloniaLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(192))
        
        kefalonia.addLocation(kefaloniaLoc)
        regions.add(kefalonia)

        return regions.toList()
    }

    override fun buildGreeceEagen(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        /**
         * Greece (Eagen)
         */
        val athens = Region("Athens",88)

        val athensLoc = Location("Athens")

        athensLoc.addNativeStore(storeBuilder.buildAthensSotiris(2, 50))
        athensLoc.addNativeStore(storeBuilder.buildAthensH360(2, 50))
        athensLoc.addAlternativeStore(storeBuilder.buildCorfu(24, 50))
        athensLoc.addAlternativeStore(storeBuilder.buildM55(120, 1800))

        athensLoc.addGrayStore(storeBuilder.buildAntibes(168))
        athensLoc.addGrayStore(storeBuilder.buildPalmaED(168))
        athensLoc.addGrayStore(storeBuilder.buildPalmaNG(168))
        athensLoc.addGrayStore(storeBuilder.buildIbiza(168))
        athensLoc.addGrayStore(storeBuilder.buildOlbiaNA(168))
        athensLoc.addGrayStore(storeBuilder.buildOlbiaSYS(168))
        athensLoc.addGrayStore(storeBuilder.buildGenova(96))
        athensLoc.addGrayStore(storeBuilder.buildNapoli(96))
        athensLoc.addGrayStore(storeBuilder.buildPalermo(144))
        athensLoc.addGrayStore(storeBuilder.buildRiposto(144))
        athensLoc.addGrayStore(storeBuilder.buildMalta(192))
        athensLoc.addGrayStore(storeBuilder.buildKosA1(24))
        athensLoc.addGrayStore(storeBuilder.buildRhodes(24))
        athensLoc.addGrayStore(storeBuilder.buildVenice(96))
        athensLoc.addGrayStore(storeBuilder.buildZadar(96))
        athensLoc.addGrayStore(storeBuilder.buildSplit(96))
        athensLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(96))
        athensLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(96))
        athensLoc.addGrayStore(storeBuilder.buildBCM(96))
        athensLoc.addGrayStore(storeBuilder.buildTivat(96))
        athensLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(96))
        
        athens.addLocation(athensLoc)
        regions.add(athens)


        // Mikonos
        val mikonos = Region("Mikonos",89)

        val mikonosLoc = Location("Mikonos")

        mikonosLoc.addNativeStore(storeBuilder.buildAthensSotiris(24, 270))
        mikonosLoc.addNativeStore(storeBuilder.buildAthensH360(24, 270))

        mikonosLoc.addGrayStore(storeBuilder.buildM55(168))
        mikonosLoc.addGrayStore(storeBuilder.buildAntibes(216))
        mikonosLoc.addGrayStore(storeBuilder.buildPalmaED(216))
        mikonosLoc.addGrayStore(storeBuilder.buildPalmaNG(216))
        mikonosLoc.addGrayStore(storeBuilder.buildIbiza(216))
        mikonosLoc.addGrayStore(storeBuilder.buildOlbiaNA(216))
        mikonosLoc.addGrayStore(storeBuilder.buildOlbiaSYS(216))
        mikonosLoc.addGrayStore(storeBuilder.buildGenova(192))
        mikonosLoc.addGrayStore(storeBuilder.buildNapoli(192))
        mikonosLoc.addGrayStore(storeBuilder.buildPalermo(216))
        mikonosLoc.addGrayStore(storeBuilder.buildRiposto(216))
        mikonosLoc.addGrayStore(storeBuilder.buildMalta(216))
        mikonosLoc.addGrayStore(storeBuilder.buildCorfu(216))
        mikonosLoc.addGrayStore(storeBuilder.buildKosA1(96))
        mikonosLoc.addGrayStore(storeBuilder.buildRhodes(96))
        mikonosLoc.addGrayStore(storeBuilder.buildVenice(144))
        mikonosLoc.addGrayStore(storeBuilder.buildZadar(144))
        mikonosLoc.addGrayStore(storeBuilder.buildSplit(144))
        mikonosLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(144))
        mikonosLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(144))
        mikonosLoc.addGrayStore(storeBuilder.buildBCM(216))
        mikonosLoc.addGrayStore(storeBuilder.buildTivat(216))
        mikonosLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(144))
        
        mikonos.addLocation(mikonosLoc)
        regions.add(mikonos)


        // Santorini
        val santorini = Region("Santorini",90)

        val santoriniLoc = Location("Santorini")

        santoriniLoc.addNativeStore(storeBuilder.buildAthensSotiris(24, 270))
        santoriniLoc.addNativeStore(storeBuilder.buildAthensH360(24, 270))

        santoriniLoc.addGrayStore(storeBuilder.buildM55(168))
        santoriniLoc.addGrayStore(storeBuilder.buildAntibes(216))
        santoriniLoc.addGrayStore(storeBuilder.buildPalmaED(216))
        santoriniLoc.addGrayStore(storeBuilder.buildPalmaNG(216))
        santoriniLoc.addGrayStore(storeBuilder.buildIbiza(216))
        santoriniLoc.addGrayStore(storeBuilder.buildOlbiaNA(216))
        santoriniLoc.addGrayStore(storeBuilder.buildOlbiaSYS(216))
        santoriniLoc.addGrayStore(storeBuilder.buildGenova(192))
        santoriniLoc.addGrayStore(storeBuilder.buildNapoli(192))
        santoriniLoc.addGrayStore(storeBuilder.buildPalermo(216))
        santoriniLoc.addGrayStore(storeBuilder.buildRiposto(216))
        santoriniLoc.addGrayStore(storeBuilder.buildMalta(216))
        santoriniLoc.addGrayStore(storeBuilder.buildCorfu(96))
        santoriniLoc.addGrayStore(storeBuilder.buildKosA1(96))
        santoriniLoc.addGrayStore(storeBuilder.buildRhodes(96))
        santoriniLoc.addGrayStore(storeBuilder.buildVenice(144))
        santoriniLoc.addGrayStore(storeBuilder.buildZadar(144))
        santoriniLoc.addGrayStore(storeBuilder.buildSplit(144))
        santoriniLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(144))
        santoriniLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(144))
        santoriniLoc.addGrayStore(storeBuilder.buildBCM(216))
        santoriniLoc.addGrayStore(storeBuilder.buildTivat(216))
        santoriniLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(144))

        santorini.addLocation(santoriniLoc)
        regions.add(santorini)


        // Rhodes
        val rhodes = Region("Rhodes",91)

        val rhodesLoc = Location("Rhodes")

        rhodesLoc.addNativeStore(storeBuilder.buildRhodes(2, 100))
        rhodesLoc.addNativeStore(storeBuilder.buildKosA1(24, 450))
        rhodesLoc.addAlternativeStore(storeBuilder.buildAthensSotiris(24, 300))
        rhodesLoc.addAlternativeStore(storeBuilder.buildAthensH360(24, 300))

        rhodesLoc.addGrayStore(storeBuilder.buildM55(168))
        rhodesLoc.addGrayStore(storeBuilder.buildAntibes(216))
        rhodesLoc.addGrayStore(storeBuilder.buildPalmaED(216))
        rhodesLoc.addGrayStore(storeBuilder.buildPalmaNG(216))
        rhodesLoc.addGrayStore(storeBuilder.buildIbiza(216))
        rhodesLoc.addGrayStore(storeBuilder.buildOlbiaNA(216))
        rhodesLoc.addGrayStore(storeBuilder.buildOlbiaSYS(216))
        rhodesLoc.addGrayStore(storeBuilder.buildGenova(192))
        rhodesLoc.addGrayStore(storeBuilder.buildNapoli(192))
        rhodesLoc.addGrayStore(storeBuilder.buildPalermo(216))
        rhodesLoc.addGrayStore(storeBuilder.buildRiposto(216))
        rhodesLoc.addGrayStore(storeBuilder.buildMalta(216))
        rhodesLoc.addGrayStore(storeBuilder.buildCorfu(96))
        rhodesLoc.addGrayStore(storeBuilder.buildVenice(144))
        rhodesLoc.addGrayStore(storeBuilder.buildZadar(144))
        rhodesLoc.addGrayStore(storeBuilder.buildSplit(144))
        rhodesLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(144))
        rhodesLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(144))
        rhodesLoc.addGrayStore(storeBuilder.buildBCM(216))
        rhodesLoc.addGrayStore(storeBuilder.buildTivat(216))
        rhodesLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(144))

        rhodes.addLocation(rhodesLoc)
        regions.add(rhodes)

        
        // Kos
        val kos = Region("Kos",92)

        val kosLoc = Location("Kos")

        kosLoc.addNativeStore(storeBuilder.buildRhodes(24, 200))
        kosLoc.addNativeStore(storeBuilder.buildKosA1(2, 100))
        kosLoc.addAlternativeStore(storeBuilder.buildAthensSotiris(48, 300))
        kosLoc.addAlternativeStore(storeBuilder.buildAthensH360(48, 300))

        kosLoc.addGrayStore(storeBuilder.buildM55(168))
        kosLoc.addGrayStore(storeBuilder.buildAntibes(216))
        kosLoc.addGrayStore(storeBuilder.buildPalmaED(216))
        kosLoc.addGrayStore(storeBuilder.buildPalmaNG(216))
        kosLoc.addGrayStore(storeBuilder.buildIbiza(216))
        kosLoc.addGrayStore(storeBuilder.buildOlbiaNA(216))
        kosLoc.addGrayStore(storeBuilder.buildOlbiaSYS(216))
        kosLoc.addGrayStore(storeBuilder.buildGenova(192))
        kosLoc.addGrayStore(storeBuilder.buildNapoli(192))
        kosLoc.addGrayStore(storeBuilder.buildPalermo(216))
        kosLoc.addGrayStore(storeBuilder.buildRiposto(216))
        kosLoc.addGrayStore(storeBuilder.buildMalta(216))
        kosLoc.addGrayStore(storeBuilder.buildCorfu(96))
        kosLoc.addGrayStore(storeBuilder.buildVenice(144))
        kosLoc.addGrayStore(storeBuilder.buildZadar(144))
        kosLoc.addGrayStore(storeBuilder.buildSplit(144))
        kosLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(144))
        kosLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(144))
        kosLoc.addGrayStore(storeBuilder.buildBCM(216))
        kosLoc.addGrayStore(storeBuilder.buildTivat(216))
        kosLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(144))
        
        kos.addLocation(kosLoc)
        regions.add(kos)

        return regions.toList()
    }

    override fun buildTurkey(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        // Bodrum
        val bodrum = Region("Bodrum",93)

        val bodrumLoc = Location("Bodrum")

        bodrumLoc.addNativeStore(storeBuilder.buildKosA1(2, 800))
        bodrumLoc.addAlternativeStore(storeBuilder.buildAthensSotiris(48, 800))
        bodrumLoc.addAlternativeStore(storeBuilder.buildAthensH360(48, 800))
        bodrumLoc.addAlternativeStore(storeBuilder.buildRhodes(24, 800))

        bodrumLoc.addGrayStore(storeBuilder.buildM55(700))
        bodrumLoc.addGrayStore(storeBuilder.buildAntibes(700))
        bodrumLoc.addGrayStore(storeBuilder.buildPalmaED(700))
        bodrumLoc.addGrayStore(storeBuilder.buildPalmaNG(700))
        bodrumLoc.addGrayStore(storeBuilder.buildIbiza(700))
        bodrumLoc.addGrayStore(storeBuilder.buildOlbiaNA(700))
        bodrumLoc.addGrayStore(storeBuilder.buildOlbiaSYS(700))
        bodrumLoc.addGrayStore(storeBuilder.buildGenova(700))
        bodrumLoc.addGrayStore(storeBuilder.buildNapoli(700))
        bodrumLoc.addGrayStore(storeBuilder.buildPalermo(700))
        bodrumLoc.addGrayStore(storeBuilder.buildRiposto(700))
        bodrumLoc.addGrayStore(storeBuilder.buildMalta(700))
        bodrumLoc.addGrayStore(storeBuilder.buildCorfu(700))
        bodrumLoc.addGrayStore(storeBuilder.buildVenice(700))
        bodrumLoc.addGrayStore(storeBuilder.buildZadar(700))
        bodrumLoc.addGrayStore(storeBuilder.buildSplit(700))
        bodrumLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(700))
        bodrumLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(700))
        bodrumLoc.addGrayStore(storeBuilder.buildBCM(700))
        bodrumLoc.addGrayStore(storeBuilder.buildTivat(700))
        bodrumLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(700))
        
        bodrum.addLocation(bodrumLoc)
        regions.add(bodrum)


        // Izmir
        val izmir = Region("Izmir",94)

        val izmirLoc = Location("Izmir")

        izmirLoc.addNativeStore(storeBuilder.buildKosA1(2, 800))
        izmirLoc.addAlternativeStore(storeBuilder.buildAthensSotiris(48, 800))
        izmirLoc.addAlternativeStore(storeBuilder.buildAthensH360(48, 800))
        izmirLoc.addAlternativeStore(storeBuilder.buildRhodes(24, 800))

        izmirLoc.addGrayStore(storeBuilder.buildM55(700))
        izmirLoc.addGrayStore(storeBuilder.buildAntibes(700))
        izmirLoc.addGrayStore(storeBuilder.buildPalmaED(700))
        izmirLoc.addGrayStore(storeBuilder.buildPalmaNG(700))
        izmirLoc.addGrayStore(storeBuilder.buildIbiza(700))
        izmirLoc.addGrayStore(storeBuilder.buildOlbiaNA(700))
        izmirLoc.addGrayStore(storeBuilder.buildOlbiaSYS(700))
        izmirLoc.addGrayStore(storeBuilder.buildGenova(700))
        izmirLoc.addGrayStore(storeBuilder.buildNapoli(700))
        izmirLoc.addGrayStore(storeBuilder.buildPalermo(700))
        izmirLoc.addGrayStore(storeBuilder.buildRiposto(700))
        izmirLoc.addGrayStore(storeBuilder.buildMalta(700))
        izmirLoc.addGrayStore(storeBuilder.buildCorfu(700))
        izmirLoc.addGrayStore(storeBuilder.buildVenice(700))
        izmirLoc.addGrayStore(storeBuilder.buildZadar(700))
        izmirLoc.addGrayStore(storeBuilder.buildSplit(700))
        izmirLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(700))
        izmirLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(700))
        izmirLoc.addGrayStore(storeBuilder.buildBCM(700))
        izmirLoc.addGrayStore(storeBuilder.buildTivat(700))
        izmirLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(700))

        izmir.addLocation(izmirLoc)
        regions.add(izmir)

        
        // Marmaris
        val marmaris = Region("Marmaris",95)

        val marmarisLoc = Location("Marmaris")

        marmarisLoc.addNativeStore(storeBuilder.buildKosA1(2, 800))
        marmarisLoc.addAlternativeStore(storeBuilder.buildAthensSotiris(48, 800))
        marmarisLoc.addAlternativeStore(storeBuilder.buildAthensH360(48, 800))
        marmarisLoc.addAlternativeStore(storeBuilder.buildRhodes(24, 800))

        marmarisLoc.addGrayStore(storeBuilder.buildM55(700))
        marmarisLoc.addGrayStore(storeBuilder.buildAntibes(700))
        marmarisLoc.addGrayStore(storeBuilder.buildPalmaED(700))
        marmarisLoc.addGrayStore(storeBuilder.buildPalmaNG(700))
        marmarisLoc.addGrayStore(storeBuilder.buildIbiza(700))
        marmarisLoc.addGrayStore(storeBuilder.buildOlbiaNA(700))
        marmarisLoc.addGrayStore(storeBuilder.buildOlbiaSYS(700))
        marmarisLoc.addGrayStore(storeBuilder.buildGenova(700))
        marmarisLoc.addGrayStore(storeBuilder.buildNapoli(700))
        marmarisLoc.addGrayStore(storeBuilder.buildPalermo(700))
        marmarisLoc.addGrayStore(storeBuilder.buildRiposto(700))
        marmarisLoc.addGrayStore(storeBuilder.buildMalta(700))
        marmarisLoc.addGrayStore(storeBuilder.buildCorfu(700))
        marmarisLoc.addGrayStore(storeBuilder.buildVenice(700))
        marmarisLoc.addGrayStore(storeBuilder.buildZadar(700))
        marmarisLoc.addGrayStore(storeBuilder.buildSplit(700))
        marmarisLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(700))
        marmarisLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(700))
        marmarisLoc.addGrayStore(storeBuilder.buildBCM(700))
        marmarisLoc.addGrayStore(storeBuilder.buildTivat(700))
        marmarisLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(700))
        
        marmaris.addLocation(marmarisLoc)
        regions.add(marmaris)


        // Antalya
        val antalya = Region("Antalya",96)

        val antalyaLoc = Location("Antalya")

        antalyaLoc.addNativeStore(storeBuilder.buildKosA1(2, 800))
        antalyaLoc.addAlternativeStore(storeBuilder.buildAthensSotiris(48, 800))
        antalyaLoc.addAlternativeStore(storeBuilder.buildAthensH360(48, 800))
        antalyaLoc.addAlternativeStore(storeBuilder.buildRhodes(24, 800))

        antalyaLoc.addGrayStore(storeBuilder.buildM55(700))
        antalyaLoc.addGrayStore(storeBuilder.buildAntibes(700))
        antalyaLoc.addGrayStore(storeBuilder.buildPalmaED(700))
        antalyaLoc.addGrayStore(storeBuilder.buildPalmaNG(700))
        antalyaLoc.addGrayStore(storeBuilder.buildIbiza(700))
        antalyaLoc.addGrayStore(storeBuilder.buildOlbiaNA(700))
        antalyaLoc.addGrayStore(storeBuilder.buildOlbiaSYS(700))
        antalyaLoc.addGrayStore(storeBuilder.buildGenova(700))
        antalyaLoc.addGrayStore(storeBuilder.buildNapoli(700))
        antalyaLoc.addGrayStore(storeBuilder.buildPalermo(700))
        antalyaLoc.addGrayStore(storeBuilder.buildRiposto(700))
        antalyaLoc.addGrayStore(storeBuilder.buildMalta(700))
        antalyaLoc.addGrayStore(storeBuilder.buildCorfu(700))
        antalyaLoc.addGrayStore(storeBuilder.buildVenice(700))
        antalyaLoc.addGrayStore(storeBuilder.buildZadar(700))
        antalyaLoc.addGrayStore(storeBuilder.buildSplit(700))
        antalyaLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(700))
        antalyaLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(700))
        antalyaLoc.addGrayStore(storeBuilder.buildBCM(700))
        antalyaLoc.addGrayStore(storeBuilder.buildTivat(700))
        antalyaLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(700))
        
        antalya.addLocation(antalyaLoc)
        regions.add(antalya)

        return regions.toList()
    }

    override fun buildCaribbean(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        // Saint Maarten
        val saintMaarten = Region("Saint Maarten",97)

        val antalyaLoc = Location("Antalya")

        antalyaLoc.addNativeStore(storeBuilder.buildKosA1(2, 800))
        antalyaLoc.addAlternativeStore(storeBuilder.buildAthensSotiris(48, 800))
        antalyaLoc.addAlternativeStore(storeBuilder.buildAthensH360(48, 800))
        antalyaLoc.addAlternativeStore(storeBuilder.buildRhodes(24, 800))

        antalyaLoc.addGrayStore(storeBuilder.buildM55(700))
        antalyaLoc.addGrayStore(storeBuilder.buildAntibes(700))
        antalyaLoc.addGrayStore(storeBuilder.buildPalmaED(700))
        antalyaLoc.addGrayStore(storeBuilder.buildPalmaNG(700))
        antalyaLoc.addGrayStore(storeBuilder.buildIbiza(700))
        antalyaLoc.addGrayStore(storeBuilder.buildOlbiaNA(700))
        antalyaLoc.addGrayStore(storeBuilder.buildOlbiaSYS(700))
        antalyaLoc.addGrayStore(storeBuilder.buildGenova(700))
        antalyaLoc.addGrayStore(storeBuilder.buildNapoli(700))
        antalyaLoc.addGrayStore(storeBuilder.buildPalermo(700))
        antalyaLoc.addGrayStore(storeBuilder.buildRiposto(700))
        antalyaLoc.addGrayStore(storeBuilder.buildMalta(700))
        antalyaLoc.addGrayStore(storeBuilder.buildCorfu(700))
        antalyaLoc.addGrayStore(storeBuilder.buildVenice(700))
        antalyaLoc.addGrayStore(storeBuilder.buildZadar(700))
        antalyaLoc.addGrayStore(storeBuilder.buildSplit(700))
        antalyaLoc.addGrayStore(storeBuilder.buildDubrovnikKristijan(700))
        antalyaLoc.addGrayStore(storeBuilder.buildDubrovnikBWA(700))
        antalyaLoc.addGrayStore(storeBuilder.buildBCM(700))
        antalyaLoc.addGrayStore(storeBuilder.buildTivat(700))
        antalyaLoc.addNewItemsStore(storeBuilder.buildNewItemsStore(700))

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
        allRegions.addAll(buildLiguriaItaly())
        allRegions.addAll(buildTuscanyItaly())
        allRegions.addAll(buildAmalfiItaly())
        allRegions.addAll(buildSouthOfItaly())
        allRegions.addAll(buildSicily())
        allRegions.addAll(buildMalta())
        allRegions.addAll(buildCorsica())
        allRegions.addAll(buildSardinia())
        allRegions.addAll(buildCroatiaAndMontenegro())
        allRegions.addAll(buildAlbania())
        allRegions.addAll(buildGreeceIonian())
        allRegions.addAll(buildGreeceEagen())
        allRegions.addAll(buildTurkey())
        allRegions.addAll(buildCaribbean())
        allRegions.addAll(buildFloridaAndBahamas())
        allRegions.addAll(buildMaldives())

        return allRegions
    }
}

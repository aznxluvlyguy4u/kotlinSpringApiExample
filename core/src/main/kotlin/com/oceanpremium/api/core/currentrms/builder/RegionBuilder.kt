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
        gibraltarLoc.alternativeStores.add(storeBuilder.buildAntibes(18, 1734))
        gibraltarLoc.grayStores.add(storeBuilder.buildM55(168))
        gibraltarLoc.grayStores.add(storeBuilder.buildPalmaED(72))
        gibraltarLoc.grayStores.add(storeBuilder.buildPalmaNG(72))
        gibraltarLoc.grayStores.add(storeBuilder.buildIbiza(96))
        gibraltarLoc.grayStores.add(storeBuilder.buildOlbiaNA(144))
        gibraltarLoc.grayStores.add(storeBuilder.buildOlbiaSYS(144))
        gibraltarLoc.grayStores.add(storeBuilder.buildGenova(144))
        gibraltarLoc.grayStores.add(storeBuilder.buildNapoli(168))
        gibraltarLoc.grayStores.add(storeBuilder.buildPalermo(216))
        gibraltarLoc.grayStores.add(storeBuilder.buildRiposto(216))
        gibraltarLoc.grayStores.add(storeBuilder.buildMalta(216))
        gibraltarLoc.grayStores.add(storeBuilder.buildAthensSotiris(192))
        gibraltarLoc.grayStores.add(storeBuilder.buildAthensH360(192))
        gibraltarLoc.grayStores.add(storeBuilder.buildCorfu(216))
        gibraltarLoc.grayStores.add(storeBuilder.buildKosA1(216))
        gibraltarLoc.grayStores.add(storeBuilder.buildRhodes(216))
        gibraltarLoc.grayStores.add(storeBuilder.buildVenice(168))
        gibraltarLoc.grayStores.add(storeBuilder.buildZadar(196))
        gibraltarLoc.grayStores.add(storeBuilder.buildSplit(196))
        gibraltarLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(196))
        gibraltarLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(196))
        gibraltarLoc.grayStores.add(storeBuilder.buildBCM(216))
        gibraltarLoc.grayStores.add(storeBuilder.buildTivat(216))
        gibraltarLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(144))
        gibraltar.addLocation(gibraltarLoc)

        regions.add(gibraltar)

        // Marbella
        val marbella = Region("Marbella", 2)

        val calaDeMijasLoc = Location("Cala de Mijas")
        val marbellaLoc = Location("Marbella")
        val laLacaidesaLoc = Location("La Lacaidesa")

        calaDeMijasLoc.alternativeStores.add(storeBuilder.buildAntibes(17, 1646))
        marbellaLoc.alternativeStores.add(storeBuilder.buildAntibes(17, 1659))
        laLacaidesaLoc.alternativeStores.add(storeBuilder.buildAntibes(17, 1721))

        marbella.addLocation(marbellaLoc)
        marbella.addLocation(calaDeMijasLoc)
        marbella.addLocation(laLacaidesaLoc)

        marbella.locations.forEach { location ->
            location.grayStores.add(storeBuilder.buildM55(168))
            location.grayStores.add(storeBuilder.buildPalmaED(96))
            location.grayStores.add(storeBuilder.buildPalmaNG(96))
            location.grayStores.add(storeBuilder.buildIbiza(96))
            location.grayStores.add(storeBuilder.buildOlbiaNA(144))
            location.grayStores.add(storeBuilder.buildOlbiaSYS(144))
            location.grayStores.add(storeBuilder.buildGenova(144))
            location.grayStores.add(storeBuilder.buildNapoli(168))
            location.grayStores.add(storeBuilder.buildPalermo(216))
            location.grayStores.add(storeBuilder.buildRiposto(216))
            location.grayStores.add(storeBuilder.buildMalta(216))
            location.grayStores.add(storeBuilder.buildAthensSotiris(192))
            location.grayStores.add(storeBuilder.buildAthensH360(192))
            location.grayStores.add(storeBuilder.buildCorfu(216))
            location.grayStores.add(storeBuilder.buildKosA1(216))
            location.grayStores.add(storeBuilder.buildRhodes(216))
            location.grayStores.add(storeBuilder.buildVenice(168))
            location.grayStores.add(storeBuilder.buildZadar(196))
            location.grayStores.add(storeBuilder.buildSplit(196))
            location.grayStores.add(storeBuilder.buildDubrovnikKristijan(196))
            location.grayStores.add(storeBuilder.buildDubrovnikBWA(196))
            location.grayStores.add(storeBuilder.buildBCM(216))
            location.grayStores.add(storeBuilder.buildTivat(216))
            location.newItemsStores.add(storeBuilder.buildNewItemsStore(144))
        }

        regions.add(marbella)

        // Malaga
        val malaga = Region("Malaga", 3)

        val fuengirolaLoc = Location("Fuengirola")
        val malagaLoc = Location("Malaga")
        val almunecarLoc = Location("Almunecar")

        fuengirolaLoc.alternativeStores.add(storeBuilder.buildAntibes(17, 1632))
        malagaLoc.alternativeStores.add(storeBuilder.buildAntibes(17, 1605))
        almunecarLoc.alternativeStores.add(storeBuilder.buildAntibes(16, 1566))

        malaga.addLocation(fuengirolaLoc)
        malaga.addLocation(malagaLoc)
        malaga.addLocation(almunecarLoc)

        malaga.locations.forEach { location ->
            location.grayStores.add(storeBuilder.buildM55(168))
            location.grayStores.add(storeBuilder.buildPalmaED(96))
            location.grayStores.add(storeBuilder.buildPalmaNG(96))
            location.grayStores.add(storeBuilder.buildIbiza(96))
            location.grayStores.add(storeBuilder.buildOlbiaNA(144))
            location.grayStores.add(storeBuilder.buildOlbiaSYS(144))
            location.grayStores.add(storeBuilder.buildGenova(144))
            location.grayStores.add(storeBuilder.buildNapoli(168))
            location.grayStores.add(storeBuilder.buildPalermo(216))
            location.grayStores.add(storeBuilder.buildRiposto(216))
            location.grayStores.add(storeBuilder.buildMalta(216))
            location.grayStores.add(storeBuilder.buildAthensSotiris(192))
            location.grayStores.add(storeBuilder.buildAthensH360(192))
            location.grayStores.add(storeBuilder.buildCorfu(216))
            location.grayStores.add(storeBuilder.buildKosA1(216))
            location.grayStores.add(storeBuilder.buildRhodes(216))
            location.grayStores.add(storeBuilder.buildVenice(168))
            location.grayStores.add(storeBuilder.buildZadar(196))
            location.grayStores.add(storeBuilder.buildSplit(196))
            location.grayStores.add(storeBuilder.buildDubrovnikKristijan(196))
            location.grayStores.add(storeBuilder.buildDubrovnikBWA(196))
            location.grayStores.add(storeBuilder.buildBCM(216))
            location.grayStores.add(storeBuilder.buildTivat(216))
            location.newItemsStores.add(storeBuilder.buildNewItemsStore(144))
        }

        regions.add(malaga)

        // Almeria
        val almeria = Region("Almeria", 4)

        val aguilasLoc = Location("Aguilas")
        val almeriaLoc = Location("Almeria")
        val mortilLoc = Location("Mortil")

        aguilasLoc.alternativeStores.add(storeBuilder.buildAntibes(14, 1315))
        almeriaLoc.alternativeStores.add(storeBuilder.buildAntibes(15, 1424))
        mortilLoc.alternativeStores.add(storeBuilder.buildAntibes(16, 1532))

        almeria.addLocation(aguilasLoc)
        almeria.addLocation(almeriaLoc)
        almeria.addLocation(mortilLoc)

        almeria.locations.forEach { location ->
            location.grayStores.add(storeBuilder.buildM55(168))
            location.grayStores.add(storeBuilder.buildPalmaED(96))
            location.grayStores.add(storeBuilder.buildPalmaNG(96))
            location.grayStores.add(storeBuilder.buildIbiza(96))
            location.grayStores.add(storeBuilder.buildOlbiaNA(144))
            location.grayStores.add(storeBuilder.buildOlbiaSYS(144))
            location.grayStores.add(storeBuilder.buildGenova(144))
            location.grayStores.add(storeBuilder.buildNapoli(168))
            location.grayStores.add(storeBuilder.buildPalermo(216))
            location.grayStores.add(storeBuilder.buildRiposto(216))
            location.grayStores.add(storeBuilder.buildMalta(216))
            location.grayStores.add(storeBuilder.buildAthensSotiris(192))
            location.grayStores.add(storeBuilder.buildAthensH360(192))
            location.grayStores.add(storeBuilder.buildCorfu(216))
            location.grayStores.add(storeBuilder.buildKosA1(216))
            location.grayStores.add(storeBuilder.buildRhodes(216))
            location.grayStores.add(storeBuilder.buildVenice(168))
            location.grayStores.add(storeBuilder.buildZadar(196))
            location.grayStores.add(storeBuilder.buildSplit(196))
            location.grayStores.add(storeBuilder.buildDubrovnikKristijan(196))
            location.grayStores.add(storeBuilder.buildDubrovnikBWA(196))
            location.grayStores.add(storeBuilder.buildBCM(216))
            location.grayStores.add(storeBuilder.buildTivat(216))
            location.newItemsStores.add(storeBuilder.buildNewItemsStore(144))
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

        calpLoc.alternativeStores.add(storeBuilder.buildPalmaED(24, 300))
        calpLoc.alternativeStores.add(storeBuilder.buildPalmaNG(24, 300))
        calpLoc.alternativeStores.add(storeBuilder.buildAntibes(12, 1188))

        calpLoc.grayStores.add(storeBuilder.buildM55(168))
        calpLoc.grayStores.add(storeBuilder.buildIbiza(96))
        calpLoc.grayStores.add(storeBuilder.buildOlbiaNA(144))
        calpLoc.grayStores.add(storeBuilder.buildOlbiaSYS(144))
        calpLoc.grayStores.add(storeBuilder.buildGenova(144))
        calpLoc.grayStores.add(storeBuilder.buildNapoli(168))
        calpLoc.grayStores.add(storeBuilder.buildPalermo(216))
        calpLoc.grayStores.add(storeBuilder.buildRiposto(216))
        calpLoc.grayStores.add(storeBuilder.buildMalta(216))
        calpLoc.grayStores.add(storeBuilder.buildAthensSotiris(192))
        calpLoc.grayStores.add(storeBuilder.buildAthensH360(192))
        calpLoc.grayStores.add(storeBuilder.buildCorfu(216))
        calpLoc.grayStores.add(storeBuilder.buildKosA1(216))
        calpLoc.grayStores.add(storeBuilder.buildRhodes(216))
        calpLoc.grayStores.add(storeBuilder.buildVenice(168))
        calpLoc.grayStores.add(storeBuilder.buildZadar(196))
        calpLoc.grayStores.add(storeBuilder.buildSplit(196))
        calpLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(196))
        calpLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(196))
        calpLoc.grayStores.add(storeBuilder.buildBCM(216))
        calpLoc.grayStores.add(storeBuilder.buildTivat(216))
        calpLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(144))

        alicanteLoc.alternativeStores.add(storeBuilder.buildPalmaED(24, 300))
        alicanteLoc.alternativeStores.add(storeBuilder.buildPalmaNG(24, 300))
        alicanteLoc.alternativeStores.add(storeBuilder.buildAntibes(12, 1166))

        alicanteLoc.grayStores.add(storeBuilder.buildM55(168))
        alicanteLoc.grayStores.add(storeBuilder.buildIbiza(96))
        alicanteLoc.grayStores.add(storeBuilder.buildOlbiaNA(144))
        alicanteLoc.grayStores.add(storeBuilder.buildOlbiaSYS(144))
        alicanteLoc.grayStores.add(storeBuilder.buildGenova(144))
        alicanteLoc.grayStores.add(storeBuilder.buildNapoli(168))
        alicanteLoc.grayStores.add(storeBuilder.buildPalermo(216))
        alicanteLoc.grayStores.add(storeBuilder.buildRiposto(216))
        alicanteLoc.grayStores.add(storeBuilder.buildMalta(216))
        alicanteLoc.grayStores.add(storeBuilder.buildAthensSotiris(192))
        alicanteLoc.grayStores.add(storeBuilder.buildAthensH360(192))
        alicanteLoc.grayStores.add(storeBuilder.buildCorfu(216))
        alicanteLoc.grayStores.add(storeBuilder.buildKosA1(216))
        alicanteLoc.grayStores.add(storeBuilder.buildRhodes(216))
        alicanteLoc.grayStores.add(storeBuilder.buildVenice(168))
        alicanteLoc.grayStores.add(storeBuilder.buildZadar(196))
        alicanteLoc.grayStores.add(storeBuilder.buildSplit(196))
        alicanteLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(196))
        alicanteLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(196))
        alicanteLoc.grayStores.add(storeBuilder.buildBCM(216))
        alicanteLoc.grayStores.add(storeBuilder.buildTivat(216))
        alicanteLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(144))


        cartagenaLoc.alternativeStores.add(storeBuilder.buildPalmaED(24, 300))
        cartagenaLoc.alternativeStores.add(storeBuilder.buildPalmaNG(24, 300))
        cartagenaLoc.alternativeStores.add(storeBuilder.buildAntibes(13, 1261))

        cartagenaLoc.grayStores.add(storeBuilder.buildM55(168))
        cartagenaLoc.grayStores.add(storeBuilder.buildIbiza(72))
        cartagenaLoc.grayStores.add(storeBuilder.buildOlbiaNA(144))
        cartagenaLoc.grayStores.add(storeBuilder.buildOlbiaSYS(144))
        cartagenaLoc.grayStores.add(storeBuilder.buildGenova(144))
        cartagenaLoc.grayStores.add(storeBuilder.buildNapoli(168))
        cartagenaLoc.grayStores.add(storeBuilder.buildPalermo(216))
        cartagenaLoc.grayStores.add(storeBuilder.buildRiposto(216))
        cartagenaLoc.grayStores.add(storeBuilder.buildMalta(216))
        cartagenaLoc.grayStores.add(storeBuilder.buildAthensSotiris(192))
        cartagenaLoc.grayStores.add(storeBuilder.buildAthensH360(192))
        cartagenaLoc.grayStores.add(storeBuilder.buildCorfu(216))
        cartagenaLoc.grayStores.add(storeBuilder.buildKosA1(216))
        cartagenaLoc.grayStores.add(storeBuilder.buildRhodes(216))
        cartagenaLoc.grayStores.add(storeBuilder.buildVenice(168))
        cartagenaLoc.grayStores.add(storeBuilder.buildZadar(196))
        cartagenaLoc.grayStores.add(storeBuilder.buildSplit(196))
        cartagenaLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(196))
        cartagenaLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(196))
        cartagenaLoc.grayStores.add(storeBuilder.buildBCM(216))
        cartagenaLoc.grayStores.add(storeBuilder.buildTivat(216))
        cartagenaLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(144))

        alicante.addLocation(calpLoc)
        alicante.addLocation(alicanteLoc)
        alicante.addLocation(cartagenaLoc)

        regions.add(alicante)

        // Valencia
        val valencia = Region("Valencia",6)

        val tarragonaLoc = Location("Tarragona")
        val valenciaLoc = Location("Valencia")

        tarragonaLoc.alternativeStores.add(storeBuilder.buildPalmaED(24, 300))
        tarragonaLoc.alternativeStores.add(storeBuilder.buildPalmaNG(24, 300))
        tarragonaLoc.alternativeStores.add(storeBuilder.buildAntibes(8, 736))

        valenciaLoc.alternativeStores.add(storeBuilder.buildPalmaED(24, 300))
        valenciaLoc.alternativeStores.add(storeBuilder.buildPalmaNG(24, 300))
        valenciaLoc.alternativeStores.add(storeBuilder.buildAntibes(10, 987))

        valencia.addLocation(tarragonaLoc)
        valencia.addLocation(valenciaLoc)

        valencia.locations.forEach { location ->
            location.grayStores.add(storeBuilder.buildM55(168))
            location.grayStores.add(storeBuilder.buildIbiza(72))
            location.grayStores.add(storeBuilder.buildOlbiaNA(144))
            location.grayStores.add(storeBuilder.buildOlbiaSYS(144))
            location.grayStores.add(storeBuilder.buildGenova(144))
            location.grayStores.add(storeBuilder.buildNapoli(168))
            location.grayStores.add(storeBuilder.buildPalermo(216))
            location.grayStores.add(storeBuilder.buildRiposto(216))
            location.grayStores.add(storeBuilder.buildMalta(216))
            location.grayStores.add(storeBuilder.buildAthensSotiris(192))
            location.grayStores.add(storeBuilder.buildAthensH360(192))
            location.grayStores.add(storeBuilder.buildCorfu(216))
            location.grayStores.add(storeBuilder.buildKosA1(216))
            location.grayStores.add(storeBuilder.buildRhodes(216))
            location.grayStores.add(storeBuilder.buildVenice(168))
            location.grayStores.add(storeBuilder.buildZadar(196))
            location.grayStores.add(storeBuilder.buildSplit(196))
            location.grayStores.add(storeBuilder.buildDubrovnikKristijan(196))
            location.grayStores.add(storeBuilder.buildDubrovnikBWA(196))
            location.grayStores.add(storeBuilder.buildBCM(216))
            location.grayStores.add(storeBuilder.buildTivat(216))
            location.newItemsStores.add(storeBuilder.buildNewItemsStore(144))
        }

        regions.add(valencia)


        // Denia
        val denia = Region("Denia",7)

        val deniaLoc = Location("Denia")

        deniaLoc.alternativeStores.add(storeBuilder.buildPalmaED(24, 300))
        deniaLoc.alternativeStores.add(storeBuilder.buildPalmaNG(24, 300))
        deniaLoc.alternativeStores.add(storeBuilder.buildAntibes(11, 1101))

        deniaLoc.grayStores.add(storeBuilder.buildM55(168))
        deniaLoc.grayStores.add(storeBuilder.buildIbiza(72))
        deniaLoc.grayStores.add(storeBuilder.buildOlbiaNA(144))
        deniaLoc.grayStores.add(storeBuilder.buildOlbiaSYS(144))
        deniaLoc.grayStores.add(storeBuilder.buildGenova(144))
        deniaLoc.grayStores.add(storeBuilder.buildNapoli(168))
        deniaLoc.grayStores.add(storeBuilder.buildPalermo(216))
        deniaLoc.grayStores.add(storeBuilder.buildRiposto(216))
        deniaLoc.grayStores.add(storeBuilder.buildMalta(216))
        deniaLoc.grayStores.add(storeBuilder.buildAthensSotiris(192))
        deniaLoc.grayStores.add(storeBuilder.buildAthensH360(192))
        deniaLoc.grayStores.add(storeBuilder.buildCorfu(216))
        deniaLoc.grayStores.add(storeBuilder.buildKosA1(216))
        deniaLoc.grayStores.add(storeBuilder.buildRhodes(216))
        deniaLoc.grayStores.add(storeBuilder.buildVenice(168))
        deniaLoc.grayStores.add(storeBuilder.buildZadar(196))
        deniaLoc.grayStores.add(storeBuilder.buildSplit(196))
        deniaLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(196))
        deniaLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(196))
        deniaLoc.grayStores.add(storeBuilder.buildBCM(216))
        deniaLoc.grayStores.add(storeBuilder.buildTivat(216))
        deniaLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(144))

        denia.addLocation(deniaLoc)

        regions.add(denia)


        // Barcelona
        val barcelona = Region("Barcelona",8)

        val barcelonaLoc = Location("Barcelona")
        val sitgesLoc = Location("Sitges")

        barcelonaLoc.alternativeStores.add(storeBuilder.buildPalmaED(18, 300))
        barcelonaLoc.alternativeStores.add(storeBuilder.buildPalmaNG(18, 300))
        barcelonaLoc.alternativeStores.add(storeBuilder.buildAntibes(7, 652))

        sitgesLoc.alternativeStores.add(storeBuilder.buildPalmaED(24, 300))
        sitgesLoc.alternativeStores.add(storeBuilder.buildPalmaNG(24, 300))
        sitgesLoc.alternativeStores.add(storeBuilder.buildAntibes(7, 683))

        barcelona.addLocation(barcelonaLoc)
        barcelona.addLocation(sitgesLoc)

        barcelona.locations.forEach { location ->
            location.grayStores.add(storeBuilder.buildM55(168))
            location.grayStores.add(storeBuilder.buildIbiza(72))
            location.grayStores.add(storeBuilder.buildOlbiaNA(120))
            location.grayStores.add(storeBuilder.buildOlbiaSYS(120))
            location.grayStores.add(storeBuilder.buildGenova(120))
            location.grayStores.add(storeBuilder.buildNapoli(168))
            location.grayStores.add(storeBuilder.buildPalermo(216))
            location.grayStores.add(storeBuilder.buildRiposto(216))
            location.grayStores.add(storeBuilder.buildMalta(216))
            location.grayStores.add(storeBuilder.buildAthensSotiris(192))
            location.grayStores.add(storeBuilder.buildAthensH360(192))
            location.grayStores.add(storeBuilder.buildCorfu(216))
            location.grayStores.add(storeBuilder.buildKosA1(216))
            location.grayStores.add(storeBuilder.buildRhodes(216))
            location.grayStores.add(storeBuilder.buildVenice(168))
            location.grayStores.add(storeBuilder.buildZadar(196))
            location.grayStores.add(storeBuilder.buildSplit(196))
            location.grayStores.add(storeBuilder.buildDubrovnikKristijan(196))
            location.grayStores.add(storeBuilder.buildDubrovnikBWA(196))
            location.grayStores.add(storeBuilder.buildBCM(216))
            location.grayStores.add(storeBuilder.buildTivat(216))
            location.newItemsStores.add(storeBuilder.buildNewItemsStore(120))
        }

        regions.add(barcelona)

        // Roses
        val roses = Region("Roses",9)

        val rosesLoc = Location("Roses")

        rosesLoc.alternativeStores.add(storeBuilder.buildPalmaED(24, 300))
        rosesLoc.alternativeStores.add(storeBuilder.buildPalmaNG(24, 300))
        rosesLoc.alternativeStores.add(storeBuilder.buildAntibes(6, 541))

        rosesLoc.grayStores.add(storeBuilder.buildM55(168))
        rosesLoc.grayStores.add(storeBuilder.buildIbiza(72))
        rosesLoc.grayStores.add(storeBuilder.buildOlbiaNA(120))
        rosesLoc.grayStores.add(storeBuilder.buildOlbiaSYS(120))
        rosesLoc.grayStores.add(storeBuilder.buildGenova(120))
        rosesLoc.grayStores.add(storeBuilder.buildNapoli(168))
        rosesLoc.grayStores.add(storeBuilder.buildPalermo(216))
        rosesLoc.grayStores.add(storeBuilder.buildRiposto(216))
        rosesLoc.grayStores.add(storeBuilder.buildMalta(216))
        rosesLoc.grayStores.add(storeBuilder.buildAthensSotiris(192))
        rosesLoc.grayStores.add(storeBuilder.buildAthensH360(192))
        rosesLoc.grayStores.add(storeBuilder.buildCorfu(216))
        rosesLoc.grayStores.add(storeBuilder.buildKosA1(216))
        rosesLoc.grayStores.add(storeBuilder.buildRhodes(216))
        rosesLoc.grayStores.add(storeBuilder.buildVenice(168))
        rosesLoc.grayStores.add(storeBuilder.buildZadar(196))
        rosesLoc.grayStores.add(storeBuilder.buildSplit(196))
        rosesLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(196))
        rosesLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(196))
        rosesLoc.grayStores.add(storeBuilder.buildBCM(216))
        rosesLoc.grayStores.add(storeBuilder.buildTivat(216))
        rosesLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(120))

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

        ibizaIslandLoc.nativeStores.add(storeBuilder.buildIbiza(1, 50))
        ibizaIslandLoc.alternativeStores.add(storeBuilder.buildPalmaED(5, 200))
        ibizaIslandLoc.alternativeStores.add(storeBuilder.buildPalmaNG(5, 200))

        ibizaIslandLoc.grayStores.add(storeBuilder.buildM55(168))
        ibizaIslandLoc.grayStores.add(storeBuilder.buildAntibes(19))
        ibizaIslandLoc.grayStores.add(storeBuilder.buildOlbiaNA(168))
        ibizaIslandLoc.grayStores.add(storeBuilder.buildOlbiaSYS(168))
        ibizaIslandLoc.grayStores.add(storeBuilder.buildGenova(120))
        ibizaIslandLoc.grayStores.add(storeBuilder.buildNapoli(168))
        ibizaIslandLoc.grayStores.add(storeBuilder.buildPalermo(216))
        ibizaIslandLoc.grayStores.add(storeBuilder.buildRiposto(216))
        ibizaIslandLoc.grayStores.add(storeBuilder.buildMalta(216))
        ibizaIslandLoc.grayStores.add(storeBuilder.buildAthensSotiris(216))
        ibizaIslandLoc.grayStores.add(storeBuilder.buildAthensH360(216))
        ibizaIslandLoc.grayStores.add(storeBuilder.buildCorfu(216))
        ibizaIslandLoc.grayStores.add(storeBuilder.buildKosA1(216))
        ibizaIslandLoc.grayStores.add(storeBuilder.buildRhodes(216))
        ibizaIslandLoc.grayStores.add(storeBuilder.buildVenice(96))
        ibizaIslandLoc.grayStores.add(storeBuilder.buildZadar(120))
        ibizaIslandLoc.grayStores.add(storeBuilder.buildSplit(120))
        ibizaIslandLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(120))
        ibizaIslandLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(120))
        ibizaIslandLoc.grayStores.add(storeBuilder.buildBCM(216))
        ibizaIslandLoc.grayStores.add(storeBuilder.buildTivat(216))
        ibizaIslandLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(72))

        formenteraIslandLoc.nativeStores.add(storeBuilder.buildIbiza(2, 100))
        formenteraIslandLoc.alternativeStores.add(storeBuilder.buildPalmaED(6, 250))
        formenteraIslandLoc.alternativeStores.add(storeBuilder.buildPalmaNG(6, 200))

        formenteraIslandLoc.grayStores.add(storeBuilder.buildM55(168))
        formenteraIslandLoc.grayStores.add(storeBuilder.buildAntibes(23))
        formenteraIslandLoc.grayStores.add(storeBuilder.buildOlbiaNA(168))
        formenteraIslandLoc.grayStores.add(storeBuilder.buildOlbiaSYS(168))
        formenteraIslandLoc.grayStores.add(storeBuilder.buildGenova(120))
        formenteraIslandLoc.grayStores.add(storeBuilder.buildNapoli(168))
        formenteraIslandLoc.grayStores.add(storeBuilder.buildPalermo(216))
        formenteraIslandLoc.grayStores.add(storeBuilder.buildRiposto(216))
        formenteraIslandLoc.grayStores.add(storeBuilder.buildMalta(216))
        formenteraIslandLoc.grayStores.add(storeBuilder.buildAthensSotiris(216))
        formenteraIslandLoc.grayStores.add(storeBuilder.buildAthensH360(216))
        formenteraIslandLoc.grayStores.add(storeBuilder.buildCorfu(216))
        formenteraIslandLoc.grayStores.add(storeBuilder.buildKosA1(216))
        formenteraIslandLoc.grayStores.add(storeBuilder.buildRhodes(216))
        formenteraIslandLoc.grayStores.add(storeBuilder.buildVenice(96))
        formenteraIslandLoc.grayStores.add(storeBuilder.buildZadar(120))
        formenteraIslandLoc.grayStores.add(storeBuilder.buildSplit(120))
        formenteraIslandLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(120))
        formenteraIslandLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(120))
        formenteraIslandLoc.grayStores.add(storeBuilder.buildBCM(216))
        formenteraIslandLoc.grayStores.add(storeBuilder.buildTivat(216))
        formenteraIslandLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(72))

        ibiza.addLocation(ibizaIslandLoc)
        ibiza.addLocation(formenteraIslandLoc)

        regions.add(ibiza)


        // Mallorca
        val mallorca = Region("Mallorca",11)

        val islandNorthEastLoc = Location("Island - North/East")
        val palmaLoc = Location("Palma")
        val portPortalsLoc = Location("Port Portals")
        val portoAdrianoLoc = Location("Porto Adriano")

        islandNorthEastLoc.nativeStores.add(storeBuilder.buildPalmaED(2, 100))
        islandNorthEastLoc.nativeStores.add(storeBuilder.buildPalmaNG(2, 100))
        islandNorthEastLoc.alternativeStores.add(storeBuilder.buildIbiza(6, 200))

        palmaLoc.nativeStores.add(storeBuilder.buildPalmaED(1, 50))
        palmaLoc.nativeStores.add(storeBuilder.buildPalmaNG(1, 50))
        palmaLoc.alternativeStores.add(storeBuilder.buildIbiza(6, 200))

        portPortalsLoc.nativeStores.add(storeBuilder.buildPalmaED(1, 50))
        portPortalsLoc.nativeStores.add(storeBuilder.buildPalmaNG(1, 50))
        portPortalsLoc.alternativeStores.add(storeBuilder.buildIbiza(6, 200))

        portoAdrianoLoc.nativeStores.add(storeBuilder.buildPalmaED(1, 50))
        portoAdrianoLoc.nativeStores.add(storeBuilder.buildPalmaNG(1, 50))
        portoAdrianoLoc.alternativeStores.add(storeBuilder.buildIbiza(6, 200))

        mallorca.addLocation(islandNorthEastLoc)
        mallorca.addLocation(palmaLoc)
        mallorca.addLocation(portPortalsLoc)
        mallorca.addLocation(portoAdrianoLoc)

        mallorca.locations.forEach { location ->
            location.grayStores.add(storeBuilder.buildM55(168))
            location.grayStores.add(storeBuilder.buildAntibes(18))
            location.grayStores.add(storeBuilder.buildOlbiaNA(168))
            location.grayStores.add(storeBuilder.buildOlbiaSYS(168))
            location.grayStores.add(storeBuilder.buildGenova(120))
            location.grayStores.add(storeBuilder.buildNapoli(168))
            location.grayStores.add(storeBuilder.buildPalermo(216))
            location.grayStores.add(storeBuilder.buildRiposto(216))
            location.grayStores.add(storeBuilder.buildMalta(216))
            location.grayStores.add(storeBuilder.buildAthensSotiris(216))
            location.grayStores.add(storeBuilder.buildAthensH360(216))
            location.grayStores.add(storeBuilder.buildCorfu(216))
            location.grayStores.add(storeBuilder.buildKosA1(216))
            location.grayStores.add(storeBuilder.buildRhodes(216))
            location.grayStores.add(storeBuilder.buildVenice(96))
            location.grayStores.add(storeBuilder.buildZadar(120))
            location.grayStores.add(storeBuilder.buildSplit(120))
            location.grayStores.add(storeBuilder.buildDubrovnikKristijan(120))
            location.grayStores.add(storeBuilder.buildDubrovnikBWA(120))
            location.grayStores.add(storeBuilder.buildBCM(216))
            location.grayStores.add(storeBuilder.buildTivat(216))
            location.newItemsStores.add(storeBuilder.buildNewItemsStore(72))
        }

        regions.add(mallorca)


        // Menorca
        val menorca = Region("Menorca",12)

        val menorcaLoc = Location("Menorca Island")

        menorcaLoc.nativeStores.add(storeBuilder.buildPalmaED(3, 200))
        menorcaLoc.nativeStores.add(storeBuilder.buildPalmaNG(3, 200))
        menorcaLoc.alternativeStores.add(storeBuilder.buildIbiza(8, 300))

        menorcaLoc.grayStores.add(storeBuilder.buildM55(168))
        menorcaLoc.grayStores.add(storeBuilder.buildAntibes(17))
        menorcaLoc.grayStores.add(storeBuilder.buildOlbiaNA(168))
        menorcaLoc.grayStores.add(storeBuilder.buildOlbiaSYS(168))
        menorcaLoc.grayStores.add(storeBuilder.buildGenova(120))
        menorcaLoc.grayStores.add(storeBuilder.buildNapoli(168))
        menorcaLoc.grayStores.add(storeBuilder.buildPalermo(216))
        menorcaLoc.grayStores.add(storeBuilder.buildRiposto(216))
        menorcaLoc.grayStores.add(storeBuilder.buildMalta(216))
        menorcaLoc.grayStores.add(storeBuilder.buildAthensSotiris(216))
        menorcaLoc.grayStores.add(storeBuilder.buildAthensH360(216))
        menorcaLoc.grayStores.add(storeBuilder.buildCorfu(216))
        menorcaLoc.grayStores.add(storeBuilder.buildKosA1(216))
        menorcaLoc.grayStores.add(storeBuilder.buildRhodes(216))
        menorcaLoc.grayStores.add(storeBuilder.buildVenice(96))
        menorcaLoc.grayStores.add(storeBuilder.buildZadar(120))
        menorcaLoc.grayStores.add(storeBuilder.buildSplit(120))
        menorcaLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(120))
        menorcaLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(120))
        menorcaLoc.grayStores.add(storeBuilder.buildBCM(216))
        menorcaLoc.grayStores.add(storeBuilder.buildTivat(216))
        menorcaLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(72))

        menorca.addLocation(menorcaLoc)

        regions.add(menorca)

        return regions.toList()
    }

    override fun buildSouthOfFrance(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        // Port Vendres
        val portVendres = Region("Port Vendres",13)

        val portVendresLoc = Location("Port Vendres")

        portVendresLoc.nativeStores.add(storeBuilder.buildAntibes(6, 500))
        portVendresLoc.alternativeStores.add(storeBuilder.buildGenova(24, 450))

        portVendresLoc.grayStores.add(storeBuilder.buildM55(24))
        portVendresLoc.grayStores.add(storeBuilder.buildPalmaED(96))
        portVendresLoc.grayStores.add(storeBuilder.buildPalmaNG(96))
        portVendresLoc.grayStores.add(storeBuilder.buildIbiza(96))
        portVendresLoc.grayStores.add(storeBuilder.buildOlbiaNA(96))
        portVendresLoc.grayStores.add(storeBuilder.buildOlbiaSYS(96))
        portVendresLoc.grayStores.add(storeBuilder.buildNapoli(96))
        portVendresLoc.grayStores.add(storeBuilder.buildPalermo(168))
        portVendresLoc.grayStores.add(storeBuilder.buildRiposto(168))
        portVendresLoc.grayStores.add(storeBuilder.buildMalta(168))
        portVendresLoc.grayStores.add(storeBuilder.buildAthensSotiris(168))
        portVendresLoc.grayStores.add(storeBuilder.buildAthensH360(168))
        portVendresLoc.grayStores.add(storeBuilder.buildCorfu(192))
        portVendresLoc.grayStores.add(storeBuilder.buildKosA1(216))
        portVendresLoc.grayStores.add(storeBuilder.buildRhodes(216))
        portVendresLoc.grayStores.add(storeBuilder.buildVenice(72))
        portVendresLoc.grayStores.add(storeBuilder.buildZadar(96))
        portVendresLoc.grayStores.add(storeBuilder.buildSplit(96))
        portVendresLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(96))
        portVendresLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(96))
        portVendresLoc.grayStores.add(storeBuilder.buildBCM(120))
        portVendresLoc.grayStores.add(storeBuilder.buildTivat(120))
        portVendresLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(72))

        portVendres.addLocation(portVendresLoc)

        regions.add(portVendres)


        // Marseilles
        val marseilles = Region("Marseilles",14)

        val marseillesLoc = Location("Marseilles")

        marseillesLoc.nativeStores.add(storeBuilder.buildAntibes(5, 184))
        marseillesLoc.alternativeStores.add(storeBuilder.buildGenova(24, 450))

        marseillesLoc.grayStores.add(storeBuilder.buildM55(24))
        marseillesLoc.grayStores.add(storeBuilder.buildPalmaED(96))
        marseillesLoc.grayStores.add(storeBuilder.buildPalmaNG(96))
        marseillesLoc.grayStores.add(storeBuilder.buildIbiza(96))
        marseillesLoc.grayStores.add(storeBuilder.buildOlbiaNA(96))
        marseillesLoc.grayStores.add(storeBuilder.buildOlbiaSYS(96))
        marseillesLoc.grayStores.add(storeBuilder.buildNapoli(96))
        marseillesLoc.grayStores.add(storeBuilder.buildPalermo(168))
        marseillesLoc.grayStores.add(storeBuilder.buildRiposto(168))
        marseillesLoc.grayStores.add(storeBuilder.buildMalta(168))
        marseillesLoc.grayStores.add(storeBuilder.buildAthensSotiris(168))
        marseillesLoc.grayStores.add(storeBuilder.buildAthensH360(168))
        marseillesLoc.grayStores.add(storeBuilder.buildCorfu(192))
        marseillesLoc.grayStores.add(storeBuilder.buildKosA1(216))
        marseillesLoc.grayStores.add(storeBuilder.buildRhodes(216))
        marseillesLoc.grayStores.add(storeBuilder.buildVenice(72))
        marseillesLoc.grayStores.add(storeBuilder.buildZadar(96))
        marseillesLoc.grayStores.add(storeBuilder.buildSplit(96))
        marseillesLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(96))
        marseillesLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(96))
        marseillesLoc.grayStores.add(storeBuilder.buildBCM(120))
        marseillesLoc.grayStores.add(storeBuilder.buildTivat(120))
        marseillesLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(72))

        marseilles.addLocation(marseillesLoc)
        regions.add(marseilles)


        // Toulon
        val toulon = Region("Toulon",15)

        val cavalaireSurMerLoc = Location("Cavalaire sur Mer")
        val hyeresLoc = Location("Hyeres")
        val toulonLoc = Location("Toulon")
        val laCiotatLoc = Location("La Ciotat")

        cavalaireSurMerLoc.nativeStores.add(storeBuilder.buildAntibes(4, 102))
        cavalaireSurMerLoc.alternativeStores.add(storeBuilder.buildGenova(24, 450))

        hyeresLoc.nativeStores.add(storeBuilder.buildAntibes(4, 136))
        hyeresLoc.alternativeStores.add(storeBuilder.buildGenova(24, 450))

        toulonLoc.nativeStores.add(storeBuilder.buildAntibes(4, 134))
        toulonLoc.alternativeStores.add(storeBuilder.buildGenova(24, 450))

        laCiotatLoc.nativeStores.add(storeBuilder.buildAntibes(4, 173))
        laCiotatLoc.alternativeStores.add(storeBuilder.buildGenova(24, 450))

        toulon.addLocation(cavalaireSurMerLoc)
        toulon.addLocation(hyeresLoc)
        toulon.addLocation(toulonLoc)
        toulon.addLocation(laCiotatLoc)

        toulon.locations.forEach { location ->
            location.grayStores.add(storeBuilder.buildM55(24))
            location.grayStores.add(storeBuilder.buildPalmaED(96))
            location.grayStores.add(storeBuilder.buildPalmaNG(96))
            location.grayStores.add(storeBuilder.buildIbiza(96))
            location.grayStores.add(storeBuilder.buildOlbiaNA(96))
            location.grayStores.add(storeBuilder.buildOlbiaSYS(96))
            location.grayStores.add(storeBuilder.buildNapoli(96))
            location.grayStores.add(storeBuilder.buildPalermo(168))
            location.grayStores.add(storeBuilder.buildRiposto(168))
            location.grayStores.add(storeBuilder.buildMalta(168))
            location.grayStores.add(storeBuilder.buildAthensSotiris(168))
            location.grayStores.add(storeBuilder.buildAthensH360(168))
            location.grayStores.add(storeBuilder.buildCorfu(192))
            location.grayStores.add(storeBuilder.buildKosA1(216))
            location.grayStores.add(storeBuilder.buildRhodes(216))
            location.grayStores.add(storeBuilder.buildVenice(72))
            location.grayStores.add(storeBuilder.buildZadar(96))
            location.grayStores.add(storeBuilder.buildSplit(96))
            location.grayStores.add(storeBuilder.buildDubrovnikKristijan(96))
            location.grayStores.add(storeBuilder.buildDubrovnikBWA(96))
            location.grayStores.add(storeBuilder.buildBCM(120))
            location.grayStores.add(storeBuilder.buildTivat(120))
            location.newItemsStores.add(storeBuilder.buildNewItemsStore(72))
        }

        regions.add(toulon)


        // St Tropez
        val stTropez = Region("St. Tropez",16)

        val saintRaphaelLoc = Location("Saint Raphael")
        val stTropezLoc = Location("St. Tropez")
        val pampellonneBeachLoc = Location("Pampellonne beach")

        saintRaphaelLoc.nativeStores.add(storeBuilder.buildAntibes(3, 52))
        saintRaphaelLoc.alternativeStores.add(storeBuilder.buildGenova(24, 450))

        stTropezLoc.nativeStores.add(storeBuilder.buildAntibes(4, 97))
        stTropezLoc.alternativeStores.add(storeBuilder.buildGenova(24, 450))

        pampellonneBeachLoc.nativeStores.add(storeBuilder.buildAntibes(4, 100))
        pampellonneBeachLoc.alternativeStores.add(storeBuilder.buildGenova(24, 450))

        stTropez.addLocation(saintRaphaelLoc)
        stTropez.addLocation(stTropezLoc)
        stTropez.addLocation(pampellonneBeachLoc)

        stTropez.locations.forEach { location ->
            location.grayStores.add(storeBuilder.buildM55(24))
            location.grayStores.add(storeBuilder.buildPalmaED(96))
            location.grayStores.add(storeBuilder.buildPalmaNG(96))
            location.grayStores.add(storeBuilder.buildIbiza(96))
            location.grayStores.add(storeBuilder.buildOlbiaNA(96))
            location.grayStores.add(storeBuilder.buildOlbiaSYS(96))
            location.grayStores.add(storeBuilder.buildNapoli(96))
            location.grayStores.add(storeBuilder.buildPalermo(168))
            location.grayStores.add(storeBuilder.buildRiposto(168))
            location.grayStores.add(storeBuilder.buildMalta(168))
            location.grayStores.add(storeBuilder.buildAthensSotiris(168))
            location.grayStores.add(storeBuilder.buildAthensH360(168))
            location.grayStores.add(storeBuilder.buildCorfu(192))
            location.grayStores.add(storeBuilder.buildKosA1(216))
            location.grayStores.add(storeBuilder.buildRhodes(216))
            location.grayStores.add(storeBuilder.buildVenice(72))
            location.grayStores.add(storeBuilder.buildZadar(96))
            location.grayStores.add(storeBuilder.buildSplit(96))
            location.grayStores.add(storeBuilder.buildDubrovnikKristijan(96))
            location.grayStores.add(storeBuilder.buildDubrovnikBWA(96))
            location.grayStores.add(storeBuilder.buildBCM(120))
            location.grayStores.add(storeBuilder.buildTivat(120))
            location.newItemsStores.add(storeBuilder.buildNewItemsStore(72))
        }

        regions.add(stTropez)


        // Cannes
        val cannes = Region("Cannes",17)

        val cannesLoc = Location("Cannes")
        val mandelieuLaNapouleLoc = Location("Mandelieu la Napoule")
        val theuleSurMerLoc = Location("Theule sur Mer")

        cannesLoc.nativeStores.add(storeBuilder.buildAntibes(1, 20))
        cannesLoc.alternativeStores.add(storeBuilder.buildGenova(24, 450))

        mandelieuLaNapouleLoc.nativeStores.add(storeBuilder.buildAntibes(2, 25))
        mandelieuLaNapouleLoc.alternativeStores.add(storeBuilder.buildGenova(24, 450))

        theuleSurMerLoc.nativeStores.add(storeBuilder.buildAntibes(2, 35))
        theuleSurMerLoc.alternativeStores.add(storeBuilder.buildGenova(24, 450))

        cannes.addLocation(cannesLoc)
        cannes.addLocation(mandelieuLaNapouleLoc)
        cannes.addLocation(theuleSurMerLoc)

        cannes.locations.forEach { location ->
            location.grayStores.add(storeBuilder.buildM55(24))
            location.grayStores.add(storeBuilder.buildPalmaED(96))
            location.grayStores.add(storeBuilder.buildPalmaNG(96))
            location.grayStores.add(storeBuilder.buildIbiza(96))
            location.grayStores.add(storeBuilder.buildOlbiaNA(96))
            location.grayStores.add(storeBuilder.buildOlbiaSYS(96))
            location.grayStores.add(storeBuilder.buildNapoli(96))
            location.grayStores.add(storeBuilder.buildPalermo(168))
            location.grayStores.add(storeBuilder.buildRiposto(168))
            location.grayStores.add(storeBuilder.buildMalta(168))
            location.grayStores.add(storeBuilder.buildAthensSotiris(168))
            location.grayStores.add(storeBuilder.buildAthensH360(168))
            location.grayStores.add(storeBuilder.buildCorfu(192))
            location.grayStores.add(storeBuilder.buildKosA1(216))
            location.grayStores.add(storeBuilder.buildRhodes(216))
            location.grayStores.add(storeBuilder.buildVenice(72))
            location.grayStores.add(storeBuilder.buildZadar(96))
            location.grayStores.add(storeBuilder.buildSplit(96))
            location.grayStores.add(storeBuilder.buildDubrovnikKristijan(96))
            location.grayStores.add(storeBuilder.buildDubrovnikBWA(96))
            location.grayStores.add(storeBuilder.buildBCM(120))
            location.grayStores.add(storeBuilder.buildTivat(120))
            location.newItemsStores.add(storeBuilder.buildNewItemsStore(72))
        }

        regions.add(cannes)


        // Antibes
        val antibes = Region("Antibes",18)

        val marinaBaieDesAngesLoc = Location("Marina Baie Des Anges")
        val antibesLoc = Location("Antibes")
        val golfeJuanLoc = Location("Golfe Juan")

        marinaBaieDesAngesLoc.nativeStores.add(storeBuilder.buildAntibes(1, 20))
        marinaBaieDesAngesLoc.alternativeStores.add(storeBuilder.buildGenova(24, 450))

        antibesLoc.nativeStores.add(storeBuilder.buildAntibes(1, 15))
        antibesLoc.alternativeStores.add(storeBuilder.buildGenova(24, 450))

        golfeJuanLoc.nativeStores.add(storeBuilder.buildAntibes(1, 15))
        golfeJuanLoc.alternativeStores.add(storeBuilder.buildGenova(24, 450))

        antibes.addLocation(marinaBaieDesAngesLoc)
        antibes.addLocation(antibesLoc)
        antibes.addLocation(golfeJuanLoc)

        antibes.locations.forEach { location ->
            location.grayStores.add(storeBuilder.buildM55(24))
            location.grayStores.add(storeBuilder.buildPalmaED(96))
            location.grayStores.add(storeBuilder.buildPalmaNG(96))
            location.grayStores.add(storeBuilder.buildIbiza(96))
            location.grayStores.add(storeBuilder.buildOlbiaNA(96))
            location.grayStores.add(storeBuilder.buildOlbiaSYS(96))
            location.grayStores.add(storeBuilder.buildNapoli(96))
            location.grayStores.add(storeBuilder.buildPalermo(168))
            location.grayStores.add(storeBuilder.buildRiposto(168))
            location.grayStores.add(storeBuilder.buildMalta(168))
            location.grayStores.add(storeBuilder.buildAthensSotiris(168))
            location.grayStores.add(storeBuilder.buildAthensH360(168))
            location.grayStores.add(storeBuilder.buildCorfu(192))
            location.grayStores.add(storeBuilder.buildKosA1(216))
            location.grayStores.add(storeBuilder.buildRhodes(216))
            location.grayStores.add(storeBuilder.buildVenice(72))
            location.grayStores.add(storeBuilder.buildZadar(96))
            location.grayStores.add(storeBuilder.buildSplit(96))
            location.grayStores.add(storeBuilder.buildDubrovnikKristijan(96))
            location.grayStores.add(storeBuilder.buildDubrovnikBWA(96))
            location.grayStores.add(storeBuilder.buildBCM(120))
            location.grayStores.add(storeBuilder.buildTivat(120))
            location.newItemsStores.add(storeBuilder.buildNewItemsStore(72))
        }

        regions.add(antibes)


        // Nice
        val nice = Region("Nice",19)

        val villeFrancheSurMerLoc = Location("Villefranche sur Mer")
        val niceLoc = Location("Nice")
        val cagnesSurMer = Location("Cagnes sur Mer")

        villeFrancheSurMerLoc.nativeStores.add(storeBuilder.buildAntibes(1, 35))
        villeFrancheSurMerLoc.alternativeStores.add(storeBuilder.buildGenova(24, 450))

        niceLoc.nativeStores.add(storeBuilder.buildAntibes(1, 25))
        niceLoc.alternativeStores.add(storeBuilder.buildGenova(24, 450))

        cagnesSurMer.nativeStores.add(storeBuilder.buildAntibes(1, 15))
        cagnesSurMer.alternativeStores.add(storeBuilder.buildGenova(24, 450))

        nice.addLocation(villeFrancheSurMerLoc)
        nice.addLocation(niceLoc)
        nice.addLocation(cagnesSurMer)

        nice.locations.forEach { location ->
            location.grayStores.add(storeBuilder.buildM55(24))
            location.grayStores.add(storeBuilder.buildPalmaED(96))
            location.grayStores.add(storeBuilder.buildPalmaNG(96))
            location.grayStores.add(storeBuilder.buildIbiza(96))
            location.grayStores.add(storeBuilder.buildOlbiaNA(96))
            location.grayStores.add(storeBuilder.buildOlbiaSYS(96))
            location.grayStores.add(storeBuilder.buildNapoli(96))
            location.grayStores.add(storeBuilder.buildPalermo(168))
            location.grayStores.add(storeBuilder.buildRiposto(168))
            location.grayStores.add(storeBuilder.buildMalta(168))
            location.grayStores.add(storeBuilder.buildAthensSotiris(168))
            location.grayStores.add(storeBuilder.buildAthensH360(168))
            location.grayStores.add(storeBuilder.buildCorfu(192))
            location.grayStores.add(storeBuilder.buildKosA1(216))
            location.grayStores.add(storeBuilder.buildRhodes(216))
            location.grayStores.add(storeBuilder.buildVenice(72))
            location.grayStores.add(storeBuilder.buildZadar(96))
            location.grayStores.add(storeBuilder.buildSplit(96))
            location.grayStores.add(storeBuilder.buildDubrovnikKristijan(96))
            location.grayStores.add(storeBuilder.buildDubrovnikBWA(96))
            location.grayStores.add(storeBuilder.buildBCM(120))
            location.grayStores.add(storeBuilder.buildTivat(120))
            location.newItemsStores.add(storeBuilder.buildNewItemsStore(72))
        }

        regions.add(nice)


        // Monaco
        val monaco = Region("Monaco",20)

        val monacoLoc = Location("Monaco")
        val capDAilLoc = Location("Cap d'Ail")
        val saintJeanCapFerratLoc = Location("Saint Jean Cap Ferrat")
        val beaulieuSurMer = Location("Beaulieu sur Mer")

        monacoLoc.nativeStores.add(storeBuilder.buildAntibes(2, 50))
        monacoLoc.alternativeStores.add(storeBuilder.buildGenova(24, 450))

        capDAilLoc.nativeStores.add(storeBuilder.buildAntibes(2, 45))
        capDAilLoc.alternativeStores.add(storeBuilder.buildGenova(24, 450))

        saintJeanCapFerratLoc.nativeStores.add(storeBuilder.buildAntibes(2, 40))
        saintJeanCapFerratLoc.alternativeStores.add(storeBuilder.buildGenova(24, 450))

        beaulieuSurMer.nativeStores.add(storeBuilder.buildAntibes(2, 35))
        beaulieuSurMer.alternativeStores.add(storeBuilder.buildGenova(24, 450))

        monaco.addLocation(monacoLoc)
        monaco.addLocation(capDAilLoc)
        monaco.addLocation(saintJeanCapFerratLoc)
        monaco.addLocation(beaulieuSurMer)

        monaco.locations.forEach { location ->
            location.grayStores.add(storeBuilder.buildM55(24))
            location.grayStores.add(storeBuilder.buildPalmaED(96))
            location.grayStores.add(storeBuilder.buildPalmaNG(96))
            location.grayStores.add(storeBuilder.buildIbiza(96))
            location.grayStores.add(storeBuilder.buildOlbiaNA(96))
            location.grayStores.add(storeBuilder.buildOlbiaSYS(96))
            location.grayStores.add(storeBuilder.buildNapoli(96))
            location.grayStores.add(storeBuilder.buildPalermo(168))
            location.grayStores.add(storeBuilder.buildRiposto(168))
            location.grayStores.add(storeBuilder.buildMalta(168))
            location.grayStores.add(storeBuilder.buildAthensSotiris(168))
            location.grayStores.add(storeBuilder.buildAthensH360(168))
            location.grayStores.add(storeBuilder.buildCorfu(192))
            location.grayStores.add(storeBuilder.buildKosA1(216))
            location.grayStores.add(storeBuilder.buildRhodes(216))
            location.grayStores.add(storeBuilder.buildVenice(72))
            location.grayStores.add(storeBuilder.buildZadar(96))
            location.grayStores.add(storeBuilder.buildSplit(96))
            location.grayStores.add(storeBuilder.buildDubrovnikKristijan(96))
            location.grayStores.add(storeBuilder.buildDubrovnikBWA(96))
            location.grayStores.add(storeBuilder.buildBCM(120))
            location.grayStores.add(storeBuilder.buildTivat(120))
            location.newItemsStores.add(storeBuilder.buildNewItemsStore(72))
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

        ventimigliaLoc.nativeStores.add(storeBuilder.buildAntibes(3, 65))
        ventimigliaLoc.alternativeStores.add(storeBuilder.buildGenova(12, 400))

        sanremoLoc.nativeStores.add(storeBuilder.buildAntibes(3, 80))
        sanremoLoc.alternativeStores.add(storeBuilder.buildGenova(12, 400))

        imperiaLoc.nativeStores.add(storeBuilder.buildAntibes(3, 100))
        imperiaLoc.alternativeStores.add(storeBuilder.buildGenova(12, 400))

        sanremo.addLocation(ventimigliaLoc)
        sanremo.addLocation(sanremoLoc)
        sanremo.addLocation(imperiaLoc)

        sanremo.locations.forEach { location ->
            location.grayStores.add(storeBuilder.buildM55(96))
            location.grayStores.add(storeBuilder.buildPalmaED(96))
            location.grayStores.add(storeBuilder.buildPalmaNG(96))
            location.grayStores.add(storeBuilder.buildIbiza(96))
            location.grayStores.add(storeBuilder.buildOlbiaNA(72))
            location.grayStores.add(storeBuilder.buildOlbiaSYS(72))
            location.grayStores.add(storeBuilder.buildNapoli(72))
            location.grayStores.add(storeBuilder.buildPalermo(120))
            location.grayStores.add(storeBuilder.buildRiposto(120))
            location.grayStores.add(storeBuilder.buildMalta(168))
            location.grayStores.add(storeBuilder.buildAthensSotiris(168))
            location.grayStores.add(storeBuilder.buildAthensH360(168))
            location.grayStores.add(storeBuilder.buildCorfu(192))
            location.grayStores.add(storeBuilder.buildKosA1(216))
            location.grayStores.add(storeBuilder.buildRhodes(216))
            location.grayStores.add(storeBuilder.buildVenice(72))
            location.grayStores.add(storeBuilder.buildZadar(96))
            location.grayStores.add(storeBuilder.buildSplit(96))
            location.grayStores.add(storeBuilder.buildDubrovnikKristijan(96))
            location.grayStores.add(storeBuilder.buildDubrovnikBWA(96))
            location.grayStores.add(storeBuilder.buildBCM(120))
            location.grayStores.add(storeBuilder.buildTivat(120))
            location.newItemsStores.add(storeBuilder.buildNewItemsStore(72))
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

        albengaLoc.nativeStores.add(storeBuilder.buildAntibes(3, 135))
        albengaLoc.alternativeStores.add(storeBuilder.buildGenova(6, 300))

        loanoLoc.nativeStores.add(storeBuilder.buildAntibes(3, 150))
        loanoLoc.alternativeStores.add(storeBuilder.buildGenova(6, 300))

        savonaLoc.nativeStores.add(storeBuilder.buildAntibes(3, 195))
        savonaLoc.alternativeStores.add(storeBuilder.buildGenova(6, 300))

        varazzeLoc.nativeStores.add(storeBuilder.buildAntibes(3, 185))
        varazzeLoc.alternativeStores.add(storeBuilder.buildGenova(6, 300))

        loano.addLocation(albengaLoc)
        loano.addLocation(loanoLoc)
        loano.addLocation(savonaLoc)
        loano.addLocation(varazzeLoc)

        loano.locations.forEach { location ->
            location.grayStores.add(storeBuilder.buildM55(96))
            location.grayStores.add(storeBuilder.buildPalmaED(96))
            location.grayStores.add(storeBuilder.buildPalmaNG(96))
            location.grayStores.add(storeBuilder.buildIbiza(96))
            location.grayStores.add(storeBuilder.buildOlbiaNA(72))
            location.grayStores.add(storeBuilder.buildOlbiaSYS(72))
            location.grayStores.add(storeBuilder.buildNapoli(48))
            location.grayStores.add(storeBuilder.buildPalermo(96))
            location.grayStores.add(storeBuilder.buildRiposto(96))
            location.grayStores.add(storeBuilder.buildMalta(168))
            location.grayStores.add(storeBuilder.buildAthensSotiris(168))
            location.grayStores.add(storeBuilder.buildAthensH360(168))
            location.grayStores.add(storeBuilder.buildCorfu(192))
            location.grayStores.add(storeBuilder.buildKosA1(216))
            location.grayStores.add(storeBuilder.buildRhodes(216))
            location.grayStores.add(storeBuilder.buildVenice(72))
            location.grayStores.add(storeBuilder.buildZadar(96))
            location.grayStores.add(storeBuilder.buildSplit(96))
            location.grayStores.add(storeBuilder.buildDubrovnikKristijan(96))
            location.grayStores.add(storeBuilder.buildDubrovnikBWA(96))
            location.grayStores.add(storeBuilder.buildBCM(120))
            location.grayStores.add(storeBuilder.buildTivat(120))
            location.newItemsStores.add(storeBuilder.buildNewItemsStore(72))
        }

        regions.add(loano)


        // Genoa
        val genoa = Region("Genoa",23)

        val genoaAeroportoLoc = Location("Genoa Aeroporto ")
        val genoaMoloVecchioLoc = Location("Genoa molo vecchio")

        genoaAeroportoLoc.nativeStores.add(storeBuilder.buildAntibes(4, 215))
        genoaAeroportoLoc.alternativeStores.add(storeBuilder.buildGenova(2, 300))

        genoaMoloVecchioLoc.nativeStores.add(storeBuilder.buildAntibes(4, 220))
        genoaMoloVecchioLoc.alternativeStores.add(storeBuilder.buildGenova(2, 300))

        genoa.addLocation(genoaAeroportoLoc)
        genoa.addLocation(genoaMoloVecchioLoc)

        genoa.locations.forEach { location ->
            location.grayStores.add(storeBuilder.buildM55(96))
            location.grayStores.add(storeBuilder.buildPalmaED(96))
            location.grayStores.add(storeBuilder.buildPalmaNG(96))
            location.grayStores.add(storeBuilder.buildIbiza(96))
            location.grayStores.add(storeBuilder.buildOlbiaNA(72))
            location.grayStores.add(storeBuilder.buildOlbiaSYS(72))
            location.grayStores.add(storeBuilder.buildNapoli(48))
            location.grayStores.add(storeBuilder.buildPalermo(96))
            location.grayStores.add(storeBuilder.buildRiposto(96))
            location.grayStores.add(storeBuilder.buildMalta(168))
            location.grayStores.add(storeBuilder.buildAthensSotiris(168))
            location.grayStores.add(storeBuilder.buildAthensH360(168))
            location.grayStores.add(storeBuilder.buildCorfu(192))
            location.grayStores.add(storeBuilder.buildKosA1(216))
            location.grayStores.add(storeBuilder.buildRhodes(216))
            location.grayStores.add(storeBuilder.buildVenice(72))
            location.grayStores.add(storeBuilder.buildZadar(96))
            location.grayStores.add(storeBuilder.buildSplit(96))
            location.grayStores.add(storeBuilder.buildDubrovnikKristijan(96))
            location.grayStores.add(storeBuilder.buildDubrovnikBWA(96))
            location.grayStores.add(storeBuilder.buildBCM(120))
            location.grayStores.add(storeBuilder.buildTivat(120))
            location.newItemsStores.add(storeBuilder.buildNewItemsStore(72))
        }

        regions.add(genoa)


        // Portofino
        val portofino = Region("Portofino",24)

        val portofinoLoc = Location("Portofino")

        portofinoLoc.nativeStores.add(storeBuilder.buildAntibes(4, 260))
        portofinoLoc.alternativeStores.add(storeBuilder.buildGenova(6, 300))

        portofinoLoc.grayStores.add(storeBuilder.buildM55(96))
        portofinoLoc.grayStores.add(storeBuilder.buildPalmaED(96))
        portofinoLoc.grayStores.add(storeBuilder.buildPalmaNG(96))
        portofinoLoc.grayStores.add(storeBuilder.buildIbiza(96))
        portofinoLoc.grayStores.add(storeBuilder.buildOlbiaNA(72))
        portofinoLoc.grayStores.add(storeBuilder.buildOlbiaSYS(72))
        portofinoLoc.grayStores.add(storeBuilder.buildNapoli(48))
        portofinoLoc.grayStores.add(storeBuilder.buildPalermo(96))
        portofinoLoc.grayStores.add(storeBuilder.buildRiposto(96))
        portofinoLoc.grayStores.add(storeBuilder.buildMalta(168))
        portofinoLoc.grayStores.add(storeBuilder.buildAthensSotiris(168))
        portofinoLoc.grayStores.add(storeBuilder.buildAthensH360(168))
        portofinoLoc.grayStores.add(storeBuilder.buildCorfu(192))
        portofinoLoc.grayStores.add(storeBuilder.buildKosA1(216))
        portofinoLoc.grayStores.add(storeBuilder.buildRhodes(216))
        portofinoLoc.grayStores.add(storeBuilder.buildVenice(72))
        portofinoLoc.grayStores.add(storeBuilder.buildZadar(96))
        portofinoLoc.grayStores.add(storeBuilder.buildSplit(96))
        portofinoLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(96))
        portofinoLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(96))
        portofinoLoc.grayStores.add(storeBuilder.buildBCM(120))
        portofinoLoc.grayStores.add(storeBuilder.buildTivat(120))
        portofinoLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(72))

        portofino.addLocation(portofinoLoc)

        regions.add(portofino)


        // La Spezia
        val laSpezia = Region("La Spezia",25)

        val laSpeziaLoc = Location("La Spezia")

        laSpeziaLoc.nativeStores.add(storeBuilder.buildAntibes(6, 330))
        laSpeziaLoc.alternativeStores.add(storeBuilder.buildGenova(6, 300))

        laSpeziaLoc.grayStores.add(storeBuilder.buildM55(96))
        laSpeziaLoc.grayStores.add(storeBuilder.buildPalmaED(96))
        laSpeziaLoc.grayStores.add(storeBuilder.buildPalmaNG(96))
        laSpeziaLoc.grayStores.add(storeBuilder.buildIbiza(96))
        laSpeziaLoc.grayStores.add(storeBuilder.buildOlbiaNA(72))
        laSpeziaLoc.grayStores.add(storeBuilder.buildOlbiaSYS(72))
        laSpeziaLoc.grayStores.add(storeBuilder.buildNapoli(48))
        laSpeziaLoc.grayStores.add(storeBuilder.buildPalermo(96))
        laSpeziaLoc.grayStores.add(storeBuilder.buildRiposto(96))
        laSpeziaLoc.grayStores.add(storeBuilder.buildMalta(168))
        laSpeziaLoc.grayStores.add(storeBuilder.buildAthensSotiris(168))
        laSpeziaLoc.grayStores.add(storeBuilder.buildAthensH360(168))
        laSpeziaLoc.grayStores.add(storeBuilder.buildCorfu(192))
        laSpeziaLoc.grayStores.add(storeBuilder.buildKosA1(216))
        laSpeziaLoc.grayStores.add(storeBuilder.buildRhodes(216))
        laSpeziaLoc.grayStores.add(storeBuilder.buildVenice(72))
        laSpeziaLoc.grayStores.add(storeBuilder.buildZadar(96))
        laSpeziaLoc.grayStores.add(storeBuilder.buildSplit(96))
        laSpeziaLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(96))
        laSpeziaLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(96))
        laSpeziaLoc.grayStores.add(storeBuilder.buildBCM(120))
        laSpeziaLoc.grayStores.add(storeBuilder.buildTivat(120))
        laSpeziaLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(72))

        laSpezia.addLocation(laSpeziaLoc)
        regions.add(laSpezia)


        // Viareggio
        val viareggio = Region("Viareggio",26)

        val viareggioLoc = Location("Viareggio")

        viareggioLoc.nativeStores.add(storeBuilder.buildAntibes(6, 365))
        viareggioLoc.alternativeStores.add(storeBuilder.buildGenova(6, 300))

        viareggioLoc.grayStores.add(storeBuilder.buildM55(96))
        viareggioLoc.grayStores.add(storeBuilder.buildPalmaED(96))
        viareggioLoc.grayStores.add(storeBuilder.buildPalmaNG(96))
        viareggioLoc.grayStores.add(storeBuilder.buildIbiza(96))
        viareggioLoc.grayStores.add(storeBuilder.buildOlbiaNA(72))
        viareggioLoc.grayStores.add(storeBuilder.buildOlbiaSYS(72))
        viareggioLoc.grayStores.add(storeBuilder.buildNapoli(48))
        viareggioLoc.grayStores.add(storeBuilder.buildPalermo(96))
        viareggioLoc.grayStores.add(storeBuilder.buildRiposto(96))
        viareggioLoc.grayStores.add(storeBuilder.buildMalta(168))
        viareggioLoc.grayStores.add(storeBuilder.buildAthensSotiris(168))
        viareggioLoc.grayStores.add(storeBuilder.buildAthensH360(168))
        viareggioLoc.grayStores.add(storeBuilder.buildCorfu(192))
        viareggioLoc.grayStores.add(storeBuilder.buildKosA1(216))
        viareggioLoc.grayStores.add(storeBuilder.buildRhodes(216))
        viareggioLoc.grayStores.add(storeBuilder.buildVenice(72))
        viareggioLoc.grayStores.add(storeBuilder.buildZadar(96))
        viareggioLoc.grayStores.add(storeBuilder.buildSplit(96))
        viareggioLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(96))
        viareggioLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(96))
        viareggioLoc.grayStores.add(storeBuilder.buildBCM(120))
        viareggioLoc.grayStores.add(storeBuilder.buildTivat(120))
        viareggioLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(72))

        viareggio.addLocation(viareggioLoc)
        regions.add(viareggio)


        // Livorno
        val livorno = Region("Livorno",27)

        val livornoLoc = Location("Livorno")

        livornoLoc.nativeStores.add(storeBuilder.buildAntibes(6, 400))
        livornoLoc.alternativeStores.add(storeBuilder.buildGenova(7, 300))

        livornoLoc.grayStores.add(storeBuilder.buildM55(96))
        livornoLoc.grayStores.add(storeBuilder.buildPalmaED(96))
        livornoLoc.grayStores.add(storeBuilder.buildPalmaNG(96))
        livornoLoc.grayStores.add(storeBuilder.buildIbiza(96))
        livornoLoc.grayStores.add(storeBuilder.buildOlbiaNA(72))
        livornoLoc.grayStores.add(storeBuilder.buildOlbiaSYS(72))
        livornoLoc.grayStores.add(storeBuilder.buildNapoli(48))
        livornoLoc.grayStores.add(storeBuilder.buildPalermo(96))
        livornoLoc.grayStores.add(storeBuilder.buildRiposto(96))
        livornoLoc.grayStores.add(storeBuilder.buildMalta(168))
        livornoLoc.grayStores.add(storeBuilder.buildAthensSotiris(168))
        livornoLoc.grayStores.add(storeBuilder.buildAthensH360(168))
        livornoLoc.grayStores.add(storeBuilder.buildCorfu(192))
        livornoLoc.grayStores.add(storeBuilder.buildKosA1(216))
        livornoLoc.grayStores.add(storeBuilder.buildRhodes(216))
        livornoLoc.grayStores.add(storeBuilder.buildVenice(72))
        livornoLoc.grayStores.add(storeBuilder.buildZadar(96))
        livornoLoc.grayStores.add(storeBuilder.buildSplit(96))
        livornoLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(96))
        livornoLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(96))
        livornoLoc.grayStores.add(storeBuilder.buildBCM(120))
        livornoLoc.grayStores.add(storeBuilder.buildTivat(120))
        livornoLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(72))

        livorno.addLocation(livornoLoc)
        regions.add(livorno)


        // Piombino
        val piombino = Region("Piombino",28)

        val piombinoLoc = Location("Piombino")

        piombinoLoc.nativeStores.add(storeBuilder.buildAntibes(7, 485))
        piombinoLoc.alternativeStores.add(storeBuilder.buildGenova(8, 300))

        piombinoLoc.grayStores.add(storeBuilder.buildM55(96))
        piombinoLoc.grayStores.add(storeBuilder.buildPalmaED(96))
        piombinoLoc.grayStores.add(storeBuilder.buildPalmaNG(96))
        piombinoLoc.grayStores.add(storeBuilder.buildIbiza(96))
        piombinoLoc.grayStores.add(storeBuilder.buildOlbiaNA(72))
        piombinoLoc.grayStores.add(storeBuilder.buildOlbiaSYS(72))
        piombinoLoc.grayStores.add(storeBuilder.buildNapoli(48))
        piombinoLoc.grayStores.add(storeBuilder.buildPalermo(96))
        piombinoLoc.grayStores.add(storeBuilder.buildRiposto(96))
        piombinoLoc.grayStores.add(storeBuilder.buildMalta(168))
        piombinoLoc.grayStores.add(storeBuilder.buildAthensSotiris(168))
        piombinoLoc.grayStores.add(storeBuilder.buildAthensH360(168))
        piombinoLoc.grayStores.add(storeBuilder.buildCorfu(192))
        piombinoLoc.grayStores.add(storeBuilder.buildKosA1(216))
        piombinoLoc.grayStores.add(storeBuilder.buildRhodes(216))
        piombinoLoc.grayStores.add(storeBuilder.buildVenice(72))
        piombinoLoc.grayStores.add(storeBuilder.buildZadar(96))
        piombinoLoc.grayStores.add(storeBuilder.buildSplit(96))
        piombinoLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(96))
        piombinoLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(96))
        piombinoLoc.grayStores.add(storeBuilder.buildBCM(120))
        piombinoLoc.grayStores.add(storeBuilder.buildTivat(120))
        piombinoLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(72))

        piombino.addLocation(piombinoLoc)
        regions.add(piombino)


        // Grosseto
        val grosseto = Region("Grosseto",29)

        val grossetoLoc = Location("Grosseto")

        grossetoLoc.nativeStores.add(storeBuilder.buildAntibes(7, 550))
        grossetoLoc.alternativeStores.add(storeBuilder.buildGenova(8, 300))
        grossetoLoc.alternativeStores.add(storeBuilder.buildNapoli(8, 400))

        grossetoLoc.grayStores.add(storeBuilder.buildM55(96))
        grossetoLoc.grayStores.add(storeBuilder.buildPalmaED(96))
        grossetoLoc.grayStores.add(storeBuilder.buildPalmaNG(96))
        grossetoLoc.grayStores.add(storeBuilder.buildIbiza(96))
        grossetoLoc.grayStores.add(storeBuilder.buildOlbiaNA(72))
        grossetoLoc.grayStores.add(storeBuilder.buildOlbiaSYS(72))
        grossetoLoc.grayStores.add(storeBuilder.buildPalermo(96))
        grossetoLoc.grayStores.add(storeBuilder.buildRiposto(96))
        grossetoLoc.grayStores.add(storeBuilder.buildMalta(168))
        grossetoLoc.grayStores.add(storeBuilder.buildAthensSotiris(168))
        grossetoLoc.grayStores.add(storeBuilder.buildAthensH360(168))
        grossetoLoc.grayStores.add(storeBuilder.buildCorfu(192))
        grossetoLoc.grayStores.add(storeBuilder.buildKosA1(216))
        grossetoLoc.grayStores.add(storeBuilder.buildRhodes(216))
        grossetoLoc.grayStores.add(storeBuilder.buildVenice(72))
        grossetoLoc.grayStores.add(storeBuilder.buildZadar(96))
        grossetoLoc.grayStores.add(storeBuilder.buildSplit(96))
        grossetoLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(96))
        grossetoLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(96))
        grossetoLoc.grayStores.add(storeBuilder.buildBCM(120))
        grossetoLoc.grayStores.add(storeBuilder.buildTivat(120))
        grossetoLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(72))

        grosseto.addLocation(grossetoLoc)
        regions.add(grosseto)


        // Argentario
        val argentario = Region("Argentario",30)

        val argentarioLoc = Location("Argentario")

        argentarioLoc.nativeStores.add(storeBuilder.buildAntibes(7, 580))
        argentarioLoc.alternativeStores.add(storeBuilder.buildGenova(9, 300))
        argentarioLoc.alternativeStores.add(storeBuilder.buildNapoli(7, 400))

        argentarioLoc.grayStores.add(storeBuilder.buildM55(96))
        argentarioLoc.grayStores.add(storeBuilder.buildPalmaED(96))
        argentarioLoc.grayStores.add(storeBuilder.buildPalmaNG(96))
        argentarioLoc.grayStores.add(storeBuilder.buildIbiza(96))
        argentarioLoc.grayStores.add(storeBuilder.buildOlbiaNA(72))
        argentarioLoc.grayStores.add(storeBuilder.buildOlbiaSYS(72))
        argentarioLoc.grayStores.add(storeBuilder.buildPalermo(72))
        argentarioLoc.grayStores.add(storeBuilder.buildRiposto(72))
        argentarioLoc.grayStores.add(storeBuilder.buildMalta(168))
        argentarioLoc.grayStores.add(storeBuilder.buildAthensSotiris(168))
        argentarioLoc.grayStores.add(storeBuilder.buildAthensH360(168))
        argentarioLoc.grayStores.add(storeBuilder.buildCorfu(192))
        argentarioLoc.grayStores.add(storeBuilder.buildKosA1(216))
        argentarioLoc.grayStores.add(storeBuilder.buildRhodes(216))
        argentarioLoc.grayStores.add(storeBuilder.buildVenice(72))
        argentarioLoc.grayStores.add(storeBuilder.buildZadar(96))
        argentarioLoc.grayStores.add(storeBuilder.buildSplit(96))
        argentarioLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(96))
        argentarioLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(96))
        argentarioLoc.grayStores.add(storeBuilder.buildBCM(120))
        argentarioLoc.grayStores.add(storeBuilder.buildTivat(120))
        argentarioLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(72))

        argentario.addLocation(argentarioLoc)
        regions.add(argentario)


        // Civitavecchia
        val civitavecchia = Region("Civitavecchia",31)

        val civitavecchiaLoc = Location("Civitavecchia")

        civitavecchiaLoc.nativeStores.add(storeBuilder.buildAntibes(8, 640))
        civitavecchiaLoc.alternativeStores.add(storeBuilder.buildGenova(9, 300))
        civitavecchiaLoc.alternativeStores.add(storeBuilder.buildNapoli(6, 300))

        civitavecchiaLoc.grayStores.add(storeBuilder.buildM55(96))
        civitavecchiaLoc.grayStores.add(storeBuilder.buildPalmaED(96))
        civitavecchiaLoc.grayStores.add(storeBuilder.buildPalmaNG(96))
        civitavecchiaLoc.grayStores.add(storeBuilder.buildIbiza(96))
        civitavecchiaLoc.grayStores.add(storeBuilder.buildOlbiaNA(72))
        civitavecchiaLoc.grayStores.add(storeBuilder.buildOlbiaSYS(72))
        civitavecchiaLoc.grayStores.add(storeBuilder.buildPalermo(72))
        civitavecchiaLoc.grayStores.add(storeBuilder.buildRiposto(72))
        civitavecchiaLoc.grayStores.add(storeBuilder.buildMalta(168))
        civitavecchiaLoc.grayStores.add(storeBuilder.buildAthensSotiris(168))
        civitavecchiaLoc.grayStores.add(storeBuilder.buildAthensH360(168))
        civitavecchiaLoc.grayStores.add(storeBuilder.buildCorfu(192))
        civitavecchiaLoc.grayStores.add(storeBuilder.buildKosA1(216))
        civitavecchiaLoc.grayStores.add(storeBuilder.buildRhodes(216))
        civitavecchiaLoc.grayStores.add(storeBuilder.buildVenice(72))
        civitavecchiaLoc.grayStores.add(storeBuilder.buildZadar(96))
        civitavecchiaLoc.grayStores.add(storeBuilder.buildSplit(96))
        civitavecchiaLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(96))
        civitavecchiaLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(96))
        civitavecchiaLoc.grayStores.add(storeBuilder.buildBCM(120))
        civitavecchiaLoc.grayStores.add(storeBuilder.buildTivat(120))
        civitavecchiaLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(72))

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

        gaetaLoc.nativeStores.add(storeBuilder.buildNapoli(3, 1100))
        gaetaLoc.alternativeStores.add(storeBuilder.buildPalermo(18, 900))
        gaetaLoc.alternativeStores.add(storeBuilder.buildRiposto(24, 900))

        gaetaLoc.grayStores.add(storeBuilder.buildM55(96))
        gaetaLoc.grayStores.add(storeBuilder.buildAntibes(12))
        gaetaLoc.grayStores.add(storeBuilder.buildPalmaED(96))
        gaetaLoc.grayStores.add(storeBuilder.buildPalmaNG(96))
        gaetaLoc.grayStores.add(storeBuilder.buildIbiza(96))
        gaetaLoc.grayStores.add(storeBuilder.buildOlbiaNA(72))
        gaetaLoc.grayStores.add(storeBuilder.buildOlbiaSYS(72))
        gaetaLoc.grayStores.add(storeBuilder.buildGenova(48))
        gaetaLoc.grayStores.add(storeBuilder.buildMalta(168))
        gaetaLoc.grayStores.add(storeBuilder.buildAthensSotiris(168))
        gaetaLoc.grayStores.add(storeBuilder.buildAthensH360(168))
        gaetaLoc.grayStores.add(storeBuilder.buildCorfu(192))
        gaetaLoc.grayStores.add(storeBuilder.buildKosA1(216))
        gaetaLoc.grayStores.add(storeBuilder.buildRhodes(216))
        gaetaLoc.grayStores.add(storeBuilder.buildVenice(72))
        gaetaLoc.grayStores.add(storeBuilder.buildZadar(96))
        gaetaLoc.grayStores.add(storeBuilder.buildSplit(96))
        gaetaLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(96))
        gaetaLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(96))
        gaetaLoc.grayStores.add(storeBuilder.buildBCM(120))
        gaetaLoc.grayStores.add(storeBuilder.buildTivat(120))
        gaetaLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(96))

        gaeta.addLocation(gaetaLoc)
        regions.add(gaeta)


        // Naples
        val naples = Region("Naples",33)

        val naplesLoc = Location("Naples")

        naplesLoc.nativeStores.add(storeBuilder.buildNapoli(1, 1100))
        naplesLoc.alternativeStores.add(storeBuilder.buildPalermo(18, 900))
        naplesLoc.alternativeStores.add(storeBuilder.buildRiposto(24, 900))

        naplesLoc.grayStores.add(storeBuilder.buildM55(96))
        naplesLoc.grayStores.add(storeBuilder.buildAntibes(12))
        naplesLoc.grayStores.add(storeBuilder.buildPalmaED(96))
        naplesLoc.grayStores.add(storeBuilder.buildPalmaNG(96))
        naplesLoc.grayStores.add(storeBuilder.buildIbiza(96))
        naplesLoc.grayStores.add(storeBuilder.buildOlbiaNA(72))
        naplesLoc.grayStores.add(storeBuilder.buildOlbiaSYS(72))
        naplesLoc.grayStores.add(storeBuilder.buildGenova(48))
        naplesLoc.grayStores.add(storeBuilder.buildMalta(168))
        naplesLoc.grayStores.add(storeBuilder.buildAthensSotiris(168))
        naplesLoc.grayStores.add(storeBuilder.buildAthensH360(168))
        naplesLoc.grayStores.add(storeBuilder.buildCorfu(192))
        naplesLoc.grayStores.add(storeBuilder.buildKosA1(216))
        naplesLoc.grayStores.add(storeBuilder.buildRhodes(216))
        naplesLoc.grayStores.add(storeBuilder.buildVenice(72))
        naplesLoc.grayStores.add(storeBuilder.buildZadar(96))
        naplesLoc.grayStores.add(storeBuilder.buildSplit(96))
        naplesLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(96))
        naplesLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(96))
        naplesLoc.grayStores.add(storeBuilder.buildBCM(120))
        naplesLoc.grayStores.add(storeBuilder.buildTivat(120))
        naplesLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(96))

        naples.addLocation(naplesLoc)
        regions.add(naples)


        // Marina di Stabia
        val marinadiStabia = Region("Marina di Stabia",34)

        val marinadiStabiaLoc = Location("Marina di Stabia")

        marinadiStabiaLoc.nativeStores.add(storeBuilder.buildNapoli(2, 1100))
        marinadiStabiaLoc.alternativeStores.add(storeBuilder.buildPalermo(18, 900))
        marinadiStabiaLoc.alternativeStores.add(storeBuilder.buildRiposto(24, 900))

        marinadiStabiaLoc.grayStores.add(storeBuilder.buildM55(96))
        marinadiStabiaLoc.grayStores.add(storeBuilder.buildAntibes(12))
        marinadiStabiaLoc.grayStores.add(storeBuilder.buildPalmaED(96))
        marinadiStabiaLoc.grayStores.add(storeBuilder.buildPalmaNG(96))
        marinadiStabiaLoc.grayStores.add(storeBuilder.buildIbiza(96))
        marinadiStabiaLoc.grayStores.add(storeBuilder.buildOlbiaNA(72))
        marinadiStabiaLoc.grayStores.add(storeBuilder.buildOlbiaSYS(72))
        marinadiStabiaLoc.grayStores.add(storeBuilder.buildGenova(48))
        marinadiStabiaLoc.grayStores.add(storeBuilder.buildMalta(168))
        marinadiStabiaLoc.grayStores.add(storeBuilder.buildAthensSotiris(168))
        marinadiStabiaLoc.grayStores.add(storeBuilder.buildAthensH360(168))
        marinadiStabiaLoc.grayStores.add(storeBuilder.buildCorfu(192))
        marinadiStabiaLoc.grayStores.add(storeBuilder.buildKosA1(216))
        marinadiStabiaLoc.grayStores.add(storeBuilder.buildRhodes(216))
        marinadiStabiaLoc.grayStores.add(storeBuilder.buildVenice(72))
        marinadiStabiaLoc.grayStores.add(storeBuilder.buildZadar(96))
        marinadiStabiaLoc.grayStores.add(storeBuilder.buildSplit(96))
        marinadiStabiaLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(96))
        marinadiStabiaLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(96))
        marinadiStabiaLoc.grayStores.add(storeBuilder.buildBCM(120))
        marinadiStabiaLoc.grayStores.add(storeBuilder.buildTivat(120))
        marinadiStabiaLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(96))

        marinadiStabia.addLocation(marinadiStabiaLoc)

        regions.add(marinadiStabia)


        // Sorrento
        val sorrento = Region("Sorrento",35)

        val sorrentoLoc = Location("Sorrento")

        sorrentoLoc.nativeStores.add(storeBuilder.buildNapoli(3, 1100))
        sorrentoLoc.alternativeStores.add(storeBuilder.buildPalermo(18, 900))
        sorrentoLoc.alternativeStores.add(storeBuilder.buildRiposto(24, 900))

        sorrentoLoc.grayStores.add(storeBuilder.buildM55(96))
        sorrentoLoc.grayStores.add(storeBuilder.buildAntibes(12))
        sorrentoLoc.grayStores.add(storeBuilder.buildPalmaED(96))
        sorrentoLoc.grayStores.add(storeBuilder.buildPalmaNG(96))
        sorrentoLoc.grayStores.add(storeBuilder.buildIbiza(96))
        sorrentoLoc.grayStores.add(storeBuilder.buildOlbiaNA(72))
        sorrentoLoc.grayStores.add(storeBuilder.buildOlbiaSYS(72))
        sorrentoLoc.grayStores.add(storeBuilder.buildGenova(48))
        sorrentoLoc.grayStores.add(storeBuilder.buildMalta(168))
        sorrentoLoc.grayStores.add(storeBuilder.buildAthensSotiris(168))
        sorrentoLoc.grayStores.add(storeBuilder.buildAthensH360(168))
        sorrentoLoc.grayStores.add(storeBuilder.buildCorfu(192))
        sorrentoLoc.grayStores.add(storeBuilder.buildKosA1(216))
        sorrentoLoc.grayStores.add(storeBuilder.buildRhodes(216))
        sorrentoLoc.grayStores.add(storeBuilder.buildVenice(72))
        sorrentoLoc.grayStores.add(storeBuilder.buildZadar(96))
        sorrentoLoc.grayStores.add(storeBuilder.buildSplit(96))
        sorrentoLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(96))
        sorrentoLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(96))
        sorrentoLoc.grayStores.add(storeBuilder.buildBCM(120))
        sorrentoLoc.grayStores.add(storeBuilder.buildTivat(120))
        sorrentoLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(96))

        sorrento.addLocation(sorrentoLoc)
        regions.add(sorrento)


        // Capri
        val capri = Region("Capri",36)

        val capriLoc = Location("Capri")

        capriLoc.nativeStores.add(storeBuilder.buildNapoli(6, 1100))
        capriLoc.alternativeStores.add(storeBuilder.buildPalermo(18, 900))
        capriLoc.alternativeStores.add(storeBuilder.buildRiposto(24, 900))

        capriLoc.grayStores.add(storeBuilder.buildM55(96))
        capriLoc.grayStores.add(storeBuilder.buildAntibes(14))
        capriLoc.grayStores.add(storeBuilder.buildPalmaED(96))
        capriLoc.grayStores.add(storeBuilder.buildPalmaNG(96))
        capriLoc.grayStores.add(storeBuilder.buildIbiza(96))
        capriLoc.grayStores.add(storeBuilder.buildOlbiaNA(72))
        capriLoc.grayStores.add(storeBuilder.buildOlbiaSYS(72))
        capriLoc.grayStores.add(storeBuilder.buildGenova(48))
        capriLoc.grayStores.add(storeBuilder.buildMalta(168))
        capriLoc.grayStores.add(storeBuilder.buildAthensSotiris(168))
        capriLoc.grayStores.add(storeBuilder.buildAthensH360(168))
        capriLoc.grayStores.add(storeBuilder.buildCorfu(192))
        capriLoc.grayStores.add(storeBuilder.buildKosA1(216))
        capriLoc.grayStores.add(storeBuilder.buildRhodes(216))
        capriLoc.grayStores.add(storeBuilder.buildVenice(72))
        capriLoc.grayStores.add(storeBuilder.buildZadar(96))
        capriLoc.grayStores.add(storeBuilder.buildSplit(96))
        capriLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(96))
        capriLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(96))
        capriLoc.grayStores.add(storeBuilder.buildBCM(120))
        capriLoc.grayStores.add(storeBuilder.buildTivat(120))
        capriLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(96))

        capri.addLocation(capriLoc)
        regions.add(capri)


        // Positano
        val positano = Region("Positano",37)

        val positanoLoc = Location("Positano")

        positanoLoc.nativeStores.add(storeBuilder.buildNapoli(3, 1100))
        positanoLoc.alternativeStores.add(storeBuilder.buildPalermo(18, 900))
        positanoLoc.alternativeStores.add(storeBuilder.buildRiposto(24, 900))

        positanoLoc.grayStores.add(storeBuilder.buildM55(96))
        positanoLoc.grayStores.add(storeBuilder.buildAntibes(12))
        positanoLoc.grayStores.add(storeBuilder.buildPalmaED(96))
        positanoLoc.grayStores.add(storeBuilder.buildPalmaNG(96))
        positanoLoc.grayStores.add(storeBuilder.buildIbiza(96))
        positanoLoc.grayStores.add(storeBuilder.buildOlbiaNA(72))
        positanoLoc.grayStores.add(storeBuilder.buildOlbiaSYS(72))
        positanoLoc.grayStores.add(storeBuilder.buildGenova(48))
        positanoLoc.grayStores.add(storeBuilder.buildMalta(168))
        positanoLoc.grayStores.add(storeBuilder.buildAthensSotiris(168))
        positanoLoc.grayStores.add(storeBuilder.buildAthensH360(168))
        positanoLoc.grayStores.add(storeBuilder.buildCorfu(192))
        positanoLoc.grayStores.add(storeBuilder.buildKosA1(216))
        positanoLoc.grayStores.add(storeBuilder.buildRhodes(216))
        positanoLoc.grayStores.add(storeBuilder.buildVenice(72))
        positanoLoc.grayStores.add(storeBuilder.buildZadar(96))
        positanoLoc.grayStores.add(storeBuilder.buildSplit(96))
        positanoLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(96))
        positanoLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(96))
        positanoLoc.grayStores.add(storeBuilder.buildBCM(120))
        positanoLoc.grayStores.add(storeBuilder.buildTivat(120))
        positanoLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(96))

        positano.addLocation(positanoLoc)
        regions.add(positano)


        // Amalfi
        val amalfi = Region("Amalfi",38)

        val amalfiLoc = Location("Amalfi")

        amalfiLoc.nativeStores.add(storeBuilder.buildNapoli(3, 1100))
        amalfiLoc.alternativeStores.add(storeBuilder.buildPalermo(18, 900))
        amalfiLoc.alternativeStores.add(storeBuilder.buildRiposto(24, 900))

        amalfiLoc.grayStores.add(storeBuilder.buildM55(96))
        amalfiLoc.grayStores.add(storeBuilder.buildAntibes(14))
        amalfiLoc.grayStores.add(storeBuilder.buildPalmaED(96))
        amalfiLoc.grayStores.add(storeBuilder.buildPalmaNG(96))
        amalfiLoc.grayStores.add(storeBuilder.buildIbiza(96))
        amalfiLoc.grayStores.add(storeBuilder.buildOlbiaNA(72))
        amalfiLoc.grayStores.add(storeBuilder.buildOlbiaSYS(72))
        amalfiLoc.grayStores.add(storeBuilder.buildGenova(48))
        amalfiLoc.grayStores.add(storeBuilder.buildMalta(168))
        amalfiLoc.grayStores.add(storeBuilder.buildAthensSotiris(168))
        amalfiLoc.grayStores.add(storeBuilder.buildAthensH360(168))
        amalfiLoc.grayStores.add(storeBuilder.buildCorfu(192))
        amalfiLoc.grayStores.add(storeBuilder.buildKosA1(216))
        amalfiLoc.grayStores.add(storeBuilder.buildRhodes(216))
        amalfiLoc.grayStores.add(storeBuilder.buildVenice(72))
        amalfiLoc.grayStores.add(storeBuilder.buildZadar(96))
        amalfiLoc.grayStores.add(storeBuilder.buildSplit(96))
        amalfiLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(96))
        amalfiLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(96))
        amalfiLoc.grayStores.add(storeBuilder.buildBCM(120))
        amalfiLoc.grayStores.add(storeBuilder.buildTivat(120))
        amalfiLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(96))

        amalfi.addLocation(amalfiLoc)
        regions.add(amalfi)


        // Salerno
        val salerno = Region("Salerno",39)

        val salernoLoc = Location("Salerno")

        salernoLoc.nativeStores.add(storeBuilder.buildNapoli(3, 1100))
        salernoLoc.alternativeStores.add(storeBuilder.buildPalermo(18, 900))
        salernoLoc.alternativeStores.add(storeBuilder.buildRiposto(24, 900))

        salernoLoc.grayStores.add(storeBuilder.buildM55(96))
        salernoLoc.grayStores.add(storeBuilder.buildAntibes(14))
        salernoLoc.grayStores.add(storeBuilder.buildPalmaED(96))
        salernoLoc.grayStores.add(storeBuilder.buildPalmaNG(96))
        salernoLoc.grayStores.add(storeBuilder.buildIbiza(96))
        salernoLoc.grayStores.add(storeBuilder.buildOlbiaNA(72))
        salernoLoc.grayStores.add(storeBuilder.buildOlbiaSYS(72))
        salernoLoc.grayStores.add(storeBuilder.buildGenova(48))
        salernoLoc.grayStores.add(storeBuilder.buildMalta(168))
        salernoLoc.grayStores.add(storeBuilder.buildAthensSotiris(168))
        salernoLoc.grayStores.add(storeBuilder.buildAthensH360(168))
        salernoLoc.grayStores.add(storeBuilder.buildCorfu(192))
        salernoLoc.grayStores.add(storeBuilder.buildKosA1(216))
        salernoLoc.grayStores.add(storeBuilder.buildRhodes(216))
        salernoLoc.grayStores.add(storeBuilder.buildVenice(72))
        salernoLoc.grayStores.add(storeBuilder.buildZadar(96))
        salernoLoc.grayStores.add(storeBuilder.buildSplit(96))
        salernoLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(96))
        salernoLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(96))
        salernoLoc.grayStores.add(storeBuilder.buildBCM(120))
        salernoLoc.grayStores.add(storeBuilder.buildTivat(120))
        salernoLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(96))

        salerno.addLocation(salernoLoc)
        regions.add(salerno)

        return regions.toList()
    }

    override fun buildSouthOfItaly(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        // Tropea
        val tropea = Region("Tropea",40)

        val tropeaLoc = Location("Tropea")

        tropeaLoc.nativeStores.add(storeBuilder.buildNapoli(8, 600))
        tropeaLoc.alternativeStores.add(storeBuilder.buildPalermo(12, 500))
        tropeaLoc.alternativeStores.add(storeBuilder.buildRiposto(12, 500))

        tropeaLoc.grayStores.add(storeBuilder.buildM55(120))
        tropeaLoc.grayStores.add(storeBuilder.buildAntibes(22))
        tropeaLoc.grayStores.add(storeBuilder.buildPalmaED(120))
        tropeaLoc.grayStores.add(storeBuilder.buildPalmaNG(120))
        tropeaLoc.grayStores.add(storeBuilder.buildIbiza(120))
        tropeaLoc.grayStores.add(storeBuilder.buildOlbiaNA(72))
        tropeaLoc.grayStores.add(storeBuilder.buildOlbiaSYS(72))
        tropeaLoc.grayStores.add(storeBuilder.buildGenova(72))
        tropeaLoc.grayStores.add(storeBuilder.buildMalta(168))
        tropeaLoc.grayStores.add(storeBuilder.buildAthensSotiris(192))
        tropeaLoc.grayStores.add(storeBuilder.buildAthensH360(192))
        tropeaLoc.grayStores.add(storeBuilder.buildCorfu(192))
        tropeaLoc.grayStores.add(storeBuilder.buildKosA1(216))
        tropeaLoc.grayStores.add(storeBuilder.buildRhodes(216))
        tropeaLoc.grayStores.add(storeBuilder.buildVenice(48))
        tropeaLoc.grayStores.add(storeBuilder.buildZadar(144))
        tropeaLoc.grayStores.add(storeBuilder.buildSplit(144))
        tropeaLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(144))
        tropeaLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(144))
        tropeaLoc.grayStores.add(storeBuilder.buildBCM(216))
        tropeaLoc.grayStores.add(storeBuilder.buildTivat(216))
        tropeaLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(120))

        tropea.addLocation(tropeaLoc)
        regions.add(tropea)


        // Reggio di Calabria
        val reggioDiCalabria = Region("Reggio di Calabria",41)

        val reggioDiCalabriaLoc = Location("Reggio di Calabria")

        reggioDiCalabriaLoc.nativeStores.add(storeBuilder.buildNapoli(8, 600))
        reggioDiCalabriaLoc.alternativeStores.add(storeBuilder.buildPalermo(12, 500))
        reggioDiCalabriaLoc.alternativeStores.add(storeBuilder.buildRiposto(12, 500))

        reggioDiCalabriaLoc.grayStores.add(storeBuilder.buildM55(120))
        reggioDiCalabriaLoc.grayStores.add(storeBuilder.buildAntibes(24))
        reggioDiCalabriaLoc.grayStores.add(storeBuilder.buildPalmaED(120))
        reggioDiCalabriaLoc.grayStores.add(storeBuilder.buildPalmaNG(120))
        reggioDiCalabriaLoc.grayStores.add(storeBuilder.buildIbiza(120))
        reggioDiCalabriaLoc.grayStores.add(storeBuilder.buildOlbiaNA(72))
        reggioDiCalabriaLoc.grayStores.add(storeBuilder.buildOlbiaSYS(72))
        reggioDiCalabriaLoc.grayStores.add(storeBuilder.buildGenova(72))
        reggioDiCalabriaLoc.grayStores.add(storeBuilder.buildMalta(168))
        reggioDiCalabriaLoc.grayStores.add(storeBuilder.buildAthensSotiris(192))
        reggioDiCalabriaLoc.grayStores.add(storeBuilder.buildAthensH360(192))
        reggioDiCalabriaLoc.grayStores.add(storeBuilder.buildCorfu(192))
        reggioDiCalabriaLoc.grayStores.add(storeBuilder.buildKosA1(216))
        reggioDiCalabriaLoc.grayStores.add(storeBuilder.buildRhodes(216))
        reggioDiCalabriaLoc.grayStores.add(storeBuilder.buildVenice(48))
        reggioDiCalabriaLoc.grayStores.add(storeBuilder.buildZadar(144))
        reggioDiCalabriaLoc.grayStores.add(storeBuilder.buildSplit(144))
        reggioDiCalabriaLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(144))
        reggioDiCalabriaLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(144))
        reggioDiCalabriaLoc.grayStores.add(storeBuilder.buildBCM(216))
        reggioDiCalabriaLoc.grayStores.add(storeBuilder.buildTivat(216))
        reggioDiCalabriaLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(120))

        reggioDiCalabria.addLocation(reggioDiCalabriaLoc)
        regions.add(reggioDiCalabria)


        // Brindisi
        val brindisi = Region("Brindisi",42)

        val brindisiLoc = Location("Brindisi")

        brindisiLoc.nativeStores.add(storeBuilder.buildNapoli(8, 1000))
        brindisiLoc.alternativeStores.add(storeBuilder.buildCorfu(24, 900))

        brindisiLoc.grayStores.add(storeBuilder.buildM55(120))
        brindisiLoc.grayStores.add(storeBuilder.buildAntibes(18))
        brindisiLoc.grayStores.add(storeBuilder.buildPalmaED(120))
        brindisiLoc.grayStores.add(storeBuilder.buildPalmaNG(120))
        brindisiLoc.grayStores.add(storeBuilder.buildIbiza(120))
        brindisiLoc.grayStores.add(storeBuilder.buildOlbiaNA(72))
        brindisiLoc.grayStores.add(storeBuilder.buildOlbiaSYS(72))
        brindisiLoc.grayStores.add(storeBuilder.buildGenova(72))
        brindisiLoc.grayStores.add(storeBuilder.buildPalermo(96))
        brindisiLoc.grayStores.add(storeBuilder.buildRiposto(96))
        brindisiLoc.grayStores.add(storeBuilder.buildMalta(168))
        brindisiLoc.grayStores.add(storeBuilder.buildAthensSotiris(192))
        brindisiLoc.grayStores.add(storeBuilder.buildAthensH360(192))
        brindisiLoc.grayStores.add(storeBuilder.buildKosA1(216))
        brindisiLoc.grayStores.add(storeBuilder.buildRhodes(216))
        brindisiLoc.grayStores.add(storeBuilder.buildVenice(48))
        brindisiLoc.grayStores.add(storeBuilder.buildZadar(144))
        brindisiLoc.grayStores.add(storeBuilder.buildSplit(144))
        brindisiLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(144))
        brindisiLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(144))
        brindisiLoc.grayStores.add(storeBuilder.buildBCM(216))
        brindisiLoc.grayStores.add(storeBuilder.buildTivat(216))
        brindisiLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(120))

        brindisi.addLocation(brindisiLoc)
        regions.add(brindisi)


        // Otranto
        val otranto = Region("Otranto",43)

        val otrantoLoc = Location("Otranto")

        otrantoLoc.nativeStores.add(storeBuilder.buildNapoli(9, 1000))
        otrantoLoc.alternativeStores.add(storeBuilder.buildCorfu(24, 900))

        otrantoLoc.grayStores.add(storeBuilder.buildM55(120))
        otrantoLoc.grayStores.add(storeBuilder.buildAntibes(18))
        otrantoLoc.grayStores.add(storeBuilder.buildPalmaED(120))
        otrantoLoc.grayStores.add(storeBuilder.buildPalmaNG(120))
        otrantoLoc.grayStores.add(storeBuilder.buildIbiza(120))
        otrantoLoc.grayStores.add(storeBuilder.buildOlbiaNA(72))
        otrantoLoc.grayStores.add(storeBuilder.buildOlbiaSYS(72))
        otrantoLoc.grayStores.add(storeBuilder.buildGenova(72))
        otrantoLoc.grayStores.add(storeBuilder.buildPalermo(96))
        otrantoLoc.grayStores.add(storeBuilder.buildRiposto(96))
        otrantoLoc.grayStores.add(storeBuilder.buildMalta(168))
        otrantoLoc.grayStores.add(storeBuilder.buildAthensSotiris(192))
        otrantoLoc.grayStores.add(storeBuilder.buildAthensH360(192))
        otrantoLoc.grayStores.add(storeBuilder.buildKosA1(216))
        otrantoLoc.grayStores.add(storeBuilder.buildRhodes(216))
        otrantoLoc.grayStores.add(storeBuilder.buildVenice(48))
        otrantoLoc.grayStores.add(storeBuilder.buildZadar(144))
        otrantoLoc.grayStores.add(storeBuilder.buildSplit(144))
        otrantoLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(144))
        otrantoLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(144))
        otrantoLoc.grayStores.add(storeBuilder.buildBCM(216))
        otrantoLoc.grayStores.add(storeBuilder.buildTivat(216))
        otrantoLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(120))

        otranto.addLocation(otrantoLoc)
        regions.add(otranto)


        // Bari
        val bari = Region("Bari",44)

        val bariLoc = Location("Bari")

        bariLoc.nativeStores.add(storeBuilder.buildNapoli(6, 1000))
        bariLoc.alternativeStores.add(storeBuilder.buildCorfu(24, 900))

        bariLoc.grayStores.add(storeBuilder.buildM55(120))
        bariLoc.grayStores.add(storeBuilder.buildAntibes(18))
        bariLoc.grayStores.add(storeBuilder.buildPalmaED(120))
        bariLoc.grayStores.add(storeBuilder.buildPalmaNG(120))
        bariLoc.grayStores.add(storeBuilder.buildIbiza(120))
        bariLoc.grayStores.add(storeBuilder.buildOlbiaNA(72))
        bariLoc.grayStores.add(storeBuilder.buildOlbiaSYS(72))
        bariLoc.grayStores.add(storeBuilder.buildGenova(72))
        bariLoc.grayStores.add(storeBuilder.buildPalermo(96))
        bariLoc.grayStores.add(storeBuilder.buildRiposto(96))
        bariLoc.grayStores.add(storeBuilder.buildMalta(168))
        bariLoc.grayStores.add(storeBuilder.buildAthensSotiris(192))
        bariLoc.grayStores.add(storeBuilder.buildAthensH360(192))
        bariLoc.grayStores.add(storeBuilder.buildKosA1(216))
        bariLoc.grayStores.add(storeBuilder.buildRhodes(216))
        bariLoc.grayStores.add(storeBuilder.buildVenice(48))
        bariLoc.grayStores.add(storeBuilder.buildZadar(144))
        bariLoc.grayStores.add(storeBuilder.buildSplit(144))
        bariLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(144))
        bariLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(144))
        bariLoc.grayStores.add(storeBuilder.buildBCM(216))
        bariLoc.grayStores.add(storeBuilder.buildTivat(216))
        bariLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(120))

        bari.addLocation(bariLoc)
        regions.add(bari)

        return regions.toList()
    }

    override fun buildSicily(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        // Palermo
        val palermo = Region("Palermo",45)

        val palermoLoc = Location("Palermo")

        palermoLoc.nativeStores.add(storeBuilder.buildRiposto(5, 150))
        palermoLoc.nativeStores.add(storeBuilder.buildPalermo(1, 150))
        palermoLoc.alternativeStores.add(storeBuilder.buildNapoli(18, 1200))

        palermoLoc.grayStores.add(storeBuilder.buildM55(168))
        palermoLoc.grayStores.add(storeBuilder.buildAntibes(168))
        palermoLoc.grayStores.add(storeBuilder.buildPalmaED(120))
        palermoLoc.grayStores.add(storeBuilder.buildPalmaNG(120))
        palermoLoc.grayStores.add(storeBuilder.buildIbiza(120))
        palermoLoc.grayStores.add(storeBuilder.buildOlbiaNA(96))
        palermoLoc.grayStores.add(storeBuilder.buildOlbiaSYS(96))
        palermoLoc.grayStores.add(storeBuilder.buildGenova(96))
        palermoLoc.grayStores.add(storeBuilder.buildMalta(72))
        palermoLoc.grayStores.add(storeBuilder.buildAthensSotiris(192))
        palermoLoc.grayStores.add(storeBuilder.buildAthensH360(192))
        palermoLoc.grayStores.add(storeBuilder.buildCorfu(192))
        palermoLoc.grayStores.add(storeBuilder.buildKosA1(216))
        palermoLoc.grayStores.add(storeBuilder.buildRhodes(216))
        palermoLoc.grayStores.add(storeBuilder.buildVenice(120))
        palermoLoc.grayStores.add(storeBuilder.buildZadar(192))
        palermoLoc.grayStores.add(storeBuilder.buildSplit(192))
        palermoLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(192))
        palermoLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(192))
        palermoLoc.grayStores.add(storeBuilder.buildBCM(216))
        palermoLoc.grayStores.add(storeBuilder.buildTivat(216))
        palermoLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(144))

        palermo.addLocation(palermoLoc)
        regions.add(palermo)


        // Millazzo
        val millazzo = Region("Millazzo",46)

        val millazzoLoc = Location("Millazzo")

        millazzoLoc.nativeStores.add(storeBuilder.buildRiposto(3, 150))
        millazzoLoc.nativeStores.add(storeBuilder.buildPalermo(4, 150))
        millazzoLoc.alternativeStores.add(storeBuilder.buildNapoli(18, 1200))

        millazzoLoc.grayStores.add(storeBuilder.buildM55(168))
        millazzoLoc.grayStores.add(storeBuilder.buildAntibes(168))
        millazzoLoc.grayStores.add(storeBuilder.buildPalmaED(120))
        millazzoLoc.grayStores.add(storeBuilder.buildPalmaNG(120))
        millazzoLoc.grayStores.add(storeBuilder.buildIbiza(120))
        millazzoLoc.grayStores.add(storeBuilder.buildOlbiaNA(96))
        millazzoLoc.grayStores.add(storeBuilder.buildOlbiaSYS(96))
        millazzoLoc.grayStores.add(storeBuilder.buildGenova(96))
        millazzoLoc.grayStores.add(storeBuilder.buildMalta(72))
        millazzoLoc.grayStores.add(storeBuilder.buildAthensSotiris(192))
        millazzoLoc.grayStores.add(storeBuilder.buildAthensH360(192))
        millazzoLoc.grayStores.add(storeBuilder.buildCorfu(192))
        millazzoLoc.grayStores.add(storeBuilder.buildKosA1(216))
        millazzoLoc.grayStores.add(storeBuilder.buildRhodes(216))
        millazzoLoc.grayStores.add(storeBuilder.buildVenice(120))
        millazzoLoc.grayStores.add(storeBuilder.buildZadar(192))
        millazzoLoc.grayStores.add(storeBuilder.buildSplit(192))
        millazzoLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(192))
        millazzoLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(192))
        millazzoLoc.grayStores.add(storeBuilder.buildBCM(216))
        millazzoLoc.grayStores.add(storeBuilder.buildTivat(216))
        millazzoLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(144))

        millazzo.addLocation(millazzoLoc)
        regions.add(millazzo)


        // Messina
        val messina = Region("Messina",47)

        val messinaLoc = Location("Messina")

        messinaLoc.nativeStores.add(storeBuilder.buildRiposto(2, 150))
        messinaLoc.nativeStores.add(storeBuilder.buildPalermo(4, 150))
        messinaLoc.alternativeStores.add(storeBuilder.buildNapoli(18, 1200))

        messinaLoc.grayStores.add(storeBuilder.buildM55(168))
        messinaLoc.grayStores.add(storeBuilder.buildAntibes(168))
        messinaLoc.grayStores.add(storeBuilder.buildPalmaED(120))
        messinaLoc.grayStores.add(storeBuilder.buildPalmaNG(120))
        messinaLoc.grayStores.add(storeBuilder.buildIbiza(120))
        messinaLoc.grayStores.add(storeBuilder.buildOlbiaNA(96))
        messinaLoc.grayStores.add(storeBuilder.buildOlbiaSYS(96))
        messinaLoc.grayStores.add(storeBuilder.buildGenova(96))
        messinaLoc.grayStores.add(storeBuilder.buildMalta(72))
        messinaLoc.grayStores.add(storeBuilder.buildAthensSotiris(192))
        messinaLoc.grayStores.add(storeBuilder.buildAthensH360(192))
        messinaLoc.grayStores.add(storeBuilder.buildCorfu(192))
        messinaLoc.grayStores.add(storeBuilder.buildKosA1(216))
        messinaLoc.grayStores.add(storeBuilder.buildRhodes(216))
        messinaLoc.grayStores.add(storeBuilder.buildVenice(120))
        messinaLoc.grayStores.add(storeBuilder.buildZadar(192))
        messinaLoc.grayStores.add(storeBuilder.buildSplit(192))
        messinaLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(192))
        messinaLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(192))
        messinaLoc.grayStores.add(storeBuilder.buildBCM(216))
        messinaLoc.grayStores.add(storeBuilder.buildTivat(216))
        messinaLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(144))

        messina.addLocation(messinaLoc)
        regions.add(messina)


        // Taormina
        val taormina = Region("Taormina",48)

        val taorminaLoc = Location("Taormina")

        taorminaLoc.nativeStores.add(storeBuilder.buildRiposto(2, 150))
        taorminaLoc.nativeStores.add(storeBuilder.buildPalermo(5, 150))
        taorminaLoc.alternativeStores.add(storeBuilder.buildNapoli(18, 1200))

        taorminaLoc.grayStores.add(storeBuilder.buildM55(168))
        taorminaLoc.grayStores.add(storeBuilder.buildAntibes(168))
        taorminaLoc.grayStores.add(storeBuilder.buildPalmaED(120))
        taorminaLoc.grayStores.add(storeBuilder.buildPalmaNG(120))
        taorminaLoc.grayStores.add(storeBuilder.buildIbiza(120))
        taorminaLoc.grayStores.add(storeBuilder.buildOlbiaNA(96))
        taorminaLoc.grayStores.add(storeBuilder.buildOlbiaSYS(96))
        taorminaLoc.grayStores.add(storeBuilder.buildGenova(96))
        taorminaLoc.grayStores.add(storeBuilder.buildMalta(72))
        taorminaLoc.grayStores.add(storeBuilder.buildAthensSotiris(192))
        taorminaLoc.grayStores.add(storeBuilder.buildAthensH360(192))
        taorminaLoc.grayStores.add(storeBuilder.buildCorfu(192))
        taorminaLoc.grayStores.add(storeBuilder.buildKosA1(216))
        taorminaLoc.grayStores.add(storeBuilder.buildRhodes(216))
        taorminaLoc.grayStores.add(storeBuilder.buildVenice(120))
        taorminaLoc.grayStores.add(storeBuilder.buildZadar(192))
        taorminaLoc.grayStores.add(storeBuilder.buildSplit(192))
        taorminaLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(192))
        taorminaLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(192))
        taorminaLoc.grayStores.add(storeBuilder.buildBCM(216))
        taorminaLoc.grayStores.add(storeBuilder.buildTivat(216))
        taorminaLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(144))

        taormina.addLocation(taorminaLoc)
        regions.add(taormina)


        // Riposto
        val riposto = Region("Riposto",49)

        val ripostoLoc = Location("Riposto")

        ripostoLoc.nativeStores.add(storeBuilder.buildRiposto(1, 150))
        ripostoLoc.nativeStores.add(storeBuilder.buildPalermo(5, 150))
        ripostoLoc.alternativeStores.add(storeBuilder.buildNapoli(18, 1200))

        ripostoLoc.grayStores.add(storeBuilder.buildM55(168))
        ripostoLoc.grayStores.add(storeBuilder.buildAntibes(168))
        ripostoLoc.grayStores.add(storeBuilder.buildPalmaED(120))
        ripostoLoc.grayStores.add(storeBuilder.buildPalmaNG(120))
        ripostoLoc.grayStores.add(storeBuilder.buildIbiza(120))
        ripostoLoc.grayStores.add(storeBuilder.buildOlbiaNA(96))
        ripostoLoc.grayStores.add(storeBuilder.buildOlbiaSYS(96))
        ripostoLoc.grayStores.add(storeBuilder.buildGenova(96))
        ripostoLoc.grayStores.add(storeBuilder.buildMalta(72))
        ripostoLoc.grayStores.add(storeBuilder.buildAthensSotiris(192))
        ripostoLoc.grayStores.add(storeBuilder.buildAthensH360(192))
        ripostoLoc.grayStores.add(storeBuilder.buildCorfu(192))
        ripostoLoc.grayStores.add(storeBuilder.buildKosA1(216))
        ripostoLoc.grayStores.add(storeBuilder.buildRhodes(216))
        ripostoLoc.grayStores.add(storeBuilder.buildVenice(120))
        ripostoLoc.grayStores.add(storeBuilder.buildZadar(192))
        ripostoLoc.grayStores.add(storeBuilder.buildSplit(192))
        ripostoLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(192))
        ripostoLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(192))
        ripostoLoc.grayStores.add(storeBuilder.buildBCM(216))
        ripostoLoc.grayStores.add(storeBuilder.buildTivat(216))
        ripostoLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(144))

        riposto.addLocation(ripostoLoc)
        regions.add(riposto)


        // Catania
        val catania = Region("Catania",50)

        val cataniaLoc = Location("Catania")

        cataniaLoc.nativeStores.add(storeBuilder.buildRiposto(2, 150))
        cataniaLoc.nativeStores.add(storeBuilder.buildPalermo(4, 150))
        cataniaLoc.alternativeStores.add(storeBuilder.buildNapoli(18, 1200))

        cataniaLoc.grayStores.add(storeBuilder.buildM55(168))
        cataniaLoc.grayStores.add(storeBuilder.buildAntibes(168))
        cataniaLoc.grayStores.add(storeBuilder.buildPalmaED(120))
        cataniaLoc.grayStores.add(storeBuilder.buildPalmaNG(120))
        cataniaLoc.grayStores.add(storeBuilder.buildIbiza(120))
        cataniaLoc.grayStores.add(storeBuilder.buildOlbiaNA(96))
        cataniaLoc.grayStores.add(storeBuilder.buildOlbiaSYS(96))
        cataniaLoc.grayStores.add(storeBuilder.buildGenova(96))
        cataniaLoc.grayStores.add(storeBuilder.buildMalta(72))
        cataniaLoc.grayStores.add(storeBuilder.buildAthensSotiris(192))
        cataniaLoc.grayStores.add(storeBuilder.buildAthensH360(192))
        cataniaLoc.grayStores.add(storeBuilder.buildCorfu(192))
        cataniaLoc.grayStores.add(storeBuilder.buildKosA1(216))
        cataniaLoc.grayStores.add(storeBuilder.buildRhodes(216))
        cataniaLoc.grayStores.add(storeBuilder.buildVenice(120))
        cataniaLoc.grayStores.add(storeBuilder.buildZadar(192))
        cataniaLoc.grayStores.add(storeBuilder.buildSplit(192))
        cataniaLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(192))
        cataniaLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(192))
        cataniaLoc.grayStores.add(storeBuilder.buildBCM(216))
        cataniaLoc.grayStores.add(storeBuilder.buildTivat(216))
        cataniaLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(144))

        catania.addLocation(cataniaLoc)
        regions.add(catania)


        // Syracuse
        val syracuse = Region("Syracuse",51)

        val syracuseLoc = Location("Syracuse")

        syracuseLoc.nativeStores.add(storeBuilder.buildRiposto(2, 150))
        syracuseLoc.nativeStores.add(storeBuilder.buildPalermo(5, 150))
        syracuseLoc.alternativeStores.add(storeBuilder.buildNapoli(18, 1200))

        syracuseLoc.grayStores.add(storeBuilder.buildM55(168))
        syracuseLoc.grayStores.add(storeBuilder.buildAntibes(168))
        syracuseLoc.grayStores.add(storeBuilder.buildPalmaED(120))
        syracuseLoc.grayStores.add(storeBuilder.buildPalmaNG(120))
        syracuseLoc.grayStores.add(storeBuilder.buildIbiza(120))
        syracuseLoc.grayStores.add(storeBuilder.buildOlbiaNA(96))
        syracuseLoc.grayStores.add(storeBuilder.buildOlbiaSYS(96))
        syracuseLoc.grayStores.add(storeBuilder.buildGenova(96))
        syracuseLoc.grayStores.add(storeBuilder.buildMalta(72))
        syracuseLoc.grayStores.add(storeBuilder.buildAthensSotiris(192))
        syracuseLoc.grayStores.add(storeBuilder.buildAthensH360(192))
        syracuseLoc.grayStores.add(storeBuilder.buildCorfu(192))
        syracuseLoc.grayStores.add(storeBuilder.buildKosA1(216))
        syracuseLoc.grayStores.add(storeBuilder.buildRhodes(216))
        syracuseLoc.grayStores.add(storeBuilder.buildVenice(120))
        syracuseLoc.grayStores.add(storeBuilder.buildZadar(192))
        syracuseLoc.grayStores.add(storeBuilder.buildSplit(192))
        syracuseLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(192))
        syracuseLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(192))
        syracuseLoc.grayStores.add(storeBuilder.buildBCM(216))
        syracuseLoc.grayStores.add(storeBuilder.buildTivat(216))
        syracuseLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(144))

        syracuse.addLocation(syracuseLoc)
        regions.add(syracuse)

        return regions.toList()
    }

    override fun buildMalta(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        // Malta
        val maltaIsland = Region("Malta Island",52)

        val maltaIslandLoc = Location("Malta Island")

        maltaIslandLoc.nativeStores.add(storeBuilder.buildMalta(2, 50))
        maltaIslandLoc.alternativeStores.add(storeBuilder.buildRiposto(48, 500))
        maltaIslandLoc.alternativeStores.add(storeBuilder.buildPalermo(48, 500))

        maltaIslandLoc.grayStores.add(storeBuilder.buildM55(168))
        maltaIslandLoc.grayStores.add(storeBuilder.buildAntibes(168))
        maltaIslandLoc.grayStores.add(storeBuilder.buildPalmaED(168))
        maltaIslandLoc.grayStores.add(storeBuilder.buildPalmaNG(168))
        maltaIslandLoc.grayStores.add(storeBuilder.buildIbiza(168))
        maltaIslandLoc.grayStores.add(storeBuilder.buildOlbiaNA(168))
        maltaIslandLoc.grayStores.add(storeBuilder.buildOlbiaSYS(168))
        maltaIslandLoc.grayStores.add(storeBuilder.buildGenova(168))
        maltaIslandLoc.grayStores.add(storeBuilder.buildNapoli(168))
        maltaIslandLoc.grayStores.add(storeBuilder.buildAthensSotiris(192))
        maltaIslandLoc.grayStores.add(storeBuilder.buildAthensH360(192))
        maltaIslandLoc.grayStores.add(storeBuilder.buildCorfu(192))
        maltaIslandLoc.grayStores.add(storeBuilder.buildKosA1(216))
        maltaIslandLoc.grayStores.add(storeBuilder.buildRhodes(216))
        maltaIslandLoc.grayStores.add(storeBuilder.buildVenice(192))
        maltaIslandLoc.grayStores.add(storeBuilder.buildZadar(240))
        maltaIslandLoc.grayStores.add(storeBuilder.buildSplit(240))
        maltaIslandLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(240))
        maltaIslandLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(240))
        maltaIslandLoc.grayStores.add(storeBuilder.buildBCM(216))
        maltaIslandLoc.grayStores.add(storeBuilder.buildTivat(216))
        maltaIslandLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(168))

        maltaIsland.addLocation(maltaIslandLoc)
        regions.add(maltaIsland)


        // Gozo Island
        val gozoIsland = Region("Gozo Island",53)

        val gozoIslandLoc = Location("Gozo Island")

        gozoIslandLoc.nativeStores.add(storeBuilder.buildMalta(4, 150))
        gozoIslandLoc.alternativeStores.add(storeBuilder.buildRiposto(48, 500))
        gozoIslandLoc.alternativeStores.add(storeBuilder.buildPalermo(48, 500))

        gozoIslandLoc.grayStores.add(storeBuilder.buildM55(168))
        gozoIslandLoc.grayStores.add(storeBuilder.buildAntibes(168))
        gozoIslandLoc.grayStores.add(storeBuilder.buildPalmaED(168))
        gozoIslandLoc.grayStores.add(storeBuilder.buildPalmaNG(168))
        gozoIslandLoc.grayStores.add(storeBuilder.buildIbiza(168))
        gozoIslandLoc.grayStores.add(storeBuilder.buildOlbiaNA(168))
        gozoIslandLoc.grayStores.add(storeBuilder.buildOlbiaSYS(168))
        gozoIslandLoc.grayStores.add(storeBuilder.buildGenova(168))
        gozoIslandLoc.grayStores.add(storeBuilder.buildNapoli(168))
        gozoIslandLoc.grayStores.add(storeBuilder.buildAthensSotiris(192))
        gozoIslandLoc.grayStores.add(storeBuilder.buildAthensH360(192))
        gozoIslandLoc.grayStores.add(storeBuilder.buildCorfu(192))
        gozoIslandLoc.grayStores.add(storeBuilder.buildKosA1(216))
        gozoIslandLoc.grayStores.add(storeBuilder.buildRhodes(216))
        gozoIslandLoc.grayStores.add(storeBuilder.buildVenice(192))
        gozoIslandLoc.grayStores.add(storeBuilder.buildZadar(240))
        gozoIslandLoc.grayStores.add(storeBuilder.buildSplit(240))
        gozoIslandLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(240))
        gozoIslandLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(240))
        gozoIslandLoc.grayStores.add(storeBuilder.buildBCM(216))
        gozoIslandLoc.grayStores.add(storeBuilder.buildTivat(216))
        gozoIslandLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(168))

        gozoIsland.addLocation(gozoIslandLoc)
        regions.add(gozoIsland)

        return regions.toList()
    }

    override fun buildCorsica(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        // Corsica
        val calvi = Region("Calvi",54)

        val calviLoc = Location("Calvi")

        calviLoc.nativeStores.add(storeBuilder.buildAntibes(16, 746))
        calviLoc.alternativeStores.add(storeBuilder.buildOlbiaNA(12, 970))
        calviLoc.alternativeStores.add(storeBuilder.buildOlbiaSYS(12, 970))

        calviLoc.grayStores.add(storeBuilder.buildM55(14))
        calviLoc.grayStores.add(storeBuilder.buildPalmaED(168))
        calviLoc.grayStores.add(storeBuilder.buildPalmaNG(168))
        calviLoc.grayStores.add(storeBuilder.buildIbiza(168))
        calviLoc.grayStores.add(storeBuilder.buildGenova(96))
        calviLoc.grayStores.add(storeBuilder.buildNapoli(144))
        calviLoc.grayStores.add(storeBuilder.buildPalermo(216))
        calviLoc.grayStores.add(storeBuilder.buildRiposto(216))
        calviLoc.grayStores.add(storeBuilder.buildMalta(216))
        calviLoc.grayStores.add(storeBuilder.buildAthensSotiris(216))
        calviLoc.grayStores.add(storeBuilder.buildAthensH360(216))
        calviLoc.grayStores.add(storeBuilder.buildCorfu(216))
        calviLoc.grayStores.add(storeBuilder.buildKosA1(216))
        calviLoc.grayStores.add(storeBuilder.buildRhodes(216))
        calviLoc.grayStores.add(storeBuilder.buildVenice(144))
        calviLoc.grayStores.add(storeBuilder.buildZadar(192))
        calviLoc.grayStores.add(storeBuilder.buildSplit(192))
        calviLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(192))
        calviLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(192))
        calviLoc.grayStores.add(storeBuilder.buildBCM(216))
        calviLoc.grayStores.add(storeBuilder.buildTivat(216))
        calviLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(168))

        calvi.addLocation(calviLoc)
        regions.add(calvi)


        // Saint Florent
        val saintFlorent = Region("Saint Florent",55)

        val saintFlorentLoc = Location("Saint Florent")

        saintFlorentLoc.nativeStores.add(storeBuilder.buildAntibes(15, 678))
        saintFlorentLoc.alternativeStores.add(storeBuilder.buildOlbiaNA(12, 970))
        saintFlorentLoc.alternativeStores.add(storeBuilder.buildOlbiaSYS(12, 970))

        saintFlorentLoc.grayStores.add(storeBuilder.buildM55(13))
        saintFlorentLoc.grayStores.add(storeBuilder.buildPalmaED(168))
        saintFlorentLoc.grayStores.add(storeBuilder.buildPalmaNG(168))
        saintFlorentLoc.grayStores.add(storeBuilder.buildIbiza(168))
        saintFlorentLoc.grayStores.add(storeBuilder.buildGenova(96))
        saintFlorentLoc.grayStores.add(storeBuilder.buildNapoli(144))
        saintFlorentLoc.grayStores.add(storeBuilder.buildPalermo(216))
        saintFlorentLoc.grayStores.add(storeBuilder.buildRiposto(216))
        saintFlorentLoc.grayStores.add(storeBuilder.buildMalta(216))
        saintFlorentLoc.grayStores.add(storeBuilder.buildAthensSotiris(216))
        saintFlorentLoc.grayStores.add(storeBuilder.buildAthensH360(216))
        saintFlorentLoc.grayStores.add(storeBuilder.buildCorfu(216))
        saintFlorentLoc.grayStores.add(storeBuilder.buildKosA1(216))
        saintFlorentLoc.grayStores.add(storeBuilder.buildRhodes(216))
        saintFlorentLoc.grayStores.add(storeBuilder.buildVenice(144))
        saintFlorentLoc.grayStores.add(storeBuilder.buildZadar(192))
        saintFlorentLoc.grayStores.add(storeBuilder.buildSplit(192))
        saintFlorentLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(192))
        saintFlorentLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(192))
        saintFlorentLoc.grayStores.add(storeBuilder.buildBCM(216))
        saintFlorentLoc.grayStores.add(storeBuilder.buildTivat(216))
        saintFlorentLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(168))

        saintFlorent.addLocation(saintFlorentLoc)
        regions.add(saintFlorent)


        // Bastia
        val bastia = Region("Bastia",56)

        val bastiaLoc = Location("Bastia")

        bastiaLoc.nativeStores.add(storeBuilder.buildAntibes(15, 654))
        bastiaLoc.alternativeStores.add(storeBuilder.buildOlbiaNA(12, 1070))
        bastiaLoc.alternativeStores.add(storeBuilder.buildOlbiaSYS(12, 1070))

        bastiaLoc.grayStores.add(storeBuilder.buildM55(13))
        bastiaLoc.grayStores.add(storeBuilder.buildPalmaED(168))
        bastiaLoc.grayStores.add(storeBuilder.buildPalmaNG(168))
        bastiaLoc.grayStores.add(storeBuilder.buildIbiza(168))
        bastiaLoc.grayStores.add(storeBuilder.buildGenova(96))
        bastiaLoc.grayStores.add(storeBuilder.buildNapoli(144))
        bastiaLoc.grayStores.add(storeBuilder.buildPalermo(216))
        bastiaLoc.grayStores.add(storeBuilder.buildRiposto(216))
        bastiaLoc.grayStores.add(storeBuilder.buildMalta(216))
        bastiaLoc.grayStores.add(storeBuilder.buildAthensSotiris(216))
        bastiaLoc.grayStores.add(storeBuilder.buildAthensH360(216))
        bastiaLoc.grayStores.add(storeBuilder.buildCorfu(216))
        bastiaLoc.grayStores.add(storeBuilder.buildKosA1(216))
        bastiaLoc.grayStores.add(storeBuilder.buildRhodes(216))
        bastiaLoc.grayStores.add(storeBuilder.buildVenice(144))
        bastiaLoc.grayStores.add(storeBuilder.buildZadar(192))
        bastiaLoc.grayStores.add(storeBuilder.buildSplit(192))
        bastiaLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(192))
        bastiaLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(192))
        bastiaLoc.grayStores.add(storeBuilder.buildBCM(216))
        bastiaLoc.grayStores.add(storeBuilder.buildTivat(216))
        bastiaLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(168))

        bastia.addLocation(bastiaLoc)
        regions.add(bastia)


        // Porto Vecchio
        val portoVecchio = Region("Porto Vecchio",57)

        val portoVecchioLoc = Location("Porto Vecchio")

        portoVecchioLoc.nativeStores.add(storeBuilder.buildOlbiaNA(8, 440))
        portoVecchioLoc.nativeStores.add(storeBuilder.buildOlbiaSYS(8, 440))
        portoVecchioLoc.alternativeStores.add(storeBuilder.buildAntibes(17, 798))

        portoVecchioLoc.grayStores.add(storeBuilder.buildM55(15))
        portoVecchioLoc.grayStores.add(storeBuilder.buildPalmaED(168))
        portoVecchioLoc.grayStores.add(storeBuilder.buildPalmaNG(168))
        portoVecchioLoc.grayStores.add(storeBuilder.buildIbiza(168))
        portoVecchioLoc.grayStores.add(storeBuilder.buildGenova(96))
        portoVecchioLoc.grayStores.add(storeBuilder.buildNapoli(144))
        portoVecchioLoc.grayStores.add(storeBuilder.buildPalermo(216))
        portoVecchioLoc.grayStores.add(storeBuilder.buildRiposto(216))
        portoVecchioLoc.grayStores.add(storeBuilder.buildMalta(216))
        portoVecchioLoc.grayStores.add(storeBuilder.buildAthensSotiris(216))
        portoVecchioLoc.grayStores.add(storeBuilder.buildAthensH360(216))
        portoVecchioLoc.grayStores.add(storeBuilder.buildCorfu(216))
        portoVecchioLoc.grayStores.add(storeBuilder.buildKosA1(216))
        portoVecchioLoc.grayStores.add(storeBuilder.buildRhodes(216))
        portoVecchioLoc.grayStores.add(storeBuilder.buildVenice(144))
        portoVecchioLoc.grayStores.add(storeBuilder.buildZadar(192))
        portoVecchioLoc.grayStores.add(storeBuilder.buildSplit(192))
        portoVecchioLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(192))
        portoVecchioLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(192))
        portoVecchioLoc.grayStores.add(storeBuilder.buildBCM(216))
        portoVecchioLoc.grayStores.add(storeBuilder.buildTivat(216))
        portoVecchioLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(168))

        portoVecchio.addLocation(portoVecchioLoc)
        regions.add(portoVecchio)


        // Bonifacio
        val bonifacio = Region("Bonifacio",58)

        val bonifacioLoc = Location("Bonifacio")

        bonifacioLoc.nativeStores.add(storeBuilder.buildOlbiaNA(7, 340))
        bonifacioLoc.nativeStores.add(storeBuilder.buildOlbiaSYS(7, 340))
        bonifacioLoc.alternativeStores.add(storeBuilder.buildAntibes(17, 827))

        bonifacioLoc.grayStores.add(storeBuilder.buildM55(15))
        bonifacioLoc.grayStores.add(storeBuilder.buildPalmaED(168))
        bonifacioLoc.grayStores.add(storeBuilder.buildPalmaNG(168))
        bonifacioLoc.grayStores.add(storeBuilder.buildIbiza(168))
        bonifacioLoc.grayStores.add(storeBuilder.buildGenova(96))
        bonifacioLoc.grayStores.add(storeBuilder.buildNapoli(144))
        bonifacioLoc.grayStores.add(storeBuilder.buildPalermo(216))
        bonifacioLoc.grayStores.add(storeBuilder.buildRiposto(216))
        bonifacioLoc.grayStores.add(storeBuilder.buildMalta(216))
        bonifacioLoc.grayStores.add(storeBuilder.buildAthensSotiris(216))
        bonifacioLoc.grayStores.add(storeBuilder.buildAthensH360(216))
        bonifacioLoc.grayStores.add(storeBuilder.buildCorfu(216))
        bonifacioLoc.grayStores.add(storeBuilder.buildKosA1(216))
        bonifacioLoc.grayStores.add(storeBuilder.buildRhodes(216))
        bonifacioLoc.grayStores.add(storeBuilder.buildVenice(144))
        bonifacioLoc.grayStores.add(storeBuilder.buildZadar(192))
        bonifacioLoc.grayStores.add(storeBuilder.buildSplit(192))
        bonifacioLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(192))
        bonifacioLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(192))
        bonifacioLoc.grayStores.add(storeBuilder.buildBCM(216))
        bonifacioLoc.grayStores.add(storeBuilder.buildTivat(216))
        bonifacioLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(168))

        bonifacio.addLocation(bonifacioLoc)
        regions.add(bonifacio)


        // Ajiaccio
        val ajjaccio = Region("Ajjaccio",59)

        val ajjaccioLoc = Location("Ajjaccio")

        ajjaccioLoc.nativeStores.add(storeBuilder.buildOlbiaNA(10, 340))
        ajjaccioLoc.nativeStores.add(storeBuilder.buildOlbiaSYS(10, 340))
        ajjaccioLoc.alternativeStores.add(storeBuilder.buildAntibes(17, 804))

        ajjaccioLoc.grayStores.add(storeBuilder.buildM55(15))
        ajjaccioLoc.grayStores.add(storeBuilder.buildPalmaED(168))
        ajjaccioLoc.grayStores.add(storeBuilder.buildPalmaNG(168))
        ajjaccioLoc.grayStores.add(storeBuilder.buildIbiza(168))
        ajjaccioLoc.grayStores.add(storeBuilder.buildGenova(96))
        ajjaccioLoc.grayStores.add(storeBuilder.buildNapoli(144))
        ajjaccioLoc.grayStores.add(storeBuilder.buildPalermo(216))
        ajjaccioLoc.grayStores.add(storeBuilder.buildRiposto(216))
        ajjaccioLoc.grayStores.add(storeBuilder.buildMalta(216))
        ajjaccioLoc.grayStores.add(storeBuilder.buildAthensSotiris(216))
        ajjaccioLoc.grayStores.add(storeBuilder.buildAthensH360(216))
        ajjaccioLoc.grayStores.add(storeBuilder.buildCorfu(216))
        ajjaccioLoc.grayStores.add(storeBuilder.buildKosA1(216))
        ajjaccioLoc.grayStores.add(storeBuilder.buildRhodes(216))
        ajjaccioLoc.grayStores.add(storeBuilder.buildVenice(144))
        ajjaccioLoc.grayStores.add(storeBuilder.buildZadar(192))
        ajjaccioLoc.grayStores.add(storeBuilder.buildSplit(192))
        ajjaccioLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(192))
        ajjaccioLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(192))
        ajjaccioLoc.grayStores.add(storeBuilder.buildBCM(216))
        ajjaccioLoc.grayStores.add(storeBuilder.buildTivat(216))
        ajjaccioLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(168))

        ajjaccio.addLocation(ajjaccioLoc)
        regions.add(ajjaccio)

        return regions.toList()
    }

    override fun buildSardinia(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        // Baia Sardinia
        val baiaSardinia = Region("Baia Sardinia",60)

        val baiaSardiniaLoc = Location("Baia Sardinia")

        baiaSardiniaLoc.nativeStores.add(storeBuilder.buildOlbiaNA(3, 70))
        baiaSardiniaLoc.nativeStores.add(storeBuilder.buildOlbiaSYS(3, 70))
        baiaSardiniaLoc.alternativeStores.add(storeBuilder.buildNapoli(24, 1100))

        baiaSardiniaLoc.grayStores.add(storeBuilder.buildM55(16))
        baiaSardiniaLoc.grayStores.add(storeBuilder.buildAntibes(16))
        baiaSardiniaLoc.grayStores.add(storeBuilder.buildPalmaED(168))
        baiaSardiniaLoc.grayStores.add(storeBuilder.buildPalmaNG(168))
        baiaSardiniaLoc.grayStores.add(storeBuilder.buildIbiza(168))
        baiaSardiniaLoc.grayStores.add(storeBuilder.buildGenova(96))
        baiaSardiniaLoc.grayStores.add(storeBuilder.buildPalermo(168))
        baiaSardiniaLoc.grayStores.add(storeBuilder.buildRiposto(168))
        baiaSardiniaLoc.grayStores.add(storeBuilder.buildMalta(216))
        baiaSardiniaLoc.grayStores.add(storeBuilder.buildAthensSotiris(168))
        baiaSardiniaLoc.grayStores.add(storeBuilder.buildAthensH360(168))
        baiaSardiniaLoc.grayStores.add(storeBuilder.buildCorfu(216))
        baiaSardiniaLoc.grayStores.add(storeBuilder.buildKosA1(216))
        baiaSardiniaLoc.grayStores.add(storeBuilder.buildRhodes(216))
        baiaSardiniaLoc.grayStores.add(storeBuilder.buildVenice(96))
        baiaSardiniaLoc.grayStores.add(storeBuilder.buildZadar(144))
        baiaSardiniaLoc.grayStores.add(storeBuilder.buildSplit(144))
        baiaSardiniaLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(144))
        baiaSardiniaLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(144))
        baiaSardiniaLoc.grayStores.add(storeBuilder.buildBCM(216))
        baiaSardiniaLoc.grayStores.add(storeBuilder.buildTivat(216))
        baiaSardiniaLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(120))

        baiaSardinia.addLocation(baiaSardiniaLoc)
        regions.add(baiaSardinia)


        // Porto Cervo
        val portoCervo = Region("Porto Cervo",61)

        val portoCervoLoc = Location("Porto Cervo")

        portoCervoLoc.nativeStores.add(storeBuilder.buildOlbiaNA(3, 65))
        portoCervoLoc.nativeStores.add(storeBuilder.buildOlbiaSYS(3, 65))
        portoCervoLoc.alternativeStores.add(storeBuilder.buildNapoli(24, 1100))

        portoCervoLoc.grayStores.add(storeBuilder.buildM55(16))
        portoCervoLoc.grayStores.add(storeBuilder.buildAntibes(16))
        portoCervoLoc.grayStores.add(storeBuilder.buildPalmaED(168))
        portoCervoLoc.grayStores.add(storeBuilder.buildPalmaNG(168))
        portoCervoLoc.grayStores.add(storeBuilder.buildIbiza(168))
        portoCervoLoc.grayStores.add(storeBuilder.buildGenova(96))
        portoCervoLoc.grayStores.add(storeBuilder.buildPalermo(168))
        portoCervoLoc.grayStores.add(storeBuilder.buildRiposto(168))
        portoCervoLoc.grayStores.add(storeBuilder.buildMalta(216))
        portoCervoLoc.grayStores.add(storeBuilder.buildAthensSotiris(168))
        portoCervoLoc.grayStores.add(storeBuilder.buildAthensH360(168))
        portoCervoLoc.grayStores.add(storeBuilder.buildCorfu(216))
        portoCervoLoc.grayStores.add(storeBuilder.buildKosA1(216))
        portoCervoLoc.grayStores.add(storeBuilder.buildRhodes(216))
        portoCervoLoc.grayStores.add(storeBuilder.buildVenice(96))
        portoCervoLoc.grayStores.add(storeBuilder.buildZadar(144))
        portoCervoLoc.grayStores.add(storeBuilder.buildSplit(144))
        portoCervoLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(144))
        portoCervoLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(144))
        portoCervoLoc.grayStores.add(storeBuilder.buildBCM(216))
        portoCervoLoc.grayStores.add(storeBuilder.buildTivat(216))
        portoCervoLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(120))

        portoCervo.addLocation(portoCervoLoc)
        regions.add(portoCervo)


        // Porto Rotondo
        val portoRotondo = Region("Porto Rotondo",62)

        val portoRotondoLoc = Location("Porto Rotondo")

        portoRotondoLoc.nativeStores.add(storeBuilder.buildOlbiaNA(3, 60))
        portoRotondoLoc.nativeStores.add(storeBuilder.buildOlbiaSYS(3, 60))
        portoRotondoLoc.alternativeStores.add(storeBuilder.buildNapoli(24, 1100))

        portoRotondoLoc.grayStores.add(storeBuilder.buildM55(16))
        portoRotondoLoc.grayStores.add(storeBuilder.buildAntibes(16))
        portoRotondoLoc.grayStores.add(storeBuilder.buildPalmaED(168))
        portoRotondoLoc.grayStores.add(storeBuilder.buildPalmaNG(168))
        portoRotondoLoc.grayStores.add(storeBuilder.buildIbiza(168))
        portoRotondoLoc.grayStores.add(storeBuilder.buildGenova(96))
        portoRotondoLoc.grayStores.add(storeBuilder.buildPalermo(168))
        portoRotondoLoc.grayStores.add(storeBuilder.buildRiposto(168))
        portoRotondoLoc.grayStores.add(storeBuilder.buildMalta(216))
        portoRotondoLoc.grayStores.add(storeBuilder.buildAthensSotiris(168))
        portoRotondoLoc.grayStores.add(storeBuilder.buildAthensH360(168))
        portoRotondoLoc.grayStores.add(storeBuilder.buildCorfu(216))
        portoRotondoLoc.grayStores.add(storeBuilder.buildKosA1(216))
        portoRotondoLoc.grayStores.add(storeBuilder.buildRhodes(216))
        portoRotondoLoc.grayStores.add(storeBuilder.buildVenice(96))
        portoRotondoLoc.grayStores.add(storeBuilder.buildZadar(144))
        portoRotondoLoc.grayStores.add(storeBuilder.buildSplit(144))
        portoRotondoLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(144))
        portoRotondoLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(144))
        portoRotondoLoc.grayStores.add(storeBuilder.buildBCM(216))
        portoRotondoLoc.grayStores.add(storeBuilder.buildTivat(216))
        portoRotondoLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(120))

        portoRotondo.addLocation(portoRotondoLoc)
        regions.add(portoRotondo)


        // Porto San Paolo
        val portoSanPaolo = Region("Porto San paolo",63)

        val portoSanPaoloLoc = Location("Porto San paolo")

        portoSanPaoloLoc.nativeStores.add(storeBuilder.buildOlbiaNA(3, 70))
        portoSanPaoloLoc.nativeStores.add(storeBuilder.buildOlbiaSYS(3, 70))
        portoSanPaoloLoc.alternativeStores.add(storeBuilder.buildNapoli(24, 1100))

        portoSanPaoloLoc.grayStores.add(storeBuilder.buildM55(16))
        portoSanPaoloLoc.grayStores.add(storeBuilder.buildAntibes(16))
        portoSanPaoloLoc.grayStores.add(storeBuilder.buildPalmaED(168))
        portoSanPaoloLoc.grayStores.add(storeBuilder.buildPalmaNG(168))
        portoSanPaoloLoc.grayStores.add(storeBuilder.buildIbiza(168))
        portoSanPaoloLoc.grayStores.add(storeBuilder.buildGenova(96))
        portoSanPaoloLoc.grayStores.add(storeBuilder.buildPalermo(168))
        portoSanPaoloLoc.grayStores.add(storeBuilder.buildRiposto(168))
        portoSanPaoloLoc.grayStores.add(storeBuilder.buildMalta(216))
        portoSanPaoloLoc.grayStores.add(storeBuilder.buildAthensSotiris(168))
        portoSanPaoloLoc.grayStores.add(storeBuilder.buildAthensH360(168))
        portoSanPaoloLoc.grayStores.add(storeBuilder.buildCorfu(216))
        portoSanPaoloLoc.grayStores.add(storeBuilder.buildKosA1(216))
        portoSanPaoloLoc.grayStores.add(storeBuilder.buildRhodes(216))
        portoSanPaoloLoc.grayStores.add(storeBuilder.buildVenice(96))
        portoSanPaoloLoc.grayStores.add(storeBuilder.buildZadar(144))
        portoSanPaoloLoc.grayStores.add(storeBuilder.buildSplit(144))
        portoSanPaoloLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(144))
        portoSanPaoloLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(144))
        portoSanPaoloLoc.grayStores.add(storeBuilder.buildBCM(216))
        portoSanPaoloLoc.grayStores.add(storeBuilder.buildTivat(216))
        portoSanPaoloLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(120))

        portoSanPaolo.addLocation(portoSanPaoloLoc)
        regions.add(portoSanPaolo)


        // Olbia
        val olbia = Region("Olbia",64)

        val olbiaLoc = Location("Olbia")

        olbiaLoc.nativeStores.add(storeBuilder.buildOlbiaNA(1, 40))
        olbiaLoc.nativeStores.add(storeBuilder.buildOlbiaSYS(1, 40))
        olbiaLoc.alternativeStores.add(storeBuilder.buildNapoli(24, 1100))

        olbiaLoc.grayStores.add(storeBuilder.buildM55(15))
        olbiaLoc.grayStores.add(storeBuilder.buildAntibes(15))
        olbiaLoc.grayStores.add(storeBuilder.buildPalmaED(168))
        olbiaLoc.grayStores.add(storeBuilder.buildPalmaNG(168))
        olbiaLoc.grayStores.add(storeBuilder.buildIbiza(168))
        olbiaLoc.grayStores.add(storeBuilder.buildGenova(96))
        olbiaLoc.grayStores.add(storeBuilder.buildPalermo(168))
        olbiaLoc.grayStores.add(storeBuilder.buildRiposto(168))
        olbiaLoc.grayStores.add(storeBuilder.buildMalta(216))
        olbiaLoc.grayStores.add(storeBuilder.buildAthensSotiris(168))
        olbiaLoc.grayStores.add(storeBuilder.buildAthensH360(168))
        olbiaLoc.grayStores.add(storeBuilder.buildCorfu(216))
        olbiaLoc.grayStores.add(storeBuilder.buildKosA1(216))
        olbiaLoc.grayStores.add(storeBuilder.buildRhodes(216))
        olbiaLoc.grayStores.add(storeBuilder.buildVenice(96))
        olbiaLoc.grayStores.add(storeBuilder.buildZadar(144))
        olbiaLoc.grayStores.add(storeBuilder.buildSplit(144))
        olbiaLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(144))
        olbiaLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(144))
        olbiaLoc.grayStores.add(storeBuilder.buildBCM(216))
        olbiaLoc.grayStores.add(storeBuilder.buildTivat(216))
        olbiaLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(120))

        olbia.addLocation(olbiaLoc)
        regions.add(olbia)


        // Palau
        val palau = Region("Palau",65)

        val palauLoc = Location("Palau")

        palauLoc.nativeStores.add(storeBuilder.buildOlbiaNA(3, 95))
        palauLoc.nativeStores.add(storeBuilder.buildOlbiaSYS(3, 95))
        palauLoc.alternativeStores.add(storeBuilder.buildNapoli(24, 1100))

        palauLoc.grayStores.add(storeBuilder.buildM55(16))
        palauLoc.grayStores.add(storeBuilder.buildAntibes(16))
        palauLoc.grayStores.add(storeBuilder.buildPalmaED(168))
        palauLoc.grayStores.add(storeBuilder.buildPalmaNG(168))
        palauLoc.grayStores.add(storeBuilder.buildIbiza(168))
        palauLoc.grayStores.add(storeBuilder.buildGenova(96))
        palauLoc.grayStores.add(storeBuilder.buildPalermo(168))
        palauLoc.grayStores.add(storeBuilder.buildRiposto(168))
        palauLoc.grayStores.add(storeBuilder.buildMalta(216))
        palauLoc.grayStores.add(storeBuilder.buildAthensSotiris(168))
        palauLoc.grayStores.add(storeBuilder.buildAthensH360(168))
        palauLoc.grayStores.add(storeBuilder.buildCorfu(216))
        palauLoc.grayStores.add(storeBuilder.buildKosA1(216))
        palauLoc.grayStores.add(storeBuilder.buildRhodes(216))
        palauLoc.grayStores.add(storeBuilder.buildVenice(96))
        palauLoc.grayStores.add(storeBuilder.buildZadar(144))
        palauLoc.grayStores.add(storeBuilder.buildSplit(144))
        palauLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(144))
        palauLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(144))
        palauLoc.grayStores.add(storeBuilder.buildBCM(216))
        palauLoc.grayStores.add(storeBuilder.buildTivat(216))
        palauLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(120))

        palau.addLocation(palauLoc)
        regions.add(palau)


        // Poltu Quatu
        val poltuQuatu = Region("Poltu Quatu",66)

        val poltuQuatuLoc = Location("Poltu Quatu")

        poltuQuatuLoc.nativeStores.add(storeBuilder.buildOlbiaNA(3, 80))
        poltuQuatuLoc.nativeStores.add(storeBuilder.buildOlbiaSYS(3, 80))
        poltuQuatuLoc.alternativeStores.add(storeBuilder.buildNapoli(24, 1100))

        poltuQuatuLoc.grayStores.add(storeBuilder.buildM55(16))
        poltuQuatuLoc.grayStores.add(storeBuilder.buildAntibes(16))
        poltuQuatuLoc.grayStores.add(storeBuilder.buildPalmaED(168))
        poltuQuatuLoc.grayStores.add(storeBuilder.buildPalmaNG(168))
        poltuQuatuLoc.grayStores.add(storeBuilder.buildIbiza(168))
        poltuQuatuLoc.grayStores.add(storeBuilder.buildGenova(96))
        poltuQuatuLoc.grayStores.add(storeBuilder.buildPalermo(168))
        poltuQuatuLoc.grayStores.add(storeBuilder.buildRiposto(168))
        poltuQuatuLoc.grayStores.add(storeBuilder.buildMalta(216))
        poltuQuatuLoc.grayStores.add(storeBuilder.buildAthensSotiris(168))
        poltuQuatuLoc.grayStores.add(storeBuilder.buildAthensH360(168))
        poltuQuatuLoc.grayStores.add(storeBuilder.buildCorfu(216))
        poltuQuatuLoc.grayStores.add(storeBuilder.buildKosA1(216))
        poltuQuatuLoc.grayStores.add(storeBuilder.buildRhodes(216))
        poltuQuatuLoc.grayStores.add(storeBuilder.buildVenice(96))
        poltuQuatuLoc.grayStores.add(storeBuilder.buildZadar(144))
        poltuQuatuLoc.grayStores.add(storeBuilder.buildSplit(144))
        poltuQuatuLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(144))
        poltuQuatuLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(144))
        poltuQuatuLoc.grayStores.add(storeBuilder.buildBCM(216))
        poltuQuatuLoc.grayStores.add(storeBuilder.buildTivat(216))
        poltuQuatuLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(120))

        poltuQuatu.addLocation(poltuQuatuLoc)
        regions.add(poltuQuatu)


        // Cala Bitta
        val calaBitta = Region("Cala Bitta",67)

        val calaBittaLoc = Location("Cala Bitta")

        calaBittaLoc.nativeStores.add(storeBuilder.buildOlbiaNA(3, 70))
        calaBittaLoc.nativeStores.add(storeBuilder.buildOlbiaSYS(3, 70))
        calaBittaLoc.alternativeStores.add(storeBuilder.buildNapoli(24, 1100))

        calaBittaLoc.grayStores.add(storeBuilder.buildM55(16))
        calaBittaLoc.grayStores.add(storeBuilder.buildAntibes(16))
        calaBittaLoc.grayStores.add(storeBuilder.buildPalmaED(168))
        calaBittaLoc.grayStores.add(storeBuilder.buildPalmaNG(168))
        calaBittaLoc.grayStores.add(storeBuilder.buildIbiza(168))
        calaBittaLoc.grayStores.add(storeBuilder.buildGenova(96))
        calaBittaLoc.grayStores.add(storeBuilder.buildPalermo(168))
        calaBittaLoc.grayStores.add(storeBuilder.buildRiposto(168))
        calaBittaLoc.grayStores.add(storeBuilder.buildMalta(216))
        calaBittaLoc.grayStores.add(storeBuilder.buildAthensSotiris(168))
        calaBittaLoc.grayStores.add(storeBuilder.buildAthensH360(168))
        calaBittaLoc.grayStores.add(storeBuilder.buildCorfu(216))
        calaBittaLoc.grayStores.add(storeBuilder.buildKosA1(216))
        calaBittaLoc.grayStores.add(storeBuilder.buildRhodes(216))
        calaBittaLoc.grayStores.add(storeBuilder.buildVenice(96))
        calaBittaLoc.grayStores.add(storeBuilder.buildZadar(144))
        calaBittaLoc.grayStores.add(storeBuilder.buildSplit(144))
        calaBittaLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(144))
        calaBittaLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(144))
        calaBittaLoc.grayStores.add(storeBuilder.buildBCM(216))
        calaBittaLoc.grayStores.add(storeBuilder.buildTivat(216))
        calaBittaLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(120))

        calaBitta.addLocation(calaBittaLoc)
        regions.add(calaBitta)


        // Cannigione
        val cannigione = Region("Cannigione",68)

        val cannigioneLoc = Location("Cannigione")

        cannigioneLoc.nativeStores.add(storeBuilder.buildOlbiaNA(3, 80))
        cannigioneLoc.nativeStores.add(storeBuilder.buildOlbiaSYS(3, 80))
        cannigioneLoc.alternativeStores.add(storeBuilder.buildNapoli(24, 1100))

        cannigioneLoc.grayStores.add(storeBuilder.buildM55(16))
        cannigioneLoc.grayStores.add(storeBuilder.buildAntibes(16))
        cannigioneLoc.grayStores.add(storeBuilder.buildPalmaED(168))
        cannigioneLoc.grayStores.add(storeBuilder.buildPalmaNG(168))
        cannigioneLoc.grayStores.add(storeBuilder.buildIbiza(168))
        cannigioneLoc.grayStores.add(storeBuilder.buildGenova(96))
        cannigioneLoc.grayStores.add(storeBuilder.buildPalermo(168))
        cannigioneLoc.grayStores.add(storeBuilder.buildRiposto(168))
        cannigioneLoc.grayStores.add(storeBuilder.buildMalta(216))
        cannigioneLoc.grayStores.add(storeBuilder.buildAthensSotiris(168))
        cannigioneLoc.grayStores.add(storeBuilder.buildAthensH360(168))
        cannigioneLoc.grayStores.add(storeBuilder.buildCorfu(216))
        cannigioneLoc.grayStores.add(storeBuilder.buildKosA1(216))
        cannigioneLoc.grayStores.add(storeBuilder.buildRhodes(216))
        cannigioneLoc.grayStores.add(storeBuilder.buildVenice(96))
        cannigioneLoc.grayStores.add(storeBuilder.buildZadar(144))
        cannigioneLoc.grayStores.add(storeBuilder.buildSplit(144))
        cannigioneLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(144))
        cannigioneLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(144))
        cannigioneLoc.grayStores.add(storeBuilder.buildBCM(216))
        cannigioneLoc.grayStores.add(storeBuilder.buildTivat(216))
        cannigioneLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(120))

        cannigione.addLocation(cannigioneLoc)
        regions.add(cannigione)


        // Cagliari
        val cagliari = Region("Cagliari",69)

        val cagliariLoc = Location("Cagliari")

        cagliariLoc.nativeStores.add(storeBuilder.buildOlbiaNA(8, 440))
        cagliariLoc.nativeStores.add(storeBuilder.buildOlbiaSYS(8, 440))
        cagliariLoc.alternativeStores.add(storeBuilder.buildNapoli(24, 1100))

        cagliariLoc.grayStores.add(storeBuilder.buildM55(18))
        cagliariLoc.grayStores.add(storeBuilder.buildAntibes(18))
        cagliariLoc.grayStores.add(storeBuilder.buildPalmaED(144))
        cagliariLoc.grayStores.add(storeBuilder.buildPalmaNG(144))
        cagliariLoc.grayStores.add(storeBuilder.buildIbiza(144))
        cagliariLoc.grayStores.add(storeBuilder.buildGenova(96))
        cagliariLoc.grayStores.add(storeBuilder.buildPalermo(168))
        cagliariLoc.grayStores.add(storeBuilder.buildRiposto(168))
        cagliariLoc.grayStores.add(storeBuilder.buildMalta(216))
        cagliariLoc.grayStores.add(storeBuilder.buildAthensSotiris(168))
        cagliariLoc.grayStores.add(storeBuilder.buildAthensH360(168))
        cagliariLoc.grayStores.add(storeBuilder.buildCorfu(216))
        cagliariLoc.grayStores.add(storeBuilder.buildKosA1(216))
        cagliariLoc.grayStores.add(storeBuilder.buildRhodes(216))
        cagliariLoc.grayStores.add(storeBuilder.buildVenice(96))
        cagliariLoc.grayStores.add(storeBuilder.buildZadar(144))
        cagliariLoc.grayStores.add(storeBuilder.buildSplit(144))
        cagliariLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(144))
        cagliariLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(144))
        cagliariLoc.grayStores.add(storeBuilder.buildBCM(216))
        cagliariLoc.grayStores.add(storeBuilder.buildTivat(216))
        cagliariLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(120))

        cagliari.addLocation(cagliariLoc)
        regions.add(cagliari)


        // La Maddalena
        val laMaddalena = Region("La Maddalena",70)

        val laMaddalenaLoc = Location("La Maddalena")

        laMaddalenaLoc.nativeStores.add(storeBuilder.buildOlbiaNA(6, 700))
        laMaddalenaLoc.nativeStores.add(storeBuilder.buildOlbiaSYS(6, 700))
        laMaddalenaLoc.alternativeStores.add(storeBuilder.buildNapoli(24, 1100))

        laMaddalenaLoc.grayStores.add(storeBuilder.buildM55(17))
        laMaddalenaLoc.grayStores.add(storeBuilder.buildAntibes(16))
        laMaddalenaLoc.grayStores.add(storeBuilder.buildPalmaED(168))
        laMaddalenaLoc.grayStores.add(storeBuilder.buildPalmaNG(168))
        laMaddalenaLoc.grayStores.add(storeBuilder.buildIbiza(168))
        laMaddalenaLoc.grayStores.add(storeBuilder.buildGenova(96))
        laMaddalenaLoc.grayStores.add(storeBuilder.buildPalermo(168))
        laMaddalenaLoc.grayStores.add(storeBuilder.buildRiposto(168))
        laMaddalenaLoc.grayStores.add(storeBuilder.buildMalta(216))
        laMaddalenaLoc.grayStores.add(storeBuilder.buildAthensSotiris(168))
        laMaddalenaLoc.grayStores.add(storeBuilder.buildAthensH360(168))
        laMaddalenaLoc.grayStores.add(storeBuilder.buildCorfu(216))
        laMaddalenaLoc.grayStores.add(storeBuilder.buildKosA1(216))
        laMaddalenaLoc.grayStores.add(storeBuilder.buildRhodes(216))
        laMaddalenaLoc.grayStores.add(storeBuilder.buildVenice(96))
        laMaddalenaLoc.grayStores.add(storeBuilder.buildZadar(144))
        laMaddalenaLoc.grayStores.add(storeBuilder.buildSplit(144))
        laMaddalenaLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(144))
        laMaddalenaLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(144))
        laMaddalenaLoc.grayStores.add(storeBuilder.buildBCM(216))
        laMaddalenaLoc.grayStores.add(storeBuilder.buildTivat(216))
        laMaddalenaLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(120))

        laMaddalena.addLocation(laMaddalenaLoc)
        regions.add(laMaddalena)

        return regions.toList()
    }

    override fun buildCroatiaAndMontenegro(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        // Venice
        val venice = Region("Venice",71)

        val veniceLoc = Location("Venice")

        veniceLoc.nativeStores.add(storeBuilder.buildM55(6, 240))
        veniceLoc.nativeStores.add(storeBuilder.buildVenice(2, 200))

        veniceLoc.grayStores.add(storeBuilder.buildAntibes(96))
        veniceLoc.grayStores.add(storeBuilder.buildPalmaED(96))
        veniceLoc.grayStores.add(storeBuilder.buildPalmaNG(96))
        veniceLoc.grayStores.add(storeBuilder.buildIbiza(96))
        veniceLoc.grayStores.add(storeBuilder.buildOlbiaNA(96))
        veniceLoc.grayStores.add(storeBuilder.buildOlbiaSYS(96))
        veniceLoc.grayStores.add(storeBuilder.buildGenova(48))
        veniceLoc.grayStores.add(storeBuilder.buildNapoli(72))
        veniceLoc.grayStores.add(storeBuilder.buildPalermo(120))
        veniceLoc.grayStores.add(storeBuilder.buildRiposto(120))
        veniceLoc.grayStores.add(storeBuilder.buildMalta(168))
        veniceLoc.grayStores.add(storeBuilder.buildAthensSotiris(144))
        veniceLoc.grayStores.add(storeBuilder.buildAthensH360(144))
        veniceLoc.grayStores.add(storeBuilder.buildCorfu(168))
        veniceLoc.grayStores.add(storeBuilder.buildKosA1(168))
        veniceLoc.grayStores.add(storeBuilder.buildRhodes(168))
        veniceLoc.grayStores.add(storeBuilder.buildZadar(48))
        veniceLoc.grayStores.add(storeBuilder.buildSplit(48))
        veniceLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(48))
        veniceLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(48))
        veniceLoc.grayStores.add(storeBuilder.buildBCM(72))
        veniceLoc.grayStores.add(storeBuilder.buildTivat(72))
        veniceLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(24))

        venice.addLocation(veniceLoc)
        regions.add(venice)


        // Piran
        val piran = Region("Piran",72)

        val piranLoc = Location("Piran")

        piranLoc.nativeStores.add(storeBuilder.buildM55(3, 125))
        piranLoc.alternativeStores.add(storeBuilder.buildVenice(4, 400))

        piranLoc.grayStores.add(storeBuilder.buildAntibes(96))
        piranLoc.grayStores.add(storeBuilder.buildPalmaED(96))
        piranLoc.grayStores.add(storeBuilder.buildPalmaNG(96))
        piranLoc.grayStores.add(storeBuilder.buildIbiza(96))
        piranLoc.grayStores.add(storeBuilder.buildOlbiaNA(120))
        piranLoc.grayStores.add(storeBuilder.buildOlbiaSYS(120))
        piranLoc.grayStores.add(storeBuilder.buildGenova(72))
        piranLoc.grayStores.add(storeBuilder.buildNapoli(96))
        piranLoc.grayStores.add(storeBuilder.buildPalermo(120))
        piranLoc.grayStores.add(storeBuilder.buildRiposto(120))
        piranLoc.grayStores.add(storeBuilder.buildMalta(216))
        piranLoc.grayStores.add(storeBuilder.buildAthensSotiris(120))
        piranLoc.grayStores.add(storeBuilder.buildAthensH360(120))
        piranLoc.grayStores.add(storeBuilder.buildCorfu(168))
        piranLoc.grayStores.add(storeBuilder.buildKosA1(168))
        piranLoc.grayStores.add(storeBuilder.buildRhodes(168))
        piranLoc.grayStores.add(storeBuilder.buildZadar(24))
        piranLoc.grayStores.add(storeBuilder.buildSplit(24))
        piranLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(48))
        piranLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(48))
        piranLoc.grayStores.add(storeBuilder.buildBCM(72))
        piranLoc.grayStores.add(storeBuilder.buildTivat(72))
        piranLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(24))

        piran.addLocation(piranLoc)
        regions.add(piran)


        // Rovinj
        val rovinj = Region("Rovinj",73)

        val rovinjLoc = Location("Rovinj")

        rovinjLoc.nativeStores.add(storeBuilder.buildM55(6, 190))
        rovinjLoc.alternativeStores.add(storeBuilder.buildVenice(6, 600))
        rovinjLoc.alternativeStores.add(storeBuilder.buildSplit(8, 550))
        rovinjLoc.alternativeStores.add(storeBuilder.buildDubrovnikKristijan(12, 550))
        rovinjLoc.alternativeStores.add(storeBuilder.buildDubrovnikBWA(12, 550))
        rovinjLoc.alternativeStores.add(storeBuilder.buildZadar(8, 550))

        rovinjLoc.grayStores.add(storeBuilder.buildAntibes(96))
        rovinjLoc.grayStores.add(storeBuilder.buildPalmaED(120))
        rovinjLoc.grayStores.add(storeBuilder.buildPalmaNG(120))
        rovinjLoc.grayStores.add(storeBuilder.buildIbiza(120))
        rovinjLoc.grayStores.add(storeBuilder.buildOlbiaNA(120))
        rovinjLoc.grayStores.add(storeBuilder.buildOlbiaSYS(120))
        rovinjLoc.grayStores.add(storeBuilder.buildGenova(72))
        rovinjLoc.grayStores.add(storeBuilder.buildNapoli(96))
        rovinjLoc.grayStores.add(storeBuilder.buildPalermo(144))
        rovinjLoc.grayStores.add(storeBuilder.buildRiposto(144))
        rovinjLoc.grayStores.add(storeBuilder.buildMalta(216))
        rovinjLoc.grayStores.add(storeBuilder.buildAthensSotiris(120))
        rovinjLoc.grayStores.add(storeBuilder.buildAthensH360(120))
        rovinjLoc.grayStores.add(storeBuilder.buildCorfu(168))
        rovinjLoc.grayStores.add(storeBuilder.buildKosA1(168))
        rovinjLoc.grayStores.add(storeBuilder.buildRhodes(168))
        rovinjLoc.grayStores.add(storeBuilder.buildBCM(72))
        rovinjLoc.grayStores.add(storeBuilder.buildTivat(72))
        rovinjLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(24))

        rovinj.addLocation(rovinjLoc)
        regions.add(rovinj)


        // Pula
        val pula = Region("Pula",74)

        val pulaLoc = Location("Pula")

        pulaLoc.nativeStores.add(storeBuilder.buildM55(6, 210))
        pulaLoc.alternativeStores.add(storeBuilder.buildVenice(6, 800))
        pulaLoc.alternativeStores.add(storeBuilder.buildSplit(8, 500))
        pulaLoc.alternativeStores.add(storeBuilder.buildDubrovnikKristijan(12, 500))
        pulaLoc.alternativeStores.add(storeBuilder.buildDubrovnikBWA(12, 500))
        pulaLoc.alternativeStores.add(storeBuilder.buildZadar(8, 500))

        pulaLoc.grayStores.add(storeBuilder.buildAntibes(96))
        pulaLoc.grayStores.add(storeBuilder.buildPalmaED(120))
        pulaLoc.grayStores.add(storeBuilder.buildPalmaNG(120))
        pulaLoc.grayStores.add(storeBuilder.buildIbiza(120))
        pulaLoc.grayStores.add(storeBuilder.buildOlbiaNA(120))
        pulaLoc.grayStores.add(storeBuilder.buildOlbiaSYS(120))
        pulaLoc.grayStores.add(storeBuilder.buildGenova(72))
        pulaLoc.grayStores.add(storeBuilder.buildNapoli(96))
        pulaLoc.grayStores.add(storeBuilder.buildPalermo(144))
        pulaLoc.grayStores.add(storeBuilder.buildRiposto(144))
        pulaLoc.grayStores.add(storeBuilder.buildMalta(216))
        pulaLoc.grayStores.add(storeBuilder.buildAthensSotiris(120))
        pulaLoc.grayStores.add(storeBuilder.buildAthensH360(120))
        pulaLoc.grayStores.add(storeBuilder.buildCorfu(168))
        pulaLoc.grayStores.add(storeBuilder.buildKosA1(168))
        pulaLoc.grayStores.add(storeBuilder.buildRhodes(168))
        pulaLoc.grayStores.add(storeBuilder.buildBCM(72))
        pulaLoc.grayStores.add(storeBuilder.buildTivat(72))
        pulaLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(24))

        pula.addLocation(pulaLoc)
        regions.add(pula)


        // Zadar
        val zadar = Region("Zadar",75)

        val zadarLoc = Location("Zadar")

        zadarLoc.nativeStores.add(storeBuilder.buildZadar(2, 50))
        zadarLoc.nativeStores.add(storeBuilder.buildSplit(4, 50))
        zadarLoc.alternativeStores.add(storeBuilder.buildDubrovnikKristijan(8, 50))
        zadarLoc.alternativeStores.add(storeBuilder.buildDubrovnikBWA(8, 50))
        zadarLoc.alternativeStores.add(storeBuilder.buildM55(8, 350))

        zadarLoc.grayStores.add(storeBuilder.buildAntibes(120))
        zadarLoc.grayStores.add(storeBuilder.buildPalmaED(120))
        zadarLoc.grayStores.add(storeBuilder.buildPalmaNG(120))
        zadarLoc.grayStores.add(storeBuilder.buildIbiza(120))
        zadarLoc.grayStores.add(storeBuilder.buildOlbiaNA(144))
        zadarLoc.grayStores.add(storeBuilder.buildOlbiaSYS(144))
        zadarLoc.grayStores.add(storeBuilder.buildGenova(96))
        zadarLoc.grayStores.add(storeBuilder.buildNapoli(120))
        zadarLoc.grayStores.add(storeBuilder.buildPalermo(168))
        zadarLoc.grayStores.add(storeBuilder.buildRiposto(168))
        zadarLoc.grayStores.add(storeBuilder.buildMalta(216))
        zadarLoc.grayStores.add(storeBuilder.buildAthensSotiris(144))
        zadarLoc.grayStores.add(storeBuilder.buildAthensH360(144))
        zadarLoc.grayStores.add(storeBuilder.buildCorfu(192))
        zadarLoc.grayStores.add(storeBuilder.buildKosA1(192))
        zadarLoc.grayStores.add(storeBuilder.buildRhodes(192))
        zadarLoc.grayStores.add(storeBuilder.buildVenice(96))
        zadarLoc.grayStores.add(storeBuilder.buildBCM(72))
        zadarLoc.grayStores.add(storeBuilder.buildTivat(72))
        zadarLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(24))

        zadar.addLocation(zadarLoc)
        regions.add(zadar)


        // Sibenik
        val sibenik = Region("Sibenik",76)

        val sibenikLoc = Location("Sibenik")

        sibenikLoc.nativeStores.add(storeBuilder.buildZadar(3, 80))
        sibenikLoc.nativeStores.add(storeBuilder.buildSplit(3, 80))
        sibenikLoc.alternativeStores.add(storeBuilder.buildDubrovnikKristijan(8, 80))
        sibenikLoc.alternativeStores.add(storeBuilder.buildDubrovnikBWA(8, 80))
        sibenikLoc.alternativeStores.add(storeBuilder.buildM55(8, 400))

        sibenikLoc.grayStores.add(storeBuilder.buildAntibes(120))
        sibenikLoc.grayStores.add(storeBuilder.buildPalmaED(120))
        sibenikLoc.grayStores.add(storeBuilder.buildPalmaNG(120))
        sibenikLoc.grayStores.add(storeBuilder.buildIbiza(120))
        sibenikLoc.grayStores.add(storeBuilder.buildOlbiaNA(144))
        sibenikLoc.grayStores.add(storeBuilder.buildOlbiaSYS(144))
        sibenikLoc.grayStores.add(storeBuilder.buildGenova(96))
        sibenikLoc.grayStores.add(storeBuilder.buildNapoli(120))
        sibenikLoc.grayStores.add(storeBuilder.buildPalermo(168))
        sibenikLoc.grayStores.add(storeBuilder.buildRiposto(168))
        sibenikLoc.grayStores.add(storeBuilder.buildMalta(216))
        sibenikLoc.grayStores.add(storeBuilder.buildAthensSotiris(144))
        sibenikLoc.grayStores.add(storeBuilder.buildAthensH360(144))
        sibenikLoc.grayStores.add(storeBuilder.buildCorfu(192))
        sibenikLoc.grayStores.add(storeBuilder.buildKosA1(192))
        sibenikLoc.grayStores.add(storeBuilder.buildRhodes(192))
        sibenikLoc.grayStores.add(storeBuilder.buildVenice(96))
        sibenikLoc.grayStores.add(storeBuilder.buildBCM(72))
        sibenikLoc.grayStores.add(storeBuilder.buildTivat(72))
        sibenikLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(24))

        sibenik.addLocation(sibenikLoc)
        regions.add(sibenik)


        // Trogir
        val trogir = Region("Trogir",77)

        val trogirLoc = Location("Trogir")

        trogirLoc.nativeStores.add(storeBuilder.buildZadar(4, 150))
        trogirLoc.nativeStores.add(storeBuilder.buildSplit(2, 150))
        trogirLoc.alternativeStores.add(storeBuilder.buildDubrovnikKristijan(8, 150))
        trogirLoc.alternativeStores.add(storeBuilder.buildDubrovnikBWA(8, 150))
        trogirLoc.alternativeStores.add(storeBuilder.buildM55(10, 440))

        trogirLoc.grayStores.add(storeBuilder.buildAntibes(120))
        trogirLoc.grayStores.add(storeBuilder.buildPalmaED(120))
        trogirLoc.grayStores.add(storeBuilder.buildPalmaNG(120))
        trogirLoc.grayStores.add(storeBuilder.buildIbiza(120))
        trogirLoc.grayStores.add(storeBuilder.buildOlbiaNA(144))
        trogirLoc.grayStores.add(storeBuilder.buildOlbiaSYS(144))
        trogirLoc.grayStores.add(storeBuilder.buildGenova(96))
        trogirLoc.grayStores.add(storeBuilder.buildNapoli(120))
        trogirLoc.grayStores.add(storeBuilder.buildPalermo(168))
        trogirLoc.grayStores.add(storeBuilder.buildRiposto(168))
        trogirLoc.grayStores.add(storeBuilder.buildMalta(216))
        trogirLoc.grayStores.add(storeBuilder.buildAthensSotiris(144))
        trogirLoc.grayStores.add(storeBuilder.buildAthensH360(144))
        trogirLoc.grayStores.add(storeBuilder.buildCorfu(192))
        trogirLoc.grayStores.add(storeBuilder.buildKosA1(192))
        trogirLoc.grayStores.add(storeBuilder.buildRhodes(192))
        trogirLoc.grayStores.add(storeBuilder.buildVenice(96))
        trogirLoc.grayStores.add(storeBuilder.buildBCM(72))
        trogirLoc.grayStores.add(storeBuilder.buildTivat(72))
        trogirLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(24))

        trogir.addLocation(trogirLoc)
        regions.add(trogir)


        // Split
        val split = Region("Split",78)

        val splitLoc = Location("Split")

        splitLoc.nativeStores.add(storeBuilder.buildZadar(4, 180))
        splitLoc.nativeStores.add(storeBuilder.buildSplit(2, 180))
        splitLoc.alternativeStores.add(storeBuilder.buildDubrovnikKristijan(8, 180))
        splitLoc.alternativeStores.add(storeBuilder.buildDubrovnikBWA(8, 180))
        splitLoc.alternativeStores.add(storeBuilder.buildM55(10, 470))

        splitLoc.grayStores.add(storeBuilder.buildAntibes(120))
        splitLoc.grayStores.add(storeBuilder.buildPalmaED(120))
        splitLoc.grayStores.add(storeBuilder.buildPalmaNG(120))
        splitLoc.grayStores.add(storeBuilder.buildIbiza(120))
        splitLoc.grayStores.add(storeBuilder.buildOlbiaNA(144))
        splitLoc.grayStores.add(storeBuilder.buildOlbiaSYS(144))
        splitLoc.grayStores.add(storeBuilder.buildGenova(96))
        splitLoc.grayStores.add(storeBuilder.buildNapoli(120))
        splitLoc.grayStores.add(storeBuilder.buildPalermo(168))
        splitLoc.grayStores.add(storeBuilder.buildRiposto(168))
        splitLoc.grayStores.add(storeBuilder.buildMalta(216))
        splitLoc.grayStores.add(storeBuilder.buildAthensSotiris(144))
        splitLoc.grayStores.add(storeBuilder.buildAthensH360(144))
        splitLoc.grayStores.add(storeBuilder.buildCorfu(192))
        splitLoc.grayStores.add(storeBuilder.buildKosA1(192))
        splitLoc.grayStores.add(storeBuilder.buildRhodes(192))
        splitLoc.grayStores.add(storeBuilder.buildVenice(96))
        splitLoc.grayStores.add(storeBuilder.buildBCM(72))
        splitLoc.grayStores.add(storeBuilder.buildTivat(72))
        splitLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(24))

        split.addLocation(splitLoc)
        regions.add(split)


        // Hvar
        val hvar = Region("Hvar",79)

        val hvarLoc = Location("Hvar")

        hvarLoc.nativeStores.add(storeBuilder.buildZadar(12, 300))
        hvarLoc.nativeStores.add(storeBuilder.buildSplit(12, 300))
        hvarLoc.alternativeStores.add(storeBuilder.buildDubrovnikKristijan(24, 300))
        hvarLoc.alternativeStores.add(storeBuilder.buildDubrovnikBWA(24, 300))
        hvarLoc.alternativeStores.add(storeBuilder.buildM55(24, 600))

        hvarLoc.grayStores.add(storeBuilder.buildAntibes(144))
        hvarLoc.grayStores.add(storeBuilder.buildPalmaED(144))
        hvarLoc.grayStores.add(storeBuilder.buildPalmaNG(144))
        hvarLoc.grayStores.add(storeBuilder.buildIbiza(144))
        hvarLoc.grayStores.add(storeBuilder.buildOlbiaNA(168))
        hvarLoc.grayStores.add(storeBuilder.buildOlbiaSYS(168))
        hvarLoc.grayStores.add(storeBuilder.buildGenova(144))
        hvarLoc.grayStores.add(storeBuilder.buildNapoli(168))
        hvarLoc.grayStores.add(storeBuilder.buildPalermo(192))
        hvarLoc.grayStores.add(storeBuilder.buildRiposto(192))
        hvarLoc.grayStores.add(storeBuilder.buildMalta(216))
        hvarLoc.grayStores.add(storeBuilder.buildAthensSotiris(192))
        hvarLoc.grayStores.add(storeBuilder.buildAthensH360(192))
        hvarLoc.grayStores.add(storeBuilder.buildCorfu(216))
        hvarLoc.grayStores.add(storeBuilder.buildKosA1(216))
        hvarLoc.grayStores.add(storeBuilder.buildRhodes(216))
        hvarLoc.grayStores.add(storeBuilder.buildVenice(144))
        hvarLoc.grayStores.add(storeBuilder.buildBCM(96))
        hvarLoc.grayStores.add(storeBuilder.buildTivat(96))
        hvarLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(72))

        hvar.addLocation(hvarLoc)
        regions.add(hvar)


        // Dubrovnik
        val dubrovnik = Region("Dubrovnik",80)

        val dubrovnikLoc = Location("Dubrovnik")

        dubrovnikLoc.nativeStores.add(storeBuilder.buildDubrovnikKristijan(2, 450))
        dubrovnikLoc.nativeStores.add(storeBuilder.buildDubrovnikBWA(2, 450))
        dubrovnikLoc.alternativeStores.add(storeBuilder.buildZadar(8, 450))
        dubrovnikLoc.alternativeStores.add(storeBuilder.buildSplit(6, 450))
        dubrovnikLoc.alternativeStores.add(storeBuilder.buildM55(18, 680))
        dubrovnikLoc.alternativeStores.add(storeBuilder.buildBCM(4, 200))
        dubrovnikLoc.alternativeStores.add(storeBuilder.buildTivat(6, 200))

        dubrovnikLoc.grayStores.add(storeBuilder.buildAntibes(144))
        dubrovnikLoc.grayStores.add(storeBuilder.buildPalmaED(144))
        dubrovnikLoc.grayStores.add(storeBuilder.buildPalmaNG(144))
        dubrovnikLoc.grayStores.add(storeBuilder.buildIbiza(144))
        dubrovnikLoc.grayStores.add(storeBuilder.buildOlbiaNA(168))
        dubrovnikLoc.grayStores.add(storeBuilder.buildOlbiaSYS(168))
        dubrovnikLoc.grayStores.add(storeBuilder.buildGenova(120))
        dubrovnikLoc.grayStores.add(storeBuilder.buildNapoli(144))
        dubrovnikLoc.grayStores.add(storeBuilder.buildPalermo(168))
        dubrovnikLoc.grayStores.add(storeBuilder.buildRiposto(168))
        dubrovnikLoc.grayStores.add(storeBuilder.buildMalta(216))
        dubrovnikLoc.grayStores.add(storeBuilder.buildAthensSotiris(144))
        dubrovnikLoc.grayStores.add(storeBuilder.buildAthensH360(144))
        dubrovnikLoc.grayStores.add(storeBuilder.buildCorfu(144))
        dubrovnikLoc.grayStores.add(storeBuilder.buildKosA1(192))
        dubrovnikLoc.grayStores.add(storeBuilder.buildRhodes(192))
        dubrovnikLoc.grayStores.add(storeBuilder.buildVenice(120))
        dubrovnikLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(48))

        dubrovnik.addLocation(dubrovnikLoc)
        regions.add(dubrovnik)


        // Cavtat
        val cavtat = Region("Cavtat",81)

        val cavtatLoc = Location("Cavtat")

        cavtatLoc.nativeStores.add(storeBuilder.buildDubrovnikKristijan(2, 500))
        cavtatLoc.nativeStores.add(storeBuilder.buildDubrovnikBWA(2, 500))
        cavtatLoc.alternativeStores.add(storeBuilder.buildZadar(8, 500))
        cavtatLoc.alternativeStores.add(storeBuilder.buildSplit(6, 500))
        cavtatLoc.alternativeStores.add(storeBuilder.buildM55(18, 680))
        cavtatLoc.alternativeStores.add(storeBuilder.buildBCM(4, 200))
        cavtatLoc.alternativeStores.add(storeBuilder.buildTivat(6, 200))

        cavtatLoc.grayStores.add(storeBuilder.buildAntibes(144))
        cavtatLoc.grayStores.add(storeBuilder.buildPalmaED(144))
        cavtatLoc.grayStores.add(storeBuilder.buildPalmaNG(144))
        cavtatLoc.grayStores.add(storeBuilder.buildIbiza(144))
        cavtatLoc.grayStores.add(storeBuilder.buildOlbiaNA(168))
        cavtatLoc.grayStores.add(storeBuilder.buildOlbiaSYS(168))
        cavtatLoc.grayStores.add(storeBuilder.buildGenova(120))
        cavtatLoc.grayStores.add(storeBuilder.buildNapoli(144))
        cavtatLoc.grayStores.add(storeBuilder.buildPalermo(168))
        cavtatLoc.grayStores.add(storeBuilder.buildRiposto(168))
        cavtatLoc.grayStores.add(storeBuilder.buildMalta(216))
        cavtatLoc.grayStores.add(storeBuilder.buildAthensSotiris(144))
        cavtatLoc.grayStores.add(storeBuilder.buildAthensH360(144))
        cavtatLoc.grayStores.add(storeBuilder.buildCorfu(144))
        cavtatLoc.grayStores.add(storeBuilder.buildKosA1(192))
        cavtatLoc.grayStores.add(storeBuilder.buildRhodes(192))
        cavtatLoc.grayStores.add(storeBuilder.buildVenice(120))
        cavtatLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(48))

        cavtat.addLocation(cavtatLoc)
        regions.add(cavtat)


        // Kotor
        val kotor = Region("Kotor",82)

        val kotorLoc = Location("Kotor")

        kotorLoc.nativeStores.add(storeBuilder.buildBCM(1, 50))
        kotorLoc.nativeStores.add(storeBuilder.buildTivat(1, 70))
        kotorLoc.alternativeStores.add(storeBuilder.buildDubrovnikKristijan(4, 700))
        kotorLoc.alternativeStores.add(storeBuilder.buildDubrovnikBWA(4, 700))
        kotorLoc.alternativeStores.add(storeBuilder.buildZadar(12, 700))
        kotorLoc.alternativeStores.add(storeBuilder.buildSplit(12, 700))
        kotorLoc.alternativeStores.add(storeBuilder.buildM55(24, 900))

        kotorLoc.grayStores.add(storeBuilder.buildAntibes(144))
        kotorLoc.grayStores.add(storeBuilder.buildPalmaED(168))
        kotorLoc.grayStores.add(storeBuilder.buildPalmaNG(168))
        kotorLoc.grayStores.add(storeBuilder.buildIbiza(168))
        kotorLoc.grayStores.add(storeBuilder.buildOlbiaNA(168))
        kotorLoc.grayStores.add(storeBuilder.buildOlbiaSYS(168))
        kotorLoc.grayStores.add(storeBuilder.buildGenova(144))
        kotorLoc.grayStores.add(storeBuilder.buildNapoli(168))
        kotorLoc.grayStores.add(storeBuilder.buildPalermo(192))
        kotorLoc.grayStores.add(storeBuilder.buildRiposto(192))
        kotorLoc.grayStores.add(storeBuilder.buildMalta(216))
        kotorLoc.grayStores.add(storeBuilder.buildAthensSotiris(144))
        kotorLoc.grayStores.add(storeBuilder.buildAthensH360(144))
        kotorLoc.grayStores.add(storeBuilder.buildCorfu(144))
        kotorLoc.grayStores.add(storeBuilder.buildKosA1(192))
        kotorLoc.grayStores.add(storeBuilder.buildRhodes(192))
        kotorLoc.grayStores.add(storeBuilder.buildVenice(144))
        kotorLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(72))

        kotor.addLocation(kotorLoc)
        regions.add(kotor)


        // Tivat
        val tivat = Region("Tivat",83)

        val tivatLoc = Location("Tivat")

        tivatLoc.nativeStores.add(storeBuilder.buildBCM(2, 70))
        tivatLoc.nativeStores.add(storeBuilder.buildTivat(2, 50))
        tivatLoc.alternativeStores.add(storeBuilder.buildDubrovnikKristijan(6, 750))
        tivatLoc.alternativeStores.add(storeBuilder.buildDubrovnikBWA(6, 750))
        tivatLoc.alternativeStores.add(storeBuilder.buildZadar(12, 750))
        tivatLoc.alternativeStores.add(storeBuilder.buildSplit(12, 750))
        tivatLoc.alternativeStores.add(storeBuilder.buildM55(24, 900))

        tivatLoc.grayStores.add(storeBuilder.buildAntibes(144))
        tivatLoc.grayStores.add(storeBuilder.buildPalmaED(168))
        tivatLoc.grayStores.add(storeBuilder.buildPalmaNG(168))
        tivatLoc.grayStores.add(storeBuilder.buildIbiza(168))
        tivatLoc.grayStores.add(storeBuilder.buildOlbiaNA(168))
        tivatLoc.grayStores.add(storeBuilder.buildOlbiaSYS(168))
        tivatLoc.grayStores.add(storeBuilder.buildGenova(144))
        tivatLoc.grayStores.add(storeBuilder.buildNapoli(168))
        tivatLoc.grayStores.add(storeBuilder.buildPalermo(192))
        tivatLoc.grayStores.add(storeBuilder.buildRiposto(192))
        tivatLoc.grayStores.add(storeBuilder.buildMalta(216))
        tivatLoc.grayStores.add(storeBuilder.buildAthensSotiris(144))
        tivatLoc.grayStores.add(storeBuilder.buildAthensH360(144))
        tivatLoc.grayStores.add(storeBuilder.buildCorfu(144))
        tivatLoc.grayStores.add(storeBuilder.buildKosA1(192))
        tivatLoc.grayStores.add(storeBuilder.buildRhodes(192))
        tivatLoc.grayStores.add(storeBuilder.buildVenice(144))
        tivatLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(72))

        tivat.addLocation(tivatLoc)
        regions.add(tivat)

        return regions.toList()
    }

    override fun buildAlbania(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        // Sarande
        val sarande = Region("Sarande",84)

        val sarandeLoc = Location("Sarande")

        sarandeLoc.nativeStores.add(storeBuilder.buildBCM(12, 400))
        sarandeLoc.nativeStores.add(storeBuilder.buildTivat(12, 400))
        sarandeLoc.alternativeStores.add(storeBuilder.buildCorfu(168, 1200))
        sarandeLoc.alternativeStores.add(storeBuilder.buildAthensSotiris(168, 1600))
        sarandeLoc.alternativeStores.add(storeBuilder.buildAthensH360(168, 1600))
        sarandeLoc.alternativeStores.add(storeBuilder.buildM55(48, 2000))

        sarandeLoc.grayStores.add(storeBuilder.buildAntibes(300))
        sarandeLoc.grayStores.add(storeBuilder.buildPalmaED(300))
        sarandeLoc.grayStores.add(storeBuilder.buildPalmaNG(300))
        sarandeLoc.grayStores.add(storeBuilder.buildIbiza(300))
        sarandeLoc.grayStores.add(storeBuilder.buildOlbiaNA(300))
        sarandeLoc.grayStores.add(storeBuilder.buildOlbiaSYS(300))
        sarandeLoc.grayStores.add(storeBuilder.buildGenova(300))
        sarandeLoc.grayStores.add(storeBuilder.buildNapoli(300))
        sarandeLoc.grayStores.add(storeBuilder.buildPalermo(300))
        sarandeLoc.grayStores.add(storeBuilder.buildRiposto(300))
        sarandeLoc.grayStores.add(storeBuilder.buildMalta(300))
        sarandeLoc.grayStores.add(storeBuilder.buildKosA1(300))
        sarandeLoc.grayStores.add(storeBuilder.buildRhodes(300))
        sarandeLoc.grayStores.add(storeBuilder.buildVenice(300))
        sarandeLoc.grayStores.add(storeBuilder.buildZadar(300))
        sarandeLoc.grayStores.add(storeBuilder.buildSplit(300))
        sarandeLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(300))
        sarandeLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(300))
        sarandeLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(300))

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

        corfuLoc.nativeStores.add(storeBuilder.buildCorfu(3, 100))
        corfuLoc.alternativeStores.add(storeBuilder.buildAthensSotiris(24, 250))
        corfuLoc.alternativeStores.add(storeBuilder.buildAthensH360(24, 250))
        corfuLoc.alternativeStores.add(storeBuilder.buildM55(26, 2198))

        corfuLoc.grayStores.add(storeBuilder.buildAntibes(216))
        corfuLoc.grayStores.add(storeBuilder.buildPalmaED(216))
        corfuLoc.grayStores.add(storeBuilder.buildPalmaNG(216))
        corfuLoc.grayStores.add(storeBuilder.buildIbiza(216))
        corfuLoc.grayStores.add(storeBuilder.buildOlbiaNA(216))
        corfuLoc.grayStores.add(storeBuilder.buildOlbiaSYS(216))
        corfuLoc.grayStores.add(storeBuilder.buildGenova(192))
        corfuLoc.grayStores.add(storeBuilder.buildNapoli(192))
        corfuLoc.grayStores.add(storeBuilder.buildPalermo(216))
        corfuLoc.grayStores.add(storeBuilder.buildRiposto(216))
        corfuLoc.grayStores.add(storeBuilder.buildMalta(216))
        corfuLoc.grayStores.add(storeBuilder.buildKosA1(216))
        corfuLoc.grayStores.add(storeBuilder.buildRhodes(216))
        corfuLoc.grayStores.add(storeBuilder.buildVenice(168))
        corfuLoc.grayStores.add(storeBuilder.buildZadar(192))
        corfuLoc.grayStores.add(storeBuilder.buildSplit(192))
        corfuLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(192))
        corfuLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(192))
        corfuLoc.grayStores.add(storeBuilder.buildBCM(192))
        corfuLoc.grayStores.add(storeBuilder.buildTivat(192))
        corfuLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(168))

        corfu.addLocation(corfuLoc)
        regions.add(corfu)


        // Zakynthos
        val zakynthos = Region("Zakynthos",86)

        val zakynthosLoc = Location("Zakynthos")

        zakynthosLoc.nativeStores.add(storeBuilder.buildCorfu(48, 250))
        zakynthosLoc.alternativeStores.add(storeBuilder.buildAthensSotiris(36, 270))
        zakynthosLoc.alternativeStores.add(storeBuilder.buildAthensH360(36, 270))
        zakynthosLoc.alternativeStores.add(storeBuilder.buildM55(34, 2198))

        zakynthosLoc.grayStores.add(storeBuilder.buildAntibes(216))
        zakynthosLoc.grayStores.add(storeBuilder.buildPalmaED(216))
        zakynthosLoc.grayStores.add(storeBuilder.buildPalmaNG(216))
        zakynthosLoc.grayStores.add(storeBuilder.buildIbiza(216))
        zakynthosLoc.grayStores.add(storeBuilder.buildOlbiaNA(216))
        zakynthosLoc.grayStores.add(storeBuilder.buildOlbiaSYS(216))
        zakynthosLoc.grayStores.add(storeBuilder.buildGenova(216))
        zakynthosLoc.grayStores.add(storeBuilder.buildNapoli(216))
        zakynthosLoc.grayStores.add(storeBuilder.buildPalermo(216))
        zakynthosLoc.grayStores.add(storeBuilder.buildRiposto(216))
        zakynthosLoc.grayStores.add(storeBuilder.buildMalta(216))
        zakynthosLoc.grayStores.add(storeBuilder.buildKosA1(216))
        zakynthosLoc.grayStores.add(storeBuilder.buildRhodes(216))
        zakynthosLoc.grayStores.add(storeBuilder.buildVenice(192))
        zakynthosLoc.grayStores.add(storeBuilder.buildZadar(216))
        zakynthosLoc.grayStores.add(storeBuilder.buildSplit(216))
        zakynthosLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(216))
        zakynthosLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(216))
        zakynthosLoc.grayStores.add(storeBuilder.buildBCM(216))
        zakynthosLoc.grayStores.add(storeBuilder.buildTivat(216))
        zakynthosLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(192))

        zakynthos.addLocation(zakynthosLoc)
        regions.add(zakynthos)


        // Kefalonia
        val kefalonia = Region("Kefalonia",87)

        val kefaloniaLoc = Location("Kefalonia")

        kefaloniaLoc.nativeStores.add(storeBuilder.buildCorfu(48, 250))
        kefaloniaLoc.alternativeStores.add(storeBuilder.buildAthensSotiris(36, 270))
        kefaloniaLoc.alternativeStores.add(storeBuilder.buildAthensH360(36, 270))
        kefaloniaLoc.alternativeStores.add(storeBuilder.buildM55(35, 2213))

        kefaloniaLoc.grayStores.add(storeBuilder.buildAntibes(216))
        kefaloniaLoc.grayStores.add(storeBuilder.buildPalmaED(216))
        kefaloniaLoc.grayStores.add(storeBuilder.buildPalmaNG(216))
        kefaloniaLoc.grayStores.add(storeBuilder.buildIbiza(216))
        kefaloniaLoc.grayStores.add(storeBuilder.buildOlbiaNA(216))
        kefaloniaLoc.grayStores.add(storeBuilder.buildOlbiaSYS(216))
        kefaloniaLoc.grayStores.add(storeBuilder.buildGenova(216))
        kefaloniaLoc.grayStores.add(storeBuilder.buildNapoli(216))
        kefaloniaLoc.grayStores.add(storeBuilder.buildPalermo(216))
        kefaloniaLoc.grayStores.add(storeBuilder.buildRiposto(216))
        kefaloniaLoc.grayStores.add(storeBuilder.buildMalta(216))
        kefaloniaLoc.grayStores.add(storeBuilder.buildKosA1(216))
        kefaloniaLoc.grayStores.add(storeBuilder.buildRhodes(216))
        kefaloniaLoc.grayStores.add(storeBuilder.buildVenice(192))
        kefaloniaLoc.grayStores.add(storeBuilder.buildZadar(216))
        kefaloniaLoc.grayStores.add(storeBuilder.buildSplit(216))
        kefaloniaLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(216))
        kefaloniaLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(216))
        kefaloniaLoc.grayStores.add(storeBuilder.buildBCM(216))
        kefaloniaLoc.grayStores.add(storeBuilder.buildTivat(216))
        kefaloniaLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(192))

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

        athensLoc.nativeStores.add(storeBuilder.buildAthensSotiris(2, 50))
        athensLoc.nativeStores.add(storeBuilder.buildAthensH360(2, 50))
        athensLoc.alternativeStores.add(storeBuilder.buildCorfu(24, 50))
        athensLoc.alternativeStores.add(storeBuilder.buildM55(120, 1800))

        athensLoc.grayStores.add(storeBuilder.buildAntibes(168))
        athensLoc.grayStores.add(storeBuilder.buildPalmaED(168))
        athensLoc.grayStores.add(storeBuilder.buildPalmaNG(168))
        athensLoc.grayStores.add(storeBuilder.buildIbiza(168))
        athensLoc.grayStores.add(storeBuilder.buildOlbiaNA(168))
        athensLoc.grayStores.add(storeBuilder.buildOlbiaSYS(168))
        athensLoc.grayStores.add(storeBuilder.buildGenova(96))
        athensLoc.grayStores.add(storeBuilder.buildNapoli(96))
        athensLoc.grayStores.add(storeBuilder.buildPalermo(144))
        athensLoc.grayStores.add(storeBuilder.buildRiposto(144))
        athensLoc.grayStores.add(storeBuilder.buildMalta(192))
        athensLoc.grayStores.add(storeBuilder.buildKosA1(24))
        athensLoc.grayStores.add(storeBuilder.buildRhodes(24))
        athensLoc.grayStores.add(storeBuilder.buildVenice(96))
        athensLoc.grayStores.add(storeBuilder.buildZadar(96))
        athensLoc.grayStores.add(storeBuilder.buildSplit(96))
        athensLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(96))
        athensLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(96))
        athensLoc.grayStores.add(storeBuilder.buildBCM(96))
        athensLoc.grayStores.add(storeBuilder.buildTivat(96))
        athensLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(96))

        athens.addLocation(athensLoc)
        regions.add(athens)


        // Mikonos
        val mikonos = Region("Mikonos",89)

        val mikonosLoc = Location("Mikonos")

        mikonosLoc.nativeStores.add(storeBuilder.buildAthensSotiris(24, 270))
        mikonosLoc.nativeStores.add(storeBuilder.buildAthensH360(24, 270))

        mikonosLoc.grayStores.add(storeBuilder.buildM55(168))
        mikonosLoc.grayStores.add(storeBuilder.buildAntibes(216))
        mikonosLoc.grayStores.add(storeBuilder.buildPalmaED(216))
        mikonosLoc.grayStores.add(storeBuilder.buildPalmaNG(216))
        mikonosLoc.grayStores.add(storeBuilder.buildIbiza(216))
        mikonosLoc.grayStores.add(storeBuilder.buildOlbiaNA(216))
        mikonosLoc.grayStores.add(storeBuilder.buildOlbiaSYS(216))
        mikonosLoc.grayStores.add(storeBuilder.buildGenova(192))
        mikonosLoc.grayStores.add(storeBuilder.buildNapoli(192))
        mikonosLoc.grayStores.add(storeBuilder.buildPalermo(216))
        mikonosLoc.grayStores.add(storeBuilder.buildRiposto(216))
        mikonosLoc.grayStores.add(storeBuilder.buildMalta(216))
        mikonosLoc.grayStores.add(storeBuilder.buildCorfu(216))
        mikonosLoc.grayStores.add(storeBuilder.buildKosA1(96))
        mikonosLoc.grayStores.add(storeBuilder.buildRhodes(96))
        mikonosLoc.grayStores.add(storeBuilder.buildVenice(144))
        mikonosLoc.grayStores.add(storeBuilder.buildZadar(144))
        mikonosLoc.grayStores.add(storeBuilder.buildSplit(144))
        mikonosLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(144))
        mikonosLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(144))
        mikonosLoc.grayStores.add(storeBuilder.buildBCM(216))
        mikonosLoc.grayStores.add(storeBuilder.buildTivat(216))
        mikonosLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(144))

        mikonos.addLocation(mikonosLoc)
        regions.add(mikonos)


        // Santorini
        val santorini = Region("Santorini",90)

        val santoriniLoc = Location("Santorini")

        santoriniLoc.nativeStores.add(storeBuilder.buildAthensSotiris(24, 270))
        santoriniLoc.nativeStores.add(storeBuilder.buildAthensH360(24, 270))

        santoriniLoc.grayStores.add(storeBuilder.buildM55(168))
        santoriniLoc.grayStores.add(storeBuilder.buildAntibes(216))
        santoriniLoc.grayStores.add(storeBuilder.buildPalmaED(216))
        santoriniLoc.grayStores.add(storeBuilder.buildPalmaNG(216))
        santoriniLoc.grayStores.add(storeBuilder.buildIbiza(216))
        santoriniLoc.grayStores.add(storeBuilder.buildOlbiaNA(216))
        santoriniLoc.grayStores.add(storeBuilder.buildOlbiaSYS(216))
        santoriniLoc.grayStores.add(storeBuilder.buildGenova(192))
        santoriniLoc.grayStores.add(storeBuilder.buildNapoli(192))
        santoriniLoc.grayStores.add(storeBuilder.buildPalermo(216))
        santoriniLoc.grayStores.add(storeBuilder.buildRiposto(216))
        santoriniLoc.grayStores.add(storeBuilder.buildMalta(216))
        santoriniLoc.grayStores.add(storeBuilder.buildCorfu(96))
        santoriniLoc.grayStores.add(storeBuilder.buildKosA1(96))
        santoriniLoc.grayStores.add(storeBuilder.buildRhodes(96))
        santoriniLoc.grayStores.add(storeBuilder.buildVenice(144))
        santoriniLoc.grayStores.add(storeBuilder.buildZadar(144))
        santoriniLoc.grayStores.add(storeBuilder.buildSplit(144))
        santoriniLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(144))
        santoriniLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(144))
        santoriniLoc.grayStores.add(storeBuilder.buildBCM(216))
        santoriniLoc.grayStores.add(storeBuilder.buildTivat(216))
        santoriniLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(144))

        santorini.addLocation(santoriniLoc)
        regions.add(santorini)


        // Rhodes
        val rhodes = Region("Rhodes",91)

        val rhodesLoc = Location("Rhodes")

        rhodesLoc.nativeStores.add(storeBuilder.buildRhodes(2, 100))
        rhodesLoc.nativeStores.add(storeBuilder.buildKosA1(24, 450))
        rhodesLoc.alternativeStores.add(storeBuilder.buildAthensSotiris(24, 300))
        rhodesLoc.alternativeStores.add(storeBuilder.buildAthensH360(24, 300))

        rhodesLoc.grayStores.add(storeBuilder.buildM55(168))
        rhodesLoc.grayStores.add(storeBuilder.buildAntibes(216))
        rhodesLoc.grayStores.add(storeBuilder.buildPalmaED(216))
        rhodesLoc.grayStores.add(storeBuilder.buildPalmaNG(216))
        rhodesLoc.grayStores.add(storeBuilder.buildIbiza(216))
        rhodesLoc.grayStores.add(storeBuilder.buildOlbiaNA(216))
        rhodesLoc.grayStores.add(storeBuilder.buildOlbiaSYS(216))
        rhodesLoc.grayStores.add(storeBuilder.buildGenova(192))
        rhodesLoc.grayStores.add(storeBuilder.buildNapoli(192))
        rhodesLoc.grayStores.add(storeBuilder.buildPalermo(216))
        rhodesLoc.grayStores.add(storeBuilder.buildRiposto(216))
        rhodesLoc.grayStores.add(storeBuilder.buildMalta(216))
        rhodesLoc.grayStores.add(storeBuilder.buildCorfu(96))
        rhodesLoc.grayStores.add(storeBuilder.buildVenice(144))
        rhodesLoc.grayStores.add(storeBuilder.buildZadar(144))
        rhodesLoc.grayStores.add(storeBuilder.buildSplit(144))
        rhodesLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(144))
        rhodesLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(144))
        rhodesLoc.grayStores.add(storeBuilder.buildBCM(216))
        rhodesLoc.grayStores.add(storeBuilder.buildTivat(216))
        rhodesLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(144))

        rhodes.addLocation(rhodesLoc)
        regions.add(rhodes)


        // Kos
        val kos = Region("Kos",92)

        val kosLoc = Location("Kos")

        kosLoc.nativeStores.add(storeBuilder.buildRhodes(24, 200))
        kosLoc.nativeStores.add(storeBuilder.buildKosA1(2, 100))
        kosLoc.alternativeStores.add(storeBuilder.buildAthensSotiris(48, 300))
        kosLoc.alternativeStores.add(storeBuilder.buildAthensH360(48, 300))

        kosLoc.grayStores.add(storeBuilder.buildM55(168))
        kosLoc.grayStores.add(storeBuilder.buildAntibes(216))
        kosLoc.grayStores.add(storeBuilder.buildPalmaED(216))
        kosLoc.grayStores.add(storeBuilder.buildPalmaNG(216))
        kosLoc.grayStores.add(storeBuilder.buildIbiza(216))
        kosLoc.grayStores.add(storeBuilder.buildOlbiaNA(216))
        kosLoc.grayStores.add(storeBuilder.buildOlbiaSYS(216))
        kosLoc.grayStores.add(storeBuilder.buildGenova(192))
        kosLoc.grayStores.add(storeBuilder.buildNapoli(192))
        kosLoc.grayStores.add(storeBuilder.buildPalermo(216))
        kosLoc.grayStores.add(storeBuilder.buildRiposto(216))
        kosLoc.grayStores.add(storeBuilder.buildMalta(216))
        kosLoc.grayStores.add(storeBuilder.buildCorfu(96))
        kosLoc.grayStores.add(storeBuilder.buildVenice(144))
        kosLoc.grayStores.add(storeBuilder.buildZadar(144))
        kosLoc.grayStores.add(storeBuilder.buildSplit(144))
        kosLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(144))
        kosLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(144))
        kosLoc.grayStores.add(storeBuilder.buildBCM(216))
        kosLoc.grayStores.add(storeBuilder.buildTivat(216))
        kosLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(144))

        kos.addLocation(kosLoc)
        regions.add(kos)

        return regions.toList()
    }

    override fun buildTurkey(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        // Bodrum
        val bodrum = Region("Bodrum",93)

        val bodrumLoc = Location("Bodrum")

        bodrumLoc.nativeStores.add(storeBuilder.buildKosA1(2, 800))
        bodrumLoc.alternativeStores.add(storeBuilder.buildAthensSotiris(48, 800))
        bodrumLoc.alternativeStores.add(storeBuilder.buildAthensH360(48, 800))
        bodrumLoc.alternativeStores.add(storeBuilder.buildRhodes(24, 800))

        bodrumLoc.grayStores.add(storeBuilder.buildM55(700))
        bodrumLoc.grayStores.add(storeBuilder.buildAntibes(700))
        bodrumLoc.grayStores.add(storeBuilder.buildPalmaED(700))
        bodrumLoc.grayStores.add(storeBuilder.buildPalmaNG(700))
        bodrumLoc.grayStores.add(storeBuilder.buildIbiza(700))
        bodrumLoc.grayStores.add(storeBuilder.buildOlbiaNA(700))
        bodrumLoc.grayStores.add(storeBuilder.buildOlbiaSYS(700))
        bodrumLoc.grayStores.add(storeBuilder.buildGenova(700))
        bodrumLoc.grayStores.add(storeBuilder.buildNapoli(700))
        bodrumLoc.grayStores.add(storeBuilder.buildPalermo(700))
        bodrumLoc.grayStores.add(storeBuilder.buildRiposto(700))
        bodrumLoc.grayStores.add(storeBuilder.buildMalta(700))
        bodrumLoc.grayStores.add(storeBuilder.buildCorfu(700))
        bodrumLoc.grayStores.add(storeBuilder.buildVenice(700))
        bodrumLoc.grayStores.add(storeBuilder.buildZadar(700))
        bodrumLoc.grayStores.add(storeBuilder.buildSplit(700))
        bodrumLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(700))
        bodrumLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(700))
        bodrumLoc.grayStores.add(storeBuilder.buildBCM(700))
        bodrumLoc.grayStores.add(storeBuilder.buildTivat(700))
        bodrumLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(700))

        bodrum.addLocation(bodrumLoc)
        regions.add(bodrum)


        // Izmir
        val izmir = Region("Izmir",94)

        val izmirLoc = Location("Izmir")

        izmirLoc.nativeStores.add(storeBuilder.buildKosA1(2, 800))
        izmirLoc.alternativeStores.add(storeBuilder.buildAthensSotiris(48, 800))
        izmirLoc.alternativeStores.add(storeBuilder.buildAthensH360(48, 800))
        izmirLoc.alternativeStores.add(storeBuilder.buildRhodes(24, 800))

        izmirLoc.grayStores.add(storeBuilder.buildM55(700))
        izmirLoc.grayStores.add(storeBuilder.buildAntibes(700))
        izmirLoc.grayStores.add(storeBuilder.buildPalmaED(700))
        izmirLoc.grayStores.add(storeBuilder.buildPalmaNG(700))
        izmirLoc.grayStores.add(storeBuilder.buildIbiza(700))
        izmirLoc.grayStores.add(storeBuilder.buildOlbiaNA(700))
        izmirLoc.grayStores.add(storeBuilder.buildOlbiaSYS(700))
        izmirLoc.grayStores.add(storeBuilder.buildGenova(700))
        izmirLoc.grayStores.add(storeBuilder.buildNapoli(700))
        izmirLoc.grayStores.add(storeBuilder.buildPalermo(700))
        izmirLoc.grayStores.add(storeBuilder.buildRiposto(700))
        izmirLoc.grayStores.add(storeBuilder.buildMalta(700))
        izmirLoc.grayStores.add(storeBuilder.buildCorfu(700))
        izmirLoc.grayStores.add(storeBuilder.buildVenice(700))
        izmirLoc.grayStores.add(storeBuilder.buildZadar(700))
        izmirLoc.grayStores.add(storeBuilder.buildSplit(700))
        izmirLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(700))
        izmirLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(700))
        izmirLoc.grayStores.add(storeBuilder.buildBCM(700))
        izmirLoc.grayStores.add(storeBuilder.buildTivat(700))
        izmirLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(700))

        izmir.addLocation(izmirLoc)
        regions.add(izmir)


        // Marmaris
        val marmaris = Region("Marmaris",95)

        val marmarisLoc = Location("Marmaris")

        marmarisLoc.nativeStores.add(storeBuilder.buildKosA1(2, 800))
        marmarisLoc.alternativeStores.add(storeBuilder.buildAthensSotiris(48, 800))
        marmarisLoc.alternativeStores.add(storeBuilder.buildAthensH360(48, 800))
        marmarisLoc.alternativeStores.add(storeBuilder.buildRhodes(24, 800))

        marmarisLoc.grayStores.add(storeBuilder.buildM55(700))
        marmarisLoc.grayStores.add(storeBuilder.buildAntibes(700))
        marmarisLoc.grayStores.add(storeBuilder.buildPalmaED(700))
        marmarisLoc.grayStores.add(storeBuilder.buildPalmaNG(700))
        marmarisLoc.grayStores.add(storeBuilder.buildIbiza(700))
        marmarisLoc.grayStores.add(storeBuilder.buildOlbiaNA(700))
        marmarisLoc.grayStores.add(storeBuilder.buildOlbiaSYS(700))
        marmarisLoc.grayStores.add(storeBuilder.buildGenova(700))
        marmarisLoc.grayStores.add(storeBuilder.buildNapoli(700))
        marmarisLoc.grayStores.add(storeBuilder.buildPalermo(700))
        marmarisLoc.grayStores.add(storeBuilder.buildRiposto(700))
        marmarisLoc.grayStores.add(storeBuilder.buildMalta(700))
        marmarisLoc.grayStores.add(storeBuilder.buildCorfu(700))
        marmarisLoc.grayStores.add(storeBuilder.buildVenice(700))
        marmarisLoc.grayStores.add(storeBuilder.buildZadar(700))
        marmarisLoc.grayStores.add(storeBuilder.buildSplit(700))
        marmarisLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(700))
        marmarisLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(700))
        marmarisLoc.grayStores.add(storeBuilder.buildBCM(700))
        marmarisLoc.grayStores.add(storeBuilder.buildTivat(700))
        marmarisLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(700))

        marmaris.addLocation(marmarisLoc)
        regions.add(marmaris)


        // Antalya
        val antalya = Region("Antalya",96)

        val antalyaLoc = Location("Antalya")

        antalyaLoc.nativeStores.add(storeBuilder.buildKosA1(2, 800))
        antalyaLoc.alternativeStores.add(storeBuilder.buildAthensSotiris(48, 800))
        antalyaLoc.alternativeStores.add(storeBuilder.buildAthensH360(48, 800))
        antalyaLoc.alternativeStores.add(storeBuilder.buildRhodes(24, 800))

        antalyaLoc.grayStores.add(storeBuilder.buildM55(700))
        antalyaLoc.grayStores.add(storeBuilder.buildAntibes(700))
        antalyaLoc.grayStores.add(storeBuilder.buildPalmaED(700))
        antalyaLoc.grayStores.add(storeBuilder.buildPalmaNG(700))
        antalyaLoc.grayStores.add(storeBuilder.buildIbiza(700))
        antalyaLoc.grayStores.add(storeBuilder.buildOlbiaNA(700))
        antalyaLoc.grayStores.add(storeBuilder.buildOlbiaSYS(700))
        antalyaLoc.grayStores.add(storeBuilder.buildGenova(700))
        antalyaLoc.grayStores.add(storeBuilder.buildNapoli(700))
        antalyaLoc.grayStores.add(storeBuilder.buildPalermo(700))
        antalyaLoc.grayStores.add(storeBuilder.buildRiposto(700))
        antalyaLoc.grayStores.add(storeBuilder.buildMalta(700))
        antalyaLoc.grayStores.add(storeBuilder.buildCorfu(700))
        antalyaLoc.grayStores.add(storeBuilder.buildVenice(700))
        antalyaLoc.grayStores.add(storeBuilder.buildZadar(700))
        antalyaLoc.grayStores.add(storeBuilder.buildSplit(700))
        antalyaLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(700))
        antalyaLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(700))
        antalyaLoc.grayStores.add(storeBuilder.buildBCM(700))
        antalyaLoc.grayStores.add(storeBuilder.buildTivat(700))
        antalyaLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(700))

        antalya.addLocation(antalyaLoc)
        regions.add(antalya)

        return regions.toList()
    }

    override fun buildCaribbean(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        // Saint Maarten
        val saintMaarten = Region("Saint Maarten",97)

        val saintMaartenLoc = Location("Saint Maarten")

        saintMaartenLoc.nativeStores.add(storeBuilder.buildStMaarten(216, 1600))
        saintMaartenLoc.alternativeStores.add(storeBuilder.buildM55(700))
        saintMaartenLoc.alternativeStores.add(storeBuilder.buildFortLauderdale(216, 1600))
        saintMaartenLoc.alternativeStores.add(storeBuilder.buildAntigua(216, 1600))

        saintMaartenLoc.grayStores.add(storeBuilder.buildAntibes(288))
        saintMaartenLoc.grayStores.add(storeBuilder.buildPalmaED(700))
        saintMaartenLoc.grayStores.add(storeBuilder.buildPalmaNG(700))
        saintMaartenLoc.grayStores.add(storeBuilder.buildIbiza(700))
        saintMaartenLoc.grayStores.add(storeBuilder.buildOlbiaNA(700))
        saintMaartenLoc.grayStores.add(storeBuilder.buildOlbiaSYS(700))
        saintMaartenLoc.grayStores.add(storeBuilder.buildGenova(700))
        saintMaartenLoc.grayStores.add(storeBuilder.buildNapoli(700))
        saintMaartenLoc.grayStores.add(storeBuilder.buildPalermo(700))
        saintMaartenLoc.grayStores.add(storeBuilder.buildRiposto(700))
        saintMaartenLoc.grayStores.add(storeBuilder.buildMalta(700))
        saintMaartenLoc.grayStores.add(storeBuilder.buildAthensSotiris(700))
        saintMaartenLoc.grayStores.add(storeBuilder.buildAthensH360(700))
        saintMaartenLoc.grayStores.add(storeBuilder.buildCorfu(700))
        saintMaartenLoc.grayStores.add(storeBuilder.buildKosA1(700))
        saintMaartenLoc.grayStores.add(storeBuilder.buildRhodes(700))
        saintMaartenLoc.grayStores.add(storeBuilder.buildVenice(700))
        saintMaartenLoc.grayStores.add(storeBuilder.buildZadar(700))
        saintMaartenLoc.grayStores.add(storeBuilder.buildSplit(700))
        saintMaartenLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(700))
        saintMaartenLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(700))
        saintMaartenLoc.grayStores.add(storeBuilder.buildBCM(700))
        saintMaartenLoc.grayStores.add(storeBuilder.buildTivat(700))
        saintMaartenLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(700))

        saintMaarten.addLocation(saintMaartenLoc)
        regions.add(saintMaarten)


        // Antigua
        val antigua = Region("Antigua",98)

        val antiguaLoc = Location("Antigua")

        antiguaLoc.nativeStores.add(storeBuilder.buildAntigua(2, 100))
        antiguaLoc.alternativeStores.add(storeBuilder.buildM55(700))
        antiguaLoc.alternativeStores.add(storeBuilder.buildFortLauderdale(216, 100))
        antiguaLoc.alternativeStores.add(storeBuilder.buildStMaarten(2, 100))

        antiguaLoc.grayStores.add(storeBuilder.buildAntibes(288))
        antiguaLoc.grayStores.add(storeBuilder.buildPalmaED(700))
        antiguaLoc.grayStores.add(storeBuilder.buildPalmaNG(700))
        antiguaLoc.grayStores.add(storeBuilder.buildIbiza(700))
        antiguaLoc.grayStores.add(storeBuilder.buildOlbiaNA(700))
        antiguaLoc.grayStores.add(storeBuilder.buildOlbiaSYS(700))
        antiguaLoc.grayStores.add(storeBuilder.buildGenova(700))
        antiguaLoc.grayStores.add(storeBuilder.buildNapoli(700))
        antiguaLoc.grayStores.add(storeBuilder.buildPalermo(700))
        antiguaLoc.grayStores.add(storeBuilder.buildRiposto(700))
        antiguaLoc.grayStores.add(storeBuilder.buildMalta(700))
        antiguaLoc.grayStores.add(storeBuilder.buildAthensSotiris(700))
        antiguaLoc.grayStores.add(storeBuilder.buildAthensH360(700))
        antiguaLoc.grayStores.add(storeBuilder.buildCorfu(700))
        antiguaLoc.grayStores.add(storeBuilder.buildKosA1(700))
        antiguaLoc.grayStores.add(storeBuilder.buildRhodes(700))
        antiguaLoc.grayStores.add(storeBuilder.buildVenice(700))
        antiguaLoc.grayStores.add(storeBuilder.buildZadar(700))
        antiguaLoc.grayStores.add(storeBuilder.buildSplit(700))
        antiguaLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(700))
        antiguaLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(700))
        antiguaLoc.grayStores.add(storeBuilder.buildBCM(700))
        antiguaLoc.grayStores.add(storeBuilder.buildTivat(700))
        antiguaLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(700))

        antigua.addLocation(antiguaLoc)
        regions.add(antigua)


        // St Barths
        val stBarths = Region("St. barths",99)

        val stBarthsLoc = Location("St. barths")

        stBarthsLoc.nativeStores.add(storeBuilder.buildStMaarten(216, 2000))
        stBarthsLoc.alternativeStores.add(storeBuilder.buildM55(700))
        stBarthsLoc.alternativeStores.add(storeBuilder.buildFortLauderdale(216, 2000))
        stBarthsLoc.alternativeStores.add(storeBuilder.buildAntigua(216, 2000))

        stBarthsLoc.grayStores.add(storeBuilder.buildAntibes(288))
        stBarthsLoc.grayStores.add(storeBuilder.buildPalmaED(700))
        stBarthsLoc.grayStores.add(storeBuilder.buildPalmaNG(700))
        stBarthsLoc.grayStores.add(storeBuilder.buildIbiza(700))
        stBarthsLoc.grayStores.add(storeBuilder.buildOlbiaNA(700))
        stBarthsLoc.grayStores.add(storeBuilder.buildOlbiaSYS(700))
        stBarthsLoc.grayStores.add(storeBuilder.buildGenova(700))
        stBarthsLoc.grayStores.add(storeBuilder.buildNapoli(700))
        stBarthsLoc.grayStores.add(storeBuilder.buildPalermo(700))
        stBarthsLoc.grayStores.add(storeBuilder.buildRiposto(700))
        stBarthsLoc.grayStores.add(storeBuilder.buildMalta(700))
        stBarthsLoc.grayStores.add(storeBuilder.buildAthensSotiris(700))
        stBarthsLoc.grayStores.add(storeBuilder.buildAthensH360(700))
        stBarthsLoc.grayStores.add(storeBuilder.buildCorfu(700))
        stBarthsLoc.grayStores.add(storeBuilder.buildKosA1(700))
        stBarthsLoc.grayStores.add(storeBuilder.buildRhodes(700))
        stBarthsLoc.grayStores.add(storeBuilder.buildVenice(700))
        stBarthsLoc.grayStores.add(storeBuilder.buildZadar(700))
        stBarthsLoc.grayStores.add(storeBuilder.buildSplit(700))
        stBarthsLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(700))
        stBarthsLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(700))
        stBarthsLoc.grayStores.add(storeBuilder.buildBCM(700))
        stBarthsLoc.grayStores.add(storeBuilder.buildTivat(700))
        stBarthsLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(700))

        stBarths.addLocation(stBarthsLoc)
        regions.add(stBarths)


        // St Lucia
        val stLucia = Region("St. Lucia",100)

        val stLuciaLoc = Location("St. Lucia")

        stLuciaLoc.alternativeStores.add(storeBuilder.buildStMaarten(240, 2000))
        stLuciaLoc.alternativeStores.add(storeBuilder.buildM55(700))
        stLuciaLoc.alternativeStores.add(storeBuilder.buildFortLauderdale(240, 2000))
        stLuciaLoc.alternativeStores.add(storeBuilder.buildAntigua(240, 2000))

        stLuciaLoc.grayStores.add(storeBuilder.buildAntibes(288))
        stLuciaLoc.grayStores.add(storeBuilder.buildPalmaED(700))
        stLuciaLoc.grayStores.add(storeBuilder.buildPalmaNG(700))
        stLuciaLoc.grayStores.add(storeBuilder.buildIbiza(700))
        stLuciaLoc.grayStores.add(storeBuilder.buildOlbiaNA(700))
        stLuciaLoc.grayStores.add(storeBuilder.buildOlbiaSYS(700))
        stLuciaLoc.grayStores.add(storeBuilder.buildGenova(700))
        stLuciaLoc.grayStores.add(storeBuilder.buildNapoli(700))
        stLuciaLoc.grayStores.add(storeBuilder.buildPalermo(700))
        stLuciaLoc.grayStores.add(storeBuilder.buildRiposto(700))
        stLuciaLoc.grayStores.add(storeBuilder.buildMalta(700))
        stLuciaLoc.grayStores.add(storeBuilder.buildAthensSotiris(700))
        stLuciaLoc.grayStores.add(storeBuilder.buildAthensH360(700))
        stLuciaLoc.grayStores.add(storeBuilder.buildCorfu(700))
        stLuciaLoc.grayStores.add(storeBuilder.buildKosA1(700))
        stLuciaLoc.grayStores.add(storeBuilder.buildRhodes(700))
        stLuciaLoc.grayStores.add(storeBuilder.buildVenice(700))
        stLuciaLoc.grayStores.add(storeBuilder.buildZadar(700))
        stLuciaLoc.grayStores.add(storeBuilder.buildSplit(700))
        stLuciaLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(700))
        stLuciaLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(700))
        stLuciaLoc.grayStores.add(storeBuilder.buildBCM(700))
        stLuciaLoc.grayStores.add(storeBuilder.buildTivat(700))
        stLuciaLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(700))

        stLucia.addLocation(stLuciaLoc)
        regions.add(stLucia)


        // Dominica
        val dominica = Region("Dominica",101)

        val dominicaLoc = Location("Dominica")

        dominicaLoc.alternativeStores.add(storeBuilder.buildStMaarten(240, 2000))
        dominicaLoc.alternativeStores.add(storeBuilder.buildM55(700))
        dominicaLoc.alternativeStores.add(storeBuilder.buildFortLauderdale(240, 2000))
        dominicaLoc.alternativeStores.add(storeBuilder.buildAntigua(240, 2000))

        dominicaLoc.grayStores.add(storeBuilder.buildAntibes(288))
        dominicaLoc.grayStores.add(storeBuilder.buildPalmaED(700))
        dominicaLoc.grayStores.add(storeBuilder.buildPalmaNG(700))
        dominicaLoc.grayStores.add(storeBuilder.buildIbiza(700))
        dominicaLoc.grayStores.add(storeBuilder.buildOlbiaNA(700))
        dominicaLoc.grayStores.add(storeBuilder.buildOlbiaSYS(700))
        dominicaLoc.grayStores.add(storeBuilder.buildGenova(700))
        dominicaLoc.grayStores.add(storeBuilder.buildNapoli(700))
        dominicaLoc.grayStores.add(storeBuilder.buildPalermo(700))
        dominicaLoc.grayStores.add(storeBuilder.buildRiposto(700))
        dominicaLoc.grayStores.add(storeBuilder.buildMalta(700))
        dominicaLoc.grayStores.add(storeBuilder.buildAthensSotiris(700))
        dominicaLoc.grayStores.add(storeBuilder.buildAthensH360(700))
        dominicaLoc.grayStores.add(storeBuilder.buildCorfu(700))
        dominicaLoc.grayStores.add(storeBuilder.buildKosA1(700))
        dominicaLoc.grayStores.add(storeBuilder.buildRhodes(700))
        dominicaLoc.grayStores.add(storeBuilder.buildVenice(700))
        dominicaLoc.grayStores.add(storeBuilder.buildZadar(700))
        dominicaLoc.grayStores.add(storeBuilder.buildSplit(700))
        dominicaLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(700))
        dominicaLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(700))
        dominicaLoc.grayStores.add(storeBuilder.buildBCM(700))
        dominicaLoc.grayStores.add(storeBuilder.buildTivat(700))
        dominicaLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(700))

        dominica.addLocation(dominicaLoc)
        regions.add(dominica)


        // Guadeloupe
        val guadeloupe = Region("Guadeloupe",102)

        val guadeloupeLoc = Location("Guadeloupe")

        guadeloupeLoc.alternativeStores.add(storeBuilder.buildStMaarten(240, 2000))
        guadeloupeLoc.alternativeStores.add(storeBuilder.buildM55(700))
        guadeloupeLoc.alternativeStores.add(storeBuilder.buildFortLauderdale(240, 2000))
        guadeloupeLoc.alternativeStores.add(storeBuilder.buildAntigua(240, 2000))

        guadeloupeLoc.grayStores.add(storeBuilder.buildAntibes(288))
        guadeloupeLoc.grayStores.add(storeBuilder.buildPalmaED(700))
        guadeloupeLoc.grayStores.add(storeBuilder.buildPalmaNG(700))
        guadeloupeLoc.grayStores.add(storeBuilder.buildIbiza(700))
        guadeloupeLoc.grayStores.add(storeBuilder.buildOlbiaNA(700))
        guadeloupeLoc.grayStores.add(storeBuilder.buildOlbiaSYS(700))
        guadeloupeLoc.grayStores.add(storeBuilder.buildGenova(700))
        guadeloupeLoc.grayStores.add(storeBuilder.buildNapoli(700))
        guadeloupeLoc.grayStores.add(storeBuilder.buildPalermo(700))
        guadeloupeLoc.grayStores.add(storeBuilder.buildRiposto(700))
        guadeloupeLoc.grayStores.add(storeBuilder.buildMalta(700))
        guadeloupeLoc.grayStores.add(storeBuilder.buildAthensSotiris(700))
        guadeloupeLoc.grayStores.add(storeBuilder.buildAthensH360(700))
        guadeloupeLoc.grayStores.add(storeBuilder.buildCorfu(700))
        guadeloupeLoc.grayStores.add(storeBuilder.buildKosA1(700))
        guadeloupeLoc.grayStores.add(storeBuilder.buildRhodes(700))
        guadeloupeLoc.grayStores.add(storeBuilder.buildVenice(700))
        guadeloupeLoc.grayStores.add(storeBuilder.buildZadar(700))
        guadeloupeLoc.grayStores.add(storeBuilder.buildSplit(700))
        guadeloupeLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(700))
        guadeloupeLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(700))
        guadeloupeLoc.grayStores.add(storeBuilder.buildBCM(700))
        guadeloupeLoc.grayStores.add(storeBuilder.buildTivat(700))
        guadeloupeLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(700))

        guadeloupe.addLocation(guadeloupeLoc)
        regions.add(guadeloupe)


        // BVI
        val bvi = Region("BVI",103)

        val bviLoc = Location("BVI")

        bviLoc.alternativeStores.add(storeBuilder.buildM55(700))
        bviLoc.alternativeStores.add(storeBuilder.buildFortLauderdale(240, 2500))

        bviLoc.grayStores.add(storeBuilder.buildAntibes(288))
        bviLoc.grayStores.add(storeBuilder.buildPalmaED(700))
        bviLoc.grayStores.add(storeBuilder.buildPalmaNG(700))
        bviLoc.grayStores.add(storeBuilder.buildIbiza(700))
        bviLoc.grayStores.add(storeBuilder.buildOlbiaNA(700))
        bviLoc.grayStores.add(storeBuilder.buildOlbiaSYS(700))
        bviLoc.grayStores.add(storeBuilder.buildGenova(700))
        bviLoc.grayStores.add(storeBuilder.buildNapoli(700))
        bviLoc.grayStores.add(storeBuilder.buildPalermo(700))
        bviLoc.grayStores.add(storeBuilder.buildRiposto(700))
        bviLoc.grayStores.add(storeBuilder.buildMalta(700))
        bviLoc.grayStores.add(storeBuilder.buildAthensSotiris(700))
        bviLoc.grayStores.add(storeBuilder.buildAthensH360(700))
        bviLoc.grayStores.add(storeBuilder.buildCorfu(700))
        bviLoc.grayStores.add(storeBuilder.buildKosA1(700))
        bviLoc.grayStores.add(storeBuilder.buildRhodes(700))
        bviLoc.grayStores.add(storeBuilder.buildVenice(700))
        bviLoc.grayStores.add(storeBuilder.buildZadar(700))
        bviLoc.grayStores.add(storeBuilder.buildSplit(700))
        bviLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(700))
        bviLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(700))
        bviLoc.grayStores.add(storeBuilder.buildBCM(700))
        bviLoc.grayStores.add(storeBuilder.buildTivat(700))
        bviLoc.grayStores.add(storeBuilder.buildAntigua(700))
        bviLoc.grayStores.add(storeBuilder.buildStMaarten(700))
        bviLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(700))

        bvi.addLocation(bviLoc)
        regions.add(bvi)


        // USVI
        val usvi = Region("USVI",104)

        val usviLoc = Location("USVI")

        usviLoc.alternativeStores.add(storeBuilder.buildM55(700))
        usviLoc.alternativeStores.add(storeBuilder.buildFortLauderdale(240, 2500))

        usviLoc.grayStores.add(storeBuilder.buildAntibes(288))
        usviLoc.grayStores.add(storeBuilder.buildPalmaED(700))
        usviLoc.grayStores.add(storeBuilder.buildPalmaNG(700))
        usviLoc.grayStores.add(storeBuilder.buildIbiza(700))
        usviLoc.grayStores.add(storeBuilder.buildOlbiaNA(700))
        usviLoc.grayStores.add(storeBuilder.buildOlbiaSYS(700))
        usviLoc.grayStores.add(storeBuilder.buildGenova(700))
        usviLoc.grayStores.add(storeBuilder.buildNapoli(700))
        usviLoc.grayStores.add(storeBuilder.buildPalermo(700))
        usviLoc.grayStores.add(storeBuilder.buildRiposto(700))
        usviLoc.grayStores.add(storeBuilder.buildMalta(700))
        usviLoc.grayStores.add(storeBuilder.buildAthensSotiris(700))
        usviLoc.grayStores.add(storeBuilder.buildAthensH360(700))
        usviLoc.grayStores.add(storeBuilder.buildCorfu(700))
        usviLoc.grayStores.add(storeBuilder.buildKosA1(700))
        usviLoc.grayStores.add(storeBuilder.buildRhodes(700))
        usviLoc.grayStores.add(storeBuilder.buildVenice(700))
        usviLoc.grayStores.add(storeBuilder.buildZadar(700))
        usviLoc.grayStores.add(storeBuilder.buildSplit(700))
        usviLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(700))
        usviLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(700))
        usviLoc.grayStores.add(storeBuilder.buildBCM(700))
        usviLoc.grayStores.add(storeBuilder.buildTivat(700))
        usviLoc.grayStores.add(storeBuilder.buildAntigua(700))
        usviLoc.grayStores.add(storeBuilder.buildStMaarten(700))
        usviLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(700))

        usvi.addLocation(usviLoc)
        regions.add(usvi)

        return regions.toList()
    }

    override fun buildFloridaAndBahamas(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        // Miami
        val miami = Region("Miami",105)

        val miamiLoc = Location("Miami")

        miamiLoc.nativeStores.add(storeBuilder.buildFortLauderdale(24, 200))
        miamiLoc.alternativeStores.add(storeBuilder.buildM55(700))

        miamiLoc.grayStores.add(storeBuilder.buildAntibes(288))
        miamiLoc.grayStores.add(storeBuilder.buildPalmaED(700))
        miamiLoc.grayStores.add(storeBuilder.buildPalmaNG(700))
        miamiLoc.grayStores.add(storeBuilder.buildIbiza(700))
        miamiLoc.grayStores.add(storeBuilder.buildOlbiaNA(700))
        miamiLoc.grayStores.add(storeBuilder.buildOlbiaSYS(700))
        miamiLoc.grayStores.add(storeBuilder.buildGenova(700))
        miamiLoc.grayStores.add(storeBuilder.buildNapoli(700))
        miamiLoc.grayStores.add(storeBuilder.buildPalermo(700))
        miamiLoc.grayStores.add(storeBuilder.buildRiposto(700))
        miamiLoc.grayStores.add(storeBuilder.buildMalta(700))
        miamiLoc.grayStores.add(storeBuilder.buildAthensSotiris(700))
        miamiLoc.grayStores.add(storeBuilder.buildAthensH360(700))
        miamiLoc.grayStores.add(storeBuilder.buildCorfu(700))
        miamiLoc.grayStores.add(storeBuilder.buildKosA1(700))
        miamiLoc.grayStores.add(storeBuilder.buildRhodes(700))
        miamiLoc.grayStores.add(storeBuilder.buildVenice(700))
        miamiLoc.grayStores.add(storeBuilder.buildZadar(700))
        miamiLoc.grayStores.add(storeBuilder.buildSplit(700))
        miamiLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(700))
        miamiLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(700))
        miamiLoc.grayStores.add(storeBuilder.buildBCM(700))
        miamiLoc.grayStores.add(storeBuilder.buildTivat(700))
        miamiLoc.grayStores.add(storeBuilder.buildAntigua(700))
        miamiLoc.grayStores.add(storeBuilder.buildStMaarten(700))
        miamiLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(700))

        miami.addLocation(miamiLoc)
        regions.add(miami)


        // Fort Lauderdale
        val fortLauderdale = Region("Fort Lauderdale",106)

        val fortLauderdaleLoc = Location("Fort Lauderdale")

        fortLauderdaleLoc.nativeStores.add(storeBuilder.buildFortLauderdale(24, 100))
        fortLauderdaleLoc.alternativeStores.add(storeBuilder.buildM55(700))

        fortLauderdaleLoc.grayStores.add(storeBuilder.buildAntibes(288))
        fortLauderdaleLoc.grayStores.add(storeBuilder.buildPalmaED(700))
        fortLauderdaleLoc.grayStores.add(storeBuilder.buildPalmaNG(700))
        fortLauderdaleLoc.grayStores.add(storeBuilder.buildIbiza(700))
        fortLauderdaleLoc.grayStores.add(storeBuilder.buildOlbiaNA(700))
        fortLauderdaleLoc.grayStores.add(storeBuilder.buildOlbiaSYS(700))
        fortLauderdaleLoc.grayStores.add(storeBuilder.buildGenova(700))
        fortLauderdaleLoc.grayStores.add(storeBuilder.buildNapoli(700))
        fortLauderdaleLoc.grayStores.add(storeBuilder.buildPalermo(700))
        fortLauderdaleLoc.grayStores.add(storeBuilder.buildRiposto(700))
        fortLauderdaleLoc.grayStores.add(storeBuilder.buildMalta(700))
        fortLauderdaleLoc.grayStores.add(storeBuilder.buildAthensSotiris(700))
        fortLauderdaleLoc.grayStores.add(storeBuilder.buildAthensH360(700))
        fortLauderdaleLoc.grayStores.add(storeBuilder.buildCorfu(700))
        fortLauderdaleLoc.grayStores.add(storeBuilder.buildKosA1(700))
        fortLauderdaleLoc.grayStores.add(storeBuilder.buildRhodes(700))
        fortLauderdaleLoc.grayStores.add(storeBuilder.buildVenice(700))
        fortLauderdaleLoc.grayStores.add(storeBuilder.buildZadar(700))
        fortLauderdaleLoc.grayStores.add(storeBuilder.buildSplit(700))
        fortLauderdaleLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(700))
        fortLauderdaleLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(700))
        fortLauderdaleLoc.grayStores.add(storeBuilder.buildBCM(700))
        fortLauderdaleLoc.grayStores.add(storeBuilder.buildTivat(700))
        fortLauderdaleLoc.grayStores.add(storeBuilder.buildAntigua(700))
        fortLauderdaleLoc.grayStores.add(storeBuilder.buildStMaarten(700))
        fortLauderdaleLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(700))

        fortLauderdale.addLocation(fortLauderdaleLoc)
        regions.add(fortLauderdale)


        // West Palm
        val westPalmBeach = Region("West Palm beach",107)

        val westPalmBeachLoc = Location("West Palm beach")

        westPalmBeachLoc.nativeStores.add(storeBuilder.buildFortLauderdale(24, 200))
        westPalmBeachLoc.alternativeStores.add(storeBuilder.buildM55(700))

        westPalmBeachLoc.grayStores.add(storeBuilder.buildAntibes(288))
        westPalmBeachLoc.grayStores.add(storeBuilder.buildPalmaED(700))
        westPalmBeachLoc.grayStores.add(storeBuilder.buildPalmaNG(700))
        westPalmBeachLoc.grayStores.add(storeBuilder.buildIbiza(700))
        westPalmBeachLoc.grayStores.add(storeBuilder.buildOlbiaNA(700))
        westPalmBeachLoc.grayStores.add(storeBuilder.buildOlbiaSYS(700))
        westPalmBeachLoc.grayStores.add(storeBuilder.buildGenova(700))
        westPalmBeachLoc.grayStores.add(storeBuilder.buildNapoli(700))
        westPalmBeachLoc.grayStores.add(storeBuilder.buildPalermo(700))
        westPalmBeachLoc.grayStores.add(storeBuilder.buildRiposto(700))
        westPalmBeachLoc.grayStores.add(storeBuilder.buildMalta(700))
        westPalmBeachLoc.grayStores.add(storeBuilder.buildAthensSotiris(700))
        westPalmBeachLoc.grayStores.add(storeBuilder.buildAthensH360(700))
        westPalmBeachLoc.grayStores.add(storeBuilder.buildCorfu(700))
        westPalmBeachLoc.grayStores.add(storeBuilder.buildKosA1(700))
        westPalmBeachLoc.grayStores.add(storeBuilder.buildRhodes(700))
        westPalmBeachLoc.grayStores.add(storeBuilder.buildVenice(700))
        westPalmBeachLoc.grayStores.add(storeBuilder.buildZadar(700))
        westPalmBeachLoc.grayStores.add(storeBuilder.buildSplit(700))
        westPalmBeachLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(700))
        westPalmBeachLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(700))
        westPalmBeachLoc.grayStores.add(storeBuilder.buildBCM(700))
        westPalmBeachLoc.grayStores.add(storeBuilder.buildTivat(700))
        westPalmBeachLoc.grayStores.add(storeBuilder.buildAntigua(700))
        westPalmBeachLoc.grayStores.add(storeBuilder.buildStMaarten(700))
        westPalmBeachLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(700))

        westPalmBeach.addLocation(westPalmBeachLoc)
        regions.add(westPalmBeach)


        // Nassau
        val nassau = Region("Nassau",108)

        val nassauLoc = Location("Nassau")

        nassauLoc.alternativeStores.add(storeBuilder.buildFortLauderdale(24, 2800))
        nassauLoc.alternativeStores.add(storeBuilder.buildM55(700))

        nassauLoc.grayStores.add(storeBuilder.buildAntibes(288))
        nassauLoc.grayStores.add(storeBuilder.buildPalmaED(700))
        nassauLoc.grayStores.add(storeBuilder.buildPalmaNG(700))
        nassauLoc.grayStores.add(storeBuilder.buildIbiza(700))
        nassauLoc.grayStores.add(storeBuilder.buildOlbiaNA(700))
        nassauLoc.grayStores.add(storeBuilder.buildOlbiaSYS(700))
        nassauLoc.grayStores.add(storeBuilder.buildGenova(700))
        nassauLoc.grayStores.add(storeBuilder.buildNapoli(700))
        nassauLoc.grayStores.add(storeBuilder.buildPalermo(700))
        nassauLoc.grayStores.add(storeBuilder.buildRiposto(700))
        nassauLoc.grayStores.add(storeBuilder.buildMalta(700))
        nassauLoc.grayStores.add(storeBuilder.buildAthensSotiris(700))
        nassauLoc.grayStores.add(storeBuilder.buildAthensH360(700))
        nassauLoc.grayStores.add(storeBuilder.buildCorfu(700))
        nassauLoc.grayStores.add(storeBuilder.buildKosA1(700))
        nassauLoc.grayStores.add(storeBuilder.buildRhodes(700))
        nassauLoc.grayStores.add(storeBuilder.buildVenice(700))
        nassauLoc.grayStores.add(storeBuilder.buildZadar(700))
        nassauLoc.grayStores.add(storeBuilder.buildSplit(700))
        nassauLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(700))
        nassauLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(700))
        nassauLoc.grayStores.add(storeBuilder.buildBCM(700))
        nassauLoc.grayStores.add(storeBuilder.buildTivat(700))
        nassauLoc.grayStores.add(storeBuilder.buildAntigua(700))
        nassauLoc.grayStores.add(storeBuilder.buildStMaarten(700))
        nassauLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(700))

        nassau.addLocation(nassauLoc)
        regions.add(nassau)

        return regions.toList()
    }

    override fun buildMaldives(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        //Maldives
        val male = Region("Male",109)

        val maleLoc = Location("Male")

        maleLoc.nativeStores.add(storeBuilder.buildMale(12, 400))
        maleLoc.alternativeStores.add(storeBuilder.buildM55(700))

        maleLoc.grayStores.add(storeBuilder.buildAntibes(288))
        maleLoc.grayStores.add(storeBuilder.buildPalmaED(700))
        maleLoc.grayStores.add(storeBuilder.buildPalmaNG(700))
        maleLoc.grayStores.add(storeBuilder.buildIbiza(700))
        maleLoc.grayStores.add(storeBuilder.buildOlbiaNA(700))
        maleLoc.grayStores.add(storeBuilder.buildOlbiaSYS(700))
        maleLoc.grayStores.add(storeBuilder.buildGenova(700))
        maleLoc.grayStores.add(storeBuilder.buildNapoli(700))
        maleLoc.grayStores.add(storeBuilder.buildPalermo(700))
        maleLoc.grayStores.add(storeBuilder.buildRiposto(700))
        maleLoc.grayStores.add(storeBuilder.buildMalta(700))
        maleLoc.grayStores.add(storeBuilder.buildAthensSotiris(700))
        maleLoc.grayStores.add(storeBuilder.buildAthensH360(700))
        maleLoc.grayStores.add(storeBuilder.buildCorfu(700))
        maleLoc.grayStores.add(storeBuilder.buildKosA1(700))
        maleLoc.grayStores.add(storeBuilder.buildRhodes(700))
        maleLoc.grayStores.add(storeBuilder.buildVenice(700))
        maleLoc.grayStores.add(storeBuilder.buildZadar(700))
        maleLoc.grayStores.add(storeBuilder.buildSplit(700))
        maleLoc.grayStores.add(storeBuilder.buildDubrovnikKristijan(700))
        maleLoc.grayStores.add(storeBuilder.buildDubrovnikBWA(700))
        maleLoc.grayStores.add(storeBuilder.buildBCM(700))
        maleLoc.grayStores.add(storeBuilder.buildTivat(700))
        maleLoc.grayStores.add(storeBuilder.buildAntigua(700))
        maleLoc.grayStores.add(storeBuilder.buildStMaarten(700))
        maleLoc.newItemsStores.add(storeBuilder.buildNewItemsStore(700))

        male.addLocation(maleLoc)
        regions.add(male)

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

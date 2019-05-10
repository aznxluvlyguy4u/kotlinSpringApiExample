package com.oceanpremium.api.locations.builder

import org.springframework.stereotype.Service

interface RegionBuilder {
    fun getAllRegions(): List<Region>

    fun buildSpainMainLand(): List<Region>
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
class RegionBuilderImpl: RegionBuilder {

    override fun buildSpainMainLand(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        // Spain - mainland
        val gibraltar = Region("Gibraltar")
        gibraltar.addLocation(Location())
        regions.add(gibraltar)

        val marbella = Region("Marbella")
        marbella.addLocation(Location()) // creates a location with the same store name
        marbella.addLocation(Location("Cala de Mijas"))
        marbella.addLocation(Location("La Lacaidesa"))
        regions.add(marbella)

        val malaga = Region("Malaga")
        malaga.addLocation(Location())
        malaga.addLocation(Location("Almunecar"))
        malaga.addLocation(Location("Fuengirola"))
        regions.add(malaga)

        val almeria = Region("Almeria")
        almeria.addLocation(Location())
        almeria.addLocation(Location("Aguilas"))
        almeria.addLocation(Location("Mortil"))
        regions.add(almeria)

        val alicante = Region("Alicante")
        alicante.addLocation(Location())
        alicante.addLocation(Location("Calp"))
        alicante.addLocation(Location("Cartagena"))
        regions.add(alicante)

        val valencia = Region("Valencia")
        valencia.addLocation(Location())
        valencia.addLocation(Location("Tarragona"))
        valencia.addLocation(Location())
        valencia.addLocation(Location())
        regions.add(valencia)

        val denia = Region("Denia")
        denia.addLocation(Location())
        regions.add(denia)

        val barcelona = Region("Barcelona")
        barcelona.addLocation(Location())
        barcelona.addLocation(Location("Sitges"))
        regions.add(barcelona)

        val roses = Region("Roses")
        roses.addLocation(Location())
        regions.add(roses)

        return regions.toList()
    }

    override fun buildBaleraicsIslands(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        //Balearic's Islands
        val ibiza = Region("Ibiza")
        ibiza.addLocation(Location("Ibiza Island"))
        ibiza.addLocation(Location("Formentera island"))
        regions.add(ibiza)

        val mallorca = Region("Mallorca")
        mallorca.addLocation(Location("Island - North/East"))
        mallorca.addLocation(Location("Palma"))
        mallorca.addLocation(Location("Port Portals"))
        mallorca.addLocation(Location("Porto Adriano"))
        regions.add(mallorca)

        val menorca = Region("Menorca")
        menorca.addLocation(Location("Menorca Island"))
        regions.add(menorca)

        return regions.toList()
    }

    override fun buildSouthOfFrance(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        //South Of France
        val portVendres = Region("Port Vendres")
        portVendres.addLocation(Location())
        regions.add(portVendres)

        val marseilles = Region("Marseilles")
        marseilles.addLocation(Location())
        regions.add(marseilles)

        val toulon = Region("Toulon")
        toulon.addLocation(Location())
        toulon.addLocation(Location("Cavalaire sur Mer"))
        toulon.addLocation(Location("Hyeres"))
        toulon.addLocation(Location("La Ciotat"))
        regions.add(toulon)

        val stTropez = Region("St. Tropez")
        stTropez.addLocation(Location())
        stTropez.addLocation(Location("Saint Raphael"))
        stTropez.addLocation(Location("Pampellonne beach"))
        regions.add(stTropez)

        val cannes = Region("Cannes")
        cannes.addLocation(Location())
        cannes.addLocation(Location("Mandelieu la Napoule"))
        cannes.addLocation(Location("Theule sur Mer"))
        regions.add(cannes)

        val antibes = Region("Antibes")
        antibes.addLocation(Location())
        antibes.addLocation(Location("Marina Baie Des Anges"))
        antibes.addLocation(Location("Golfe Juan"))
        regions.add(antibes)

        val nice = Region("Nice")
        nice.addLocation(Location())
        nice.addLocation(Location("Villefranche sur Mer"))
        nice.addLocation(Location("Cagnes sur Mer"))
        regions.add(nice)

        val monaco = Region("Monaco")
        monaco.addLocation(Location())
        monaco.addLocation(Location("Cap d'Ail"))
        monaco.addLocation(Location("Saint Jean Cap Ferrat"))
        monaco.addLocation(Location("Beaulieu sur Mer"))
        regions.add(monaco)

        return regions.toList()
    }

    override fun buildItaly(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        /**
         * Liguria Italy
         */
        val sanremo = Region("Sanremo")
        sanremo.addLocation(Location())
        sanremo.addLocation(Location("Imperia"))
        sanremo.addLocation(Location("Ventimiglia"))
        regions.add(sanremo)

        /**
         * Tuscany Italy
         */
        val loano = Region("Loano")
        loano.addLocation(Location())
        loano.addLocation(Location("Albenga"))
        loano.addLocation(Location("Savona"))
        loano.addLocation(Location("Varazze"))
        regions.add(loano)

        val genoa = Region("Genoa")
        genoa.addLocation(Location("Genoa Aeroporto"))
        genoa.addLocation(Location("Genoa molo vecchio"))
        regions.add(genoa)

        val portofino = Region("Portofino")
        portofino.addLocation(Location())
        regions.add(portofino)

        val laSpezia = Region("La Spezia")
        laSpezia.addLocation(Location())
        regions.add(laSpezia)

        val viareggio = Region("Viareggio")
        viareggio.addLocation(Location())
        regions.add(viareggio)

        val livorno = Region("Livorno")
        livorno.addLocation(Location())
        regions.add(livorno)

        val piombino = Region("Piombino")
        piombino.addLocation(Location())
        regions.add(piombino)

        val grosseto = Region("Grosseto")
        grosseto.addLocation(Location())
        regions.add(grosseto)

        val argentario = Region("Argentario")
        argentario.addLocation(Location())
        regions.add(argentario)

        val civitavecchia = Region("Civitavecchia")
        civitavecchia.addLocation(Location())
        regions.add(civitavecchia)

        /**
         * Amalfi (Italy)
         */
        val gaeta = Region("Gaeta")
        gaeta.addLocation(Location())
        regions.add(gaeta)

        val naples = Region("Naples")
        naples.addLocation(Location())
        regions.add(naples)

        val marinadiStabia = Region("Marina di Stabia")
        marinadiStabia.addLocation(Location())
        regions.add(marinadiStabia)

        val sorrento = Region("Sorrento")
        sorrento.addLocation(Location())
        regions.add(sorrento)

        val capri = Region("Capri")
        capri.addLocation(Location())
        regions.add(capri)

        val positano = Region("Positano")
        positano.addLocation(Location())
        regions.add(positano)

        val amalfi = Region("Amalfi")
        amalfi.addLocation(Location())
        regions.add(amalfi)

        val salerno = Region("Salerno")
        salerno.addLocation(Location())
        regions.add(salerno)

        //South of Italy
        val tropea = Region("Tropea")
        tropea.addLocation(Location())
        regions.add(tropea)

        val reggioDiCalabria = Region("Reggio di Calabria")
        reggioDiCalabria.addLocation(Location())
        regions.add(reggioDiCalabria)

        val brindisi = Region("Brindisi")
        brindisi.addLocation(Location())
        regions.add(brindisi)

        val otranto = Region("Otranto")
        otranto.addLocation(Location())
        regions.add(otranto)

        val bari = Region("Bari")
        bari.addLocation(Location())
        regions.add(bari)

        return regions.toList()
    }

    override fun buildSicily(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        // Sicily
        val palermo = Region("Palermo")
        palermo.addLocation(Location())
        regions.add(palermo)

        val millazzo = Region("Millazzo")
        millazzo.addLocation(Location())
        regions.add(millazzo)

        val messina = Region("Messina")
        messina.addLocation(Location())
        regions.add(messina)

        val taormina = Region("Taormina")
        taormina.addLocation(Location())
        regions.add(taormina)

        val riposto = Region("Riposto")
        riposto.addLocation(Location())
        regions.add(riposto)

        val catania = Region("Catania")
        catania.addLocation(Location())
        regions.add(catania)

        val syracuse = Region("Syracuse")
        syracuse.addLocation(Location())
        regions.add(syracuse)

        return regions.toList()
    }

    override fun buildMalta(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        // Malta
        val maltaIsland = Region("Malta Island")
        maltaIsland.addLocation(Location())
        regions.add(maltaIsland)

        val gozoIsland = Region("Gozo Island")
        gozoIsland.addLocation(Location())
        regions.add(gozoIsland)

        return regions.toList()
    }

    override fun buildCorsica(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        // Corsica
        val calvi = Region("Calvi")
        calvi.addLocation(Location())
        regions.add(calvi)

        val saintFlorent = Region("Saint Florent")
        saintFlorent.addLocation(Location())
        regions.add(saintFlorent)

        val bastia = Region("Bastia")
        bastia.addLocation(Location())
        regions.add(bastia)

        val portoVecchio = Region("Porto Vecchio")
        portoVecchio.addLocation(Location())
        regions.add(portoVecchio)

        val bonifacio = Region("Bonifacio")
        bonifacio.addLocation(Location())
        regions.add(bonifacio)

        val ajjaccio = Region("Ajjaccio")
        ajjaccio.addLocation(Location())
        regions.add(ajjaccio)

        return regions.toList()
    }

    override fun buildSardinia(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        // Sardinia
        val baiaSardinia = Region("Baia Sardinia")
        baiaSardinia.addLocation(Location())
        regions.add(baiaSardinia)

        val portoCervo = Region("Porto Cervo")
        portoCervo.addLocation(Location())
        regions.add(portoCervo)

        val portoRotondo = Region("Porto Rotondo")
        portoRotondo.addLocation(Location())
        regions.add(portoRotondo)

        val portoSanPaolo = Region("Porto San paolo")
        portoSanPaolo.addLocation(Location())
        regions.add(portoSanPaolo)

        val olbia = Region("Olbia")
        olbia.addLocation(Location())
        regions.add(olbia)

        val palau = Region("Palau")
        palau.addLocation(Location())
        regions.add(palau)

        val poltuQuatu = Region("Poltu Quatu")
        poltuQuatu.addLocation(Location())
        regions.add(poltuQuatu)

        val calaBitta = Region("Cala Bitta")
        calaBitta.addLocation(Location())
        regions.add(calaBitta)

        val cannigione = Region("Cannigione")
        cannigione.addLocation(Location())
        regions.add(cannigione)

        val cagliari = Region("Cagliari")
        cagliari.addLocation(Location())
        regions.add(cagliari)

        val laMaddalena = Region("La Maddalena")
        laMaddalena.addLocation(Location())
        regions.add(laMaddalena)

        return regions.toList()
    }

    override fun buildCroatiaAndMontenegro(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        // Croatia & Montenegro
        val venice = Region("Venice")
        venice.addLocation(Location())
        regions.add(venice)

        val piran = Region("Piran")
        piran.addLocation(Location())
        regions.add(piran)

        val rovinj = Region("Rovinj")
        rovinj.addLocation(Location())
        regions.add(rovinj)

        val pula = Region("Pula")
        pula.addLocation(Location())
        regions.add(pula)

        val zadar = Region("Zadar")
        zadar.addLocation(Location())
        regions.add(zadar)

        val sibenik = Region("Sibenik")
        sibenik.addLocation(Location())
        regions.add(sibenik)

        val trogir = Region("Trogir")
        trogir.addLocation(Location())
        regions.add(trogir)

        val split = Region("Split")
        split.addLocation(Location())
        regions.add(split)

        val hvar = Region("Hvar")
        hvar.addLocation(Location())
        regions.add(hvar)

        val dubrovnik = Region("Dubrovnik")
        dubrovnik.addLocation(Location())
        regions.add(dubrovnik)

        val cavtat = Region("Cavtat")
        cavtat.addLocation(Location())
        regions.add(cavtat)

        val kotor = Region("Kotor")
        kotor.addLocation(Location())
        regions.add(kotor)

        val tivat = Region("Tivat")
        tivat.addLocation(Location())
        regions.add(tivat)

        return regions.toList()
    }

    override fun buildAlbania(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        //Albania
        val sarande = Region("Sarande")
        sarande.addLocation(Location())
        regions.add(sarande)

        return regions.toList()
    }

    override fun buildGreece(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        /**
         * Greece (Ionian)
         */
        val corfu = Region("Corfu")
        corfu.addLocation(Location())
        regions.add(corfu)

        val zakynthos = Region("Zakynthos")
        zakynthos.addLocation(Location())
        regions.add(zakynthos)

        val kefalonia = Region("Kefalonia")
        kefalonia.addLocation(Location())
        regions.add(kefalonia)

        /**
         * Greece (Eagen)
         */
        val athens = Region("Athens")
        athens.addLocation(Location())
        regions.add(athens)

        val mikonos = Region("Mikonos")
        mikonos.addLocation(Location())
        regions.add(mikonos)

        val santorini = Region("Santorini")
        santorini.addLocation(Location())
        regions.add(santorini)

        val rhodes = Region("Rhodes")
        rhodes.addLocation(Location())
        regions.add(rhodes)

        val kos = Region("Kos")
        kos.addLocation(Location())
        regions.add(kos)

        return regions.toList()
    }

    override fun buildTurkey(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        //Turkey
        val bodrum = Region("Bodrum")
        bodrum.addLocation(Location())
        regions.add(bodrum)

        val izmir = Region("Izmir")
        izmir.addLocation(Location())
        regions.add(izmir)

        val marmaris = Region("Marmaris")
        marmaris.addLocation(Location())
        regions.add(marmaris)

        val antalya = Region("Antalya")
        antalya.addLocation(Location())
        regions.add(antalya)

        return regions.toList()
    }

    override fun buildCaribbean(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        // Caribbean
        val saintMaarten = Region("Saint Maarten")
        saintMaarten.addLocation(Location())
        regions.add(saintMaarten)

        val antigua = Region("Antigua")
        antigua.addLocation(Location())
        regions.add(antigua)

        val stBarths = Region("St. barths")
        stBarths.addLocation(Location())
        regions.add(stBarths)

        val stLucia = Region("St. Lucia")
        stLucia.addLocation(Location())
        regions.add(stLucia)

        val dominica = Region("Dominica")
        dominica.addLocation(Location())
        regions.add(dominica)

        val guadeloupe = Region("Guadeloupe")
        guadeloupe.addLocation(Location())
        regions.add(guadeloupe)

        val bvi = Region("BVI")
        bvi.addLocation(Location())
        regions.add(bvi)

        val usvi = Region("USVI")
        usvi.addLocation(Location())
        regions.add(usvi)

        return regions.toList()
    }

    override fun buildFloridaAndBahamas(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        //Florida & Bahamas
        val miami = Region("Miami")
        miami.addLocation(Location())
        regions.add(miami)

        val fortLauderdale = Region("Fort Lauderdale")
        fortLauderdale.addLocation(Location())
        regions.add(fortLauderdale)

        val westPalmBeach = Region("West Palm beach")
        westPalmBeach.addLocation(Location())
        regions.add(westPalmBeach)

        val nassau = Region("Nassau")
        nassau.addLocation(Location())
        regions.add(nassau)

        return regions.toList()
    }

    override fun buildMaldives(): List<Region> {
        val regions: MutableList<Region> = mutableListOf()

        //Maldives
        val male = Region("Male")
        male.addLocation(Location())
        regions.add(male)

        return regions.toList()
    }

    override fun getAllRegions(): List<Region> {
        val allRegions: MutableList<Region> = mutableListOf()

        allRegions.addAll(buildSpainMainLand())
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

        var i = 1
        allRegions.forEach {
            it.id = i
            i++
        }

        return allRegions
    }
}

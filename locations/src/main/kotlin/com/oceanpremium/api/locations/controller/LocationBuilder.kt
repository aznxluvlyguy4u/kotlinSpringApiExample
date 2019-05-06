package com.oceanpremium.api.locations.controller

class Location(var name: String? = null, var id: Int = 0,  var store_id: Int = 0)

class Store(private val name: String, var id: Int = 0, val locations: MutableList<Location> = mutableListOf()) {

    fun addLocation(location: Location) {
        location.store_id = id

        if (location.name == null) {
            location.name = name
        }

        locations.add(location)
    }
}

interface LocationBuilder {
    fun getAllLocations(): List<Location>
}

class LocationBuilderImpl(private val storeBuilder: StoreBuilder = StoreBuilderImpl()): LocationBuilder {

    override fun getAllLocations(): List<Location> {

        val stores = storeBuilder.getAllStores()
        val locations: MutableList<Location> = mutableListOf()

        var j = 1
        stores.forEach { store ->
            store.locations.forEach { location ->
                location.id = j
                location.store_id = store.id
                j++
            }

            locations.addAll(store.locations)
        }

        return locations
    }
}

interface StoreBuilder {
    fun getAllStores(): List<Store>
}

/**
 * Source @link: https://docs.google.com/spreadsheets/d/1Jpx59haz_8cZw0uGUBSZX47mQDPv0V3OvgJUtr--JzA/edit#gid=0
 */
class StoreBuilderImpl: StoreBuilder {

    private fun buildSpainMainLand(): List<Store> {
        val stores: MutableList<Store> = mutableListOf()

        // Spain - mainland
        val gibraltar = Store("Gibraltar")
        gibraltar.addLocation(Location())
        stores.add(gibraltar)

        val marbella = Store("Marbella")
        marbella.addLocation(Location()) // creates a location with the same store name
        marbella.addLocation(Location("Cala de Mijas"))
        marbella.addLocation(Location("La Lacaidesa"))
        stores.add(marbella)

        val malaga = Store("Malaga")
        malaga.addLocation(Location())
        malaga.addLocation(Location( "Almunecar"))
        malaga.addLocation(Location( "Fuengirola"))
        stores.add(malaga)

        val almeria =  Store("Almeria")
        almeria.addLocation(Location())
        almeria.addLocation(Location( "Aguilas"))
        almeria.addLocation(Location("Mortil"))
        stores.add(almeria)

        val alicante = Store("Alicante")
        alicante.addLocation(Location())
        alicante.addLocation(Location("Calp"))
        alicante.addLocation(Location( "Cartagena"))
        stores.add(alicante)

        val valencia = Store("Valencia")
        valencia.addLocation(Location())
        valencia.addLocation(Location( "Tarragona"))
        valencia.addLocation(Location())
        valencia.addLocation(Location())
        stores.add(valencia)

        val denia = Store("Denia")
        denia.addLocation(Location())
        stores.add(denia)

        val barcelona = Store("Barcelona")
        barcelona.addLocation(Location())
        barcelona.addLocation(Location("Sitges"))
        stores.add(barcelona)

        val roses = Store("Roses")
        roses.addLocation(Location())
        stores.add(roses)

        return stores.toList()
    }

    private fun buildBaleraicsIslands(): List<Store> {
        val stores: MutableList<Store> = mutableListOf()

        //Baleraics Islands
        val ibiza = Store("Ibiza")
        ibiza.addLocation(Location( "Ibiza Island"))
        ibiza.addLocation(Location("Formentera island"))
        stores.add(ibiza)

        val mallorca = Store("Mallorca")
        mallorca.addLocation(Location( "Island - North/East"))
        mallorca.addLocation(Location( "Palma"))
        mallorca.addLocation(Location( "Port Portals"))
        mallorca.addLocation(Location( "Porto Adriano"))
        stores.add(mallorca)

        val menorca = Store("Menorca")
        menorca.addLocation(Location( "Menorca Island"))
        stores.add(menorca)

        return stores.toList()
    }

    private fun buildSouthOfFrance(): List<Store> {
        val stores: MutableList<Store> = mutableListOf()

        //South Of France
        val portVendres = Store("Port Vendres")
        portVendres.addLocation(Location())
        stores.add(portVendres)

        val marseilles = Store("Marseilles")
        marseilles.addLocation(Location())
        stores.add(marseilles)

        val toulon = Store("Toulon")
        toulon.addLocation(Location())
        toulon.addLocation(Location( "Cavalaire sur Mer"))
        toulon.addLocation(Location( "Hyeres"))
        toulon.addLocation(Location( "La Ciotat"))
        stores.add(toulon)

        val stTropez = Store("St. Tropez")
        stTropez.addLocation(Location())
        stTropez.addLocation(Location( "Saint Raphael"))
        stTropez.addLocation(Location( "Pampellonne beach"))
        stores.add(stTropez)

        val cannes = Store("Cannes")
        cannes.addLocation(Location())
        cannes.addLocation(Location( "Mandelieu la Napoule"))
        cannes.addLocation(Location( "Theule sur Mer"))
        stores.add(cannes)

        val antibes = Store("Antibes")
        antibes.addLocation(Location())
        antibes.addLocation(Location( "Marina Baie Des Anges"))
        antibes.addLocation(Location( "Golfe Juan"))
        stores.add(antibes)

        val nice = Store("Nice")
        nice.addLocation(Location())
        nice.addLocation(Location( "Villefranche sur Mer"))
        nice.addLocation(Location( "Cagnes sur Mer"))
        stores.add(nice)

        val monaco = Store("Monaco")
        monaco.addLocation(Location())
        monaco.addLocation(Location( "Cap d'Ail"))
        monaco.addLocation(Location( "Saint Jean Cap Ferrat"))
        monaco.addLocation(Location( "Beaulieu sur Mer"))
        stores.add(monaco)

        return stores.toList()
    }

    private fun buildItaly(): List<Store> {
        val stores: MutableList<Store> = mutableListOf()

        //Liguria (Italy)
        val sanremo = Store("Sanremo")
        sanremo.addLocation(Location())
        sanremo.addLocation(Location( "Imperia"))
        sanremo.addLocation(Location( "Ventimiglia"))
        stores.add(sanremo)

        //Tuscany (Italy)
        val loano = Store("Loano")
        loano.addLocation(Location())
        loano.addLocation(Location( "Albenga"))
        loano.addLocation(Location( "Savona"))
        loano.addLocation(Location( "Varazze"))
        stores.add(loano)

        val genoa = Store("Genoa")
        genoa.addLocation(Location( "Genoa Aeroporto"))
        genoa.addLocation(Location( "Genoa molo vecchio"))
        stores.add(genoa)

        val portofino = Store("Portofino")
        portofino.addLocation(Location())
        stores.add(portofino)

        val laSpezia = Store("La Spezia")
        laSpezia.addLocation(Location())
        stores.add(laSpezia)

        val viareggio = Store("Viareggio")
        viareggio.addLocation(Location())
        stores.add(viareggio)

        val livorno = Store("Livorno")
        livorno.addLocation(Location())
        stores.add(livorno)

        val piombino = Store("Piombino")
        piombino.addLocation(Location())
        stores.add(piombino)

        val grosseto = Store("Grosseto")
        grosseto.addLocation(Location())
        stores.add(grosseto)

        val argentario = Store("Argentario")
        argentario.addLocation(Location())
        stores.add(argentario)

        val civitavecchia = Store("Civitavecchia")
        civitavecchia.addLocation(Location())
        stores.add(civitavecchia)

        // Amalfi (Italy)
        val gaeta = Store("Gaeta")
        gaeta.addLocation(Location())
        stores.add(gaeta)

        val naples = Store("Naples")
        naples.addLocation(Location())
        stores.add(naples)

        val marinadiStabia = Store("Marina di Stabia")
        marinadiStabia.addLocation(Location())
        stores.add(marinadiStabia)

        val sorrento = Store("Sorrento")
        sorrento.addLocation(Location())
        stores.add(sorrento)

        val capri = Store("Capri")
        capri.addLocation(Location())
        stores.add(capri)

        val positano = Store("Positano")
        positano.addLocation(Location())
        stores.add(positano)

        val amalfi = Store("Amalfi")
        amalfi.addLocation(Location())
        stores.add(amalfi)

        val salerno = Store("Salerno")
        salerno.addLocation(Location())
        stores.add(salerno)

        //South of Italy
        val tropea = Store("Tropea")
        tropea.addLocation(Location())
        stores.add(tropea)

        val reggioDiCalabria = Store("Reggio di Calabria")
        reggioDiCalabria.addLocation(Location())
        stores.add(reggioDiCalabria)

        val brindisi = Store("Brindisi")
        brindisi.addLocation(Location())
        stores.add(brindisi)

        val otranto = Store("Otranto")
        otranto.addLocation(Location())
        stores.add(otranto)

        val bari = Store("Bari")
        bari.addLocation(Location())
        stores.add(bari)

        return stores.toList()
    }

    private fun buildSicily(): List<Store> {
        val stores: MutableList<Store> = mutableListOf()

        // Sicily
        val palermo = Store("Palermo")
        palermo.addLocation(Location())
        stores.add(palermo)

        val millazzo = Store("Millazzo")
        millazzo.addLocation(Location())
        stores.add(millazzo)

        val messina = Store("Messina")
        messina.addLocation(Location())
        stores.add(messina)

        val taormina = Store("Taormina")
        taormina.addLocation(Location())
        stores.add(taormina)

        val riposto = Store("Riposto")
        riposto.addLocation(Location())
        stores.add(riposto)

        val catania = Store("Catania")
        catania.addLocation(Location())
        stores.add(catania)

        val syracuse = Store("Syracuse")
        syracuse.addLocation(Location())
        stores.add(syracuse)

        return stores.toList()
    }

    private fun buildMalta() : List<Store> {
        val stores: MutableList<Store> = mutableListOf()

        // Malta
        val maltaIsland = Store("Malta Island")
        maltaIsland.addLocation(Location())
        stores.add(maltaIsland)

        val gozoIsland = Store("Gozo Island")
        gozoIsland.addLocation(Location())
        stores.add(gozoIsland)

        return stores.toList()
    }

    private fun buildCorsica(): List<Store> {
        val stores: MutableList<Store> = mutableListOf()

        // Corsica
        val calvi = Store("Calvi")
        calvi.addLocation(Location())
        stores.add(calvi)

        val saintFlorent = Store("Saint Florent")
        saintFlorent.addLocation(Location())
        stores.add(saintFlorent)

        val bastia = Store("Bastia")
        bastia.addLocation(Location())
        stores.add(bastia)

        val portoVecchio = Store("Porto Vecchio")
        portoVecchio.addLocation(Location())
        stores.add(portoVecchio)

        val bonifacio = Store("Bonifacio")
        bonifacio.addLocation(Location())
        stores.add(bonifacio)

        val ajjaccio = Store("Ajjaccio")
        ajjaccio.addLocation(Location())
        stores.add(ajjaccio)

        return stores.toList()
    }

    private fun buildSardinia(): List<Store> {
        val stores: MutableList<Store> = mutableListOf()

        // Sardinia
        val baiaSardinia = Store("Baia Sardinia")
        baiaSardinia.addLocation(Location())
        stores.add(baiaSardinia)

        val portoCervo = Store("Porto Cervo")
        portoCervo.addLocation(Location())
        stores.add(portoCervo)

        val portoRotondo = Store("Porto Rotondo")
        portoRotondo.addLocation(Location())
        stores.add(portoRotondo)

        val portoSanPaolo = Store("Porto San paolo")
        portoSanPaolo.addLocation(Location())
        stores.add(portoSanPaolo)

        val olbia = Store("Olbia")
        olbia.addLocation(Location())
        stores.add(olbia)

        val palau = Store("Palau")
        palau.addLocation(Location())
        stores.add(palau)

        val poltuQuatu = Store("Poltu Quatu")
        poltuQuatu.addLocation(Location())
        stores.add(poltuQuatu)

        val calaBitta = Store("Cala Bitta")
        calaBitta.addLocation(Location())
        stores.add(calaBitta)

        val cannigione = Store("Cannigione")
        cannigione.addLocation(Location())
        stores.add(cannigione)

        val cagliari = Store("Cagliari")
        cagliari.addLocation(Location())
        stores.add(cagliari)

        val laMaddalena = Store("La Maddalena")
        laMaddalena.addLocation(Location())
        stores.add(laMaddalena)

        return stores.toList()
    }

    private fun buildCroatiaAndMontenegro(): List<Store> {
        val stores: MutableList<Store> = mutableListOf()

        // Croatia & Montenegro
        val venice = Store("Venice")
        venice.addLocation(Location())
        stores.add(venice)

        val piran = Store("Piran")
        piran.addLocation(Location())
        stores.add(piran)

        val rovinj = Store("Rovinj")
        rovinj.addLocation(Location())
        stores.add(rovinj)

        val pula = Store("Pula")
        pula.addLocation(Location())
        stores.add(pula)

        val zadar = Store("Zadar")
        zadar.addLocation(Location())
        stores.add(zadar)

        val sibenik = Store("Sibenik")
        sibenik.addLocation(Location())
        stores.add(sibenik)

        val trogir = Store("Trogir")
        trogir.addLocation(Location())
        stores.add(trogir)

        val split = Store("Split")
        split.addLocation(Location())
        stores.add(split)

        val hvar = Store("Hvar")
        hvar.addLocation(Location())
        stores.add(hvar)

        val dubrovnik = Store("Dubrovnik")
        dubrovnik.addLocation(Location())
        stores.add(dubrovnik)

        val cavtat = Store("Cavtat")
        cavtat.addLocation(Location())
        stores.add(cavtat)

        val kotor = Store("Kotor")
        kotor.addLocation(Location())
        stores.add(kotor)

        val tivat = Store("Tivat")
        tivat.addLocation(Location())
        stores.add(tivat)

        return stores.toList()
    }

    private fun buildAlbania(): List<Store> {
        val stores: MutableList<Store> = mutableListOf()

        //Albania
        val sarande =Store("Sarande")
        sarande.addLocation(Location())
        stores.add(sarande)

        return stores.toList()
    }

    private fun buildGreece(): List<Store> {
        val stores: MutableList<Store> = mutableListOf()

        // Greece (Ionian)
        val corfu = Store("Corfu")
        corfu.addLocation(Location())
        stores.add(corfu)

        val zakynthos = Store("Zakynthos")
        zakynthos.addLocation(Location())
        stores.add(zakynthos)

        val kefalonia = Store("Kefalonia")
        kefalonia.addLocation(Location())
        stores.add(kefalonia)

        // Greece (Eagen)
        val athens = Store("Athens")
        athens.addLocation(Location())
        stores.add(athens)

        val mikonos = Store("Mikonos")
        mikonos.addLocation(Location())
        stores.add(mikonos)

        val santorini = Store("Santorini")
        santorini.addLocation(Location())
        stores.add(santorini)

        val rhodes = Store("Rhodes")
        rhodes.addLocation(Location())
        stores.add(rhodes)

        val kos = Store("Kos")
        kos.addLocation(Location())
        stores.add(kos)

        return stores.toList()
    }

    private fun buildTurkey(): List<Store> {
        val stores: MutableList<Store> = mutableListOf()

        //Turkey
        val bodrum = Store("Bodrum")
        bodrum.addLocation(Location())
        stores.add(bodrum)

        val izmir = Store("Izmir")
        izmir.addLocation(Location())
        stores.add(izmir)

        val marmaris = Store("Marmaris")
        marmaris.addLocation(Location())
        stores.add(marmaris)

        val antalya = Store("Antalya")
        antalya.addLocation(Location())
        stores.add(antalya)

        return stores.toList()
    }

    private fun buildCaribbean(): List<Store> {
        val stores: MutableList<Store> = mutableListOf()

        // Caribbean
        val saintMaarten = Store("Saint Maarten")
        saintMaarten.addLocation(Location())
        stores.add(saintMaarten)

        val antigua = Store("Antigua")
        antigua.addLocation(Location())
        stores.add(antigua)

        val stBarths = Store("St. barths")
        stBarths.addLocation(Location())
        stores.add(stBarths)

        val stLucia = Store("St. Lucia")
        stLucia.addLocation(Location())
        stores.add(stLucia)

        val dominica = Store("Dominica")
        dominica.addLocation(Location())
        stores.add(dominica)

        val guadeloupe = Store("Guadeloupe")
        guadeloupe.addLocation(Location())
        stores.add(guadeloupe)

        val bvi = Store("BVI")
        bvi.addLocation(Location())
        stores.add(bvi)

        val usvi = Store("USVI")
        usvi.addLocation(Location())
        stores.add(usvi)

        return stores.toList()
    }

    private fun buildFloridaAndBahamas(): List<Store> {
        val stores: MutableList<Store> = mutableListOf()

        //Florida & Bahamas
        val miami = Store("Miami")
        miami.addLocation(Location())
        stores.add(miami)

        val fortLauderdale = Store("Fort Lauderdale")
        fortLauderdale.addLocation(Location())
        stores.add(fortLauderdale)

        val westPalmBeach = Store("West Palm beach")
        westPalmBeach.addLocation(Location())
        stores.add(westPalmBeach)

        val nassau = Store("Nassau")
        nassau.addLocation(Location())
        stores.add(nassau)

        return stores.toList()
    }

    private fun buildMaldives(): List<Store> {
        val stores: MutableList<Store> = mutableListOf()

        //Maldives
        val male = Store("Male")
        male.addLocation(Location())
        stores.add(male)

        return stores.toList()
    }

    override fun getAllStores(): List<Store> {
        val allStores: MutableList<Store> = mutableListOf()

        allStores.addAll(buildSpainMainLand())
        allStores.addAll(buildBaleraicsIslands())
        allStores.addAll(buildSouthOfFrance())
        allStores.addAll(buildItaly())
        allStores.addAll(buildSicily())
        allStores.addAll(buildMalta())
        allStores.addAll(buildCorsica())
        allStores.addAll(buildSardinia())
        allStores.addAll(buildCroatiaAndMontenegro())
        allStores.addAll(buildAlbania())
        allStores.addAll(buildGreece())
        allStores.addAll(buildTurkey())
        allStores.addAll(buildCaribbean())
        allStores.addAll(buildFloridaAndBahamas())
        allStores.addAll(buildMaldives())

        var i = 1
        allStores.forEach {
            it.id = i
            i++
        }

        return allStores
    }
}
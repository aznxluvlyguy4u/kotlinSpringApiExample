package com.oceanpremium.api.locations.controller


class Location(val id: Int, var name: String? = null, var store_id: Int = 0)
class Store(val id: Int, var name: String, val locations: MutableList<Location> = mutableListOf()) {
    fun addLocation(location: Location) {
        location.store_id = id

        if (location.name == null) {
            location.name = name
        }

        locations.add(location)
    }
}

/**
 *
 */
class LocationMapping {

    fun getStores(): Any? {
        // Spain - mainland
        Store(1, "Gibraltar").addLocation(Location(1))

        val marbella = Store(2, "Marbella")
        marbella.addLocation(Location(2)) // creates a location with the same store name
        marbella.addLocation(Location(3, "Cala de Mijas"))
        marbella.addLocation(Location(4, "La Lacaidesa"))


        val malaga = Store(3, "Malaga")
        malaga.addLocation(Location((5)))
        malaga.addLocation(Location(5, "Almunecar"))
        malaga.addLocation(Location(6, "Fuengirola"))


        val almeria =  Store(4, "Almeria")
        almeria.addLocation(Location(7))
        almeria.addLocation(Location(8, "Aguilas"))
        almeria.addLocation(Location(9,"Mortil"))


        val alicante = Store(5, "Alicante")
        alicante.addLocation(Location(10))
        alicante.addLocation(Location(11, "Calp"))
        alicante.addLocation(Location(12, "Cartagena"))


        val valencia = Store(6, "Valencia")
        valencia.addLocation(Location(13))
        valencia.addLocation(Location(14, "Tarragona"))
        valencia.addLocation(Location(15))
        valencia.addLocation(Location(16))


        Store(7, "Denia").addLocation(Location(17))

        val barcelona = Store(8, "Barcelona")
        barcelona.addLocation(Location(18))
        barcelona.addLocation(Location(18, "Sitges"))

        Store(9, "Roses").addLocation(Location(19))

        //Baleraics Islands
        val ibiza = Store(10, "Ibiza")
        ibiza.addLocation(Location(20, "Ibiza Island"))
        ibiza.addLocation(Location(21, "Formentera island"))

        val mallorca = Store(11, "Mallorca")
        mallorca.addLocation(Location(22, "Island - North/East"))
        mallorca.addLocation(Location(22, "Palma"))
        mallorca.addLocation(Location(22, "Port Portals"))
        mallorca.addLocation(Location(22, "Porto Adriano"))

        Store(12, "Menorca").addLocation(Location(22, "Menorca Island"))


        //South Of France
        Store(13, "Port Vendres").addLocation(Location(22))
        Store(14, "Marseilles").addLocation(Location(22))

        val toulon = Store(15, "Toulon")
        toulon.addLocation(Location(22 ))
        toulon.addLocation(Location(22, "Cavalaire sur Mer"))
        toulon.addLocation(Location(22, "Hyeres"))
        toulon.addLocation(Location(22, "La Ciotat"))


        val stTropez = Store(16, "St. Tropez")
        stTropez.addLocation(Location(22))
        stTropez.addLocation(Location(22, "Saint Raphael"))
        stTropez.addLocation(Location(22, "Pampellonne beach"))

        val cannes = Store(17, "Cannes")
        cannes.addLocation(Location(22))
        cannes.addLocation(Location(22, "Mandelieu la Napoule"))
        cannes.addLocation(Location(22, "Theule sur Mer"))

        val antibes = Store(18, "Antibes")
        antibes.addLocation(Location(22))
        antibes.addLocation(Location(22, "Marina Baie Des Anges"))
        antibes.addLocation(Location(22, "Golfe Juan"))

        val nice = Store(19, "Nice")
        nice.addLocation(Location(22))
        nice.addLocation(Location(22, "Villefranche sur Mer"))
        nice.addLocation(Location(22, "Cagnes sur Mer"))

        val monaco = Store(20, "Monaco")
        monaco.addLocation(Location(22))
        monaco.addLocation(Location(22, "Cap d'Ail"))
        monaco.addLocation(Location(22, "Saint Jean Cap Ferrat"))
        monaco.addLocation(Location(22, "Beaulieu sur Mer"))

        //Liguria (Italy)
        val sanremo = Store(21, "Sanremo")
        sanremo.addLocation(Location(22))
        sanremo.addLocation(Location(22, "Imperia"))
        sanremo.addLocation(Location(22, "Ventimiglia"))


        //Tuscany (Italy)
        val loano = Store(22, "Loano")
        loano.addLocation(Location(22))
        loano.addLocation(Location(22, "Albenga"))
        loano.addLocation(Location(22, "Savona"))
        loano.addLocation(Location(22, "Varazze"))

        val genoa = Store(23, "Genoa")
        genoa.addLocation(Location((22, "Genoa Aeroporto"))
        genoa.addLocation(Location((22, "Genoa molo vecchio"))

        Store(24, "Portofino").addLocation(Location(22))

        Store(25, "La Spezia").addLocation(Location(22))

        Store(26, "Viareggio").addLocation(Location(22))

        Store(27, "Livorno").addLocation(Location(22))

        Store(28, "Piombino").addLocation(Location(22))

        Store(29, "Grosseto").addLocation(Location(22))

        Store(30, "Argentario").addLocation(Location(22))

        Store(31, "Civitavecchia").addLocation(Location(22))


        // Amalfi (Italy)
        Store(32, "Gaeta").addLocation(Location(22))

        Store(33, "Naples").addLocation(Location(22))

        Store(34, "Marina di Stabia").addLocation(Location(22))

        Store(35, "Sorrento").addLocation(Location(22))

        Store(36, "Capri").addLocation(Location(22))
        Store(37, "Positano").addLocation(Location(22))
        Store(38, "Amalfi").addLocation(Location(22))
        Store(39, "Salerno").addLocation(Location(22))

        //South of Italy
        Store(40, "Tropea").addLocation(Location(22))
        Store(41, "Reggio di Calabria").addLocation(Location(22))
        Store(42, "Brindisi").addLocation(Location(22))
        Store(43, "Otranto").addLocation(Location(22))
        Store(44, "Bari").addLocation(Location(22))

        // Sicily
        Store(45, "Palermo")
        Store(46, "Millazzo")
        Store(47, "Messina")
        Store(48, "Taormina")
        Store(49, "Riposto")
        Store(50, "Catania")
        Store(51, "Syracuse")

        // Malta
        Store(52, "Malta Island")
        Store(53, "Gozo Island")

        // Corsica
        Store(54, "Calvi")
        Store(55, "Saint Florent")
        Store(56, "Bastia")
        Store(57, "Porto Vecchio")
        Store(58, "Bonifacio")
        Store(59, "Ajjaccio")

        // Sardinia
        Store(60, "Baia Sardinia")
        Store(61, "Porto Cervo")
        Store(62, "Porto Rotondo")
        Store(63, "Porto San paolo")
        Store(64, "Olbia")
        Store(65, "Palau")
        Store(66, "Poltu Quatu")
        Store(67, "Cala Bitta")
        Store(68, "Cannigione")
        Store(69, "Cagliari")
        Store(70, "La Maddalena")

        // Croatia & Montenegro
        Store(71, "Venice")
        Store(72, "Piran")
        Store(73, "Rovinj")
        Store(74, "Pula")
        Store(75, "Zadar")
        Store(76, "Sibenik")
        Store(77, "Trogir")
        Store(78, "Split")
        Store(79, "Hvar")
        Store(80, "Dubrovnik")
        Store(81, "Cavtat")
        Store(82, "Kotor")
        Store(83, "Tivat")

        //Albania
        Store(84, "Sarande")

        // Greece (Ionian)
        Store(85, "Corfu")
        Store(86, "Zakynthos")
        Store(87, "Kefalonia")

        // Greece (Eagen)
        Store(88, "Athens")
        Store(89, "Mikonos")
        Store(90, "Santorini")
        Store(91, "Rhodes")
        Store(92, "Kos")

        //Turkey
        Store(93, "Bodrum")
        Store(94, "Izmir")
        Store(95, "Marmaris")
        Store(96, "Antalya")

        // Caribbean
        Store(97, "Saint Maarten")
        Store(98, "Antigua")
        Store(99, "St. barths")
        Store(100, "St. Lucia")
        Store(110, "Dominica")
        Store(102, "Guadeloupe")
        Store(103, "BVI")
        Store(104, "USVI")

        //Florida & Bahamas
        Store(105, "Miami")
        Store(106, "Fort Lauderdale")
        Store(107, "West Palm beach")
        Store(108, "Nassau")

        //Maldives
        Store(109, "Male")
    }

    /**
     *
     */
    fun getLocations(): Any? {

        Location("Gibraltar", 0)
        Location("", 0)

        return null
    }
}
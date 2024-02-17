package starwars.domain

import ddd.DomaineService
import starwars.domain.api.AssembleAFleet
import starwars.domain.spi.StarShipInventory

@DomaineService
class FleetAssembler(private val starShipInventory: StarShipInventory) : AssembleAFleet {

    override fun forPassengers(numberOfPassengers: Int): Fleet {
        val  starShips : List<StarShip> = getStarShipHavingPassengerCapacity()
        val rescueStarship : List<StarShip> = selectStartShip(numberOfPassengers, starShips)

        return Fleet(rescueStarship)
    }

    private fun selectStartShip(numberOfPassengers: Int, starShips: List<StarShip>): List<StarShip> {
        val selectedStarShips = mutableListOf<StarShip>()
        var totalCapacity = 0

        // Tri des vaisseaux spatiaux en fonction de leur capacité
        val sortedShips = starShips.sortedByDescending { it.capacity }

        // Sélection des vaisseaux spatiaux
        for (ship in sortedShips) {
            totalCapacity += ship.capacity
            selectedStarShips.add(ship)
            if (totalCapacity >= numberOfPassengers) {
                break
            }
        }

        return selectedStarShips

    }

    private fun getStarShipHavingPassengerCapacity(): List<StarShip> {
        return starShipInventory.starShips().filter { it.capacity > 0 }.sortedByDescending { it.capacity }
    }

}

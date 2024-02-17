package starwars.domain


import io.kotest.inspectors.shouldForAll
import io.kotest.matchers.ints.shouldBeGreaterThan
import starwars.domain.Fleet
import starwars.domain.FleetAssembler
import starwars.domain.StarShip
import starwars.domain.api.AssembleAFleet
import starwars.domain.spi.StarShipInventory
import org.junit.jupiter.api.Test

class AssembleAFleetTest {

    @Test
    fun `init should assemble a fleet for 1050 passenger`(){

        val numberOfPassengers = 1050

        val fleet : Fleet = Fleet(emptyList())

        fleet.starships.shouldForAll { it.capacity > 0 }
        fleet.starships.sumOf { it.capacity } shouldBeGreaterThan numberOfPassengers


    }

    @Test
    fun `should assemble a fleet for 1050 passenger`(){

        val numberOfPassengers = 1050

        val starShips = listOf(
            StarShip("no-passenger",0),
            StarShip("xs",10),
            StarShip("s",50),
            StarShip("m",200),
            StarShip("l",800),
            StarShip("xl",2000),

        )
        val startShipInventory = StarShipInventory{starShips}
        val assembleAfleet : AssembleAFleet =  FleetAssembler(startShipInventory)
        val fleet : Fleet = assembleAfleet.forPassengers(numberOfPassengers)

        fleet.starships.shouldForAll { it.capacity > 0 }
        fleet.starships.sumOf { it.capacity } shouldBeGreaterThan numberOfPassengers


    }
}

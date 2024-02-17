package starwars.domain.spi.externalStubs

import ddd.Stub
import starwars.domain.StarShip
import starwars.domain.spi.StarShipInventory

@Stub
class StarShipInventoryStub : StarShipInventory {
    override fun starShips(): List<StarShip> =
        listOf(
            StarShip("x-wing",0),
            StarShip("Millenium falcon",6),
            StarShip("Rebel transport",80),
            StarShip("Star cruisers",1200),

            )
    }

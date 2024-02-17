package starwars.domain.spi

import starwars.domain.StarShip


fun interface StarShipInventory {
    fun starShips(): List<StarShip>
}

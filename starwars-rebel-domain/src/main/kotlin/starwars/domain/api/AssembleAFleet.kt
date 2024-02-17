package starwars.domain.api

import starwars.domain.Fleet


interface AssembleAFleet {
    fun forPassengers(numberOfPassengers: Int): Fleet
}

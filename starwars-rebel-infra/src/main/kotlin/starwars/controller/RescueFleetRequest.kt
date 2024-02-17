package starwars.controller

import com.fasterxml.jackson.annotation.JsonProperty


data class RescueFleetRequest(@JsonProperty("numberOfPassengers") val numberOfPassengers : Int)

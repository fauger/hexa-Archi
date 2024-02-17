package starwars.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import starwars.domain.Fleet
import starwars.domain.api.AssembleAFleet

@RestController
@RequestMapping("/rescueFleets")
class FleetController {

    @Autowired
    private lateinit var assemblerFleet : AssembleAFleet


    @PostMapping
    fun assembleFeet(@RequestBody rescueFleetRequest : RescueFleetRequest) : ResponseEntity<Fleet> {
        return ResponseEntity.ok().body(assemblerFleet.forPassengers(rescueFleetRequest.numberOfPassengers))
    }
}

package starwars

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class StarWarsRescueApplication {
    fun main(args: Array<String>) {
        runApplication<StarWarsRescueApplication>(*args)
    }
}



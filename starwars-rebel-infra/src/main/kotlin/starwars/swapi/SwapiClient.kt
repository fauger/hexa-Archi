package starwars.swapi

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import starwars.controller.swapi.model.SwapiResponse
import starwars.controller.swapi.model.SwapiStarship
import starwars.domain.StarShip
import starwars.domain.spi.StarShipInventory

@Component
class SwapiClient(restTemplateBuilder: RestTemplateBuilder) : StarShipInventory {

    private val restTemplate: RestTemplate = restTemplateBuilder.build()

    @Value("\${swapi.base-uri}")
    private lateinit var swapiBaseUri: String

    override fun starShips(): List<StarShip> =
        getStarShipFromSwapi(swapiBaseUri + "api/starships").filterIsInstance<SwapiResponse.Success>()
            .flatMap { success ->
                success.results.mapNotNull { starship ->
                    starship.takeIf { it.hasValidPassengersValue() }
                        ?.run { StarShip(name, passengers?.replace(",","")?.toIntOrNull() ?: 0) }
                }
            }

private fun getStarShipFromSwapi(url: String): List<SwapiResponse> {
    val allPages: MutableList<SwapiResponse> = mutableListOf()
    var nextUrl: String? = url
    while (nextUrl != null) {
        val response = restTemplate.getForObject(nextUrl, SwapiResponse.Success::class.java)
            ?: SwapiResponse.Error("error")
        allPages.add(response)
        nextUrl = (response as? SwapiResponse.Success)?.next
    }
    return allPages
}

private fun SwapiStarship.hasValidPassengersValue() =
    (passengers != null) && (passengers !in invalidCapacitiesValues)

companion object {
    val invalidCapacitiesValues = listOf("n/a", "unknown")
}

}

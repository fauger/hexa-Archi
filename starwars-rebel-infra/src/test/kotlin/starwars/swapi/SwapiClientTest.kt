package starwars.swapi

import io.kotest.matchers.collections.shouldContainInOrder
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest
import org.springframework.core.io.Resource
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.test.web.client.MockRestServiceServer
import org.springframework.test.web.client.match.MockRestRequestMatchers
import org.springframework.test.web.client.response.MockRestResponseCreators
import starwars.domain.StarShip
import starwars.domain.spi.StarShipInventory

@RestClientTest(SwapiClient::class)
class SwapiClientTest {

    @Autowired
    lateinit var mockService: MockRestServiceServer

    @Value("classpath:swapi/swapi-page1.json")
    lateinit var swapiPage1: Resource

    @Value("classpath:swapi/swapi-page2.json")
    lateinit var swapiPage2: Resource

    @Autowired
    private lateinit var swapiClient: StarShipInventory


    @Test
    fun `should return ship inventory`() {
        mockService.expect(MockRestRequestMatchers.method(HttpMethod.GET))
            .andExpect(MockRestRequestMatchers.requestTo("http://swapi/api/starships"))
            .andRespond(MockRestResponseCreators.withSuccess(swapiPage1, MediaType.APPLICATION_JSON))

        mockService.expect(MockRestRequestMatchers.method(HttpMethod.GET))
            .andExpect(MockRestRequestMatchers.requestTo("http://swapi/api/starships/?page=2"))
            .andRespond(MockRestResponseCreators.withSuccess(swapiPage2, MediaType.APPLICATION_JSON))

        swapiClient.starShips().shouldContainInOrder(
            StarShip("CR90 corvette", 600),
            StarShip("Slave 1", 6),
            StarShip("Death Star", 843342),
            StarShip("Naboo star skiff", 3)
        )

    }
}

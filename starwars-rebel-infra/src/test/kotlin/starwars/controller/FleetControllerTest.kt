package starwars.controller

import com.fasterxml.jackson.databind.ObjectMapper
import configurations.DomainConfiguration
import org.hamcrest.Matcher
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post


@WebMvcTest(FleetController::class)
@AutoConfigureMockMvc
@Import(DomainConfiguration::class)
class FleetControllerTest {

    @Autowired
    lateinit var mockMvc : MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun `should assemble a rescue fleet`() {

        mockMvc.post("/rescueFleets") {
            accept = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(RescueFleetRequest(50))
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            jsonPath("$.starships"){ exists() }
            jsonPath("$.starships[0].name"){ value("Star cruisers") }
        }
    }

}

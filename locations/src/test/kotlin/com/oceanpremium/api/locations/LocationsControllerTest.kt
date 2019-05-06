package com.oceanpremium.api.locations

import com.oceanpremium.api.core.model.Response
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LocationsControllerTest {

    @Autowired
    val restTemplate: TestRestTemplate? = null

    companion object {
        private const val endpoint = "/api/v1/locations"
    }

    /**
     * Get location API docs.
     */
    @Test
    fun testGetProductApiDocs() {
        val response = restTemplate?.getForEntity("$endpoint/docs", Response::class.java)
        assertThat(response?.statusCodeValue).isEqualTo(HttpStatus.OK.value())
    }

    /**
     * Retrieve locations.
     */
    @Test
    fun testGetLocations() {

        val response = restTemplate?.getForEntity(endpoint, Response::class.java)

        // Assert that the HTTP response code is OK
        assertThat(response?.statusCodeValue).isEqualTo(HttpStatus.OK.value())
    }
}
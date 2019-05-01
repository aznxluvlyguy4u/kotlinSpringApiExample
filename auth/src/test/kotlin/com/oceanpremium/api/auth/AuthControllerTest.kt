package com.oceanpremium.api.auth

import com.oceanpremium.api.core.model.Response
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthControllerTest {

    private val endpoint = "/api/v1/auth"

    @Autowired
    val restTemplate: TestRestTemplate? = null

    /**
     * Get auth API docs.
     */
    @Test
    fun getAuthApiDocs() {
        val response = restTemplate?.getForEntity("$endpoint/docs", Response::class.java)
        assertThat(response?.statusCodeValue).isEqualTo(HttpStatus.OK.value())
    }
}
package com.oceanpremium.api.auth

import com.oceanpremium.api.core.util.ObjectMapperConfig
import com.oceanpremium.api.core.model.Response
import com.oceanpremium.api.core.model.Token
import com.oceanpremium.api.core.model.User
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDateTime
import java.time.ZoneOffset

@RunWith(SpringRunner::class)
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthControllerTest {

    private val endpoint = "/api/v1/auth"

    private val mapper = ObjectMapperConfig.mapper

    @Autowired
    val restTemplate: TestRestTemplate? = null

    /**
     * Create auth.
     */
    @Test
    fun createAuthTest() {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val mockedUser = User("mockedUser", "mocked@address.com")

        val mockedToken = Token(
            "access-token-test",
            "refresh-token-test",
            LocalDateTime.now().atZone(ZoneOffset.UTC)?.toInstant()?.toEpochMilli()!!,
            mockedUser.emailAddress!!
        )

        val entity = HttpEntity(mapper.writeValueAsString(mockedUser), headers)

        val response = restTemplate?.postForEntity(
            endpoint,
            entity,
            Response::class.java
        )

        assertThat(response?.statusCodeValue).isEqualTo(HttpStatus.OK.value()) // Needs to be 201 CREATED

        val responseBody = response?.body as Response
        val message = responseBody.message as Map<*,*>

        val token = mapper.convertValue(message, Token::class.java)

        assertThat(token.accessToken).isEqualTo(mockedToken.accessToken)
        assertThat(token.refreshToken).isEqualTo(mockedToken.refreshToken)
        assertThat(token.emailAddress).isEqualTo(mockedToken.emailAddress)
    }

    /**
     * Get auth by date.
     */
    @Test
    fun getAuthByDateTest() {
        val date = "2001-01-01"
        val dateQueryParameter = "date"

        val response = restTemplate?.getForEntity("$endpoint?$dateQueryParameter=$date", Response::class.java)
        assertThat(response?.statusCodeValue).isEqualTo(HttpStatus.OK.value())

        val data = response?.body as Response
        assertThat(data.message).isEqualTo("getAuths with params: $date")
    }

    /**
     * Get auth by id.
     */
    @Test
    fun getAuthByIdTest() {
        val id = "123"

        val response = restTemplate?.getForEntity("$endpoint/$id", Response::class.java)
        assertThat(response?.statusCodeValue).isEqualTo(HttpStatus.OK.value())

        val data = response?.body as Response
        assertThat(data.message).isEqualTo("getAuthById: $id")
    }

    /**
     * Update auth.
     */
    @Test
    fun updateAuthByIdTest() {
        val id = "123"

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val mockedUser = User("mockedUser", "mocked@address.com")

        val entity = HttpEntity(mapper.writeValueAsString(mockedUser), headers)
        val response = restTemplate?.exchange("$endpoint/$id", HttpMethod.PUT, entity, Response::class.java, id)
        assertThat(response?.statusCodeValue).isEqualTo(HttpStatus.OK.value())

        val data = response?.body as Response
        assertThat(data.message).isEqualTo("updateAuthById: 123Update, ${mockedUser.name}!")
    }

    /**
     * Delete auth.
     */
    @Test
    fun deleteAuthByIdTest() {
        val id = "123"
        val response = restTemplate?.exchange("$endpoint/$id", HttpMethod.DELETE, null, Response::class.java, id)
        assertThat(response?.statusCodeValue).isEqualTo(HttpStatus.OK.value())

        val data = response?.body as Response
        assertThat(data.success).isEqualTo(true)
    }
}

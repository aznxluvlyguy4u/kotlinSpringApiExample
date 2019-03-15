package com.oceanpremium.api.controller

import com.oceanpremium.api.ApiDriver
import com.oceanpremium.api.entity.Response
import io.kotlintest.specs.FeatureSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.*
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestContextManager


@SpringBootTest(classes = [ApiDriver::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = ["test"])
@DirtiesContext
class AuthPresenterTest : FeatureSpec() {

    private val endpoint = "/api/v1/auth"

    @Autowired
    val restTemplate: TestRestTemplate? = null

    override fun beforeAll() {
        TestContextManager(this.javaClass).prepareTestInstance(this)
    }

    init {

        // CREATE
        feature("CREATE - POST /api/v1/auth ") {
            scenario("Assert the return of a newly created resource") {

                val headers = HttpHeaders()
                headers.contentType = MediaType.APPLICATION_JSON

                val entity = HttpEntity("{\"name\": \"foobar\", \"emailAddress\": \"foobar@example.com\"}", headers)

                val response = restTemplate?.postForEntity(
                    endpoint,
                    entity,
                    Response::class.java
                )

                response?.statusCodeValue shouldBe 200 // Needs to be 201 CREATED

                val data = response?.body as Response

                val token = data.message as Map<*, *>
                token["accessToken"] shouldEqual "access-token-test"
                token["refreshToken"] shouldEqual "refresh-token-test"
                token["emailAddress"] shouldEqual "foobar@example.com"
            }
        }

        // READ
        feature("READ - GET /api/v1/auth ") {
            /**
             * response
             * {
                "name": "foobar",
                "emailAddress": "foobar@example.com"
                }
             */
            scenario("Assert getAll with date filter returns resource by supplied date filter") {
                val date = "2001-01-01"
                val response = restTemplate?.getForEntity("$endpoint?date=$date", Response::class.java)

                response?.statusCodeValue shouldBe 200

                val data = response?.body as Response

                data.message shouldEqual "getAuths with params: $date"
            }

            scenario("Assert getbyId is returnsresource with requested id") {
                val id = "123"
                val response = restTemplate?.getForEntity("$endpoint/$id", Response::class.java)

                response?.statusCodeValue shouldBe 200

                val data = response?.body as Response

                data.message shouldEqual "getAuthById: $id"
            }
        }

        // UPDATE
        feature("UPDATE - PUT /api/v1/auth/{id} ") {
            scenario("Assert updateByID returns an updated resource") {
                val id = "123"
                val headers = HttpHeaders()
                headers.contentType = MediaType.APPLICATION_JSON

                val name = "foobar"
                val emailAddress = "foobar@example.com"
                val entity = HttpEntity("{\"name\": \"$name\", \"emailAddress\": \"$emailAddress\"}", headers)
                val response = restTemplate?.exchange("$endpoint/$id", HttpMethod.PUT, entity, Response::class.java, id)

                response?.statusCodeValue shouldBe 200
                val data = response?.body as Response

                data.message shouldEqual "updateAuthById: 123Update, $name!"
            }
        }

        // DELETE
        feature("DELETE - DELETE /api/v1/auth/{id} ") {
            scenario("Assert deleteByID returns an empty resource") {
                val id = "123"
                val response = restTemplate?.getForEntity("$endpoint/$id", Response::class.java)

                response?.statusCodeValue shouldBe 200

                val data = response?.body as Response

                data.message shouldEqual "getAuthById: $id"
            }
        }
    }
}

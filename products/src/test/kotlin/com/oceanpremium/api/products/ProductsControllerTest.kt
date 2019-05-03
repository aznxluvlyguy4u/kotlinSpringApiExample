package com.oceanpremium.api.products

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
class ProductsControllerTest {

    @Autowired
    val restTemplate: TestRestTemplate? = null

    companion object {
        private val endpoint = "/api/v1/products"
    }

    /**
     * Get product API docs.
     */
    @Test
    fun testGetProductApiDocs() {
        val response = restTemplate?.getForEntity("$endpoint/docs", Response::class.java)
        assertThat(response?.statusCodeValue).isEqualTo(HttpStatus.OK.value())
    }

    /**
     * Find products by query parameters.
     */
    @Test
    fun testGetProductsByQueryParameters() {

        val searchKeyword = "Wakeboard"
        val params = "?q[name_cont]=$searchKeyword&page=1&per_page=20"
        val response = restTemplate?.getForEntity("$endpoint$params", Response::class.java)

        // Assert that the HTTP response code is OK
        assertThat(response?.statusCodeValue).isEqualTo(HttpStatus.OK.value())
    }

    /**
     * Get product by id.
     */
    @Test
    fun testGetProductById() {
        val productId = 19
        val response = restTemplate?.getForEntity("$endpoint/$productId", Response::class.java)

        // Assert that the HTTP response code is OK
        assertThat(response?.statusCodeValue).isEqualTo(HttpStatus.OK.value())
    }

    /**
     * Get products groups.
     */
    @Test
    fun testGetProductGroups() {
        val response = restTemplate?.getForEntity("$endpoint/groups", Response::class.java)

        // Assert that the HTTP response code is OK
        assertThat(response?.statusCodeValue).isEqualTo(HttpStatus.OK.value())
    }
}
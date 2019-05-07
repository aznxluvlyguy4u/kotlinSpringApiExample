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
        val params = "?page=1&per_page=2&q[name_or_product_group_name_or_product_tags_name_cont]=seabob"
        val response = restTemplate?.getForEntity("$endpoint$params", Response::class.java)

        // Assert that the HTTP response code is OK
        assertThat(response?.statusCodeValue).isEqualTo(HttpStatus.OK.value())
    }

    /**
     * Find products by query parameters.
     */
    @Test
    fun testGetProductsByQueryParametersNotFound() {
        val params = "?page=1&per_page=2&q[name_or_product_group_name_or_product_tags_name_cont]=testci"
        val response = restTemplate?.getForEntity("$endpoint$params", Response::class.java)

        // Assert that the HTTP response code is OK
        assertThat(response?.statusCodeValue).isEqualTo(HttpStatus.NOT_FOUND.value())
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
     * Get product by id.
     */
    @Test
    fun testGetProductByIdNotFound() {
        val productId = 9999999
        val response = restTemplate?.getForEntity("$endpoint/$productId", Response::class.java)

        // Assert that the HTTP response code is OK
        assertThat(response?.statusCodeValue).isEqualTo(HttpStatus.NOT_FOUND.value())
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
package com.oceanpremium.api.products

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
class ProductsControllerTest {

    private val endpoint = "/api/v1/products"

    @Autowired
    val restTemplate: TestRestTemplate? = null

    /**
     * Get product API docs.
     */
    @Test
    fun getProductApiDocs() {
        val response = restTemplate?.getForEntity("$endpoint/docs", Response::class.java)
        assertThat(response?.statusCodeValue).isEqualTo(HttpStatus.OK.value())
    }

    /**
     * Find products by query parameters.
     */
    @Test
    fun getProductsByQueryParameters() {
        val params = "?q[name_cont]=Wakeboard&page=1&per_page=20"
        val response = restTemplate?.getForEntity("$endpoint$params", Response::class.java)
        assertThat(response?.statusCodeValue).isEqualTo(HttpStatus.OK.value())
    }
}
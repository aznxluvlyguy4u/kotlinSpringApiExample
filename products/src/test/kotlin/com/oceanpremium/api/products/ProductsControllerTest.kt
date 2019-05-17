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

class ProductsResponse(var code: Int? = null, var data: List<Products>? = null)
class ProductGroups(var id: Int? = null, var name: String? = null)
class RateItem(var price: String? = null, var quantityAvailable: String? = null, var chargePeriod: String? = null)
class Image(var fullImageUrl: String? = null, var thumbnailUrl: String? = null)

class Products (
    var id : Int? = null,
    var name : String? = null,
    var description : String? = null,
    var productGroup: ProductGroups? = null,
    var rates: List<RateItem>? = null,
    var images: List<Image>? = null
)

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductsControllerTest {

    @Autowired
    val restTemplate: TestRestTemplate? = null

    internal class TestProduct(
        val id: Int = 247,
        val name: String = "jvtrubberduck",
        val group: String = "functionalintegrationtest"
    )

    companion object {
        private const val endpoint = "/api/v1/products"
        private val testProduct = TestProduct()
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
        val params = "q[name_or_product_group_name_or_product_tags_name_cont]=seabob"
        val response = restTemplate?.getForEntity("$endpoint?$params", Response::class.java)

        val productsResponse = restTemplate?.getForObject("$endpoint?$params", ProductsResponse::class.java)

        // Assert that the HTTP response code is OK
        assertThat(response?.statusCodeValue).isEqualTo(HttpStatus.OK.value())
    }

    /**
     * Find products by query parameters that are not found.
     */
    @Test
    fun testGetProductsByQueryParametersNotFound() {
        val params = "q[name_or_product_group_name_or_product_tags_name_cont]=continousintegration"
        val response = restTemplate?.getForEntity("$endpoint?$params", Response::class.java)

        // Assert that the HTTP response code is OK
        assertThat(response?.statusCodeValue).isEqualTo(HttpStatus.NOT_FOUND.value())
    }

    /**
     * Get product by id.
     */
    @Test
    fun testGetProductById() {
        val response = restTemplate?.getForEntity("$endpoint/${testProduct.id}", Response::class.java)

        // Assert that the HTTP response code is OK
        assertThat(response?.statusCodeValue).isEqualTo(HttpStatus.OK.value())
    }

    /**
     * Get product by id that is not found.
     */
    @Test
    fun testGetProductByIdNotFound() {
        val response = restTemplate?.getForEntity("$endpoint/99999999", Response::class.java)

        // Assert that the HTTP response code NOT FOUND
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

    /**
     * Get products inventory.
     */
    @Test
    fun testGetProductsInventory() {
        val params = "q[product_group_name_matches]=${testProduct.group}&q[product_name_cont]=${testProduct.name}"
        val response = restTemplate?.getForEntity("$endpoint/inventory?$params", Response::class.java)

        // Assert that the HTTP response code is OK
        assertThat(response?.statusCodeValue).isEqualTo(HttpStatus.OK.value())
    }

    /**
     * Get products inventory that is not found.
     */
    @Test
    fun testGetProductsInventoryNotFound() {
        val params = "q[product_tags_name_cont]=${testProduct.name}"
        val response = restTemplate?.getForEntity("$endpoint/inventory?$params", Response::class.java)

        // Assert that the HTTP response code NOT FOUND
        assertThat(response?.statusCodeValue).isEqualTo(HttpStatus.NOT_FOUND.value())
    }

    /**
     * Get products inventory that is not found on the FunctionalIntegrationTest product group.
     */
    @Test
    fun testGetProductsInventoryNotFoundOnTestProductGroup() {
        val params = "q[product_tags_name_cont]=${testProduct.name}"
        val response = restTemplate?.getForEntity("$endpoint/inventory?$params", Response::class.java)

        // Assert that the HTTP response code NOT FOUND
        assertThat(response?.statusCodeValue).isEqualTo(HttpStatus.NOT_FOUND.value())
    }
}

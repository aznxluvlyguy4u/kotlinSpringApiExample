package com.oceanpremium.api.products

import com.oceanpremium.api.core.currentrms.response.dto.parameter.QueryParametersResolverImpl.Companion.FUNCTIONAL_INTEGRATION_GROUP_NAME
import com.oceanpremium.api.core.model.ProductAvailabilityItem
import com.oceanpremium.api.core.model.RentalLocation
import com.oceanpremium.api.core.model.RentalPeriod
import com.oceanpremium.api.core.model.Response
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner
import java.util.*

class Errors(var errors: List<String>? = null)
class ErrorResponse(var code: Int? = null, var message: Errors? = null)
class ProductResponse(var code: Int? = null, var data: Products? = null)
class ProductsResponse(var code: Int? = null, var data: List<Products>? = null)
class ProductGroupsResponse(var code: Int?, var data: List<ProductGroup>? = null)
class RateItem(var price: String? = null, var quantityAvailable: String? = null, var chargePeriod: String? = null)
class Image(var fullImageUrl: String? = null, var thumbnailUrl: String? = null)
class CustomFields(var publicIconUrl: String? = null, var publicIconThumbUrl: String? = null)
class ProductGroup(var id: Int? = null, var name: String, var description: String?)

class Products (
    var id : Int? = null,
    var name : String? = null,
    var description : String? = null,
    var productGroup: ProductGroup? = null,
    var rates: List<RateItem>? = null,
    var images: List<Image>? = null,
    var customFields: CustomFields? = null
)

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductsControllerTest {

    @Autowired
    val restTemplate: TestRestTemplate? = null

    internal class TestProduct(
        val id: Int = 247,
        val name: String = "JVTRubberDuck",
        val group: String = FUNCTIONAL_INTEGRATION_GROUP_NAME
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
        val params = "tags=[\"ci\"]"
        val productsResponse = restTemplate?.getForObject("$endpoint?$params", ProductsResponse::class.java)

        assertThat(productsResponse).isNotNull
        assertThat(productsResponse?.code).isEqualTo(HttpStatus.OK.value())
        assertThat(productsResponse?.data).isNotNull

        val productItems = productsResponse?.data
        assertThat(productItems).isNotEmpty

        productItems?.forEach {
            assertThat(it.name).startsWith("JVT")
        }
    }

    /**
     * Find products by query parameters that are not found.
     */
    @Test
    fun testGetProductsByQueryParametersNotFound() {
        val params = "tags=[\"notfound\"]"
        val productsErrorResponse = restTemplate?.getForObject("$endpoint?$params", ErrorResponse::class.java)

        assertThat(productsErrorResponse).isNotNull
        assertThat(productsErrorResponse?.code).isEqualTo(HttpStatus.NOT_FOUND.value())
        assertThat(productsErrorResponse?.message).isNotNull

        val errorMessage = productsErrorResponse?.message
        assertThat(errorMessage?.errors).isNotEmpty

        errorMessage?.errors?.forEach {
            assertThat(it.toLowerCase()).contains("not found")
        }
    }

    /**
     * Get product by id.
     */
    @Test
    fun testGetProductById() {
        val productsResponse = restTemplate?.getForObject("$endpoint/${testProduct.id}", ProductResponse::class.java)

        assertThat(productsResponse).isNotNull
        assertThat(productsResponse?.code).isEqualTo(HttpStatus.OK.value())
        assertThat(productsResponse?.data).isNotNull

        val productItem = productsResponse?.data
        assertThat(productItem).isNotNull
        assertThat(productItem?.id).isEqualTo(testProduct.id)
    }

    /**
     * Get product by id that is not found.
     */
    @Test
    fun testGetProductByIdNotFound() {
        val id = 9999999
        val productErrorResponse = restTemplate?.getForObject("$endpoint/$id", ErrorResponse::class.java)

        assertThat(productErrorResponse).isNotNull
        assertThat(productErrorResponse?.code).isEqualTo(HttpStatus.NOT_FOUND.value())
        assertThat(productErrorResponse?.message).isNotNull

        val errorMessage = productErrorResponse?.message
        assertThat(errorMessage?.errors).isNotEmpty

        errorMessage?.errors?.forEach {
            assertThat(it.toLowerCase()).contains("couldn't find product with 'id'=$id")
        }
    }

    /**
     * Get products groups.
     * Note that this test does not yet account for paginated results, so it could actually miss a hit.
     */
    @Test
    fun testGetProductGroups() {
        val productGroupsResponse = restTemplate?.getForObject("$endpoint/groups?page=1&per_page=100", ProductGroupsResponse::class.java)

        assertThat(productGroupsResponse).isNotNull
        assertThat(productGroupsResponse?.code).isEqualTo(HttpStatus.OK.value())
        assertThat(productGroupsResponse?.data).isNotNull

        val productGroupItem = productGroupsResponse?.data
        assertThat(productGroupItem).isNotNull

        val filteredGroups  = productGroupItem?.filter { item -> item.name.contains(testProduct.group)}

        assertThat(filteredGroups).isNotNull
        assertThat(filteredGroups?.size).isEqualTo(1)
    }

    /**
     * Get products inventory.
     */
    @Test
    fun testGetProductsInventory() {
        val params = "q[product_tags_name_cont]=jvt"
        val productsResponse = restTemplate?.getForObject("$endpoint/inventory?$params", ProductsResponse::class.java)

        assertThat(productsResponse).isNotNull
        assertThat(productsResponse?.code).isEqualTo(HttpStatus.OK.value())
        assertThat(productsResponse?.data).isNotNull

        val productItems = productsResponse?.data
        assertThat(productItems).isNotEmpty

        productItems?.forEach {
            assertThat(it.name).contains(testProduct.name)
        }
    }

    /**
     * Get products inventory that is not found.
     */
    @Test
    fun testGetProductsInventoryNotFound() {
        val params = "q[product_tags_name_cont]=${testProduct.name}"
        val productsErrorResponse = restTemplate?.getForObject("$endpoint/inventory?$params", ErrorResponse::class.java)

        assertThat(productsErrorResponse).isNotNull
        assertThat(productsErrorResponse?.code).isEqualTo(HttpStatus.NOT_FOUND.value())
        assertThat(productsErrorResponse?.message).isNotNull

        val errorMessage = productsErrorResponse?.message
        assertThat(errorMessage?.errors).isNotEmpty

        errorMessage?.errors?.forEach {
            assertThat(it.toLowerCase()).contains("not found")
        }
    }

    /**
     * Get products inventory that is not found on the FunctionalIntegrationTest product group.
     */
    @Test
    fun testGetProductsInventoryNotFoundOnTestProductGroup() {
        val params = "q[product_tags_name_cont]=${testProduct.name}"
        val productsErrorResponse = restTemplate?.getForObject("$endpoint/inventory?$params", ErrorResponse::class.java)

        assertThat(productsErrorResponse).isNotNull
        assertThat(productsErrorResponse?.code).isEqualTo(HttpStatus.NOT_FOUND.value())
        assertThat(productsErrorResponse?.message).isNotNull

        val errorMessage = productsErrorResponse?.message
        assertThat(errorMessage?.errors).isNotEmpty

        errorMessage?.errors?.forEach {
            assertThat(it.toLowerCase()).contains("not found")
        }
    }

    /**
     * Check product batch availability.
     */
    @Test
    fun testGetProductBatchAvailability() {
        val batch: MutableList<ProductAvailabilityItem> = mutableListOf()

        val mockedItem1 = ProductAvailabilityItem(247, 1)
        val rentalPeriod1 = RentalPeriod(Date(), Date())
        val rentalLocal1 = RentalLocation(1,2)
        mockedItem1.period = rentalPeriod1
        mockedItem1.location = rentalLocal1
        batch.add(mockedItem1)

        val mockedItem2 = ProductAvailabilityItem(196, 2)
        val rentalPeriod2 = RentalPeriod(Date(), Date())
        val rentalLocal2 = RentalLocation(1,2)
        mockedItem2.period = rentalPeriod2
        mockedItem2.location = rentalLocal2
        batch.add(mockedItem2)

        val mockedItem3 = ProductAvailabilityItem(148, 3)
        val rentalPeriod3 = RentalPeriod(Date(), Date())
        val rentalLocal3 = RentalLocation(1,2)
        mockedItem3.period = rentalPeriod3
        mockedItem3.location = rentalLocal3
        batch.add(mockedItem3)

        val request = HttpEntity<List<ProductAvailabilityItem>>(batch)
        val productsResponse = restTemplate?.postForEntity("$endpoint/availability", request, Any::class.java)

        assertThat(productsResponse).isNotNull
        assertThat(productsResponse?.statusCode).isEqualTo(HttpStatus.CREATED)
    }

    /**
     * Check product batch availability empty array payload.
     */
    @Test
    fun testGetProductBatchAvailabilityEmptyArrayPayload() {
        val batch: MutableList<ProductAvailabilityItem> = mutableListOf()

        val request = HttpEntity<List<ProductAvailabilityItem>>(batch)
        val productsResponse = restTemplate?.postForEntity("$endpoint/availability", request, Any::class.java)

        assertThat(productsResponse).isNotNull
        assertThat(productsResponse?.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
    }


    /**
     * Check product batch availability empty array payload.
     */
    @Test
    fun testGetProductBatchAvailabilityInvalidQuantity() {
        val batch: MutableList<ProductAvailabilityItem> = mutableListOf()

        val request = HttpEntity<List<ProductAvailabilityItem>>(batch)
        val productsResponse = restTemplate?.postForEntity("$endpoint/availability", request, Any::class.java)

        assertThat(productsResponse).isNotNull
        assertThat(productsResponse?.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
    }
}

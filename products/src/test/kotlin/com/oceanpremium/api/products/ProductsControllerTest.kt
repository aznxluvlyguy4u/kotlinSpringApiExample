package com.oceanpremium.api.products

import com.oceanpremium.api.core.currentrms.response.dto.parameter.QueryParametersResolverImpl.Companion.FUNCTIONAL_INTEGRATION_GROUP_NAME
import com.oceanpremium.api.core.currentrms.response.dto.product.ProductDto
import com.oceanpremium.api.core.model.*
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
class ProductResponse(var code: Int? = null, var data: ProductDto? = null)
class ProductsResponse(var code: Int? = null, var data: List<ProductDto>? = null)
class ProductGroupsResponse(var code: Int?, var data: List<ProductGroup>? = null)
class RateItem(var price: String? = null, var quantityAvailable: String? = null, var chargePeriod: String? = null)
class Image(var fullImageUrl: String? = null, var thumbnailUrl: String? = null)
class CustomFields(var publicIconUrl: String? = null, var publicIconThumbUrl: String? = null)
class ProductGroup(var id: Int? = null, var name: String, var description: String?)

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductsControllerTest {

    @Autowired
    val restTemplate: TestRestTemplate? = null

    /**
     * This is a product setup in Current RMS through the FunctionalIntegrationTest product group.
     * These products are filtered out from public results queries results. And are only returned on LOCAL, DEV, STAGING & TEST environments.
     */
    internal class TestProduct(
        val id: Int = 247,
        val name: String = "JVTRubberDuck",
        val group: String = FUNCTIONAL_INTEGRATION_GROUP_NAME
    )

    internal class TestExistingProduct(
        val id: Int = 148,
        val name: String = "Seabob F5",
        val gibraltarLocationId: Int = 1,
        val portVendresLocationId: Int = 29
    )

    companion object {
        private const val endpoint = "/api/v1/products"
        private val testProduct = TestProduct()
        private val testExistingProduct = TestExistingProduct()
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
     *
     * Query for products containing jvt term. These products belong to a specific product group:
     *
     * FunctionalIntegrationTest
     *
     * that has been setup in current RMS to setup testing through functional integration testing
     *
     * Products under the group are setup with a product name of the following format:
     *
     * JVTSpecificProductName
     *
     * The Functional Integration Test group is excluded in the result sets for public usage.
     * It is ONLY EXPOSED on the LOCAL & TEST environments.
     */
    @Test
    fun testGetProductsInventory() {
        val params = "q[product_tags_name_cont]=f5"
        val productsResponse = restTemplate?.getForObject("$endpoint/inventory?$params", ProductsResponse::class.java)

        assertThat(productsResponse).isNotNull
        assertThat(productsResponse?.code).isEqualTo(HttpStatus.OK.value())
        assertThat(productsResponse?.data).isNotNull

        val productItems = productsResponse?.data
        assertThat(productItems).isNotEmpty

        productItems?.forEach {
            assertThat(it.name).containsIgnoringCase("f5")
        }
    }

    /**
     * Get products inventory.
     */
    @Test
    fun testGetProductsInventoryDifferentQuantityAvailableForDifferentLocationId() {
        // First query inventory with delivery_location_id set to gebraltar
        val params = "delivery_location_id=${testExistingProduct.gibraltarLocationId}&q[product_tags_name_cont]=f5"
        val productsResponse = restTemplate?.getForObject("$endpoint/inventory?$params", ProductsResponse::class.java)

        assertThat(productsResponse).isNotNull
        assertThat(productsResponse?.code).isEqualTo(HttpStatus.OK.value())
        assertThat(productsResponse?.data).isNotNull

        val productItems = productsResponse?.data
        assertThat(productItems).isNotEmpty

        val testProduct: ProductDto? = productItems?.find{ p -> p.id == testExistingProduct.id }
        assertThat(testProduct?.rates?.first()?.quantityAvailable).isEqualTo("2.0")

        // Then query inventory with delivery_location_id set to port vendres
        val params2 = "delivery_location_id=${testExistingProduct.portVendresLocationId}&q[product_tags_name_cont]=f5"
        val productsResponse2 = restTemplate?.getForObject("$endpoint/inventory?$params2", ProductsResponse::class.java)

        assertThat(productsResponse2).isNotNull
        assertThat(productsResponse2?.code).isEqualTo(HttpStatus.OK.value())
        assertThat(productsResponse2?.data).isNotNull

        val productItems2 = productsResponse2?.data
        assertThat(productItems2).isNotEmpty

        val testProduct2: ProductDto? = productItems2?.find{ p -> p.id == testExistingProduct.id }
        assertThat(testProduct2?.rates?.first()?.quantityAvailable).isEqualTo("3.0")
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
        val rentalLocal1 = RentalLocation(Location("Foo", 1),Location("Bar", 13))
        mockedItem1.period = rentalPeriod1
        mockedItem1.location = rentalLocal1
        batch.add(mockedItem1)

        val mockedItem2 = ProductAvailabilityItem(196, 2)
        val rentalPeriod2 = RentalPeriod(Date(), Date())
        val rentalLocal2 = RentalLocation(Location("Foo", 1),Location("Bar", 13))
        mockedItem2.period = rentalPeriod2
        mockedItem2.location = rentalLocal2
        batch.add(mockedItem2)

        val mockedItem3 = ProductAvailabilityItem(148, 2)
        val rentalPeriod3 = RentalPeriod(Date(), Date())
        val rentalLocal3 = RentalLocation(Location("Foo", 1),Location("Bar", 13))
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

    /*
     * Get products inventory bad request for start date before end date.
     */
    @Test
    fun testBadRequestStartDateBeforeEndDateGetProductsInventory() {
        val params = "starts_at=2019-09-15&ends_at=2019-09-01&q[product_tags_name_cont]=seabob"
        val productsErrorResponse = restTemplate?.getForObject("$endpoint/inventory?$params", ErrorResponse::class.java)

        assertThat(productsErrorResponse).isNotNull
        assertThat(productsErrorResponse?.code).isEqualTo(HttpStatus.BAD_REQUEST.value())
        assertThat(productsErrorResponse?.message).isNotNull

        val errorMessage = productsErrorResponse?.message
        assertThat(errorMessage?.errors).isNotEmpty

        errorMessage?.errors?.forEach {
            assertThat(it.toLowerCase()).contains("bad request")
        }
    }
}

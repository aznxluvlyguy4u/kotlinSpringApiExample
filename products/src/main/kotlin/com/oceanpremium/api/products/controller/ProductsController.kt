package com.oceanpremium.api.products.controller

import com.oceanpremium.api.core.currentrms.ProductsApiImpl
import com.oceanpremium.api.core.currentrms.response.CurrentRmsApiResponse
import com.oceanpremium.api.core.currentrms.response.dto.mapper.ProductDtoMapper
import com.oceanpremium.api.core.currentrms.response.dto.mapper.ProductGroupDtoMapper
import com.oceanpremium.api.core.currentrms.response.dto.product.ProductDto
import com.oceanpremium.api.core.exception.throwable.BadRequestException
import com.oceanpremium.api.core.messenger.Slogger
import com.oceanpremium.api.core.model.ProductAvailabilityItem
import com.oceanpremium.api.core.model.WrappedResponse
import com.oceanpremium.api.core.util.Constants
import com.oceanpremium.api.core.util.ObjectMapperConfig
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ResourceLoader
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/products")
class ProductsController(
    @Autowired private val resourceLoader: ResourceLoader,
    @Autowired private val productsApi: ProductsApiImpl
) {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
        private val mapper = ObjectMapperConfig.mapper
    }

    @RequestMapping("docs")
    @ResponseBody
    fun getApiDocs(): ResponseEntity<Any?> {
        logger.debug("GET products API docs")

        return ResponseEntity(
            mapper.readTree(resourceLoader.getResource("classpath:${Constants.API_DOC_FILE_SWAGGER}").file),
            HttpStatus.OK
        )
    }

    /**
     * Endpoint to find products based on given query parameters.
     */
    @RequestMapping
    @ResponseBody
    fun getProducts(@RequestHeader headers: HttpHeaders, @RequestParam queryParameters: Map<String, String>): ResponseEntity<*> {
        val logMessage = "[API] - GET products with request parameters: $queryParameters"
        logger.debug(logMessage)

        val logMessageSales = "[Sales analytics] GET products - sales analytics: $queryParameters"
        logger.debug(logMessageSales)
        Slogger.send(messageBody = logMessage, salesLog = true, inDebugMode = true)

        val productResponse = productsApi.getProducts(queryParameters, headers)
        val productDto = ProductDtoMapper(productResponse?.code()!!, productResponse)

        return CurrentRmsApiResponse.build {
            rawResponse = productResponse
            dtoMapper = productDto
        }
    }

    /**
     * Endpoint to get a product based on given id.
     */
    @RequestMapping("/{productId}")
    @ResponseBody
    fun getProductById(@PathVariable productId:Int): ResponseEntity<*>  {
        val logMessage = "[API] - GET products with request parameters: $productId"
        logger.debug(logMessage)

        val productResponse = productsApi.getProductById(productId)
        val productDto = ProductDtoMapper(productResponse?.code()!!, productResponse)
        val productData = productDto.data as ProductDto?
        val accessoryDtos: MutableList<ProductDto> = mutableListOf()

        productData?.accesoryIds?.forEach {
            val accessoryResponse = productsApi.getProductById(it.id)
            val accessoryDto = ProductDtoMapper(accessoryResponse?.code()!!, accessoryResponse).data as ProductDto
            accessoryDto.type = it.type

            accessoryDtos.add(accessoryDto)
            logger.debug("Retrieved accessory for product with id: ${productData.id}: - $accessoryDto")
        }

        productData?.accessories = accessoryDtos

        return CurrentRmsApiResponse.build {
            rawResponse = productResponse
            dtoMapper = productDto
            error = productDto.error
        }
    }

    /**
     * Endpoint to get the available product groups.
     */
    @RequestMapping("groups")
    @ResponseBody
    fun getProductGroups(@RequestHeader headers: HttpHeaders, @RequestParam queryParameters: Map<String, String>): ResponseEntity<*> {
        val logMessage = "[API] - GET products groups"
        logger.debug(logMessage)

        val productGroupsResponse = productsApi.getProductGroups(queryParameters, headers)
        val productGroupsDto = ProductGroupDtoMapper(productGroupsResponse?.code()!!, productGroupsResponse)

        return CurrentRmsApiResponse.build {
            rawResponse = productGroupsResponse
            dtoMapper = productGroupsDto
            error = productGroupsDto.error
        }
    }

    /**
     * Endpoint to get the inventory of products.
     */
    @RequestMapping("inventory")
    @ResponseBody
    fun getProductsInventory(@RequestHeader headers: HttpHeaders, @RequestParam queryParameters: MutableMap<String, String>): ResponseEntity<*> {
        val logMessage = "[API] - GET products inventories with request headers: $headers, parameters: $queryParameters"
        logger.debug(logMessage)

        val logMessageSales = "[Sales analytics] GET products inventories - sales analytics: $queryParameters"
        logger.debug(logMessageSales)
        Slogger.send(messageBody = logMessage, salesLog = true, inDebugMode = true)

        val productsResponse = productsApi.getProductsInventory(queryParameters, headers)
        val productsDto = ProductDtoMapper(productsResponse?.code()!!, productsResponse)

        return CurrentRmsApiResponse.build {
            rawResponse = productsResponse
            dtoMapper = productsDto
            error = productsDto.error
        }
    }

    /**
     * Endpoint to get the availability of a batch of selected products.
     *
     *
     */
    @RequestMapping("availability")
    @ResponseBody
    fun getProductBatchAvailability(@RequestBody productItems: List<ProductAvailabilityItem>?): ResponseEntity<*> {
        val logMessage = "[API] - GET products availability for batch $productItems"
        logger.debug(logMessage)

        if (productItems == null) {
            throw BadRequestException("Payload may not be empty")
        }

        if (productItems.isEmpty()) {
            throw BadRequestException("Payload may not contain empty array")
        }

        productItems.forEach {
            logger.debug("check availability for product with id: ${it.id} on location collection: ${it.location?.collectionId} - dropOff: ${it.location?.dropOffId} in period: ${it.period?.start} - ${it.period?.end}")
        }

        return ResponseEntity(
            WrappedResponse(HttpStatus.OK.value(), productItems),
            HttpStatus.OK
        )
    }
}




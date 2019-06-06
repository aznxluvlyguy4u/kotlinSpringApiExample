package com.oceanpremium.api.products.controller

import com.oceanpremium.api.core.currentrms.ProductsApiImpl
import com.oceanpremium.api.core.currentrms.response.CurrentRmsApiResponse
import com.oceanpremium.api.core.currentrms.response.dto.mapper.ProductDtoMapper
import com.oceanpremium.api.core.currentrms.response.dto.mapper.ProductGroupDtoMapper
import com.oceanpremium.api.core.currentrms.response.dto.config.ConfigProperty
import com.oceanpremium.api.core.currentrms.response.dto.config.ProductConfigOptionsResolverImpl
import com.oceanpremium.api.core.currentrms.response.dto.mapper.ProductConfigsDtoMapper
import com.oceanpremium.api.core.currentrms.response.dto.product.ProductDto
import com.oceanpremium.api.core.messenger.Slogger
import com.oceanpremium.api.core.model.ProductAvailabilityItem
import com.oceanpremium.api.core.model.WrappedResponse
import com.oceanpremium.api.core.usecase.CheckProductBatchAvailability
import com.oceanpremium.api.core.usecase.GetProductInventoryUseCase
import com.oceanpremium.api.core.util.Constants
import com.oceanpremium.api.core.util.ObjectMapperConfig
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ResourceLoader
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("api/v1/products")
class ProductsController(
    @Autowired private val resourceLoader: ResourceLoader,
    @Autowired private val productsApi: ProductsApiImpl,
    @Autowired private val getProductInventoryUseCase: GetProductInventoryUseCase,
    @Autowired private val checkProductBatchAvailability: CheckProductBatchAvailability
) {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
        private val mapper = ObjectMapperConfig.mapper
        private const val REQUEST_FORWARD_HEADER = "X-FORWARDED-FOR"
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
    fun getProductById(@PathVariable productId: Int): ResponseEntity<*> {
        val logMessage = "[API] - GET products with request parameters: $productId"
        logger.debug(logMessage)

        // Get all product configurations options
        val allConfigOptionsResponse = productsApi.getProductConfigOptions()
        val allConfigOptionsDto = ProductConfigsDtoMapper(allConfigOptionsResponse?.code()!!, allConfigOptionsResponse)

        // Process product
        val productResponse = productsApi.getProductById(productId)
        val productDto = ProductDtoMapper(productResponse?.code()!!, productResponse)
        val productData = productDto.data as ProductDto?

        // Resolve the product specific configurations
        @Suppress("UNCHECKED_CAST")
        val resolvedProductConfigurationOptions = ProductConfigOptionsResolverImpl(
            allConfigOptionsDto.data as List<ConfigProperty>,
            productData
        )
        productData?.configurations = resolvedProductConfigurationOptions.data

        // Process product accessories
        val accessoryDtos: MutableList<ProductDto> = mutableListOf()
        productData?.accesoryIds?.forEach { accessoryItem ->
            val accessoryResponse = productsApi.getProductById(accessoryItem.id)
            val accessoryDto = ProductDtoMapper(accessoryResponse?.code()!!, accessoryResponse)
            val accessoryData = accessoryDto.data as ProductDto?

            if (accessoryItem.quantity?.toDouble() != null && accessoryItem.quantity!!.toDouble() > 0.0) {
                accessoryData?.type = accessoryItem.type
                accessoryData?.rates?.forEach { rates ->
                    rates.quantityAvailable = accessoryItem.quantity
                }
                accessoryDtos.add(accessoryData!!)

                logger.debug("Retrieved accessory for product with id: ${productData.id}: - $accessoryDto")
            }

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
    fun getProductsInventory(
        @RequestHeader headers: HttpHeaders,
        @RequestParam queryParameters: MutableMap<String, String>,
        request: HttpServletRequest
    ): ResponseEntity<*> {
        val logMessage = "[API] - GET products inventories with request headers: $headers, parameters: $queryParameters"
        logger.debug(logMessage)

        val logMessageSales = "[Sales analytics] GET products inventories by IP: " +
                "${request.getHeader(REQUEST_FORWARD_HEADER)} - sales analytics: $queryParameters"
        logger.debug(logMessageSales)
        Slogger.send(messageBody = logMessage, salesLog = true, inDebugMode = true)

        val result =  getProductInventoryUseCase.execute(queryParameters, headers)

        return CurrentRmsApiResponse.build {
            rawResponse = result.getRawResponse()
            dtoMapper = result.dtoMapper
        }
    }

    /**
     * Endpoint to get the availability of a batch of selected products.
     *
     *
     */
    @RequestMapping("availability")
    @ResponseBody
    fun getProductBatchAvailability(@RequestBody productItems: List<ProductAvailabilityItem>): ResponseEntity<*> {
        val logMessage = "[API] - GET products availability for batch $productItems"
        logger.debug(logMessage)

        val result= checkProductBatchAvailability.execute(productItems)

        return ResponseEntity(WrappedResponse(HttpStatus.CREATED.value(), result), HttpStatus.CREATED)
    }
}




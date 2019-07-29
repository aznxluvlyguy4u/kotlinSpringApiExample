package com.oceanpremium.api.products.controller

import com.oceanpremium.api.core.currentrms.ProductsApiImpl
import com.oceanpremium.api.core.currentrms.response.CurrentRmsApiResponse
import com.oceanpremium.api.core.currentrms.response.dto.mapper.ProductDtoMapper
import com.oceanpremium.api.core.currentrms.response.dto.mapper.ProductGroupDtoMapper
import com.oceanpremium.api.core.messenger.Slogger
import com.oceanpremium.api.core.model.OrderDto
import com.oceanpremium.api.core.model.ProductAvailabilityItemDto
import com.oceanpremium.api.core.model.WrappedResponse
import com.oceanpremium.api.core.usecase.CheckProductBatchAvailabilityUseCase
import com.oceanpremium.api.core.usecase.GetProductInventoryUseCase
import com.oceanpremium.api.core.usecase.OrderPlacementUseCase
import com.oceanpremium.api.core.util.Constants
import com.oceanpremium.api.core.util.ObjectMapperConfig
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ResourceLoader
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("api/v1/products")
class ProductsController(
    @Autowired private val resourceLoader: ResourceLoader,
    @Autowired private val productsApi: ProductsApiImpl,
    @Autowired private val getProductInventoryUseCase: GetProductInventoryUseCase,
    @Autowired private val checkProductBatchAvailabilityUseCase: CheckProductBatchAvailabilityUseCase,
    @Autowired private val orderPlacementUseCase: OrderPlacementUseCase
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

        /*
        Currently product configuration options are not used

        Get all product configurations options
        val allConfigOptionsResponse = productsApi.getProductConfigOptions()
        val allConfigOptionsDto = ProductConfigsDtoMapper(allConfigOptionsResponse?.code()!!, allConfigOptionsResponse)
        val productData = productDto.data as ProductDto?
        // Resolve the product specific configurations
        val resolvedProductConfigurationOptions = ProductConfigOptionsResolverImpl(allConfigOptionsDto.data as List<ConfigProperty>,productData)
        productData?.configurations = resolvedProductConfigurationOptions.data
        */
        val productResponse = productsApi.getProductById(productId)
        val productDto = ProductDtoMapper(productResponse?.code()!!, productResponse)

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

        val originIp =  when {
            request.getHeader(REQUEST_FORWARD_HEADER) != null -> {
                request.getHeader(REQUEST_FORWARD_HEADER)
            }
            else -> {
                request.remoteAddr
            }
        }

        val queryMap = queryParameters.toSortedMap()
        queryMap.remove("per_page")
        queryMap.remove("page")

        if (queryMap.containsKey("q[product_tags_name_cont]")) {
            var searchQuery = "_Search Parameters:_ \n"
            searchQuery += "\n```"
            queryMap.forEach { (k, v) ->
                searchQuery += "$k=$v\n"
            }

            val keyword = queryMap["q[product_tags_name_cont]"]
            searchQuery += "```\n_Search Words (Clickable links):_ \n```\n<http://rental.oceanpremium.com/search?keyword=$keyword|$keyword>\n```"

            val logMessageSales = "*OP - Sales Analytics* \n_Request Date:_ `${LocalDateTime.now()}`" +
                    "\n\n_Origin:_\n\n```$originIp```\n$searchQuery"

            logger.debug(logMessageSales)
            Slogger.send(messageBody = logMessageSales, salesLog = true, inDebugMode = true)
        }

        val result= getProductInventoryUseCase.execute(queryParameters, headers)

        return CurrentRmsApiResponse.build {
            rawResponse = result.response
            dtoMapper = result.dtoMapper
        }
    }

    /**
     * Endpoint to get the availability of a batch of selected products.
     */
    @RequestMapping("availability")
    @ResponseBody
    fun checkProductBatchAvailability(@RequestBody productItems: List<ProductAvailabilityItemDto>): ResponseEntity<*> {
        val logMessage = "[API] - GET products availability for batch $productItems"
        logger.debug(logMessage)

        val result= checkProductBatchAvailabilityUseCase.execute(productItems)

        return ResponseEntity(WrappedResponse(HttpStatus.CREATED.value(), result), HttpStatus.CREATED)
    }

    /**
     * Endpoint to get place order of selected products.
     */
    @RequestMapping("orders")
    @ResponseBody
    fun createOrderPlacement(@RequestBody order: OrderDto): ResponseEntity<*> {
        val logMessage = "[API] - POST order: $order"
        logger.debug(logMessage)

        val result= orderPlacementUseCase.execute(order)

        return ResponseEntity(WrappedResponse(HttpStatus.CREATED.value(), result), HttpStatus.CREATED)
    }
}




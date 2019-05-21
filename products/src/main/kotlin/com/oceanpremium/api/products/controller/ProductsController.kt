package com.oceanpremium.api.products.controller

import com.oceanpremium.api.core.currentrms.ProductsApiImpl
import com.oceanpremium.api.core.currentrms.response.CurrentRmsApiResponse
import com.oceanpremium.api.core.currentrms.response.dto.mapper.CurrentRmsBaseDtoMapper
import com.oceanpremium.api.core.currentrms.response.dto.mapper.ProductDtoMapper
import com.oceanpremium.api.core.currentrms.response.dto.mapper.ProductGroupDtoMapper
import com.oceanpremium.api.core.messenger.Slogger
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

        val response = productsApi.getProducts(queryParameters, headers)
        val dto = ProductDtoMapper(response?.code()!!, response)

        return CurrentRmsApiResponse.build {
            rawResponse = response
            dtoMapper = dto
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

        val response = productsApi.getProductById(productId)
        val dto = ProductDtoMapper(response?.code()!!, response)

        return CurrentRmsApiResponse.build {
            rawResponse = response
            dtoMapper = dto
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

        val response = productsApi.getProductGroups(queryParameters, headers)
        val dto = ProductGroupDtoMapper(response?.code()!!, response)

        return CurrentRmsApiResponse.build {
            rawResponse = response
            dtoMapper = dto
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

        val responses = productsApi.getProductsInventory(queryParameters, headers)

        val dtos: MutableList<CurrentRmsBaseDtoMapper> = mutableListOf()

        responses?.forEach {
            dtos.add(ProductDtoMapper(it.code(), it))
        }

        return CurrentRmsApiResponse.build {
            rawResponse = responses?.first()
            dtoMapper = dtos.first()
        }
    }
}

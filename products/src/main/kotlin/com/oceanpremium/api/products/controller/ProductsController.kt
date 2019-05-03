package com.oceanpremium.api.products.controller

import com.oceanpremium.api.core.currentrms.ProductsApiImpl
import com.oceanpremium.api.core.util.Constants
import com.oceanpremium.api.core.util.ObjectMapperConfig
import com.oceanpremium.api.currentrms.response.CurrentRmsApiResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ResourceLoader
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/products")
class ProductsController(
    @Autowired private val resourceLoader: ResourceLoader,
    @Autowired private val productsApi: ProductsApiImpl) {

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
    fun getProducts(@RequestParam fields: Map<String, String>): ResponseEntity<*> {
        val logMessage = "[API] - GET products with request parameters: $fields"
        logger.debug(logMessage)

        val logMessageSales = "[Sales analytics] GET products - sales analytics: $fields"
        logger.debug(logMessageSales)
//         Slogger.send(messageBody = logMessage, salesAnalyticsLog = true)

        val response = productsApi.getProducts(fields)

        return CurrentRmsApiResponse.build {
            statusCode = HttpStatus.valueOf(response?.code()!!)
            objectBody = response.body()
        }
    }

    /**
     * Endpoint to get a product based on given id.
     */
    @RequestMapping("/{productId}")
    @ResponseBody
    fun getProductById(@PathVariable productId:Int): ResponseEntity<*> {
        val logMessage = "[API] - GET products with request parameters: $productId"
        logger.debug(logMessage)

        val response = productsApi.getProductById(productId)

        return CurrentRmsApiResponse.build {
            statusCode = HttpStatus.valueOf(response?.code()!!)
            objectBody = response.body()
        }
    }

    /**
     * Endpoint to get the available product groups.
     */
    @RequestMapping("groups")
    @ResponseBody
    fun getProductGroups(@RequestParam fields: Map<String, String>): ResponseEntity<*> {
        val logMessage = "[API] - GET products groups"
        logger.debug(logMessage)

        val response = productsApi.getProductGroups(fields)

        return CurrentRmsApiResponse.build {
            statusCode = HttpStatus.valueOf(response?.code()!!)
            objectBody = response.body()
        }
    }
}
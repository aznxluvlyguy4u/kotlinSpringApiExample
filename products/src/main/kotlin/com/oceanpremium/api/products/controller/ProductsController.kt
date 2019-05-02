package com.oceanpremium.api.products.controller

import com.oceanpremium.api.core.util.Constants
import com.oceanpremium.api.core.util.ObjectMapperConfig
import com.oceanpremium.api.currentrms.CurrentRmsApiResponse
import com.oceanpremium.api.currentrms.endpoint.ProductsApiImpl
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ResourceLoader
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/products")
class ProductsController(@Autowired private val resourceLoader: ResourceLoader,
                         @Autowired private val productsApi: ProductsApiImpl) {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
        private val mapper = ObjectMapperConfig.mapper
    }

    @RequestMapping("docs")
    @ResponseBody
    fun getApiDocs(): ResponseEntity<Any?> {
        logger.debug("GET products api docs")

        return ResponseEntity(
            mapper.readTree(resourceLoader.getResource("classpath:${Constants.API_DOC_FILE_SWAGGER}").file),
            HttpStatus.OK
        )
    }

    /**
     * Endpoint to get products based on given input parameters.
     */
    @RequestMapping
    @ResponseBody
    fun getProducts(@RequestParam fields: Map<String, String>): ResponseEntity<*> {
        val logMessage = "[API] - GET products with request parameters: $fields"
        logger.debug(logMessage)

        // val logMessage ="[Sales analytics] GET products - sales analytics: $response"
        // logger.debug(logMessage)
        // Slogger.send(messageBody = logMessage, salesAnalyticsLog = true)

//        val response = "mocked response: input params: $fields"

        val response = productsApi.getProducts(fields)

        return CurrentRmsApiResponse.build {
            statusCode = HttpStatus.valueOf(response?.code()!!)
            objectBody = response.body()
        }
    }
}
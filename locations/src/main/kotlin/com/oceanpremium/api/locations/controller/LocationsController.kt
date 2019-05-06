package com.oceanpremium.api.locations.controller

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
@RequestMapping("api/v1/locations")
class LocationsController(
    @Autowired private val resourceLoader: ResourceLoader) {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
        private val mapper = ObjectMapperConfig.mapper
    }

    @RequestMapping("docs")
    @ResponseBody
    fun getApiDocs(): ResponseEntity<Any?> {
        logger.debug("GET locations API docs")

        return ResponseEntity(
            mapper.readTree(resourceLoader.getResource("classpath:${Constants.API_DOC_FILE_SWAGGER}").file),
            HttpStatus.OK
        )
    }

    /**
     * Endpoint to retrieve locations know to the platform.
     */
    @RequestMapping
    @ResponseBody
    fun getLocations(@RequestParam fields: Map<String, String>): ResponseEntity<*> {
        val logMessage = "[API] - GET locations: $fields"
        logger.debug(logMessage)


        return CurrentRmsApiResponse.build {
            statusCode = HttpStatus.OK
            objectBody = null
        }
    }
}
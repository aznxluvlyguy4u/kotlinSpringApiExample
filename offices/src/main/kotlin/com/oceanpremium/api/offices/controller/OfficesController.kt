package com.oceanpremium.api.offices.controller

import com.oceanpremium.api.core.model.Enquiry
import com.oceanpremium.api.core.model.Office
import com.oceanpremium.api.core.model.WrappedResponse
import com.oceanpremium.api.core.util.Constants
import com.oceanpremium.api.core.util.ObjectMapperConfig
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ResourceLoader
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/offices")
class OfficesController(
    @Autowired private val resourceLoader: ResourceLoader
) {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
        private val mapper = ObjectMapperConfig.mapper
    }

    @RequestMapping("docs")
    @ResponseBody
    fun getApiDocs(): ResponseEntity<Any?> {
        logger.debug("GET offices API docs")

        return ResponseEntity(
            mapper.readTree(resourceLoader.getResource("classpath:${Constants.API_DOC_FILE_SWAGGER}").file),
            HttpStatus.OK
        )
    }

    /**
     * Endpoint to get offices to send enquiries to.
     */
    @RequestMapping
    @ResponseBody
    fun getOffices(): ResponseEntity<*> {
        val mockedOffice = Office(1, "Test office", "testoffice1@example.com")

        return ResponseEntity(listOf(mockedOffice), HttpStatus.OK)
    }

    /**
     * Endpoint to create an enquiry to a given office.
     */
    @RequestMapping("/{officeId}/enquiries")
    @ResponseBody
    fun createEnquiryToOffice(@PathVariable officeId: Int, @RequestBody enquiry: Enquiry): ResponseEntity<*> {
        val logMessage = "[API] - POST office:$officeId/enquiries: ${enquiry.message}"
        logger.debug(logMessage)

        return ResponseEntity(WrappedResponse(HttpStatus.CREATED.value(), enquiry), HttpStatus.CREATED)
    }
}




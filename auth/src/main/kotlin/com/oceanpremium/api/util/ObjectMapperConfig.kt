package com.oceanpremium.api.util

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.slf4j.LoggerFactory

class ObjectMapperConfig {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)

        // Setup an object mapper and provision it with additional models, such as: Kotlin and LocalDate
        val mapper = jacksonObjectMapper()
            .registerModule(KotlinModule())
            .registerModule(JavaTimeModule())
            /**
             * See for more info:
             *
             * @link: https://stackoverflow.com/questions/28802544/java-8-localdate-jackson-format
             **/
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)

        /**
         *
         */
        @Throws(Exception::class)
        fun <T> mapToObject(request: Map<String, Any>, classType: Class<T>): Any {
            // Parse the input body to a map
            try {
                // Grab the body content of the input
                val root = request["body"] as String
                return mapper.readValue(root, classType) as Any
            } catch (e: Exception) {
                logger.error("Failed to parse incoming request body to $classType: ${e.localizedMessage}")
                e.printStackTrace()
            }

            throw Exception("Could not create $classType object instantiation")
        }
    }
}
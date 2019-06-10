package com.oceanpremium.api.core.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.slf4j.LoggerFactory

class ObjectMapperConfig {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)

        // Setup an object mapper and provision it with additional modules, such as: Kotlin and LocalDate
        val mapper: ObjectMapper = jacksonObjectMapper()
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
        @Suppress("unused")
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

        // Convert a data class to a map
        fun serializeToMap(obj: Any): Map<String, Any> {
            return convert(obj)
        }

        // Convert a map to a data class
        fun <T> Map<String, Any>.toDataClass(obj: Any): T {
            return convert(obj)
        }

        // Convert an object of type I to type O
        private fun <I, O> I.convert(obj: Any): O {
            val gson = Gson()
            val json = gson.toJson(obj)
            return gson.fromJson(json, object : TypeToken<O>() {}.type)
        }
    }
}

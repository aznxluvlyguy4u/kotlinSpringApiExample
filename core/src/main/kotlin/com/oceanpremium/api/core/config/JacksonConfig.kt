package com.oceanpremium.api.core.config

import com.fasterxml.jackson.core.*
import org.springframework.context.annotation.Configuration
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean
import com.fasterxml.jackson.databind.*
import java.io.IOException
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.oceanpremium.api.core.util.DateTimeUtil
import org.joda.time.DateTime

@Configuration
class JacksonConfig {

    /**
     *   // Setup an object mapper and provision it with additional modules, such as: Kotlin and LocalDate
    val mapper: ObjectMapper = jacksonObjectMapper()
    .registerModule(KotlinModule())
    .registerModule(JavaTimeModule())

    /**
     * See for more info:
     *
     * @link: https://stackoverflow.com/questions/28802544/java-8-localdate-jackson-format
    **/
    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
     */
    @Bean
    fun objectMapper(): ObjectMapper {
        return ObjectMapper()
            .registerModule(KotlinModule())
            .registerModule(JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true)
    }
}


class CustomDateSerializer : JsonSerializer<DateTime>() {
    @Throws(IOException::class, JsonProcessingException::class)
    override fun serialize(value: DateTime?, gen: JsonGenerator, serializers: SerializerProvider?) {
        gen.writeString(DateTimeUtil.defaultDateFormatter.print(value))
    }
}

class LocalDateDeserializer : StdDeserializer<DateTime>(DateTime::class.java) {
    @Throws(IOException::class, JsonProcessingException::class)
    override fun deserialize(jp: JsonParser, ctxt: DeserializationContext): DateTime {
        return DateTime.parse(jp.readValueAs(String::class.java), DateTimeUtil.defaultDateFormatter)
    }
}

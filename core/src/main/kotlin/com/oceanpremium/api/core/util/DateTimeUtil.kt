package com.oceanpremium.api.core.util

import java.text.ParseException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateTimeUtil {

    private const val DEFAULT_API_DATE_ISO8601_FORMAT = "yyyy-MM-dd'T'HH:mm'Z'"
    const val DEFAULT_API_DATE_FORMAT = "yyyy-MM-dd"

    fun toISO8601UTC(date: LocalDateTime, format: String = DEFAULT_API_DATE_ISO8601_FORMAT): String? {
        try {
            return date.format(DateTimeFormatter.ofPattern(format))
        } catch (e: ParseException) {
            e.printStackTrace()

        }

        return null
    }

    fun fromISO8601UTC(dateStr: String, format: String = DEFAULT_API_DATE_ISO8601_FORMAT): LocalDateTime? {
        try {
            return LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern(format))
        } catch (e: ParseException) {
            e.printStackTrace()

        }

        return null
    }
}

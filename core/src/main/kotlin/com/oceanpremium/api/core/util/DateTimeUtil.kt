package com.oceanpremium.api.core.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

object DateTimeUtil {

    private const val UTC_TIMEZONE = "UTC"
    private const val DEFAULT_API_DATE_ISO8601_FORMAT_ = "yyyy-MM-dd'T'HH:mm'Z'"
    const val DEFAULT_API_DATE_FORMAT = "yyyy-MM-dd"
    private val formatter = DateTimeFormatter.ofPattern(DEFAULT_API_DATE_ISO8601_FORMAT_)


    fun toISO8601UTC(date: LocalDateTime, format: String = DEFAULT_API_DATE_ISO8601_FORMAT_): String? {
        try {
            return date.format(formatter)
        } catch (e: ParseException) {
            e.printStackTrace()

        }

        return null
    }

    fun fromISO8601UTC(dateStr: String, format: String = DEFAULT_API_DATE_ISO8601_FORMAT_): LocalDateTime? {
        try {
            return LocalDateTime.parse(dateStr, formatter)
        } catch (e: ParseException) {
            e.printStackTrace()

        }

        return null
    }
}

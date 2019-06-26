package com.oceanpremium.api.core.util

import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormat
import java.text.ParseException
import java.time.format.DateTimeFormatter
import org.joda.time.format.ISODateTimeFormat



object DateTimeUtil {

    private const val DEFAULT_API_DATE_ISO8601_FORMAT = "yyyy-MM-dd'T'HH:mm'Z'" //2008-09-15T15:53:00+05:00
    const val DEFAULT_API_DATE_FORMAT = "yyyy-MM-dd"

    fun toISO8601UTC(date: LocalDateTime, format: String = DEFAULT_API_DATE_ISO8601_FORMAT): String? {
        try {
            val fmt = ISODateTimeFormat.dateTime()
            return date.toString(fmt)
        } catch (e: ParseException) {
            e.printStackTrace()

        }

        return null
    }

    fun fromISO8601UTC(dateStr: String, format: String = DEFAULT_API_DATE_ISO8601_FORMAT): LocalDateTime? {
        try {
            return LocalDateTime(dateStr)
        } catch (e: ParseException) {
            e.printStackTrace()

        }

        return null
    }
}

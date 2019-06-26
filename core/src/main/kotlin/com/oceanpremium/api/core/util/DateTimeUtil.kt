package com.oceanpremium.api.core.util

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import java.lang.Exception

object DateTimeUtil {

    private const val DEFAULT_API_DATE_ISO8601_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.sssZ" //2019-06-20T12:00:00.000+0000
    const val CURRENT_RMS_API_DATE_ISO8601_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.sss'Z'" //2019-06-20T12:00:00.000+0000
    val defaultDateFormatter = DateTimeFormat.forPattern(DEFAULT_API_DATE_ISO8601_FORMAT).withOffsetParsed()!!
    val currentRmsDateFormatter = DateTimeFormat.forPattern(CURRENT_RMS_API_DATE_ISO8601_FORMAT).withOffsetParsed()!!

    const val NOON = 12

    fun toISO8601UTC(date: DateTime, format: String? = null): String? {
        try {
            if (format != null) {
                return date.toString(DateTimeFormat.forPattern(format).withOffsetParsed())
            }

            return date.toString(defaultDateFormatter)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    fun fromISO8601UTC(dateStr: String, format: String? = null): DateTime? {
        try {
            if (format != null) {
                return DateTime.parse(dateStr, DateTimeFormat.forPattern(format).withOffsetParsed())
            }

            return DateTime.parse(dateStr, defaultDateFormatter)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }
}

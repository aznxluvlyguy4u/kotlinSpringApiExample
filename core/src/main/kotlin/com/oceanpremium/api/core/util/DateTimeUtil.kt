package com.oceanpremium.api.core.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtil {

    private const val UTC_TIMEZONE = "UTC"
    const val DEFAULT_API_DATE_FORMAT = "yyyy-MM-dd"

    fun toISO8601UTC(date: Date, format: String = "yyyy-MM-dd'T'HH:mm'Z'"): String {
        val tz = TimeZone.getTimeZone(UTC_TIMEZONE)
        val df = SimpleDateFormat(format)
        df.timeZone = tz

        return df.format(date)
    }

    fun fromISO8601UTC(dateStr: String, format: String = "yyyy-MM-dd'T'HH:mm'Z'"): Date? {
        val tz = TimeZone.getTimeZone(UTC_TIMEZONE)
        val df = SimpleDateFormat(format)
        df.timeZone = tz

        try {
            return df.parse(dateStr)
        } catch (e: ParseException) {
            e.printStackTrace()

        }

        return null
    }
}

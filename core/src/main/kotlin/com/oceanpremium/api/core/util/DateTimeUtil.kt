package com.oceanpremium.api.core.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtil {

    private const val UTC_TIMEZONE = "UTC"
    private const val ISO_8601_FORMAT = "yyyy-MM-dd'T'HH:mm'Z'"

    fun toISO8601UTC(date: Date): String {
        val tz = TimeZone.getTimeZone(UTC_TIMEZONE)
        val df = SimpleDateFormat(ISO_8601_FORMAT)
        df.timeZone = tz

        return df.format(date)
    }

    fun fromISO8601UTC(dateStr: String): Date? {
        val tz = TimeZone.getTimeZone(UTC_TIMEZONE)
        val df = SimpleDateFormat(ISO_8601_FORMAT)
        df.timeZone = tz

        try {
            return df.parse(dateStr)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return null
    }
}

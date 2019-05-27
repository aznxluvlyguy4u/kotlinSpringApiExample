package com.oceanpremium.api.core.util

import io.sentry.Sentry
import java.text.DecimalFormat

object FileSizeFormatUtil {

    fun convert(sizeInput: Int): String?  {
        var result: String? = null

        try {
            val size = sizeInput.toLong()
            if (size <= 0) return "0"
            val units = arrayOf("B", "kB", "MB", "GB", "TB")
            val digitGroups = (Math.log10(size.toDouble()) / Math.log10(1024.0)).toInt()
            result = DecimalFormat("#,##0.#").format(size / Math.pow(1024.0, digitGroups.toDouble())) + " " + units[digitGroups]
        } catch (e: Exception) {
            e.printStackTrace()
            Sentry.capture(e)
        }

        return result
    }
}

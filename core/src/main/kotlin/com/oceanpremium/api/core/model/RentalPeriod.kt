package com.oceanpremium.api.core.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.oceanpremium.api.core.config.CustomDateSerializer
import com.oceanpremium.api.core.config.LocalDateDeserializer
import com.oceanpremium.api.core.util.DateTimeUtil
import com.oceanpremium.api.core.util.DateTimeUtil.emailerDateFormat
import org.joda.time.DateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
class RentalPeriod(
    @JsonSerialize(using = CustomDateSerializer::class)
    @JsonDeserialize(using = LocalDateDeserializer::class)
    val start: DateTime,
    @JsonSerialize(using = CustomDateSerializer::class)
    @JsonDeserialize(using = LocalDateDeserializer::class)
    val end: DateTime
) {

    init {
        getDateStr()
    }
    var startDateTime : String? = null
    var endDateTime : String? = null

    private fun getDateStr(){
        startDateTime = DateTimeUtil.toISO8601UTC(start, emailerDateFormat)
        endDateTime = DateTimeUtil.toISO8601UTC(end, emailerDateFormat)
    }

}

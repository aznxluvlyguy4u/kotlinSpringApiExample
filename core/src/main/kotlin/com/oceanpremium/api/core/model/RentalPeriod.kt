package com.oceanpremium.api.core.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import org.joda.time.LocalDateTime
import org.springframework.format.annotation.DateTimeFormat

@JsonInclude(JsonInclude.Include.NON_NULL)
class RentalPeriod(
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "YYYY-MM-dd")
    val start: LocalDateTime,
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "YYYY-MM-dd")
    val end: LocalDateTime)

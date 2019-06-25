package com.oceanpremium.api.core.model

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
class RentalPeriod(val start: LocalDateTime, val end: LocalDateTime)

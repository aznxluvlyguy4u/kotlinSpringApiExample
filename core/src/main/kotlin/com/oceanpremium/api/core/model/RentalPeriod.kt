package com.oceanpremium.api.core.model

import com.fasterxml.jackson.annotation.JsonInclude
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
class RentalPeriod(val start: Date, val end: Date)

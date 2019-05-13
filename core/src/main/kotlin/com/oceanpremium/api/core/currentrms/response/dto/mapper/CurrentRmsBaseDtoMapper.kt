package com.oceanpremium.api.core.currentrms.response.dto.mapper

import org.springframework.http.HttpStatus

abstract class CurrentRmsBaseDtoMapper(code: Int) {
    var httpStatus: HttpStatus = HttpStatus.valueOf(code)
    var data: Any? = null
    var meta: Any? = null
}
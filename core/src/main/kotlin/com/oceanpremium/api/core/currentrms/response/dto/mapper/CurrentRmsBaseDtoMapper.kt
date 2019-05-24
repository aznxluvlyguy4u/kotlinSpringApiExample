package com.oceanpremium.api.core.currentrms.response.dto.mapper

import com.oceanpremium.api.core.currentrms.response.dto.product.MetaDto
import org.springframework.http.HttpStatus

abstract class CurrentRmsBaseDtoMapper(code: Int) {
    var httpStatus: HttpStatus = HttpStatus.valueOf(code)
    var data: Any? = null
    var meta: MetaDto? = null
}

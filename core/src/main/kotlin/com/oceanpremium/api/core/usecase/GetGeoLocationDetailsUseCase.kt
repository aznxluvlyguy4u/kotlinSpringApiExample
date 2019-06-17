package com.oceanpremium.api.core.usecase

import com.oceanpremium.api.core.ipdata.IPDataApiImpl
import org.springframework.beans.factory.annotation.Autowired
import javax.servlet.http.HttpServletRequest

interface GetGeoLocationDetailsUseCase {
    fun execute(request: HttpServletRequest): Any
}

class  GetGeoLocationDetailsUseCaseImpl(@Autowired private val ipDataApi: IPDataApiImpl) : GetGeoLocationDetailsUseCase {

    companion object {
        private const val REQUEST_FORWARD_HEADER = "X-FORWARDED-FOR"
    }

    override fun execute(request: HttpServletRequest): Any {
        var details: Any? = null

        val originIp =  when {
            request.getHeader(REQUEST_FORWARD_HEADER) != null -> {
                request.getHeader(REQUEST_FORWARD_HEADER)
            }
            else -> {
                request.remoteAddr
            }
        }

        details = originIp

//        val response = ipDataApi.getGeoLocation(originIp)
//        val result = response?.body() as Map<*, *>?
//
//        when {
//            result != null && result.isNotEmpty() -> {
//                val ip = result["ip"] as String?
//                val city = result["city"] as String?
//                val country = result["country_name"] as String?
//                val region = result["region"] as String?
//                val latitude = result["latitude"] as Double?
//                val longitude = result["longitude"] as Double?
//                val timeZone = result["time_zone"] as Map<*, *>?
//
//                val map = mapOf(
//                    "ip" to ip,
//                    "city" to city,
//                    "country" to country,
//                    "region" to region,
//                    "latitude" to latitude,
//                    "longitude" to longitude,
//                    "time_zone" to timeZone
//                )
//
//                if(map.isNotEmpty()) {
//                    details = map
//                }
//            }
//        }

        return details!!
    }
}

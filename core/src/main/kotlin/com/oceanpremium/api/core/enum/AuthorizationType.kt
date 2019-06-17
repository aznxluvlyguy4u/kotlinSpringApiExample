package com.oceanpremium.api.core.enum

enum class AuthorizationType(val type: String) {
    API("api"),
    //The Third party is in this case the Current RMS API
    THIRD_PARTY("third-party")
}

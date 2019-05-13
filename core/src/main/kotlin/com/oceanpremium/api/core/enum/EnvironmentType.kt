package com.oceanpremium.api.core.enum

enum class EnvironmentType(val type: String) {
    LOCAL("local"),
    STAGING("stage"),
    DEVELOPMENT("dev"),
    PRODUCTION("prod"),
    TEST("test")
}
package com.oceanpremium.api.core.exception

import com.fasterxml.jackson.annotation.JsonInclude
import com.oceanpremium.api.core.enum.EnvironmentType
import org.slf4j.LoggerFactory

@JsonInclude(JsonInclude.Include.NON_NULL)
class ApiException(override var message: String? = "The request was badly formed") : Exception(message) {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
        private const val ENVIRONMENT = "env"
    }


    init {
        val env = getEnvironment()

        if(env != null) {
            if(EnvironmentType.valueOf(env) == EnvironmentType.PRODUCTION) {
                stackTrace = null
            }
        }


    }

    @Synchronized
    override fun fillInStackTrace(): Throwable {
        return this
    }

    private fun getEnvironment(): String? {
        return System.getenv(ENVIRONMENT) ?: null
    }
}
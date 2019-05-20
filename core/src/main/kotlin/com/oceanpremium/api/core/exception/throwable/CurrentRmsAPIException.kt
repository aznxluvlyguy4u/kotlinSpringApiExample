package com.oceanpremium.api.core.exception.throwable

class CurrentRmsAPIException(override val message: String? = "The request was badly formed") : Exception(message)
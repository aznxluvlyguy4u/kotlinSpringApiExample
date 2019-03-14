package com.oceanpremium.api.gateway

import com.oceanpremium.api.model.User
import com.oceanpremium.api.util.Mockable
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

interface AuthGateway {
    fun retrieveUser(user: User): User?
}

@Component
@Mockable
class AuthGatewayImpl : AuthGateway {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    override fun retrieveUser(user: User): User {
        logger.debug("Called ${this::class.java} with input ($user})")

        return User(user.name, user.emailAddress)
    }
}
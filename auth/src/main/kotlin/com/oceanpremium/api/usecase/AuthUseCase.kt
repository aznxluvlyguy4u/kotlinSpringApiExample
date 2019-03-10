package com.oceanpremium.api.usecase

import com.oceanpremium.api.gateway.AuthGateway
import com.oceanpremium.api.model.Token
import com.oceanpremium.api.model.User
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

interface AuthUseCase {
    fun execute(request: User): Token?
}

@Service
class AuthUseCaseImpl : AuthUseCase {

    @Autowired
    private var authGateway: AuthGateway? = null

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    override fun execute(request: User): Token? {
        logger.debug("Called ${this::class.java} with input ($request})")

        val user = authGateway?.retrieveUser(request)

        return when {
            user?.emailAddress != request.emailAddress -> null
            else -> Token(
                "access-token",
                "refresh-token",
                123213123,
                user?.emailAddress!!
            )
        }
    }
}
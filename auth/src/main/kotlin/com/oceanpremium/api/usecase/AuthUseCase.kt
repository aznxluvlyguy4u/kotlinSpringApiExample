package com.oceanpremium.api.usecase

import com.oceanpremium.api.entity.Token
import com.oceanpremium.api.entity.User
import com.oceanpremium.api.gateway.AuthGateway
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneOffset

interface AuthUseCase {
    fun execute(request: User): Token?
}

@Service
class AuthUseCaseImpl(

    @Autowired
    private var authGateway: AuthGateway) : AuthUseCase {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    override fun execute(request: User): Token? {
        logger.debug("Called ${this::class.java} with input ($request})")

        val user = authGateway.retrieveUser(request)
        val milliseconds = LocalDateTime.now().atZone(ZoneOffset.UTC)?.toInstant()?.toEpochMilli()!!
        return when {
            user?.emailAddress != request.emailAddress -> null
            else -> Token(
                "access-token-test",
                "refresh-token-test",
                milliseconds,
                user?.emailAddress!!
            )
        }
    }
}
package com.oceanpremium.api.auth.usecase

import com.oceanpremium.api.core.model.Token
import com.oceanpremium.api.core.model.User
import com.oceanpremium.api.auth.gateway.AuthGateway
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneOffset

interface CreateAuthUseCase {
    fun execute(request: User): Token?
}

@Service
class CreateAuthUseCaseImpl(@Autowired private var authGateway: AuthGateway) : CreateAuthUseCase {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    override fun execute(request: User): Token? {
        logger.debug("Called ${this::class.java} with input ($request})")

        val user = authGateway.persist(request)
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
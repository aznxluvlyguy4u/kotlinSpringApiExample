package com.oceanpremium.api.auth.usecase

import com.oceanpremium.api.auth.gateway.AuthGateway
import com.oceanpremium.api.core.model.Token
import com.oceanpremium.api.core.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

interface RetrieveAuthUseCase {
    fun execute(request: User): Token?
}

@Service
class RetrieveAuthUseCaseImpl(@Autowired private var authGateway: AuthGateway) : RetrieveAuthUseCase {

    override fun execute(request: User): Token? {
        val milliseconds = LocalDateTime.now().atZone(ZoneOffset.UTC)?.toInstant()?.toEpochMilli()!!
        val user = authGateway.retrieveById(UUID.randomUUID())

        return Token(
            "access-token-test",
            "refresh-token-test",
            milliseconds,
            user?.emailAddress!!
        )
    }
}
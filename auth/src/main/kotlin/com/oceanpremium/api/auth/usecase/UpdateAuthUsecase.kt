package com.oceanpremium.api.auth.usecase

import com.oceanpremium.api.auth.gateway.AuthGateway
import com.oceanpremium.api.core.model.Token
import com.oceanpremium.api.core.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneOffset

interface UpdateAuthUseCase {
    fun execute(request: User): Token?
}

@Service
class UpdateAuthUseCaseImpl(@Autowired private var authGateway: AuthGateway) : UpdateAuthUseCase {

    override fun execute(request: User): Token? {
        val milliseconds = LocalDateTime.now().atZone(ZoneOffset.UTC)?.toInstant()?.toEpochMilli()!!
        val user = authGateway.updateById(request)

        return Token(
            "access-token-test",
            "refresh-token-test",
            milliseconds,
            user?.emailAddress!!
        )
    }
}
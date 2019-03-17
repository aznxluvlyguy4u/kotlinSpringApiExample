package com.oceanpremium.api.auth.usecase

import com.oceanpremium.api.auth.gateway.AuthGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

interface DeleteAuthUseCase {
    fun execute(uuid: UUID): Boolean
}

@Service
class DeleteAuthUseCaseImpl(@Autowired private var authGateway: AuthGateway) : DeleteAuthUseCase {

    override fun execute(uuid: UUID): Boolean {
       return authGateway.deleteById(UUID.randomUUID())
    }

}
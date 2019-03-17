package com.oceanpremium.api.auth.gateway

import com.oceanpremium.api.core.model.User
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.*

interface AuthGateway {
    fun persist(user: User): User?
    fun retrieveById(uuid: UUID): User?
    fun retrieveAll(): List<User>
    fun updateById(user: User): User?
    fun deleteById(uuid: UUID): Boolean
}

@Component
class AuthGatewayImpl : AuthGateway {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    override fun persist(user: User): User? {
        logger.debug("Called ${this::class.java} with input ($user})")

        return User(user.name, user.emailAddress)
    }

    override fun retrieveById(uuid: UUID): User? {
        logger.debug("Called ${this::class.java} with input ($uuid})")

        return User("mockedName", "mocked@EmailAddress.com")
    }

    override fun retrieveAll(): List<User> {
        logger.debug("Called ${this::class.java}")
        val result: MutableList<User> = mutableListOf()
        result.add(User("mockedName", "mocked@EmailAddress.com"))

        return result
    }

    override fun updateById(user: User): User? {
        logger.debug("Called ${this::class.java} with input ($user})")

        return User(user.name, user.emailAddress)
    }

    override fun deleteById(uuid: UUID): Boolean {
        logger.debug("Called ${this::class.java} with input ($uuid})")

        return true
    }
}
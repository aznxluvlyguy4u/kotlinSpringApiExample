package com.oceanpremium.api.auth.controller

import com.oceanpremium.api.auth.usecase.CreateAuthUseCase
import com.oceanpremium.api.auth.usecase.DeleteAuthUseCase
import com.oceanpremium.api.auth.usecase.RetrieveAuthUseCase
import com.oceanpremium.api.auth.usecase.UpdateAuthUseCase
import com.oceanpremium.api.core.model.Response
import com.oceanpremium.api.core.model.User
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.util.*

@RestController
@RequestMapping("api/v1/auth")
class AuthController(@Autowired private val createAuthUseCase: CreateAuthUseCase,
                     @Autowired private val retrieveAuthUser: RetrieveAuthUseCase,
                     @Autowired private val updateAuthUseCase: UpdateAuthUseCase,
                     @Autowired private val deleteAuthUseCase: DeleteAuthUseCase) {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    @PostMapping
    @ResponseBody
    fun createAuth(@RequestBody user: User): Response {
        logger.debug("POST auth")
        val token = createAuthUseCase.execute(user)

        return Response(true, token)
    }

    @GetMapping
    @ResponseBody
    fun getAuths(@RequestParam(value = "date", required = false)
                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) date: LocalDate): Response {
        logger.debug("GET auths")
        val user = retrieveAuthUser.execute(User("foobar", "foobar@example.com"))

        return Response(true, "getAuths with params: $date, result: $user")
    }

    @RequestMapping("/{id}")
    @ResponseBody
    fun getAuthById(@PathVariable("id") id: Int): Response {
        logger.debug("GET authById")

        retrieveAuthUser.execute(User("foobar", "foobar@example.com"))

        return Response(true, "getAuths with id: $id")
    }

    @PutMapping("/{id}")
    @ResponseBody
    fun updateAuthById(@PathVariable("id") id: Int, @RequestBody user: User): Response {
        logger.debug("PUT authById")

        updateAuthUseCase.execute(User("foobar", "foobar@example.com"))

        return Response(true, "updateAuthById: " + id + "Update, " + user.name + "!")
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    fun deleteAuthById(@PathVariable("id") id: Int): Response {
        logger.debug("DELETE authById")

        deleteAuthUseCase.execute(UUID.randomUUID())

        return Response(deleteAuthUseCase.execute(UUID.randomUUID()), "deleteAuthById: $id")
    }
}
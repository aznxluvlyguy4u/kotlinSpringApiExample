package com.oceanpremium.api.presenter

import com.oceanpremium.api.model.*
import com.oceanpremium.api.usecase.AuthUseCase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("api/v1/auth")
class AuthPresenter {

    @Autowired
    private var authUseCase: AuthUseCase? = null

    @PostMapping
    @ResponseBody
    fun createAuth(@RequestBody user: User): Response {
        val token = authUseCase?.execute(user)
        return Response(true, token as Any)
    }

    @GetMapping
    @ResponseBody
    fun getAuths(@RequestParam(name = "date", required = false)
                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) date: LocalDate): Response {
        return Response(true, "getAuths with params: $date")
    }

    @GetMapping("/{id}")
    @ResponseBody
    fun getAuthById(@PathVariable("id") id: Int): Response {
        return Response(true, "getAuthById: $id")
    }

    @PutMapping("/{id}")
    @ResponseBody
    fun updateAuthById(@PathVariable("id") id: Int, @RequestBody user: User): Response {
        return Response(true, "updateAuthById: " + id + "Update, " + user.name + "!")
    }

    @DeleteMapping("{id}")
    @ResponseBody
    fun deleteAuthById(@PathVariable("id") id: Int): Response {
        return Response(true, "deleteAuthById: $id")
    }
}
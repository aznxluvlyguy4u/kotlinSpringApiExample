package com.oceanpremium.api.controller

import com.oceanpremium.api.ApiDriver
import com.oceanpremium.api.model.Response
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [ApiDriver::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthPresenterTest {

    private val endpoint = "/api/v1/auth"

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun testGetAllAuths() {
        val id = "123"
        val result= testRestTemplate.getForEntity("$endpoint/$id", Response::class.java)

        assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)

        val res = result?.body as Response
        assertEquals(res.message, "getAuthById: $id")
    }
}
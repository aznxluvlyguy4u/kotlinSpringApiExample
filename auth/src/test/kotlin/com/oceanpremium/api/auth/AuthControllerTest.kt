package com.oceanpremium.api.auth

import com.oceanpremium.api.core.util.ObjectMapperConfig
import org.springframework.beans.factory.annotation.Autowired
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthControllerTest {

    private val endpoint = "/api/v1/auth"

    private val mapper = ObjectMapperConfig.mapper

    @Autowired
    val restTemplate: TestRestTemplate? = null
}

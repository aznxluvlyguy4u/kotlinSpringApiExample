package com.oceanpremium.api

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

@SpringBootApplication
class ApiDriver : SpringBootServletInitializer()

fun main(args: Array<String>) {
    SpringApplication.run(ApiDriver::class.java, *args)
}

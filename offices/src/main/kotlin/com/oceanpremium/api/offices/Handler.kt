package com.oceanpremium.api.offices

import com.amazonaws.serverless.exceptions.ContainerInitializationException
import com.amazonaws.serverless.proxy.model.AwsProxyRequest
import com.amazonaws.serverless.proxy.model.AwsProxyResponse
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler
import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestStreamHandler
import com.oceanpremium.api.core.config.CorsConfig
import com.oceanpremium.api.core.config.JacksonConfig
import com.oceanpremium.api.core.config.SentryConfig
import com.oceanpremium.api.core.config.ThymeleafConfig
import com.oceanpremium.api.core.exception.handler.GlobalExceptionHandler
import com.oceanpremium.api.core.usecase.*
import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.context.annotation.Import
import org.springframework.web.bind.annotation.RestController
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import kotlin.jvm.*
import kotlin.jvm.java as java1

/**
 * The main application entry point that spins up the API.
 */
@Import(
    GlobalExceptionHandler::class,
    SendEmailUseCaseImpl::class,
    CorsConfig::class,
    SentryConfig::class,
    ThymeleafConfig::class,
    JacksonConfig::class
)
@SpringBootApplication
class OfficesDriver : SpringBootServletInitializer() {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(OfficesDriver::class.java1, *args)
        }
    }
}

/**
 * The AWS server-less main entry point that is called when function is triggered.
 */
@RestController
class Handler : RequestStreamHandler {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java1)
        private var handler: SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse>? = null

        init {
            try {
                handler =
                    SpringBootLambdaContainerHandler.getAwsProxyHandler(OfficesDriver::class.java1)
            } catch (e: ContainerInitializationException) {

                e.printStackTrace()
                logger.error(e.message)

                // Re-throw exception to force a cold start
                throw RuntimeException("Could not initialize Spring Boot application", e)
            }
        }
    }

    @Throws(IOException::class)
    override fun handleRequest(inputStream: InputStream, outputStream: OutputStream, context: Context) {

        try {
            handler!!.proxyStream(inputStream, outputStream, context)
        } catch (e: ContainerInitializationException) {
            e.printStackTrace()
            logger.error(e.message)
        } finally {
            outputStream.close()
        }
    }
}

package com.oceanpremium.api

import com.amazonaws.serverless.exceptions.ContainerInitializationException
import com.amazonaws.serverless.proxy.model.AwsProxyRequest
import com.amazonaws.serverless.proxy.model.AwsProxyResponse
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler
import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestStreamHandler
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.web.bind.annotation.RestController
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

@SpringBootApplication
class ApiDriver : SpringBootServletInitializer()

fun main(args: Array<String>) {
    SpringApplication.run(ApiDriver::class.java, *args)
}

@RestController
class Handler : RequestStreamHandler {

    companion object {
        private var handler: SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse>? = null

        init {
            try {
                handler =
                    SpringBootLambdaContainerHandler.getAwsProxyHandler(ApiDriver::class.java)
            } catch (e: ContainerInitializationException) {
                // if we fail here. We re-throw the exception to force another cold start
                e.printStackTrace()
                throw RuntimeException("Could not initialize Spring Boot application", e)
            }
        }
    }

    @Throws(IOException::class)
    override fun handleRequest(inputStream: InputStream, outputStream: OutputStream, context: Context) {
        handler!!.proxyStream(inputStream, outputStream, context)

        // just in case it wasn't closed by the mapper
        outputStream.close()
    }
}

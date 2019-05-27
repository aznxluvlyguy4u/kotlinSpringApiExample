package com.oceanpremium.api.products

import com.amazonaws.serverless.exceptions.ContainerInitializationException
import com.amazonaws.serverless.proxy.model.AwsProxyRequest
import com.amazonaws.serverless.proxy.model.AwsProxyResponse
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler
import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestStreamHandler
import com.oceanpremium.api.core.currentrms.ProductsApiImpl
import com.oceanpremium.api.core.currentrms.builder.LocationBuilderImpl
import com.oceanpremium.api.core.currentrms.builder.RegionBuilderImpl
import com.oceanpremium.api.core.currentrms.builder.StoreBuilderImpl
import com.oceanpremium.api.core.currentrms.response.dto.parameter.LocationStoreResolverImpl
import com.oceanpremium.api.core.currentrms.response.dto.config.ProductConfigOptionsResolverImpl
import com.oceanpremium.api.core.exception.handler.GlobalExceptionHandler
import io.sentry.spring.SentryServletContextInitializer
import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.boot.web.servlet.ServletContextInitializer
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.springframework.core.Ordered
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import org.springframework.web.servlet.HandlerExceptionResolver
import kotlin.jvm.*
import kotlin.jvm.java as java1

/**
 * The main application entry point that spins up the API.
 */
@SpringBootApplication
@Import(ProductsApiImpl::class, LocationStoreResolverImpl::class, LocationBuilderImpl::class, RegionBuilderImpl::class, StoreBuilderImpl::class, ProductConfigOptionsResolverImpl::class, GlobalExceptionHandler::class)
class ProductsDriver : SpringBootServletInitializer() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(ProductsDriver::class.java1, *args)
        }
    }

    @Bean
    fun sentryExceptionResolver(): HandlerExceptionResolver {
        return io.sentry.spring.SentryExceptionResolver()
    }

    /**
     * This should only be necessary in Spring Boot applications. "Classic" Spring
     * should automatically load the `io.sentry.servlet.SentryServletContainerInitializer`.
     */
    @Bean
    fun sentryServletContextInitializer(): ServletContextInitializer {
        return SentryServletContextInitializer()
    }

    @Bean
    fun simpleCorsFilter(): FilterRegistrationBean<CorsFilter> {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()
        config.allowCredentials = true
        config.allowedOrigins = listOf("*")
        config.allowedMethods = listOf("POST", "GET", "PUT", "DELETE")
        config.allowedHeaders = listOf("*")
        config.maxAge = 3600

        source.registerCorsConfiguration("/**", config)

        val bean = FilterRegistrationBean(CorsFilter(source))
        bean.order = Ordered.HIGHEST_PRECEDENCE

        return bean
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
                    SpringBootLambdaContainerHandler.getAwsProxyHandler(ProductsDriver::class.java1)
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

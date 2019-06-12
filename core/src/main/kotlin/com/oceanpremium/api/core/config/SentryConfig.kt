//package com.oceanpremium.api.core.config
//
//import io.sentry.spring.SentryServletContextInitializer
//import org.springframework.boot.web.servlet.ServletContextInitializer
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.web.servlet.HandlerExceptionResolver
//
//@Configuration
//class SentryConfig {
//
//    @Bean
//    fun sentryExceptionResolver(): HandlerExceptionResolver {
//        return io.sentry.spring.SentryExceptionResolver()
//    }
//
//    /**
//     * This should only be necessary in Spring Boot applications. "Classic" Spring
//     * should automatically load the `io.sentry.servlet.SentryServletContainerInitializer`.
//     */
//    @Bean
//    fun sentryServletContextInitializer(): ServletContextInitializer {
//        return SentryServletContextInitializer()
//    }
//}

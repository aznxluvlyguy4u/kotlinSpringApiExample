//package com.oceanpremium.api.core.config
//
//import org.springframework.boot.web.servlet.FilterRegistrationBean
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.core.Ordered
//import org.springframework.web.cors.CorsConfiguration
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource
//import org.springframework.web.filter.CorsFilter
//
//@Configuration
//class CorsConfig {
//
//    @Bean
//    fun simpleCorsFilter(): FilterRegistrationBean<CorsFilter> {
//        val source = UrlBasedCorsConfigurationSource()
//        val config = CorsConfiguration()
//        config.allowCredentials = true
//        config.allowedOrigins = listOf("*")
//        config.allowedMethods = listOf("POST", "GET", "PUT", "DELETE")
//        config.allowedHeaders = listOf("*")
//        config.maxAge = 3600
//
//        source.registerCorsConfiguration("/**", config)
//
//        val bean = FilterRegistrationBean(CorsFilter(source))
//        bean.order = Ordered.HIGHEST_PRECEDENCE
//
//        return bean
//    }
//}

package com.oceanpremium.api.core.config

import nz.net.ultraq.thymeleaf.JodaDialect
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.thymeleaf.spring5.SpringTemplateEngine
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver
import org.thymeleaf.spring5.view.ThymeleafViewResolver
import org.thymeleaf.templatemode.TemplateMode

@Configuration
class ThymeleafConfig {
    @Bean
    fun templateResolver(): SpringResourceTemplateResolver {
        return SpringResourceTemplateResolver()
            .apply { prefix = "classpath:/templates/" }
            .apply { suffix = ".html"}
            .apply { templateMode = TemplateMode.HTML }
    }

    @Bean
    fun templateEngine(): SpringTemplateEngine {
        return SpringTemplateEngine()
            .apply { setTemplateResolver(templateResolver()) }
            .apply { addDialect(JodaDialect()) }
    }

    @Bean
    fun viewResolver(): ThymeleafViewResolver {
        return ThymeleafViewResolver()
            .apply { templateEngine = templateEngine() }
            .apply { characterEncoding = "UTF-8" }
            .apply { order = 1 }
    }
}

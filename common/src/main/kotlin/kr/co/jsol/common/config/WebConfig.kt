package kr.co.jsol.common.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOriginPatterns("*")
            .allowCredentials(true)
            .allowedMethods("OPTIONS", "GET", "POST", "PUT", "DELETE")
            .exposedHeaders("Authorization")
    }
}

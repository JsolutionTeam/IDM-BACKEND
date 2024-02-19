package kr.co.jsol.common.config

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.security.SecurityScheme
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springdoc.core.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter.ofPattern

@Configuration
@SecurityScheme(
    name = "Bearer Authentication",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer",
)
class SwaggerConfig {

    private val QR_URL = "/api/qr/**"

    @Bean
    fun swagger(): OpenAPI = OpenAPI().info(
        Info()
            .title("J-ERP API Doc")
            .description(
                "API 문서입니다. 마지막 업데이트 시간 : ${
                    LocalDateTime.now()
                        .format(ofPattern("yyyy-MM-dd HH:mm"))
                }",
            )
            .version("0.0.1-SNAPSHOT"),
    )

    @Bean
    fun api(): GroupedOpenApi = GroupedOpenApi.builder()
        .group("001.api v1")
        .pathsToMatch("/api/**")
        .pathsToExclude(QR_URL)
        .displayName("API V1 버전")
        .build()

    @Bean
    fun qr(): GroupedOpenApi = GroupedOpenApi.builder()
        .group("999.qr")
        .pathsToMatch(QR_URL)
        .displayName("QR코드 API")
        .build()
}

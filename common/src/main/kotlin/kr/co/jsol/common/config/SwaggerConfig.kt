package kr.co.jsol.common.config

import org.springframework.context.annotation.Bean
import java.util.Arrays

@Configuration
@EnableWebMvc
class SwaggerConfig {
    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2) //그룹이룸 설정으로 페이지를 나눌수 있다.
            .groupName("JWT NEED")
            .ignoredParameterTypes(AuthenticationPrincipal::class.java) // @AuthenticationPrincipal의 파라미터 요구 필드를 없애기 위함
            .useDefaultResponseMessages(false)
            .securityContexts(Arrays.asList(securityContext())) // swagger에서 jwt 토큰값 넣기위한 설정
            .securitySchemes(Arrays.asList(apiKey())) // swagger에서 jwt 토큰값 넣기위한 설정
            .select()
            .apis(RequestHandlerSelectors.basePackage("org.example.controller")) // Swagger가 controller를 전부 스캔을 한다.
            //                .paths(PathSelectors.any())
            // API 의 URL 경로를 지정할 수 있다.
            .paths(PathSelectors.ant("/api/v1/**")) // .paths(PathSelectors.ant("/api/v1/**")) 와 같이 하면
            // http://localhost/api/v1/ 하위 경로를 가지는 API에 대해 문서를 생성해준다.
            .build()
            .apiInfo(apiInfo())
    }

    @Bean
    fun api2(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .groupName("JWT NO NEED")
            .ignoredParameterTypes(AuthenticationPrincipal::class.java) // @AuthenticationPrincipal의 파라미터 요구 필드를 없애기 위함
            .useDefaultResponseMessages(false)
            .securityContexts(Arrays.asList(securityContext())) // swagger에서 jwt 토큰값 넣기위한 설정
            .securitySchemes(Arrays.asList(apiKey())) // swagger에서 jwt 토큰값 넣기위한 설정
            .select()
            .apis(RequestHandlerSelectors.basePackage("org.example.controller"))
            .paths(PathSelectors.ant("/api/v2/**"))
            .build()
            .apiInfo(apiInfo())
    }

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
            .title("MCall-shop API documentation")
            .description("")
            .version("1.0") //                .termsOfServiceUrl("")
            //                .license("LICENSE")
            //                .licenseUrl("")
            .build()
    }

    // 이하 swagger에서 jwt 토큰값 넣기위한 설정
    private fun securityContext(): SecurityContext {
        return SecurityContext.builder()
            .securityReferences(defaultAuth())
            .build()
    }

    private fun defaultAuth(): List<SecurityReference> {
        val authorizationScope: AuthorizationScope = AuthorizationScope(AUTHORIZATION_SCOPE_GLOBAL, AUTHORIZATION_SCOPE_GLOBAL_DESC)
        val authorizationScopes: Array<AuthorizationScope?> = arrayOfNulls<AuthorizationScope>(1)
        authorizationScopes[0] = authorizationScope
        return Arrays.asList<SecurityReference>(SecurityReference("JWT", authorizationScopes))
    }

    private fun apiKey(): ApiKey {
        return ApiKey("JWT", "Authorization", "header")
    }

    companion object {
        const val AUTHORIZATION_SCOPE_GLOBAL: String = "global"
        const val AUTHORIZATION_SCOPE_GLOBAL_DESC: String = "accessEverything"
    }
}

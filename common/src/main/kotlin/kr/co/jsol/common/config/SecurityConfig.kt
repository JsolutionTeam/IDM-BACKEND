package kr.co.jsol.common.config

import kr.co.jsol.common.exception.ExceptionHandlerFilter
import kr.co.jsol.common.jwt.JwtAuthenticationFilter
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
class SecurityConfig(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    private val exceptionHandlerFilter: ExceptionHandlerFilter,
) : WebSecurityConfigurerAdapter() {
    
    override fun configure(http: HttpSecurity) {
        http.httpBasic()
            .disable()
            .csrf()
            .disable()
            .cors()
            .disable()

        http.sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http.authorizeRequests()
            .antMatchers("/api/**")
            .permitAll()

        http.addFilterBefore(
            exceptionHandlerFilter,
            UsernamePasswordAuthenticationFilter::class.java
        )

        http.addFilterBefore(
            jwtAuthenticationFilter,
            UsernamePasswordAuthenticationFilter::class.java,
        )
    }
}

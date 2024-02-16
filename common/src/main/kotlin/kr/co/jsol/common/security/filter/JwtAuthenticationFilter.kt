package kr.co.jsol.common.security.filter

import com.fasterxml.jackson.databind.ObjectMapper
import kr.co.jsol.common.security.jwt.JwtService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter(private val jwtService: JwtService) : GenericFilterBean() {

    private val objectMapper = ObjectMapper()

    override fun doFilter(
        request: ServletRequest,
        response: ServletResponse,
        chain: FilterChain,
    ) {
        val req = request as HttpServletRequest
        val res = response as HttpServletResponse
        res.setHeader("Access-Control-Allow-Origin", "*")
        res.setHeader("Access-Control-Allow-Methods", "*")
        res.setHeader("Access-Control-Max-Age", "3600")
        res.setHeader(
            "Access-Control-Allow-Headers",
            "Origin, Content-Type, Accept, Authorization",
        )

        val token = jwtService.resolveToken(req)

        if (token.isEmpty()) {
            chain.doFilter(request, response)
        } else {
            if (jwtService.validateToken(token)) {
                val authentication = jwtService.getAuthentication(token)
                SecurityContextHolder.getContext().authentication = authentication
                chain.doFilter(request, response)
            } else {
                responseMessage(res, objectMapper, "토큰이 유효하지 않습니다.")
                SecurityContextHolder.clearContext()
            }
        }
    }

    @Throws(IOException::class)
    private fun responseMessage(
        response: HttpServletResponse,
        objectMapper: ObjectMapper,
        message: String,
    ) {
        response.status = HttpStatus.UNAUTHORIZED.value()
        response.characterEncoding = "UTF-8"
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        objectMapper.writeValue(response.outputStream, message)
    }
}

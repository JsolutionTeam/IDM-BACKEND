package kr.co.jsol.common.exception

import org.springframework.stereotype.Component
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse

@Component
class ExceptionHandlerFilter : GenericFilterBean() {

    override fun doFilter(
        request: ServletRequest,
        response: ServletResponse,
        filterChain: FilterChain,
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: Exception) {
            response.writer.write(e.message)
        }
    }
}

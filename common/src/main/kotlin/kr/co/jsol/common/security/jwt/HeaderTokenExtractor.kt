package kr.co.jsol.common.security.jwt

import lombok.extern.slf4j.Slf4j

//스프링 시큐리티 에서 처리 가능
@Component
@Slf4j
class HeaderTokenExtractor {
    /*
     * HEADER_PREFIX
     * header Authorization token 값의 표준이되는 변수
     */
    val HEADER_PREFIX: String = "Bearer "

    @Throws(AuthenticationException::class)
    fun extract(
        header: String,
        request: HttpServletRequest
    ): String {
        /*
         * - Token 값이 올바르지 않은경우 -
         * header 값이 비어있거나 또는 HEADER_PREFIX 값보다 짧은 경우
         * 이셉션을(예외)를 던져주어야 합니다.
         */


        if (ObjectUtils.isEmpty(header) || header.length < HEADER_PREFIX.length) {
            log.info("error request : " + request.getRequestURI())
            throw NoSuchElementException("헤더에 Authorization 키는 있으나 value 가 올바르지 않음!")
        }

        /*
         * - Token 값이 존재하는 경우 -
         * (bearer ) 부분만 제거 후 token 값 반환
         */
        return header.substring(
            HEADER_PREFIX.length
        )
    }
}

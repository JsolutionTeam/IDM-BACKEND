package kr.co.jsol.common.security.jwt

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

class JwtPreProcessingToken private constructor(
    principal: Any,
    credentials: Any
) : UsernamePasswordAuthenticationToken(principal, credentials) {
    constructor(token: String) : this(token, token.length)
}

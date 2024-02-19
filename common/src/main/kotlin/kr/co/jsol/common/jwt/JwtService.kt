package kr.co.jsol.common.jwt

import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.security.Key
import java.util.Date
import javax.servlet.http.HttpServletRequest

@Component
class JwtService(
    private val userDetailsService: UserDetailsService,
    @Value("\${secret-key}")
    private val strSecretKey: String,
) {
    private val logger = LoggerFactory.getLogger(this.javaClass)
    private val userPkKey = "userPk"
    private val companyIdKey = "companyId"
    private val depthIdKey = "depth"

    private var secretKey: Key =
        Keys.hmacShaKeyFor(strSecretKey.toByteArray()) ?: Keys.secretKeyFor(SignatureAlgorithm.HS256)

    fun getAuthentication(token: String): Authentication {
        val userDetailsImpl = userDetailsService.loadUserByUsername(getUserPk(token))

        class PayloadUserDetailsImpl(
            userDetails: UserDetails,
            payload: Payload,
        )

        val payload = getPayload(token)
        val payloadUserDetails = PayloadUserDetailsImpl(userDetailsImpl, payload)
        return UsernamePasswordAuthenticationToken(payloadUserDetails, "", userDetailsImpl.authorities)
    }

    fun getUserPk(token: String): String {
        return Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .body[userPkKey].toString()
    }

    fun getPayload(token: String): Payload {
        val claims = Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .body
        return Payload.of(claims)
    }

    fun getCompanyId(token: String): String {
        return Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .body[companyIdKey].toString()
    }

    fun resolveToken(request: HttpServletRequest): String {
        val token: String? = request.getHeader("Authorization")
        return token
            ?.takeIf { it.startsWith("Bearer ") } // Bearer 로 시작한다면
            ?.substringAfter("Bearer ") // Bearer 뒤에 있는 토큰을 반환한다.
            ?: "" // 그 외(null 포함)는 빈 문자열을 반환한다.
    }

    fun validateToken(token: String): Boolean {
        return try {
            val claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
            !claims.body.expiration.before(Date())
        } catch (e: JwtException) {
            logger.debug(e.localizedMessage)
            false
        } catch (e: IllegalArgumentException) {
            logger.debug(e.localizedMessage)
            false
        }
    }
}
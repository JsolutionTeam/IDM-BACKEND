package kr.co.jsol.common.security.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.security.Key
import java.time.LocalDateTime
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

    private fun createToken(
        userPk: String,
        expiredTime: LocalDateTime,
        companyId: String,
        depth: Int,
    ): String {
        val claims: Claims = Jwts.claims()
            .setSubject(userPk)
        claims[userPkKey] = userPk
        claims[companyIdKey] = companyId
        claims[depthIdKey] = depth
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(Date())
            .setExpiration(
                Date(
                    expiredTime.atZone(java.time.ZoneId.systemDefault())
                        .toInstant()
                        .toEpochMilli()
                )
            )
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact()
    }

    fun createAccessToken(
        userPk: String,
        companyId: String,
        depth: Int,
    ): String {
        return createToken(
            userPk, LocalDateTime.now()
                .plusMinutes(30), companyId, depth
        )
    }

    fun createRefreshToken(
        userPk: String,
        companyId: String,
        depth: Int,
    ): String {
        return createToken(
            userPk, LocalDateTime.now()
                .plusDays(30), companyId, depth
        )
    }

    fun getAuthentication(token: String): Authentication {
        val userDetailsImpl = userDetailsService.loadUserByUsername(getUserPk(token))
        return UsernamePasswordAuthenticationToken(userDetailsImpl, "", userDetailsImpl.authorities)
    }

    fun getUserPk(token: String): String {
        return Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .body[userPkKey].toString()
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

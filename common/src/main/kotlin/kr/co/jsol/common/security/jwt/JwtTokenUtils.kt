package kr.co.jsol.common.security.jwt

import com.auth0.jwt.JWT
import java.util.Date

//JWT 설정
//유틸성 클래스를 객체를 생성하도록 만든 것은 불필요하게 코드를 열어두는 것이므로, 내부 생성자를 통해 이를 제한할 필요가 있다. 롬복을 사용중이라면 NoArgsConstructor를 이용
@NoArgsConstructor
@Slf4j
object JwtTokenUtils {
    private const val SEC = 1 * 1000
    private const val MINUTE = 60 * SEC
    private const val HOUR = 60 * MINUTE
    private const val DAY = 24 * HOUR
    private const val JWT_TOKEN_VALID_TIME = 24 * HOUR // 24시간
    private const val REFRESH_TOKEN_EXPIRE_TIME = (DAY * 7 // 7일
            ).toLong()

    const val CLAIM_EXPIRED_DATE: String = "EXPIRED_DATE"
    const val CLAIM_USER_ID: String = "USER_ID"
    const val CLAIM_USER_ROLE: String = "USER_ROLE"
    const val JWT_SECRET: String = "WhyCan`tDoThis@Value..?!StaticSetOnMemory"
    const val ISSUER: String = "J-SOL"

    private fun generateAlgorithm(): Algorithm {
        return Algorithm.HMAC256(JWT_SECRET)
    }

    fun generateJwtAndRefreshToken(
        name: String?,
        id: String?,
        role: String?,
        companyName: String?
    ): TokenDto {
        var accessToken: String? = null
        val accessTokenExpiresIn = Date(System.currentTimeMillis() + JWT_TOKEN_VALID_TIME)
        try {
            accessToken = JWT.create()
                .withIssuer(ISSUER)
                .withClaim(CLAIM_USER_ID, id) // 사용자 로그인 ID
                .withClaim(CLAIM_USER_ROLE, role) // 사용자 권한
                // 토큰 만료 일시 = 현재 시간 + 토큰 유효기간)
                .withClaim(CLAIM_EXPIRED_DATE, accessTokenExpiresIn)
                .sign(generateAlgorithm())
        } catch (e: Exception) {
            log.error(e.message)
        }

        //        String refreshToken = null;
//        Date refreshTokenExpiresIn = new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRE_TIME);
//        try {
//            refreshToken = JWT.create()
//                    .withIssuer("JsolRefresh")
//                    // 토큰 만료 일시 = 현재 시간 + 토큰 유효기간)
//                    .withClaim(CLAIM_EXPIRED_DATE, refreshTokenExpiresIn)
//                    .sign(generateAlgorithm());
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        }
        val tokenDto: TokenDto = builder()
            .grantType("BEARER")
            .accessToken("BEARER $accessToken")
            .status(true)
            .id(id)
            .role(role)
            .companyName(companyName)
            .name(name)
            .message("토큰 발급 성공")
            .accessTokenExpiresIn(accessTokenExpiresIn)
            .build()

        return tokenDto
    }
}

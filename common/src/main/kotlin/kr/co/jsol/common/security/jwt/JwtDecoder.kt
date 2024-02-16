package kr.co.jsol.common.security.jwt

import com.auth0.jwt.JWT
import java.util.Date
import java.util.Optional
import java.util.function.Supplier

//JWT 디코드
@Component
@Slf4j
class JwtDecoder {
    //    public String decodeShopName(String token) {
    //        DecodedJWT decodedJWT = isValidToken(token)
    //                .orElseThrow(() -> new IllegalArgumentException("유효한 토큰이 아닙니다."));
    //
    //        Date expiredDate = decodedJWT
    //                .getClaim(JwtTokenUtils.CLAIM_EXPIRED_DATE)
    //                .asDate();
    //
    //        Date now = new Date();
    //        if (expiredDate.before(now)) {
    //            throw new IllegalArgumentException("유효한 토큰이 아닙니다.");
    //        }
    //
    //        String shopName = decodedJWT
    //                .getClaim(JwtTokenUtils.CLAIM_USER_SHOP_NAME)
    //                .asString();
    //
    //        return shopName;
    //    }
    //
    //    public String decodeExpiredTokenId(String token) {
    //        DecodedJWT decodedJWT = isValidToken(token)
    //                .orElseThrow(() -> new IllegalArgumentException("유효한 토큰이 아닙니다."));
    //
    //        Date expiredDate = decodedJWT
    //                .getClaim(JwtTokenUtils.CLAIM_EXPIRED_DATE)
    //                .asDate();
    //
    //        Date now = new Date();
    //        if (!expiredDate.before(now)) {
    //            throw new IllegalArgumentException("기한이 유효한 토큰을 다루는 중입니다. 잘못된 메서드 사용!!");
    //        }
    //
    //        String id = decodedJWT
    //                .getClaim(JwtTokenUtils.CLAIM_USER_ID)
    //                .asString();
    //
    //        return id;
    //
    //    }
    //    public String decodeExpiredTokenShopName(String token) {
    //        DecodedJWT decodedJWT = isValidToken(token)
    //                .orElseThrow(() -> new IllegalArgumentException("유효한 토큰이 아닙니다."));
    //
    //        Date expiredDate = decodedJWT
    //                .getClaim(JwtTokenUtils.CLAIM_EXPIRED_DATE)
    //                .asDate();
    //
    //        Date now = new Date();
    //        if (!expiredDate.before(now)) {
    //            throw new IllegalArgumentException("기한이 유효한 토큰을 다루는 중입니다. 잘못된 메서드 사용!!");
    //        }
    //
    //        String shopName = decodedJWT
    //                .getClaim(JwtTokenUtils.CLAIM_USER_SHOP_NAME)
    //                .asString();
    //
    //        return shopName;
    //    }
    //
    fun decodeId(token: String): String {
        val decodedJWT: DecodedJWT = isValidToken(token)
            .orElseThrow { IllegalArgumentException("유효한 토큰이 아닙니다.") }

        val expiredDate: Date = decodedJWT
            .getClaim(JwtTokenUtils.CLAIM_EXPIRED_DATE)
            .asDate()

        val now = Date()
        require(!expiredDate.before(now)) { "유효한 토큰이 아닙니다." }

        val id: String = decodedJWT
            .getClaim(JwtTokenUtils.CLAIM_USER_ID)
            .asString()

        return id
    }

    fun isExpiredToken(token: String): Boolean {
        val decodedJWT: DecodedJWT = isValidToken(token)
            .orElseThrow<RuntimeException>(
                Supplier<RuntimeException> { ResponseStatusException(HttpStatus.UNAUTHORIZED, "토큰 검증 실패 (isValidToken)") })

        val expiredDate: Date = decodedJWT
            .getClaim(JwtTokenUtils.CLAIM_EXPIRED_DATE)
            .asDate()

        val now = Date()

        return expiredDate.before(now)
    }

    private fun isValidToken(token: String): Optional<DecodedJWT> {
        var jwt: DecodedJWT? = null

        try {
            val algorithm: Algorithm = Algorithm.HMAC256(JwtTokenUtils.JWT_SECRET)
            val verifier: JWTVerifier = JWT
                .require(algorithm)
                .build()

            jwt = verifier.verify(token)
        } catch (e: Exception) {
            log.error(e.message)
        }

        return Optional.ofNullable<DecodedJWT>(jwt)
    }

    fun decoderole(token: String): String {
        val decodedJWT: DecodedJWT = isValidToken(token)
            .orElseThrow { IllegalArgumentException("유효한 토큰이 아닙니다.") }

        val expiredDate: Date = decodedJWT
            .getClaim(JwtTokenUtils.CLAIM_EXPIRED_DATE)
            .asDate()

        val now = Date()
        require(!expiredDate.before(now)) { "유효한 토큰이 아닙니다." }

        val role: String = decodedJWT
            .getClaim(JwtTokenUtils.CLAIM_USER_ROLE)
            .asString()

        return role
    }
}

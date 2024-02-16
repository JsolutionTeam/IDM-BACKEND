package kr.co.jsol.common.security.jwt

import lombok.Builder
import java.util.Date

@Getter
@Builder
class TokenDto {
    private val status = false
    private val message: String? = null
    private val id: String? = null
    private val role: String? = null
    private val name: String? = null
    private val companyName: String? = null
    private val grantType: String? = null
    private val accessToken: String? = null
    private val accessTokenExpiresIn: Date? = null
}

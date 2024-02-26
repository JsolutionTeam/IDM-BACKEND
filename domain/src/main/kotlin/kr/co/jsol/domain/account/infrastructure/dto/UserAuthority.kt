package kr.co.jsol.domain.account.infrastructure.dto

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.security.core.GrantedAuthority

@Schema(name = "유저 권한")
enum class UserAuthority(
    @field:Schema(description = "권한")
    private val authority: String,
    @field:Schema(description = "설명")
    val description: String,
) : GrantedAuthority {
    USER(ROLES.USER, "유저권한"),
    ADMIN(ROLES.ADMIN, "어드민권한"),
    ;

    object ROLES {
        const val USER = "USER"
        const val ADMIN = "ADMIN"
    }

    override fun getAuthority(): String {
        return authority
    }
}

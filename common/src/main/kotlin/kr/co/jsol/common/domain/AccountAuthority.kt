package kr.co.jsol.common.domain

import io.swagger.v3.oas.annotations.media.Schema
import org.slf4j.LoggerFactory
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

enum class AccountAuthority(
    @field:Schema(description = "권한")
    private val authority: String,
    @field:Schema(description = "설명")
    val description: String,
) : GrantedAuthority {

    USER(ROLES.USER, "하위 소속 계정 권한으로 회사 권한 계정이 직접 부여합니다."),
    COMPANY(ROLES.COMPANY, "회사 계정 권한으로 최종 관리자 권한이 회사를 관리할 때 직접 부여합니다."),
    ADMIN(ROLES.ADMIN, "최종 관리자 계정 권한 (제이솔루션)"),
    ;

    object ROLES {
        const val USER = "USER"
        const val COMPANY = "COMPANY"
        const val ADMIN = "ADMIN"
    }

    object ROLECHECK {
        const val HasUserRole = "hasAnyAuthority(\"USER\")"
        const val HasCompanyRole = "hasAnyAuthority(\"COMPANY\")"
        const val HasAdminRole = "hasAnyAuthority(\"ADMIN\")"
        const val HasAdminAndCompanyRole = "hasAnyAuthority(\"ADMIN\", \"COMPANY\")"
        const val HasCompanyAndUserRole = "hasAnyAuthority(\"ADMIN\", \"COMPANY\")"
        const val HasAnyRole = "hasAnyAuthority(\"ADMIN\", \"COMPANY\", \"USER\")"
    }

    class Response(role: AccountAuthority) {
        val name = role.name
        val description = role.description
    }

    override fun getAuthority(): String {
        return authority
    }

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)

        fun of(value: String): GrantedAuthority {
            log.info("value : $value")
            log.info("entries : ${entries.joinToString(", ") { it.name }}")

            val role = value.uppercase()

            if (role.contains("ADMIN") || role.contains("ROOT") || role.contains("JSOL")) {
                return ADMIN
            } else if (role.contains("COMPANY") || role.contains("PARENT") || role.contains("AGENCY")) {
                return COMPANY
            } else if (role.contains("USER") || role.contains("BELONG") || role.contains("STORE")) {
                return USER
            }

            return entries.find { it.name == role }
                ?: SimpleGrantedAuthority(value)
        }
    }
}

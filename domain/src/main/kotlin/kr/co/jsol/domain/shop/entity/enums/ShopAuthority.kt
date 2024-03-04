package kr.co.jsol.domain.shop.entity.enums

import io.swagger.v3.oas.annotations.media.Schema
import org.slf4j.LoggerFactory
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

enum class ShopAuthority(
    @field:Schema(description = "권한")
    private val authority: String,
    @field:Schema(description = "설명")
    val description: String,
) : GrantedAuthority {

    COMPANY(ROLES.COMPANY, "회사 계정 권한으로 최종 관리자 권한이 회사를 관리할 때 직접 부여합니다."),
    ADMIN(ROLES.ADMIN, "최종 관리자 계정 권한 (제이솔루션)"),
    ;

    object ROLES {
        const val COMPANY = "COMPANY"
        const val ADMIN = "ADMIN"
    }

    object ROLECHECK {
        const val HasCompanyRole = "hasAnyAuthority(\"COMPANY\")"
        const val HasAdminRole = "hasAnyAuthority(\"ADMIN\")"
        const val HasAdminAndCompanyRole = "hasAnyAuthority(\"ADMIN\", \"COMPANY\")"
        const val HasCompanyAndUserRole = "hasAnyAuthority(\"ADMIN\", \"COMPANY\")"
        const val HasAnyRole = "hasAnyAuthority(\"ADMIN\", \"COMPANY\")"
    }

    class Response(role: ShopAuthority) {
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
            }

            return entries.find { it.name == role }
                ?: SimpleGrantedAuthority(value)
        }

//        fun of(value: String): MutableList<GrantedAuthority> {
//            log.info("value : $value")
//            log.info("entries : ${entries.joinToString(", ") { it.name }}")
//
//            val role = value.uppercase()
//
//            if (role.contains("ADMIN") || role.contains("ROOT") || role.contains("JSOL")) {
//                return mutableListOf(ADMIN, COMPANY, USER)
//            } else if (role.contains("COMPANY") || role.contains("PARENT") || role.contains("AGENCY")) {
//                return mutableListOf(COMPANY, USER)
//            } else if (role.contains("USER") || role.contains("BELONG") || role.contains("STORE")) {
//                return mutableListOf(USER)
//            }
//
//            return mutableListOf(entries.find { it.name == role }
//                                     ?: SimpleGrantedAuthority(value))
//        }
    }
}

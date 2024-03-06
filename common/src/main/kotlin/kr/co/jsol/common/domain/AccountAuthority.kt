package kr.co.jsol.common.domain

import io.swagger.v3.oas.annotations.media.Schema
import org.slf4j.LoggerFactory
import org.springframework.security.core.GrantedAuthority

enum class AccountAuthority(
    @field:Schema(description = "권한")
    private val authority: String,
    @field:Schema(description = "설명")
    val description: String,
) : GrantedAuthority {

    COMPANY(ROLES.COMPANY, "회사 계정 권한으로 최종 관리자 권한이 회사를 관리할 때 직접 부여합니다."),
    MASTER(ROLES.MASTER, "최종 관리자 계정 권한 (제이솔루션)"),
    ;

    object ROLES {
        const val COMPANY = "COMPANY"
        const val MASTER = "MASTER"
    }

    object ROLECHECK {
        const val HasCompanyRole = "hasAnyAuthority(\"COMPANY\")"
        const val HasMasterRole = "hasAnyAuthority(\"MASTER\")"
        const val HasMasterAndCompanyRole = "hasAnyAuthority(\"MASTER\", \"COMPANY\")"
        const val HasAnyRole = "hasAnyAuthority(\"MASTER\", \"COMPANY\")"
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
    }
}

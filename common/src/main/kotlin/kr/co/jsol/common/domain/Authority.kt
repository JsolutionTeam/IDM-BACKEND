package kr.co.jsol.common.domain

enum class Authority(
    val description: String,
) {

    ROLE_USER("하위 소속 계정 권한으로 회사 권한 계정이 직접 부여합니다."),
    ROLE_COMPANY("회사 계정 권한으로 최종 관리자 권한이 회사를 관리할 때 직접 부여합니다."),
    ROLE_ADMIN("최종 관리자 계정 권한 (제이솔루션)"),
    ;

    object ROLECHECK {
        const val HasUserRole = "hasAnyAuthority(\"USER\")"
        const val HasCompanyRole = "hasAnyAuthority(\"COMPANY\")"
        const val HasAdminRole = "hasAnyAuthority(\"ADMIN\")"
        const val HasAdminAndCompanyRole = "hasAnyAuthority(\"ADMIN\", \"COMPANY\")"
        const val HasCompanyAndUserRole = "hasAnyAuthority(\"ADMIN\", \"COMPANY\")"
        const val HasAnyRole = "hasAnyAuthority(\"ADMIN\", \"COMPANY\", \"USER\")"
    }

    class Response(role: Authority) {
        val name = role.name
        val description = role.description
    }
}

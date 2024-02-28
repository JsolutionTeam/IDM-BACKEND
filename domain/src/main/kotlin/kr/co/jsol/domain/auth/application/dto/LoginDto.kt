package kr.co.jsol.domain.auth.application.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "로그인 요청")
data class LoginDto(
    @Schema(description = "아이디", example = "js")
    val id: String,
    @Schema(description = "비밀번호", example = "js")
    val password: String,
) {
    
    fun toMap(): Map<String, String> {
        return mapOf(
            "id" to id,
            "password" to password
        )
    }
}

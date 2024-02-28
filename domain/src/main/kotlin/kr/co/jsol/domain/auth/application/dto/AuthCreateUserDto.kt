package kr.co.jsol.domain.auth.application.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "AUTH 서버 계정 생성 요청")
data class AuthCreateUserDto(
    val id: String,
    val password: String,
    val name: String,
    val role: String,
) {

    fun toMap(): Map<String, String> {
        return mapOf(
            "id" to id,
            "password" to password,
            "name" to name,
            "role" to role.toString(),
        )
    }
}

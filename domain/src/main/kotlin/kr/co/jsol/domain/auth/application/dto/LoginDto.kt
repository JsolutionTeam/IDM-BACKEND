package kr.co.jsol.domain.auth.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotBlank

@Schema(name = "로그인 요청")
data class LoginDto(
    @field:NotBlank(message = "아이디를 입력해주세요.")
    @field:Schema(description = "아이디", example = "js")
    val id: String,

    @field:NotBlank(message = "비밀번호를 입력해주세요.")
    @field:Schema(description = "비밀번호", example = "js")
    val password: String,
) {

    fun toMap(): Map<String, String> {
        return mapOf(
            "id" to id,
            "password" to password
        )
    }
}

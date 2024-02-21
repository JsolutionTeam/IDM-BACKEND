package kr.co.jsol.domain.account.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Schema(name = "계정 생성 요청")
data class CreateAccountDto(
    @field:NotBlank(message = "아이디는 필수 입력값입니다.")
    @field:Size(min = 6, max = 20, message = "아이디는 6~20자 이내로 입력해야 합니다.")
    @field:Schema(description = "계정 아이디[로그인시 사용]")
    val id: String,

    @field:NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @field:Size(min = 8, max = 20, message = "비밀번호는 8~20자 이내로 입력해야 합니다.")
    @field:Schema(description = "비밀번호")
    val password: String,
)


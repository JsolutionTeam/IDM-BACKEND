package kr.co.jsol.domain.account.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Schema(name = "계정 수정 요청")
data class UpdateAccountDto(
    @field:NotBlank(message = "아이디는 필수 입력값입니다.")
    @field:Size(max = 20, message = "아이디는 20자 이내로 입력해야 합니다.")
    @field:Schema(description = "계정 아이디[로그인시 사용]")
    private val id: String,

    @field:Size(min = 8, max = 20, message = "비밀번호는 8~20자 이내로 입력해야 합니다.")
    @field:Schema(description = "비밀번호")
    private val password: String? = null,

    @field:Schema(description = "사용자명")
    private val name: String? = null,
)

package kr.co.jsol.domain.account.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Schema(name = "계정 수정 요청")
data class UpdateAccountDto(
    @field:NotBlank(message = "아이디는 필수 입력값입니다.")
    @field:Size(max = 20, message = "아이디는 20자 이내로 입력해야 합니다.")
    @field:Schema(description = "계정 아이디[로그인시 사용]")
    var id: String,

    @field:Size(max = 255, message = "비밀번호는 255자 이내로 입력해야 합니다.")
    @field:Schema(description = "비밀번호")
    val password: String? = null,

    @field:Schema(description = "사용자명")
    val name: String? = null,

    @field:Schema(description = "전화번호")
    val phone: String? = null,

    @field:Schema(description = "메모")
    val memo: String? = null,
)

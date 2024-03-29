package kr.co.jsol.domain.account.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty

@Schema(name = "계정 다중 삭제 요청")
data class DeleteAccountsDto(
    @field:Valid
    @field:NotEmpty(message = "계정 아이디는 필수 입력입니다.")
    @field:Schema(description = "계정 아이디 리스트")
    val ids: List<@NotBlank(message = "계정 아이디는 필수 입력입니다.") String>,
)

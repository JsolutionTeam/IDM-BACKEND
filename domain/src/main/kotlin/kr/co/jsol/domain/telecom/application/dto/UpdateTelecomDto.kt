package kr.co.jsol.domain.telecom.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Schema(name = "통신사 수정 요청")
data class UpdateTelecomDto(
    @field:NotNull(message = "통신사 아이디는 필수입니다.")
    @field:Min(value = 1, message = "통신사 아이디는 1 이상의 숫자로 입력해주세요.")
    @field:Schema(description = "통신사 아이디", example = "1")
    val id: Long,

    @field:NotBlank(message = "통신사 이름은 필수입니다.")
    @field:Size(max = 255, message = "통신사 이름은 255자 이하로 입력해주세요.")
    @field:Schema(description = "통신사 이름", example = "SKT")
    val name: String,
)

package kr.co.jsol.domain.telecom.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.jsol.domain.telecom.entity.Telecom
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Schema(name = "통신사 등록 요청")
data class CreateTelecomDto(
    @field:NotBlank(message = "통신사 이름은 필수입니다.")
    @field:Size(max = 255, message = "통신사 이름은 255자 이하로 입력해주세요.")
    @field:Schema(description = "통신사 이름", example = "SKT")
    val name: String,
) {

    fun toEntity() = Telecom(
        name = name,
    )
}

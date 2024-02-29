package kr.co.jsol.domain.devicesubsidy.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty

@Schema(name = "회사 지원금 다중 삭제 요청")
data class DeleteDeviceSubsidiesDto(
    @field:Valid
    @field:NotEmpty(message = "회사 지원금 아이디는 필수 입력입니다.")
    @field:Schema(description = "회사 지원금 아이디 리스트")
    val ids: List<@Min(value = 1L, message = "회사 지원금 아이디는 1보다 큰 숫자여야 합니다.") Long>,
)

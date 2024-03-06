package kr.co.jsol.domain.devicesubsidy.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

@Schema(name = "공시 지원금 가격만 조회 요청")
data class GetDeviceSubsidyPriceDto(
    @field:NotNull(message = "통신사 아이디는 필수입력입니다.")
    @field:Min(value = 1, message = "통신사 아이디 1 이상이어야 합니다.")
    @field:Schema(description = "통신사 아이디")
    val telecomId: Long,

    @field:NotNull(message = "요금제 아이디는 필수입력입니다.")
    @field:Min(value = 1, message = "요금제 아이디는 1 이상이어야 합니다.")
    @field:Schema(description = "요금제 아이디")
    val phonePlanId: Long,

    @field:NotNull(message = "단말 아이디는 필수입력입니다.")
    @field:Min(value = 1, message = "단말 아이디는 1 이상이어야 합니다.")
    @field:Schema(description = "단말 아이디")
    val deviceId: Long,
)

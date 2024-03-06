package kr.co.jsol.domain.devicesubsidy.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

@Schema(name = "공시 지원금 단일 수정 요청")
data class UpdateDeviceSubsidyDto(
    @field:NotNull(message = "회사 지원금 아이디는 필수입력입니다.")
    @field:Min(value = 1, message = "회사 지원금 아이디는 1 이상이어야 합니다.")
    @field:Schema(description = "회사 지원금 아이디")
    val id: Long,

    @field:Schema(description = "공시 지원금 가격")
    val price: Long? = null,

    @field:Schema(description = "idm - telecom idx")
    val telecomId: Long? = null,

    @field:Schema(description = "mcall - phone-plan id")
    val phonePlanId: Long? = null,

    @field:Schema(description = "idm - device idx")
    val deviceId: Long? = null,
)

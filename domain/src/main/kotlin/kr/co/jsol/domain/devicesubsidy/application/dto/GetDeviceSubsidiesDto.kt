package kr.co.jsol.domain.devicesubsidy.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.Min

@Schema(name = "공시 지원금 페이지 조회 요청")
data class GetDeviceSubsidiesDto(
    @field:Min(value = 0, message = "최소 공시 지원금 가격은 0원 이상이어야 합니다.")
    @field:Schema(description = "최소 공시 지원금 가격")
    val priceMin: Long? = null,

    @field:Min(value = 0, message = "최대 공시 지원금 가격은 0원 이상이어야 합니다.")
    @field:Schema(description = "최대 공시 지원금 가격")
    val priceMax: Long? = null,

    @field:Min(value = 1, message = "idm - telecom idx는 1 이상이어야 합니다.")
    @field:Schema(description = "idm - telecom idx")
    val telecomId: Long? = null,

    @field:Min(value = 1, message = "mcall - phone-plan id는 1 이상이어야 합니다.")
    @field:Schema(description = "mcall - phone-plan id")
    val phonePlanId: Long? = null,

    @field:Min(value = 1, message = "idm - device idx는 1 이상이어야 합니다.")
    @field:Schema(description = "idm - device idx")
    val deviceId: Long? = null,
)

package kr.co.jsol.domain.devicesubsidy.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.jsol.domain.device.entity.Device
import kr.co.jsol.domain.devicesubsidy.entity.DeviceSubsidy
import kr.co.jsol.domain.phoneplan.entity.PhonePlan
import kr.co.jsol.domain.telecom.entity.Telecom
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

@Schema(name = "공시 지원금 등록 요청")
data class CreateDeviceSubsidyDto(
    @field:NotNull(message = "공시 지원금 가격은 필수입력입니다.")
    @field:Min(value = 0, message = "공시 지원금 가격은 0원 이상이어야 합니다.")
    @field:Schema(description = "공시 지원금 가격")
    val price: Long,

    @field:NotNull(message = "idm - telecom idx는 필수입력입니다.")
    @field:Min(value = 1, message = "idm - telecom idx는 1 이상이어야 합니다.")
    @field:Schema(description = "idm - telecom idx")
    val telecomId: Long,

    @field:NotNull(message = "mcall - phone-plan id는 필수입력입니다.")
    @field:Min(value = 1, message = "mcall - phone-plan id는 1 이상이어야 합니다.")
    @field:Schema(description = "mcall - phone-plan id")
    val phonePlanId: Long,

    @field:NotNull(message = "idm - device idx는 필수입력입니다.")
    @field:Min(value = 1, message = "idm - device idx는 1 이상이어야 합니다.")
    @field:Schema(description = "idm - device idx")
    val deviceId: Long,
) {

    fun toEntity(
        telecom: Telecom,
        phonePlan: PhonePlan,
        device: Device,
    ) = DeviceSubsidy(
        price = price,
        telecom = telecom,
        phonePlan = phonePlan,
        device = device,
    )
}

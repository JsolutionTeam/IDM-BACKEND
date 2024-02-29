package kr.co.jsol.domain.devicesubsidy.infrastructure.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.jsol.domain.device.entity.Device
import kr.co.jsol.domain.device.infrastructure.dto.DeviceDto
import kr.co.jsol.domain.devicesubsidy.entity.DeviceSubsidy
import kr.co.jsol.domain.phoneplan.entity.PhonePlan
import kr.co.jsol.domain.phoneplan.infrastructure.dto.PhonePlanDto
import kr.co.jsol.domain.telecom.entity.Telecom
import kr.co.jsol.domain.telecom.infrastructure.dto.TelecomDto

@Schema(name = "공시 지원금 응답")
data class DeviceSubsidyDto(
    @Schema(description = "공시 지원금 아이디")
    val id: Long,

    @field:Schema(description = "공시지원금액")
    var price: Long,

    var telecom: TelecomDto,

    var phonePlan: PhonePlanDto,

    var device: DeviceDto,
) {

    constructor(deviceSubsidy: DeviceSubsidy) : this(
        id = deviceSubsidy.id,
        price = deviceSubsidy.price,
        telecom = TelecomDto(deviceSubsidy.telecom),
        phonePlan = PhonePlanDto(deviceSubsidy.phonePlan),
        device = DeviceDto(deviceSubsidy.device),
    )
}

package kr.co.jsol.domain.telecomdevice.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.Valid

@Schema(name = "통신팀 판매용 단말 정보 수정 요청")
data class UpdateTelecomDevicesDto(
    @field:Valid
    val telecomDevices: List<UpdateTelecomDeviceDto>,
)

package kr.co.jsol.domain.telecomdevice.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.Valid

@Schema(name = "통신팀 판매용 단말 표시 여부 다중 수정 요청")
data class UpdateTelecomDeviceIsDisplaysDto(
    @field:Valid
    val telecomDevices: List<UpdateTelecomDeviceIsDisplayDto>,
)

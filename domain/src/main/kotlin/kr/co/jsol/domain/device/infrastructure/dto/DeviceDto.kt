package kr.co.jsol.domain.device.infrastructure.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "디바이스 응답")
data class DeviceDto(
    @field:Schema(description = "디바이스 아이디")
    val id: Long,

    @field:Schema(description = "펫네임")
    val petName: String,

    @field:Schema(description = "모델네임")
    val modelName: String,

    @field:Schema(description = "시리즈")
    
)

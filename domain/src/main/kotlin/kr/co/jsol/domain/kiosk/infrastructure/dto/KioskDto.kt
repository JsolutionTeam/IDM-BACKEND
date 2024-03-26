package kr.co.jsol.domain.kiosk.infrastructure.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "키오스크 응답")
data class KioskDto(
    @field:Schema(description = "키오스크 아이디")
    val id: Long,
)

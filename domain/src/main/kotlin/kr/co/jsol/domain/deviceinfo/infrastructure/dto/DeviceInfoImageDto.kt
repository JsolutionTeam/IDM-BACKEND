package kr.co.jsol.domain.deviceinfo.infrastructure.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "단말 상세 아이디 + 이미지 응답")
class DeviceInfoImageDto(
    @field:Schema(description = "단말 상세 아이디")
    val id: Long,

    @field:Schema(description = "이미지 파일 (Resource) URL")
    val imageUrl: String,
)

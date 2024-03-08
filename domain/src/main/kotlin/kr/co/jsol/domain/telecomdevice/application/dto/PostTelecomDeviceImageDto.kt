package kr.co.jsol.domain.telecomdevice.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.web.multipart.MultipartFile

@Schema(name = "통신팀 판매용 단말의 대표 이미지 등록")
data class PostTelecomDeviceImageDto(
    @field:Schema(description = "단말 상세 아이디 TelecomDevice.id")
    val id: Long,

    @field:Schema(description = "이미지 파일")
    val multipartFile: MultipartFile,
)

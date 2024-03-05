package kr.co.jsol.domain.deviceinfo.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.web.multipart.MultipartFile

@Schema(name = "디바이스 이미지 등록(수정, 삭제X)")
data class PostDeviceInfoImage(
    @field:Schema(description = "단말 상세 아이디 DeviceInfo.id")
    val id: Long,

    @field:Schema(description = "이미지 파일")
    val multipartFile: MultipartFile,
)

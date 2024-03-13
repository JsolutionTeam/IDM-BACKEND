package kr.co.jsol.common.file.application.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "파일 정보")
data class FileDto(
    var filename: String,

    @field:Schema(description = "파일 크기, 0일 경우 파일 크기를 읽을 수 없음")
    var size: Long,
)

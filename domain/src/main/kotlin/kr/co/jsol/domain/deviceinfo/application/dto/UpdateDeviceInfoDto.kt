package kr.co.jsol.domain.deviceinfo.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotNull

@Schema(name = "단말 상세 등록 요청")
data class UpdateDeviceInfoDto(
    @field:NotNull(message = "단말 상세 아이디는 필수입니다.")
    @field:Schema(description = "단말 상세 아이디")
    val id: Long,

    @field:NotNull(message = "모델 이미지 링크는 필수입니다.")
    @field:Schema(description = "모델 이미지 링크, 이미지 등록 후 반환받은 문자열 입력")
    val imageUrl: String,
)

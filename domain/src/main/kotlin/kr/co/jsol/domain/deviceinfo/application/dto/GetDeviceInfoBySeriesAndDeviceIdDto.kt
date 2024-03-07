package kr.co.jsol.domain.deviceinfo.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import org.springdoc.api.annotations.ParameterObject
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@ParameterObject
@Schema(name = "단말 이미지 리스트 조회 요청")
data class GetDeviceInfoBySeriesAndDeviceIdDto(
    @field:NotBlank(message = "series는 필수입력입니다.")
    @field:Schema(description = "series")
    val series: String,

    @field:NotNull(message = "단말 아이디는 필수입력입니다.")
    @field:Schema(description = "단말 아이디")
    val deviceId: Long,
)

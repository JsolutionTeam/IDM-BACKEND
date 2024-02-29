package kr.co.jsol.domain.devicesubsidy.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.Valid
import javax.validation.constraints.NotEmpty

@Schema(name = "회사 지원금 다중 수정 요청")
data class UpdateDeviceSubsidiesDto(
    @field:Valid
    @field:NotEmpty(message = "회사 지원금 수정 요청 데이터가 없습니다.")
    @field:Schema(description = "회사 지원금 수정 요청")
    val companySubsidies: List<UpdateDeviceSubsidyDto>,
)

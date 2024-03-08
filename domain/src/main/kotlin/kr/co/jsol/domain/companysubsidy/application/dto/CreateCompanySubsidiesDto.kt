package kr.co.jsol.domain.companysubsidy.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.Valid

@Schema(name = "회사 지원금 다중 등록 요청")
data class CreateCompanySubsidiesDto(
    @field:Valid
    val companySubsidies: List<CreateCompanySubsidyDto>,
)

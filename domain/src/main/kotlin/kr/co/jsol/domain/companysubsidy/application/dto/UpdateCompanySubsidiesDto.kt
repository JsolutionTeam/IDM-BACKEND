package kr.co.jsol.domain.companysubsidy.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.jsol.domain.telecom.entity.enums.DiscountType
import kr.co.jsol.domain.telecom.entity.enums.OpenType
import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Schema(name = "회사 지원금 다중 수정 요청")
data class UpdateCompanySubsidiesDto(
    @field:Valid
    @field:NotEmpty(message = "회사 지원금 수정 요청 데이터가 없습니다.")
    @field:Schema(description = "회사 지원금 수정 요청")
    val companySubsidies: List<UpdateCompanySubsidyDto>,
)

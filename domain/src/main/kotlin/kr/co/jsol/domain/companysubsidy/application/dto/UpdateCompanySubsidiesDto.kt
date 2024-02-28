package kr.co.jsol.domain.companysubsidy.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.jsol.domain.telecom.entity.enums.DiscountType
import kr.co.jsol.domain.telecom.entity.enums.OpenType
import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Schema(name = "회사 지원금 등록 DTO")
data class UpdateCompanySubsidiesDto(
    @field:Valid
    @field:NotEmpty(message = "회사 지원금 수정 요청 데이터가 없습니다.")
    @field:Schema(description = "회사 지원금 수정 요청")
    val companySubsidies: List<In>,
) {

    inner class In(
        @field:NotNull(message = "회사 지원금 아이디는 필수입력입니다.")
        @field:Min(value = 1, message = "회사 지원금 아이디는 1 이상이어야 합니다.")
        @field:Schema(description = "회사 지원금 아이디")
        val id: Long,

        @field:Schema(description = "개통 유형", implementation = OpenType::class)
        val openType: OpenType? = null,

        @field:Schema(description = "선약/공시 할인 유형", implementation = DiscountType::class)
        val discountType: DiscountType? = null,

        @field:Schema(description = "공시지원금 가격")
        val price: Long? = null,

        @field:Schema(description = "idm - shop idx")
        val shopId: Long? = null,

        @field:Schema(description = "idm - telecom idx")
        val telecomId: Long? = null,

        @field:Schema(description = "mcall - phone-plan id")
        val phonePlanId: Long? = null,

        @field:Schema(description = "idm - device idx")
        val deviceId: Long? = null,
    )
}

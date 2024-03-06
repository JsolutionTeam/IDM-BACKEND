package kr.co.jsol.domain.companysubsidy.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.jsol.domain.telecom.entity.enums.DiscountType
import kr.co.jsol.domain.telecom.entity.enums.OpenType
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

@Schema(name = "회사 지원금 중복 확인 요청")
data class ExistsCompanySubsidyDto(
    @field:NotNull(message = "업체 아이디는 필수입니다.")
    @field:Min(value = 1, message = "업체 아이디는 1 이상이어야 합니다.")
    @field:Schema(description = "업체 아이디")
    var shopId: Long,

    @field:NotNull(message = "개통 유형은 필수입니다.")
    @field:Schema(description = "개통 유형", implementation = OpenType::class)
    var openType: OpenType?,

    @field:NotNull(message = "선약/공시 할인 유형은 필수입니다.")
    @field:Schema(description = "선약/공시 할인 유형", implementation = DiscountType::class)
    val discountType: DiscountType,

    @field:NotNull(message = "통신사 아이디는 필수입니다.")
    @field:Min(value = 1, message = "통신사 아이디는 1 이상이어야 합니다.")
    @field:Schema(description = "통신사 아이디")
    var telecomId: Long,

    @field:NotNull(message = "단말 아이디는 필수입니다.")
    @field:Min(value = 1, message = "단말 아이디는 1 이상이어야 합니다.")
    @field:Schema(description = "단말 아이디")
    var deviceId: Long,

    @field:NotNull(message = "요금제 아이디는 필수입니다.")
    @field:Min(value = 1, message = "요금제 아이디는 1 이상이어야 합니다.")
    @field:Schema(description = "요금제 아이디")
    var phonePlanId: Long,
)

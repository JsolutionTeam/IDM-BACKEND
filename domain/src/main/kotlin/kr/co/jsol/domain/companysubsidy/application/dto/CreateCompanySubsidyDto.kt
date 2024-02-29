package kr.co.jsol.domain.companysubsidy.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.jsol.domain.companysubsidy.entity.CompanySubsidy
import kr.co.jsol.domain.device.entity.Device
import kr.co.jsol.domain.phoneplan.entity.PhonePlan
import kr.co.jsol.domain.shop.entity.Shop
import kr.co.jsol.domain.telecom.entity.Telecom
import kr.co.jsol.domain.telecom.entity.enums.DiscountType
import kr.co.jsol.domain.telecom.entity.enums.OpenType
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

@Schema(name = "회사 지원금 등록 요청")
data class CreateCompanySubsidyDto(
    @field:NotNull(message = "개통 유형은 필수입력입니다.")
    @field:Schema(description = "개통 유형", implementation = OpenType::class)
    val openType: OpenType,

    @field:NotNull(message = "선약/공시 할인 유형은 필수입력입니다.")
    @field:Schema(description = "선약/공시 할인 유형", implementation = DiscountType::class)
    val discountType: DiscountType,

    @field:NotNull(message = "공시 지원금 가격은 필수입력입니다.")
    @field:Min(value = 0, message = "공시 지원금 가격은 0원 이상이어야 합니다.")
    @field:Schema(description = "공시 지원금 가격")
    val price: Long,

    @field:NotNull(message = "idm - shop idx는 필수입력입니다.")
    @field:Min(value = 1, message = "idm - shop idx는 1 이상이어야 합니다.")
    @field:Schema(description = "idm - shop idx")
    val shopId: Long,

    @field:NotNull(message = "idm - telecom idx는 필수입력입니다.")
    @field:Min(value = 1, message = "idm - telecom idx는 1 이상이어야 합니다.")
    @field:Schema(description = "idm - telecom idx")
    val telecomId: Long,

    @field:NotNull(message = "mcall - phone-plan id는 필수입력입니다.")
    @field:Min(value = 1, message = "mcall - phone-plan id는 1 이상이어야 합니다.")
    @field:Schema(description = "mcall - phone-plan id")
    val phonePlanId: Long,

    @field:NotNull(message = "idm - device idx는 필수입력입니다.")
    @field:Min(value = 1, message = "idm - device idx는 1 이상이어야 합니다.")
    @field:Schema(description = "idm - device idx")
    val deviceId: Long,
) {

    fun toEntity(
        telecom: Telecom,
        phonePlan: PhonePlan,
        device: Device,
        shop: Shop,
    ) = CompanySubsidy(
        openType = openType,
        discountType = discountType,
        price = price,
        shop = shop,
        telecom = telecom,
        phonePlan = phonePlan,
        device = device,
    )
}

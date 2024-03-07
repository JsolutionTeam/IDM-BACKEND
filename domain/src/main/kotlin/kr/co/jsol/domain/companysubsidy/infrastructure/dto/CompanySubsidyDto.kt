package kr.co.jsol.domain.companysubsidy.infrastructure.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.jsol.domain.companysubsidy.entity.CompanySubsidy
import kr.co.jsol.domain.device.infrastructure.dto.DeviceDto
import kr.co.jsol.domain.phoneplan.infrastructure.dto.PhonePlanDto
import kr.co.jsol.domain.shop.infrastructure.dto.ShopDto
import kr.co.jsol.domain.telecom.entity.enums.DiscountType
import kr.co.jsol.domain.telecom.entity.enums.OpenType
import kr.co.jsol.domain.telecom.infrastructure.dto.TelecomDto

@Schema(name = "회사 지원금 응답")
data class CompanySubsidyDto(
    @field:Schema(description = "회사 지원금 아이디")
    val id: Long,

    @field:Schema(description = "개통 유형")
    val openType: OpenType,

    @field:Schema(description = "선약/공시 할인 유형")
    val discountType: DiscountType,

    @field:Schema(description = "공시 지원금 가격")
    val price: Long,

    @field:Schema(description = "idm - shop")
    val shop: ShopDto,

    @field:Schema(description = "idm - telecom")
    val telecom: TelecomDto,

    @field:Schema(description = "mcall - phone-plan")
    val phonePlan: PhonePlanDto,

    @field:Schema(description = "idm - device")
    val device: DeviceDto,
) {

    constructor(companySubsidy: CompanySubsidy) : this(
        id = companySubsidy.id,
        openType = companySubsidy.openType,
        discountType = companySubsidy.discountType,
        price = companySubsidy.price,
        telecom = TelecomDto(companySubsidy.telecom),
        phonePlan = PhonePlanDto(companySubsidy.phonePlan),
        device = DeviceDto(companySubsidy.device),
        shop = ShopDto(companySubsidy.shop),
    )
}

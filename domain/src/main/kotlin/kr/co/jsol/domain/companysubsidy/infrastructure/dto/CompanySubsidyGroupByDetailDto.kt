package kr.co.jsol.domain.companysubsidy.infrastructure.dto

import com.querydsl.core.annotations.QueryProjection
import io.swagger.v3.oas.annotations.media.Schema
import kr.co.jsol.domain.device.infrastructure.dto.DeviceRawDto
import kr.co.jsol.domain.phoneplan.infrastructure.dto.PhonePlanRawDto
import kr.co.jsol.domain.shop.infrastructure.dto.ShopSimpleDto
import kr.co.jsol.domain.telecom.entity.enums.DiscountType
import kr.co.jsol.domain.telecom.entity.enums.OpenType
import kr.co.jsol.domain.telecom.infrastructure.dto.TelecomDto

@Schema(name = "회사 지원금 페이지 조회 DTO")
data class CompanySubsidyGroupByDetailDto @QueryProjection constructor(
    @field:Schema(description = "idm - telecom")
    val telecom: TelecomDto,

    @field:Schema(description = "mcall - phone-plan")
    val phonePlan: PhonePlanRawDto,

    @field:Schema(description = "idm - device")
    val device: DeviceRawDto,

    @field:Schema(description = "idm - shop")
    val shop: ShopSimpleDto,

    var detailList: List<Detail>,
) {

    @QueryProjection
    constructor(
        telecom: TelecomDto,
        phonePlan: PhonePlanRawDto,
        device: DeviceRawDto,
        shop: ShopSimpleDto,
    ) : this(
        telecom = telecom,
        phonePlan = phonePlan,
        device = device,
        shop = shop,
        detailList = emptyList(),
    )

    @Schema(name = "회사 지원금 상세 DTO")
    data class Detail @QueryProjection constructor(
        @field:Schema(description = "회사 지원금 아이디")
        val id: Long,

        @field:Schema(description = "공시 지원금 가격")
        val price: Long,

        @field:Schema(description = "개통 유형")
        val openType: OpenType,

        @field:Schema(description = "선약/공시 할인 유형")
        val discountType: DiscountType,
    )
}

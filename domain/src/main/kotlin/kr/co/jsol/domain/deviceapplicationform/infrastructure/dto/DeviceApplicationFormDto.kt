package kr.co.jsol.domain.deviceapplicationform.infrastructure.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.jsol.domain.telecom.entity.enums.DiscountType
import kr.co.jsol.domain.telecom.entity.enums.OpenType

@Schema(name = "단말 신청서 응답")
data class DeviceApplicationFormDto(
    @field:Schema(description = "단말 신청서 아이디")
    var id: Long,

    @field:Schema(description = "신청자 전화번호")
    var phoneNumber: String,

    @field:Schema(description = "개통 유형", implementation = OpenType::class)
    var openType: OpenType,

    @field:Schema(description = "선약/공시 할인 유형", implementation = DiscountType::class)
    var discountType: DiscountType,

    @field:Schema(description = "회사 지원금 금액")
    var companySubsidyPrice: Long,

    @field:Schema(description = "유심 신규/변경 여부, true: 유심 변경, false: 기존 유심 사용", implementation = Boolean::class)
    var usimChange: Boolean,

    @field:Schema(description = "약정 할부 개월 수")
    var contractMonth: Int,

    @field:Schema(description = "공시 지원금 금액")
    var deviceSubsidyPrice: Long,

    @field:Schema(description = "현금 선입 금액")
    var prePaymentPrice: Long,

    @field:Schema(description = "단말 할부 개월 수")
    var installmentMonth: Int,

    @field:Schema(description = "메모")
    var memo: String,

    @field:Schema(description = "통신사 아이디")
    var telecomId: Long,

    @field:Schema(description = "단말 상세 아이디")
    var deviceInfoId: Long,

    @field:Schema(description = "요금제 아이디")
    var phonePlanId: Long,

    @field:Schema(description = "보험 아이디")
    var insuranceId: Long,

    @field:Schema(description = "부가서비스 아이디")
    var subserviceId: Long,
)

package kr.co.jsol.domain.deviceapplicationform.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.jsol.domain.telecom.entity.enums.DiscountType
import kr.co.jsol.domain.telecom.entity.enums.OpenType
import javax.validation.constraints.Min
import javax.validation.constraints.Size

@Schema(name = "단말 신청서 수정 요청")
data class UpdateDeviceApplicationFormDto(
    @field:Min(value = 1, message = "단말 신청서 아이디는 1 이상으로 입력해주세요.")
    @field:Schema(description = "단말 신청서 아이디")
    var id: Long,

    @field:Size(min = 4, max = 255, message = "신청자 전화번호는 4자 이상 255자 이하로 입력해주세요.")
    @field:Schema(description = "신청자 전화번호")
    var phoneNumber: String? = null,

    @field:Schema(description = "개통 유형", implementation = OpenType::class)
    var openType: OpenType? = null,

    @field:Schema(description = "선약/공시 할인 유형", implementation = DiscountType::class)
    var discountType: DiscountType? = null,

    @field:Min(value = 0, message = "회사 지원금 금액은 0 이상으로 입력해주세요.")
    @field:Schema(description = "회사 지원금 금액")
    var companySubsidyPrice: Long? = null,

    @field:Schema(description = "유심 신규/변경 여부, true: 유심 변경, false: 기존 유심 사용", implementation = Boolean::class)
    var usimChange: Boolean? = null,

    @field:Min(value = 0, message = "약정 할부 개월 수는 0 이상으로 입력해주세요.")
    @field:Schema(description = "약정 할부 개월 수")
    var contractMonth: Int? = null,

    @field:Min(value = 0, message = "공시 지원금 금액은 0 이상으로 입력해주세요.")
    @field:Schema(description = "공시 지원금 금액")
    var deviceSubsidyPrice: Long? = null,

    @field:Min(value = 0, message = "현금 선입 금액은 0 이상으로 입력해주세요.")
    @field:Schema(description = "현금 선입 금액")
    var prePaymentPrice: Long? = null,

    @field:Min(value = 0, message = "단말 할부 개월 수는 0 이상으로 입력해주세요.")
    @field:Schema(description = "단말 할부 개월 수")
    var installmentMonth: Int? = null,

    @field:Size(max = 255, message = "메모는 255자 이하로 입력해주세요.")
    @field:Schema(description = "메모")
    var memo: String? = null,

    @field:Min(value = 1, message = "통신사 아이디는 1 이상으로 입력해주세요.")
    @field:Schema(description = "통신사 아이디")
    var telecomId: Long? = null,

    @field:Min(value = 1, message = "단말 상세 아이디는 1 이상으로 입력해주세요.")
    @field:Schema(description = "단말 상세 아이디")
    var deviceInfoId: Long? = null,

    @field:Min(value = 1, message = "요금제 아이디는 1 이상으로 입력해주세요.")
    @field:Schema(description = "요금제 아이디")
    var phonePlanId: Long? = null,

    @field:Min(value = 1, message = "보험 아이디는 1 이상으로 입력해주세요.")
    @field:Schema(description = "보험 아이디")
    var insuranceId: Long? = null,

    @field:Min(value = 1, message = "부가서비스 아이디는 1 이상으로 입력해주세요.")
    @field:Schema(description = "부가서비스 아이디")
    var subserviceId: Long? = null,
)

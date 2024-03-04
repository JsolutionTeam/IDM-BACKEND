package kr.co.jsol.domain.deviceapplicationform.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.jsol.domain.deviceapplicationform.entity.DeviceApplicationForm
import kr.co.jsol.domain.deviceinfo.entity.DeviceInfo
import kr.co.jsol.domain.insurance.entity.Insurance
import kr.co.jsol.domain.phoneplan.entity.PhonePlan
import kr.co.jsol.domain.shop.entity.Shop
import kr.co.jsol.domain.subservice.entity.Subservice
import kr.co.jsol.domain.telecom.entity.Telecom
import kr.co.jsol.domain.telecom.entity.enums.DiscountType
import kr.co.jsol.domain.telecom.entity.enums.OpenType
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Schema(name = "단말 신청서 등록 요청")
data class CreateDeviceApplicationFormDto(
    @field:NotBlank(message = "신청자 전화번호는 필수입니다.")
    @field:Size(min = 4, max = 255, message = "신청자 전화번호는 4자 이상 255자 이하로 입력해주세요.")
    @field:Schema(description = "신청자 전화번호")
    var phoneNumber: String,

    @field:NotNull(message = "개통 유형은 필수입니다.")
    @field:Schema(description = "개통 유형", implementation = OpenType::class)
    var openType: OpenType,

    @field:NotNull(message = "선약/공시 할인 유형은 필수입니다.")
    @field:Schema(description = "선약/공시 할인 유형", implementation = DiscountType::class)
    var discountType: DiscountType,

    @field:NotNull(message = "회사 지원금 금액은 필수입니다.")
    @field:Min(value = 0, message = "회사 지원금 금액은 0 이상으로 입력해주세요.")
    @field:Schema(description = "회사 지원금 금액")
    var companySubsidyPrice: Long,

    @field:NotNull(message = "유심 신규/변경 여부는 필수입니다.")
    @field:Schema(description = "유심 신규/변경 여부, true: 유심 변경, false: 기존 유심 사용", implementation = Boolean::class)
    var usimChange: Boolean,

    @field:NotNull(message = "약정 할부 개월 수는 필수입니다.")
    @field:Min(value = 0, message = "약정 할부 개월 수는 0 이상으로 입력해주세요.")
    @field:Schema(description = "약정 할부 개월 수")
    var contractMonth: Int,

    @field:NotNull(message = "공시 지원금 금액은 필수입니다.")
    @field:Min(value = 0, message = "공시 지원금 금액은 0 이상으로 입력해주세요.")
    @field:Schema(description = "공시 지원금 금액")
    var deviceSubsidyPrice: Long,

    @field:NotNull(message = "현금 선입 금액은 필수입니다.")
    @field:Min(value = 0, message = "현금 선입 금액은 0 이상으로 입력해주세요.")
    @field:Schema(description = "현금 선입 금액")
    var prePaymentPrice: Long,

    @field:NotNull(message = "단말 할부 개월 수는 필수입니다.")
    @field:Min(value = 0, message = "단말 할부 개월 수는 0 이상으로 입력해주세요.")
    @field:Schema(description = "단말 할부 개월 수")
    var installmentMonth: Int,

    @field:NotNull(message = "메모는 필수입니다.")
    @field:Size(max = 255, message = "메모는 255자 이하로 입력해주세요.")
    @field:Schema(description = "메모")
    var memo: String,

    @field:NotNull(message = "통신사 아이디는 필수입니다.")
    @field:Min(value = 1, message = "통신사 아이디는 1 이상으로 입력해주세요.")
    @field:Schema(description = "통신사 아이디")
    var telecomId: Long,

    @field:NotNull(message = "단말 상세 아이디는 필수입니다.")
    @field:Min(value = 1, message = "단말 상세 아이디는 1 이상으로 입력해주세요.")
    @field:Schema(description = "단말 상세 아이디")
    var deviceInfoId: Long,

    @field:NotNull(message = "요금제 아이디는 필수입니다.")
    @field:Min(value = 1, message = "요금제 아이디는 1 이상으로 입력해주세요.")
    @field:Schema(description = "요금제 아이디")
    var phonePlanId: Long,

    @field:NotNull(message = "보험 아이디는 필수입니다.")
    @field:Min(value = 1, message = "보험 아이디는 1 이상으로 입력해주세요.")
    @field:Schema(description = "보험 아이디")
    var insuranceId: Long,

    @field:NotNull(message = "부가서비 아이디는 필수입니다.")
    @field:Min(value = 1, message = "부가서비스 아이디는 1 이상으로 입력해주세요.")
    @field:Schema(description = "부가서비스 아이디")
    var subserviceId: Long,
) {
    // shopId는 로그인한 사용자의 업체 아이디를 받아올 예정
    var shopId: Long = 0L

    fun toEntity(
        shop: Shop,
        telecom: Telecom,
        deviceInfo: DeviceInfo,
        phonePlan: PhonePlan,
        insurance: Insurance,
        subservice: Subservice,
    ): DeviceApplicationForm {
        return DeviceApplicationForm(
            phoneNumber = phoneNumber,
            openType = openType,
            discountType = discountType,
            companySubsidyPrice = companySubsidyPrice,
            usimChange = usimChange,
            contractMonth = contractMonth,
            deviceSubsidyPrice = deviceSubsidyPrice,
            prePaymentPrice = prePaymentPrice,
            installmentMonth = installmentMonth,
            memo = memo,
            shop = shop,
            telecom = telecom,
            deviceInfo = deviceInfo,
            phonePlan = phonePlan,
            insurance = insurance,
            subservice = subservice,
        )
    }
}

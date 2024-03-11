package kr.co.jsol.domain.deviceapplicationform.infrastructure.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import io.swagger.v3.oas.annotations.media.Schema
import kr.co.jsol.domain.deviceapplicationform.entity.DeviceApplicationForm
import kr.co.jsol.domain.deviceapplicationform.entity.DeviceApplicationFormSubservice
import kr.co.jsol.domain.deviceinfo.infrastructure.dto.DeviceInfoDto
import kr.co.jsol.domain.insurance.infrastructure.dto.InsuranceDto
import kr.co.jsol.domain.phoneplan.infrastructure.dto.PhonePlanDto
import kr.co.jsol.domain.shop.entity.Shop
import kr.co.jsol.domain.subservice.infrastructure.dto.SubserviceDto
import kr.co.jsol.domain.telecom.entity.enums.DiscountType
import kr.co.jsol.domain.telecom.entity.enums.OpenType
import kr.co.jsol.domain.telecom.infrastructure.dto.TelecomDto

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

    @field:Schema(hidden = true)
    @field:JsonIgnore
    val shop: Shop,

    @field:Schema(description = "통신사 아이디")
    var telecom: TelecomDto,

    @field:Schema(description = "단말 상세 아이디")
    var deviceInfo: DeviceInfoDto,

    @field:Schema(description = "요금제 아이디")
    var phonePlan: PhonePlanDto,

    @field:Schema(description = "보험 아이디")
    var insurance: InsuranceDto?,

    @field:Schema(description = "부가서비스 아이디")
    var subserviceList: List<SubserviceDto>,
) {

    constructor(
        deviceApplicationForm: DeviceApplicationForm,
        deviceApplicationFormSubserviceList: List<DeviceApplicationFormSubservice>,
    ) : this(
        id = deviceApplicationForm.id,
        phoneNumber = deviceApplicationForm.phoneNumber,
        openType = deviceApplicationForm.openType,
        discountType = deviceApplicationForm.discountType,
        companySubsidyPrice = deviceApplicationForm.companySubsidyPrice,
        usimChange = deviceApplicationForm.usimChange,
        contractMonth = deviceApplicationForm.contractMonth,
        deviceSubsidyPrice = deviceApplicationForm.deviceSubsidyPrice,
        prePaymentPrice = deviceApplicationForm.prePaymentPrice,
        installmentMonth = deviceApplicationForm.installmentMonth,
        memo = deviceApplicationForm.memo,
        shop = deviceApplicationForm.shop,
        telecom = TelecomDto(deviceApplicationForm.telecom),
        deviceInfo = DeviceInfoDto(deviceApplicationForm.deviceInfo),
        phonePlan = PhonePlanDto(deviceApplicationForm.phonePlan),
        insurance = deviceApplicationForm.insurance?.let { InsuranceDto(it) },
        subserviceList = deviceApplicationFormSubserviceList.map {
            SubserviceDto(it.subservice)
        },
    )
}

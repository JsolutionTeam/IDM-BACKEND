package kr.co.jsol.domain.deviceapplicationform.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.jsol.domain.telecom.entity.enums.DiscountType
import kr.co.jsol.domain.telecom.entity.enums.OpenType
import org.springdoc.api.annotations.ParameterObject
import javax.validation.constraints.Min
import javax.validation.constraints.Size

@ParameterObject
@Schema(name = "단말 신청서 조회 요청")
data class GetDeviceApplicationFormsDto(
    @field:Schema(description = "업체 아이디, MASTER 계정만 입력해서 사용 가능")
    var shopId: Long? = null,

    @field:Size(max = 255, message = "신청자 전화번호는 255자 이하로 입력해주세요.")
    @field:Schema(description = "신청자 전화번호")
    var phoneNumber: String? = null,

    @field:Schema(description = "개통 유형", implementation = OpenType::class)
    var openType: OpenType? = null,

    @field:Schema(description = "선약/공시 할인 유형", implementation = DiscountType::class)
    var discountType: DiscountType? = null,

    @field:Min(value = 0, message = "최소 회사 지원금 금액은 0 이상으로 입력해주세요.")
    @field:Schema(description = "최소 회사 지원금 금액")
    var companySubsidyPriceMin: Long? = null,

    @field:Min(value = 0, message = "최대 회사 지원금 금액은 0 이상으로 입력해주세요.")
    @field:Schema(description = "최대 회사 지원금 금액")
    var companySubsidyPriceMax: Long? = null,

    @field:Schema(description = "유심 신규/변경 여부, true: 유심 변경, false: 기존 유심 사용", implementation = Boolean::class)
    var usimChange: Boolean? = null,

    @field:Min(value = 0, message = "최소 약정 할부 개월 수는 0 이상으로 입력해주세요.")
    @field:Schema(description = "최소 약정 할부 개월 수")
    var contractMonthMin: Int? = null,

    @field:Min(value = 0, message = "최대 약정 할부 개월 수는 0 이상으로 입력해주세요.")
    @field:Schema(description = "최대 약정 할부 개월 수")
    var contractMonthMax: Int? = null,

    @field:Min(value = 0, message = "최소 공시 지원금 금액은 0 이상으로 입력해주세요.")
    @field:Schema(description = "최소 공시 지원금 금액")
    var deviceSubsidyPriceMin: Long? = null,

    @field:Min(value = 0, message = "최대 공시 지원금 금액은 0 이상으로 입력해주세요.")
    @field:Schema(description = "최대 공시 지원금 금액")
    var deviceSubsidyPriceMax: Long? = null,

    @field:Min(value = 0, message = "최소 현금 선입 금액은 0 이상으로 입력해주세요.")
    @field:Schema(description = "최소 현금 선입 금액")
    var prePaymentPriceMin: Long? = null,

    @field:Min(value = 0, message = "최대 현금 선입 금액은 0 이상으로 입력해주세요.")
    @field:Schema(description = "최대 현금 선입 금액")
    var prePaymentPriceMax: Long? = null,

    @field:Min(value = 0, message = "최소 단말 할부 개월 수는 0 이상으로 입력해주세요.")
    @field:Schema(description = "최소 단말 할부 개월 수")
    var installmentMonthMin: Int? = null,

    @field:Min(value = 0, message = "최대 단말 할부 개월 수는 0 이상으로 입력해주세요.")
    @field:Schema(description = "최대 단말 할부 개월 수")
    var installmentMonthMax: Int? = null,

    @field:Size(max = 255, message = "메모는 255자 이하로 입력해주세요.")
    @field:Schema(description = "메모")
    var memo: String? = null,

    @field:Size(max = 255, message = "통신사 이름은 255자 이하으로 입력해주세요.")
    @field:Schema(description = "통신사 이름")
    var telecomName: String? = null,

    @field:Size(max = 255, message = "단말 모델네임은 255자 이하으로 입력해주세요.")
    @field:Schema(description = "단말 모델네임")
    var deviceModelName: String? = null,

    @field:Size(max = 255, message = "단말 펫네임은 255자 이하으로 입력해주세요.")
    @field:Schema(description = "단말 펫네임")
    var devicePetName: String? = null,

    @field:Size(max = 255, message = "요금제 이름은 255자 이하으로 입력해주세요.")
    @field:Schema(description = "요금제 이름")
    var phonePlanName: String? = null,

    @field:Size(max = 255, message = "보험 이름은 255자 이하으로 입력해주세요.")
    @field:Schema(description = "보험 이름")
    var insuranceName: String? = null,

    @field:Size(max = 255, message = "부가서비스 이름은 255자 이하로 입력해주세요.")
    @field:Schema(description = "부가서비스 이름")
    var subserviceName: String? = null,
)

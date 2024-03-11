package kr.co.jsol.domain.companysubsidy.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.jsol.domain.telecom.entity.enums.DiscountType
import kr.co.jsol.domain.telecom.entity.enums.OpenType
import org.springdoc.api.annotations.ParameterObject
import javax.validation.constraints.Min

@ParameterObject
@Schema(name = "회사 지원금 페이지 조회 요청")
data class GetCompanySubsidiesDto(
    @field:Min(value = 1, message = "idm - shop idx는 1 이상이어야 합니다.")
    @field:Schema(description = "업체 아이디, MASTER 권한만 적용. MASTER권한이 null로 입력시 모든 업체를 조회하고 숫자 입력시 해당 업체만 조회한다. 그 외는 요청자 업체 아이디로 조회된다.")
    var shopId: Long? = null,

    @field:Schema(description = "개통 유형", implementation = OpenType::class)
    val openType: OpenType? = null,

    @field:Schema(description = "선약/공시 할인 유형", implementation = DiscountType::class)
    val discountType: DiscountType? = null,

    @field:Min(value = 0, message = "최소 회사 지원금 가격은 0원 이상이어야 합니다.")
    @field:Schema(description = "최소 회사 지원금 가격")
    val priceMin: Long? = null,

    @field:Min(value = 0, message = "최대 회사 지원금 가격은 0원 이상이어야 합니다.")
    @field:Schema(description = "최대 회사 지원금 가격")
    val priceMax: Long? = null,

    @field:Min(value = 1, message = "idm - telecom idx는 1 이상이어야 합니다.")
    @field:Schema(description = "idm - telecom idx")
    val telecomId: Long? = null,

    @field:Min(value = 1, message = "mcall - phone-plan id는 1 이상이어야 합니다.")
    @field:Schema(description = "mcall - phone-plan id")
    val phonePlanId: Long? = null,

    @field:Min(value = 1, message = "idm - device idx는 1 이상이어야 합니다.")
    @field:Schema(description = "idm - device idx")
    val deviceId: Long? = null,
)

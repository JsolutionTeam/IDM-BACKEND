package kr.co.jsol.domain.insurance.infrastructure.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.jsol.domain.telecom.infrastructure.dto.TelecomDto

@Schema(name = "보험 응답")
data class InsuranceDto(
    @field:Schema(description = "보험 아이디")
    var id: Long,

    @field:Schema(description = "보험 이름")
    var name: String,

    @field:Schema(description = "보험 가격")
    var price: Long,

    @field:Schema(description = "보상 대상 설명")
    var targetExp: String,

    @field:Schema(description = "보상 범위 설명")
    var rangeExp: String,

    @field:Schema(description = "보상 한도(최대 보상) 설명")
    var limitExp: String,

    @field:Schema(description = "자기부담금 설명")
    var selfPriceExp: String,

    @field:Schema(description = "추가 보상 설명")
    var plusRewardExp: String,

    @field:Schema(description = "기타")
    var etc: String,

    @field:Schema(description = "통신사")
    var telecom: TelecomDto,
) {

    constructor(insurance: kr.co.jsol.domain.insurance.entity.Insurance) : this(
        id = insurance.id,
        name = insurance.name,
        price = insurance.price,
        targetExp = insurance.targetExp,
        rangeExp = insurance.rangeExp,
        limitExp = insurance.limitExp,
        selfPriceExp = insurance.selfPriceExp,
        plusRewardExp = insurance.plusRewardExp,
        etc = insurance.etc,
        telecom = TelecomDto(insurance.telecom)
    )
}

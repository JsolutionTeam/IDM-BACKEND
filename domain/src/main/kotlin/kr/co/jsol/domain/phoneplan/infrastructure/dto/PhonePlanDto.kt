package kr.co.jsol.domain.phoneplan.infrastructure.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.jsol.domain.phoneplan.entity.PhonePlan
import kr.co.jsol.domain.telecom.infrastructure.dto.TelecomDto

@Schema(name = "요금제 응답")
data class PhonePlanDto(
    @field:Schema(description = "요금제 아이디")
    val id: Long,

    @field:Schema(description = "요금제 이름")
    val name: String,

    @field:Schema(description = "요금제 가격")
    val price: Long,

    @field:Schema(description = "카테고리 ex) 5G일반, LTE일반 등...")
    val category: String,

    @field:Schema(description = "음성통화 설명")
    val cellExp: String,

    @field:Schema(description = "데이터 설명")
    val dataExp: String,

    @field:Schema(description = "문자 설명")
    val mailExp: String,

    @field:Schema(description = "통신사 응답")
    val telecom: TelecomDto,
) {
    
    constructor(phonePlan: PhonePlan) : this(
        id = phonePlan.id,
        name = phonePlan.name,
        price = phonePlan.price,
        category = phonePlan.category,
        cellExp = phonePlan.cellExp,
        dataExp = phonePlan.dataExp,
        mailExp = phonePlan.mailExp,
        telecom = TelecomDto(phonePlan.telecom),
    )
}

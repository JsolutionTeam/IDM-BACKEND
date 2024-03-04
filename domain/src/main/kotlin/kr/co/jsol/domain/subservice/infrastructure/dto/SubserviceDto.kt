package kr.co.jsol.domain.subservice.infrastructure.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.jsol.domain.subservice.entity.Subservice
import kr.co.jsol.domain.telecom.infrastructure.dto.TelecomDto

@Schema(name = "부가서비스 응답")
data class SubserviceDto(
    @field:Schema(description = "부가서비스 아이디")
    var id: Long,

    @field:Schema(description = "이름")
    var name: String,

    @field:Schema(description = "설명")
    var explanation: String,

    @field:Schema(description = "가격")
    var price: Long,

    @field:Schema(description = "포인트 사용량")
    var point: Long,

    var telecom: TelecomDto,
) {

    constructor(subservice: Subservice) : this(
        id = subservice.id,
        name = subservice.name,
        explanation = subservice.explanation,
        price = subservice.price,
        point = subservice.point,
        telecom = TelecomDto(subservice.telecom)
    )
}

package kr.co.jsol.domain.telecom.infrastructure.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.jsol.domain.telecom.entity.Telecom

@Schema(name = "통신사 응답")
data class TelecomDto(
    @field:Schema(description = "통신사 아이디")
    val id: Long,

    @field:Schema(description = "통신사 이름")
    val name: String,

    @field:Schema(description = "통신사 공식 홈페이지")
    val homepage: String,
) {
    constructor(telecom: Telecom) : this(
        id = telecom.id,
        name = telecom.name,
        homepage = telecom.homepage,
    )
}
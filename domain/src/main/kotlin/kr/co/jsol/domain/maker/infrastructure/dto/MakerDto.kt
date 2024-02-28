package kr.co.jsol.domain.maker.infrastructure.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.jsol.domain.maker.entity.Maker

@Schema(name = "제조사 응답")
data class MakerDto(
    @field:Schema(description = "제조사 아이디")
    val id: Long,

    @field:Schema(description = "제조사명")
    val name: String,
) {

    constructor(maker: Maker) : this(
        id = maker.id,
        name = maker.name,
    )
}

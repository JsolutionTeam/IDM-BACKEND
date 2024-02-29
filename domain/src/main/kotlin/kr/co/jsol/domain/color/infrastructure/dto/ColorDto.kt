package kr.co.jsol.domain.color.infrastructure.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.jsol.domain.color.entity.Color

@Schema(name = "색상 응답")
data class ColorDto(
    @field:Schema(description = "색상 아이디")
    val id: Long,

    @field:Schema(description = "색상명")
    val name: String,

    @field:Schema(description = "색상 영문명")
    var nameEng: String,

    @field:Schema(description = "색상 코드[사용자 사용하기 나름] ex) hex값, rgb값")
    var displayValue: String = "",
) {

    constructor(color: Color) : this(
        id = color.id,
        name = color.name,
        nameEng = color.nameEng,
        displayValue = color.displayValue,
    )
}

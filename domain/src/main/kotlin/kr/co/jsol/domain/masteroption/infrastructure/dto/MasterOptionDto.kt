package kr.co.jsol.domain.masteroption.infrastructure.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.jsol.domain.masteroption.entity.MasterOption

@Schema(name = "마스터 옵션 조회 응답")
class MasterOptionDto(
    @field:Schema(description = "마스터 옵션 ID")
    val id: Long,

    @field:Schema(description = "마스터 옵션 명")
    val name: String,

    @field:Schema(description = "노출 여부", implementation = Boolean::class)
    val isShow: Boolean,
) {

    constructor(masterOption: MasterOption) : this(
        id = masterOption.id,
        name = masterOption.name,
        isShow = masterOption.isShow,
    )
}

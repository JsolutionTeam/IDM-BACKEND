package kr.co.jsol.domain.masteroption.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.jsol.domain.masteroption.entity.MasterOption
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Schema(name = "마스터 옵션 등록 요청")
class CreateMasterOptionDto(
    @field:NotNull(message = "옵션 명은 필수 입력값입니다.")
    @field:Size(max = 50, message = "옵션 명은 50자 이내로 입력해야 합니다.")
    @field:Schema(description = "옵션 명")
    val name: String,

    @field:NotNull(message = "노출 여부는 필수 입력값입니다.")
    @field:Schema(description = "노출 여부", implementation = Boolean::class)
    val isShow: Boolean,
) {

    fun toEntity() = MasterOption(
        name = name,
        isShow = isShow,
    )
}

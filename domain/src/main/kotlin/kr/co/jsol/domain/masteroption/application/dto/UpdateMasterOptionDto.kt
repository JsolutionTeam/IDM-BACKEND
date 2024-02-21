package kr.co.jsol.domain.masteroption.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.jsol.domain.masteroption.entity.MasterOption
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Schema(name = "마스터 옵션 수정 요청")
class UpdateMasterOptionDto(
    @field:NotNull(message = "마스터 옵션 ID는 필수 입력값입니다.")
    @field:Min(value = 1, message = "마스터 옵션 ID는 1 이상 입력해야 합니다.")
    @field:Schema(description = "마스터 옵션 ID")
    val id: Long,

    @field:Size(max = 50, message = "마스터 옵션 명은 50자 이내로 입력해야 합니다.")
    @field:Schema(description = "마스터 옵션 명")
    val name: String? = null,

    @field:Schema(description = "노출 여부", implementation = Boolean::class)
    val isShow: Boolean? = null,
) {

    fun updateEntity(entity: MasterOption) {
        entity.apply {
            this@UpdateMasterOptionDto.name?.let { newName ->
                this.name = newName
            }
            this@UpdateMasterOptionDto.isShow?.let { newIsShow ->
                this.isShow = newIsShow
            }
        }
    }
}

package kr.co.jsol.domain.shop.application.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import io.swagger.v3.oas.annotations.media.Schema
import org.springdoc.api.annotations.ParameterObject
import javax.validation.constraints.Size

@ParameterObject
@Schema(name = "업체 계정(사용자) 조회")
data class GetShopAccountsDto(
    @field:Schema(description = "이름", example = "권세기")
    var name: String? = null,

    @field:Schema(description = "관리자 여부", implementation = Boolean::class)
    var isManager: Boolean? = null,

    @field:Size(max = 20, message = "전화번호는 20자 이내로 입력해주세요.")
    @field:Schema(description = "전화번호", example = "010-0000-0000")
    var phone: String? = null,
) {

    @Schema(hidden = true)
    @JsonIgnore
    var shopId: Long? = null
}

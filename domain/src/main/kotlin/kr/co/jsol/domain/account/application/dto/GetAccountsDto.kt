package kr.co.jsol.domain.account.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import org.springdoc.api.annotations.ParameterObject
import javax.validation.constraints.Size

@ParameterObject
@Schema(name = "계정 조회 요청")
data class GetAccountsDto(
    @field:Size(min = 6, max = 20, message = "아이디는 6~20자 이내로 입력해야 합니다.")
    @field:Schema(description = "계정 아이디[로그인시 사용]")
    val id: String? = null,

    @field:Size(min = 2, max = 20, message = "이름은 2~20자 이내로 입력해야 합니다.")
    @field:Schema(description = "이름")
    val name: String? = null,

    @field:Size(max = 20, message = "전화번호는 20자 이내로 입력해주세요.")
    @field:Schema(description = "전화번호")
    val phone: String? = null,

    @field:Schema(description = "업체 아이디, 해당 업체의 정보를 조회하나 JSOL이 아닐 경우 해당 업체 아이디로 조회함")
    var shopId: Long? = null,
)


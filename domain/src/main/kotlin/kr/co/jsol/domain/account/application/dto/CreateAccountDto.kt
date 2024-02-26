package kr.co.jsol.domain.account.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.jsol.common.domain.Authority
import kr.co.jsol.domain.account.entity.Account
import kr.co.jsol.domain.shop.entity.Shop
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Schema(name = "계정 생성 요청")
data class CreateAccountDto(
    @field:NotBlank(message = "아이디는 필수 입력값입니다.")
    @field:Size(min = 6, max = 20, message = "아이디는 6~20자 이내로 입력해야 합니다.")
    @field:Schema(description = "계정 아이디[로그인시 사용]")
    val id: String,

    @field:NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @field:Size(min = 8, max = 20, message = "비밀번호는 8~20자 이내로 입력해야 합니다.")
    @field:Schema(description = "비밀번호")
    val password: String,

    @field:NotBlank(message = "이름은 필수 입력값입니다.")
    @field:Size(min = 2, max = 20, message = "이름은 2~20자 이내로 입력해야 합니다.")
    @field:Schema(description = "이름")
    val name: String,

    @field:NotEmpty(message = "권한은 필수 입력값입니다.")
    @field:Schema(description = "권한, ROLE_로 시작하지 않으면 자동으로 ROLE_가 붙습니다.", implementation = Authority::class)
    val role: Authority,

    @field:Schema(description = "전화번호, 없을 경우 null로 입력해주세요.")
    val phone: String? = null,

    @field:NotNull(message = "업체 아이디는 필수 입력값입니다.")
    @field:Min(value = 1, message = "업체 아이디는 1이상의 값이어야 합니다.")
    @field:Schema(description = "업체 아이디")
    val shopId: Long,
) {
    fun toMap(): Map<String, String> {
        return mapOf(
            "id" to id,
            "password" to password,
            "name" to name,
            "role" to role.toString(),
        )
    }

    fun toEntity(shop: Shop): Account {
        return Account(
            id = this.id,
            name = name,
            role = role,
            phone = phone ?: "",
            shop = shop,
        )
    }
}


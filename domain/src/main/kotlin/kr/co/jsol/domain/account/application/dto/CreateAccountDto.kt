package kr.co.jsol.domain.account.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.jsol.common.domain.AccountAuthority
import kr.co.jsol.domain.account.entity.Account
import kr.co.jsol.domain.auth.application.dto.AuthCreateUserDto
import kr.co.jsol.domain.shop.entity.Shop
import javax.validation.constraints.NotBlank
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

    @field:NotNull(message = "권한은 필수 입력값입니다.")
    @field:Schema(description = "권한", implementation = AccountAuthority::class)
    val role: AccountAuthority,

    @field:NotNull(message = "관리자 여부는 필수 입력값입니다.")
    @field:Schema(description = "관리자 여부")
    val isManager: Boolean = false,

    @field:Schema(description = "전화번호, 없을 경우 null로 입력해주세요.")
    val phone: String? = null,

    @field:Schema(description = "업체 아이디, JSOL일 경우 설정 가능하며 아닌 경우 업체는 따라감")
    var shopId: Long?,
) {

    fun toAuthDto(): AuthCreateUserDto {
        return AuthCreateUserDto(
            id = id,
            password = password,
            name = name,
            role = role.toString(),
        )
    }

    fun toEntity(shop: Shop): Account {
        return Account(
            id = this.id,
            name = name,
            role = role.toString(),
            isManager = isManager,
            phone = phone ?: "",
            shop = shop,
        )
    }
}


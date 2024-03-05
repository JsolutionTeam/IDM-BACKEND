package kr.co.jsol.domain.account.infrastructure.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.jsol.domain.account.entity.Account
import kr.co.jsol.domain.shop.infrastructure.dto.ShopDto

@Schema(name = "계정 응답")
data class AccountDto(
    @field:Schema(description = "아이디", example = "js")
    val id: String,

    @field:Schema(description = "이름", example = "권세기")
    val name: String,

    @field:Schema(description = "권한")
    val role: String,

    @field:Schema(description = "관리자 여부", implementation = Boolean::class)
    val isManager: Boolean,

    @field:Schema(description = "연락처", example = "010-0000-0000")
    val phone: String,

    @field:Schema(description = "업체 정보")
    val shop: ShopDto,
) {

    constructor(account: Account) : this(
        id = account.id,
        name = account.name,
        role = account.role,
        isManager = account.isManager,
        phone = account.phone,
        shop = ShopDto(account.shop)
    )
}

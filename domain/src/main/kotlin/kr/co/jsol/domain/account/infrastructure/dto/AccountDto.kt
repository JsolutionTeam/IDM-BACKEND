package kr.co.jsol.domain.account.infrastructure.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.jsol.domain.account.entity.Account
import kr.co.jsol.domain.account.entity.enums.AccountRole
import kr.co.jsol.domain.shop.infrastructure.dto.ShopDto

@Schema(name = "계정 응답")
data class AccountDto(
    @field:Schema(description = "아이디", example = "js")
    var id: String,

    @field:Schema(description = "이름", example = "권세기")
    var name: String,

    @field:Schema(description = "권한")
    var role: AccountRole,

    @field:Schema(description = "관리자 여부", implementation = Boolean::class)
    var isManager: Boolean,

    @field:Schema(description = "연락처", example = "010-0000-0000")
    var phone: String,

    @field:Schema(description = "메모", example = "")
    var memo: String,

    @field:Schema(description = "업체 정보")
    var shop: ShopDto,
) {

    constructor(account: Account) : this(
        id = account.id,
        name = account.name,
        role = AccountRole.of(account.role),
        isManager = account.isManager,
        phone = account.phone,
        memo = account.memo,
        shop = ShopDto(account.shop)
    )
}

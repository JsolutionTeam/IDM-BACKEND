package kr.co.jsol.domain.account.infrastructure.dto

import kr.co.jsol.common.domain.Authority
import kr.co.jsol.domain.account.entity.Account
import kr.co.jsol.domain.shop.infrastructure.dto.ShopDto

data class AccountDto(
    val id: String,
    val name: String,
    val role: Authority,
    val phone: String,
    val shop: ShopDto,
) {
    constructor(account: Account) : this(
        id = account.id,
        name = account.name,
        role = account.role,
        phone = account.phone,
        shop = ShopDto(account.shop)
    )
}

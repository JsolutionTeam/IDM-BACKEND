package kr.co.jsol.domain.entity.account.infrastructure.dto

import kr.co.jsol.domain.entity.account.entity.Account

class AccountDto(
    var id: String,
    var role: String,
    var companyName: String,
    var name: String,
    var ownerName: String,
) {

    constructor(account: Account) : this(
        id = account.id,
        role = account.role,
        companyName = account.companyName,
        name = account.name,
        ownerName = account.ownerName,
    )
}

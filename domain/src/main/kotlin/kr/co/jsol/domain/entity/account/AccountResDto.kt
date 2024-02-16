package kr.co.jsol.domain.entity.account

import kr.co.jsol.domain.account.entity.Account

class AccountResDto(account: Account) {
    private val id: String
    private val role: String
    private val companyName: String
    private val name: String
    private val ownerName: String

    init {
        this.id = account.id
        this.role = account.role
        this.companyName = account.companyName
        this.name = account.name
        this.ownerName = account.ownerName
    }
}

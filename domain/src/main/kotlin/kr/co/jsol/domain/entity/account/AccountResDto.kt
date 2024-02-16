package kr.co.jsol.domain.entity.account

import kr.co.jsol.domain.account.entity.Account
import lombok.Getter

@Getter
@NoArgsConstructor
class AccountResDto(account: Account) {
    private val idx: Long
    private val id: String
    private val role: String
    private val companyName: String
    private val name: String
    private val ownerName: String

    init {
        this.idx = account.getIdx()
        this.id = account.getId()
        this.role = account.getRole()
        this.companyName = account.getCompanyName()
        this.name = account.getName()
        this.ownerName = account.getOwnerName()
    }
}

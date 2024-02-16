package kr.co.jsol.domain.entity.useraccount

import lombok.Getter

@Getter
@NoArgsConstructor
class UserAccountResDto(userAccount: UserAccount) {
    private val idx: Long
    private val name: String
    private val phoneNumber: String
    private val companyName: String
    private val id: String
    private val role: String

    init {
        this.idx = userAccount.getIdx()
        this.name = userAccount.getName()
        this.phoneNumber = userAccount.getPhoneNumber()
        this.companyName = userAccount.getCompanyName()
        this.id = userAccount.getId()
        this.role = userAccount.getRole()
    }
}

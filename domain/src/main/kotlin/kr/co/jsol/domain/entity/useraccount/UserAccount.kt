//package kr.co.jsol.domain.entity.useraccount
//
//import com.fasterxml.jackson.annotation.JsonIgnore
//import kr.co.jsol.common.domain.BaseEntity
//import lombok.AccessLevel
//import javax.persistence.Column
//import javax.persistence.Entity
//import javax.persistence.Id
//import javax.persistence.ManyToOne
//
//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Entity
//@Slf4j
//class UserAccount(
//    userAccountDto: UserAccountDto,
//    @field:Column(nullable = false)
//    var password: String,
//    account: Account,
//) : BaseEntity(), UserDetails {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "user_account_idx", nullable = false)
//    private var id: Long? = null
//
//    @Column(nullable = false)
//    private var name: String
//
//    @Column(nullable = false)
//    private var phoneNumber: String
//
//    @Column(nullable = false)
//    private var companyName: String
//
//    @Column(nullable = false)
//    var username: String
//        private set
//
//    @Column(nullable = false)
//    private var role: String
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "account_idx")
//    @JsonIgnore
//    private var account: Account
//
//    init {
//        this.name = userAccountDto.getName()
//        this.phoneNumber = userAccountDto.getPhoneNumber()
//        this.companyName = userAccountDto.getCompanyName()
//        this.username = userAccountDto.getId()
//        this.role = userAccountDto.getRole()
//        this.account = account
//    }
//
//    fun update(
//        userAccountUpdateDto: UserAccountUpdateDto,
//        password: String,
//        account: Account,
//        findUser: UserAccount,
//    ) {
//        this.name = userAccountUpdateDto.getName()
//        this.phoneNumber = userAccountUpdateDto.getPhoneNumber()
//        this.companyName = findUser.getCompanyName()
//        this.username = findUser.getId()
//        this.password = password
//        this.role = findUser.getRole()
//        this.account = account
//    }
//
//    fun updateByCompany(
//        findUserAccount: UserAccount,
//        userAccountReqDto: UserAccountReqDto,
//    ) {
//        this.name = userAccountReqDto.getName()
//        this.phoneNumber = userAccountReqDto.getPhoneNumber()
//        this.companyName = findUserAccount.getCompanyName()
//        this.username = findUserAccount.getId()
//        this.password = findUserAccount.password
//        this.role = userAccountReqDto.getRole()
//        this.account = findUserAccount.getAccount()
//    }
//
//    val authorities: Collection<Any>
//        get() = emptyList()
//
//    val isAccountNonExpired: Boolean
//        get() = false
//
//    val isAccountNonLocked: Boolean
//        get() = false
//
//    val isCredentialsNonExpired: Boolean
//        get() = false
//
//    val isEnabled: Boolean
//        get() = false
//}

//package kr.co.jsol.domain.entity.companypolicy
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
//class CompanyPolicy(
//    companyPolicyDto: CompanyPolicyDto,
//    account: Account,
//) : BaseEntity() {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(nullable = false)
//    private var id: Long? = null
//
//    @Column(nullable = false)
//    private var policyName: String
//
//    @Column(nullable = false)
//    private var policyPrice: Long
//
//    @Column(nullable = false)
//    private var modelIdx: Long
//
//    @Column(nullable = false)
//    private var companyName: String
//
//    @JsonIgnore
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "account_idx")
//    private var account: Account
//
//    init {
//        this.policyName = companyPolicyDto.getPolicyName()
//        this.policyPrice = companyPolicyDto.getPolicyPrice()
//        this.modelIdx = companyPolicyDto.getModelIdx()
//        this.companyName = account.getCompanyName()
//        this.account = account
//    }
//
//    fun update(
//        companyPolicyDto: CompanyPolicyDto,
//        account: Account,
//    ) {
//        this.policyName = companyPolicyDto.getPolicyName()
//        this.policyPrice = companyPolicyDto.getPolicyPrice()
//        this.modelIdx = companyPolicyDto.getModelIdx()
//        this.companyName = account.getCompanyName()
//        this.account = account
//    }
//}

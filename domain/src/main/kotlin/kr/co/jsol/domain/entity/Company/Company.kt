//package kr.co.jsol.domain.entity.Company
//
//import com.fasterxml.jackson.annotation.JsonIgnore
//import kr.co.jsol.common.domain.BaseEntity
//import kr.co.jsol.domain.account.entity.Account
//import javax.persistence.Column
//import javax.persistence.Entity
//import javax.persistence.FetchType
//import javax.persistence.GeneratedValue
//import javax.persistence.GenerationType
//import javax.persistence.Id
//import javax.persistence.JoinColumn
//import javax.persistence.OneToOne
//
//@Entity
//class Company(
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(nullable = false)
//    var id: Long = 0,
//
//    @field:Column(nullable = false)
//    var companyName: String,
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "account_idx")
//    @JsonIgnore
//    val account: Account,
//) : BaseEntity()

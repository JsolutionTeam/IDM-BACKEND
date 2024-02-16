package kr.co.jsol.domain.entity.sumittform

import lombok.Getter

@Getter
class SummitFormResDto(summitForm: SummitForm) {
    private val idx: Long
    private val modelNumber: Long
    private val modelColor: String
    private val modelVolume: String
    private val planIdx: Long
    private val telecomPrice: Long
    private val policyPrice: Long
    private val discountPrice: Long
    private val monthlyPlan: Long
    private val selectiveContract: Long
    private val userName: String
    private val userPhoneNumber: String
    private val companyName: String
    private val telecomName: String

    init {
        this.idx = summitForm.getIdx()
        this.modelNumber = summitForm.getModelNumber()
        this.modelColor = summitForm.getModelColor()
        this.modelVolume = summitForm.getModelVolume()
        this.planIdx = summitForm.getPlanIdx()
        this.telecomPrice = summitForm.getTelecomPrice()
        this.policyPrice = summitForm.getPolicyPrice()
        this.discountPrice = summitForm.getDiscountPrice()
        this.monthlyPlan = summitForm.getMonthlyPlan()
        this.selectiveContract = summitForm.getSelectiveContract()
        this.userName = summitForm.getUserName()
        this.userPhoneNumber = summitForm.getUserPhoneNumber()
        this.companyName = summitForm.getCompanyName()
        this.telecomName = summitForm.getTelecomName()
    }
}

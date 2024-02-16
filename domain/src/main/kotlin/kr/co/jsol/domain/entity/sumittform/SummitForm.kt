package kr.co.jsol.domain.entity.sumittform

import lombok.AccessLevel
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
class SummitForm(
    summitFormDto: SummitFormDto,
    @field:Column(nullable = false)
    private var userPhoneNumber: String
) : TimeStamp() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private var idx: Long? = null

    @Column(nullable = false)
    private var modelNumber: Long

    @Column(nullable = false)
    private var modelColor: String
    private var modelVolume: String

    @Column(nullable = false)
    private var planIdx: Long
    private var telecomPrice: Long
    private var policyPrice: Long
    private var discountPrice: Long

    @Column(nullable = false)
    private var monthlyPlan: Long
    private var selectiveContract: Long

    @Column(nullable = false)
    private var userName: String

    @Column(nullable = false)
    private var companyName: String
    private var telecomName: String

    init {
        this.modelNumber = summitFormDto.getModelNumber()
        this.modelColor = summitFormDto.getModelColor()
        this.modelVolume = summitFormDto.getModelVolume()
        this.planIdx = summitFormDto.getPlanIdx()
        this.telecomPrice = summitFormDto.getTelecomPrice()
        this.policyPrice = summitFormDto.getPolicyPrice()
        this.discountPrice = summitFormDto.getDiscountPrice()
        this.monthlyPlan = summitFormDto.getMonthlyPlan()
        this.selectiveContract = summitFormDto.getSelectiveContract()
        this.userName = summitFormDto.getUserName()
        this.companyName = summitFormDto.getCompanyName()
        this.telecomName = summitFormDto.getTelecomName()
    }

    fun update(summitFormDto: SummitFormDto) {
        this.modelNumber = summitFormDto.getModelNumber()
        this.modelColor = summitFormDto.getModelColor()
        this.modelVolume = summitFormDto.getModelVolume()
        this.planIdx = summitFormDto.getPlanIdx()
        this.telecomPrice = summitFormDto.getTelecomPrice()
        this.policyPrice = summitFormDto.getPolicyPrice()
        this.discountPrice = summitFormDto.getDiscountPrice()
        this.monthlyPlan = summitFormDto.getMonthlyPlan()
        this.selectiveContract = summitFormDto.getSelectiveContract()
        this.userName = summitFormDto.getUserName()
        this.userPhoneNumber = summitFormDto.getUserPhoneNumber()
        this.companyName = summitFormDto.getCompanyName()
        this.telecomName = summitFormDto.getTelecomName()
    }
}

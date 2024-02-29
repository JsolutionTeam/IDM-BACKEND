package kr.co.jsol.domain.deviceapplicationform.entity

import kr.co.jsol.common.domain.BaseEntity
import kr.co.jsol.domain.deviceinfo.entity.DeviceInfo
import kr.co.jsol.domain.insurance.entity.Insurance
import kr.co.jsol.domain.phoneplan.entity.PhonePlan
import kr.co.jsol.domain.shop.entity.Shop
import kr.co.jsol.domain.subservice.entity.Subservice
import kr.co.jsol.domain.telecom.entity.Telecom
import kr.co.jsol.domain.telecom.entity.enums.DiscountType
import kr.co.jsol.domain.telecom.entity.enums.OpenType
import org.hibernate.annotations.Comment
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Table
import javax.persistence.Column
import javax.persistence.ConstraintMode
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.ForeignKey
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@SQLDelete(sql = "UPDATE tb_device_application_form SET deleted_at = now() WHERE id = ?")
@Entity
@javax.persistence.Table(name = "tb_device_application_form")
@Table(appliesTo = "tb_device_application_form", comment = "[통신팀] 단말기 신청서")
class DeviceApplicationForm(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("아이디")
    var id: Long = 0,

    @Column(name = "phone_number")
    @Comment("신청자 전화번호")
    var phoneNumber: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "open_type")
    @Comment("개통 유형")
    var openType: OpenType,

    @Enumerated(EnumType.STRING)
    @Column(name = "discount_type")
    @Comment("선약/공시 할인 유형")
    var discountType: DiscountType,

    @Column(name = "copmany_subsidy_price")
    @Comment("회사 지원금 금액")
    var companySubsidyPrice: Long,

    @Column(name = "usim_change")
    @Comment("유심 신규/변경 여부")
    var usimChange: Boolean,

    @Column(name = "contract_month")
    @Comment("약정 할부 개월 수")
    var contractMonth: Int,

    @Column(name = "device_subsidy_price")
    @Comment("공시 지원금 금액")
    var deviceSubsidyPrice: Long,

    @Column(name = "pre_payment_price")
    @Comment("현금 선입 금액")
    var prePaymentPrice: Long,

    @Column(name = "contract_month")
    @Comment("단말 할부 개월 수")
    var installmentMonth: Int,

    @Comment("메모")
    var memo: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("업체 아이디")
    var shop: Shop,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "telecom_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("통신사 아이디")
    var telecom: Telecom,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("단말 상세 정보 아이디")
    var deviceInfo: DeviceInfo,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phone_plan_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("요금제 아이디")
    var phonePlan: PhonePlan,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "insurance_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("보험 아이디")
    var insurance: Insurance,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subservice_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("부가서비스 아이디")
    var subservice: Subservice,
) : BaseEntity() {

}

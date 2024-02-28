package kr.co.jsol.domain.companysubsidy.entity

import kr.co.jsol.common.domain.BaseEntity
import kr.co.jsol.domain.device.entity.Device
import kr.co.jsol.domain.phoneplan.entity.PhonePlan
import kr.co.jsol.domain.shop.entity.Shop
import kr.co.jsol.domain.telecom.entity.Telecom
import kr.co.jsol.domain.telecom.entity.enums.DiscountType
import kr.co.jsol.domain.telecom.entity.enums.OpenType
import org.hibernate.annotations.Comment
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

@Entity
@javax.persistence.Table(name = "tb_company_subsidy")
@Table(appliesTo = "tb_company_subsidy", comment = "회사 지원금(제이솔루션이 특정 회사에 지급하는 회사/통신사/모델/유형/요금제별 추가 지원금)")
class CompanySubsidy(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("회사 지원금 ID")
    var id: Long = 0,

    @Enumerated(EnumType.STRING)
    @Column(name = "open_type")
    @Comment("개통 유형")
    var openType: OpenType,

    @Enumerated(EnumType.STRING)
    @Column(name = "discount_type")
    @Comment("선약/공시 할인 유형")
    var discountType: DiscountType,

    @Column(name = "discount_price")
    @Comment("공시지원금액")
    var price: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "telecom_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("통신사 아이디")
    var telecom: Telecom,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phone_plan_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("요금제 아이디")
    var phonePlan: PhonePlan,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("단말 아이디")
    var device: Device,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("업체 아이디")
    var shop: Shop,
) : BaseEntity()

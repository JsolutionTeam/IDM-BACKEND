package kr.co.jsol.domain.devicesubsidy.entity

import kr.co.jsol.common.domain.BaseEntity
import kr.co.jsol.domain.deviceinfo.entity.DeviceInfo
import kr.co.jsol.domain.telecom.entity.Telecom
import kr.co.jsol.domain.telecom.entity.enums.OpenSubsidyType
import org.hibernate.annotations.Comment
import org.hibernate.annotations.Table
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
@javax.persistence.Table(name = "device_subsidy")
@Table(appliesTo = "device_subsidy", comment = "공시 지원금(통신사가 제이솔루션에게 지급하는 통신사/모델/요금제별 추가 지원금)")
class DeviceSubsidy(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("공시지원금 아이디")
    var id: Long = 0,

    @Comment("공시지원금액")
    var subsidyPrice: Long,

    @Enumerated(EnumType.STRING)
    @Comment("선약/공시 할인 유형")
    var discountType: OpenSubsidyType,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "telecom_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("단말 상세 아이디")
    var telecom: Telecom,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("단말 상세 아이디")
    var deviceInfo: DeviceInfo,
) : BaseEntity()

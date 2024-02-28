package kr.co.jsol.domain.devicesubsidy.entity

import kr.co.jsol.common.domain.BaseEntity
import kr.co.jsol.domain.device.entity.Device
import kr.co.jsol.domain.phoneplan.entity.PhonePlan
import kr.co.jsol.domain.telecom.entity.Telecom
import org.hibernate.annotations.Comment
import org.hibernate.annotations.Table
import javax.persistence.ConstraintMode
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ForeignKey
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
@javax.persistence.Table(name = "tb_device_subsidy")
@Table(appliesTo = "tb_device_subsidy", comment = "공시 지원금(통신사가 제이솔루션에게 지급하는 통신사/모델/요금제별 추가 지원금)")
class DeviceSubsidy(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("공시지원금 아이디")
    var id: Long = 0,

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
    var deviceInfo: Device,
) : BaseEntity()

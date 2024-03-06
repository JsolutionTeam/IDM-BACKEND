package kr.co.jsol.domain.deviceapplicationform.entity

import kr.co.jsol.common.domain.BaseEntity
import kr.co.jsol.domain.subservice.entity.Subservice
import org.hibernate.annotations.Comment
import org.hibernate.annotations.SQLDelete
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

@SQLDelete(sql = "UPDATE tb_device_application_form_subservice SET deleted_at = now() WHERE id = ?")
@Entity
@javax.persistence.Table(name = "tb_device_application_form_subservice")
@Table(appliesTo = "tb_device_application_form_subservice", comment = "[통신팀] 단말기 신청서 부가서비스 매핑")
class DeviceApplicationFormSubservice(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("아이디")
    var id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_application_form_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("단말기 신청서 아이디")
    var deviceApplicationForm: DeviceApplicationForm,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subservice_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("부가서비스 아이디")
    var subservice: Subservice,
) : BaseEntity()

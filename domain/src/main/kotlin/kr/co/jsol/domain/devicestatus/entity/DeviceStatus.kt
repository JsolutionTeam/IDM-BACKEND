package kr.co.jsol.domain.devicestatus.entity

import kr.co.jsol.common.domain.BaseEntity
import kr.co.jsol.domain.devicestatus.entity.enums.DeviceStatusType
import org.hibernate.annotations.Comment
import org.hibernate.annotations.SQLDelete
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType.STRING
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@SQLDelete(sql = "UPDATE tb_shop_device SET deleted_at = now() WHERE idx = ?")
@Entity
@Table(name = "tb_status", catalog = "idm")
class DeviceStatus(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    @Comment("아이디")
    var id: Long,

    @Column(name = "name")
    @Comment("상태명")
    var name: String,

    @Enumerated(STRING)
    @Comment("유형")
    var type: DeviceStatusType,
) : BaseEntity() {

    @Column(name = "exited_at")
    @Comment("출고일자")
    var exitedAt: LocalDateTime? = null

    @Column(name = "client_id")
    @Comment("고객사 아이디 [ mcall 고객사 용도 ]")
    var clientId: Long? = null

    @Column(name = "client_name")
    @Comment("고객사 이름 [ mcall 고객사 용도 ]")
    var clientName: String? = null

    @Column(name = "department_id")
    @Comment("부서 아이디 [ mcall 고객사 용도]")
    var departmentId: Long? = null

    @Column(name = "department_name")
    @Comment("부서 이름 [ mcall 고객사 용도]")
    var departmentName: String? = null
}

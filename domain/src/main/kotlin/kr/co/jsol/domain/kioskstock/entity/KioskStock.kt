package kr.co.jsol.domain.kioskstock.entity

import kr.co.jsol.common.domain.BaseEntity
import org.hibernate.annotations.Comment
import org.hibernate.annotations.SQLDelete
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@SQLDelete(sql = "UPDATE tb_kiosk_stock SET deleted_at = now() WHERE id = ?")
@Entity
@javax.persistence.Table(name = "tb_stock", catalog = "idm")
class KioskStock(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    @Comment("아이디")
    var id: Long,

    @Column(name = "device_nm")
    @Comment("기기 이름")
    var deviceName: String,

    @Column(name = "serial_no")
    @Comment("시리얼넘버")
    var serialNumber: String,

    @Comment("사용자명")
    var username: String,

    @Comment("키오스크 uid")
    var uid: String,

//    @ManyToOne(fetch = javax.persistence.FetchType.LAZY)
//    @JoinColumn(name = "kiosk_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
//    @Comment("키오스크 id")
//    var kiosk: Kiosk,
) : BaseEntity()

package kr.co.jsol.domain.kioskstock.entity

import kr.co.jsol.common.domain.BaseEntity
import kr.co.jsol.domain.kiosk.entity.Kiosk
import org.hibernate.annotations.Comment
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Table
import javax.persistence.Column
import javax.persistence.ConstraintMode
import javax.persistence.Entity
import javax.persistence.ForeignKey
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@SQLDelete(sql = "UPDATE kiosk_stock SET deleted_at = now() WHERE id = ?")
@Entity
@javax.persistence.Table(name = "kiosk_stock")
@Table(appliesTo = "kiosk_stock", comment = "단말 보관함 재고 현황")
class KioskStock(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Comment("아이디")
    var id: Long,

    @Column(name = "device_name")
    @Comment("기기 이름")
    var deviceName: String,

    @Column(name = "serial_number")
    @Comment("시리얼넘버")
    var serialNumber: String,

    @Column(name = "user_name")
    @Comment("사용자명")
    var userName: String,

    @ManyToOne(fetch = javax.persistence.FetchType.LAZY)
    @JoinColumn(name = "kiosk_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("키오스크 id")
    var kiosk: Kiosk,
) : BaseEntity()

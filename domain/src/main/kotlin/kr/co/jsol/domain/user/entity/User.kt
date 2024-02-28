package kr.co.jsol.domain.user.entity

import kr.co.jsol.common.domain.BaseEntity
import kr.co.jsol.domain.kiosk.entity.Kiosk
import kr.co.jsol.domain.shop.entity.Shop
import kr.co.jsol.domain.shopdevice.entity.ShopDevice
import org.hibernate.annotations.Comment
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Table
import javax.persistence.Column
import javax.persistence.ConstraintMode
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@SQLDelete(sql = "UPDATE tb_user SET deleted_at = now() WHERE idx = ?")
@Entity
@javax.persistence.Table(name = "tb_user")
@Table(appliesTo = "tb_user", comment = "인원 정보[ 현재 키오스크에서 사용 중 ]")
class User(
    @Id
    @Column(name = "id")
    @Comment("아이디")
    var id: String,

    @Comment("이름")
    var name: String,

    @Comment("전화번호")
    var phone: String,

    @Comment("직급/계급")
    val rank: String,

    @Column(name = "serial_number")
    @Comment("사번/군번")
    val serialNumber: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", foreignKey = javax.persistence.ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("소속 아이디")
    var shop: Shop,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_device_id", foreignKey = javax.persistence.ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("소속 단말 아이디")
    var shopDevice: ShopDevice,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kiosk_id", foreignKey = javax.persistence.ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("키오스크 아이디")
    var kiosk: Kiosk,
) : BaseEntity()

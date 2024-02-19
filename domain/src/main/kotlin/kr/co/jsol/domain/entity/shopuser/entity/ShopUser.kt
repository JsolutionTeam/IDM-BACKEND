package kr.co.jsol.domain.entity.shopuser.entity

import kr.co.jsol.common.domain.BaseEntity
import kr.co.jsol.domain.entity.kiosk.entity.Kiosk
import kr.co.jsol.domain.entity.shop.entity.Shop
import kr.co.jsol.domain.entity.shopdevice.entity.ShopDevice
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

@SQLDelete(sql = "UPDATE user SET deleted_at = now() WHERE id = ?")
@Entity
@Table(appliesTo = "user")
class ShopUser(
    @field:Id
    @field:Column(name = "id")
    @field:Comment("아이디")
    var id: String,

    @field:Comment("이름")
    var name: String,

    @field:Comment("전화번호")
    var phone: String,

    @field:Comment("직급/계급")
    val rank: String,

    @field:Comment("사번/군번")
    val serial: String,

    @field:ManyToOne(fetch = FetchType.LAZY)
    @field:JoinColumn(name = "shop_id", foreignKey = javax.persistence.ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @field:Comment("소속 아이디")
    var shop: Shop,

    @field:ManyToOne(fetch = FetchType.LAZY)
    @field:JoinColumn(name = "shop_device_id", foreignKey = javax.persistence.ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @field:Comment("소속 아이디")
    var shopDevice: ShopDevice,

    @field:ManyToOne(fetch = FetchType.LAZY)
    @field:JoinColumn(name = "kiosk_id", foreignKey = javax.persistence.ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @field:Comment("소속 아이디")
    var kiosk: Kiosk,
) : BaseEntity()

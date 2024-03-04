package kr.co.jsol.domain.user.entity

import kr.co.jsol.common.domain.BaseEntity
import kr.co.jsol.domain.kiosk.entity.Kiosk
import kr.co.jsol.domain.shop.entity.Shop
import kr.co.jsol.domain.shopdevice.entity.ShopDevice
import org.hibernate.annotations.Comment
import org.hibernate.annotations.SQLDelete
import javax.persistence.Column
import javax.persistence.ConstraintMode
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@SQLDelete(sql = "UPDATE tb_user SET deleted_at = now() WHERE idx = ?")
@Entity
@Table(name = "tb_user", catalog = "idm")
class User(
    @Id
    @Column(name = "idx")
    @Comment("아이디")
    var id: String,

    @Comment("이름")
    var name: String,

    @Column(name = "tel_no")
    @Comment("전화번호")
    var phone: String,

    @Column(name = "class")
    @Comment("직급/계급")
    val rank: String,

    @Column(name = "serial")
    @Comment("사번/군번")
    val serialNumber: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "belong_idx", foreignKey = javax.persistence.ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("소속 아이디")
    var shop: Shop,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "belong_device_id", foreignKey = javax.persistence.ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("소속 단말 아이디")
    var shopDevice: ShopDevice,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kiosk_idx", foreignKey = javax.persistence.ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("키오스크 아이디")
    var kiosk: Kiosk,
) : BaseEntity()

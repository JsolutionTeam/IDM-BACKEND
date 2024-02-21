package kr.co.jsol.domain.shopdevice.entity

import kr.co.jsol.common.domain.BaseEntity
import kr.co.jsol.domain.deviceinfo.entity.DeviceInfo
import kr.co.jsol.domain.shop.entity.Shop
import kr.co.jsol.domain.shopdepot.entity.ShopDepot
import kr.co.jsol.domain.shopdevice.entity.enums.ShopDeviceStatus
import kr.co.jsol.domain.telecom.entity.Telecom
import org.hibernate.annotations.Comment
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Table
import javax.persistence.Column
import javax.persistence.ConstraintMode
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType.LAZY
import javax.persistence.ForeignKey
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@SQLDelete(sql = "UPDATE shop_device SET deleted_at = now() WHERE id = ?")
@Entity
@javax.persistence.Table(name = "shop_device")
@Table(appliesTo = "shop_device")
class ShopDevice(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Comment("아이디")
    var id: Long,

    @Comment("")
    var serialNo: String,

    @Comment("")
    var modelNm: String,

    @Comment("")
    var makerNm: String,

    @Comment("")
    var volume: String,

    @Comment("")
    var deviceColor: String,

    @Comment("")
    var price: String,

    @Comment("")
    var qrBase: String,

    @Comment("")
    var processor: String,

    @Comment("")
    var inDate: String,

    @Comment("")
    var outDate: String,

    @Comment("")
    var moveDate: String,

    @Comment("")
    var memo: String,

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "shop_depot_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("")
    var shopDepot: ShopDepot,

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "owner_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("")
    var owner: Shop,

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "location_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("")
    var location: Shop,

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "device_info_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("")
    var deviceInfo: DeviceInfo,

    @Enumerated(EnumType.STRING)
    @Comment("단말 상태")
    var status: ShopDeviceStatus,

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "telecom_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("")
    var telecom: Telecom,

    @Comment("")
    var clientId: Long,

    @Comment("")
    var clientName: String,

    @Comment("")
    var departmentId: Long,

    @Comment("")
    var departmentName: String,

    @Comment("")
    var outPrice: String,
) : BaseEntity()

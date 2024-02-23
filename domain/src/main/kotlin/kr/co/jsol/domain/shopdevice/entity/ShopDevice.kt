package kr.co.jsol.domain.shopdevice.entity

import kr.co.jsol.common.domain.BaseEntity
import kr.co.jsol.domain.deviceinfo.entity.DeviceInfo
import kr.co.jsol.domain.maker.entity.Maker
import kr.co.jsol.domain.shop.entity.Shop
import kr.co.jsol.domain.shopdepot.entity.ShopDepot
import kr.co.jsol.domain.shopdevice.entity.enums.ShopDeviceStatus
import kr.co.jsol.domain.telecom.entity.Telecom
import org.hibernate.annotations.Comment
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Table
import java.time.LocalDateTime
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

    @Comment("시리얼넘버[일련번호]")
    var serialNumber: String,

    @Comment("모델명 ex) SM-G950N")
    var modelName: String,

    @Comment("용량 ex) 256GB")
    var volume: String,

    @Comment("색상 ex) 블랙")
    var deviceColor: String,

    @Comment("출고가")
    var price: String,

    @Comment("qr코드 기본값[시리얼넘버]")
    var qrBase: String,

    @Comment("입고일자")
    var enteredAt: LocalDateTime,

    @Comment("이동일시")
    var movedAt: LocalDateTime,

    @Comment("메모")
    var memo: String,

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "shop_depot_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("입고처 아이디")
    var shopDepot: ShopDepot,

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "owner_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("등록처 아이디 [Shop.id]")
    var owner: Shop,

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "location_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("보유처 아이디 [Shop.id]")
    var location: Shop,

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "device_info_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("단말 상세 아이디")
    var deviceInfo: DeviceInfo,

    @Enumerated(EnumType.STRING)
    @Comment("단말 상태")
    var status: ShopDeviceStatus,

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "telecom_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("통신사 아이디")
    var telecom: Telecom,

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "maker_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("제조사 아이디")
    var maker: Maker,
) : BaseEntity() {

    @Comment("출고일자")
    var exitedAt: LocalDateTime? = null

    @Comment("고객사 아이디 [ mcall 고객사 용도 ]")
    var clientId: Long? = null

    @Comment("고객사 이름 [ mcall 고객사 용도 ]")
    var clientName: String? = null

    @Comment("부서 아이디 [ mcall 고객사 용도]")
    var departmentId: Long? = null

    @Comment("부서 이름 [ mcall 고객사 용도]")
    var departmentName: String? = null
}

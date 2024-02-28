package kr.co.jsol.domain.shopdepot.entity

import kr.co.jsol.common.domain.BaseEntity
import kr.co.jsol.domain.shop.entity.Shop
import org.hibernate.annotations.Comment
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Table
import javax.persistence.Column
import javax.persistence.ConstraintMode
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ForeignKey
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@SQLDelete(sql = "UPDATE shop_depot SET deleted_at = now() WHERE id = ?")
@Entity
@javax.persistence.Table(name = "tb_shop_depot")
@Table(appliesTo = "tb_shop_depot")
class ShopDepot(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Comment("아이디")
    var id: Long,

    @Comment("입고처명")
    var name: String,

    @Comment("주소")
    var address: String,

    @Column(name = "address_detail")
    @Comment("상세 주소")
    var addressDetail: String,

    @Comment("전화번호")
    var phone: String,

    @Column(name = "zip_code")
    @Comment("우편번호")
    var zipCode: String,

    @Column(name = "is_enabled")
    @Comment("활성화 여부, 0: false, 1: true")
    var isEnabled: Boolean,

    @Comment("메모")
    var memo: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("")
    var shop: Shop,
) : BaseEntity()

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
@javax.persistence.Table(name = "shop_depot")
@Table(appliesTo = "shop_depot")
class ShopDepot(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Comment("아이디")
    var id: Long,

    @Comment("")
    var name: String,

    @Comment("")
    var addr: String,

    @Comment("")
    var daddr: String,

    @Comment("")
    var telNo: String,

    @Comment("")
    var zipCode: String,

    @Comment("")
    var isEnabled: String,

    @Comment("")
    var memo: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("")
    var shop: Shop,
) : BaseEntity()

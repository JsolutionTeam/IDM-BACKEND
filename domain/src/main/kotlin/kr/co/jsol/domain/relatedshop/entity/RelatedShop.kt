package kr.co.jsol.domain.relatedshop.entity

import kr.co.jsol.common.domain.BaseEntity
import kr.co.jsol.domain.shop.entity.Shop
import org.hibernate.annotations.Comment
import org.hibernate.annotations.SQLDelete
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
import javax.persistence.Table

@SQLDelete(sql = "UPDATE tb_releated_shop SET deleted_at = now() WHERE id = ?")
@Entity
@Table(name = "tb_releated_shop", catalog = "idm")
class RelatedShop(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    @Comment("아이디")
    var id: Long,

    @Column(name = "req_shop_id")
    @Comment("요청자 업체 아이디")
    var requesterShopId: Long,

    // request(0), no(1), yes(2)
    val status: Int, // TODO enum으로 변경

//    @Column(name = "status")
//    @Comment("수락 여부")
//    var isAccepted: Boolean,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_a_idx", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("")
    var shopA: Shop,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_b_idx", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("")
    var shopB: Shop,
) : BaseEntity()

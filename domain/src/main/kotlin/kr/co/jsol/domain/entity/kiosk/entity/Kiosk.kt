package kr.co.jsol.domain.entity.kiosk.entity

import kr.co.jsol.common.domain.BaseEntity
import kr.co.jsol.domain.entity.shop.entity.Shop
import kr.co.jsol.domain.entity.shopuser.entity.ShopUser
import org.hibernate.annotations.Comment
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Table
import javax.persistence.CascadeType
import javax.persistence.ConstraintMode
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ForeignKey
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@SQLDelete(sql = "UPDATE kiosk SET deleted_at = now() WHERE id = ?")
@Entity
@Table(appliesTo = "kiosk", comment = "단말 보관함")
class Kiosk(
    @field:Id
    @field:Comment("아이디")
    val uid: String,

    @field:Comment("이름")
    var name: String,

    @field:Comment("메모")
    var memo: String,

    @field:Comment("최대 보관 개수")
    var max: Int,

    @field:ManyToOne(fetch = javax.persistence.FetchType.LAZY)
    @field:JoinColumn(name = "shop_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @field:Comment("소속")
    var shop: Shop,

    @field:OneToMany(
        mappedBy = "kiosk",
        fetch = FetchType.LAZY,
        orphanRemoval = true,
        cascade = [CascadeType.ALL],
    )
    @field:Comment("사용자")
    var shopUsers: MutableList<ShopUser>,
) : BaseEntity()

package kr.co.jsol.domain.kiosk.entity

import kr.co.jsol.common.domain.BaseEntity
import kr.co.jsol.domain.shop.entity.Shop
import kr.co.jsol.domain.user.entity.User
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
@javax.persistence.Table(name = "tb_kiosk")
@Table(appliesTo = "tb_kiosk", comment = "단말 보관함")
class Kiosk(
    @Id
    @Comment("아이디")
    val uid: String,

    @Comment("이름")
    var name: String,

    @Comment("메모")
    var memo: String,

    @Comment("최대 보관 개수")
    var max: Int,

    @ManyToOne(fetch = javax.persistence.FetchType.LAZY)
    @JoinColumn(name = "shop_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("소속")
    var shop: Shop,

    @OneToMany(
        mappedBy = "kiosk",
        fetch = FetchType.LAZY,
        orphanRemoval = true,
        cascade = [CascadeType.ALL],
    )
    @Comment("사용자")
    var users: MutableList<User>,
) : BaseEntity()

package kr.co.jsol.domain.kiosk.entity

import kr.co.jsol.common.domain.BaseEntity
import kr.co.jsol.domain.shop.entity.Shop
import org.hibernate.annotations.Comment
import org.hibernate.annotations.SQLDelete
import javax.persistence.Column
import javax.persistence.ConstraintMode
import javax.persistence.Entity
import javax.persistence.ForeignKey
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@SQLDelete(sql = "UPDATE tb_kiosk SET deleted_at = now() WHERE idx = ?")
@Entity
@Table(name = "tb_kiosk", catalog = "idm")
class Kiosk(
    @Id
    @Column(name = "idx")
    @Comment("아이디")
    var id: String,

    @Comment("이름")
    var name: String,

    @Comment("고유키")
    val uid: String,

    @Comment("메모")
    var memo: String,

    @Comment("최대 보관 개수")
    var max: Int,

    @ManyToOne(fetch = javax.persistence.FetchType.LAZY)
    @JoinColumn(name = "belong_idx", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("소속")
    var shop: Shop,

//    @OneToMany(
//        mappedBy = "kiosk",
//        fetch = FetchType.LAZY,
//        orphanRemoval = true,
//        cascade = [CascadeType.ALL],
//    )
//    @Comment("사용자")
//    var users: MutableList<User>,
) : BaseEntity()

package kr.co.jsol.domain.account.entity

import kr.co.jsol.common.domain.AccountAuthority
import kr.co.jsol.common.domain.BaseEntity
import kr.co.jsol.domain.shop.entity.Shop
import org.hibernate.annotations.Comment
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Table
import javax.persistence.Column
import javax.persistence.ConstraintMode
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.ForeignKey
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@SQLDelete(sql = "UPDATE tb_account SET deleted_at = now() WHERE id = ?")
@Entity
@javax.persistence.Table(name = "tb_account")
@Table(appliesTo = "tb_account", comment = "계정")
class Account(
    @Id
    @Column(name = "id")
    @Comment("계정 아이디[로그인시 사용]")
    var id: String,

    @Comment("사용자명")
    var name: String,

    @Enumerated(EnumType.STRING)
    @Comment("계정 권한")
    var role: AccountAuthority,

    @Comment("전화번호")
    val phone: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("업체 아이디")
    var shop: Shop,
) : BaseEntity() {

    override fun toString(): String {
        return "Account(id='$id', name='$name', role='$role', phone='$phone', shop=$shop)"
    }
}

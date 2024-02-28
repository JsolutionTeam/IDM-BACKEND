package kr.co.jsol.domain.shop.entity

import kr.co.jsol.common.domain.AccountAuthority
import kr.co.jsol.common.domain.BaseEntity
import kr.co.jsol.domain.shop.entity.enums.ShopStatus
import org.hibernate.annotations.Comment
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Table
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@SQLDelete(sql = "UPDATE tb_shop SET deleted_at = now() WHERE idx = ?")
@Entity
@javax.persistence.Table(name = "tb_shop")
@Table(appliesTo = "tb_shop", comment = "업체[매장], 대리점, 판매점 등")
class Shop(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("아이디")
    var id: Long = 0,

    @Comment("이름")
    var name: String,

    @Enumerated(EnumType.STRING)
    @Comment("권한")
    var role: AccountAuthority,

    @Enumerated(EnumType.STRING)
    @Comment("상태")
    var status: ShopStatus,

    @Comment("전화번호")
    var phone: String,

    @Column(name = "manager_id")
    @Comment("관리자 아이디")
    var managerId: String,

    @Column(name = "manager_name")
    @Comment("관리자 이름")
    var managerName: String,

    @Column(name = "manager_phone")
    @Comment("관리자 전화번호")
    var managerPhone: String,
) : BaseEntity() {

    override fun toString(): String {
        return "Shop(id=$id, name='$name', role='$role', status='$status', phone='$phone'," +
                " managerId='$managerId', managerName='$managerName', managerPhone='$managerPhone')"
    }
}

package kr.co.jsol.domain.shop.entity

import kr.co.jsol.common.domain.BaseEntity
import kr.co.jsol.domain.shop.entity.enums.ShopStatus
import org.hibernate.annotations.Comment
import org.hibernate.annotations.SQLDelete
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@SQLDelete(sql = "UPDATE tb_shop SET deleted_at = now() WHERE idx = ?")
@Entity
@Table(name = "tb_shop", catalog = "idm")
class Shop(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    @Comment("아이디")
    var id: Long = 0,

    @Comment("이름")
    var name: String,

    @Comment("권한")
    var role: String = "COMPANY",

    @Column(name = "use_mcall_shop")
    @Comment("엠콜샵 사용 여부")
    var useMcallShop: Boolean = true,

    @Enumerated(EnumType.STRING)
    @Comment("상태")
    var status: ShopStatus = ShopStatus.DONE,

    @Column(name = "tel_no")
    @Comment("전화번호")
    var phone: String,

    @Column(name = "manager_id")
    @Comment("관리자 아이디")
    var managerId: String,

    @Column(name = "manager_nm")
    @Comment("관리자 이름")
    var managerName: String,

    @Column(name = "manager_tel_no")
    @Comment("관리자 전화번호")
    var managerPhone: String,
) : BaseEntity() {

    override fun toString(): String {
        return "Shop(id=$id, name='$name', role='$role', status='$status', phone='$phone'," +
                " managerId='$managerId', managerName='$managerName', managerPhone='$managerPhone')"
    }
}

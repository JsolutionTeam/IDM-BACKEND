package kr.co.jsol.domain.account.entity

import kr.co.jsol.common.domain.BaseEntity
import kr.co.jsol.domain.account.application.dto.UpdateAccountDto
import kr.co.jsol.domain.shop.entity.Shop
import org.hibernate.annotations.Comment
import org.hibernate.annotations.SQLDelete
import javax.persistence.Column
import javax.persistence.ConstraintMode
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ForeignKey
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@SQLDelete(sql = "UPDATE integration_idm.tb_account SET deleted_at = now() WHERE id = ?")
@Entity
@Table(name = "tb_account", catalog = "idm")
class Account(
    @Id
    @Column(name = "id")
    @Comment("계정 아이디[로그인시 사용]")
    var id: String,

    @Comment("사용자명")
    var name: String,

//    @Enumerated(EnumType.STRING)
    @Comment("계정 권한(최종 관리자/회사)")
//    var role: AccountAuthority, //TODO IDM 완전 변경시 사용
    var role: String,

    @Column(name = "is_manager")
    @Comment("관리자 여부, 0 = false, 1 = true")
    var isManager: Boolean = false,

    @Column(name = "tel_no")
    @Comment("전화번호")
    var phone: String,

    @Column(name = "memo")
    @Comment("전화번호")
    var memo: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_idx", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("업체 아이디")
    var shop: Shop,
) : BaseEntity() {

    fun update(updateAccountDto: UpdateAccountDto) {
        updateAccountDto.name?.let { this.name = it }
        updateAccountDto.phone?.let { this.phone = it }
        updateAccountDto.memo?.let { this.memo = it }
    }

    override fun toString(): String {
        return "Account(id='$id', name='$name', role='$role', phone='$phone', memo='$memo', shop=$shop)"
    }
}

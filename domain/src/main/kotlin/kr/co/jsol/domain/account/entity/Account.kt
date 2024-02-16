package kr.co.jsol.domain.account.entity

import kr.co.jsol.common.domain.BaseEntity
import org.hibernate.annotations.Comment
import org.hibernate.annotations.SQLDelete
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

// 삭제시 deletedAt = now() 처리
@SQLDelete(sql = "UPDATE tb_account SET deleted_at = now() WHERE id = ?")
@Entity
@Table(name = "tb_account")
class AccountJpaEntity(
    @Id
    @Column(nullable = false)
    @Comment("계정 아이디[로그인시 사용]")
    var id: String,

    @Comment("사용자명")
    var name: String,

    @Column(nullable = false)
    @Comment("계정 권한")
    var role: String,

    @Column(name = "tel_no", nullable = false)
    @Comment("전화번호")
    val phone: String,

    @Column(nullable = false)
    @Comment("회사명")
    var companyName: String,

    @Column(nullable = false)
    @Comment("대표자명")
    var ownerName: String,

    @Column(nullable = false)
    @Comment("매장 고유번호")
    var shopIdx: Long,
) : BaseEntity()

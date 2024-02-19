package kr.co.jsol.domain.entity.account.entity

import kr.co.jsol.common.domain.BaseEntity
import org.hibernate.annotations.Comment
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Table
import javax.persistence.Entity
import javax.persistence.Id

@SQLDelete(sql = "UPDATE account SET deleted_at = now() WHERE id = ?")
@Entity
@Table(appliesTo = "account", comment = "계정")
class Account(
    @field:Id
    @field:Comment("계정 아이디[로그인시 사용]")
    var id: String,

    @field:Comment("사용자명")
    var name: String,

    @field:Comment("계정 권한")
    var role: String,

    @field:Comment("전화번호")
    val phone: String,

    @field:Comment("회사명")
    var companyName: String,

    @field:Comment("대표자명")
    var ownerName: String,

    @field:Comment("매장 고유번호")
    var shopIdx: Long,
) : BaseEntity()

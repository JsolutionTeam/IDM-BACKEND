package kr.co.jsol.domain.telecom.entity

import kr.co.jsol.common.domain.BaseEntity
import org.hibernate.annotations.Comment
import org.hibernate.annotations.SQLDelete
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@SQLDelete(sql = "UPDATE tb_telecom SET deleted_at = now() WHERE idx = ?")
@Entity
@Table(name = "tb_telecom")
class Telecom(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:Column(name = "idx")
    @field:Comment("아이디")
    var id: Long,

    @field:Comment("이름")
    var name: String,
) : BaseEntity()

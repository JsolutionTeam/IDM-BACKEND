package kr.co.jsol.domain.maker.entity

import kr.co.jsol.common.domain.BaseEntity
import org.hibernate.annotations.Comment
import org.hibernate.annotations.SQLDelete
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@SQLDelete(sql = "UPDATE tb_maker SET deleted_at = now() WHERE id = ?")
@Entity
@Table(
    name = "tb_maker",
    uniqueConstraints = [
        UniqueConstraint(
            name = "uk_maker_name",
            columnNames = ["name"]
        ),
    ]
)
class Maker(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:Comment("아이디")
    var id: Long,

    @field:Comment("이름")
    var name: String,
) : BaseEntity()

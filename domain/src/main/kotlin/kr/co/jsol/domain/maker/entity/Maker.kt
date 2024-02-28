package kr.co.jsol.domain.maker.entity

import kr.co.jsol.common.domain.BaseEntity
import org.hibernate.annotations.Comment
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Table
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.UniqueConstraint

@SQLDelete(sql = "UPDATE tb_maker SET deleted_at = now() WHERE idx = ?")
@Entity
@javax.persistence.Table(
    name = "tb_maker",
    uniqueConstraints = [
        UniqueConstraint(
            name = "uk_maker_name",
            columnNames = ["name"]
        ),
    ]
)
@Table(appliesTo = "tb_maker", comment = "제조사")
class Maker(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("아이디")
    var id: Long,

    @Comment("이름")
    var name: String,
) : BaseEntity()

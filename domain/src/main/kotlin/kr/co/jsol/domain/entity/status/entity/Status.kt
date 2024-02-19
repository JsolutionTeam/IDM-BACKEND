package kr.co.jsol.domain.entity.status.entity

import kr.co.jsol.common.domain.BaseEntity
import kr.co.jsol.domain.entity.status.entity.enums.StatusType
import org.hibernate.annotations.Comment
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Table
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType.STRING
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@SQLDelete(sql = "UPDATE status SET deleted_at = now() WHERE id = ?")
@Entity
@Table(appliesTo = "status")
class Status(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:Column(name = "id")
    @field:Comment("아이디")
    var id: Long,

    @field:Comment("이름")
    var name: String,

    @field:Enumerated(STRING)
    @field:Comment("타입, in(입고), out(출고), etc(기타), tempOut(임시출고)")
    var type: StatusType,
) : BaseEntity()

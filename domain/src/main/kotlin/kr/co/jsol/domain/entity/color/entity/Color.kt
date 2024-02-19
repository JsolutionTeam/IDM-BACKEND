package kr.co.jsol.domain.entity.color.entity

import kr.co.jsol.common.domain.BaseEntity
import org.hibernate.annotations.Comment
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Table
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@SQLDelete(sql = "UPDATE color SET deleted_at = now() WHERE id = ?")
@Entity
@Table(appliesTo = " color", comment = "색상")
class Color(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:Comment("아이디")
    var id: Long,

    @field:Comment("색상 한글명")
    var name: String,

    @field:Comment("색상 영문명")
    var nameEng: String,

//    @field:Comment("색상 코드[사용자 사용하기 나름] ex) hex값, rgb값")
//    var code: String,
) : BaseEntity()

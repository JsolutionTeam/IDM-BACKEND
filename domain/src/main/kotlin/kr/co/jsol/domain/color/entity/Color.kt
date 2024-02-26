package kr.co.jsol.domain.color.entity

import kr.co.jsol.common.domain.BaseEntity
import org.hibernate.annotations.Comment
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Table
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@SQLDelete(sql = "UPDATE color SET deleted_at = now() WHERE id = ?")
@Entity
@javax.persistence.Table(name = "color")
@Table(appliesTo = "color", comment = "색상")
class Color(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("아이디")
    var id: Long,

    @Comment("색상 한글명")
    var name: String,

    @Column(name = "name_eng")
    @Comment("색상 영문명")
    var nameEng: String = "",

    @Column(name = "display_value")
    @Comment("색상 코드[사용자 사용하기 나름] ex) hex값, rgb값")
    var displayValue: String = "",
) : BaseEntity()

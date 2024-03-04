package kr.co.jsol.domain.color.entity

import kr.co.jsol.common.domain.BaseEntity
import org.hibernate.annotations.Comment
import org.hibernate.annotations.SQLDelete
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@SQLDelete(sql = "UPDATE tb_color SET deleted_at = now() WHERE idx = ?")
@Entity
@Table(name = "tb_color", catalog = "idm")
class Color(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
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

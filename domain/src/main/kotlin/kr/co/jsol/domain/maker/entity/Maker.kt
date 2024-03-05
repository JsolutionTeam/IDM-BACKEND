package kr.co.jsol.domain.maker.entity

import kr.co.jsol.common.domain.BaseEntity
import org.hibernate.annotations.Comment
import org.hibernate.annotations.SQLDelete
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@SQLDelete(sql = "UPDATE tb_maker SET deleted_at = now() WHERE idx = ?")
@Entity
@Table(name = "tb_maker", catalog = "idm")
class Maker(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    @Comment("아이디")
    var id: Long,

    @Comment("이름")
    var name: String,
) : BaseEntity()

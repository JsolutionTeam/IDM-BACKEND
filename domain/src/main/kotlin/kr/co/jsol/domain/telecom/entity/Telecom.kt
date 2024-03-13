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
@Table(name = "tb_telecom", catalog = "idm")
class Telecom(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    @Comment("아이디")
    var id: Long = 0,

    @Comment("이름")
    var name: String,
) : BaseEntity()

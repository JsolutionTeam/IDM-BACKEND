package kr.co.jsol.domain.telecom.entity

import kr.co.jsol.common.domain.BaseEntity
import org.hibernate.annotations.Comment
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Table
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@SQLDelete(sql = "UPDATE telecom SET deleted_at = now() WHERE id = ?")
@Entity
@javax.persistence.Table(name = "telecom")
@Table(appliesTo = "telecom")
class Telecom(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Comment("아이디")
    var id: Long,

    @Comment("이름")
    var name: String,
    
    @Comment("공식 홈페이지")
    var homepage: String,
) : BaseEntity()

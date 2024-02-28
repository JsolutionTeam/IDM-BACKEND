package kr.co.jsol.domain.masteroption.entity

import kr.co.jsol.common.domain.BaseEntity
import org.hibernate.annotations.Comment
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Table
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@SQLDelete(sql = "UPDATE tb_master_option SET deleted_at = now() WHERE id = ?")
@Entity
@javax.persistence.Table(name = "tb_master_option")
@Table(appliesTo = "tb_master_option", comment = "마스터 옵션")
class MasterOption(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("마스터 옵션 ID")
    var id: Long = 0,

    @Comment("마스터 옵션 명")
    var name: String,

    @Comment("노출 여부")
    var isShow: Boolean,
) : BaseEntity()

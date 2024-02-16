package kr.co.jsol.domain.device.entity

import kr.co.jsol.common.domain.BaseEntity
import kr.co.jsol.domain.maker.entity.Maker
import org.hibernate.annotations.Comment
import org.hibernate.annotations.SQLDelete
import javax.persistence.ConstraintMode
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ForeignKey
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@SQLDelete(sql = "UPDATE tb_device SET deleted_at = now() WHERE id = ?")
@Entity
@Table(name = "tb_device")
class Device(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:Comment("아이디")
    var id: Long,

    @field:Comment("펫네임 ex)갤럭시 S10")
    var petName: String,

    @field:Comment("모델명 ex)SM-G973N")
    var modelName: String,

    @field:Comment("가격")
    var price: Int,

    @field:Comment("용량")
    var volume: String,

    @field:ManyToOne(fetch = FetchType.LAZY)
    @field:JoinColumn(name = "maker_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @field:Comment("제조사 아이디")
    var maker: Maker,
) : BaseEntity()

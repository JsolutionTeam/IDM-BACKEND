package kr.co.jsol.domain.device.entity

import kr.co.jsol.common.domain.BaseEntity
import kr.co.jsol.domain.maker.entity.Maker
import org.hibernate.annotations.Comment
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Table
import javax.persistence.ConstraintMode
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ForeignKey
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@SQLDelete(sql = "UPDATE device SET deleted_at = now() WHERE id = ?")
@Entity
@javax.persistence.Table(name = "device")
@Table(appliesTo = "device", comment = "단말")
class Device(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("아이디")
    var id: Long,

    @Comment("펫네임 ex)갤럭시 S10")
    var petName: String,

    @Comment("모델명 ex)SM-G973N")
    var modelName: String,

    @Comment("가격")
    var price: Int,

    @Comment("용량")
    var volume: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maker_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("제조사 아이디")
    var maker: Maker,
) : BaseEntity()

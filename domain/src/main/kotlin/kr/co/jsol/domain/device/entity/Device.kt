package kr.co.jsol.domain.device.entity

import kr.co.jsol.common.domain.BaseEntity
import kr.co.jsol.domain.maker.entity.Maker
import org.hibernate.annotations.Comment
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Table
import javax.persistence.Column
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
@javax.persistence.Table(name = "tb_device")
@Table(appliesTo = "tb_device", comment = "단말")
class Device(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("아이디")
    var id: Long,

    @Column(name = "pet_name")
    @Comment("펫네임 ex) 아이폰 15 프로 맥스")
    var petName: String,

    @Column(name = "model_name")
    @Comment("모델명 ex) AIP-15PM")
    var modelName: String,

    @Comment("시리즈명 ex) 아이폰 15")
    var series: String,

    @Comment("가격")
    var price: Long,

    @Comment("용량 ex) 125GB, 256TB 등..")
    var volume: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maker_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("제조사 아이디")
    var maker: Maker,
) : BaseEntity()

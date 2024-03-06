package kr.co.jsol.domain.subservice.entity

import kr.co.jsol.common.domain.BaseEntity
import kr.co.jsol.domain.telecom.entity.Telecom
import org.hibernate.annotations.Comment
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

@Entity
@javax.persistence.Table(name = "tb_subservice", catalog = "mcall")
class Subservice(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subservice_id")
    @Comment("아이디")
    var id: Long = 0,

    @Column(name = "name")
    @Comment("이름")
    var name: String,

    @Column(name = "exp")
    @Comment("설명")
    var explanation: String,

    @Column(name = "price")
    @Comment("가격")
    var price: Long,

    @Column(name = "point")
    @Comment("포인트 사용량")
    var point: Long,

//    @Column(name = "etc")
//    @Comment("기타")
//    var etc: String,

    @Column(name = "frequency")
    @Comment("사용 빈도 회수 체크용")
    var frequency: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "telecom_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("통신사 아이디")
    var telecom: Telecom,
) : BaseEntity()

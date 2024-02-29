package kr.co.jsol.domain.insurance.entity

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
@javax.persistence.Table(name = "tb_insurance", schema = "mcall")
class Insurance(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "insurance_id")
    @Comment("아이디")
    var id: Long,

    @Column(name = "name")
    @Comment("이름")
    var name: String,

    @Column(name = "price")
    @Comment("가격")
    var price: Long,

    @Column(name = "target_exp")
    @Comment("보상 대상 설명")
    var targetExp: String = "",

    @Column(name = "range_exp")
    @Comment("보상 범위 설명")
    var rangeExp: String = "",

    @Column(name = "limit_exp")
    @Comment("보상 한도(최대 보상) 설명")
    var limitExp: String = "",

    @Column(name = "self_price_exp")
    @Comment("자기부담금 설명")
    var selfPriceExp: String = "",

    @Column(name = "plus_reward_exp")
    @Comment("추가 보상 설명")
    var plusRewardExp: String = "",

    @Column(name = "etc")
    @Comment("기타")
    var etc: String,

    @Column(name = "frequency")
    @Comment("사용 빈도 회수 체크용")
    var frequency: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "telecom_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("통신사 아이디")
    var telecom: Telecom,
) : BaseEntity()

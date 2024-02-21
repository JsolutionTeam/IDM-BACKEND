package kr.co.jsol.domain.phoneplan.entity

import kr.co.jsol.domain.telecom.entity.Telecom
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.ConstraintMode
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ForeignKey
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "tb_phone_plan", catalog = "mcall") // mcall 테이블의 tb_phone_plan 테이블이 존재하지 않으면 아래 정보로 테이블 생성됨
class PhonePlan(
    @Id
    @Column(name = "phone_plan_id", insertable = false, updatable = false)
    val id: Long,

    @Column(insertable = false, updatable = false)
    val cellExp: String,

    @Column(insertable = false, updatable = false)
    val category: String,

    @Column(insertable = false, updatable = false)
    val crtdDt: LocalDateTime,

    @Column(insertable = false, updatable = false)
    val dataExp: String,

    @Column(insertable = false, updatable = false)
    val frequency: String,

    @Column(insertable = false, updatable = false)
    val mailExp: String,

    @Column(insertable = false, updatable = false)
    val name: String,

    @Column(insertable = false, updatable = false)
    val price: Long,

    @Column(insertable = false, updatable = false)
    val updtDt: LocalDateTime,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "telecom_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT), insertable = false, updatable = false)
    val telecom: Telecom,
)

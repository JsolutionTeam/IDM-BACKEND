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

    /**
     * 요금제명
     */
    @Column(name = "name", insertable = false, updatable = false)
    val name: String,

    /**
     * 가격
     */
    @Column(name = "price", insertable = false, updatable = false)
    val price: Long,

    /**
     * 카테고리 ex) 5G일반, LTE일반 등...
     */
    @Column(name = "category", insertable = false, updatable = false)
    val category: String,

    /**
     * 음성통화 설명
     */
    @Column(name = "cell_exp", insertable = false, updatable = false)
    val cellExp: String,

    /**
     * 데이터 설명
     */
    @Column(name = "data_exp", insertable = false, updatable = false)
    val dataExp: String,

    /**
     * 문자 설명
     */
    @Column(name = "mail_exp", insertable = false, updatable = false)
    val mailExp: String,

    /**
     *  사용된 회수
     */
    @Column(name = "frequency", insertable = false, updatable = false)
    val frequency: Long,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "telecom_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT), insertable = false, updatable = false)
    val telecom: Telecom,

    @Column(name = "crtd_dt", insertable = false, updatable = false)
    val createdAt: LocalDateTime,

    @Column(name = "updt_dt", insertable = false, updatable = false)
    val updatedAt: LocalDateTime,
)

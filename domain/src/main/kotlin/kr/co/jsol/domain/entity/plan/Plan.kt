package kr.co.jsol.domain.entity.plan

import kr.co.jsol.common.domain.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Plan(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    var id: Long = 0,

    @Column(nullable = false)
    var planIdx: Long,
) : BaseEntity() {

    fun update(planDto: PlanDto) {
        this.planIdx = planDto.planIdx
    }
}

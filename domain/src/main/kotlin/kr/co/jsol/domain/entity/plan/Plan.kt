package kr.co.jsol.domain.entity.plan

import lombok.AccessLevel
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
class Plan(planDto: PlanDto) : TimeStamp() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private var idx: Long? = null

    @Column(nullable = false)
    private var planIdx: Long

    init {
        this.planIdx = planDto.getPlanIdx()
    }

    fun update(planDto: PlanDto) {
        this.planIdx = planDto.getPlanIdx()
    }
}

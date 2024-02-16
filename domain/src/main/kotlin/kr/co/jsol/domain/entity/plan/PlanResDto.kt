package kr.co.jsol.domain.entity.plan

import lombok.Getter

@Getter
@NoArgsConstructor
class PlanResDto(plan: Plan) {
    private val idx: Long

    private val planIdx: Long

    init {
        this.idx = plan.getIdx()
        this.planIdx = plan.getPlanIdx()
    }
}

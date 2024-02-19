package kr.co.jsol.domain.entity.plan

class PlanResDto(
    val id: Long,
    val planIdx: Long,
) {

    constructor(
        plan: Plan,
    ) : this(
        id = plan.id,
        planIdx = plan.planIdx,
    )
}

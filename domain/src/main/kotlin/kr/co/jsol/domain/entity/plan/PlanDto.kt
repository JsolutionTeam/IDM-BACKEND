package kr.co.jsol.domain.entity.plan

import javax.validation.constraints.NotNull

class PlanDto(
    @field:NotNull(message = "플랜 인덱스는 필수값입니다.")
    val planIdx: Long,
)

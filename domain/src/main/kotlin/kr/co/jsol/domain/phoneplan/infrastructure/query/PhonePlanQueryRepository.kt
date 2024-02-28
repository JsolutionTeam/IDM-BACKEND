package kr.co.jsol.domain.phoneplan.infrastructure.query

import com.querydsl.jpa.impl.JPAQueryFactory
import kr.co.jsol.common.repository.BaseQueryRepository
import kr.co.jsol.domain.phoneplan.entity.PhonePlan
import kr.co.jsol.domain.phoneplan.entity.QPhonePlan.Companion.phonePlan
import kr.co.jsol.domain.phoneplan.infrastructure.repository.PhonePlanRepository
import org.springframework.stereotype.Component

@Component
class PhonePlanQueryRepository(
    private val repository: PhonePlanRepository,
    private val queryFactory: JPAQueryFactory,
) : BaseQueryRepository<PhonePlan, Long>(phonePlan, repository) {

    fun getById(id: Long): PhonePlan {
        return repository.findOne(
            phonePlan.id.eq(id)
                .and(phonePlan.deletedAt.isNull)
        )
            .orElseThrow { throw IllegalArgumentException("사용자를 찾을 수 없습니다.") }
    }
}

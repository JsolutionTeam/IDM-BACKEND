package kr.co.jsol.domain.insurance.infrastructure.query

import com.querydsl.jpa.impl.JPAQueryFactory
import kr.co.jsol.common.repository.BaseQueryRepository
import kr.co.jsol.domain.insurance.entity.Insurance
import kr.co.jsol.domain.insurance.entity.QInsurance.Companion.insurance
import kr.co.jsol.domain.insurance.infrastructure.repository.InsuranceRepository
import org.springframework.stereotype.Component

@Component
class InsuranceQueryRepository(
    private val repository: InsuranceRepository,
    private val queryFactory: JPAQueryFactory,
) : BaseQueryRepository<Insurance, Long>(insurance, repository) {

    fun getById(id: Long): Insurance? {
        return queryFactory
            .selectFrom(insurance)
            .where(
                insurance.id.eq(id)
                    .and(insurance.deletedAt.isNull)
            )
            .fetchOne()
    }
}


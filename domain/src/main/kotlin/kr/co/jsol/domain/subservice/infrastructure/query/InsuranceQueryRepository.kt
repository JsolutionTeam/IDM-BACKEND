package kr.co.jsol.domain.subservice.infrastructure.query

import com.querydsl.jpa.impl.JPAQueryFactory
import kr.co.jsol.common.repository.BaseQueryRepository
import kr.co.jsol.domain.subservice.entity.QSubservice.Companion.subservice
import kr.co.jsol.domain.subservice.entity.Subservice
import kr.co.jsol.domain.subservice.infrastructure.repository.SubserviceRepository
import org.springframework.stereotype.Component

@Component
class InsuranceQueryRepository(
    private val repository: SubserviceRepository,
    private val queryFactory: JPAQueryFactory,
) : BaseQueryRepository<Subservice, Long>(subservice, repository) {

    fun getById(id: Long): Subservice? {
        return queryFactory
            .selectFrom(subservice)
            .where(
                subservice.id.eq(id)
                    .and(subservice.deletedAt.isNull)
            )
            .fetchOne()
    }
}


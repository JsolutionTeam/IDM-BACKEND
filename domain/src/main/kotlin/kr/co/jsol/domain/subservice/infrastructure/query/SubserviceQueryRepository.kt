package kr.co.jsol.domain.subservice.infrastructure.query

import com.querydsl.jpa.impl.JPAQueryFactory
import kr.co.jsol.common.exception.domain.subservice.SubserviceException
import kr.co.jsol.common.repository.BaseQueryRepository
import kr.co.jsol.domain.subservice.entity.QSubservice.Companion.subservice
import kr.co.jsol.domain.subservice.entity.Subservice
import kr.co.jsol.domain.subservice.infrastructure.repository.SubserviceRepository
import org.springframework.stereotype.Component

@Component
class SubserviceQueryRepository(
    private val repository: SubserviceRepository,
    private val queryFactory: JPAQueryFactory,
) : BaseQueryRepository<Subservice, Long>(subservice, repository) {

    fun getById(id: Long): Subservice {
        return repository.findOne(
            subservice.id.eq(id)
                .and(subservice.deletedAt.isNull)
        )
            .orElseThrow { throw SubserviceException.NotFoundByIdException() }
    }
}


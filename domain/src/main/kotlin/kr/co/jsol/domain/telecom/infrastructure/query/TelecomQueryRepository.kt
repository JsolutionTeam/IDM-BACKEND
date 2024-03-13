package kr.co.jsol.domain.telecom.infrastructure.query

import com.querydsl.jpa.impl.JPAQueryFactory
import kr.co.jsol.common.repository.BaseQueryRepository
import kr.co.jsol.domain.telecom.entity.QTelecom.Companion.telecom
import kr.co.jsol.domain.telecom.entity.Telecom
import kr.co.jsol.domain.telecom.infrastructure.repository.TelecomRepository
import org.springframework.stereotype.Component

@Component
class TelecomQueryRepository(
    private val repository: TelecomRepository,
    private val queryFactory: JPAQueryFactory,
) : BaseQueryRepository<Telecom, Long>(telecom, repository) {

    fun getById(id: Long): Telecom {
        return repository.findOne(
            telecom.id.eq(id)
                .and(telecom.deletedAt.isNull)
        )
            .orElseThrow { throw IllegalArgumentException("통신사를 찾을 수 없습니다.") }
    }
}

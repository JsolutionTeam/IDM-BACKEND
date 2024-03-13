package kr.co.jsol.domain.telecom.infrastructure.query

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import kr.co.jsol.common.repository.BaseQueryRepository
import kr.co.jsol.domain.telecom.application.dto.GetTelecomDto
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

    fun findAll(getTelecomDto: GetTelecomDto): List<Telecom> {
        val booleanBuilder = BooleanBuilder()
            .and(telecom.deletedAt.isNull)
            .and(nameContains(getTelecomDto.name))
        return repository.findAll(booleanBuilder).toList()
    }

    private fun nameContains(name: String?): BooleanExpression? {
        return name?.let { telecom.name.contains(it) }
    }
}

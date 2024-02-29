package kr.co.jsol.domain.color.infrastructure.query

import com.querydsl.jpa.impl.JPAQueryFactory
import kr.co.jsol.common.exception.domain.color.ColorException
import kr.co.jsol.common.repository.BaseQueryRepository
import kr.co.jsol.domain.color.entity.Color
import kr.co.jsol.domain.color.entity.QColor.Companion.color
import kr.co.jsol.domain.color.infrastructure.repository.ColorRepository
import org.springframework.stereotype.Component

@Component
class ColorQueryRepository(
    private val repository: ColorRepository,
    private val queryFactory: JPAQueryFactory,
) : BaseQueryRepository<Color, Long>(color, repository) {

    fun getById(id: Long): Color {
        return repository.findOne(
            color.id.eq(id)
                .and(color.deletedAt.isNull)
        )
            .orElseThrow { throw ColorException.NotFoundByIdException() }
    }
}

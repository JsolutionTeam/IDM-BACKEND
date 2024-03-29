package kr.co.jsol.domain.kiosk.infrastructure.query

import com.querydsl.jpa.impl.JPAQueryFactory
import kr.co.jsol.common.exception.domain.kiosk.KioskException
import kr.co.jsol.common.repository.BaseQueryRepository
import kr.co.jsol.domain.kiosk.entity.Kiosk
import kr.co.jsol.domain.kiosk.entity.QKiosk.Companion.kiosk
import kr.co.jsol.domain.kiosk.infrastructure.repository.KioskRepository
import org.springframework.stereotype.Component

@Component
class KioskQueryRepository(
    private val repository: KioskRepository,
    private val queryFactory: JPAQueryFactory,
) : BaseQueryRepository<Kiosk, Long>(kiosk, repository) {

    fun getById(id: Long): Kiosk {
        return findById(id) ?: throw KioskException.NotFoundByIdException()
    }
}

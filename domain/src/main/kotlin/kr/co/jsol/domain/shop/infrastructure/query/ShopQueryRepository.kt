package kr.co.jsol.domain.shop.infrastructure.query

import com.querydsl.jpa.impl.JPAQueryFactory
import kr.co.jsol.common.exception.domain.shop.ShopException
import kr.co.jsol.common.repository.BaseQueryRepository
import kr.co.jsol.domain.shop.entity.QShop.Companion.shop
import kr.co.jsol.domain.shop.entity.Shop
import kr.co.jsol.domain.shop.infrastructure.repository.ShopRepository
import org.springframework.stereotype.Component

@Component
class ShopQueryRepository(
    private val repository: ShopRepository,
    private val queryFactory: JPAQueryFactory,
) : BaseQueryRepository<Shop, Long>(shop, repository) {

    fun getById(id: Long): Shop {
        return repository.findById(id)
            .orElseThrow {
                throw ShopException.NotFoundByIdException()
            }
    }
}

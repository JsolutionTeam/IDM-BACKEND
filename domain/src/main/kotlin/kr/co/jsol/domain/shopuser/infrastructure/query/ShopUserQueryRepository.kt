package kr.co.jsol.domain.shopuser.infrastructure.query

import com.querydsl.jpa.impl.JPAQueryFactory
import kr.co.jsol.common.repository.BaseQueryRepository
import kr.co.jsol.domain.shopuser.entity.QShopUser.Companion.shopUser
import kr.co.jsol.domain.shopuser.entity.ShopUser
import kr.co.jsol.domain.shopuser.infrastructure.repository.ShopUserRepository
import org.springframework.stereotype.Component

@Component
class ShopUserQueryRepository(
    private val repository: ShopUserRepository,
    private val queryFactory: JPAQueryFactory,
) : BaseQueryRepository<ShopUser, String>(shopUser, repository) {

    fun getById(id: String): ShopUser {
        return repository.findOne(
            shopUser.id.eq(id)
        )
            .orElseThrow { throw IllegalArgumentException("사용자를 찾을 수 없습니다.") }
    }
}

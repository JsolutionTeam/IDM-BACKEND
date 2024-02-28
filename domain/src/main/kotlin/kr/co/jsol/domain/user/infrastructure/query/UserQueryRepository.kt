package kr.co.jsol.domain.user.infrastructure.query

import com.querydsl.jpa.impl.JPAQueryFactory
import kr.co.jsol.common.repository.BaseQueryRepository
import kr.co.jsol.domain.user.entity.QUser.Companion.user
import kr.co.jsol.domain.user.entity.User
import kr.co.jsol.domain.user.infrastructure.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class UserQueryRepository(
    private val repository: UserRepository,
    private val queryFactory: JPAQueryFactory,
) : BaseQueryRepository<User, String>(user, repository) {

    fun getById(id: String): User {
        return repository.findOne(
            user.id.eq(id)
                .and(user.deletedAt.isNull)
        )
            .orElseThrow { throw IllegalArgumentException("사용자를 찾을 수 없습니다.") }
    }
}

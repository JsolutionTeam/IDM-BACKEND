package kr.co.jsol.domain.account.infrastructure.query

import com.querydsl.core.BooleanBuilder
import com.querydsl.jpa.impl.JPAQueryFactory
import kr.co.jsol.common.exception.domain.account.AccountException
import kr.co.jsol.common.repository.BaseQueryRepository
import kr.co.jsol.domain.account.application.dto.GetAccountsDto
import kr.co.jsol.domain.account.entity.Account
import kr.co.jsol.domain.account.entity.QAccount.Companion.account
import kr.co.jsol.domain.account.infrastructure.repository.AccountRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class AccountQueryRepository(
    private val repository: AccountRepository,
    private val queryFactory: JPAQueryFactory,
) : BaseQueryRepository<Account, String>(account, repository) {

    fun getById(id: String): Account {
        return repository.findById(id)
            .orElseThrow { throw AccountException.NotFoundByIdException("계정 정보를 찾을 수 없습니다.") }
    }

    fun existsById(id: String): Boolean {
        return queryFactory.selectOne()
            .from(account)
            .where(
                account.deletedAt.isNull,
                account.id.eq(id),
            )
            .fetchFirst() != null
    }

    fun findOffsetPageBySearch(
        shopCompaniesDto: GetAccountsDto,
        pageable: Pageable,
    ): Page<Account> {
        val booleanBuilder = BooleanBuilder()
            .and(account.deletedAt.isNull)
            .and(account.shop.id.eq(shopCompaniesDto.shopId))
        return repository.findAll(booleanBuilder, pageable)
    }
}

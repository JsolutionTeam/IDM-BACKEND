package kr.co.jsol.domain.entity.account.infrastructure.repository

import kr.co.jsol.domain.entity.account.entity.Account
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository :
    CrudRepository<Account, String>,
    QuerydslPredicateExecutor<Account>

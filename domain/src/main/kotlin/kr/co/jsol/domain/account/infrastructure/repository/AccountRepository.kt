package kr.co.jsol.domain.account.infrastructure.repository

import kr.co.jsol.domain.account.entity.Account
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository :
    CrudRepository<Account, String>,
    QuerydslPredicateExecutor<Account>

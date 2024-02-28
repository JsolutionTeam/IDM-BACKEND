package kr.co.jsol.domain.user.infrastructure.repository

import kr.co.jsol.domain.user.entity.User
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository :
    CrudRepository<User, String>,
    QuerydslPredicateExecutor<User>

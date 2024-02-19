package kr.co.jsol.domain.entity.shopuser.infrastructure.repository

import kr.co.jsol.domain.entity.shopuser.entity.ShopUser
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ShopUserRepository :
    CrudRepository<ShopUser, String>,
    QuerydslPredicateExecutor<ShopUser>

package kr.co.jsol.domain.shop.infrastructure.repository

import kr.co.jsol.domain.shop.entity.Shop
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ShopRepository :
    CrudRepository<Shop, Long>,
    QuerydslPredicateExecutor<Shop>

package kr.co.jsol.domain.insurance.infrastructure.repository

import kr.co.jsol.domain.insurance.entity.Insurance
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface InsuranceRepository :
    CrudRepository<Insurance, Long>,
    QuerydslPredicateExecutor<Insurance>

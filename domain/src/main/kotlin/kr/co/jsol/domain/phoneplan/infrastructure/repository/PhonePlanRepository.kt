package kr.co.jsol.domain.phoneplan.infrastructure.repository

import kr.co.jsol.domain.phoneplan.entity.PhonePlan
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PhonePlanRepository :
    CrudRepository<PhonePlan, Long>,
    QuerydslPredicateExecutor<PhonePlan>

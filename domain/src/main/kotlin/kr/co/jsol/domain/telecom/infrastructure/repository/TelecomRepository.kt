package kr.co.jsol.domain.telecom.infrastructure.repository

import kr.co.jsol.domain.telecom.entity.Telecom
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TelecomRepository :
    CrudRepository<Telecom, Long>,
    QuerydslPredicateExecutor<Telecom>

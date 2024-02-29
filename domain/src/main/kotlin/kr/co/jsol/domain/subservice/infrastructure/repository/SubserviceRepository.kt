package kr.co.jsol.domain.subservice.infrastructure.repository

import kr.co.jsol.domain.subservice.entity.Subservice
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SubserviceRepository :
    CrudRepository<Subservice, Long>,
    QuerydslPredicateExecutor<Subservice>

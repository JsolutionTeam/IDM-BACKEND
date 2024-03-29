package kr.co.jsol.domain.kiosk.infrastructure.repository

import kr.co.jsol.domain.kiosk.entity.Kiosk
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface KioskRepository :
    CrudRepository<Kiosk, Long>,
    QuerydslPredicateExecutor<Kiosk>

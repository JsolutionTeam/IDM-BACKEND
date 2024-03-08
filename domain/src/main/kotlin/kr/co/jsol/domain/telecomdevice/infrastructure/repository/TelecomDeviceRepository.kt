package kr.co.jsol.domain.telecomdevice.infrastructure.repository

import kr.co.jsol.domain.telecomdevice.entity.TelecomDevice
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TelecomDeviceRepository :
    CrudRepository<TelecomDevice, Long>,
    QuerydslPredicateExecutor<TelecomDevice>


package kr.co.jsol.domain.device.infrastructure.repository

import kr.co.jsol.domain.device.entity.Device
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.data.repository.CrudRepository

interface DeviceRepository :
    CrudRepository<Device, Long>,
    QuerydslPredicateExecutor<Device>

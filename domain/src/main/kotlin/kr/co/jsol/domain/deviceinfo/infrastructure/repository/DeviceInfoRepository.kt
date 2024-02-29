package kr.co.jsol.domain.deviceinfo.infrastructure.repository

import kr.co.jsol.domain.deviceinfo.entity.DeviceInfo
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DeviceInfoRepository :
    CrudRepository<DeviceInfo, Long>,
    QuerydslPredicateExecutor<DeviceInfo>


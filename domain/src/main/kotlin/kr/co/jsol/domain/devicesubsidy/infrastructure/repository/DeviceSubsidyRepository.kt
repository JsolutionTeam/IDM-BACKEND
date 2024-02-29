package kr.co.jsol.domain.devicesubsidy.infrastructure.repository

import kr.co.jsol.domain.devicesubsidy.entity.DeviceSubsidy
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DeviceSubsidyRepository :
    CrudRepository<DeviceSubsidy, Long>,
    QuerydslPredicateExecutor<DeviceSubsidy>

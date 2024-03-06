package kr.co.jsol.domain.deviceapplicationform.infrastructure.repository

import kr.co.jsol.domain.deviceapplicationform.entity.DeviceApplicationFormSubservice
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DeviceApplicationFormSubserviceRepository :
    CrudRepository<DeviceApplicationFormSubservice, Long>,
    QuerydslPredicateExecutor<DeviceApplicationFormSubservice>

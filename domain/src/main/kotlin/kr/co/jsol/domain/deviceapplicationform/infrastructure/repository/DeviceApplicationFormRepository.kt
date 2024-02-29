package kr.co.jsol.domain.deviceapplicationform.infrastructure.repository

import kr.co.jsol.domain.deviceapplicationform.entity.DeviceApplicationForm
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DeviceApplicationFormRepository :
    CrudRepository<DeviceApplicationForm, Long>,
    QuerydslPredicateExecutor<DeviceApplicationForm>

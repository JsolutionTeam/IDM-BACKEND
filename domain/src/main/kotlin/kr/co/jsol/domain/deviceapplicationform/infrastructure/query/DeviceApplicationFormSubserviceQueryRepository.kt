package kr.co.jsol.domain.deviceapplicationform.infrastructure.query

import com.querydsl.jpa.impl.JPAQueryFactory
import kr.co.jsol.common.exception.domain.deviceapplicationform.DeviceApplicationFormException
import kr.co.jsol.common.repository.BaseQueryRepository
import kr.co.jsol.domain.deviceapplicationform.entity.DeviceApplicationFormSubservice
import kr.co.jsol.domain.deviceapplicationform.entity.QDeviceApplicationFormSubservice.Companion.deviceApplicationFormSubservice
import kr.co.jsol.domain.deviceapplicationform.infrastructure.repository.DeviceApplicationFormSubserviceRepository
import org.springframework.stereotype.Component

@Component
class DeviceApplicationFormSubserviceQueryRepository(
    private val repository: DeviceApplicationFormSubserviceRepository,
    private val queryFactory: JPAQueryFactory,
) : BaseQueryRepository<DeviceApplicationFormSubservice, Long>(deviceApplicationFormSubservice, repository) {

    fun getById(id: Long): DeviceApplicationFormSubservice {
        return findById(id) ?: throw DeviceApplicationFormException.NotFoundByIdException()
    }

    fun getByDeviceApplicationFormId(deviceApplicationFormId: Long): List<DeviceApplicationFormSubservice> {
        return queryFactory
            .selectFrom(deviceApplicationFormSubservice)
            .where(
                deviceApplicationFormSubservice.deviceApplicationForm.id.eq(deviceApplicationFormId)
                    .and(deviceApplicationFormSubservice.deletedAt.isNull())
            )
            .fetch()
    }
}


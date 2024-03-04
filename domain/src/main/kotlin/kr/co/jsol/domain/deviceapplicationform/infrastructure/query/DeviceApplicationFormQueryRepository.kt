package kr.co.jsol.domain.deviceapplicationform.infrastructure.query

import com.querydsl.jpa.impl.JPAQueryFactory
import kr.co.jsol.common.repository.BaseQueryRepository
import kr.co.jsol.domain.deviceapplicationform.entity.DeviceApplicationForm
import kr.co.jsol.domain.deviceapplicationform.entity.QDeviceApplicationForm.Companion.deviceApplicationForm
import kr.co.jsol.domain.deviceapplicationform.infrastructure.repository.DeviceApplicationFormRepository
import org.springframework.stereotype.Component

@Component
class DeviceApplicationFormQueryRepository(
    private val repository: DeviceApplicationFormRepository,
    private val queryFactory: JPAQueryFactory,
) : BaseQueryRepository<DeviceApplicationForm, Long>(deviceApplicationForm, repository) {

    fun getById(id: Long): DeviceApplicationForm? {
        return queryFactory
            .selectFrom(deviceApplicationForm)
            .where(
                deviceApplicationForm.id.eq(id)
                    .and(deviceApplicationForm.deletedAt.isNull)
            )
            .fetchOne()
    }
}


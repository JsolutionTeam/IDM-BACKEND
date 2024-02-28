package kr.co.jsol.domain.device.infrastructure.query

import com.querydsl.jpa.impl.JPAQueryFactory
import kr.co.jsol.common.repository.BaseQueryRepository
import kr.co.jsol.domain.device.entity.Device
import kr.co.jsol.domain.device.entity.QDevice.Companion.device
import kr.co.jsol.domain.device.infrastructure.repository.DeviceRepository
import org.springframework.stereotype.Component

@Component
class DeviceQueryRepository(
    private val repository: DeviceRepository,
    private val queryFactory: JPAQueryFactory,
) : BaseQueryRepository<Device, Long>(device, repository) {

    fun getById(id: Long): Device {
        return repository.findOne(
            device.id.eq(id)
                .and(device.deletedAt.isNull)
        )
            .orElseThrow { throw IllegalArgumentException("사용자를 찾을 수 없습니다.") }
    }
}

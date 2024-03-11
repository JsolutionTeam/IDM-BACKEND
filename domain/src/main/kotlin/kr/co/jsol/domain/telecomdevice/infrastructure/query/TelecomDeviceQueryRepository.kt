package kr.co.jsol.domain.telecomdevice.infrastructure.query

import com.querydsl.core.BooleanBuilder
import com.querydsl.jpa.impl.JPAQueryFactory
import kr.co.jsol.common.exception.domain.deviceinfo.DeviceInfoException
import kr.co.jsol.common.repository.BaseQueryRepository
import kr.co.jsol.domain.telecomdevice.application.dto.GetTelecomDevicesDto
import kr.co.jsol.domain.telecomdevice.entity.QTelecomDevice.Companion.telecomDevice
import kr.co.jsol.domain.telecomdevice.entity.TelecomDevice
import kr.co.jsol.domain.telecomdevice.infrastructure.repository.TelecomDeviceRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component

@Component
class TelecomDeviceQueryRepository(
    private val repository: TelecomDeviceRepository,
    private val queryFactory: JPAQueryFactory,
) : BaseQueryRepository<TelecomDevice, Long>(telecomDevice, repository) {

    fun getById(id: Long): TelecomDevice {
        return repository.findOne(
            telecomDevice.id.eq(id)
                .and(telecomDevice.deletedAt.isNull)
        )
            .orElseThrow { throw DeviceInfoException.NotFoundByIdException() }
    }

    fun findListByIdList(
        ids: List<Long>,
    ): List<TelecomDevice> {
        return queryFactory
            .selectFrom(telecomDevice)
            .where(
                telecomDevice.id.`in`(ids)
                    .and(telecomDevice.deletedAt.isNull)
            )
            .fetch()
    }

    fun findOffsetPageBySearch(
        getTelecomDevicesDto: GetTelecomDevicesDto,
        pageable: Pageable,
    ): Page<TelecomDevice> {
        val booleanBuilder = BooleanBuilder()
            .and(telecomDevice.deletedAt.isNull)

        val sortBy = Sort.by(
            listOf(
                Sort.Order(Sort.Direction.ASC, "displayOrder"),
                Sort.Order(Sort.Direction.DESC, "updatedAt"),
            )
        )

        return repository.findAll(
            booleanBuilder,
            PageRequest.of(
                pageable.pageNumber,
                pageable.pageSize,
                sortBy,
            ),
        )
    }

    fun findAllOrderAscAndUpdatedAtDesc(): List<TelecomDevice> {
        return queryFactory
            .selectFrom(telecomDevice)
            .where(
                telecomDevice.deletedAt.isNull
            )
            .orderBy(
                telecomDevice.displayOrder.asc(),
                telecomDevice.updatedAt.desc(),
            )
            .fetch()
    }
}

package kr.co.jsol.domain.telecomdevice.infrastructure.query

import com.querydsl.core.BooleanBuilder
import com.querydsl.jpa.impl.JPAQueryFactory
import kr.co.jsol.common.config.pagination
import kr.co.jsol.common.exception.domain.deviceinfo.DeviceInfoException
import kr.co.jsol.common.repository.BaseQueryRepository
import kr.co.jsol.domain.telecomdevice.application.dto.GetTelecomDevicesDto
import kr.co.jsol.domain.telecomdevice.entity.QTelecomDevice.Companion.telecomDevice
import kr.co.jsol.domain.telecomdevice.entity.TelecomDevice
import kr.co.jsol.domain.telecomdevice.infrastructure.repository.TelecomDeviceRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
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

        getTelecomDevicesDto.modelName?.let { booleanBuilder.and(telecomDevice.modelName.contains(it)) }
        getTelecomDevicesDto.petName?.let { booleanBuilder.and(telecomDevice.petName.contains(it)) }
        getTelecomDevicesDto.price?.let { booleanBuilder.and(telecomDevice.price.contains(it)) }
        getTelecomDevicesDto.companySubsidy?.let { booleanBuilder.and(telecomDevice.companySubsidy.contains(it)) }
        getTelecomDevicesDto.phonePlan?.let { booleanBuilder.and(telecomDevice.phonePlan.contains(it)) }
        getTelecomDevicesDto.totalPrice?.let { booleanBuilder.and(telecomDevice.totalPrice.contains(it)) }
        getTelecomDevicesDto.isDisplay?.let { booleanBuilder.and(telecomDevice.isDisplay.eq(it)) }
        getTelecomDevicesDto.etc1?.let { booleanBuilder.and(telecomDevice.etc1.contains(it)) }
        getTelecomDevicesDto.etc2?.let { booleanBuilder.and(telecomDevice.etc2.contains(it)) }
        getTelecomDevicesDto.etc3?.let { booleanBuilder.and(telecomDevice.etc3.contains(it)) }
        getTelecomDevicesDto.etc4?.let { booleanBuilder.and(telecomDevice.etc4.contains(it)) }
        getTelecomDevicesDto.etc5?.let { booleanBuilder.and(telecomDevice.etc5.contains(it)) }
        getTelecomDevicesDto.etc6?.let { booleanBuilder.and(telecomDevice.etc6.contains(it)) }

        val query = queryFactory
            .from(telecomDevice)
            .where(booleanBuilder)

        val result = query.clone()
            .select(telecomDevice)
            .pagination(pageable)
            .orderBy(
                telecomDevice.displayOrder.asc(),
                telecomDevice.updatedAt.desc(),
            )
            .fetch()

        val count = query.clone()
            .select(telecomDevice.id.count())
            .fetchOne() ?: 0L

        return PageImpl(result, pageable, count)
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

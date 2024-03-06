package kr.co.jsol.domain.devicesubsidy.infrastructure.query

import com.querydsl.core.BooleanBuilder
import com.querydsl.jpa.impl.JPAQueryFactory
import kr.co.jsol.common.exception.domain.devicesubsidy.DeviceSubsidyException
import kr.co.jsol.common.repository.BaseQueryRepository
import kr.co.jsol.domain.devicesubsidy.application.dto.ExistsDeviceSubsidyDto
import kr.co.jsol.domain.devicesubsidy.application.dto.GetDeviceSubsidiesDto
import kr.co.jsol.domain.devicesubsidy.application.dto.GetDeviceSubsidyPriceDto
import kr.co.jsol.domain.devicesubsidy.entity.DeviceSubsidy
import kr.co.jsol.domain.devicesubsidy.entity.QDeviceSubsidy.Companion.deviceSubsidy
import kr.co.jsol.domain.devicesubsidy.infrastructure.repository.DeviceSubsidyRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class DeviceSubsidyQueryRepository(
    private val repository: DeviceSubsidyRepository,
    private val queryFactory: JPAQueryFactory,
) : BaseQueryRepository<DeviceSubsidy, Long>(deviceSubsidy, repository) {

    fun getById(id: Long): DeviceSubsidy {
        return repository.findOne(
            deviceSubsidy.id.eq(id)
                .and(deviceSubsidy.deletedAt.isNull)
        )
            .orElseThrow { throw DeviceSubsidyException.NotFoundByIdException() }
    }

    fun exists(existsDeviceSubsidyDto: ExistsDeviceSubsidyDto): Boolean {
        val booleanBuilder = BooleanBuilder()
            .and(deviceSubsidy.telecom.id.eq(existsDeviceSubsidyDto.telecomId))
            .and(deviceSubsidy.phonePlan.id.eq(existsDeviceSubsidyDto.phonePlanId))
            .and(deviceSubsidy.device.id.eq(existsDeviceSubsidyDto.deviceId))
            .and(deviceSubsidy.deletedAt.isNull)

        return queryFactory.selectOne()
            .from(deviceSubsidy)
            .where(booleanBuilder)
            .fetchFirst() != null
    }

    fun getPrice(getDeviceSubsidyPriceDto: GetDeviceSubsidyPriceDto): Long {
        val booleanBuilder = BooleanBuilder()
            .and(deviceSubsidy.telecom.id.eq(getDeviceSubsidyPriceDto.telecomId))
            .and(deviceSubsidy.phonePlan.id.eq(getDeviceSubsidyPriceDto.phonePlanId))
            .and(deviceSubsidy.device.id.eq(getDeviceSubsidyPriceDto.deviceId))
            .and(deviceSubsidy.deletedAt.isNull)

        return queryFactory.select(deviceSubsidy.price)
            .from(deviceSubsidy)
            .where(booleanBuilder)
            .fetchFirst() ?: throw DeviceSubsidyException.NotFoundBySearchException()
    }

    fun findListByIdList(
        ids: List<Long>,
    ): List<DeviceSubsidy> {
        return queryFactory
            .selectFrom(deviceSubsidy)
            .where(
                deviceSubsidy.id.`in`(ids)
                    .and(deviceSubsidy.deletedAt.isNull)
            )
            .fetch()
    }

    fun findOffsetPageBySearch(
        getCompanySubsidiesDto: GetDeviceSubsidiesDto,
        pageable: Pageable,
    ): Page<DeviceSubsidy> {
        val booleanBuilder = BooleanBuilder()
            .and(deviceSubsidy.deletedAt.isNull)

        getCompanySubsidiesDto.priceMin?.let {
            booleanBuilder.and(deviceSubsidy.price.goe(it))
        }
        getCompanySubsidiesDto.priceMax?.let {
            booleanBuilder.and(deviceSubsidy.price.loe(it))
        }
        getCompanySubsidiesDto.telecomId?.let {
            booleanBuilder.and(deviceSubsidy.telecom.id.eq(it))
        }
        getCompanySubsidiesDto.phonePlanId?.let {
            booleanBuilder.and(deviceSubsidy.phonePlan.id.eq(it))
        }
        getCompanySubsidiesDto.deviceId?.let {
            booleanBuilder.and(deviceSubsidy.device.id.eq(it))
        }

        return repository.findAll(
            booleanBuilder,
            pageable,
        )
    }
}

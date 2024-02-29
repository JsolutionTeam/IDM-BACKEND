package kr.co.jsol.domain.devicesubsidy.infrastructure.query

import com.querydsl.core.BooleanBuilder
import com.querydsl.jpa.impl.JPAQueryFactory
import kr.co.jsol.common.exception.domain.devicesubsidy.DeviceSubsidyException
import kr.co.jsol.common.repository.BaseQueryRepository
import kr.co.jsol.domain.devicesubsidy.application.dto.GetDeviceSubsidiesDto
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

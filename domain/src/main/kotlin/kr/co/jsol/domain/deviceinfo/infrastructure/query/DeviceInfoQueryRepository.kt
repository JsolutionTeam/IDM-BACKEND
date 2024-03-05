package kr.co.jsol.domain.deviceinfo.infrastructure.query

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.group.GroupBy.groupBy
import com.querydsl.core.group.GroupBy.list
import com.querydsl.jpa.impl.JPAQueryFactory
import kr.co.jsol.common.exception.domain.deviceinfo.DeviceInfoException
import kr.co.jsol.common.repository.BaseQueryRepository
import kr.co.jsol.domain.color.entity.QColor.Companion.color
import kr.co.jsol.domain.device.entity.QDevice.Companion.device
import kr.co.jsol.domain.deviceinfo.application.dto.GetDeviceInfoSearchDto
import kr.co.jsol.domain.deviceinfo.application.dto.GetDeviceInfosDto
import kr.co.jsol.domain.deviceinfo.entity.DeviceInfo
import kr.co.jsol.domain.deviceinfo.entity.QDeviceInfo.Companion.deviceInfo
import kr.co.jsol.domain.deviceinfo.infrastructure.dto.DeviceInfoGroupByDeviceSeriesDto
import kr.co.jsol.domain.deviceinfo.infrastructure.dto.QDeviceInfoGroupByDeviceSeriesDto
import kr.co.jsol.domain.deviceinfo.infrastructure.dto.QDeviceInfoGroupByDeviceSeriesDto_Color
import kr.co.jsol.domain.deviceinfo.infrastructure.dto.QDeviceInfoGroupByDeviceSeriesDto_Device
import kr.co.jsol.domain.deviceinfo.infrastructure.repository.DeviceInfoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class DeviceInfoQueryRepository(
    private val repository: DeviceInfoRepository,
    private val queryFactory: JPAQueryFactory,
) : BaseQueryRepository<DeviceInfo, Long>(deviceInfo, repository) {

    fun getById(id: Long): DeviceInfo {
        return repository.findOne(
            deviceInfo.id.eq(id)
                .and(deviceInfo.deletedAt.isNull)
        )
            .orElseThrow { throw DeviceInfoException.NotFoundByIdException() }
    }

    fun findBySeriesAndDeviceIdAndColorId(
        getDeviceInfoSearchDto: GetDeviceInfoSearchDto,
    ): DeviceInfo? {
        return repository.findOne(
            deviceInfo.device.series.eq(getDeviceInfoSearchDto.series)
                .and(deviceInfo.device.id.eq(getDeviceInfoSearchDto.deviceId))
                .and(deviceInfo.color.id.eq(getDeviceInfoSearchDto.colorId))
                .and(deviceInfo.deletedAt.isNull)
        ).orElse(null)
    }

    fun findListByIdList(
        ids: List<Long>,
    ): List<DeviceInfo> {
        return queryFactory
            .selectFrom(deviceInfo)
            .where(
                deviceInfo.id.`in`(ids)
                    .and(deviceInfo.deletedAt.isNull)
            )
            .fetch()
    }

    fun findOffsetPageBySearch(
        getDeviceInfosDto: GetDeviceInfosDto,
        pageable: Pageable,
    ): Page<DeviceInfo> {
        val booleanBuilder = BooleanBuilder()
            .and(deviceInfo.deletedAt.isNull)

        getDeviceInfosDto.barcode?.let {
            booleanBuilder.and(deviceInfo.barcode.eq(it))
        }
        getDeviceInfosDto.barcodeColor?.let {
            booleanBuilder.and(deviceInfo.barcodeColor.eq(it))
        }
        getDeviceInfosDto.devicePetName?.let {
            if (it.isNotBlank()) booleanBuilder.and(deviceInfo.device.petName.contains(it))
        }
        getDeviceInfosDto.deviceModelName?.let {
            if (it.isNotBlank()) booleanBuilder.and(deviceInfo.device.modelName.contains(it))
        }
        getDeviceInfosDto.deviceSeries?.let {
            if (it.isNotBlank()) booleanBuilder.and(deviceInfo.device.series.contains(it))
        }
        getDeviceInfosDto.devicePriceMin?.let {
            booleanBuilder.and(deviceInfo.device.price.goe(it))
        }
        getDeviceInfosDto.devicePriceMax?.let {
            booleanBuilder.and(deviceInfo.device.price.loe(it))
        }
        getDeviceInfosDto.deviceVolume?.let {
            if (it.isNotBlank()) booleanBuilder.and(deviceInfo.device.volume.contains(it))
        }
        getDeviceInfosDto.makerId?.let {
            booleanBuilder.and(deviceInfo.device.maker.id.eq(it))
        }
        getDeviceInfosDto.colorId?.let {
            booleanBuilder.and(deviceInfo.color.id.eq(it))
        }
        getDeviceInfosDto.colorName?.let {
            if (it.isNotBlank()) booleanBuilder.and(deviceInfo.color.name.contains(it))
        }

        return repository.findAll(
            booleanBuilder,
            pageable,
        )
    }

    fun findByDeviceSeriesGroupByDeviceSeries(series: String): List<DeviceInfoGroupByDeviceSeriesDto> {
        return queryFactory
            .selectFrom(deviceInfo)
            .innerJoin(device)
            .on(
                deviceInfo.device.id.eq(device.id),
                device.deletedAt.isNull
            )
            .innerJoin(color)
            .on(
                deviceInfo.color.id.eq(color.id),
                color.deletedAt.isNull
            )
            .where(
                deviceInfo.device.series.contains(series)
                    .and(deviceInfo.deletedAt.isNull)
            )
            .groupBy(
                deviceInfo.device.series,
            )
            .transform(
                groupBy(deviceInfo.device.series).list(
                    QDeviceInfoGroupByDeviceSeriesDto(
                        deviceInfo.device.series,
                        list(
                            QDeviceInfoGroupByDeviceSeriesDto_Device(
                                device.id,
                                device.volume,
                                device.price
                            )
                        ),
                        list(
                            QDeviceInfoGroupByDeviceSeriesDto_Color(
                                color.id,
                                color.name,
                                color.displayValue,
                            )
                        )
                    )
                )
            )
    }
}

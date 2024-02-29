package kr.co.jsol.domain.deviceinfo.application

import kr.co.jsol.domain.color.infrastructure.query.ColorQueryRepository
import kr.co.jsol.domain.device.infrastructure.query.DeviceQueryRepository
import kr.co.jsol.domain.deviceinfo.application.dto.CreateDeviceInfoDto
import kr.co.jsol.domain.deviceinfo.infrastructure.dto.DeviceInfoDto
import kr.co.jsol.domain.deviceinfo.infrastructure.dto.DeviceInfoGroupByDeviceSeriesDto
import kr.co.jsol.domain.deviceinfo.infrastructure.query.DeviceInfoQueryRepository
import kr.co.jsol.domain.deviceinfo.infrastructure.repository.DeviceInfoRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeviceInfoService(
    private val repository: DeviceInfoRepository,
    private val query: DeviceInfoQueryRepository,

    private val deviceQuery: DeviceQueryRepository,
    private val colorQuery: ColorQueryRepository,
) {

    @Transactional
    fun create(createDeviceInfoDto: CreateDeviceInfoDto): DeviceInfoDto {
        return DeviceInfoDto(
            repository.save(
                createDeviceInfoDto.toEntity(
                    device = deviceQuery.getById(createDeviceInfoDto.deviceId),
                    color = colorQuery.getById(createDeviceInfoDto.colorId)
                )
            )
        )
    }

    fun findByDeviceSeriesGroupByDeviceSeries(series: String): List<DeviceInfoGroupByDeviceSeriesDto> {
        return query.findByDeviceSeriesGroupByDeviceSeries(series)
    }

    @Transactional(readOnly = true)
    fun getById(id: Long): DeviceInfoDto {
        return DeviceInfoDto(query.getById(id))
    }
}

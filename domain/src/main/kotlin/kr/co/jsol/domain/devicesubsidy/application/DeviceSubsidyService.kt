package kr.co.jsol.domain.devicesubsidy.application

import kr.co.jsol.domain.device.infrastructure.query.DeviceQueryRepository
import kr.co.jsol.domain.devicesubsidy.application.dto.CreateDeviceSubsidyDto
import kr.co.jsol.domain.devicesubsidy.application.dto.DeleteDeviceSubsidiesDto
import kr.co.jsol.domain.devicesubsidy.application.dto.ExistsDeviceSubsidyDto
import kr.co.jsol.domain.devicesubsidy.application.dto.GetDeviceSubsidiesDto
import kr.co.jsol.domain.devicesubsidy.application.dto.GetDeviceSubsidyPriceDto
import kr.co.jsol.domain.devicesubsidy.application.dto.UpdateDeviceSubsidiesDto
import kr.co.jsol.domain.devicesubsidy.infrastructure.dto.DeviceSubsidyDto
import kr.co.jsol.domain.devicesubsidy.infrastructure.query.DeviceSubsidyQueryRepository
import kr.co.jsol.domain.devicesubsidy.infrastructure.repository.DeviceSubsidyRepository
import kr.co.jsol.domain.phoneplan.infrastructure.query.PhonePlanQueryRepository
import kr.co.jsol.domain.shop.infrastructure.query.ShopQueryRepository
import kr.co.jsol.domain.telecom.infrastructure.query.TelecomQueryRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeviceSubsidyService(
    private val repository: DeviceSubsidyRepository,
    private val query: DeviceSubsidyQueryRepository,

    private val shopQuery: ShopQueryRepository,
    private val telecomQuery: TelecomQueryRepository,
    private val phonePlanQuery: PhonePlanQueryRepository,
    private val deviceQuery: DeviceQueryRepository,
) {

    @Transactional
    fun create(createDeviceSubsidyDto: CreateDeviceSubsidyDto): DeviceSubsidyDto {
        return DeviceSubsidyDto(
            repository.save(
                createDeviceSubsidyDto.toEntity(
                    telecom = telecomQuery.getById(createDeviceSubsidyDto.telecomId),
                    phonePlan = phonePlanQuery.getById(createDeviceSubsidyDto.phonePlanId),
                    device = deviceQuery.getById(createDeviceSubsidyDto.deviceId),
                )
            )
        )
    }

    @Transactional
    fun updateMultiple(updateDeviceSubsidiesDto: UpdateDeviceSubsidiesDto) {
        repository.saveAll(updateDeviceSubsidiesDto.deviceSubsidies.map { ds ->
            val deviceSubsidy = query.getById(ds.id)

            ds.telecomId?.let { deviceSubsidy.telecom = telecomQuery.getById(it) }
            ds.phonePlanId?.let { deviceSubsidy.phonePlan = phonePlanQuery.getById(it) }
            ds.deviceId?.let { deviceSubsidy.device = deviceQuery.getById(it) }

            deviceSubsidy.update(ds)
            deviceSubsidy
        })
    }

    @Transactional
    fun deleteMultiple(deleteDeviceSubsidiesDto: DeleteDeviceSubsidiesDto) {
        repository.deleteAll(deleteDeviceSubsidiesDto.ids.map { query.getById(it) })
    }

    @Transactional(readOnly = true)
    fun getById(id: Long): DeviceSubsidyDto {
        return DeviceSubsidyDto(query.getById(id))
    }

    @Transactional(readOnly = true)
    fun exists(existsDeviceSubsidyDto: ExistsDeviceSubsidyDto): Boolean {
        return query.exists(existsDeviceSubsidyDto)
    }

    @Transactional(readOnly = true)
    fun getPrice(getDeviceSubsidyPriceDto: GetDeviceSubsidyPriceDto): Long {
        return query.getPrice(getDeviceSubsidyPriceDto)
    }

    @Transactional(readOnly = true)
    fun findOffsetPageBySearch(
        getDeviceSubsidiesDto: GetDeviceSubsidiesDto,
        pageable: Pageable,
    ): Page<DeviceSubsidyDto> {
        return query.findOffsetPageBySearch(getDeviceSubsidiesDto, pageable)
            .map { DeviceSubsidyDto(it) }
    }
}

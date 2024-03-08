package kr.co.jsol.domain.telecomdevice.application

import kr.co.jsol.domain.telecomdevice.application.dto.CreateTelecomDeviceDto
import kr.co.jsol.domain.telecomdevice.application.dto.GetTelecomDevicesDto
import kr.co.jsol.domain.telecomdevice.application.dto.UpdateTelecomDeviceDto
import kr.co.jsol.domain.telecomdevice.infrastructure.dto.TelecomDeviceDto
import kr.co.jsol.domain.telecomdevice.infrastructure.query.TelecomDeviceQueryRepository
import kr.co.jsol.domain.telecomdevice.infrastructure.repository.TelecomDeviceRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TelecomDeviceService(
    private val repository: TelecomDeviceRepository,
    private val query: TelecomDeviceQueryRepository,
) {

    @Transactional
    fun create(createTelecomDeviceDto: CreateTelecomDeviceDto): TelecomDeviceDto {
        return TelecomDeviceDto(repository.save(createTelecomDeviceDto.toEntity()))
    }

    @Transactional
    fun update(updateTelecomDeviceDto: UpdateTelecomDeviceDto): TelecomDeviceDto {
        val telecomDevice = query.getById(updateTelecomDeviceDto.id)
        telecomDevice.update(updateTelecomDeviceDto)
        return TelecomDeviceDto(repository.save(telecomDevice))
    }

    @Transactional(readOnly = true)
    fun getById(id: Long): TelecomDeviceDto {
        return TelecomDeviceDto(query.getById(id))
    }

    @Transactional(readOnly = true)
    fun findOffsetPageBySearch(
        getDeviceInfosDto: GetTelecomDevicesDto,
        pageable: Pageable,
    ): Page<TelecomDeviceDto> {
        return query.findOffsetPageBySearch(getDeviceInfosDto, pageable)
            .map(::TelecomDeviceDto)
    }
}

package kr.co.jsol.domain.telecomdevice.application

import kr.co.jsol.common.exception.domain.deviceinfo.DeviceInfoException
import kr.co.jsol.common.file.application.FileService
import kr.co.jsol.common.file.application.dto.FileDto
import kr.co.jsol.domain.device.infrastructure.query.DeviceQueryRepository
import kr.co.jsol.domain.deviceinfo.infrastructure.query.DeviceInfoQueryRepository
import kr.co.jsol.domain.telecomdevice.application.dto.CreateTelecomDeviceDto
import kr.co.jsol.domain.telecomdevice.application.dto.GetTelecomDevicesDto
import kr.co.jsol.domain.telecomdevice.application.dto.PostTelecomDeviceImageDto
import kr.co.jsol.domain.telecomdevice.application.dto.UpdateTelecomDeviceDto
import kr.co.jsol.domain.telecomdevice.application.dto.UpdateTelecomDeviceIsDisplayDto
import kr.co.jsol.domain.telecomdevice.application.dto.UpdateTelecomDeviceIsDisplaysDto
import kr.co.jsol.domain.telecomdevice.application.dto.UpdateTelecomDevicesDto
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

    private val fileService: FileService,

    private val deviceQuery: DeviceQueryRepository,
    private val deviceInfoQuery: DeviceInfoQueryRepository,
) {

    @Transactional
    fun create(createTelecomDeviceDto: CreateTelecomDeviceDto): TelecomDeviceDto {
        val device = deviceQuery.getById(createTelecomDeviceDto.deviceId)
        val imageUrl = try {
            deviceInfoQuery.getFirstByDeviceId(device.id).imageUrl
        } catch (e: DeviceInfoException.NotFoundByDeviceIdException) {
            ""
        }

        val telecomDevice = repository.save(
            createTelecomDeviceDto.toEntity(
                imageUrl = imageUrl,
                device = device,
            )
        )

        reorder()

        return TelecomDeviceDto(telecomDevice)
    }

    @Transactional
    fun updateMultiple(updateTelecomDevicesDto: UpdateTelecomDevicesDto): List<TelecomDeviceDto> {
        return updateTelecomDevicesDto.telecomDevices.mapIndexed { index, updateTelecomDeviceDto ->
            // 마지막 데이터일 경우 재정렬
            update(updateTelecomDeviceDto, isReorder = index == updateTelecomDevicesDto.telecomDevices.size - 1)
        }
    }

    /**
     * @param isReorder 재정렬 여부, 기본값은 true
     */
    @Transactional
    fun update(
        updateTelecomDeviceDto: UpdateTelecomDeviceDto,
        isReorder: Boolean = true,
    ): TelecomDeviceDto {
        val telecomDevice = query.getById(updateTelecomDeviceDto.id)
        telecomDevice.update(updateTelecomDeviceDto)
        if (isReorder) reorder()
        return TelecomDeviceDto(repository.save(telecomDevice))
    }

    @Transactional
    fun deleteMultiple(ids: List<Long>) {
        repository.deleteAllById(ids)
    }

    @Transactional
    fun delete(id: Long) {
        repository.deleteById(id)
    }

    @Transactional
    fun postImage(postTelecomDeviceImageDto: PostTelecomDeviceImageDto): FileDto {
        val deviceInfo = query.getById(postTelecomDeviceImageDto.id)
        val uploadFile = fileService.addFile("telecom_device", postTelecomDeviceImageDto.multipartFile)
        deviceInfo.imageUrl = uploadFile.filename
        repository.save(deviceInfo)
        return uploadFile
    }

    @Transactional
    fun reorder() {
        val list = query.findAllOrderAscAndUpdatedAtDesc()

        // idx 순으로 변경하는데, 기존과 같다면 list에 포함하지 않는다. (filter 처리)
        list.filterIndexed { index, telecomDevice -> telecomDevice.displayOrder != index + 1 }
            .forEachIndexed { index, telecomDevice -> telecomDevice.displayOrder = index + 1 }

        repository.saveAll(list)
    }

    @Transactional
    fun updateIsDisplayMultiple(updateTelecomDeviceIsDisplaysDto: UpdateTelecomDeviceIsDisplaysDto): List<TelecomDeviceDto> {
        return updateTelecomDeviceIsDisplaysDto.telecomDevices.map(::updateIsDisplay)
    }

    @Transactional
    fun updateIsDisplay(updateTelecomDeviceIsDisplayDto: UpdateTelecomDeviceIsDisplayDto): TelecomDeviceDto {
        val telecomDevice = query.getById(updateTelecomDeviceIsDisplayDto.id)
        telecomDevice.isDisplay = updateTelecomDeviceIsDisplayDto.isDisplay
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

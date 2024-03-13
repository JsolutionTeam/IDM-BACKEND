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
import kr.co.jsol.domain.telecomdevice.entity.TelecomDevice
import kr.co.jsol.domain.telecomdevice.infrastructure.dto.TelecomDeviceDto
import kr.co.jsol.domain.telecomdevice.infrastructure.dto.TelecomDeviceFactory
import kr.co.jsol.domain.telecomdevice.infrastructure.query.TelecomDeviceQueryRepository
import kr.co.jsol.domain.telecomdevice.infrastructure.repository.TelecomDeviceJDBCRepository
import kr.co.jsol.domain.telecomdevice.infrastructure.repository.TelecomDeviceRepository
import org.slf4j.LoggerFactory.getLogger
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@Service
class TelecomDeviceService(
    private val em: EntityManager,
    private val factory: TelecomDeviceFactory,

    private val repository: TelecomDeviceRepository,
    private val query: TelecomDeviceQueryRepository,
    private val jdbcRepository: TelecomDeviceJDBCRepository,

    private val fileService: FileService,

    private val deviceQuery: DeviceQueryRepository,
    private val deviceInfoQuery: DeviceInfoQueryRepository,
) {

    private final val log = getLogger(this::class.java)

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

        return factory.create(telecomDevice)
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

        if (updateTelecomDeviceDto.deviceId != null) {
            val device = deviceQuery.getById(updateTelecomDeviceDto.deviceId!!)
            telecomDevice.updateDevice(
                device = device,
                imageUrl = try {
                    deviceInfoQuery.getFirstByDeviceId(device.id).imageUrl
                } catch (e: DeviceInfoException.NotFoundByDeviceIdException) {
                    ""
                }
            )
        }

        if (isReorder) reorder()
        return factory.create(repository.save(telecomDevice))
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
        val updatedList = mutableListOf<TelecomDevice>()
        // idx 순으로 변경하는데, 기존과 같다면 list에 포함하지 않는다. (filter 처리)
        list.forEachIndexed { index, telecomDevice ->
            if (telecomDevice.displayOrder != index + 1) {
                updatedList.add(telecomDevice.apply { displayOrder = index + 1 })
            }
        }

        // 로그엔 보지이 않지만 정상적으로 쿼리가 실행된다.
        jdbcRepository.batchUpdateDisplayOrder(subItems = updatedList)
        em.flush() // 없으면 Transactional 어노테이션에 의해 자동으로 업데이트 쿼리가 n회 실행됨
//        em.clear() // 하게되면 영속성 컨텍스트 정보가 날라가게 되어서 update 쿼리가 n회 실행됨
    }

    @Transactional
    fun updateIsDisplayMultiple(updateTelecomDeviceIsDisplaysDto: UpdateTelecomDeviceIsDisplaysDto): List<TelecomDeviceDto> {
        return updateTelecomDeviceIsDisplaysDto.telecomDevices.map(::updateIsDisplay)
    }

    @Transactional
    fun updateIsDisplay(updateTelecomDeviceIsDisplayDto: UpdateTelecomDeviceIsDisplayDto): TelecomDeviceDto {
        val telecomDevice = query.getById(updateTelecomDeviceIsDisplayDto.id)
        telecomDevice.isDisplay = updateTelecomDeviceIsDisplayDto.isDisplay
        return factory.create(repository.save(telecomDevice))
    }

    @Transactional(readOnly = true)
    fun getById(id: Long): TelecomDeviceDto {
        return factory.create(query.getById(id))
    }

    @Transactional(readOnly = true)
    fun findOffsetPageBySearch(
        getDeviceInfosDto: GetTelecomDevicesDto,
        pageable: Pageable,
    ): Page<TelecomDeviceDto> {
        return query.findOffsetPageBySearch(getDeviceInfosDto, pageable)
            // TelecomDeviceFactory 클래스의 create 메소드
            .map(factory::create)
//            .map(::TelecomDeviceDto)
    }
}

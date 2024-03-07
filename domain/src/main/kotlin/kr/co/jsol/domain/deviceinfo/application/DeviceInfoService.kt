package kr.co.jsol.domain.deviceinfo.application

import kr.co.jsol.common.file.application.FileService
import kr.co.jsol.domain.color.infrastructure.query.ColorQueryRepository
import kr.co.jsol.domain.device.infrastructure.query.DeviceQueryRepository
import kr.co.jsol.domain.deviceinfo.application.dto.CreateDeviceInfoDto
import kr.co.jsol.domain.deviceinfo.application.dto.GetDeviceInfoBySeriesAndDeviceIdDto
import kr.co.jsol.domain.deviceinfo.application.dto.GetDeviceInfoSearchDto
import kr.co.jsol.domain.deviceinfo.application.dto.GetDeviceInfosDto
import kr.co.jsol.domain.deviceinfo.application.dto.PostDeviceInfoImage
import kr.co.jsol.domain.deviceinfo.infrastructure.dto.DeviceInfoDto
import kr.co.jsol.domain.deviceinfo.infrastructure.dto.DeviceInfoGroupByDeviceSeriesDto
import kr.co.jsol.domain.deviceinfo.infrastructure.dto.DeviceInfoImageDto
import kr.co.jsol.domain.deviceinfo.infrastructure.query.DeviceInfoQueryRepository
import kr.co.jsol.domain.deviceinfo.infrastructure.repository.DeviceInfoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeviceInfoService(
    private val repository: DeviceInfoRepository,
    private val query: DeviceInfoQueryRepository,

    private val fileService: FileService,

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

    @Transactional
    fun postImage(postDeviceInfoImage: PostDeviceInfoImage): String {
        val deviceInfo = query.getById(postDeviceInfoImage.id)
        val uploadFile = fileService.addFile("device_info", postDeviceInfoImage.multipartFile)
        deviceInfo.imageUrl = uploadFile.filename
        return repository.save(deviceInfo).imageUrl
    }

    @Transactional(readOnly = true)
    fun getById(id: Long): DeviceInfoDto {
        return DeviceInfoDto(query.getById(id))
    }

    @Transactional(readOnly = true)
    fun findOffsetPageBySearch(
        getDeviceInfosDto: GetDeviceInfosDto,
        pageable: Pageable,
    ): Page<DeviceInfoDto> {
        return query.findOffsetPageBySearch(getDeviceInfosDto, pageable)
            .map(::DeviceInfoDto)
    }

    @Transactional(readOnly = true)
    fun findBySeriesAndDeviceId(getDeviceInfoBySeriesAndDeviceIdDto: GetDeviceInfoBySeriesAndDeviceIdDto): List<DeviceInfoImageDto> {
        return query.findBySeriesAndDeviceId(getDeviceInfoBySeriesAndDeviceIdDto).map {
            DeviceInfoImageDto(it.id, it.imageUrl)
        }
    }

    @Transactional(readOnly = true)
    fun findBySeriesAndDeviceIdAndColorId(getDeviceInfoSearchDto: GetDeviceInfoSearchDto): DeviceInfoDto? {
        return query.findBySeriesAndDeviceIdAndColorId(getDeviceInfoSearchDto)?.let(::DeviceInfoDto)
    }

    @Transactional(readOnly = true)
    fun findByDeviceSeriesGroupByDeviceSeries(series: String): List<DeviceInfoGroupByDeviceSeriesDto> {
        return query.findByDeviceSeriesGroupByDeviceSeries(series)
    }
}

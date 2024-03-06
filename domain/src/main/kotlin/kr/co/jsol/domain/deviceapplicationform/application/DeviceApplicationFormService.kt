package kr.co.jsol.domain.deviceapplicationform.application

import kr.co.jsol.domain.deviceapplicationform.application.dto.CreateDeviceApplicationFormDto
import kr.co.jsol.domain.deviceapplicationform.application.dto.UpdateDeviceApplicationFormDto
import kr.co.jsol.domain.deviceapplicationform.infrastructure.dto.DeviceApplicationFormDto
import kr.co.jsol.domain.deviceapplicationform.infrastructure.query.DeviceApplicationFormQueryRepository
import kr.co.jsol.domain.deviceapplicationform.infrastructure.repository.DeviceApplicationFormRepository
import kr.co.jsol.domain.deviceinfo.infrastructure.query.DeviceInfoQueryRepository
import kr.co.jsol.domain.insurance.infrastructure.query.InsuranceQueryRepository
import kr.co.jsol.domain.phoneplan.infrastructure.query.PhonePlanQueryRepository
import kr.co.jsol.domain.shop.infrastructure.query.ShopQueryRepository
import kr.co.jsol.domain.subservice.entity.Subservice
import kr.co.jsol.domain.subservice.infrastructure.query.SubserviceQueryRepository
import kr.co.jsol.domain.telecom.infrastructure.query.TelecomQueryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeviceApplicationFormService(
    private val repository: DeviceApplicationFormRepository,
    private val query: DeviceApplicationFormQueryRepository,

    private val shopQuery: ShopQueryRepository,
    private val telecomQuery: TelecomQueryRepository,
    private val deviceInfoQuery: DeviceInfoQueryRepository,
    private val phonePlanQuery: PhonePlanQueryRepository,
    private val insuranceQuery: InsuranceQueryRepository,
    private val subserviceQuery: SubserviceQueryRepository,
) {

    @Transactional
    fun create(
        shopId: Long,
        createDeviceApplicationFormDto: CreateDeviceApplicationFormDto,
    ): DeviceApplicationFormDto {
        val subserviceList = mutableListOf<Subservice>()

        createDeviceApplicationFormDto.subserviceIds.forEach {
            subserviceQuery.findById(it)?.let { subservice ->
                subserviceList.add(subservice)
            }
        }

        val deviceApplicationForm = createDeviceApplicationFormDto.toEntity(
            shop = shopQuery.getById(shopId),
            telecom = telecomQuery.getById(createDeviceApplicationFormDto.telecomId),
            deviceInfo = deviceInfoQuery.getById(createDeviceApplicationFormDto.deviceInfoId),
            phonePlan = phonePlanQuery.getById(createDeviceApplicationFormDto.phonePlanId),
            insurance = insuranceQuery.getById(createDeviceApplicationFormDto.insuranceId),
            subserviceList = subserviceList,
        )
        repository.save(deviceApplicationForm)
        return DeviceApplicationFormDto(deviceApplicationForm)
    }

    @Transactional
    fun update(updateDeviceApplicationFormDto: UpdateDeviceApplicationFormDto): DeviceApplicationFormDto {
        val deviceApplicationForm = query.getById(updateDeviceApplicationFormDto.id)
        deviceApplicationForm.update(updateDeviceApplicationFormDto)
        return DeviceApplicationFormDto(deviceApplicationForm)
    }
}

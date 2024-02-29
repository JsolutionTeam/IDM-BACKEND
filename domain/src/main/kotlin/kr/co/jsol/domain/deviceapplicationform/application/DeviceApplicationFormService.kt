package kr.co.jsol.domain.deviceapplicationform.application

import kr.co.jsol.domain.deviceapplicationform.application.dto.CreateDeviceApplicationFormDto
import kr.co.jsol.domain.deviceapplicationform.entity.DeviceApplicationForm
import kr.co.jsol.domain.deviceapplicationform.infrastructure.dto.DeviceApplicationFormDto
import kr.co.jsol.domain.deviceapplicationform.infrastructure.repository.InsuranceRepository
import kr.co.jsol.domain.deviceinfo.infrastructure.query.DeviceInfoQueryRepository
import kr.co.jsol.domain.insurance.infrastructure.query.InsuranceQueryRepository
import kr.co.jsol.domain.phoneplan.infrastructure.query.PhonePlanQueryRepository
import kr.co.jsol.domain.shop.infrastructure.query.ShopQueryRepository
import kr.co.jsol.domain.telecom.infrastructure.query.TelecomQueryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeviceApplicationFormService(
    private val repository: InsuranceRepository,
    private val query: InsuranceQueryRepository,

    private val shopQuery: ShopQueryRepository,
    private val telecomQuery: TelecomQueryRepository,
    private val deviceInfoQuery: DeviceInfoQueryRepository,
    private val phonePlanQuery: PhonePlanQueryRepository,
    private val insuranceQuery: InsuranceQueryRepository,
    private val subserviceQuery: SubserviceQueryRepository,
) {

    @Transactional
    fun create(createDeviceApplicationFormDto: CreateDeviceApplicationFormDto): DeviceApplicationFormDto {
        val deviceApplicationForm = DeviceApplicationForm(
            createDeviceApplicationFormDto.toEntity(
                shop
                        telecom
                        deviceInfo
                        phonePlan
                        insurance
                        subservice
            )
        )
        repository.save(deviceApplicationForm)
        return DeviceApplicationFormDto(deviceApplicationForm)
    }
}

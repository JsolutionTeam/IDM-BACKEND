package kr.co.jsol.domain.deviceapplicationform.application

import kr.co.jsol.domain.deviceapplicationform.application.dto.CreateDeviceApplicationFormDto
import kr.co.jsol.domain.deviceapplicationform.application.dto.GetDeviceApplicationFormsDto
import kr.co.jsol.domain.deviceapplicationform.application.dto.UpdateDeviceApplicationFormDto
import kr.co.jsol.domain.deviceapplicationform.entity.DeviceApplicationFormSubservice
import kr.co.jsol.domain.deviceapplicationform.infrastructure.dto.DeviceApplicationFormDto
import kr.co.jsol.domain.deviceapplicationform.infrastructure.query.DeviceApplicationFormQueryRepository
import kr.co.jsol.domain.deviceapplicationform.infrastructure.query.DeviceApplicationFormSubserviceQueryRepository
import kr.co.jsol.domain.deviceapplicationform.infrastructure.repository.DeviceApplicationFormRepository
import kr.co.jsol.domain.deviceapplicationform.infrastructure.repository.DeviceApplicationFormSubserviceRepository
import kr.co.jsol.domain.deviceinfo.infrastructure.query.DeviceInfoQueryRepository
import kr.co.jsol.domain.insurance.infrastructure.query.InsuranceQueryRepository
import kr.co.jsol.domain.phoneplan.infrastructure.query.PhonePlanQueryRepository
import kr.co.jsol.domain.shop.infrastructure.query.ShopQueryRepository
import kr.co.jsol.domain.subservice.entity.Subservice
import kr.co.jsol.domain.subservice.infrastructure.query.SubserviceQueryRepository
import kr.co.jsol.domain.telecom.infrastructure.query.TelecomQueryRepository
import kr.co.jsol.domain.userdetails.UserDetailsImpl
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeviceApplicationFormService(
    private val repository: DeviceApplicationFormRepository,
    private val query: DeviceApplicationFormQueryRepository,

    private val formSubserviceRepository: DeviceApplicationFormSubserviceRepository,
    private val formSubserviceQuery: DeviceApplicationFormSubserviceQueryRepository,

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
            insurance = createDeviceApplicationFormDto.insuranceId?.let { insuranceQuery.getById(it) },
        )

        val deviceApplicationFormSubserviceList = subserviceList.map {
            formSubserviceRepository.save(
                DeviceApplicationFormSubservice(
                    deviceApplicationForm = deviceApplicationForm,
                    subservice = it,
                )
            )
        }

        repository.save(deviceApplicationForm)
        return DeviceApplicationFormDto(deviceApplicationForm, deviceApplicationFormSubserviceList)
    }

    @Transactional
    fun update(
        updateDeviceApplicationFormDto: UpdateDeviceApplicationFormDto,
        userDetails: UserDetailsImpl,
    ): DeviceApplicationFormDto {

        // 일반 사용자가 수정할 때는 해당 매장의 신청서만 수정 가능
        val deviceApplicationForm =
            if (userDetails.isNotMaster()) query.getByIdAndShopId(updateDeviceApplicationFormDto.id, userDetails.shop.id)
            else query.getById(updateDeviceApplicationFormDto.id)

        deviceApplicationForm.update(updateDeviceApplicationFormDto)

        updateDeviceApplicationFormDto.telecomId?.let {
            deviceApplicationForm.telecom = telecomQuery.getById(it)
        }
        updateDeviceApplicationFormDto.deviceInfoId?.let {
            deviceApplicationForm.deviceInfo = deviceInfoQuery.getById(it)
        }
        updateDeviceApplicationFormDto.phonePlanId?.let {
            deviceApplicationForm.phonePlan = phonePlanQuery.getById(it)
        }
        updateDeviceApplicationFormDto.insuranceId?.let {
            deviceApplicationForm.insurance = insuranceQuery.getById(it)
        }

        updateDeviceApplicationFormDto.subserviceIds?.let { subserviceIds ->
            val subserviceList = mutableListOf<Subservice>()
            subserviceIds.forEach { subserviceId ->
                subserviceQuery.findById(subserviceId)?.let { subservice ->
                    subserviceList.add(subservice)
                }
            }

            subserviceList.map {
                formSubserviceQuery.getByDeviceApplicationFormId(deviceApplicationForm.id).forEach { formSubservice ->
                    formSubserviceRepository.delete(formSubservice)
                }

                formSubserviceRepository.save(
                    DeviceApplicationFormSubservice(
                        deviceApplicationForm = deviceApplicationForm,
                        subservice = it,
                    )
                )
            }
        }

        return DeviceApplicationFormDto(
            repository.save(deviceApplicationForm),
            formSubserviceQuery.getByDeviceApplicationFormId(deviceApplicationForm.id)
        )
    }

    @Transactional(readOnly = true)
    fun getByIdAndShopId(
        id: Long,
        shopId: Long,
    ): DeviceApplicationFormDto {
        val deviceApplicationForm = query.getByIdAndShopId(id, shopId)
        return DeviceApplicationFormDto(
            deviceApplicationForm,
            formSubserviceQuery.getByDeviceApplicationFormId(deviceApplicationForm.id)
        )
    }

    @Transactional(readOnly = true)
    fun getById(id: Long): DeviceApplicationFormDto {
        val deviceApplicationForm = query.getById(id)
        return DeviceApplicationFormDto(
            deviceApplicationForm,
            formSubserviceQuery.getByDeviceApplicationFormId(deviceApplicationForm.id)
        )
    }

    @Transactional(readOnly = true)
    fun findOffsetPageBySearch(
        getDeviceApplicationFormsDto: GetDeviceApplicationFormsDto,
        pageable: Pageable,
    ): Page<DeviceApplicationFormDto> {
        return query.findOffsetPageBySearch(getDeviceApplicationFormsDto, pageable)
            .map {
                DeviceApplicationFormDto(
                    it,
                    formSubserviceQuery.getByDeviceApplicationFormId(it.id)
                )
            }
    }
}

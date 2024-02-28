package kr.co.jsol.domain.companysubsidy.application

import kr.co.jsol.domain.companysubsidy.application.dto.CreateCompanySubsidyDto
import kr.co.jsol.domain.companysubsidy.application.dto.UpdateCompanySubsidiesDto
import kr.co.jsol.domain.companysubsidy.infrastructure.dto.CompanySubsidyDto
import kr.co.jsol.domain.companysubsidy.infrastructure.query.CompanySubsidyQueryRepository
import kr.co.jsol.domain.companysubsidy.infrastructure.repository.CompanySubsidyRepository
import kr.co.jsol.domain.device.infrastructure.query.DeviceQueryRepository
import kr.co.jsol.domain.phoneplan.infrastructure.query.PhonePlanQueryRepository
import kr.co.jsol.domain.shop.infrastructure.query.ShopQueryRepository
import kr.co.jsol.domain.telecom.infrastructure.query.TelecomQueryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CompanySubsidyService(
    private val repository: CompanySubsidyRepository,
    private val query: CompanySubsidyQueryRepository,

    private val shopQuery: ShopQueryRepository,
    private val telecomQuery: TelecomQueryRepository,
    private val phonePlanQuery: PhonePlanQueryRepository,
    private val deviceQuery: DeviceQueryRepository,
) {

    @Transactional
    fun create(createCompanySubsidyDto: CreateCompanySubsidyDto): CompanySubsidyDto {
        return CompanySubsidyDto(
            repository.save(
                createCompanySubsidyDto.toEntity(
                    shop = shopQuery.getById(createCompanySubsidyDto.shopId),
                    telecom = telecomQuery.getById(createCompanySubsidyDto.telecomId),
                    phonePlan = phonePlanQuery.getById(createCompanySubsidyDto.phonePlanId),
                    device = deviceQuery.getById(createCompanySubsidyDto.deviceId),
                )
            )
        )
    }

    @Transactional
    fun updateMultiple(updateCompanySubsidiesDto: UpdateCompanySubsidiesDto) {
//        updateCompanySubsidiesDto.companySubsidies.forEach {
//            val companySubsidy = query.getById(it.id)
//        }
    }
}

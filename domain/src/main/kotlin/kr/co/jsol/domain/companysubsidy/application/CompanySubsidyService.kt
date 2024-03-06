package kr.co.jsol.domain.companysubsidy.application

import kr.co.jsol.domain.account.infrastructure.query.AccountQueryRepository
import kr.co.jsol.domain.companysubsidy.application.dto.CreateCompanySubsidyDto
import kr.co.jsol.domain.companysubsidy.application.dto.DeleteCompanySubsidiesDto
import kr.co.jsol.domain.companysubsidy.application.dto.ExistsCompanySubsidyDto
import kr.co.jsol.domain.companysubsidy.application.dto.GetCompanySubsidiesDto
import kr.co.jsol.domain.companysubsidy.application.dto.GetCompanySubsidyPriceDto
import kr.co.jsol.domain.companysubsidy.application.dto.UpdateCompanySubsidiesDto
import kr.co.jsol.domain.companysubsidy.infrastructure.dto.CompanySubsidyDto
import kr.co.jsol.domain.companysubsidy.infrastructure.query.CompanySubsidyQueryRepository
import kr.co.jsol.domain.companysubsidy.infrastructure.repository.CompanySubsidyRepository
import kr.co.jsol.domain.device.infrastructure.query.DeviceQueryRepository
import kr.co.jsol.domain.phoneplan.infrastructure.query.PhonePlanQueryRepository
import kr.co.jsol.domain.shop.infrastructure.query.ShopQueryRepository
import kr.co.jsol.domain.telecom.infrastructure.query.TelecomQueryRepository
import kr.co.jsol.domain.userdetails.UserDetailsImpl
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CompanySubsidyService(
    private val repository: CompanySubsidyRepository,
    private val query: CompanySubsidyQueryRepository,

    private val accountQuery: AccountQueryRepository,
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
        repository.saveAll(updateCompanySubsidiesDto.companySubsidies.map { cs ->
            val companySubsidy = query.getById(cs.id)

            cs.shopId?.let { companySubsidy.shop = shopQuery.getById(it) }
            cs.telecomId?.let { companySubsidy.telecom = telecomQuery.getById(it) }
            cs.phonePlanId?.let { companySubsidy.phonePlan = phonePlanQuery.getById(it) }
            cs.deviceId?.let { companySubsidy.device = deviceQuery.getById(it) }

            companySubsidy.update(cs)
            companySubsidy
        })
    }

    @Transactional
    fun deleteMultiple(deleteCompanySubsidiesDto: DeleteCompanySubsidiesDto) {
        repository.deleteAll(deleteCompanySubsidiesDto.ids.map { query.getById(it) })
    }

    @Transactional(readOnly = true)
    fun findOffsetPageBySearch(
        getCompanySubsidiesDto: GetCompanySubsidiesDto,
        pageable: Pageable,
        requester: UserDetailsImpl,
    ): Page<CompanySubsidyDto> {

        // 최종 관리자가 아니라면 해당 업체의 데이터만 조회 가능
        if (requester.isNotMaster() || getCompanySubsidiesDto.shopId == null) {
            getCompanySubsidiesDto.shopId = requester.shop.id
        }

        return query.findOffsetPageBySearch(getCompanySubsidiesDto, pageable)
            .map { CompanySubsidyDto(it) }
    }

    @Transactional(readOnly = true)
    fun getById(id: Long): CompanySubsidyDto {
        return CompanySubsidyDto(query.getById(id))
    }

    @Transactional(readOnly = true)
    fun exists(existsCompanySubsidyDto: ExistsCompanySubsidyDto): Boolean {
        return query.exists(existsCompanySubsidyDto)
    }

    @Transactional(readOnly = true)
    fun getPrice(getCompanySubsidyPriceDto: GetCompanySubsidyPriceDto): Long {
        return query.getPrice(getCompanySubsidyPriceDto)
    }
}

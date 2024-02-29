package kr.co.jsol.domain.companysubsidy.infrastructure.query

import com.querydsl.core.BooleanBuilder
import com.querydsl.jpa.impl.JPAQueryFactory
import kr.co.jsol.common.exception.domain.companysubsidy.CompanySubsidyException
import kr.co.jsol.common.repository.BaseQueryRepository
import kr.co.jsol.domain.companysubsidy.application.dto.GetCompanySubsidiesDto
import kr.co.jsol.domain.companysubsidy.entity.CompanySubsidy
import kr.co.jsol.domain.companysubsidy.entity.QCompanySubsidy.Companion.companySubsidy
import kr.co.jsol.domain.companysubsidy.infrastructure.repository.CompanySubsidyRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class CompanySubsidyQueryRepository(
    private val repository: CompanySubsidyRepository,
    private val queryFactory: JPAQueryFactory,
) : BaseQueryRepository<CompanySubsidy, Long>(companySubsidy, repository) {

    fun getById(id: Long): CompanySubsidy {
        return repository.findOne(
            companySubsidy.id.eq(id)
                .and(companySubsidy.deletedAt.isNull)
        )
            .orElseThrow { throw CompanySubsidyException.NotFoundByIdException() }
    }

    fun findListByIdList(
        ids: List<Long>,
    ): List<CompanySubsidy> {
        return queryFactory
            .selectFrom(companySubsidy)
            .where(
                companySubsidy.id.`in`(ids)
                    .and(companySubsidy.deletedAt.isNull)
            )
            .fetch()
    }

    fun findOffsetPageBySearch(
        getCompanySubsidiesDto: GetCompanySubsidiesDto,
        pageable: Pageable,
    ): Page<CompanySubsidy> {
        val booleanBuilder = BooleanBuilder()
            .and(companySubsidy.shop.id.eq(getCompanySubsidiesDto.shopId))
            .and(companySubsidy.deletedAt.isNull)

        getCompanySubsidiesDto.openType?.let {
            booleanBuilder.and(companySubsidy.openType.eq(it))
        }
        getCompanySubsidiesDto.discountType?.let {
            booleanBuilder.and(companySubsidy.discountType.eq(it))
        }
        getCompanySubsidiesDto.priceMin?.let {
            booleanBuilder.and(companySubsidy.price.goe(it))
        }
        getCompanySubsidiesDto.priceMax?.let {
            booleanBuilder.and(companySubsidy.price.loe(it))
        }
        getCompanySubsidiesDto.telecomId?.let {
            booleanBuilder.and(companySubsidy.telecom.id.eq(it))
        }
        getCompanySubsidiesDto.phonePlanId?.let {
            booleanBuilder.and(companySubsidy.phonePlan.id.eq(it))
        }
        getCompanySubsidiesDto.deviceId?.let {
            booleanBuilder.and(companySubsidy.device.id.eq(it))
        }

        return repository.findAll(
            booleanBuilder,
            pageable,
        )
    }
}

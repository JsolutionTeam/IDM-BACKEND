package kr.co.jsol.domain.companysubsidy.infrastructure.query

import com.querydsl.jpa.impl.JPAQueryFactory
import kr.co.jsol.common.exception.domain.companysubsidy.CompanySubsidyException
import kr.co.jsol.common.repository.BaseQueryRepository
import kr.co.jsol.domain.companysubsidy.entity.CompanySubsidy
import kr.co.jsol.domain.companysubsidy.entity.QCompanySubsidy.Companion.companySubsidy
import kr.co.jsol.domain.companysubsidy.infrastructure.repository.CompanySubsidyRepository
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
}

package kr.co.jsol.domain.companysubsidy.infrastructure.query

import com.querydsl.jpa.impl.JPAQueryFactory
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

//    fun getById(id: String): CompanySubsidy {
//        return // TODO: Implement the function
//        return repository.findOne(
//            companySubsidy.id.eq(id)
//                .and(companySubsidy.deletedAt.isNull)
//        )
//            .orElseThrow { throw IllegalArgumentException("사용자를 찾을 수 없습니다.") }
//    }
}

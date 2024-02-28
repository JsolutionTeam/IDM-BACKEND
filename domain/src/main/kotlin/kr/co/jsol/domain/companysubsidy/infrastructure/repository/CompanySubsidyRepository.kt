package kr.co.jsol.domain.companysubsidy.infrastructure.repository

import kr.co.jsol.domain.companysubsidy.entity.CompanySubsidy
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CompanySubsidyRepository :
    CrudRepository<CompanySubsidy, Long>,
    QuerydslPredicateExecutor<CompanySubsidy>

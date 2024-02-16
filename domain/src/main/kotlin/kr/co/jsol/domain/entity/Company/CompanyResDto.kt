package kr.co.jsol.domain.entity.Company

import lombok.Getter

@Getter
@NoArgsConstructor
class CompanyResDto(company: Company) {
    private val idx: Long
    private val companyName: String

    init {
        this.idx = company.getIdx()
        this.companyName = company.getCompanyName()
    }
}

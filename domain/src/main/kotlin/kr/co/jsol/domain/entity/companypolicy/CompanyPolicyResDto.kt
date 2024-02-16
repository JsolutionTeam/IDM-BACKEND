package kr.co.jsol.domain.entity.companypolicy

import lombok.Getter

@Getter
@NoArgsConstructor
class CompanyPolicyResDto(companyPolicy: CompanyPolicy) {
    private val idx: Long

    private val policyName: String

    private val policyPrice: Long

    private val modelIdx: Long

    private val companyName: String

    init {
        this.idx = companyPolicy.getIdx()
        this.policyName = companyPolicy.getPolicyName()
        this.policyPrice = companyPolicy.getPolicyPrice()
        this.modelIdx = companyPolicy.getModelIdx()
        this.companyName = companyPolicy.getCompanyName()
    }
}

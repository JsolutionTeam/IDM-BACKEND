package kr.co.jsol.domain.entity.companypolicy

import lombok.Getter
@Getter
@NoArgsConstructor
class CompanyPolicyDto {
    private val policyName: @javax.validation.constraints.NotNull String? = null
    private val policyPrice: @javax.validation.constraints.NotNull Long? = null
    private val modelIdx: @javax.validation.constraints.NotNull Long? = null
}

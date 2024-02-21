package kr.co.jsol.domain.companysubsidy.entity

import kr.co.jsol.common.domain.BaseEntity
import org.hibernate.annotations.Comment
import org.hibernate.annotations.Table
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
@javax.persistence.Table(name = "company_subsidy")
@Table(appliesTo = "company_subsidy", comment = "회사 지원금(제이솔루션이 특정 회사에 지급하는 회사/통신사/모델/유형/요금제별 추가 지원금)")
class CompanySubsidy(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("마스터 옵션 ID")
    var id: Long = 0,

    @Comment("마스터 옵션 명")
    var name: String,

    @Comment("노출 여부")
    var isShow: Boolean,
) : BaseEntity()

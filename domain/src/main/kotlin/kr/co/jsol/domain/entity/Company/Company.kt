package kr.co.jsol.domain.entity.Company

import com.fasterxml.jackson.annotation.JsonIgnore
import lombok.AccessLevel
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.validation.constraints.NotNull

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Slf4j
class Company(
    @field:Column(nullable = false)
    private var companyName: @NotNull String?,
    account: Account
) : TimeStamp() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private var idx: Long? = null

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_idx")
    @JsonIgnore
    private val account: Account = account
}

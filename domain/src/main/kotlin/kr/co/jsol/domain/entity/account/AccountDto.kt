package kr.co.jsol.domain.entity.account

import lombok.Getter
import javax.validation.constraints.NotNull

@Getter
@NoArgsConstructor
class AccountDto {
    private val id: @NotNull String? = null
    private val password: @NotNull String? = null
    private val role: @NotNull String? = null
    private val companyName: @NotNull String? = null

    private val name: String? = null
    private val ownerName: @NotNull String? = null
}

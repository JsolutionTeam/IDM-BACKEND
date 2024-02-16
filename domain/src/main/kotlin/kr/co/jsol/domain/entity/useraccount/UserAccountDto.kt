package kr.co.jsol.domain.entity.useraccount

import lombok.Getter
import javax.validation.constraints.NotNull

@Getter
@NoArgsConstructor
class UserAccountDto {
    private val name: @NotNull String? = null

    private val phoneNumber: @NotNull String? = null

    private val companyName: @NotNull String? = null

    private val id: @NotNull String? = null

    private val password: @NotNull String? = null

    private val role: String? = null
}

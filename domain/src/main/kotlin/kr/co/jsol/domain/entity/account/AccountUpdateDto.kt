package kr.co.jsol.domain.entity.account

import lombok.Getter
import javax.validation.constraints.NotNull

@Getter
@NoArgsConstructor
class AccountUpdateDto {
    private val id: @NotNull String? = null

    private val password: String? = null

    private val name: String? = null
}

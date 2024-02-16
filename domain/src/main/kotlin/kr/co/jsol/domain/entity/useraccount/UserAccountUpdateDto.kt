package kr.co.jsol.domain.entity.useraccount

import lombok.Getter
import javax.validation.constraints.NotNull

@Getter
@NoArgsConstructor
class UserAccountUpdateDto {
    private val name: @NotNull String? = null
    private val phoneNumber: @NotNull String? = null

    private val password: String? = null
}

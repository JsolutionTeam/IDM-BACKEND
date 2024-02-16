package kr.co.jsol.domain.entity.account

import lombok.Getter
@Getter
@NoArgsConstructor
class AccountReqDto {
    private val id: @javax.validation.constraints.NotNull String? = null
    private val password: @javax.validation.constraints.NotNull String? = null
}

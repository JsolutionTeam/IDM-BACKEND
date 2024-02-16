package kr.co.jsol.domain.entity.telecom_data

import lombok.Getter
@Getter
@NoArgsConstructor
class TelecomDataDto {
    private val telecomUrl: @javax.validation.constraints.NotNull String? = null

    private val modelIdx: @javax.validation.constraints.NotNull Long? = null

    private val telecomName: @javax.validation.constraints.NotNull String? = null
}

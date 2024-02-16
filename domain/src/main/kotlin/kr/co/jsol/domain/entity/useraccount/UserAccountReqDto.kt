package kr.co.jsol.domain.entity.useraccount

import javax.validation.constraints.NotNull

class UserAccountReqDto(
    @field:NotNull
    private val name: String,

    @field:NotNull
    private val phoneNumber: String?,

    @field:NotNull
    private val role: String?,
)

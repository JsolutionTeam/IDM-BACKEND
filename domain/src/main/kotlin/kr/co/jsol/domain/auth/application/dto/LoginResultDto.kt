package kr.co.jsol.domain.auth.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.jsol.domain.account.entity.enums.AccountRole
import kr.co.jsol.domain.shop.infrastructure.dto.ShopDto

@Schema(name = "로그인 결과")
data class LoginResultDto(
    val accessToken: String,
    val refreshToken: String,

    @field:Schema(description = "이름", example = "권세기")
    val name: String,

    @field:Schema(description = "권한", implementation = AccountRole::class)
    val role: AccountRole,

    @field:Schema(description = "관리자 여부, true = 회사, false = 사용자", implementation = Boolean::class)
    val isManager: Boolean,

    @field:Schema(description = "연락처", example = "010-0000-0000")
    val phone: String,

    @field:Schema(description = "업체 정보")
    val shop: ShopDto,
)

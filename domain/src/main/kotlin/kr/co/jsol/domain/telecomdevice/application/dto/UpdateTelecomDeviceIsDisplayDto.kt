package kr.co.jsol.domain.telecomdevice.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotNull

@Schema(name = "통신팀 판매용 단말 표시 여부 단일 수정 요청")
data class UpdateTelecomDeviceIsDisplayDto(
    @field:NotNull(message = "통신팀 판매용 단말 정보 아이디는 필수입니다.")
    @field:Schema(description = "통신팀 판매용 단말 정보 아이디")
    val id: Long,

    @field:NotNull(message = "표시 여부는 필수입니다.")
    @field:Schema(description = "표시 여부", implementation = Boolean::class)
    var isDisplay: Boolean,
)

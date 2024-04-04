package kr.co.jsol.domain.auth.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.jsol.domain.account.application.dto.UpdateAccountDto

@Schema(name = "AUTH 서버 계정 생성 요청")
data class AuthUpdateUserDto(
    val id: String,
    val password: String?,
    val name: String?,
) {

    constructor(updateUserDto: UpdateAccountDto) : this(
        id = updateUserDto.id,
        password = updateUserDto.password,
        name = updateUserDto.name,
    )

    fun toMap(): Map<String, Any?> {
        return mapOf(
            "id" to id,
            "password" to password,
            "name" to name,
        )
    }
}

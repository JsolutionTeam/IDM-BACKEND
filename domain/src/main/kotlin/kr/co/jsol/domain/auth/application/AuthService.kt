package kr.co.jsol.domain.auth.application

import kr.co.jsol.common.exception.GeneralClientException
import kr.co.jsol.domain.auth.application.dto.AuthCreateUserDto
import kr.co.jsol.domain.auth.application.dto.AuthLoginResultDto
import kr.co.jsol.domain.auth.application.dto.AuthUpdateUserDto
import kr.co.jsol.domain.auth.application.dto.LoginDto
import kr.co.jsol.domain.auth.restapi.AuthRest
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(
    private val authRest: AuthRest,
) {

    @Transactional
    fun postUsers(authCreateUserDto: AuthCreateUserDto): ResponseEntity<Map<String, Any>> {
        return authRest.post<Map<String, Any>>(
            "/api/users",
            authCreateUserDto.toMap(),
        )
    }

    @Transactional
    fun login(loginDto: LoginDto): AuthLoginResultDto {
        val loginResult = authRest.post<AuthLoginResultDto>("/api/users/login", loginDto.toMap())

        if (!loginResult.statusCode.is2xxSuccessful) {
            throw GeneralClientException.LoginFailedException()
        }

        if (loginResult.body == null) {
            throw GeneralClientException.LoginFailedException()
        }

        return loginResult.body!!
    }

    @Transactional
    fun patchUsers(authUpdateUserDto: AuthUpdateUserDto) {
        authRest.patch<Long>("/api/users", authUpdateUserDto.toMap())
    }

    @Transactional
    fun deleteAuthById(id: String) {
        authRest.delete("/api/users/${id}")
    }
}

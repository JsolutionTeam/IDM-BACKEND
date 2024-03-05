package kr.co.jsol.api.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import kr.co.jsol.domain.account.application.AccountService
import kr.co.jsol.domain.auth.application.AuthService
import kr.co.jsol.domain.auth.application.dto.LoginDto
import kr.co.jsol.domain.auth.application.dto.LoginResultDto
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "권한, 로그인", description = "로그인, 권한 관련 API")
class AuthController(
    private val service: AuthService,
    private val accountService: AccountService,
) {

    @Operation(summary = "로그인")
    @PostMapping("login")
    fun login(
        @Valid
        @RequestBody
        loginDto: LoginDto,
    ): LoginResultDto {
        val (accessToken, refreshToken) = service.login(loginDto)
        val account = accountService.getById(loginDto.id)
        return LoginResultDto(
            accessToken = accessToken,
            refreshToken = refreshToken,
            name = account.name,
            role = account.role,
            isManager = account.isManager,
            phone = account.phone,
            shop = account.shop,
        )
    }
}

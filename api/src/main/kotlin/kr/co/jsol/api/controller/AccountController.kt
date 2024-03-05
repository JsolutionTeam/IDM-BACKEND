package kr.co.jsol.api.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import kr.co.jsol.common.jwt.JwtService
import kr.co.jsol.domain.account.application.AccountService
import kr.co.jsol.domain.account.application.dto.CreateAccountDto
import kr.co.jsol.domain.account.infrastructure.dto.AccountDto
import kr.co.jsol.domain.userdetails.UserDetailsImpl
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/accounts")
class AccountController(
    private val service: AccountService,
    private val jwtService: JwtService,
) {

    @Operation(summary = "유저 생성 [추후 IDM 이전시 사용]")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    fun createAccount(
        @Valid
        @RequestBody
        createAccountDto: CreateAccountDto,
        @AuthenticationPrincipal
        userDetails: UserDetailsImpl,
    ): AccountDto {
        return service.create(createAccountDto)
    }

    @Operation(summary = "내 정보 조회")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("my-info")
    @ResponseStatus(HttpStatus.OK)
    fun getMyInfo(
        @AuthenticationPrincipal
        userDetails: UserDetailsImpl,
    ): AccountDto {
        return service.getById(userDetails.id)
    }
}

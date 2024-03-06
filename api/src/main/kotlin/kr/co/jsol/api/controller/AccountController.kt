package kr.co.jsol.api.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import kr.co.jsol.common.domain.AccountAuthority
import kr.co.jsol.common.jwt.JwtService
import kr.co.jsol.domain.account.application.AccountService
import kr.co.jsol.domain.account.application.dto.CreateAccountDto
import kr.co.jsol.domain.account.infrastructure.dto.AccountDto
import kr.co.jsol.domain.userdetails.UserDetailsImpl
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/accounts")
@Tag(name = "계정", description = "계정 관련 API")
class AccountController(
    private val service: AccountService,
    private val jwtService: JwtService,
) {

    @Operation(summary = "유저 생성 [추후 IDM 이전시 사용]")
    @PreAuthorize(AccountAuthority.ROLECHECK.HasCompanyRole)
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
        // 요청자가 관리자인지 확인
        if (!userDetails.isManager) {
            throw IllegalAccessException("관리자만 계정을 생성할 수 있습니다.") // TODO 사용자 정의 예외 처리
        }

        if (userDetails.isNotMaster()) {
            createAccountDto.shopId = userDetails.shop.id
        }

        return service.create(createAccountDto)
    }

    @Operation(summary = "중복 조회")
    @PreAuthorize(AccountAuthority.ROLECHECK.HasAnyRole)
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("exists/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun existsId(
        @PathVariable
        id: String,
    ): Boolean {
        return service.existsById(id)
    }

    @Operation(summary = "내 정보 조회")
    @PreAuthorize(AccountAuthority.ROLECHECK.HasAnyRole)
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

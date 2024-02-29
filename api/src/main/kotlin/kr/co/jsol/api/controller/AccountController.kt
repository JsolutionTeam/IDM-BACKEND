package kr.co.jsol.api.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import kr.co.jsol.common.domain.AccountAuthority
import kr.co.jsol.common.exception.GeneralClientException
import kr.co.jsol.common.jwt.JwtService
import kr.co.jsol.domain.account.application.AccountService
import kr.co.jsol.domain.account.application.dto.CreateAccountDto
import kr.co.jsol.domain.account.infrastructure.dto.AccountDto
import kr.co.jsol.domain.userdetails.UserDetailsImpl
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
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
class AccountController(
    private val accountService: AccountService,
    private val jwtService: JwtService,
) {

    @Operation(summary = "JWT Creation TEST")
    @PostMapping("/jwt/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    fun cr(
        @PathVariable
        id: String,
    ): String {
        val account = accountService.getById(id)
        return jwtService.createToken(
            account.id,
            account.name,
            account.role.toString(),
        )
    }

    @Operation(summary = "JWT Validation TEST")
//    @PreAuthorize("hasAnyAuthority(\"USER\")")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    fun jwtValidationTest(
        @AuthenticationPrincipal
        userDetails: UserDetailsImpl,
    ) {
        val log = LoggerFactory.getLogger(this.javaClass)
        log.info("userDetails : $userDetails")
    }

    @Operation(summary = "Create Account")
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
        val requesterRole = userDetails.role
        val requestedRole = createAccountDto.role


        if (requesterRole != AccountAuthority.ADMIN) {
            if (requesterRole == AccountAuthority.COMPANY) {
                if (requestedRole != AccountAuthority.USER) {
                    throw GeneralClientException.ForbiddenException()
                }
            }
        }

        return accountService.create(createAccountDto)
    }
}

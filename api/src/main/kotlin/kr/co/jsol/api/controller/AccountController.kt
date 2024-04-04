package kr.co.jsol.api.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import kr.co.jsol.common.domain.AccountAuthority
import kr.co.jsol.common.exception.GeneralClientException
import kr.co.jsol.domain.account.application.AccountService
import kr.co.jsol.domain.account.application.dto.CreateAccountDto
import kr.co.jsol.domain.account.application.dto.DeleteAccountsDto
import kr.co.jsol.domain.account.application.dto.UpdateAccountDto
import kr.co.jsol.domain.account.infrastructure.dto.AccountDto
import kr.co.jsol.domain.userdetails.UserDetailsImpl
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
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
) {

    @Operation(summary = "유저 생성 [추후 IDM 이전시 사용]")
    @PreAuthorize(AccountAuthority.ROLECHECK.HasUserRole)
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

    @Operation(summary = "유저 단일 수정 ")
    @PreAuthorize(AccountAuthority.ROLECHECK.HasUserRole)
    @SecurityRequirement(name = "Bearer Authentication")
    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    fun updateAccount(
        @Valid
        @RequestBody
        updateAccountDto: UpdateAccountDto,
        @AuthenticationPrincipal
        userDetails: UserDetailsImpl,
    ): AccountDto {
        // 요청자가 관리자인지 확인
        if (!userDetails.isManager) {
            updateAccountDto.id = userDetails.id
        }

        return service.update(updateAccountDto)
    }

    @Operation(summary = "유저 삭제")
    @PreAuthorize(AccountAuthority.ROLECHECK.HasUserRole)
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    fun deleteAccounts(
        @Valid
        @RequestBody
        deleteAccountsDto: DeleteAccountsDto,
        @AuthenticationPrincipal
        userDetails: UserDetailsImpl,
    ): List<AccountDto> {
        // 요청자가 관리자인지 확인
        if (!userDetails.isManager) {
            throw IllegalAccessException("관리자만 계정을 생성할 수 있습니다.") // TODO 사용자 정의 예외 처리
        }

        return service.deleteMultiple(deleteAccountsDto)
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

    @Operation(summary = "사용자 조회")
    @PreAuthorize(AccountAuthority.ROLECHECK.HasAnyRole)
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getById(
        @PathVariable
        id: String,
        @AuthenticationPrincipal
        userDetails: UserDetailsImpl,
    ): AccountDto {
        val account = service.getById(id)

        // 요청자와 수정자가 다르고 마스터가 아니라면,
        if (account.id != userDetails.id && !userDetails.isMaster()) {

            // 회사 관리자가 아니라면 불가능
            if (!userDetails.isManager) {
                throw GeneralClientException.ForbiddenException()
            }
        }

        return account
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

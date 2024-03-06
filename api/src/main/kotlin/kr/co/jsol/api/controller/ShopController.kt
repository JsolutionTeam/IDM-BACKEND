package kr.co.jsol.api.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import kr.co.jsol.common.domain.AccountAuthority
import kr.co.jsol.common.paging.PageRequest
import kr.co.jsol.domain.account.application.AccountService
import kr.co.jsol.domain.account.application.dto.GetAccountsDto
import kr.co.jsol.domain.account.infrastructure.dto.AccountDto
import kr.co.jsol.domain.shop.application.ShopService
import kr.co.jsol.domain.shop.application.dto.CreateCompanyDto
import kr.co.jsol.domain.shop.application.dto.GetShopCompaniesDto
import kr.co.jsol.domain.shop.infrastructure.dto.ShopDto
import kr.co.jsol.domain.userdetails.UserDetailsImpl
import org.springdoc.api.annotations.ParameterObject
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/shops")
@Tag(name = "업체", description = "업체(Shop) 관련 API")
class ShopController(
    private val service: ShopService,
    private val accountService: AccountService,
) {

    @Operation(summary = "엠콜샵 사용할 업체 신규 등록 - 최종 관리자만 가능")
    @PreAuthorize(AccountAuthority.ROLECHECK.HasMasterRole)
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCompanyShop(
        @Valid
        @RequestBody
        createCompanyDto: CreateCompanyDto,
    ): ShopDto {
        return service.createCompany(createCompanyDto)
    }

    @Operation(summary = "엠콜샵 사용 업체 사용자 페이지 조회")
    @PreAuthorize(AccountAuthority.ROLECHECK.HasMasterRole)
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("accounts")
    @ResponseStatus(HttpStatus.OK)
    fun findAccountPage(
        @Valid
        @ParameterObject
        getShopCompaniesDto: GetAccountsDto,
        pageRequest: PageRequest,
        @AuthenticationPrincipal
        userDetails: UserDetailsImpl,
    ): Page<AccountDto> {
        val pageable = pageRequest.of()

        // 최종 관리자가 아니라면 해당 업체의 데이터만 조회 가능
        if (userDetails.isNotMaster()) {
            getShopCompaniesDto.shopId = userDetails.shop.id
        }

        return accountService.findOffsetPageBySearch(getShopCompaniesDto, pageable)
    }

    @Operation(summary = "엠콜샵 사용 업체 페이지 조회")
    @PreAuthorize(AccountAuthority.ROLECHECK.HasMasterRole)
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun findCompanyPage(
        @Valid
        @ParameterObject
        getShopCompaniesDto: GetShopCompaniesDto,
        pageRequest: PageRequest,
    ): Page<ShopDto> {
        return service.findCompanyPage(getShopCompaniesDto, pageRequest.of())
    }
}

package kr.co.jsol.api.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import kr.co.jsol.common.domain.AccountAuthority
import kr.co.jsol.common.exception.GeneralClientException
import kr.co.jsol.common.paging.PageRequest
import kr.co.jsol.domain.companysubsidy.application.CompanySubsidyService
import kr.co.jsol.domain.companysubsidy.application.dto.CreateCompanySubsidiesDto
import kr.co.jsol.domain.companysubsidy.application.dto.CreateCompanySubsidyDto
import kr.co.jsol.domain.companysubsidy.application.dto.DeleteCompanySubsidiesDto
import kr.co.jsol.domain.companysubsidy.application.dto.ExistsCompanySubsidyDto
import kr.co.jsol.domain.companysubsidy.application.dto.GetCompanySubsidiesDto
import kr.co.jsol.domain.companysubsidy.application.dto.GetCompanySubsidyPriceDto
import kr.co.jsol.domain.companysubsidy.application.dto.UpdateCompanySubsidiesDto
import kr.co.jsol.domain.companysubsidy.infrastructure.dto.CompanySubsidyDto
import kr.co.jsol.domain.companysubsidy.infrastructure.dto.CompanySubsidyGroupByDetailDto
import kr.co.jsol.domain.userdetails.UserDetailsImpl
import org.springframework.data.domain.Page
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
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/company-subsidies")
@Tag(name = "회사 지원금", description = "회사 지원금 관련 API")
class CompanySubsidyController(
    private val service: CompanySubsidyService,
) {

    @Operation(summary = "회사 지원금 다중 등록")
    @PreAuthorize(AccountAuthority.ROLECHECK.HasMasterRole)
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("list")
    @ResponseStatus(HttpStatus.CREATED)
    fun createCompanySubsidies(
        @Valid
        @RequestBody
        createCompanySubsidiesDto: CreateCompanySubsidiesDto,
    ): List<CompanySubsidyDto> {
        return service.createMultiple(createCompanySubsidiesDto)
    }

    @Operation(summary = "회사 지원금 등록")
    @PreAuthorize(AccountAuthority.ROLECHECK.HasMasterRole)
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCompanySubsidy(
        @Valid
        @RequestBody
        createCompanySubsidyDto: CreateCompanySubsidyDto,
    ): CompanySubsidyDto {
        return service.create(createCompanySubsidyDto)
    }

    @Operation(summary = "회사 지원금 다중 수정")
    @PreAuthorize(AccountAuthority.ROLECHECK.HasMasterRole)
    @SecurityRequirement(name = "Bearer Authentication")
    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    fun updateCompanySubsidy(
        @Valid
        @RequestBody
        updateCompanySubsidiesDto: UpdateCompanySubsidiesDto,
    ) {
        return service.updateMultiple(updateCompanySubsidiesDto)
    }

    @Operation(summary = "회사 지원금 다중 삭제")
    @PreAuthorize(AccountAuthority.ROLECHECK.HasMasterRole)
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    fun deleteCompanySubsidy(
        @Valid
        @RequestBody
        deleteCompanySubsidiesDto: DeleteCompanySubsidiesDto,
    ) {
        return service.deleteMultiple(deleteCompanySubsidiesDto)
    }

    @Operation(summary = "회사 지원금 중복 조회")
    @GetMapping("exists")
    @ResponseStatus(HttpStatus.OK)
    fun exists(
        @Valid
        existsCompanySubsidyDto: ExistsCompanySubsidyDto,
    ): Boolean {
        return service.exists(existsCompanySubsidyDto)
    }

    @Operation(summary = "회사 지원금 가격만 조회")
    @GetMapping("price")
    @ResponseStatus(HttpStatus.OK)
    fun getCompanySubsidyPrice(
        @Valid
        getCompanySubsidyPriceDto: GetCompanySubsidyPriceDto,
    ): Long {
        return service.getPrice(getCompanySubsidyPriceDto)
    }

    @Operation(summary = "회사 지원금 페이지 조회")
    @PreAuthorize(AccountAuthority.ROLECHECK.HasAnyRole)
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun findOffsetPageBySearch(
        @Valid
        getCompanySubsidiesDto: GetCompanySubsidiesDto,
        pageRequest: PageRequest,
        @AuthenticationPrincipal
        userDetails: UserDetailsImpl,
    ): Page<CompanySubsidyGroupByDetailDto> {
        val pageable = pageRequest.of()

        // 최종 관리자가 아니라면 해당 업체의 데이터만 조회 가능
        if (userDetails.isNotMaster()) {
            getCompanySubsidiesDto.shopId = userDetails.shop.id
        }

        return service.findOffsetPageBySearch(getCompanySubsidiesDto, pageable)
    }

    @Operation(summary = "회사 지원금 단일(Detail) 조회")
    @PreAuthorize(AccountAuthority.ROLECHECK.HasAnyRole)
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("details")
    @ResponseStatus(HttpStatus.OK)
    fun getWithDetailById(
        @RequestParam
        idList: List<Long>,
        @AuthenticationPrincipal
        userDetails: UserDetailsImpl,
    ): CompanySubsidyGroupByDetailDto {
        if (idList.isEmpty()) {
            throw GeneralClientException.BadRequestException("요청을 확인해주세요.")
        }

        return service.getWithDetailByIdList(idList)
    }

    @Operation(summary = "회사 지원금 단일 조회")
    @PreAuthorize(AccountAuthority.ROLECHECK.HasAnyRole)
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getById(
        @PathVariable
        id: Long,
        @AuthenticationPrincipal
        userDetails: UserDetailsImpl,
    ): CompanySubsidyDto {
        return service.getById(id)
    }
}

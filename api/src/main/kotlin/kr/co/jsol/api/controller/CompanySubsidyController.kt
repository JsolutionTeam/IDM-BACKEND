package kr.co.jsol.api.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import kr.co.jsol.common.domain.Authority
import kr.co.jsol.domain.companysubsidy.application.CompanySubsidyService
import kr.co.jsol.domain.companysubsidy.application.dto.CreateCompanySubsidyDto
import kr.co.jsol.domain.companysubsidy.application.dto.UpdateCompanySubsidiesDto
import kr.co.jsol.domain.companysubsidy.infrastructure.dto.CompanySubsidyDto
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/company-subsidies")
class CompanySubsidyController(
    private val service: CompanySubsidyService,
) {

    @Operation(summary = "회사 지원금 등록")
    @PreAuthorize(Authority.ROLECHECK.HasAdminRole)
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
    @PreAuthorize(Authority.ROLECHECK.HasAdminRole)
    @SecurityRequirement(name = "Bearer Authentication")
    @PatchMapping()
    @ResponseStatus(HttpStatus.OK)
    fun updateCompanySubsidy(
        @Valid
        @RequestBody
        updateCompanySubsidiesDto: UpdateCompanySubsidiesDto,
    ) {
        return service.updateMultiple(updateCompanySubsidiesDto)
    }
}

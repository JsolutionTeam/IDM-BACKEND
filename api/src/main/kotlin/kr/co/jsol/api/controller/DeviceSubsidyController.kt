package kr.co.jsol.api.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import kr.co.jsol.common.domain.AccountAuthority
import kr.co.jsol.common.paging.PageRequest
import kr.co.jsol.domain.devicesubsidy.application.DeviceSubsidyService
import kr.co.jsol.domain.devicesubsidy.application.dto.CreateDeviceSubsidyDto
import kr.co.jsol.domain.devicesubsidy.application.dto.DeleteDeviceSubsidiesDto
import kr.co.jsol.domain.devicesubsidy.application.dto.GetDeviceSubsidiesDto
import kr.co.jsol.domain.devicesubsidy.application.dto.UpdateDeviceSubsidiesDto
import kr.co.jsol.domain.devicesubsidy.infrastructure.dto.DeviceSubsidyDto
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
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/device-subsidies")
class DeviceSubsidyController(
    private val service: DeviceSubsidyService,
) {

    @Operation(summary = "공시 지원금 등록")
    @PreAuthorize(AccountAuthority.ROLECHECK.HasAdminRole)
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createDeviceSubsidy(
        @Valid
        @RequestBody
        createDeviceSubsidyDto: CreateDeviceSubsidyDto,
    ): DeviceSubsidyDto {
        return service.create(createDeviceSubsidyDto)
    }

    @Operation(summary = "공시 지원금 다중 수정")
    @PreAuthorize(AccountAuthority.ROLECHECK.HasAdminRole)
    @SecurityRequirement(name = "Bearer Authentication")
    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    fun updateDeviceSubsidy(
        @Valid
        @RequestBody
        updateDeviceSubsidiesDto: UpdateDeviceSubsidiesDto,
    ) {
        return service.updateMultiple(updateDeviceSubsidiesDto)
    }

    @Operation(summary = "공시 지원금 다중 삭제")
    @PreAuthorize(AccountAuthority.ROLECHECK.HasAdminRole)
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    fun deleteDeviceSubsidy(
        @Valid
        @RequestBody
        deleteDeviceSubsidiesDto: DeleteDeviceSubsidiesDto,
    ) {
        return service.deleteMultiple(deleteDeviceSubsidiesDto)
    }

    @Operation(summary = "공시 지원금 페이지 조회")
    @PreAuthorize(AccountAuthority.ROLECHECK.HasAnyRole)
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun findOffsetPageBySearch(
        @Valid
        getDeviceSubsidiesDto: GetDeviceSubsidiesDto,
        pageRequest: PageRequest,
    ): Page<DeviceSubsidyDto> {
        val pageable = pageRequest.of()
        return service.findOffsetPageBySearch(getDeviceSubsidiesDto, pageable)
    }

    @Operation(summary = "공시 지원금 단일 조회")
    @PreAuthorize(AccountAuthority.ROLECHECK.HasAnyRole)
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getById(
        @PathVariable
        id: Long,
        @AuthenticationPrincipal
        userDetails: UserDetailsImpl,
    ): DeviceSubsidyDto {
        val companySubsidyDto = service.getById(id)
        return companySubsidyDto
    }
}

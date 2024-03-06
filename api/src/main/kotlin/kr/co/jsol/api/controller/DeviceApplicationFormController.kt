package kr.co.jsol.api.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import kr.co.jsol.common.domain.AccountAuthority
import kr.co.jsol.common.paging.PageRequest
import kr.co.jsol.domain.deviceapplicationform.application.DeviceApplicationFormService
import kr.co.jsol.domain.deviceapplicationform.application.dto.CreateDeviceApplicationFormDto
import kr.co.jsol.domain.deviceapplicationform.application.dto.GetDeviceApplicationFormsDto
import kr.co.jsol.domain.deviceapplicationform.infrastructure.dto.DeviceApplicationFormDto
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
@RequestMapping("/api/v1/device-application-forms")
@Tag(name = "단말 신청서", description = "단말 신청서 관련 API")
class DeviceApplicationFormController(
    private val service: DeviceApplicationFormService,
) {

    @Operation(summary = "단말 신청서 등록")
    @PreAuthorize(AccountAuthority.ROLECHECK.HasAnyRole)
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createDeviceApplicationForm(
        @Valid
        @RequestBody
        createDeviceApplicationFormDto: CreateDeviceApplicationFormDto,
        @AuthenticationPrincipal
        userDetails: UserDetailsImpl,
    ): DeviceApplicationFormDto {
        return service.create(userDetails.shop.id, createDeviceApplicationFormDto)
    }

    @Operation(summary = "단말 신청서 페이지 조회")
    @PreAuthorize(AccountAuthority.ROLECHECK.HasAnyRole)
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun createDeviceApplicationForm(
        @Valid
        @ParameterObject
        getDeviceApplicationFormsDto: GetDeviceApplicationFormsDto,
        pageRequest: PageRequest,
    ): Page<DeviceApplicationFormDto> {
        val pageable = pageRequest.of()
        return service.findOffsetPageBySearch(getDeviceApplicationFormsDto, pageable)
    }
}

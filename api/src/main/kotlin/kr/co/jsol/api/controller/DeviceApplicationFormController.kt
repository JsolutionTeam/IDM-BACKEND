package kr.co.jsol.api.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import kr.co.jsol.common.domain.AccountAuthority
import kr.co.jsol.common.exception.domain.deviceapplicationform.DeviceApplicationFormException
import kr.co.jsol.common.paging.PageRequest
import kr.co.jsol.domain.deviceapplicationform.application.DeviceApplicationFormService
import kr.co.jsol.domain.deviceapplicationform.application.dto.CreateDeviceApplicationFormDto
import kr.co.jsol.domain.deviceapplicationform.application.dto.GetDeviceApplicationFormsDto
import kr.co.jsol.domain.deviceapplicationform.application.dto.UpdateDeviceApplicationFormDto
import kr.co.jsol.domain.deviceapplicationform.infrastructure.dto.DeviceApplicationFormDto
import kr.co.jsol.domain.userdetails.UserDetailsImpl
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
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

    @Operation(summary = "단말 신청서 수정")
    @PreAuthorize(AccountAuthority.ROLECHECK.HasAnyRole)
    @SecurityRequirement(name = "Bearer Authentication")
    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    fun updateDeviceApplicationForm(
        @Valid
        @RequestBody
        updateDeviceApplicationFormDto: UpdateDeviceApplicationFormDto,
        @AuthenticationPrincipal
        userDetails: UserDetailsImpl,
    ): DeviceApplicationFormDto {
        return service.update(updateDeviceApplicationFormDto, userDetails)
    }

    @Operation(summary = "단말 신청서 단일 조회")
    @PreAuthorize(AccountAuthority.ROLECHECK.HasAnyRole)
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getById(
        @PathVariable
        id: Long,
        @AuthenticationPrincipal
        userDetails: UserDetailsImpl,
    ): DeviceApplicationFormDto {
        val deviceApplicationFormDto = service.getById(id)

        if (userDetails.isNotMaster()) {
            if (deviceApplicationFormDto.shop.id != userDetails.shop.id) {
                // 권한 에러로 반환하지 않고 찾을 수 없음으로 반환
                throw DeviceApplicationFormException.NotFoundByIdException()
            }
        }

        return deviceApplicationFormDto
    }

    @Operation(summary = "단말 신청서 페이지 조회")
    @PreAuthorize(AccountAuthority.ROLECHECK.HasAnyRole)
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun createDeviceApplicationForm(
        @Valid
        getDeviceApplicationFormsDto: GetDeviceApplicationFormsDto,
        pageRequest: PageRequest,
        @AuthenticationPrincipal
        userDetails: UserDetailsImpl,
    ): Page<DeviceApplicationFormDto> {
        val pageable = pageRequest.of()

        if (userDetails.isNotMaster()) {
            // 일반 사용자는 자신의 업체 단말 신청서만 조회 가능
            getDeviceApplicationFormsDto.shopId = userDetails.shop.id
        }

        return service.findOffsetPageBySearch(getDeviceApplicationFormsDto, pageable)
    }
}

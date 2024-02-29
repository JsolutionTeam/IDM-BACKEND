package kr.co.jsol.api.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import kr.co.jsol.common.domain.AccountAuthority
import kr.co.jsol.domain.deviceinfo.application.DeviceInfoService
import kr.co.jsol.domain.deviceinfo.application.dto.CreateDeviceInfoDto
import kr.co.jsol.domain.deviceinfo.infrastructure.dto.DeviceInfoDto
import kr.co.jsol.domain.deviceinfo.infrastructure.dto.DeviceInfoGroupByDeviceSeriesDto
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
@RequestMapping("/api/v1/device-info")
class DeviceInfoController(
    private val service: DeviceInfoService,
) {

    @Operation(summary = "단말 상세 등록")
    @PreAuthorize(AccountAuthority.ROLECHECK.HasAdminRole)
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createDeviceSubsidy(
        @Valid
        @RequestBody
        createDeviceInfoDto: CreateDeviceInfoDto,
    ): DeviceInfoDto {
        return service.create(createDeviceInfoDto)
    }

//    @Operation(summary = "단말 상세 다중 수정")
//    @PreAuthorize(AccountAuthority.ROLECHECK.HasAdminRole)
//    @SecurityRequirement(name = "Bearer Authentication")
//    @PatchMapping
//    @ResponseStatus(HttpStatus.OK)
//    fun updateDeviceSubsidy(
//        @Valid
//        @RequestBody
//        updateDeviceSubsidiesDto: UpdateDeviceSubsidiesDto,
//    ) {
//        return service.updateMultiple(updateDeviceSubsidiesDto)
//    }

//    @Operation(summary = "단말 상세 다중 삭제")
//    @PreAuthorize(AccountAuthority.ROLECHECK.HasAdminRole)
//    @SecurityRequirement(name = "Bearer Authentication")
//    @DeleteMapping
//    @ResponseStatus(HttpStatus.OK)
//    fun deleteDeviceSubsidy(
//        @Valid
//        @RequestBody
//        deleteDeviceSubsidiesDto: DeleteDeviceSubsidiesDto,
//    ) {
//        return service.deleteMultiple(deleteDeviceSubsidiesDto)
//    }

//    @Operation(summary = "단말 상세 페이지 조회")
//    @PreAuthorize(AccountAuthority.ROLECHECK.HasAnyRole)
//    @SecurityRequirement(name = "Bearer Authentication")
//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    fun findOffsetPageBySearch(
//        @Valid
//        getDeviceSubsidiesDto: GetDeviceSubsidiesDto,
//        pageRequest: PageRequest,
//    ): Page<DeviceInfoDto> {
//        val pageable = pageRequest.of()
//        return service.findOffsetPageBySearch(getDeviceSubsidiesDto, pageable)
//    }

    @Operation(summary = "단말 상세 시리즈별 조회")
    @GetMapping("device/{series}")
    @ResponseStatus(HttpStatus.OK)
    fun findOffsetPageBySearch(
        @PathVariable
        series: String,
    ): List<DeviceInfoGroupByDeviceSeriesDto> {
        return service.findByDeviceSeriesGroupByDeviceSeries(series)
    }

    @Operation(summary = "단말 상세 단일 조회")
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getById(
        @PathVariable
        id: Long,
        @AuthenticationPrincipal
        userDetails: UserDetailsImpl,
    ): DeviceInfoDto {
        val companySubsidyDto = service.getById(id)
        return companySubsidyDto
    }
}

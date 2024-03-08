package kr.co.jsol.api.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import kr.co.jsol.common.domain.AccountAuthority
import kr.co.jsol.common.paging.PageRequest
import kr.co.jsol.domain.telecomdevice.application.TelecomDeviceService
import kr.co.jsol.domain.telecomdevice.application.dto.CreateTelecomDeviceDto
import kr.co.jsol.domain.telecomdevice.application.dto.GetTelecomDevicesDto
import kr.co.jsol.domain.telecomdevice.infrastructure.dto.TelecomDeviceDto
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/telecom-devices")
@Tag(name = "통신팀 판매용 단말", description = "통신팀 판매 단말 정보 관리 API")
class TelecomDeviceController(
    private val service: TelecomDeviceService,
) {

    @Operation(summary = "통신 단말 정보 단일 등록")
    @PreAuthorize(AccountAuthority.ROLECHECK.HasMasterRole)
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(
        @Valid
        @RequestBody
        createTelecomDeviceDto: CreateTelecomDeviceDto,
    ): TelecomDeviceDto {
        return service.create(createTelecomDeviceDto)
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

    @Operation(summary = "단말 상세 페이지 조회")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun findOffsetPageBySearch(
        @Valid
        getTelecomDevicesDto: GetTelecomDevicesDto,
        pageRequest: PageRequest,
    ): Page<TelecomDeviceDto> {
        val pageable = pageRequest.of()
        return service.findOffsetPageBySearch(getTelecomDevicesDto, pageable)
    }

    @Operation(summary = "단말 상세 단일 조회")
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getById(
        @PathVariable
        id: Long,
    ): TelecomDeviceDto {
        val companySubsidyDto = service.getById(id)
        return companySubsidyDto
    }
}

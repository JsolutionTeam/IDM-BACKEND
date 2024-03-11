package kr.co.jsol.api.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import kr.co.jsol.common.domain.AccountAuthority
import kr.co.jsol.common.file.application.dto.FileDto
import kr.co.jsol.common.paging.PageRequest
import kr.co.jsol.domain.telecomdevice.application.TelecomDeviceService
import kr.co.jsol.domain.telecomdevice.application.dto.CreateTelecomDeviceDto
import kr.co.jsol.domain.telecomdevice.application.dto.GetTelecomDevicesDto
import kr.co.jsol.domain.telecomdevice.application.dto.PostTelecomDeviceImageDto
import kr.co.jsol.domain.telecomdevice.application.dto.UpdateTelecomDeviceDto
import kr.co.jsol.domain.telecomdevice.application.dto.UpdateTelecomDeviceIsDisplayDto
import kr.co.jsol.domain.telecomdevice.application.dto.UpdateTelecomDeviceIsDisplaysDto
import kr.co.jsol.domain.telecomdevice.application.dto.UpdateTelecomDevicesDto
import kr.co.jsol.domain.telecomdevice.infrastructure.dto.TelecomDeviceDto
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
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
import org.springframework.web.multipart.MultipartFile
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
    fun createTelecomDevice(
        @Valid
        @RequestBody
        createTelecomDeviceDto: CreateTelecomDeviceDto,
    ): TelecomDeviceDto {
        return service.create(createTelecomDeviceDto)
    }

    @Operation(summary = "통신팀 판매용 단말의 대표 이미지 등록 (수정, 삭제X) 기존 이미지 파일이 남아있게 됨")
    @PreAuthorize(AccountAuthority.ROLECHECK.HasMasterRole)
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("image", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    fun postImage(
        @RequestParam("id")
        id: Long,
        @RequestParam("file")
        file: MultipartFile,
    ): FileDto {
        return service.postImage(PostTelecomDeviceImageDto(id, file))
    }

    @Operation(summary = "통신 단말 정보 다중 수정")
    @PreAuthorize(AccountAuthority.ROLECHECK.HasMasterRole)
    @SecurityRequirement(name = "Bearer Authentication")
    @PatchMapping("list")
    @ResponseStatus(HttpStatus.OK)
    fun updateTelecomDevices(
        @Valid
        @RequestBody
        updateTelecomDevicesDto: UpdateTelecomDevicesDto,
    ): List<TelecomDeviceDto> {
        return service.updateMultiple(updateTelecomDevicesDto)
    }

    @Operation(summary = "통신 단말 정보 단일 수정")
    @PreAuthorize(AccountAuthority.ROLECHECK.HasMasterRole)
    @SecurityRequirement(name = "Bearer Authentication")
    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    fun updateTelecomDevice(
        @Valid
        @RequestBody
        updateTelecomDeviceDto: UpdateTelecomDeviceDto,
    ): TelecomDeviceDto {
        return service.update(updateTelecomDeviceDto)
    }

    @Operation(summary = "통신 단말 표시 여부 다중 수정")
    @PreAuthorize(AccountAuthority.ROLECHECK.HasMasterRole)
    @SecurityRequirement(name = "Bearer Authentication")
    @PatchMapping("is-displays/list")
    @ResponseStatus(HttpStatus.OK)
    fun updateIsDisplayMultiple(
        @Valid
        @RequestBody
        updateTelecomDeviceIsDisplaysDto: UpdateTelecomDeviceIsDisplaysDto,
    ): List<TelecomDeviceDto> {
        return service.updateIsDisplayMultiple(updateTelecomDeviceIsDisplaysDto)
    }

    @Operation(summary = "통신 단말 표시 여부 단일 수정")
    @PreAuthorize(AccountAuthority.ROLECHECK.HasMasterRole)
    @SecurityRequirement(name = "Bearer Authentication")
    @PatchMapping("is-displays")
    @ResponseStatus(HttpStatus.OK)
    fun updateIsDisplay(
        @Valid
        @RequestBody
        updateTelecomDeviceIsDisplayDto: UpdateTelecomDeviceIsDisplayDto,
    ): TelecomDeviceDto {
        return service.updateIsDisplay(updateTelecomDeviceIsDisplayDto)
    }

    @Operation(summary = "통신 단말 정보 다중 삭제")
    @PreAuthorize(AccountAuthority.ROLECHECK.HasMasterRole)
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("list")
    @ResponseStatus(HttpStatus.OK)
    fun deleteTelecomDevices(
        @RequestParam
        ids: List<Long>,
    ) {
        return service.deleteMultiple(ids)
    }

    @Operation(summary = "통신 단말 정보 페이지 조회")
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

    @Operation(summary = "통신 단말 정보 단일 조회")
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

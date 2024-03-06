package kr.co.jsol.api.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import kr.co.jsol.common.domain.AccountAuthority
import kr.co.jsol.common.paging.PageRequest
import kr.co.jsol.domain.deviceinfo.application.DeviceInfoService
import kr.co.jsol.domain.deviceinfo.application.dto.CreateDeviceInfoDto
import kr.co.jsol.domain.deviceinfo.application.dto.GetDeviceInfoSearchDto
import kr.co.jsol.domain.deviceinfo.application.dto.GetDeviceInfosDto
import kr.co.jsol.domain.deviceinfo.application.dto.PostDeviceInfoImage
import kr.co.jsol.domain.deviceinfo.infrastructure.dto.DeviceInfoDto
import kr.co.jsol.domain.deviceinfo.infrastructure.dto.DeviceInfoGroupByDeviceSeriesDto
import org.springdoc.api.annotations.ParameterObject
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
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
@RequestMapping("/api/v1/device-info")
@Tag(name = "단말 상세", description = "단말 상세 관련 API")
class DeviceInfoController(
    private val service: DeviceInfoService,
) {

    @Operation(summary = "단말 상세 등록")
    @PreAuthorize(AccountAuthority.ROLECHECK.HasMasterRole)
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

    @Operation(summary = "단말 상세 이미지 등록 (수정, 삭제X) 기존 이미지 파일이 남아있게 됨")
    @PreAuthorize(AccountAuthority.ROLECHECK.HasMasterRole)
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("image", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    fun postImage(
        @RequestParam("id")
        id: Long,
        @RequestParam("file")
        file: MultipartFile,
    ): String {
        return service.postImage(
            PostDeviceInfoImage(
                id,
                file,
            )
        )
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
        getDeviceInfosDto: GetDeviceInfosDto,
        pageRequest: PageRequest,
    ): Page<DeviceInfoDto> {
        val pageable = pageRequest.of()
        return service.findOffsetPageBySearch(getDeviceInfosDto, pageable)
    }

    @Operation(summary = "단말 시리즈/단말/색상으로 검색 조회")
    @GetMapping("search")
    @ResponseStatus(HttpStatus.OK)
    fun getBySearch(
        @Valid
        @ParameterObject
        getDeviceInfoSearchDto: GetDeviceInfoSearchDto,
    ): DeviceInfoDto? {
        return service.findBySeriesAndDeviceIdAndColorId(getDeviceInfoSearchDto)
    }

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
    ): DeviceInfoDto {
        val companySubsidyDto = service.getById(id)
        return companySubsidyDto
    }
}

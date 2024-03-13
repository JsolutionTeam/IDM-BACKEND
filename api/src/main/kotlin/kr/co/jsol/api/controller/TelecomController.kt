package kr.co.jsol.api.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import kr.co.jsol.common.domain.AccountAuthority
import kr.co.jsol.domain.telecom.application.TelecomService
import kr.co.jsol.domain.telecom.application.dto.CreateTelecomDto
import kr.co.jsol.domain.telecom.application.dto.UpdateTelecomDto
import kr.co.jsol.domain.telecom.infrastructure.dto.TelecomDto
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
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
@RequestMapping("/api/v1/telecoms")
@Tag(name = "통신사", description = "통신사 관리 API")
class TelecomController(
    private val service: TelecomService,
) {

    @Operation(summary = "통신사 단일 등록")
    @PreAuthorize(AccountAuthority.ROLECHECK.HasMasterRole)
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createTelecom(
        @Valid
        @RequestBody
        createTelecomDto: CreateTelecomDto,
    ): TelecomDto {
        return service.create(createTelecomDto)
    }

    @Operation(summary = "통신사 단일 수정")
    @PreAuthorize(AccountAuthority.ROLECHECK.HasMasterRole)
    @SecurityRequirement(name = "Bearer Authentication")
    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    fun updateTelecom(
        @Valid
        @RequestBody
        updateTelecomDto: UpdateTelecomDto,
    ): TelecomDto {
        return service.update(updateTelecomDto)
    }

    @Operation(summary = "통신사 단일 삭제")
    @PreAuthorize(AccountAuthority.ROLECHECK.HasMasterRole)
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    fun deleteTelecom(
        @RequestBody
        id: Long,
    ): TelecomDto {
        return service.delete(id)
    }

    @Operation(summary = "통신사 단일 조회")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getById(
        @PathVariable
        id: Long,
    ): TelecomDto {
        return service.getById(id)
    }

    @Operation(summary = "통신사 전체 조회")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun findAll(): List<TelecomDto> {
        return service.findAll()
    }
}

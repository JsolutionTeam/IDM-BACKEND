package kr.co.jsol.api.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import kr.co.jsol.common.domain.AccountAuthority
import kr.co.jsol.common.paging.PageRequest
import kr.co.jsol.domain.notice.application.NoticeService
import kr.co.jsol.domain.notice.application.dto.CreateNoticeDto
import kr.co.jsol.domain.notice.application.dto.DeleteNoticesDto
import kr.co.jsol.domain.notice.application.dto.GetNoticesDto
import kr.co.jsol.domain.notice.application.dto.UpdateNoticesDto
import kr.co.jsol.domain.notice.infrastructure.dto.NoticeDto
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
@RequestMapping("/api/v1/notices")
@Tag(name = "공지사항", description = "공지사항 관련 API")
class NoticeController(
    private val service: NoticeService,
) {
    @Operation(summary = "공지사항 등록")
    @PreAuthorize(AccountAuthority.ROLECHECK.HasMasterRole)
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCompanySubsidy(
        @Valid
        @RequestBody
        createNoticeDto: CreateNoticeDto,
        @AuthenticationPrincipal
        userDetailsImpl: UserDetailsImpl,
    ): NoticeDto {
        val writer = userDetailsImpl.name
        return service.create(createNoticeDto, writer)
    }

    @Operation(summary = "공지사항 다중 수정")
    @PreAuthorize(AccountAuthority.ROLECHECK.HasMasterRole)
    @SecurityRequirement(name = "Bearer Authentication")
    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    fun updateCompanySubsidy(
        @Valid
        @RequestBody
        updateCompanySubsidiesDto: UpdateNoticesDto,
        @AuthenticationPrincipal
        userDetailsImpl: UserDetailsImpl,
    ): List<NoticeDto> {
        val writer = userDetailsImpl.name
        return service.updateMultiple(updateCompanySubsidiesDto, writer)
    }

    @Operation(summary = "공지사항 다중 삭제")
    @PreAuthorize(AccountAuthority.ROLECHECK.HasMasterRole)
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    fun deleteCompanySubsidy(
        @Valid
        @RequestBody
        deleteNoticesDto: DeleteNoticesDto,
    ) {
        return service.deleteMultiple(deleteNoticesDto)
    }

    @Operation(summary = "공지사항 페이지 조회")
    @PreAuthorize(AccountAuthority.ROLECHECK.HasAnyRole)
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun findOffsetPageBySearch(
        @Valid
        getNoticesDto: GetNoticesDto,
        pageRequest: PageRequest,
        @AuthenticationPrincipal
        userDetails: UserDetailsImpl,
    ): Page<NoticeDto> {
        val pageable = pageRequest.of()

        return service.findOffsetPageBySearch(getNoticesDto, pageable)
    }

    @Operation(summary = "공지사항 단일 조회")
    @PreAuthorize(AccountAuthority.ROLECHECK.HasAnyRole)
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getById(
        @PathVariable
        id: Long,
    ): NoticeDto {
        return service.getById(id)
    }
}

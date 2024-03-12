package kr.co.jsol.api.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import kr.co.jsol.common.domain.AccountAuthority
import kr.co.jsol.common.file.application.FileService
import kr.co.jsol.common.file.application.dto.DomainType
import kr.co.jsol.common.file.application.dto.FileDto
import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.support.ResourceRegion
import org.springframework.http.CacheControl
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.MediaTypeFactory
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.util.concurrent.TimeUnit

@RestController
//@RequestMapping("api/v2/local-files")
@RequestMapping("\${custom.api-url.shop.local-files:/api/v2/local-files}")
@Tag(name = "로컬 파일 관련 공개 API")
class LocalFileController(
    private val service: FileService,
) {
    @Operation(summary = "로컬 파일 시스템에서 동영상 파일을 스트리밍 다운로드")
    @GetMapping("streaming")
    @ResponseStatus(HttpStatus.OK)
    fun viewStreaming(
        @RequestParam
        filename: String,
        @RequestHeader
        headers: HttpHeaders,
    ): ResponseEntity<ResourceRegion> {
        val (path, resource, region) = service.streaming(filename, headers)
        return ResponseEntity
            .status(HttpStatus.PARTIAL_CONTENT)
            .cacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES))
            .contentType(
                MediaTypeFactory.getMediaType(resource)
                    .orElse(MediaType.APPLICATION_OCTET_STREAM)
            )
            .header("Accept-Ranges", "bytes")
            .eTag(path)
            .body<ResourceRegion>(region)
    }

    /**
     * 아래 API는 기존 시스템에서 사용하는 부분이 있기 때문에 API 스펙을 바꿔선 안되고
     * 기능이 크게 수정되어야 할 경우 새로운 API를 추가하고 기존 API는 Deprecated 처리해야 합니다.
     */
    @Operation(summary = "로컬 파일 시스템에서 동영상 파일을 한 번에 조회")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun loadFile(
        @RequestParam
        filename: String,
    ): ResponseEntity<FileSystemResource> {
        val resource = service.getFile(filename)
        val contentType = MediaTypeFactory.getMediaType(resource).orElseThrow {
            RuntimeException("미디어 타입을 찾을 수 없습니다.")
        }
        return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(contentType)
            .header("Accept-Ranges", "bytes")
            .body(resource as FileSystemResource)
    }

    @Operation(summary = "이미지 등록 (수정, 삭제X) 기존 이미지 파일이 남아있게 됨")
    @PreAuthorize(AccountAuthority.ROLECHECK.HasMasterRole)
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    fun postImage(
        @RequestParam("type")
        type: DomainType,
        @RequestParam("file")
        file: MultipartFile,
    ): FileDto {
        return service.addFile(
            type.rootDir,
            file
        )
    }

    @Operation(summary = "로컬 파일 시스템에서 파일 삭제, 없는 파일을 요청해도 성공(200 OK)으로 처리됨.")
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    fun deleteFile(
        @RequestParam
        filename: String,
    ): Boolean {
        return service.deleteFile(filename)
    }
}

package kr.co.jsol.api.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import kr.co.jsol.common.file.application.FileService
import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.support.ResourceRegion
import org.springframework.http.CacheControl
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.MediaTypeFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.TimeUnit

@RestController
@RequestMapping("api/v2/local-files")
@Tag(name = "로컬 파일 관련 공개 API")
class LocalFileController(
    private val localFileService: FileService,
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
        val (path, resource, region) = localFileService.streaming(filename, headers)
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

    @Operation(summary = "로컬 파일 시스템에서 동영상 파일을 한 번에 다운로드")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun loadFile(
        @RequestParam
        filename: String,
    ): ResponseEntity<FileSystemResource> {
        val resource = localFileService.getFile(filename)
        val contentType = MediaTypeFactory.getMediaType(resource).orElseThrow {
            RuntimeException("미디어 타입을 찾을 수 없습니다.")
        }
        return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(contentType)
            .header("Accept-Ranges", "bytes")
            .body(resource as FileSystemResource)
    }

    @Operation(summary = "로컬 파일 시스템에서 파일 삭제, 없는 파일을 요청해도 성공(200 OK)으로 처리됨.")
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    fun deleteFile(
        @RequestParam
        filename: String,
    ): Boolean {
        return localFileService.deleteFile(filename)
    }
}

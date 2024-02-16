package kr.co.jsol.api.controller

import io.swagger.annotations.Api
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.RequestBody

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/telecom")
@Api(description = "통신사 관련 API")
class TelecomController {
    private val telecomService: TelecomService? = null

    @ApiOperation("통신사 등록 = 마스터롤만 가능")
    @PostMapping("")
    fun addTelecom(
        @RequestBody
        telecomDto: TelecomDto?,
        @AuthenticationPrincipal
        account: Account
    ): ResponseEntity<*> {
        val role: String = account.getRole()

        return telecomService.addTelecom(telecomDto, role)
    }

    @get:GetMapping("")
    @get:ApiOperation("통신사 조회 = 모든롤 가능")
    val telecom: ResponseEntity<*>
        get() = telecomService.getTelecom()

    @ApiOperation("통신사 수정 = 마스터롤만 가능")
    @PutMapping("{telecomIdx}")
    fun updateTelecom(
        @RequestBody
        telecomDto: TelecomDto?,
        @PathVariable
        telecomIdx: Long?,
        @AuthenticationPrincipal
        account: Account
    ): ResponseEntity<*> {
        val role: String = account.getRole()

        return telecomService.updateTelecom(telecomIdx, telecomDto, role)
    }

    @ApiOperation("통신사 삭제 = 마스터롤만 가능")
    @DeleteMapping("/{telecomIdx}")
    fun deleteTelecom(
        @PathVariable
        telecomIdx: Long?,
        @AuthenticationPrincipal
        account: Account
    ): ResponseEntity<*> {
        val role: String = account.getRole()

        return telecomService.deleteTelecom(telecomIdx, role)
    }
}

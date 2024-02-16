package kr.co.jsol.api.controller

import io.swagger.annotations.Api
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.RequestBody
import javax.validation.Valid

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/telecomdata")
@Api(description = "통신사 데이터 관련 API")
class TelecomDataController {
    private val telecomDataService: TelecomDataService? = null

    @ApiOperation("통신사 데이터 등록 = 마스터롤만 가능")
    @PostMapping("")
    fun addTelecomData(
        @RequestBody
        telecomDataDto: @Valid TelecomDataDto?,
        @AuthenticationPrincipal
        account: Account
    ): ResponseEntity<ResponseDto> {
        val role: String = account.getRole()
        val result: Long = telecomDataService.addTelecomData(telecomDataDto, role)

        return ResponseEntity<ResponseDto>(ResponseDto(result, true, "통신사 데이터 등록에 성공 하였습니다."), HttpStatus.OK)
    }

    @get:GetMapping("")
    @get:ApiOperation("통신사 데이터 조회 = 모든롤 가능")
    val telecomData: ResponseEntity<List<TelecomDataResDto>>
        get() {
            val telecomDataList: List<TelecomDataResDto> = telecomDataService.getTelecomData()

            return ResponseEntity<List<TelecomDataResDto>>(telecomDataList, HttpStatus.OK)
        }

    @ApiOperation("통신사 데이터 수정 = 마스터롤만 가능 , telecomIdx 수정 불가능")
    @PutMapping("{telecomDataIdx}")
    fun updateTelecomData(
        @RequestBody
        telecomDataDto: @Valid TelecomDataDto?,
        @PathVariable
        telecomDataIdx: Long?,
        @AuthenticationPrincipal
        account: Account
    ): ResponseEntity<ResponseDto> {
        val role: String = account.getRole()
        val result: Long = telecomDataService.updateTelecomData(telecomDataIdx, telecomDataDto, role)

        return ResponseEntity<ResponseDto>(ResponseDto(result, true, "통신사 데이터 수정에 성공 했습니다."), HttpStatus.OK)
    }

    // 리스폰 다시 생각해 봐야함
    @ApiOperation("통신사 데이터 삭제 = 마스터롤만 가능")
    @DeleteMapping("/{telecomDataIdx}")
    fun deleteTelecomData(
        @PathVariable
        telecomDataIdx: Long?,
        @AuthenticationPrincipal
        account: Account
    ): ResponseEntity<ResponseDto> {
        val role: String = account.getRole()
        val result: Boolean = telecomDataService.deleteTelecomData(telecomDataIdx, role)

        return ResponseEntity<ResponseDto>(ResponseDto(result, "통신사 데이터 삭제를 완료 했습니다."), HttpStatus.OK)
    }
}

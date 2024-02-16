package kr.co.jsol.api.controller

import io.swagger.annotations.Api
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.RequestBody
import javax.validation.Valid

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/option")
@Api(description = "마스터옵션 관련 API")
class MasterOptionController {
    private val masterOptionService: MasterOptionService? = null

    @ApiOperation("등록 = 마스터롤만 가능")
    @PostMapping("")
    fun addMasterOption(
        @RequestBody
        masterOptionDto: @Valid MasterOptionDto?,
        @AuthenticationPrincipal
        account: Account
    ): ResponseEntity<ResponseDto> {
        val role: String = account.getRole()

        val result: Long = masterOptionService.addMasterOption(masterOptionDto, role)

        return ResponseEntity<ResponseDto>(ResponseDto(result, true, "마스터 옵션을 등록 하였습니다."), HttpStatus.OK)
    }

    @get:GetMapping("")
    @get:ApiOperation("조회 = 모든롤 가능")
    val masterOption: ResponseEntity<List<MasterOptionResDto>>
        get() {
            val masterOptionList: List<MasterOptionResDto> = masterOptionService.getMasterOption()

            return ResponseEntity<List<MasterOptionResDto>>(masterOptionList, HttpStatus.OK)
        }

    @ApiOperation("수정 = 마스터롤만 가능")
    @PutMapping("/{masterOptionIdx}")
    fun updateMasterOption(
        @RequestBody
        masterOptionDto: @Valid MasterOptionDto?,
        @PathVariable
        masterOptionIdx: Long?,
        @AuthenticationPrincipal
        account: Account
    ): ResponseEntity<ResponseDto> {
        val role: String = account.getRole()

        val result: Long = masterOptionService.updateMasterOption(masterOptionDto, masterOptionIdx, role)

        return ResponseEntity<ResponseDto>(ResponseDto(result, true, "마스터옵션을 수정 하였습니다."), HttpStatus.OK)
    }

    @ApiOperation("삭제 = 마스터롤만 가능")
    @DeleteMapping("/{masterOptionIdx}")
    fun deleteMasterOption(
        @PathVariable
        masterOptionIdx: Long?,
        @AuthenticationPrincipal
        account: Account
    ): ResponseEntity<ResponseDto> {
        val role: String = account.getRole()

        val result: Boolean = masterOptionService.deleteMasterOption(masterOptionIdx, role)

        return ResponseEntity<ResponseDto>(ResponseDto(result, "마스터옵션을 삭제하였습니다."), HttpStatus.OK)
    }
}

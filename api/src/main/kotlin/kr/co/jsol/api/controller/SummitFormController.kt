//package kr.co.jsol.api.controller
//
//import io.swagger.annotations.Api
//import org.springframework.http.HttpStatus
//import org.springframework.security.core.annotation.AuthenticationPrincipal
//import org.springframework.web.bind.annotation.RequestBody
//import javax.validation.Valid
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/v1/summitform")
//@Api(description = "신청서 관련 API")
//class SummitFormController {
//    private val summitFormService: SummitFormService? = null
//
//    @ApiOperation("신청서 등록 = 유저롤만 가능")
//    @PostMapping("")
//    fun addSummitForm(
//        @RequestBody
//        summitFormDto: @Valid SummitFormDto?,
//        @AuthenticationPrincipal
//        userAccount: UserAccount
//    ): ResponseEntity<ResponseDto> {
//        val role: String = userAccount.getRole()
//        val phoneNumber: String = userAccount.getPhoneNumber()
//
//        val result: Long = summitFormService.addSummitForm(summitFormDto, phoneNumber, role)
//
//        return ResponseEntity<ResponseDto>(ResponseDto(result, true, "신청서 등록이 완료되었습니다."), HttpStatus.OK)
//    }
//
//    @ApiOperation("신청서 조회 = 자신이 작성한 신청서 조회 유저롤만 가능")
//    @GetMapping("")
//    fun getSummitForm(
//        @AuthenticationPrincipal
//        userAccount: UserAccount
//    ): ResponseEntity<List<SummitFormResDto>> {
//        val phoneNumber: String = userAccount.getPhoneNumber()
//
//        val result: List<SummitFormResDto> = summitFormService.getSummitForm(phoneNumber)
//
//        return ResponseEntity<List<SummitFormResDto>>(result, HttpStatus.OK)
//    }
//
//    @ApiOperation("신청서 조회 = 마스터롤이 모든 신청서 조회")
//    @GetMapping("/master")
//    fun getSummitFormList(
//        @AuthenticationPrincipal
//        account: Account
//    ): ResponseEntity<List<SummitFormResDto>> {
//        val role: String = account.getRole()
//
//        val result: List<SummitFormResDto> = summitFormService.getSummitFormList(role)
//
//        return ResponseEntity<List<SummitFormResDto>>(result, HttpStatus.OK)
//    }
//
//    @ApiOperation("신청서 수정 =  유저롤만 가능")
//    @PutMapping("{SummitFormIdx}")
//    fun updateSummitForm(
//        @RequestBody
//        summitFormDto: @Valid SummitFormDto?,
//        @PathVariable
//        SummitFormIdx: Long?,
//        @AuthenticationPrincipal
//        userAccount: UserAccount
//    ): ResponseEntity<ResponseDto> {
//        val role: String = userAccount.getRole()
//        val phoneNumber: String = userAccount.getPhoneNumber()
//
//        val result: Long = summitFormService.updateSummitForm(SummitFormIdx, summitFormDto, phoneNumber, role)
//
//        return ResponseEntity<ResponseDto>(ResponseDto(result, true, "신청서 수정이 완료되었습니다."), HttpStatus.OK)
//    }
//
//    @ApiOperation("신청서 삭제 = 유저롤만 가능")
//    @DeleteMapping("/{SummitFormIdx}")
//    fun deleteSummitForm(
//        @PathVariable
//        SummitFormIdx: Long?,
//        @AuthenticationPrincipal
//        userAccount: UserAccount
//    ): ResponseEntity<*> {
//        val role: String = userAccount.getRole()
//        val phoneNumber: String = userAccount.getPhoneNumber()
//
//        val result: Boolean = summitFormService.deleteSummitForm(SummitFormIdx, phoneNumber, role)
//
//        return ResponseEntity<Any>(ResponseDto(result, "신청서 삭제가 완료 되었습니다"), HttpStatus.OK)
//    }
//}

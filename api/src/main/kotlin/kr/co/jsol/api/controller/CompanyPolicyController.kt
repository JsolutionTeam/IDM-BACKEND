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
//@RequestMapping("/api/v1/companypolicy")
//@Api(description = "회사정책 관련 API")
//class CompanyPolicyController {
//    private val companyPolicyService: CompanyPolicyService? = null
//
//    @ApiOperation("회사 정책 등록 = 컴퍼니롤 마스터롤 둘다 가능 (마스터롤은 등록하고싶은 companyName 을 요청)")
//    @PostMapping("")
//    fun addCompanyPolicy(
//        @RequestBody
//        companyPolicyDto: @Valid CompanyPolicyDto?,
//        @AuthenticationPrincipal
//        account: Account,
//        @ApiParam("companyName : 마스터롤이 정책등록 하고 싶은 회사이름 ")
//        @RequestParam(required = false)
//        companyName: String?
//    ): ResponseEntity<ResponseDto> {
//        val role: String = account.getRole()
//        val userId: String = account.getId()
//
//        val result: Long = companyPolicyService.addCompanyPolicy(companyPolicyDto, role, userId, companyName)
//
//        return ResponseEntity<ResponseDto>(ResponseDto(result, true, "정책 들록에 성공 하였습니다."), HttpStatus.OK)
//    }
//
//    @ApiOperation("정책 조회 = 회사이름으로 UserAccount, Account 둘다 조회 가능, 마스터롤은 전체조회")
//    @GetMapping("")
//    fun getCompanyPolicy(
//        @AuthenticationPrincipal
//        account: Account?,
//        @AuthenticationPrincipal
//        userAccount: UserAccount?
//    ): ResponseEntity<List<CompanyPolicyResDto>> {
//        // 어카운트가 null 이면 UserAccount 에서 찾는다
//        if (account == null) {
//            val userId: String = userAccount.getId()
//            val role: String = userAccount.getRole()
//            val companyName: String = userAccount.getCompanyName()
//
//            val result: List<CompanyPolicyResDto> = companyPolicyService.getCompanyPolicy(role, userId, companyName)
//
//            return ResponseEntity<List<CompanyPolicyResDto>>(result, HttpStatus.OK)
//        }
//        // 유저 어카운트가 null 이면 Account 에서 찾는다
//        if (userAccount == null) {
//            val role: String = account.getRole()
//            val userId: String = account.getId()
//            val companyName: String = account.getCompanyName()
//
//            val result: List<CompanyPolicyResDto> = companyPolicyService.getCompanyPolicy(role, userId, companyName)
//
//            return ResponseEntity<List<CompanyPolicyResDto>>(result, HttpStatus.OK)
//        }
//
//        throw RuntimeException("로그인 정보가 없습니다.")
//    }
//
//    @ApiOperation("정책 수정 = 컴퍼니롤은 자신의 회사 정책만 수정 가능, 마스터는 다 가능")
//    @PutMapping("/{companyPolicyIdx}")
//    fun updateCompanyPolicy(
//        @RequestBody
//        companyPolicyDto: @Valid CompanyPolicyDto?,
//        @PathVariable
//        companyPolicyIdx: Long?,
//        @AuthenticationPrincipal
//        account: Account
//    ): ResponseEntity<ResponseDto> {
//        val role: String = account.getRole()
//        val userId: String = account.getId()
//
//        val result: Long = companyPolicyService.updateCompanyPolicy(companyPolicyIdx, companyPolicyDto, role, userId)
//
//        return ResponseEntity<ResponseDto>(ResponseDto(result, true, "정책 수정에 성공 하였습니다."), HttpStatus.OK)
//    }
//
//    @ApiOperation("정책 삭제 = 컴퍼니롤은 자신의 회사 정책만 삭제 가능, 마스터는 다 가능")
//    @DeleteMapping("/{companyPolicyIdx}")
//    fun deleteCompanyPolicy(
//        @PathVariable
//        companyPolicyIdx: Long?,
//        @AuthenticationPrincipal
//        account: Account
//    ): ResponseEntity<ResponseDto> {
//        val role: String = account.getRole()
//        val userId: String = account.getId()
//
//        val result: Boolean = companyPolicyService.deleteCompanyPolicy(companyPolicyIdx, role, userId)
//
//        return ResponseEntity<ResponseDto>(ResponseDto(result, "정책이 삭제 되었습니다."), HttpStatus.OK)
//    }
//}

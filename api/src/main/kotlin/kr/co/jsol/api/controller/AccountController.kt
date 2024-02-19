//package kr.co.jsol.api.controller
//
//import io.swagger.v3.oas.annotations.tags.Tag
//import org.springframework.http.HttpStatus
//import org.springframework.security.core.annotation.AuthenticationPrincipal
//import org.springframework.web.bind.annotation.RequestBody
//import org.springframework.web.bind.annotation.RequestMapping
//import org.springframework.web.bind.annotation.RestController
//import javax.validation.Valid
//
//@RestController
//@RequestMapping("/api/v1/account")
//@Tag(
//    name = "Account",
//    description = "사용자 관련 API",
//)
//class AccountController(
//    private val accountService: AccountService,
//) {
//
//    @ApiOperation("회원 가입 = role 값 MASTER, COMPANY 만 사용")
//    @PostMapping("")
//    fun signUp(
//        @RequestBody
//        accountDto: @Valid AccountDto?,
//    ): ResponseEntity<ResponseDto> {
//        val result: Long = accountService.signUp(accountDto)
//
//        return ResponseEntity<ResponseDto>(ResponseDto(result, true, "회원가입이 완료 되었습니다."), HttpStatus.OK)
//    }
//
//    @ApiOperation("회사이름 중복 체크 = 컴퍼니롤로 생성된 계정의 모든 회사이름에서 중복체크")
//    @GetMapping("/company/{companyName}")
//    fun checkCompany(
//        @PathVariable
//        companyName: String?,
//    ): ResponseEntity<ResponseDto> {
//        val result: Boolean = accountService.isCompany(companyName)
//
//        if (result) return ResponseEntity<ResponseDto>(ResponseDto(false, "회사 이름이 중복 됩니다."), HttpStatus.CONFLICT)
//
//        return ResponseEntity<ResponseDto>(ResponseDto(true, "사용 가능한 회사 이름 입니다."), HttpStatus.OK)
//    }
//
//    @ApiOperation("로그인요청 Jwt 토큰발급")
//    @PostMapping("/login")
//    fun login(
//        @RequestBody
//        accountReqDto: @Valid AccountReqDto?,
//    ): ResponseEntity<TokenDto> {
//        val tokenDto: TokenDto = accountService.login(accountReqDto)
//
//        return ResponseEntity<TokenDto>(tokenDto, HttpStatus.OK)
//    }
//
//    @ApiOperation("어카운트 수정 = 컴퍼니롤은 자기 자신만, 마스터롤은 모든 어카운트 수정 가능(UserAccount 는 UserAccountController 에서 수정)")
//    @PutMapping("")
//    fun update(
//        @RequestBody
//        accountUpdateDto: @Valid AccountUpdateDto?,
//        @AuthenticationPrincipal
//        account: Account,
//    ): ResponseEntity<ResponseDto> {
//        val role: String = account.getRole()
//        val userId: String = account.getId()
//
//        val result: Long = accountService.update(accountUpdateDto, userId, role)
//
//        return ResponseEntity<ResponseDto>(ResponseDto(result, true, "회원정보 수정이 완료 되었습니다."), HttpStatus.OK)
//    }
//
//    @ApiOperation("어카운트 삭제 = 컴퍼니롤은 자기자신만, 마스터는 다른 아이디 삭제 가능(UserAccount 는 UserAccountController 에서 삭제)")
//    @DeleteMapping("/{targetId}")
//    fun delete(
//        @PathVariable
//        targetId: String?,
//        @AuthenticationPrincipal
//        account: Account,
//    ): ResponseEntity<ResponseDto> {
//        val role: String = account.getRole()
//        val userId: String = account.getId()
//
//        val result: Boolean = accountService.delete(targetId, role, userId)
//
//        return ResponseEntity<ResponseDto>(ResponseDto(result, "삭제가 완료 되었습니다."), HttpStatus.OK)
//    }
//
//    @ApiOperation("어카운트 중복확인")
//    @GetMapping("/check/{id}")
//    fun checkId(
//        @PathVariable
//        id: String?,
//    ): ResponseEntity<ResponseDto> {
//        val result: Boolean = accountService.checkId(id)
//
//        return ResponseEntity<ResponseDto>(ResponseDto(result, "사용가능한 아이디 입니다."), HttpStatus.OK)
//    }
//
//    @ApiOperation("어카운트 전체조회 = 컴퍼니롤은 같은 컴퍼니만 조회 마스터는 전체조회")
//    @GetMapping("/user")
//    fun getAllAccount(
//        @AuthenticationPrincipal
//        account: Account,
//    ): ResponseEntity<List<AccountResDto>> {
//        val role: String = account.getRole()
//        val companyName: String = account.getCompanyName()
//
//        val accountList: List<AccountResDto> = accountService.getAllAccount(role, companyName)
//
//        return ResponseEntity<List<AccountResDto>>(accountList, HttpStatus.OK)
//    }
//
//    @ApiOperation("어카운트 자기자신 조회")
//    @GetMapping("")
//    fun getAccount(
//        @AuthenticationPrincipal
//        account: Account,
//    ): ResponseEntity<AccountResDto> {
//        val userId: String = account.getId()
//
//        val result: AccountResDto = accountService.getAccount(userId)
//
//        return ResponseEntity<AccountResDto>(result, HttpStatus.OK)
//    }
//}

package kr.co.jsol.api.controller

import io.swagger.annotations.Api
import org.springframework.http.HttpStatus
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.RequestBody
import javax.validation.Valid

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/useraccount")
@Api(description = "유저롤 사용자 관련 API")
class UserAccountController {
    private val userAccountService: UserAccountService? = null

    @ApiOperation("회원가입 role = NONE")
    @PostMapping("")
    fun signUp(
        @RequestBody
        userAccountDto: @Valid UserAccountDto?
    ): ResponseEntity<ResponseDto> {
        val result: Long = userAccountService.signUp(userAccountDto)

        return ResponseEntity<ResponseDto>(ResponseDto(result, true, "회원가입이 완료 됬었습니다."), HttpStatus.OK)
    }

    @ApiOperation("회사리스트 조회 = 컴퍼니롤 회원가입시 사용된 모든 회사이름 리스트 반환")
    @GetMapping("/company")
    fun showCompanyList(): ResponseEntity<List<CompanyResDto>> {
        val result: List<CompanyResDto> = userAccountService.showCompanyList()

        return ResponseEntity<List<CompanyResDto>>(result, HttpStatus.OK)
    }

    @ApiOperation("로그인요청 Jwt 토큰발급")
    @PostMapping("/login")
    fun login(
        @RequestBody
        userAccountDto: UserAccountDto?
    ): ResponseEntity<TokenDto> {
        val result: TokenDto = userAccountService.login(userAccountDto)

        return ResponseEntity<TokenDto>(result, HttpStatus.OK)
    }

    @ApiOperation("유저 자기 자신 조회")
    @GetMapping("")
    fun getUserAccount(
        @AuthenticationPrincipal
        userAccount: UserAccount
    ): ResponseEntity<UserAccountResDto> {
        val id: String = userAccount.getId()

        val result: UserAccountResDto = userAccountService.getUserAccount(id)

        return ResponseEntity<UserAccountResDto>(result, HttpStatus.OK)
    }

    @ApiOperation("유저어카운트 수정 = 유저롤 자기자신 수정")
    @PutMapping("")
    fun update(
        @RequestBody
        userAccountUpdateDto: @Valid UserAccountUpdateDto?,
        @AuthenticationPrincipal
        userAccount: UserAccount
    ): ResponseEntity<ResponseDto> {
        val id: String = userAccount.getId()

        val result: Long = userAccountService.update(userAccountUpdateDto, id)

        return ResponseEntity<ResponseDto>(ResponseDto(result, true, "회원정보 수정을 완료 했습니다."), HttpStatus.OK)
    }

    @ApiOperation("유저어카운트 수정 = 컴퍼니 롤이 user 정보를 수정할때 사용 role 줄때 포함")
    @PutMapping("/role/{userAccountId}")
    fun updateRole(
        @PathVariable
        userAccountId: String?,
        @RequestBody
        userAccountReqDto: UserAccountReqDto,
        @AuthenticationPrincipal
        account: Account
    ): ResponseEntity<ResponseDto> {
        if (userAccountReqDto.getRole()
                .equals("company")
        ) throw AccessDeniedException("유저를 컴퍼니로 변경 할 수 없습니다.")

        if (userAccountReqDto.getRole()
                .equals("master")
        ) throw AccessDeniedException("유저를 마스터로 변경 할 수 없습니다.")

        val accountId: String = account.getId()

        val result: Long = userAccountService.updateRole(userAccountId, accountId, userAccountReqDto)

        return ResponseEntity<ResponseDto>(ResponseDto(result, true, "유저 정보를 수정완료 하였습니다."), HttpStatus.OK)
    }

    @ApiOperation("유저어카운트 삭제 = 유저롤 자기 자신 삭제")
    @DeleteMapping("")
    fun delete(
        @AuthenticationPrincipal
        userAccount: UserAccount
    ): ResponseEntity<ResponseDto> {
        val userId: String = userAccount.getId()

        val result: Boolean = userAccountService.delete(userId)

        return ResponseEntity<ResponseDto>(ResponseDto(result, "유저 삭제가 완료 되었습니다."), HttpStatus.OK)
    }

    @ApiOperation("유저어카운트 삭제 = 컴퍼니롤,마스터롤 이 유저를 삭제")
    @DeleteMapping("/role")
    fun deleteUserAccount(
        @AuthenticationPrincipal
        account: Account,
        @RequestParam
        targetId: String?
    ): ResponseEntity<ResponseDto> {
        val userId: String = account.getId()

        val result: Boolean = userAccountService.deleteUserAccount(targetId, userId)

        return ResponseEntity<ResponseDto>(ResponseDto(result, "유저 삭제가 완료 되었습니다."), HttpStatus.OK)
    }

    @ApiOperation("유저어카운트 아이디 중복확인")
    @GetMapping("/check/{id}")
    fun checkId(
        @PathVariable
        id: String?
    ): ResponseEntity<ResponseDto> {
        val result: Boolean = userAccountService.checkId(id)

        if (result) return ResponseEntity<ResponseDto>(ResponseDto(result, "사용가능한 아이디입니다.."), HttpStatus.OK)

        throw RuntimeException("중복된 아이디 입니다. 다른 아이디를 선택해 주세요.")
    }

    @ApiOperation("유저어카운트 회사단위 전체조회 컴퍼니롤, 마스터롤 만 사용")
    @GetMapping("/role/user")
    fun getAllUserAccount(
        @AuthenticationPrincipal
        account: Account,
        @ApiParam("companyName : 마스터롤이 회사이름으로 유저 어카운트 검색시 요청")
        @RequestParam(required = false)
        companyName: String?
    ): ResponseEntity<List<UserAccountResDto>> {
        val accountId: String = account.getId()

        val result: List<UserAccountResDto> = userAccountService.getAllUserAccount(accountId, companyName)

        return ResponseEntity<List<UserAccountResDto>>(result, HttpStatus.OK)
    }
}

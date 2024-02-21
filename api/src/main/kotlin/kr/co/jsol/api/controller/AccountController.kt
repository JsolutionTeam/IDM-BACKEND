package kr.co.jsol.api.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import kr.co.jsol.common.jwt.JwtService
import kr.co.jsol.domain.userdetails.UserDetailsImpl
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/account")
class AccountController(
    private val jwtService: JwtService,
//    private val accountService: AccountService,
) {

    @Operation(summary = "JWT Creation TEST")
    @ApiResponses(ApiResponse(responseCode = "201", description = "건물 등록 성공"))
//    @PreAuthorize("hasAnyAuthority(\"USER\")")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    fun cr(): String {
        return jwtService.createToken()
    }

    @Operation(summary = "JWT Validation TEST")
    @ApiResponses(ApiResponse(responseCode = "201", description = "건물 등록 성공"))
//    @PreAuthorize("hasAnyAuthority(\"USER\")")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    fun createBuilding(
        @AuthenticationPrincipal
        userDetails: UserDetailsImpl?,
    ) {
        val log = LoggerFactory.getLogger(this.javaClass)
        log.info("userDetails : $userDetails")
    }
}

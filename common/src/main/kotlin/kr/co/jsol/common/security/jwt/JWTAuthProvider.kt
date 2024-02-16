package kr.co.jsol.common.security.jwt

import lombok.RequiredArgsConstructor

@Component
@RequiredArgsConstructor
@Slf4j
class JWTAuthProvider : AuthenticationProvider {
    private val jwtDecoder: JwtDecoder? = null
    private val accountRepository: AccountRepository? = null
    private val userAccountRepository: UserAccountRepository? = null

    // JwtAuthFilter 에서 넘어온 토큰의 ID 값으로 account 찾아서 userDetails 생성
    @Throws(AuthenticationException::class)
    fun authenticate(authentication: Authentication): Authentication {
        val token = authentication.getPrincipal() as String
        val id = jwtDecoder!!.decodeId(token)
        val role = jwtDecoder.decoderole(token)
        val useraccount: UserAccount = userAccountRepository.findByIdAndRole(id, role)

        if (useraccount == null) {
            val account: Account = accountRepository.findByIdAndRole(id, role)

            return UsernamePasswordAuthenticationToken(account, null, account.getAuthorities())
        }

        //3번째 매개변수값으로 인가처리 가능
        return UsernamePasswordAuthenticationToken(useraccount, null, useraccount.getAuthorities())
    }

    fun supports(authentication: Class<*>?): Boolean {
        return JwtPreProcessingToken::class.java.isAssignableFrom(authentication)
    }
}

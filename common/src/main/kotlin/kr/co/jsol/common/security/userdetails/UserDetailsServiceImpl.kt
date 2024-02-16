package kr.co.jsol.common.security.userdetails

import lombok.RequiredArgsConstructor

@RequiredArgsConstructor
@Service
class UserDetailsServiceImpl : UserDetailsService {
    private val accountRepository: AccountRepository? = null

    private val userAccountRepository: UserAccountRepository? = null

    @Throws(UsernameNotFoundException::class)
    fun loadUserByUsername(loginId: String?): Account {
        return accountRepository.findById(loginId)
    }

    @Throws(UsernameNotFoundException::class)
    fun loadUserByUsername2(loginId: String?): UserAccount {
        return userAccountRepository.findById(loginId)
    }
}

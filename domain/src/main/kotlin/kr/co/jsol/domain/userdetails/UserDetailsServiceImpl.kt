package kr.co.jsol.domain.userdetails

import kr.co.jsol.domain.account.infrastructure.query.AccountQueryRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(
    private val query: AccountQueryRepository,
) : UserDetailsService {

    override fun loadUserByUsername(id: String): UserDetails {
        val account = query.getById(id)
        return UserDetailsImpl(account)
    }
}

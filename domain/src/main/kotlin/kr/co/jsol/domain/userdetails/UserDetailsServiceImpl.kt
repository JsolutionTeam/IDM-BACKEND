package kr.co.jsol.domain.userdetails

import kr.co.jsol.domain.account.infrastructure.query.AccountQueryRepository
import org.slf4j.LoggerFactory
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(
    private val query: AccountQueryRepository,
) : UserDetailsService {

    private final val log = LoggerFactory.getLogger(this::class.java)

    override fun loadUserByUsername(id: String): UserDetails {
        log.info("jwt id : $id")
        val account = query.getById(id)
        return UserDetailsImpl(account)
    }
}

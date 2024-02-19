package kr.co.jsol.domain.userdetails

import kr.co.jsol.domain.entity.account.infrastructure.query.AccountQueryRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(
    private val query: AccountQueryRepository,
) : UserDetailsService {

    override fun loadUserByUsername(id: String): UserDetails {
        val user = query.getById(id)

//        // TODO: 관리자 권한 제대로 된 방식으로 체크하도록 수정
//        if (user.role == UserAuthority.ADMIN) {
//            return UserDetailsImpl(user, false)
//        }

        return UserDetailsImpl(user)
    }
}

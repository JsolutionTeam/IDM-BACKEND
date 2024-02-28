package kr.co.jsol.common.jwt

import kr.co.jsol.common.domain.AccountAuthority
import org.slf4j.LoggerFactory
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class PayloadUserDetailsImpl(
    val payload: Payload,
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        log.info("payload.role : ${payload.role}")
        return mutableListOf<GrantedAuthority>(AccountAuthority.of(payload.role))
    }

    override fun isEnabled() = true

    override fun getUsername(): String = payload.id

    override fun isCredentialsNonExpired() = true

    override fun getPassword(): String = ""

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun toString(): String {
        return "PayloadUserDetailsImpl(payload=$payload)"
    }

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }
}

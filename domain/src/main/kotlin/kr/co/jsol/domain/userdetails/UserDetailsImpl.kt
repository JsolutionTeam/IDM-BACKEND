package kr.co.jsol.domain.userdetails

import kr.co.jsol.common.domain.AccountAuthority
import kr.co.jsol.domain.account.entity.Account
import org.slf4j.LoggerFactory
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class UserDetailsImpl(
    private val account: Account,
) : UserDetails {

    val id = account.id
    val name = account.name
    val role = account.role
    val shop = account.shop

    fun isNotAdmin() = isAdmin().not()
    fun isAdmin() = role == AccountAuthority.ADMIN

    fun isNotCompany() = isCompany().not()
    fun isCompany() = role == AccountAuthority.COMPANY

    fun isNotUser() = isUser().not()
    fun isUser() = role == AccountAuthority.USER

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        log.info("role : $role")
        return mutableListOf<GrantedAuthority>(role)
    }

    override fun isEnabled() = true

    override fun getUsername(): String = account.id

    override fun isCredentialsNonExpired() = true

    override fun getPassword(): String = ""

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }
}

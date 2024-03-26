package kr.co.jsol.domain.userdetails

import kr.co.jsol.domain.account.entity.Account
import org.slf4j.LoggerFactory
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class UserDetailsImpl(
    private val account: Account,
) : UserDetails {

    val id = account.id
    val name = account.name
    val role = account.role
    val isManager = account.isManager || isMaster()
    val shop = account.shop

    fun isNotMaster() = isMaster().not()

    fun isMaster(): Boolean {
        val upperRole = role.uppercase().removePrefix("ROLE_")
        return upperRole.contains("ADMIN") ||
                upperRole.contains("JSOL") ||
                upperRole.contains("ROOT")
    }

    fun isNotUser() = isUser().not()
    fun isUser(): Boolean {
        val upperRole = role.uppercase().removePrefix("ROLE_")
        return upperRole.contains("COMPANY") ||
                upperRole.contains("AGENCY") ||
                upperRole.contains("STORE") ||
                upperRole.contains("PARENT") ||
                upperRole.contains("BELONG")
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {

        if (isMaster()) {
            log.info("role : MASTER")
            return mutableListOf<GrantedAuthority>(SimpleGrantedAuthority("MASTER"))
        }

        if (isUser()) {
            log.info("role : User")
            return mutableListOf<GrantedAuthority>(SimpleGrantedAuthority("User"))
        }

        log.info("role : $role")
        return mutableListOf<GrantedAuthority>(SimpleGrantedAuthority(role))
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

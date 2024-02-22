package kr.co.jsol.domain.userdetails

import kr.co.jsol.domain.account.entity.Account
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsImpl(val entity: Account) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf<GrantedAuthority>(GrantedAuthority { "" })
    }

    override fun getPassword(): String = ""

    override fun getUsername(): String = this.entity.id.toString()

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true

    val id get() = this.entity.id

    override fun toString(): String {
        return "UserDetailsImpl(entity=$entity, id=$id username=${this.entity.id})"
    }
}

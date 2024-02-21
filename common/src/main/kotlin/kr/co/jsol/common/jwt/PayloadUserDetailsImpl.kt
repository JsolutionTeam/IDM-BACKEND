package kr.co.jsol.common.jwt

import org.springframework.security.core.userdetails.UserDetails

class PayloadUserDetailsImpl(
    val payload: Payload,
    val userDetails: UserDetails,
) : UserDetails {
    override fun getAuthorities() = userDetails.authorities

    override fun getPassword() = userDetails.password

    override fun getUsername() = userDetails.username

    override fun isAccountNonExpired() = userDetails.isAccountNonExpired

    override fun isAccountNonLocked() = userDetails.isAccountNonLocked

    override fun isCredentialsNonExpired() = userDetails.isCredentialsNonExpired

    override fun isEnabled() = userDetails.isEnabled
}

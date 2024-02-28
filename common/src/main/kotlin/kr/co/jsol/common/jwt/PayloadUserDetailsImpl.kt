package kr.co.jsol.common.jwt

import org.springframework.security.core.userdetails.UserDetails

class PayloadUserDetailsImpl(
    val payload: Payload,
    val userDetails: UserDetails,
) {
    override fun toString(): String {
        return "PayloadUserDetailsImpl(payload=$payload, userDetails=$userDetails)"
    }
}

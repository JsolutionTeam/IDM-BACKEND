package kr.co.jsol.common.jwt

import io.jsonwebtoken.Claims

data class Payload(
    val id: String,
    val name: String,
    val role: String,
    val shopId: Long,
) {

    companion object {
        fun of(claims: Claims): Payload {
            return Payload(
                id = claims["id", String::class.java],
                name = claims["name", String::class.java],
                role = claims["role", String::class.java],
                shopId = claims["shopId", Long::class.java],
            )
        }
    }
}

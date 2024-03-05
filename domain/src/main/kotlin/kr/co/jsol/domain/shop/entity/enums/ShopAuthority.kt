package kr.co.jsol.domain.shop.entity.enums

import io.swagger.v3.oas.annotations.media.Schema

enum class ShopAuthority(
    @field:Schema(description = "설명")
    val description: String,
) {

    B("판매점"),
    P("대리점"),
    ;

    class Response(role: ShopAuthority) {
        val name = role.name
        val description = role.description
    }
}

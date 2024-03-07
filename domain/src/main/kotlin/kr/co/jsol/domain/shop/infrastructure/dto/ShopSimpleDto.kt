package kr.co.jsol.domain.shop.infrastructure.dto

import com.querydsl.core.annotations.QueryProjection
import kr.co.jsol.domain.shop.entity.Shop

data class ShopSimpleDto @QueryProjection constructor(
    val id: Long,
    val name: String,
) {

    constructor(shop: Shop) : this(
        id = shop.id,
        name = shop.name,
    )
}

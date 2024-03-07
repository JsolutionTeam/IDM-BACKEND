package kr.co.jsol.domain.shop.infrastructure.dto

import com.querydsl.core.annotations.QueryProjection
import kr.co.jsol.domain.shop.entity.Shop

data class ShopDto @QueryProjection constructor(
    val id: Long,
    val name: String,
    val phone: String,
    val managerId: String,
    val managerName: String,
    val managerPhone: String,
) {

    constructor(shop: Shop) : this(
        id = shop.id,
        name = shop.name,
        phone = shop.phone,
        managerId = shop.managerId,
        managerName = shop.managerName,
        managerPhone = shop.managerPhone,
    )
}

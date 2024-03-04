package kr.co.jsol.domain.shop.infrastructure.dto

import kr.co.jsol.domain.shop.entity.Shop
import kr.co.jsol.domain.shop.entity.enums.ShopStatus

data class ShopDto(
    val id: Long,
    val name: String,
    val role: String,
    val status: ShopStatus,
    val phone: String,
    val managerId: String,
    val managerName: String,
    val managerPhone: String,
) {

    constructor(shop: Shop) : this(
        id = shop.id,
        name = shop.name,
        role = shop.role,
        status = shop.status,
        phone = shop.phone,
        managerId = shop.managerId,
        managerName = shop.managerName,
        managerPhone = shop.managerPhone,
    )
}

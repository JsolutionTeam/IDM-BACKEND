package kr.co.jsol.domain.shop.infrastructure.dto

import com.querydsl.core.annotations.QueryProjection
import io.swagger.v3.oas.annotations.media.Schema
import kr.co.jsol.domain.shop.entity.Shop

@Schema(description = "업체 정보")
data class ShopDto @QueryProjection constructor(
    @field:Schema(description = "업체 아이디")
    val id: Long,

    @field:Schema(description = "이름")
    val name: String,

    @field:Schema(description = "연락처")
    val phone: String,

    @field:Schema(description = "권한")
    val role: String,

    @field:Schema(description = "관리자 아이디")
    val managerId: String,

    @field:Schema(description = "관리자 이름")
    val managerName: String,

    @field:Schema(description = "관리자 연락처")
    val managerPhone: String,
) {

    constructor(shop: Shop) : this(
        id = shop.id,
        name = shop.name,
        phone = shop.phone,
        role = shop.role,
        managerId = shop.managerId,
        managerName = shop.managerName,
        managerPhone = shop.managerPhone,
    )
}

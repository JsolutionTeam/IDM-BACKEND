package kr.co.jsol.domain.shopdevice.entity.enums

enum class ShopDeviceStatus(
    val kor: String,
    val type: String,
) {
    IN("입고", "in"),
    OUT("출고", "out"),
    RETURN("반품", "in"),
    BROKEN("파손", "out"),
    REFUND("환불", "in"),
    REPAIR("수리", "tempOut"),
    RENTAL("대여", "tempOut"),
    ;

    class Response(shopDeviceStatus: ShopDeviceStatus) {
        val name = shopDeviceStatus.name
        val kor = shopDeviceStatus.kor
        val type = shopDeviceStatus.type
    }
}

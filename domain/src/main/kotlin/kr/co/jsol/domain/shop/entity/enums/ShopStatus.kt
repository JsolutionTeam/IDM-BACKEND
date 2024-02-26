package kr.co.jsol.domain.shop.entity.enums

enum class ShopStatus(
    val description: String,
) {
    DONE("서비스를 정상적으로 사용 가능한 상태입니다."),
    REQUEST("서비스를 신청한 상태입니다. 서비스 사용이 불가합니다."),
    REJECT("서비스 신청이 거절된 상태입니다. 서비스 사용이 불가합니다."),
    ;

    class Response(discountType: ShopStatus) {
        val name = discountType.name
        val description = discountType.description
    }
}

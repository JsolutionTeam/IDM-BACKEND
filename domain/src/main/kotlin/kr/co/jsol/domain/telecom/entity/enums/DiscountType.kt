package kr.co.jsol.domain.telecom.entity.enums

enum class DiscountType(
    val description: String,
) {
    DEVICE("단말기기 지원금, 단말기 지원금을 제공받아 단말기 가격을 할인받을 수 있습니다."),
    PHONE_PLAN("선택 약정, 요금제 비용의 25%만큼 선택 약정 기간동안 할인됩니다.")
    ;

    class Response(discountType: DiscountType) {
        val name = discountType.name
        val description = discountType.description
    }
}

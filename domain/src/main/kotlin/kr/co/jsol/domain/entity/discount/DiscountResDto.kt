package kr.co.jsol.domain.entity.discount

import lombok.Getter

@Getter
@NoArgsConstructor
class DiscountResDto(discount: Discount) {
    private val idx: Long
    private val modelNumber: Long
    private val discountPrice: Long

    init {
        this.idx = discount.getIdx()
        this.modelNumber = discount.getModelNumber()
        this.discountPrice = discount.getDiscountPrice()
    }
}

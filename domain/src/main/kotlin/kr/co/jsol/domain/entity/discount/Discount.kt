package kr.co.jsol.domain.entity.discount

import lombok.AccessLevel
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class Discount(discountDto: DiscountDto) : TimeStamp() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private var idx: Long? = null

    @Column(nullable = false)
    private var modelNumber: Long

    private var discountPrice: Long

    init {
        this.modelNumber = discountDto.getModelNumber()
        this.discountPrice = discountDto.getDiscountPrice()
    }

    fun update(discountDto: DiscountDto) {
        this.modelNumber = discountDto.getModelNumber()
        this.discountPrice = discountDto.getDiscountPrice()
    }
}

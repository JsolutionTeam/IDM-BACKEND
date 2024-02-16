package kr.co.jsol.domain.entity.discount

import lombok.Getter
import javax.validation.constraints.NotNull

@Getter
@NoArgsConstructor
class DiscountDto {
    private val modelNumber: @NotNull Long? = null

    private val discountPrice: Long? = null
}

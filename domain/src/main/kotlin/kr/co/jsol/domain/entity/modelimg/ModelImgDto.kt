package kr.co.jsol.domain.entity.modelimg

import lombok.Getter
@Getter
@NoArgsConstructor
class ModelImgDto {
    private val modelNumber: @javax.validation.constraints.NotNull Long? = null
    private val modelColor: @javax.validation.constraints.NotNull String? = null
}

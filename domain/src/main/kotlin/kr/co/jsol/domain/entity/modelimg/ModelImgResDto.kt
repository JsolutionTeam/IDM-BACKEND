package kr.co.jsol.domain.entity.modelimg

import lombok.Getter

@Getter
@NoArgsConstructor
class ModelImgResDto(modelImg: ModelImg) {
    private val idx: Long

    private val modelImg: String

    private val modelNumber: Long

    private val modelColor: String

    init {
        this.idx = modelImg.getIdx()
        this.modelImg = modelImg.getModelImg()
        this.modelNumber = modelImg.getModelNumber()
        this.modelColor = modelImg.getModelColor()
    }
}

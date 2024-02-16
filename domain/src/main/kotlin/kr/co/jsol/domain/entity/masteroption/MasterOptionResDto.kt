package kr.co.jsol.domain.entity.masteroption

import lombok.Getter

@Getter
@NoArgsConstructor
class MasterOptionResDto(masterOption: MasterOption) {
    private val idx: Long

    private val name: String

    private val isShow: Boolean

    init {
        this.idx = masterOption.getIdx()
        this.name = masterOption.getName()
        this.isShow = masterOption.getIsShow()
    }
}

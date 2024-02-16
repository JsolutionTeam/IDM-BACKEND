package kr.co.jsol.domain.entity.telecom_data

import lombok.Getter

@Getter
class TelecomDataResDto(telecomData: TelecomData) {
    private val idx: Long

    private val telecomUrl: String

    private val modelIdx: Long

    private val telecomName: String

    init {
        this.idx = telecomData.getIdx()
        this.telecomUrl = telecomData.getTelecomUrl()
        this.modelIdx = telecomData.getModelIdx()
        this.telecomName = telecomData.getTelecomName()
    }
}

package kr.co.jsol.domain.telecomdevice.infrastructure.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.jsol.domain.telecomdevice.entity.TelecomDevice

@Schema(name = "통신팀 판매용 단말 정보 응답")
class TelecomDeviceDto(
    @field:Schema(description = "통신팀 판매용 단말 정보 아이디")
    val id: Long,

    @field:Schema(description = "단말 모델명")
    var modelName: String,

    @field:Schema(description = "단말 펫네임")
    var petName: String,

    @field:Schema(description = "출고가 설명")
    var price: String,

    @field:Schema(description = "회사 지원금(행사가) 설명")
    var companySubsidy: String,

    @field:Schema(description = "요금제 설명")
    var phonePlan: String,

    @field:Schema(description = "총 요금 설명")
    var totalPrice: String,

    @field:Schema(description = "통신사 바로가기 링크")
    var link: String,

    @field:Schema(description = "기타 1")
    var etc1: String,

    @field:Schema(description = "기타 2")
    var etc2: String,

    @field:Schema(description = "기타 3")
    var etc3: String,

    @field:Schema(description = "기타 4")
    var etc4: String,

    @field:Schema(description = "기타 5")
    var etc5: String,

    @field:Schema(description = "기타 6")
    var etc6: String,
) {

    constructor(telecomDevice: TelecomDevice) : this(
        id = telecomDevice.id,
        modelName = telecomDevice.modelName,
        petName = telecomDevice.petName,
        price = telecomDevice.price,
        companySubsidy = telecomDevice.companySubsidy,
        phonePlan = telecomDevice.phonePlan,
        totalPrice = telecomDevice.totalPrice,
        link = telecomDevice.link,
        etc1 = telecomDevice.etc1,
        etc2 = telecomDevice.etc2,
        etc3 = telecomDevice.etc3,
        etc4 = telecomDevice.etc4,
        etc5 = telecomDevice.etc5,
        etc6 = telecomDevice.etc6,
    )
}

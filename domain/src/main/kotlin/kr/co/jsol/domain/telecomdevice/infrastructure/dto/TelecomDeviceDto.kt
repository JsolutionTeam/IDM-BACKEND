package kr.co.jsol.domain.telecomdevice.infrastructure.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.jsol.domain.telecomdevice.entity.TelecomDevice
import org.springframework.stereotype.Component

@Component // @Value annotation를 사용하기 위함
@Schema(name = "통신팀 판매용 단말 정보 응답")
class TelecomDeviceDto(
    @field:Schema(description = "통신팀 판매용 단말 정보 아이디")
    val id: Long = 0,

    @field:Schema(description = "단말 모델명")
    var modelName: String = "",

    @field:Schema(description = "단말 펫네임")
    var petName: String = "",

    @field:Schema(description = "대표 이미지 URL")
    var imageUrl: String = "",

    @field:Schema(description = "출고가 설명")
    var price: String = "",

    @field:Schema(description = "회사 지원금(행사가) 설명")
    var companySubsidy: String = "",

    @field:Schema(description = "요금제 설명")
    var phonePlan: String = "",

    @field:Schema(description = "총 요금 설명")
    var totalPrice: String = "",

    @field:Schema(description = "통신사 신청 링크")
    var link: String = "",

    @field:Schema(
        description = "단말신청서 이동 여부, true = 단말신청서 폼으로 이동 false = 통신사 이동 링크로 바로 이동",
        implementation = Boolean::class
    )
    var isForm: Boolean = true,

    @field:Schema(description = "표시 순서")
    var displayOrder: Int = 0,

    @field:Schema(description = "표시 여부")
    var isDisplay: Boolean = false,

    @field:Schema(description = "기타 1")
    var etc1: String = "",

    @field:Schema(description = "기타 2")
    var etc2: String = "",

    @field:Schema(description = "기타 3")
    var etc3: String = "",

    @field:Schema(description = "기타 4")
    var etc4: String = "",

    @field:Schema(description = "기타 5")
    var etc5: String = "",

    @field:Schema(description = "기타 6")
    var etc6: String = "",
) {

    constructor(telecomDevice: TelecomDevice) : this(
        id = telecomDevice.id,
        modelName = telecomDevice.modelName,
        petName = telecomDevice.petName,
        imageUrl = telecomDevice.imageUrl,
        price = telecomDevice.price,
        companySubsidy = telecomDevice.companySubsidy,
        phonePlan = telecomDevice.phonePlan,
        totalPrice = telecomDevice.totalPrice,
        link = telecomDevice.link,
        isForm = telecomDevice.isForm,
        displayOrder = telecomDevice.displayOrder,
        isDisplay = telecomDevice.isDisplay,
        etc1 = telecomDevice.etc1,
        etc2 = telecomDevice.etc2,
        etc3 = telecomDevice.etc3,
        etc4 = telecomDevice.etc4,
        etc5 = telecomDevice.etc5,
        etc6 = telecomDevice.etc6,
    )
}

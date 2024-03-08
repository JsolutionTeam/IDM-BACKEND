package kr.co.jsol.domain.telecomdevice.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.jsol.domain.telecomdevice.entity.TelecomDevice
import javax.validation.constraints.NotNull

@Schema(name = "통신팀 판매용 단말 정보 등록 요청")
data class CreateTelecomDeviceDto(
    @field:NotNull(message = "단말 모델명은 필수입니다..")
    @field:Schema(description = "단말 모델명")
    var modelName: String,

    @field:NotNull(message = "단말 펫네임은 필수입니다.")
    @field:Schema(description = "단말 펫네임")
    var petName: String,

    @field:NotNull(message = "대표 이미지 URL은 필수입니다.")
    @field:Schema(description = "대표 이미지 URL")
    var imageUrl: String,

    @field:NotNull(message = "출고가 설명은 필수입니다.")
    @field:Schema(description = "출고가 설명")
    var price: String,

    @field:NotNull(message = "회사 지원금(행사가) 설명은 필수입니다.")
    @field:Schema(description = "회사 지원금(행사가) 설명")
    var companySubsidy: String,

    @field:NotNull(message = "요금제 설명은 필수입니다.")
    @field:Schema(description = "요금제 설명")
    var phonePlan: String,

    @field:NotNull(message = "총 요금 설명은 필수입니다.")
    @field:Schema(description = "총 요금 설명")
    var totalPrice: String,

    @field:NotNull(message = "통신사 바로가기 링크 설명은 필수입니다.")
    @field:Schema(description = "통신사 바로가기 링크")
    var link: String,

    @field:Schema(description = "기타 1")
    var etc1: String? = null,

    @field:Schema(description = "기타 2")
    var etc2: String? = null,

    @field:Schema(description = "기타 3")
    var etc3: String? = null,

    @field:Schema(description = "기타 4")
    var etc4: String? = null,

    @field:Schema(description = "기타 5")
    var etc5: String? = null,

    @field:Schema(description = "기타 6")
    var etc6: String? = null,
) {

    fun toEntity(): TelecomDevice {
        return TelecomDevice(
            modelName = modelName,
            petName = petName,
            imageUrl = imageUrl,
            price = price,
            companySubsidy = companySubsidy,
            phonePlan = phonePlan,
            totalPrice = totalPrice,
            link = link,
            etc1 = etc1 ?: "",
            etc2 = etc2 ?: "",
            etc3 = etc3 ?: "",
            etc4 = etc4 ?: "",
            etc5 = etc5 ?: "",
            etc6 = etc6 ?: "",
        )
    }
}

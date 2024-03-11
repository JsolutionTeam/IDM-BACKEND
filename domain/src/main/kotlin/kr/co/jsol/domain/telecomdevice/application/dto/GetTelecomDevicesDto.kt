package kr.co.jsol.domain.telecomdevice.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import org.springdoc.api.annotations.ParameterObject

@ParameterObject
@Schema(name = "통신팀 판매용 단말 정보 페이지 조회 요청")
data class GetTelecomDevicesDto(
    @field:Schema(description = "단말 모델명")
    var modelName: String? = null,

    @field:Schema(description = "단말 펫네임")
    var petName: String? = null,

    @field:Schema(description = "출고가 설명")
    var price: String? = null,

    @field:Schema(description = "회사 지원금(행사가) 설명")
    var companySubsidy: String? = null,

    @field:Schema(description = "요금제 설명")
    var phonePlan: String? = null,

    @field:Schema(description = "총 요금 설명")
    var totalPrice: String? = null,

    @field:Schema(description = "이동 링크")
    var link: String? = null,

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
)

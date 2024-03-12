package kr.co.jsol.domain.telecomdevice.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotNull

@Schema(name = "통신팀 판매용 단말 정보 단일 수정 요청")
data class UpdateTelecomDeviceDto(
    @field:NotNull(message = "통신팀 판매용 단말 정보 아이디는 필수입니다.")
    @field:Schema(description = "통신팀 판매용 단말 정보 아이디")
    val id: Long,

    @field:Schema(description = "단말 모델명")
    var modelName: String? = null,

    @field:Schema(description = "단말 펫네임")
    var petName: String? = null,

    @field:Schema(description = "출고가 설명")
    var price: String? = null,

    @field:Schema(description = "대표 이미지 URL")
    var imageUrl: String? = null,

    @field:Schema(description = "회사 지원금(행사가) 설명")
    var companySubsidy: String? = null,

    @field:Schema(description = "요금제 설명")
    var phonePlan: String? = null,

    @field:Schema(description = "총 요금 설명")
    var totalPrice: String? = null,

    @field:Schema(description = "통신사 신청 링크")
    var link: String? = null,

    @field:Schema(
        description = "단말신청서 이동 여부, true = 단말신청서 폼으로 이동 false = 통신사 이동 링크로 바로 이동",
        implementation = Boolean::class
    )
    var isForm: Boolean? = null,

    @field:Schema(description = "표시 순서")
    var displayOrder: Int? = null,

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

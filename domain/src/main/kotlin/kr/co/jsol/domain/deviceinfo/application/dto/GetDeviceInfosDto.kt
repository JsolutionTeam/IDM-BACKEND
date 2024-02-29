package kr.co.jsol.domain.deviceinfo.application.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "단말 단일 조회 요청")
data class GetDeviceInfosDto(
    @field:Schema(description = "바코드 모델 구분")
    val barcode: String? = null,

    @field:Schema(description = "바코드 색상 구분")
    val barcodeColor: String? = null,

    @field:Schema(description = "단말 펫네임 [device.petName]")
    val devicePetName: String? = null,

    @field:Schema(description = "단말 모델네임 [device.modelName]")
    val deviceModelName: String? = null,

    @field:Schema(description = "단말 시리즈 [device.series]")
    val deviceSeries: String? = null,

    @field:Schema(description = "단말 최소 가격 [device.price]")
    val devicePriceMin: Long? = null,

    @field:Schema(description = "단말 최대 가격 [device.price]")
    val devicePriceMax: Long? = null,

    @field:Schema(description = "단말 용량 [device.volume]")
    val deviceVolume: String? = null,

    @field:Schema(description = "제조사 아이디 [device.maker.id]")
    val makerId: Long? = null,

    @field:Schema(description = "색상 아이디 [color.id]")
    val colorId: Long? = null,

    @field:Schema(description = "색상명 [color.name]")
    val colorName: String? = null,
)

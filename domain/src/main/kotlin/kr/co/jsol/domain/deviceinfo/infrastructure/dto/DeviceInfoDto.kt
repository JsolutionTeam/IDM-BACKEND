package kr.co.jsol.domain.deviceinfo.infrastructure.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.jsol.domain.color.infrastructure.dto.ColorDto
import kr.co.jsol.domain.device.infrastructure.dto.DeviceDto
import kr.co.jsol.domain.deviceinfo.entity.DeviceInfo

@Schema(name = "단말 상세 응답")
class DeviceInfoDto(
    @field:Schema(description = "단말 상세 아이디")
    val id: Long,

    @field:Schema(description = "바코드 모델 구분")
    val barcode: String,

    @field:Schema(description = "바코드 색상 구분")
    val barcodeColor: String,

    @field:Schema(description = "모델 이미지 링크")
    val imageUrl: String,

    val device: DeviceDto,

    val color: ColorDto,
) {

    constructor(deviceInfo: DeviceInfo) : this(
        id = deviceInfo.id,
        barcode = deviceInfo.barcode,
        barcodeColor = deviceInfo.barcodeColor,
        imageUrl = deviceInfo.imageUrl,
        device = DeviceDto(deviceInfo.device),
        color = ColorDto(deviceInfo.color),
    )
}

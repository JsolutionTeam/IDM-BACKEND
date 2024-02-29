package kr.co.jsol.domain.deviceinfo.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.jsol.domain.color.entity.Color
import kr.co.jsol.domain.device.entity.Device
import kr.co.jsol.domain.deviceinfo.entity.DeviceInfo
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Schema(name = "단말 상세 등록 요청")
data class CreateDeviceInfoDto(
    @field:NotBlank(message = "바코드 모델 구분은 필수입니다.")
    @field:Size(max = 255, message = "바코드 모델 구분은 255자 이내로 입력해주세요.")
    @field:Schema(description = "바코드 모델 구분")
    val barcode: String,

    @field:NotBlank(message = "바코드 색상 구분은 필수입니다.")
    @field:Size(max = 255, message = "바코드 색상 구분은 255자 이내로 입력해주세요.")
    @field:Schema(description = "바코드 색상 구분")
    val barcodeColor: String,

    @field:NotNull(message = "단말 아이디는 필수입니다.")
    @field:Schema(description = "단말 아이디")
    val deviceId: Long,

    @field:NotNull(message = "상 아이디는 필수입니다.")
    @field:Schema(description = "색상 아이디")
    val colorId: Long,
) {

    fun toEntity(
        device: Device,
        color: Color,
    ): DeviceInfo {
        return DeviceInfo(
            barcode = barcode,
            barcodeColor = barcodeColor,
            device = device,
            color = color,
        )
    }
}

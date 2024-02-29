package kr.co.jsol.domain.deviceinfo.infrastructure.dto

import com.querydsl.core.annotations.QueryProjection
import io.swagger.v3.oas.annotations.media.Schema

data class DeviceInfoGroupByDeviceSeriesDto @QueryProjection constructor(
    val series: String,
    val devices: List<Device>,
    val colors: List<Color>,
) {

    inner class Device @QueryProjection constructor(
        @field:Schema(description = "용량", example = "256GB")
        val volume: String,
        @field:Schema(description = "가격", example = "1280000")
        val price: Long,
    )

    inner class Color @QueryProjection constructor(
        @field:Schema(description = "색상명", example = "화이트")
        val name: String,
        @field:Schema(description = "색상 코드 hex or rgb", example = "#FFFFFF")
        val displayValue: String,
    )
}

package kr.co.jsol.domain.device.infrastructure.dto

import com.querydsl.core.annotations.QueryProjection
import io.swagger.v3.oas.annotations.media.Schema
import kr.co.jsol.common.annotation.JDateTimeFormat
import kr.co.jsol.domain.device.entity.Device
import java.time.LocalDateTime

@Schema(name = "디바이스 Raw 응답")
data class DeviceRawDto @QueryProjection constructor(
    @field:Schema(description = "디바이스 아이디")
    val id: Long,

    @field:Schema(description = "펫네임")
    val petName: String,

    @field:Schema(description = "모델네임")
    val modelName: String,

    @field:Schema(description = "가격")
    val price: Long,

    @field:Schema(description = "용량")
    val volume: String,

    @field:Schema(description = "시리즈")
    val series: String,

    @field:JDateTimeFormat
    @field:Schema(description = "등록일시")
    val createdAt: LocalDateTime,

    @field:JDateTimeFormat
    @field:Schema(description = "수정일시")
    val updatedAt: LocalDateTime? = null,
) {

    constructor(device: Device) : this(
        id = device.id,
        petName = device.petName,
        modelName = device.modelName,
        price = device.price,
        volume = device.volume,
        series = device.series,
        createdAt = device.createdAt,
        updatedAt = device.updatedAt,
    )
}

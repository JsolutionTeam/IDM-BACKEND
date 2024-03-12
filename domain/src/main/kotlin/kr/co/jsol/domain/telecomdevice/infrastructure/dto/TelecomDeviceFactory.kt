package kr.co.jsol.domain.telecomdevice.infrastructure.dto

import kr.co.jsol.domain.telecomdevice.entity.TelecomDevice
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component // @Value annotation를 사용하기 위함
class TelecomDeviceFactory(
    @Value("\${custom.api-url.shop:https://api-shop.j-sol.co.kr}")
    private var shopApiUrl: String,
) {

    fun create(telecomDevice: TelecomDevice): TelecomDeviceDto {
//        println("shopApiUrl = ${shopApiUrl}")
        // imageUrl를 풀 경로로 만들기 위해 shopApiUrl을 붙여줌
        telecomDevice.imageUrl = "$shopApiUrl/${telecomDevice.imageUrl}"
        return TelecomDeviceDto(telecomDevice)
    }
}

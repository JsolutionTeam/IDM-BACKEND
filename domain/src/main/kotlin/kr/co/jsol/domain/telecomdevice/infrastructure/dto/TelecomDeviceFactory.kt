package kr.co.jsol.domain.telecomdevice.infrastructure.dto

import kr.co.jsol.domain.telecomdevice.entity.TelecomDevice
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component // @Value annotation를 사용하기 위함
class TelecomDeviceFactory(
    @Value("\${custom.api-url.shop.host:https://api-shop.j-sol.co.kr}")
    private var host: String,

    @Value("\${custom.api-url.shop.local-files:/api/v2/local-files}")
    private var localFiles: String,

    @Value("\${custom.api-url.shop.get-local-files:?filename=}")
    private var getLocalFiles: String,
) {

    fun create(telecomDevice: TelecomDevice): TelecomDeviceDto {
        // imageUrl를 풀 경로로 만들기 위해 shopApiUrl을 붙여줌
        var imageUrl = telecomDevice.imageUrl

        // http가 있으면 그대로 사용하지만 아니라면 full path 만들어줌
        if (imageUrl.contains("http").not()) {
            imageUrl = "$host$localFiles$getLocalFiles$imageUrl"
        }
        return TelecomDeviceDto(telecomDevice, imageUrl)
    }
}

package kr.co.jsol.api.controller

import io.swagger.v3.oas.annotations.tags.Tag
import kr.co.jsol.domain.kiosk.application.KioskService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/kiosks")
@Tag(name = "키오스크(단말 보관함)", description = "키오스크(단말 보관함) 관련 API")
class KioskController(
    private val service: KioskService,
) {

}


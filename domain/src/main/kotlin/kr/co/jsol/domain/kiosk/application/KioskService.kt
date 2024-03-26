package kr.co.jsol.domain.kiosk.application

import kr.co.jsol.domain.kiosk.infrastructure.query.KioskQueryRepository
import kr.co.jsol.domain.kiosk.infrastructure.repository.KioskRepository
import org.springframework.stereotype.Service

@Service
class KioskService(
    private val repository: KioskRepository,
    private val query: KioskQueryRepository,
) {
}

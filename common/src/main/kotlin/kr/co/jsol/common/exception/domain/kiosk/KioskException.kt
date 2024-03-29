package kr.co.jsol.common.exception.domain.kiosk

import kr.co.jsol.common.exception.CustomException
import org.springframework.http.HttpStatus

class KioskException {
    class NotFoundByIdException(
        message: String = "id로 $NAME 정보를 찾을 수 없습니다.",
        e: Throwable? = null,
    ) : CustomException("$CODE-0001", message, HttpStatus.NOT_FOUND, e)

    companion object {
        private const val NAME = "단말 보관함"
        private const val CODE = "KIOSK"
    }
}
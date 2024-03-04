package kr.co.jsol.common.exception.domain.phoneplan

import kr.co.jsol.common.exception.CustomException
import org.springframework.http.HttpStatus

private const val NAME = "요금제"
private const val CODE = "PHONE-PLAN"

sealed class PhonePlanException {
    class NotFoundByIdException(
        message: String = "id로 $NAME 정보를 찾을 수 없습니다.",
        e: Throwable? = null,
    ) : CustomException("$CODE-0001", message, HttpStatus.NOT_FOUND, e)
}

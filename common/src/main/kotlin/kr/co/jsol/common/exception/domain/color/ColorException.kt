package kr.co.jsol.common.exception.domain.color

import kr.co.jsol.common.exception.CustomException
import org.springframework.http.HttpStatus

sealed class ColorException {
    class NotFoundByIdException(
        message: String = "id로 $NAME 정보를 찾을 수 없습니다.",
        e: Throwable? = null,
    ) : CustomException("$CODE-0001", message, HttpStatus.NOT_FOUND, e)

    companion object {
        private const val NAME = "색상"
        private const val CODE = "COLOR"
    }
}

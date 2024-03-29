package kr.co.jsol.common.exception.domain.notice

import kr.co.jsol.common.exception.CustomException
import org.springframework.http.HttpStatus

sealed class NoticeException {
    class NotFoundByIdException(
        message: String = "id로 $NAME 정보를 찾을 수 없습니다.",
        e: Throwable? = null,
    ) : CustomException("$CODE-0001", message, HttpStatus.NOT_FOUND, e)

    companion object {
        private const val NAME = "공지사항"
        private const val CODE = "NOTICE"
    }
}
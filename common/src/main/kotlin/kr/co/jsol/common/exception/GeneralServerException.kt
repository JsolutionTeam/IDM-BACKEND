package kr.co.jsol.common.exception

import org.springframework.http.HttpStatus

sealed class GeneralServerException {
    class InternalServerException(
        message: String? = null,
        e: Throwable? = null,
    ) :
        CustomException("GNR-5000", message ?: "서버 에러 발생, 담당 개발자에게 연락해주세요.", HttpStatus.INTERNAL_SERVER_ERROR, e)

    class ServerNullPointerException(
        message: String? = null,
        e: Throwable? = null,
    ) :
        CustomException("GNR-5001", message ?: "서버 에러 발생, 담당 개발자에게 연락해주세요.", HttpStatus.INTERNAL_SERVER_ERROR, e)
}

package kr.co.jsol.common.exception

import org.springframework.http.HttpStatus

sealed class GeneralClientException {
    class BadRequestException(
        message: String = "잘못된 요청입니다. 데이터를 확인해주세요.",
        e: Throwable? = null,
    ) : CustomException("GNR-4000", message, HttpStatus.BAD_REQUEST, e)

    class UnauthorizedException(
        message: String = "인증이 필요합니다.",
        e: Throwable? = null,
    ) : CustomException("GNR-4010", message, HttpStatus.UNAUTHORIZED, e)

    class ForbiddenException(
        message: String = "접근 권한이 없습니다.",
        e: Throwable? = null,
    ) : CustomException("GNR-4030", message, HttpStatus.FORBIDDEN, e)

    class NotFoundException(
        message: String = "요청에 대한 결과를 찾을 수 없습니다.",
        e: Throwable? = null,
    ) : CustomException("GNR-4040", message, HttpStatus.NOT_FOUND, e)

    // 로그인 실패
    class LoginFailedException(
        message: String = "로그인 실패",
        e: Throwable? = null,
    ) : CustomException("GNR-4031", message, HttpStatus.FORBIDDEN, e)
}

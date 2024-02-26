package kr.co.jsol.common.exception.domain.account

import kr.co.jsol.common.exception.CustomException
import org.springframework.http.HttpStatus

sealed class AccountException {
    class NotFoundByIdException(
        message: String = "id로 계정 정보를 찾을 수 없습니다.",
        e: Throwable? = null,
    ) : CustomException("ACCOUNT-0001", message, HttpStatus.NOT_FOUND, e)

    class NotFoundExceptionByEmployeeNumber(
        message: String = "사원아이디로 사용자 정보를 찾을 수 없습니다.",
        e: Throwable? = null,
    ) : CustomException("ACCOUNT-0002", message, HttpStatus.NOT_FOUND, e)

    class ForbiddenCompanyAccess(
        message: String = "요청한 회사 정보에 대한 접근 권한이 없습니다.",
        e: Throwable? = null,
    ) : CustomException("ACCOUNT-0003", message, HttpStatus.FORBIDDEN, e)

    class ForbiddenClientCompanyAccess(
        message: String = "요청한 고객사 정보에 대한 접근 권한이 없습니다.",
        e: Throwable? = null,
    ) : CustomException("ACCOUNT-0004", message, HttpStatus.FORBIDDEN, e)

    class DuplicatedUid(
        message: String = "중복된 아이디가 존재합니다.",
        e: Throwable? = null,
    ) : CustomException("ACCOUNT-0005", message, HttpStatus.CONFLICT, e)

    class DuplicatedEmployeeNumber(
        message: String = "중복된 사원아이디가 존재합니다.",
        e: Throwable? = null,
    ) : CustomException("ACCOUNT-0020", message, HttpStatus.CONFLICT, e)
}

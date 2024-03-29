package kr.co.jsol.common.exception.domain.account

import kr.co.jsol.common.exception.CustomException
import org.springframework.http.HttpStatus

sealed class AccountException {
    class NotFoundByIdException(
        message: String = "id로 $NAME 정보를 찾을 수 없습니다.",
        e: Throwable? = null,
    ) : CustomException("$CODE-0001", message, HttpStatus.NOT_FOUND, e)

    class DuplicatedUid(
        message: String = "중복된 아이디가 존재합니다.",
        e: Throwable? = null,
    ) : CustomException("$CODE-0002", message, HttpStatus.CONFLICT, e)

    class NotFoundExceptionByEmployeeNumber(
        message: String = "사원 아이디로 $NAME 정보를 찾을 수 없습니다.",
        e: Throwable? = null,
    ) : CustomException("$CODE-0021", message, HttpStatus.NOT_FOUND, e)

    class DuplicatedEmployeeNumber(
        message: String = "중복된 사원 아이디가 존재합니다.",
        e: Throwable? = null,
    ) : CustomException("$CODE-0022", message, HttpStatus.CONFLICT, e)

    companion object {
        private const val NAME = "계정"
        private const val CODE = "ACCOUNT"
    }
}

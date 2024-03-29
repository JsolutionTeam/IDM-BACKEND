package kr.co.jsol.common.exception.domain.devicesubsidy

import kr.co.jsol.common.exception.CustomException
import org.springframework.http.HttpStatus

sealed class DeviceSubsidyException {
    class NotFoundByIdException(
        message: String = "id로 $NAME 정보를 찾을 수 없습니다.",
        e: Throwable? = null,
    ) : CustomException("$CODE-0001", message, HttpStatus.NOT_FOUND, e)

    class NotFoundBySearchException(
        message: String = "검색 조건에 맞는 $NAME 정보를 찾을 수 없습니다.",
        e: Throwable? = null,
    ) : CustomException("$CODE-0002", message, HttpStatus.NOT_FOUND, e)

    companion object {
        private const val NAME = "공시 지원금"
        private const val CODE = "DEVICE-SUBSIDY"
    }
}

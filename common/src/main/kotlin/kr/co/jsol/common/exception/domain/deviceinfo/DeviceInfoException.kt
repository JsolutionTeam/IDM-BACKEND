package kr.co.jsol.common.exception.domain.deviceinfo

import kr.co.jsol.common.exception.CustomException
import org.springframework.http.HttpStatus

private const val NAME = "단말 상세"
private const val CODE = ""

sealed class DeviceInfoException {
    class NotFoundByIdException(
        message: String = "id로 $NAME 정보를 찾을 수 없습니다.",
        e: Throwable? = null,
    ) : CustomException("$CODE-0001", message, HttpStatus.NOT_FOUND, e)

    class NotFoundByDeviceIdException(
        message: String = "단말 아이디로 $NAME 정보를 찾을 수 없습니다.",
        e: Throwable? = null,
    ) : CustomException("$CODE-0002", message, HttpStatus.NOT_FOUND, e)
}

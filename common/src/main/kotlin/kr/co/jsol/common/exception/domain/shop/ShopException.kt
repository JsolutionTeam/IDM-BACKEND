package kr.co.jsol.common.exception.domain.shop

import kr.co.jsol.common.exception.CustomException
import org.springframework.http.HttpStatus

private const val NAME = "업체"
private const val CODE = "SHOP"

sealed class ShopException {
    class NotFoundByIdException(
        message: String = "id로 $NAME 정보를 찾을 수 없습니다.",
        e: Throwable? = null,
    ) : CustomException("$CODE-0001", message, HttpStatus.NOT_FOUND, e)
}

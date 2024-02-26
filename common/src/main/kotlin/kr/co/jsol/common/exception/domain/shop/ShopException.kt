package kr.co.jsol.common.exception.domain.shop

import kr.co.jsol.common.exception.CustomException
import org.springframework.http.HttpStatus

sealed class ShopException {
    class NotFoundByIdException(
        message: String = "id로 업체 정보를 찾을 수 없습니다.",
        e: Throwable? = null,
    ) : CustomException("SHOP-0001", message, HttpStatus.NOT_FOUND, e)
}

package kr.co.jsol.common.handler

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler

class GlobalExceptionHandler {

    private val log = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    /**
     * javax.validation.Valid or @Validated 으로 binding error 발생시 발생한다.
     * HttpMessageConverter 에서 등록한 HttpMessageConverter binding 못할경우 발생
     * 주로 @RequestBody, @RequestPart 어노테이션에서 발생
     */
    @ExceptionHandler(MethodArgumentNotValidException::class)
    protected fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException?): ResponseEntity<ErrorResponse> {
        log.error("handleMethodArgumentNotValidException", e)
        val response = ErrorResponse("요구사항이 맞지 않습니다! 필수 정보를 입력해 주세요", false)
        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }

    /**
     * @ModelAttribut 으로 binding error 발생시 BindException 발생한다.
     * ref https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-modelattrib-method-args
     */
    @ExceptionHandler(BindException::class)
    protected fun handleBindException(e: BindException?): ResponseEntity<ErrorResponse> {
        log.error("handleBindException", e)
        val response = ErrorResponse("요구사항이 맞지 않습니다! 필수 정보를 입력해 주세요", false)
        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }

    //    /**
    //     * enum type 일치하지 않아 binding 못할 경우 발생
    //     * 주로 @RequestParam enum으로 binding 못했을 경우 발생
    //     */
    //    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    //    protected ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
    //        log.error("handleMethodArgumentTypeMismatchException", e);
    //        final ErrorResponse response = ErrorResponse.of(e);
    //        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    //    }
    /**
     * 지원하지 않은 HTTP method 호출 할 경우 발생
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    protected fun handleHttpRequestMethodNotSupportedException(e: HttpRequestMethodNotSupportedException?): ResponseEntity<ErrorResponse> {
        log.error("handleHttpRequestMethodNotSupportedException", e)
        val response = ErrorResponse("지원하지 않는 요청을 요청 중입니다.", false)
        return ResponseEntity(response, HttpStatus.METHOD_NOT_ALLOWED)
    }

    /**
     * Authentication 객체가 필요한 권한을 보유하지 않은 경우 발생합니다.
     */
    @ExceptionHandler(AccessDeniedException::class)
    protected fun handleAccessDeniedException(e: AccessDeniedException): ResponseEntity<ErrorResponse> {
        log.error("handleAccessDeniedException", e)
        val response = ErrorResponse(e.message, false)
        return ResponseEntity(response, HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(RuntimeException::class)
    protected fun handleRuntimeException(e: RuntimeException): ResponseEntity<ErrorResponse> {
        log.error("handleRuntimeException", e)
        val response = ErrorResponse(e.message, false)
        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    protected fun handleException(e: Exception): ResponseEntity<ErrorResponse> {
        log.error("handleEntityNotFoundException", e)
        val response = ErrorResponse(e.message, false)
        return ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}

package kr.co.jsol.domain.notice.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty

@Schema(name = "공지사항 다중 삭제 요청")
data class DeleteNoticesDto(
    @field:Valid
    @field:NotEmpty(message = "공지사항 아이디는 필수 입력입니다.")
    @field:Schema(description = "공지사항 아이디 리스트")
    val ids: List<@Min(value = 1L, message = "공지사항 아이디는 1보다 큰 숫자여야 합니다.") Long>,
)

package kr.co.jsol.domain.notice.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.Valid
import javax.validation.constraints.NotEmpty

@Schema(name = "공지사항 다중 수정 요청")
data class UpdateNoticesDto(
    @field:Valid
    @field:NotEmpty(message = "공지사항 수정 요청 데이터가 없습니다.")
    @field:Schema(description = "공지사항 수정 요청")
    val notices: List<UpdateNoticeDto>,
)

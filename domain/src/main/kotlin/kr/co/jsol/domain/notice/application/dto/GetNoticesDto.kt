package kr.co.jsol.domain.notice.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.Size

@Schema(name = "공지사항 페이지 조회 요청")
data class GetNoticesDto(
    @field:Size(max = 255, message = "제목은 255자 이하로 입력해주세요.")
    @field:Schema(description = "제목")
    val title: String? = null,

    @field:Size(max = 1000, message = "내용은 1000자 이하로 입력해주세요.")
    @field:Schema(description = "내용")
    val content: String? = null,

    @field:Schema(description = "팝업 사용 여부", implementation = Boolean::class)
    val isPopup: Boolean? = null,
)

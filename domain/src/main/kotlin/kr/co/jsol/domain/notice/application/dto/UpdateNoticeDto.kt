package kr.co.jsol.domain.notice.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Schema(name = "공지사항 단일 수정 요청")
data class UpdateNoticeDto(
    @field:NotNull(message = "공지사항 아이디는 필수 입력입니다.")
    @field:Length(min = 1, message = "공지사항 아이디는 1 이상 입력해야 합니다.")
    @field:Schema(description = "공지사항 아이디")
    val id: Long,

    @field:Size(max = 255, message = "제목은 255자 이하로 입력해주세요.")
    @field:Schema(description = "제목")
    val title: String? = null,

    @field:Size(max = 1000, message = "내용은 1000자 이하로 입력해주세요.")
    @field:Schema(description = "내용")
    val content: String? = null,

    @field:Schema(description = "팝업 사용 여부")
    val isPopup: Boolean? = null,
)

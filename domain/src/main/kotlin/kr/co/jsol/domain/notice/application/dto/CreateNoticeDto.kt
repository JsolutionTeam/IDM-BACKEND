package kr.co.jsol.domain.notice.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.jsol.domain.notice.entity.Notice
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Schema(name = "공지사항 등록 요청")
data class CreateNoticeDto(
    @field:NotBlank(message = "제목은 필수 입력입니다.")
    @field:Size(max = 255, message = "제목은 255자 이하로 입력해주세요.")
    @field:Schema(description = "제목")
    val title: String,

    @field:NotBlank(message = "내용은 필수 입력입니다.")
    @field:Size(max = 1000, message = "내용은 1000자 이하로 입력해주세요.")
    @field:Schema(description = "내용")
    val content: String,

    @field:NotNull(message = "팝업 사용 여부는 필수 입력입니다.")
    @field:Schema(description = "팝업 사용 여부")
    val isPopup: Boolean,
) {

    fun toEntity(writer: String) = Notice(
        title = title,
        content = content,
        writer = writer,
        isPopup = isPopup,
    )
}

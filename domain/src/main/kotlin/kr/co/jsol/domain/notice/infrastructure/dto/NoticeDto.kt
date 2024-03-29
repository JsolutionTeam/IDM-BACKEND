package kr.co.jsol.domain.notice.infrastructure.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.jsol.domain.notice.entity.Notice
import java.time.LocalDateTime

@Schema(name = "공지사항 응답")
data class NoticeDto(
    @field:Schema(description = "공지사항 아이디")
    val id: Long,

    @field:Schema(description = "공지사항 제목")
    val title: String,

    @field:Schema(description = "공지사항 내용")
    val content: String,

    @field:Schema(description = "공지사항 작성자")
    val writer: String,

    @field:Schema(description = "팝업 사용 여부")
    val isPopup: Boolean,

    @field:Schema(description = "공지사항 수정일")
    val updatedAt: LocalDateTime,
) {

    constructor(notice: Notice) : this(
        id = notice.id,
        title = notice.title,
        content = notice.content,
        writer = notice.writer,
        isPopup = notice.isPopup,
        updatedAt = notice.updatedAt!! // 생성시 무조건 updatedAt이 존재함
    )
}

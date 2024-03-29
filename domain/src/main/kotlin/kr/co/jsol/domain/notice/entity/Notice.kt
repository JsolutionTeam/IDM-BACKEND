package kr.co.jsol.domain.notice.entity

import kr.co.jsol.common.domain.BaseEntity
import kr.co.jsol.domain.notice.application.dto.UpdateNoticeDto
import org.hibernate.annotations.Comment
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Table
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@SQLDelete(sql = "UPDATE tb_notice SET deleted_at = now() WHERE id = ?")
@Entity
@javax.persistence.Table(name = "tb_notice")
@Table(appliesTo = "tb_notice", comment = "공지사항")
class Notice(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    @Comment("아이디")
    var id: Long = 0,

    @Column(name = "title")
    @Comment("제목")
    var title: String,

    @Column(name = "content", length = 1000)
    @Comment("내용")
    var content: String,

    @Column(name = "writer")
    @Comment("작성자")
    var writer: String,

    @Column(name = "is_popup")
    var isPopup: Boolean,
) : BaseEntity(updatedAt = LocalDateTime.now()) {

    fun update(
        updateNoticesDto: UpdateNoticeDto,
        writer: String,
    ) {
        updateNoticesDto.title?.let { this.title = it }
        updateNoticesDto.content?.let { this.content = it }
        updateNoticesDto.isPopup?.let { this.isPopup = it }
        this.writer = writer
    }
}

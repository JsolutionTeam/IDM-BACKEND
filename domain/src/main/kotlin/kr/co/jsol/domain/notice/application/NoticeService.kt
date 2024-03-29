package kr.co.jsol.domain.notice.application

import kr.co.jsol.domain.notice.application.dto.CreateNoticeDto
import kr.co.jsol.domain.notice.application.dto.DeleteNoticesDto
import kr.co.jsol.domain.notice.application.dto.GetNoticesDto
import kr.co.jsol.domain.notice.application.dto.UpdateNoticeDto
import kr.co.jsol.domain.notice.application.dto.UpdateNoticesDto
import kr.co.jsol.domain.notice.infrastructure.command.NoticeCommandRepository
import kr.co.jsol.domain.notice.infrastructure.dto.NoticeDto
import kr.co.jsol.domain.notice.infrastructure.query.NoticeQueryRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class NoticeService(
    private val command: NoticeCommandRepository,
    private val query: NoticeQueryRepository,
) {

    @Transactional
    fun create(
        createNoticeDto: CreateNoticeDto,
        writer: String,
    ): NoticeDto {
        val notice = command.save(createNoticeDto.toEntity(writer))
        return NoticeDto(notice)
    }

    @Transactional
    fun updateMultiple(
        updateNoticesDto: UpdateNoticesDto,
        writer: String,
    ): List<NoticeDto> {
        return updateNoticesDto.notices.map {
            update(it, writer)

        }
    }

    @Transactional
    fun update(
        updateNoticeDto: UpdateNoticeDto,
        writer: String,
    ): NoticeDto {
        val notice = query.getById(updateNoticeDto.id)
        notice.update(updateNoticeDto, writer)
        return NoticeDto(command.save(notice))
    }

    @Transactional
    fun deleteMultiple(deleteNoticesDto: DeleteNoticesDto) {
        command.deleteMultiple(query.findListByIdList(deleteNoticesDto.ids))
    }

    @Transactional(readOnly = true)
    fun findOffsetPageBySearch(
        getNoticesDto: GetNoticesDto,
        pageable: Pageable,
    ): Page<NoticeDto> {
        return query.findOffsetPageBySearch(getNoticesDto, pageable)
            .map { NoticeDto(it) }
    }

    @Transactional(readOnly = true)
    fun getById(id: Long): NoticeDto {
        return NoticeDto(query.getById(id))
    }
}

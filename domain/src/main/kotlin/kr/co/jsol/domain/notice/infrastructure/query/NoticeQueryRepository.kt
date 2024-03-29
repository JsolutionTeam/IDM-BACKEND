package kr.co.jsol.domain.notice.infrastructure.query

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import kr.co.jsol.common.exception.domain.notice.NoticeException
import kr.co.jsol.common.repository.BaseQueryRepository
import kr.co.jsol.domain.notice.application.dto.GetNoticesDto
import kr.co.jsol.domain.notice.entity.Notice
import kr.co.jsol.domain.notice.entity.QNotice.Companion.notice
import kr.co.jsol.domain.notice.infrastructure.repository.NoticeRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class NoticeQueryRepository(
    private val repository: NoticeRepository,
    private val queryFactory: JPAQueryFactory,
) : BaseQueryRepository<Notice, Long>(notice, repository) {

    fun getById(id: Long): Notice {
        return queryFactory
            .selectFrom(notice)
            .where(notice.id.eq(id))
            .fetchOne() ?: throw NoticeException.NotFoundByIdException()
    }

    fun findListByIdList(ids: List<Long>): List<Notice> {
        return queryFactory
            .selectFrom(notice)
            .where(notice.id.`in`(ids))
            .fetch()
    }

    fun findOffsetPageBySearch(
        getNoticesDto: GetNoticesDto,
        pageable: Pageable,
    ): Page<Notice> {
        return repository.findAll(
            BooleanBuilder()
                .and(titleContains(getNoticesDto.title))
                .and(contentContains(getNoticesDto.content))
                .and(isPopupEq(getNoticesDto.isPopup)),
            pageable,
        )
    }

    fun titleContains(title: String?): BooleanExpression? {
        return title?.let { notice.title.contains(it) }
    }

    fun contentContains(contents: String?): BooleanExpression? {
        return contents?.let { notice.content.contains(it) }
    }

    fun isPopupEq(isPopup: Boolean?): BooleanExpression? {
        return isPopup?.let { notice.isPopup.eq(it) }
    }
}

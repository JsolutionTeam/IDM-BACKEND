package kr.co.jsol.domain.notice.infrastructure.repository

import kr.co.jsol.domain.notice.entity.Notice
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface NoticeRepository
    : CrudRepository<Notice, Long>,
    QuerydslPredicateExecutor<Notice>

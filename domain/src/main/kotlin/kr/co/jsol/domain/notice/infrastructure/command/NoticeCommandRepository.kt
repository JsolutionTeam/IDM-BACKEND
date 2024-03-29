package kr.co.jsol.domain.notice.infrastructure.command

import kr.co.jsol.common.repository.BaseCommandRepository
import kr.co.jsol.domain.notice.entity.Notice
import kr.co.jsol.domain.notice.infrastructure.repository.NoticeRepository
import org.springframework.stereotype.Component

@Component
class NoticeCommandRepository(
    repository: NoticeRepository,
) : BaseCommandRepository<Notice, Long>(repository) {

}

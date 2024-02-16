package kr.co.jsol.common.timestamp

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass // 멤버 변수가 컬럼이 되도록 합니다. 공통적으로 사용할 목적으로 사용 (idx 도 사실 들어갈 수는 있다)
@EntityListeners(AuditingEntityListener::class) // 변경되었을 때 자동으로 기록합니다. Main에 @EnableJpaAuditing 어노테이션 필요 or Config 만들어서 적용
abstract class TimeStamp(
    @field:Column(updatable = false) // update 쿼리 시 안날라감, 최초 생성이후 변하지 않음
    @field:CreatedDate // 최초 생성 시점
    private val createdAt: LocalDateTime? = null,

    @field:LastModifiedDate // 마지막 변경 시점
    private val updatedAt: LocalDateTime? = null,
)

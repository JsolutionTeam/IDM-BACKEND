package kr.co.jsol.common.domain

import org.hibernate.annotations.Comment
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity(

    // TODO 모든 기능이 이전됐을때 속성명을 created_at, updated_at 으로 변경해야 함
    @Column(name = "crtd_dt")
    @CreatedDate
    @Comment("생성일시")
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updt_dt")
    @LastModifiedDate
    @Comment("수정일시")
    open var updatedAt: LocalDateTime? = null,

    @Column(name = "deleted_at")
    @Comment("삭제일시")
    var deletedAt: LocalDateTime? = null,
) {

    fun isDeleted(): Boolean {
        return this.deletedAt != null
    }
}

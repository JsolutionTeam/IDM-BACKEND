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
    @Column
    @CreatedDate
    @Comment("생성일시")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column
    @LastModifiedDate
    @Comment("수정일시")
    var updatedAt: LocalDateTime? = null,

    @Column
    @Comment("삭제일시")
    var deletedAt: LocalDateTime? = null,
) {

    fun softDelete() {
        this.deletedAt = LocalDateTime.now()
    }

    fun isDeleted(): Boolean {
        return this.deletedAt != null
    }
}

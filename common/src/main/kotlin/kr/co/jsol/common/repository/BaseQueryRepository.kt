package kr.co.jsol.common.repository

import com.querydsl.core.types.dsl.BeanPath
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.DateTimePath
import kr.co.jsol.common.domain.BaseEntity
import org.slf4j.LoggerFactory
import org.springframework.data.repository.CrudRepository
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.jvm.optionals.getOrNull

abstract class BaseQueryRepository<T : BaseEntity, ID : Any>(
    private val qEntity: BeanPath<T>,
    private val crudRepository: CrudRepository<T, ID>,
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    fun findById(id: ID): T? {
        val entity = crudRepository.findById(id)
            .getOrNull() ?: return null

        if (entity.isDeleted()) {
            return null
        }

        return entity
    }

    fun findAll(withDelete: Boolean = false): List<T> {
        // withDelete가 true라면 삭제된 데이터도 반환
        val list = crudRepository.findAll()
        if (withDelete) {
            return list.toList()
        }
        return list.filter { !it.isDeleted() }
    }

    fun DateTimePath<LocalDateTime>.between(
        start: LocalDate,
        end: LocalDate,
    ): BooleanExpression {
        var startDateTime = start.atStartOfDay()
        var endDateTime = end.atStartOfDay()

        // 만약 end가 start보다 이전이라면 둘을 바꿔줌
        if (startDateTime.isAfter(endDateTime)) {
            val temp = startDateTime
            startDateTime = endDateTime
            endDateTime = temp
        }

        // LocalDate를 LocalDateTime으로 변환
        // 만약 start와 end가 같은 날짜라면, end에 23시 59분 59초를 추가
        if (startDateTime == endDateTime) {
            endDateTime = endDateTime.plusHours(23)
                .plusMinutes(59)
                .plusSeconds(59)
        }

        logger.info(
            "[BaseQueryRepository - DateTimePath.between] startDateTime: $startDateTime, endDateTime: $endDateTime",
        )
        return this.between(startDateTime, endDateTime)
    }
}

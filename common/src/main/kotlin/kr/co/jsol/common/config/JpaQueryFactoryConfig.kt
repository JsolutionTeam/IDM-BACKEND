package kr.co.jsol.common.config

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.Expressions
import com.querydsl.core.types.dsl.StringPath
import com.querydsl.jpa.impl.JPAQuery
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.Pageable
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Configuration
class JpaQueryFactoryConfig {
    @PersistenceContext
    private lateinit var entityManager: EntityManager

    @Bean
    fun jpaQueryFactory() = JPAQueryFactory(entityManager)
}

fun <T> JPAQuery<T>.pagination(pageable: Pageable): JPAQuery<T> {
    return this.offset(getOffset(pageable))
        .limit(getLimit(pageable))
}

private fun getOffset(pageable: Pageable): Long {
    return if (pageable.isPaged) {
        pageable.offset
    } else {
        0
    }
}

const val MAX_LIMIT: Long = 10_000
private fun getLimit(pageable: Pageable): Long {
    // 너무 큰 값이 들어오면 어플리케이션 부하가 커질 수 있으므로 제한을 둔다.
    return if (pageable.isPaged) {
        var toLong = pageable.pageSize.toLong()
        if (toLong > MAX_LIMIT) toLong = MAX_LIMIT
        toLong
    } else {
        MAX_LIMIT
    }
}

fun isNamesContainsIn(
    column: StringPath,
    names: List<String>?,
): BooleanExpression? {
    if (names.isNullOrEmpty()) return null
    return Expressions.booleanTemplate(
        "function('regexp', {0}, {1}) = true",
        column, // ex QClient.client.name
        names.joinToString("|"),
    )
}

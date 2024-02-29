package kr.co.jsol.domain.color.infrastructure.repository

import kr.co.jsol.domain.color.entity.Color
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ColorRepository :
    CrudRepository<Color, Long>,
    QuerydslPredicateExecutor<Color>



package kr.co.jsol.common.repository

import kr.co.jsol.common.domain.BaseEntity
import org.springframework.data.repository.CrudRepository

open class BaseCommandRepository<T : BaseEntity, ID : Any>(
    private val curdRepository: CrudRepository<T, ID>,
) {

    /**
     * 계정 정보가 없는 저장작업
     */
    fun save(entity: T): T {
        return curdRepository.save(entity)
    }

    fun saveMultiple(entities: List<T>): List<T> {
        return curdRepository.saveAll(entities).toList()
    }

    fun softDelete(entity: T): T {
        entity.softDelete()
        return curdRepository.save(entity)
    }

    fun softDeleteMultiple(entities: List<T>): List<T> {
        entities.forEach { it.softDelete() }

        return curdRepository.saveAll(entities).toList()
    }

    fun hardDelete(entity: T) {
        curdRepository.delete(entity)
    }

    fun hardDeleteMultiple(entities: List<T>) {
        curdRepository.deleteAll(entities)
    }
}

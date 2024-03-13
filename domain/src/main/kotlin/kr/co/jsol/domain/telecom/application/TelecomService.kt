package kr.co.jsol.domain.telecom.application

import kr.co.jsol.domain.telecom.application.dto.CreateTelecomDto
import kr.co.jsol.domain.telecom.application.dto.UpdateTelecomDto
import kr.co.jsol.domain.telecom.infrastructure.dto.TelecomDto
import kr.co.jsol.domain.telecom.infrastructure.query.TelecomQueryRepository
import kr.co.jsol.domain.telecom.infrastructure.repository.TelecomRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TelecomService(
    private val repository: TelecomRepository,
    private val query: TelecomQueryRepository,
) {

    @Transactional
    fun create(createTelecomDto: CreateTelecomDto): TelecomDto {
        return TelecomDto(repository.save(createTelecomDto.toEntity()))
    }

    @Transactional
    fun update(updateTelecomDto: UpdateTelecomDto): TelecomDto {
        val telecom = repository.findById(updateTelecomDto.id)
            .orElseThrow { throw IllegalArgumentException("통신사를 찾을 수 없습니다.") }
            .apply {
                name = updateTelecomDto.name
            }
        return TelecomDto(repository.save(telecom))
    }

    @Transactional
    fun delete(id: Long): TelecomDto {
        val telecom = query.getById(id)
        repository.delete(telecom)
        return TelecomDto(telecom)
    }

    @Transactional(readOnly = true)
    fun getById(id: Long): TelecomDto {
        return TelecomDto(query.getById(id))
    }

    @Transactional(readOnly = true)
    fun findAll(): List<TelecomDto> {
        return query.findAll().map(::TelecomDto)
    }
}

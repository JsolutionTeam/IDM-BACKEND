package kr.co.jsol.domain.insurance.application

import kr.co.jsol.domain.insurance.infrastructure.query.InsuranceQueryRepository
import kr.co.jsol.domain.insurance.infrastructure.repository.InsuranceRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class InsuranceService(
    private val repository: InsuranceRepository,
    private val query: InsuranceQueryRepository,
) {

    private final val log = LoggerFactory.getLogger(this.javaClass)

    init {
        log.info("insurance size : ${repository.count()}")
    }

    @Transactional(readOnly = true)
    fun getById(id: Long) = query.getById(id)
}

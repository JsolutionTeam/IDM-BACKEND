package kr.co.jsol.domain.account.infrastructure.command

import kr.co.jsol.common.repository.BaseCommandRepository
import kr.co.jsol.domain.account.entity.Account
import kr.co.jsol.domain.account.infrastructure.repository.AccountRepository
import org.springframework.stereotype.Component

@Component
class AccountCommandRepository(
    private val repository: AccountRepository,
) : BaseCommandRepository<Account, String>(repository)

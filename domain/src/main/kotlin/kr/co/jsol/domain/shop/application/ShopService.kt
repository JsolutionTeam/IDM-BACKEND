package kr.co.jsol.domain.shop.application

import kr.co.jsol.common.domain.AccountAuthority
import kr.co.jsol.domain.account.application.AccountService
import kr.co.jsol.domain.account.application.dto.CreateAccountDto
import kr.co.jsol.domain.shop.application.dto.CreateCompanyDto
import kr.co.jsol.domain.shop.entity.Shop
import kr.co.jsol.domain.shop.entity.enums.ShopStatus
import kr.co.jsol.domain.shop.infrastructure.dto.ShopDto
import kr.co.jsol.domain.shop.infrastructure.query.ShopQueryRepository
import kr.co.jsol.domain.shop.infrastructure.repository.ShopRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ShopService(
    private val repository: ShopRepository,
    private val query: ShopQueryRepository,

    private val accountService: AccountService,
) {

    init {
        // 아무 데이터가 없다면, 기본 업체를 생성한다
        // 대신 계정 서버 및 MCall 서버에도 아래 정보로 계정이 등록되어야 한다
        if (!query.existsAny()) {
            val shop = repository.save(
                Shop(
                    name = "제이솔루션",
                    role = "JSOL",
                    status = ShopStatus.DONE,
                    phone = "1644-3950",
                    managerId = "js",
                    managerName = "권세기",
                    managerPhone = "010-0000-0000",
                )
            )

            accountService.create(
                CreateAccountDto(
                    id = "js",
                    password = "js",
                    name = "권세기",
                    role = AccountAuthority.COMPANY.toString(),
                    phone = "010-0000-0000",
                    shopId = shop.id,
                )
            )
        }
    }

    @Transactional
    fun createCompany(createCompanyDto: CreateCompanyDto): ShopDto {
        val shop = repository.save(createCompanyDto.toEntity())
        accountService.create(
            CreateAccountDto(
                id = createCompanyDto.managerId,
                password = createCompanyDto.managerPassword,
                name = createCompanyDto.managerName,
                role = AccountAuthority.COMPANY.toString(),
                phone = createCompanyDto.managerPhone,
                shopId = shop.id,
            )
        )
        return ShopDto(shop)
    }
}

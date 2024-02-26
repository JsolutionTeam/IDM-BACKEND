package kr.co.jsol.domain.shop.application

import kr.co.jsol.domain.shop.application.dto.CreateShopDto
import kr.co.jsol.domain.shop.infrastructure.dto.ShopDto
import kr.co.jsol.domain.shop.infrastructure.repository.ShopRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ShopService(
    private val shopRepository: ShopRepository,
) {

    @Transactional
    fun create(createShopDto: CreateShopDto): ShopDto {
        return ShopDto(shopRepository.save(createShopDto.toEntity()))
    }
}

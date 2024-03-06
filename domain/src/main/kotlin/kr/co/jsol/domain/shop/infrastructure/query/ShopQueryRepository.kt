package kr.co.jsol.domain.shop.infrastructure.query

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import kr.co.jsol.common.exception.domain.shop.ShopException
import kr.co.jsol.common.repository.BaseQueryRepository
import kr.co.jsol.domain.shop.application.dto.GetCompanyDto
import kr.co.jsol.domain.shop.entity.QShop.Companion.shop
import kr.co.jsol.domain.shop.entity.Shop
import kr.co.jsol.domain.shop.infrastructure.repository.ShopRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class ShopQueryRepository(
    private val repository: ShopRepository,
    private val queryFactory: JPAQueryFactory,
) : BaseQueryRepository<Shop, Long>(shop, repository) {

    fun existsAny(): Boolean {
        return repository.exists(
            shop.id.isNotNull
                .and(shop.deletedAt.isNull),
        )
    }

    fun getById(id: Long): Shop {
        return findById(id) ?: throw ShopException.NotFoundByIdException()
    }

    fun findCompanyPage(
        getCompanyDto: GetCompanyDto,
        pageable: Pageable,
    ): Page<Shop> {
        val booleanBuilder = BooleanBuilder()
            .and(shop.deletedAt.isNull)
            .and(shop.useMcallShop.isTrue)
            .and(nameContains(getCompanyDto.name))
            .and(phoneContains(getCompanyDto.phone))
            .and(managerIdContains(getCompanyDto.managerId))
            .and(managerNameContains(getCompanyDto.managerName))
            .and(managerPhoneContains(getCompanyDto.managerPhone))

        return repository.findAll(
            booleanBuilder,
            pageable,
        )
    }

    /////

    private fun nameContains(name: String?): BooleanExpression? {
        return name?.let { shop.name.contains(it) }
    }

    private fun phoneContains(phone: String?): BooleanExpression? {
        return phone?.let { shop.phone.contains(it) }
    }

    private fun managerIdContains(managerId: String?): BooleanExpression? {
        return managerId?.let { shop.managerId.contains(it) }
    }

    private fun managerNameContains(managerName: String?): BooleanExpression? {
        return managerName?.let { shop.managerName.contains(it) }
    }

    private fun managerPhoneContains(managerPhone: String?): BooleanExpression? {
        return managerPhone?.let { shop.managerPhone.contains(it) }
    }
}

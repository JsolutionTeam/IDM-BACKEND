package kr.co.jsol.domain.companysubsidy.infrastructure.query

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.group.GroupBy.groupBy
import com.querydsl.core.group.GroupBy.list
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.Expressions
import com.querydsl.jpa.impl.JPAQueryFactory
import kr.co.jsol.common.config.pagination
import kr.co.jsol.common.exception.domain.companysubsidy.CompanySubsidyException
import kr.co.jsol.common.repository.BaseQueryRepository
import kr.co.jsol.domain.companysubsidy.application.dto.ExistsCompanySubsidyDto
import kr.co.jsol.domain.companysubsidy.application.dto.GetCompanySubsidiesDto
import kr.co.jsol.domain.companysubsidy.application.dto.GetCompanySubsidyPriceDto
import kr.co.jsol.domain.companysubsidy.entity.CompanySubsidy
import kr.co.jsol.domain.companysubsidy.entity.QCompanySubsidy.Companion.companySubsidy
import kr.co.jsol.domain.companysubsidy.infrastructure.dto.CompanySubsidyGroupByDetailDto
import kr.co.jsol.domain.companysubsidy.infrastructure.dto.QCompanySubsidyGroupByDetailDto
import kr.co.jsol.domain.companysubsidy.infrastructure.dto.QCompanySubsidyGroupByDetailDto_Detail
import kr.co.jsol.domain.companysubsidy.infrastructure.repository.CompanySubsidyRepository
import kr.co.jsol.domain.device.entity.QDevice.Companion.device
import kr.co.jsol.domain.device.infrastructure.dto.QDeviceRawDto
import kr.co.jsol.domain.phoneplan.entity.QPhonePlan.Companion.phonePlan
import kr.co.jsol.domain.phoneplan.infrastructure.dto.QPhonePlanRawDto
import kr.co.jsol.domain.shop.entity.QShop.Companion.shop
import kr.co.jsol.domain.shop.infrastructure.dto.QShopSimpleDto
import kr.co.jsol.domain.telecom.entity.QTelecom.Companion.telecom
import kr.co.jsol.domain.telecom.infrastructure.dto.QTelecomDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class CompanySubsidyQueryRepository(
    private val repository: CompanySubsidyRepository,
    private val queryFactory: JPAQueryFactory,
) : BaseQueryRepository<CompanySubsidy, Long>(companySubsidy, repository) {

    fun getById(id: Long): CompanySubsidy {
        return repository.findOne(
            companySubsidy.id.eq(id)
                .and(companySubsidy.deletedAt.isNull)
        )
            .orElseThrow { throw CompanySubsidyException.NotFoundByIdException() }
    }

    fun getWithDetailByIdList(idList: List<Long>): CompanySubsidyGroupByDetailDto {

        val result = queryFactory.from(companySubsidy)
            .where(
                companySubsidy.id.`in`(idList)
                    .and(companySubsidy.deletedAt.isNull)
            )
            .select(companySubsidy)
            .groupBy(
                companySubsidy.telecom,
                companySubsidy.phonePlan,
                companySubsidy.device,
                companySubsidy.shop,
                companySubsidy.openType,
                companySubsidy.discountType,
            )
            .transform(
                groupBy(
                    companySubsidy.telecom,
                    companySubsidy.phonePlan,
                    companySubsidy.device,
                    companySubsidy.shop,
                )
                    .list(
                        QCompanySubsidyGroupByDetailDto(
                            QTelecomDto(
                                companySubsidy.telecom.id,
                                companySubsidy.telecom.name,
                            ),
                            QPhonePlanRawDto(
                                companySubsidy.phonePlan.id,
                                companySubsidy.phonePlan.name,
                                companySubsidy.phonePlan.price,
                                companySubsidy.phonePlan.category,
                                companySubsidy.phonePlan.callExp,
                                companySubsidy.phonePlan.dataExp,
                                companySubsidy.phonePlan.mailExp,
                            ),
                            QDeviceRawDto(
                                companySubsidy.device.id,
                                companySubsidy.device.petName,
                                companySubsidy.device.modelName,
                                companySubsidy.device.price,
                                companySubsidy.device.volume,
                                companySubsidy.device.series,
                                companySubsidy.device.createdAt,
                                companySubsidy.device.updatedAt,
                            ),
                            QShopSimpleDto(
                                companySubsidy.shop.id,
                                companySubsidy.shop.name,
                            ),
                            list(
                                QCompanySubsidyGroupByDetailDto_Detail(
                                    companySubsidy.id,
                                    companySubsidy.price,
                                    companySubsidy.openType,
                                    companySubsidy.discountType,
                                )
                            )
                        )
                    )
            ).toList()

        if (result.isNotEmpty()) return result[0]
        else throw CompanySubsidyException.NotFoundByIdException()
    }

    fun exists(existsCompanySubsidyDto: ExistsCompanySubsidyDto): Boolean {
        val booleanBuilder = BooleanBuilder()
            .and(companySubsidy.shop.id.eq(existsCompanySubsidyDto.shopId))
            .and(companySubsidy.telecom.id.eq(existsCompanySubsidyDto.telecomId))
            .and(companySubsidy.phonePlan.id.eq(existsCompanySubsidyDto.phonePlanId))
            .and(companySubsidy.device.id.eq(existsCompanySubsidyDto.deviceId))
            .and(companySubsidy.deletedAt.isNull)

        return queryFactory.selectOne()
            .from(companySubsidy)
            .where(booleanBuilder)
            .fetchFirst() != null
    }

    fun getPrice(getCompanySubsidyPriceDto: GetCompanySubsidyPriceDto): Long {
        val booleanBuilder = BooleanBuilder()
            .and(companySubsidy.shop.id.eq(getCompanySubsidyPriceDto.shopId))
            .and(companySubsidy.telecom.id.eq(getCompanySubsidyPriceDto.telecomId))
            .and(companySubsidy.phonePlan.id.eq(getCompanySubsidyPriceDto.phonePlanId))
            .and(companySubsidy.device.id.eq(getCompanySubsidyPriceDto.deviceId))
            .and(companySubsidy.deletedAt.isNull)

        return queryFactory.select(companySubsidy.price)
            .from(companySubsidy)
            .where(booleanBuilder)
            .fetchFirst() ?: 0L
    }

    fun findListByIdList(
        ids: List<Long>,
    ): List<CompanySubsidy> {
        return queryFactory
            .selectFrom(companySubsidy)
            .where(
                companySubsidy.id.`in`(ids)
                    .and(companySubsidy.deletedAt.isNull)
            )
            .fetch()
    }

    fun findOffsetPageBySearch(
        getCompanySubsidiesDto: GetCompanySubsidiesDto,
        pageable: Pageable,
    ): Page<CompanySubsidyGroupByDetailDto> {
        val booleanBuilder = BooleanBuilder()
            .and(shopIdEq(getCompanySubsidiesDto.shopId))
            .and(companySubsidy.deletedAt.isNull)

        getCompanySubsidiesDto.openType?.let {
            booleanBuilder.and(companySubsidy.openType.eq(it))
        }
        getCompanySubsidiesDto.discountType?.let {
            booleanBuilder.and(companySubsidy.discountType.eq(it))
        }
        getCompanySubsidiesDto.priceMin?.let {
            booleanBuilder.and(companySubsidy.price.goe(it))
        }
        getCompanySubsidiesDto.priceMax?.let {
            booleanBuilder.and(companySubsidy.price.loe(it))
        }
        getCompanySubsidiesDto.telecomId?.let {
            booleanBuilder.and(companySubsidy.telecom.id.eq(it))
        }
        getCompanySubsidiesDto.phonePlanId?.let {
            booleanBuilder.and(companySubsidy.phonePlan.id.eq(it))
        }
        getCompanySubsidiesDto.deviceId?.let {
            booleanBuilder.and(companySubsidy.device.id.eq(it))
        }

        val query = queryFactory.from(companySubsidy)
            .innerJoin(telecom).on(
                companySubsidy.telecom.id.eq(telecom.id),
                telecom.deletedAt.isNull,
            )
            .innerJoin(phonePlan).on(
                companySubsidy.phonePlan.id.eq(phonePlan.id),
                phonePlan.deletedAt.isNull,
            )
            .innerJoin(device).on(
                companySubsidy.device.id.eq(device.id),
                device.deletedAt.isNull,
            )
            .innerJoin(shop).on(
                companySubsidy.shop.id.eq(shop.id),
                shop.deletedAt.isNull,
            )
            .where(booleanBuilder)

        // 기존의 전체를 groupby로 가져와서 transform하는 방식은 details를 온전히 가져오지 못함
        // 그래서 group by를 openType, discountType을 제외한 뒤 먼저 공통 정보를 가져오고
        // 나머지 정보를 가져오는 방식으로 변경
        val result: List<CompanySubsidyGroupByDetailDto> = query.clone()
            .select(
                QCompanySubsidyGroupByDetailDto(
                    QTelecomDto(
                        companySubsidy.telecom.id,
                        companySubsidy.telecom.name,
                    ),
                    QPhonePlanRawDto(
                        companySubsidy.phonePlan.id,
                        companySubsidy.phonePlan.name,
                        companySubsidy.phonePlan.price,
                        companySubsidy.phonePlan.category,
                        companySubsidy.phonePlan.callExp,
                        companySubsidy.phonePlan.dataExp,
                        companySubsidy.phonePlan.mailExp,
                    ),
                    QDeviceRawDto(
                        companySubsidy.device.id,
                        companySubsidy.device.petName,
                        companySubsidy.device.modelName,
                        companySubsidy.device.price,
                        companySubsidy.device.volume,
                        companySubsidy.device.series,
                        companySubsidy.device.createdAt,
                        companySubsidy.device.updatedAt,
                    ),
                    QShopSimpleDto(
                        companySubsidy.shop.id,
                        companySubsidy.shop.name,
                    ),
                )
            )
            .pagination(pageable)
            .groupBy(
                telecom,
                phonePlan,
                device,
                shop,
            )
            .fetch()

        // details setting
        result.forEach { commonInfo ->
            val detailList: List<CompanySubsidyGroupByDetailDto.Detail> = query.clone()
                .select(
                    QCompanySubsidyGroupByDetailDto_Detail(
                        companySubsidy.id,
                        companySubsidy.price,
                        companySubsidy.openType,
                        companySubsidy.discountType,
                    )
                )
                .innerJoin(telecom).on(
                    companySubsidy.telecom.id.eq(telecom.id),
                    telecom.deletedAt.isNull,
                )
                .innerJoin(phonePlan).on(
                    companySubsidy.phonePlan.id.eq(phonePlan.id),
                    phonePlan.deletedAt.isNull,
                )
                .innerJoin(device).on(
                    companySubsidy.device.id.eq(device.id),
                    device.deletedAt.isNull,
                )
                .innerJoin(shop).on(
                    companySubsidy.shop.id.eq(shop.id),
                    shop.deletedAt.isNull,
                )
                .where(
                    telecom.id.eq(commonInfo.telecom.id),
                    phonePlan.id.eq(commonInfo.phonePlan.id),
                    device.id.eq(commonInfo.device.id),
                    shop.id.eq(commonInfo.shop.id),
                )
                .fetch()

            commonInfo.detailList = detailList
        }

        /**
         * SELECT COUNT(*) AS row_count
         * FROM (
         *          SELECT 1
         *          FROM tb_company_subsidy companysub0_
         *          GROUP BY companysub0_.telecom_id,
         *                   companysub0_.phone_plan_id,
         *                   companysub0_.device_id,
         *                   companysub0_.shop_id
         *          ORDER BY telecom_id, phone_plan_id, device_id, shop_id ASC
         *      ) AS subquery;
         */

        val count: Long = query.clone()
            .select(Expressions.ONE)
            .groupBy(
                companySubsidy.telecom,
                companySubsidy.phonePlan,
                companySubsidy.device,
                companySubsidy.shop,
            )
            .fetch().size.toLong()

        return PageImpl(result, pageable, count)
//        return repository.findAll(
//            booleanBuilder,
//            pageable,
//        )
    }

    /////

    private fun shopIdEq(shopId: Long?): BooleanExpression? {
        return shopId?.let { companySubsidy.shop.id.eq(it) }
    }
}

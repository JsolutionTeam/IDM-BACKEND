package kr.co.jsol.domain.deviceapplicationform.infrastructure.query

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import kr.co.jsol.common.config.pagination
import kr.co.jsol.common.exception.domain.deviceapplicationform.DeviceApplicationFormException
import kr.co.jsol.common.repository.BaseQueryRepository
import kr.co.jsol.domain.deviceapplicationform.application.dto.GetDeviceApplicationFormsDto
import kr.co.jsol.domain.deviceapplicationform.entity.DeviceApplicationForm
import kr.co.jsol.domain.deviceapplicationform.entity.QDeviceApplicationForm.Companion.deviceApplicationForm
import kr.co.jsol.domain.deviceapplicationform.infrastructure.repository.DeviceApplicationFormRepository
import kr.co.jsol.domain.insurance.entity.QInsurance.Companion.insurance
import kr.co.jsol.domain.phoneplan.entity.QPhonePlan.Companion.phonePlan
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class DeviceApplicationFormQueryRepository(
    private val repository: DeviceApplicationFormRepository,
    private val queryFactory: JPAQueryFactory,
) : BaseQueryRepository<DeviceApplicationForm, Long>(deviceApplicationForm, repository) {

    fun getById(id: Long): DeviceApplicationForm {
        return findById(id) ?: throw DeviceApplicationFormException.NotFoundByIdException()
    }

    fun findOffsetPageBySearch(
        getDeviceApplicationFormsDto: GetDeviceApplicationFormsDto,
        pageable: Pageable,
//    ): Page<DeviceApplicationFormDto> {
    ): Page<DeviceApplicationForm> {
        val booleanBuilder = BooleanBuilder()
            .and(deviceApplicationForm.deletedAt.isNull)
            .and(shopIdEq(getDeviceApplicationFormsDto.shopId))

        val query = queryFactory.from(deviceApplicationForm)
            .pagination(pageable)

        getDeviceApplicationFormsDto.phoneNumber?.let {
            booleanBuilder.and(deviceApplicationForm.phoneNumber.contains(it))
        }
        getDeviceApplicationFormsDto.openType?.let {
            booleanBuilder.and(deviceApplicationForm.openType.eq(it))
        }
        getDeviceApplicationFormsDto.discountType?.let {
            booleanBuilder.and(deviceApplicationForm.discountType.eq(it))
        }
        getDeviceApplicationFormsDto.companySubsidyPriceMin?.let {
            booleanBuilder.and(deviceApplicationForm.companySubsidyPrice.goe(it))
        }
        getDeviceApplicationFormsDto.companySubsidyPriceMax?.let {
            booleanBuilder.and(deviceApplicationForm.companySubsidyPrice.loe(it))
        }
        getDeviceApplicationFormsDto.usimChange?.let {
            booleanBuilder.and(deviceApplicationForm.usimChange.eq(it))
        }
        getDeviceApplicationFormsDto.contractMonthMin?.let {
            booleanBuilder.and(deviceApplicationForm.contractMonth.goe(it))
        }
        getDeviceApplicationFormsDto.contractMonthMax?.let {
            booleanBuilder.and(deviceApplicationForm.contractMonth.loe(it))
        }
        getDeviceApplicationFormsDto.deviceSubsidyPriceMin?.let {
            booleanBuilder.and(deviceApplicationForm.deviceSubsidyPrice.goe(it))
        }
        getDeviceApplicationFormsDto.deviceSubsidyPriceMax?.let {
            booleanBuilder.and(deviceApplicationForm.deviceSubsidyPrice.loe(it))
        }
        getDeviceApplicationFormsDto.prePaymentPriceMin?.let {
            booleanBuilder.and(deviceApplicationForm.prePaymentPrice.goe(it))
        }
        getDeviceApplicationFormsDto.prePaymentPriceMax?.let {
            booleanBuilder.and(deviceApplicationForm.prePaymentPrice.loe(it))
        }
        getDeviceApplicationFormsDto.installmentMonthMin?.let {
            booleanBuilder.and(deviceApplicationForm.installmentMonth.goe(it))
        }
        getDeviceApplicationFormsDto.installmentMonthMax?.let {
            booleanBuilder.and(deviceApplicationForm.installmentMonth.loe(it))
        }
        getDeviceApplicationFormsDto.memo?.let {
            booleanBuilder.and(deviceApplicationForm.memo.contains(it))
        }
        getDeviceApplicationFormsDto.telecomName?.let {
//            query.leftJoin(deviceApplicationForm.telecom, telecom).fetchJoin()
            booleanBuilder.and(deviceApplicationForm.telecom.name.contains(it))
        }

//        var isLeftJoinedDevice = false
        getDeviceApplicationFormsDto.deviceModelName?.let {
//            query.leftJoin(deviceApplicationForm.deviceInfo, deviceInfo).fetchJoin()
//                .leftJoin(deviceInfo.device, device).fetchJoin()
//            isLeftJoinedDevice = true
            booleanBuilder.and(deviceApplicationForm.deviceInfo.device.modelName.contains(it))
        }
        getDeviceApplicationFormsDto.devicePetName?.let {
//            if (!isLeftJoinedDevice) {
//                query.leftJoin(deviceApplicationForm.deviceInfo, deviceInfo).fetchJoin()
//                    .leftJoin(deviceInfo.device, device).fetchJoin()
//            }
            booleanBuilder.and(deviceApplicationForm.deviceInfo.device.petName.contains(it))
        }
        getDeviceApplicationFormsDto.phonePlanName?.let {
            booleanBuilder.and(phonePlan.name.contains(it))
        }
        getDeviceApplicationFormsDto.insuranceName?.let {
            booleanBuilder.and(insurance.name.contains(it))
        }

//        val result = query.clone()
//            .select(deviceApplicationForm)
//            .where(booleanBuilder)
//            .fetch()
//
//        val count = query.clone()
//            .select(deviceApplicationForm.count())
//            .fetchOne() ?: 0L
//
//        val response = result.map {
//            DeviceApplicationFormDto(
//                it,
//                queryFactory.selectFrom(deviceApplicationFormSubservice)
//                    .where(deviceApplicationFormSubservice.deviceApplicationForm.id.eq(it.id))
//                    .fetch()
//            )
//        }

//        return PageImpl(response, pageable, count)
        return repository.findAll(booleanBuilder, pageable)
    }

    /////

    private fun shopIdEq(shopId: Long?): BooleanExpression? {
        return shopId?.let { deviceApplicationForm.shop.id.eq(it) }
    }
}


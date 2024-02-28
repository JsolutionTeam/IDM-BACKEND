package kr.co.jsol.domain.shop.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.jsol.common.domain.AccountAuthority
import kr.co.jsol.domain.shop.entity.enums.ShopStatus
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Schema(name = "업체 생성 요청")
data class CreateCompanyDto(
    @field:NotBlank(message = "업체명은 필수입니다.")
    @field:Size(max = 100, message = "업체명은 100자 이내로 입력해주세요.")
    @field:Schema(description = "업체명", example = "제이솔루션")
    val name: String,

    @field:Schema(description = "업체 상태 null로 입력시 REQUEST로 처리합니다.", example = "DONE", implementation = ShopStatus::class)
    val status: ShopStatus? = null, // default

    @field:NotBlank(message = "전화번호는 필수입니다.")
    @field:Size(max = 20, message = "전화번호는 20자 이내로 입력해주세요.")
    @field:Schema(description = "전화번호", example = "010-1234-5678")
    val phone: String,

    @field:NotBlank(message = "관리자 아이디는 필수입니다.")
    @field:Size(max = 100, message = "관리자 아이디는 100자 이내로 입력해주세요.")
    @field:Schema(description = "관리자 아이디", example = "manager")
    val managerId: String,

    @field:NotBlank(message = "관리자 비밀번호는 필수입니다.")
    @field:Size(max = 100, message = "관리자 비밀번호는 100자 이내로 입력해주세요.")
    @field:Schema(description = "관리자 비밀번호", example = "manager123!@#")
    val managerPassword: String,

    @field:NotBlank(message = "관리자 이름은 필수입니다.")
    @field:Size(max = 100, message = "관리자 이름은 100자 이내로 입력해주세요.")
    @field:Schema(description = "관리자 이름", example = "관리자")
    val managerName: String,

    @field:NotBlank(message = "관리자 전화번호는 필수입니다.")
    @field:Size(max = 20, message = "관리자 전화번호는 20자 이내로 입력해주세요.")
    @field:Schema(description = "관리자 전화번호", example = "010-1234-5678")
    val managerPhone: String,
) {
    fun toEntity() = kr.co.jsol.domain.shop.entity.Shop(
        name = name,
        role = AccountAuthority.COMPANY,
        status = status ?: ShopStatus.REQUEST,
        phone = phone,
        managerId = managerId,
        managerName = managerName,
        managerPhone = managerPhone,
    )
}

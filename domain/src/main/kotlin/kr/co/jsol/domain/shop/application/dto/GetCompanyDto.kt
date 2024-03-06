package kr.co.jsol.domain.shop.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.Size

@Schema(name = "업체 생성 요청")
data class GetCompanyDto(
    @field:Size(max = 100, message = "업체명은 100자 이내로 입력해주세요.")
    @field:Schema(description = "업체명", example = "제이솔루션")
    val name: String? = null,

    @field:Size(max = 20, message = "전화번호는 20자 이내로 입력해주세요.")
    @field:Schema(description = "전화번호", example = "010-1234-5678")
    val phone: String? = null,

    @field:Size(max = 100, message = "관리자 아이디는 100자 이내로 입력해주세요.")
    @field:Schema(description = "관리자 아이디", example = "manager")
    val managerId: String? = null,

    @field:Size(max = 100, message = "관리자 이름은 100자 이내로 입력해주세요.")
    @field:Schema(description = "관리자 이름", example = "관리자")
    val managerName: String? = null,

    @field:Size(max = 20, message = "관리자 전화번호는 20자 이내로 입력해주세요.")
    @field:Schema(description = "관리자 전화번호", example = "010-1234-5678")
    val managerPhone: String? = null,
)

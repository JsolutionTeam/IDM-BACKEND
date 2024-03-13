package kr.co.jsol.domain.telecom.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import org.springdoc.api.annotations.ParameterObject
import javax.validation.constraints.Size

@ParameterObject
@Schema(
    name = "통신사 리스트 조회 요청",
    description = """"
    페이지 조회가 아님(많아도 20개를 넘지 않기 때문에 페이징 처리하지 않음) 
    """,
)
data class GetTelecomDto(
    @field:Size(max = 255, message = "통신사 이름은 255자 이하로 입력해주세요.")
    @field:Schema(description = "통신사 이름", example = "SKT")
    val name: String?,
)

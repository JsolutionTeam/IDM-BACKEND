package kr.co.jsol.api.controller

import io.swagger.annotations.Api
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.RequestBody
import javax.validation.Valid

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/plan")
@Api(description = "요금제 관련 API")
class PlanController {
    private val planService: PlanService? = null

    @ApiOperation("등록 = 마스터롤만 가능")
    @PostMapping("")
    fun addPlan(
        @RequestBody
        planDto: @Valid PlanDto?,
        @AuthenticationPrincipal
        account: Account
    ): ResponseEntity<ResponseDto> {
        val role: String = account.getRole()

        val result: Long = planService.addPlan(planDto, role)

        return ResponseEntity<ResponseDto>(ResponseDto(result, true, "요금제를 등록 하였습니다."), HttpStatus.OK)
    }

    @get:GetMapping("")
    @get:ApiOperation("조회 = 모든롤 가능")
    val plan: ResponseEntity<List<PlanResDto>>
        get() {
            val planList: List<PlanResDto> = planService.getPlan()

            return ResponseEntity<List<PlanResDto>>(planList, HttpStatus.OK)
        }

    @ApiOperation("수정 = 마스터롤만 가능")
    @PutMapping("{planIdx}")
    fun updatePlan(
        @RequestBody
        planDto: @Valid PlanDto?,
        @PathVariable
        planIdx: Long?,
        @AuthenticationPrincipal
        account: Account
    ): ResponseEntity<ResponseDto> {
        val role: String = account.getRole()

        val result: Long = planService.updatePlan(planDto, planIdx, role)

        return ResponseEntity<ResponseDto>(ResponseDto(result, true, "요금제를 수정하였습니다."), HttpStatus.OK)
    }

    @ApiOperation("삭제 = 마스터롤만 가능")
    @DeleteMapping("/{planIdx}")
    fun deletePlan(
        @PathVariable
        planIdx: Long?,
        @AuthenticationPrincipal
        account: Account
    ): ResponseEntity<ResponseDto> {
        val role: String = account.getRole()

        val result: Boolean = planService.deletePlan(planIdx, role)

        return ResponseEntity<ResponseDto>(ResponseDto(result, "요금제를 삭제하였습니다."), HttpStatus.OK)
    }
}


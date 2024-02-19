//package kr.co.jsol.api.controller
//
//import io.swagger.annotations.Api
//import org.springframework.http.HttpStatus
//import org.springframework.security.core.annotation.AuthenticationPrincipal
//import org.springframework.web.bind.annotation.RequestBody
//import javax.validation.Valid
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/v1/discount")
//@Api(description = "추가지원금 관련 API")
//class DiscountController {
//    private val discountService: DiscountService? = null
//
//    @ApiOperation("지원금 등록 = 마스터롤만 가능")
//    @PostMapping("")
//    fun addDiscount(
//        @RequestBody
//        discountDto: @Valid DiscountDto?,
//        @AuthenticationPrincipal
//        account: Account
//    ): ResponseEntity<ResponseDto> {
//        val role: String = account.getRole()
//
//        val result: Long = discountService.addDiscount(discountDto, role)
//
//        return ResponseEntity<ResponseDto>(ResponseDto(result, true, "지원금을 등록 하였습니다."), HttpStatus.OK)
//    }
//
//    @get:GetMapping("")
//    @get:ApiOperation("지원금 조회 = 모든 인원이 가능")
//    val discount: ResponseEntity<List<DiscountResDto>>
//        get() {
//            val discountList: List<DiscountResDto> = discountService.getDiscount()
//
//            return ResponseEntity<List<DiscountResDto>>(discountList, HttpStatus.OK)
//        }
//
//    @ApiOperation("지원금 수정 = 마스터롤만 가능")
//    @PutMapping("/{discountIdx}")
//    fun updateDiscount(
//        @RequestBody
//        discountDto: @Valid DiscountDto?,
//        @PathVariable
//        discountIdx: Long?,
//        @AuthenticationPrincipal
//        account: Account
//    ): ResponseEntity<ResponseDto> {
//        val role: String = account.getRole()
//
//        val result: Long = discountService.updateDiscount(discountIdx, discountDto, role)
//
//        return ResponseEntity<ResponseDto>(ResponseDto(result, true, " 지원금이 수정 되었습니다."), HttpStatus.OK)
//    }
//
//    @ApiOperation("지원금 삭제 = 마스터롤만 가능")
//    @DeleteMapping("/{modelNumber}")
//    fun deleteDiscount(
//        @PathVariable
//        modelNumber: Long?,
//        @AuthenticationPrincipal
//        account: Account
//    ): ResponseEntity<ResponseDto> {
//        val role: String = account.getRole()
//
//        val result: Boolean = discountService.deleteDiscount(modelNumber, role)
//
//        return ResponseEntity<ResponseDto>(ResponseDto(result, "지원금 삭제가 완료 되었습니다."), HttpStatus.OK)
//    }
//}
